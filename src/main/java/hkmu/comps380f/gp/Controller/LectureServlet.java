/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package hkmu.comps380f.gp.Controller;

import hkmu.comps380f.gp.Model.Lecture;
import hkmu.comps380f.gp.Model.LectureComment;
import hkmu.comps380f.gp.Model.Material;
import hkmu.comps380f.gp.dao.LectureCommentRepository;
import hkmu.comps380f.gp.dao.LectureRepository;
import hkmu.comps380f.gp.dao.MaterialRepository;
import hkmu.comps380f.gp.service.LectureCommentService;
import hkmu.comps380f.gp.service.LectureService;
import hkmu.comps380f.gp.service.MaterialService;
import hkmu.comps380f.gp.view.DownloadingView;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author emilylau
 */
// @WebServlet(name = "PollServlet", urlPatterns = {"/poll"})

@Controller
@RequestMapping("/lecture")
public class LectureServlet extends HttpServlet {
    @Autowired
    LectureService LectureService;
    @Autowired
    LectureRepository LectureRepo;
    @Autowired
    LectureCommentService LectureCommentService;
    @Autowired
    LectureCommentRepository LectureCommentRepo;
    @Autowired
    MaterialService MaterialService;
    @Autowired
    MaterialRepository MaterialRepo;

    public class Form{
        private String title;
        private List<MultipartFile> materials;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MultipartFile> getMaterials() {
            return materials;
        }

        public void setMaterials(List<MultipartFile> materials) {
            this.materials = materials;
        }

        

     }
    public class CommentForm{
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

            
    }

    @GetMapping("/{lectureId}")
    public ModelAndView lectureList(@PathVariable("lectureId") Integer lectureId, HttpServletRequest request, Principal principal)
                throws Exception{
        ModelAndView lecturePage = new ModelAndView("lecturePage");
        Lecture lecture = LectureRepo.findById(lectureId).orElse(null);
        
        String title = lecture.getTitle();

        lecturePage.addObject("title", title);
        lecturePage.addObject("material", lecture.getMaterials());
        
        
        List<LectureComment> commentsSet = lecture.getComments();
                
        lecturePage.addObject("comments", commentsSet);
        return lecturePage;
    }

    @GetMapping("/{lectureId}/material/{material:.+}")
    public View download(@PathVariable("lectureId") Integer lectureId,
            @PathVariable("material") String name) {

        Material material = MaterialService.getMaterial(lectureId, name);
        if (material != null) {
            return new DownloadingView(material.getName(),
                    material.getMimeContentType(), material.getContents());
        }
        return new RedirectView("/", true);
    }

    @GetMapping("/create")
    public ModelAndView lectureCreateForm(){
        ModelAndView lectureForm = new ModelAndView("lectureForm","lecture",new Form());
        lectureForm.addObject("action", "createForm");
        return lectureForm;
    }

    @PostMapping("/create")
        public String lectureCreate(ModelMap map, @ModelAttribute("lecture") Form form) 
                        throws Exception{

            LectureService.createLecture(form.getTitle(), form.getMaterials());
            System.out.println("lecture inserted");

            return "redirect:../";
        }

    @GetMapping("/{lectureId}/delete")
    public String deletePoll(@PathVariable("lectureId") Integer lectureId) throws Exception{
        LectureRepo.delete(LectureRepo.findById(lectureId).orElse(null));

        return "redirect:../";
    }

    @GetMapping("/{lectureId}/comment/create")
    public ModelAndView commentCreateForm(){
        // ModelAndView pollCommentForm = new ModelAndView("pollCommentForm","Poll",new Poll());
        return new ModelAndView("lectureCommentForm","comment",new CommentForm());
    }

    @PostMapping("/{lectureId}/comment/create")
    public String commentCreate(@PathVariable("lectureId") Integer lectureId, 
                                @ModelAttribute("comment") CommentForm commentForm,
                                Principal principal)
                    throws Exception{
        LectureCommentService.createLectureComment(lectureId, principal.getName(), commentForm.getContent());      
        return "redirect:..";
        
    }

    @GetMapping("/{lectureId}/comment/{commentId}/delete")
    public String commentDelete(@PathVariable("lectureId") Integer lectureId, @PathVariable("commentId") Integer commentId)
                        throws Exception{

        LectureCommentService.deleteLectureComment(lectureId, commentId);
        System.out.println(commentId + " Comment deleted");
        return "redirect:../../";

    }

}

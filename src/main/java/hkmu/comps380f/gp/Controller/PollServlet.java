/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package hkmu.comps380f.gp.Controller;

import hkmu.comps380f.gp.Model.Poll;
import hkmu.comps380f.gp.Model.PollComment;
import hkmu.comps380f.gp.Model.Vote;
import hkmu.comps380f.gp.dao.PollCommentRepository;
import hkmu.comps380f.gp.dao.PollRepository;
import hkmu.comps380f.gp.dao.VoteRepository;
import hkmu.comps380f.gp.service.PollCommentService;
import hkmu.comps380f.gp.service.PollService;
import hkmu.comps380f.gp.service.VoteService;
import java.security.Principal;
import java.sql.ResultSet;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author emilylau
 */
// @WebServlet(name = "PollServlet", urlPatterns = {"/poll"})

@Controller
@RequestMapping("/poll")
public class PollServlet extends HttpServlet {
    @Autowired
    PollService PollService;
    @Autowired
    PollRepository PollRepo;
    @Autowired
    VoteService VoteService;
    @Autowired
    VoteRepository VoteRepo;
    @Autowired
    PollCommentService PollCommentService;
    @Autowired
    PollCommentRepository PollCommentRepo;

    public class Form{
        private String question;
        private String option1;
        private String option2;
        private String option3;
        private String option4;

      
        public String getQuestion() {
            return question;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }

        public String getOption4() {
            return option4;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }

        public void setOption3(String option3) {
            this.option3 = option3;
        }

        public void setOption4(String option4) {
            this.option4 = option4;
        }

    }
    public class VoteForm{
            private String voteOption;

            public String getVoteOption() {
                return voteOption;
            }

            public void setVoteOption(String votOption) {
                this.voteOption = voteOption;
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

    @GetMapping("/{pollId}")
    public ModelAndView pollList(@PathVariable("pollId") Integer pollId, @ModelAttribute("vote") VoteForm voteForm, HttpServletRequest request, Principal principal)
                throws Exception{
        ModelAndView pollPage = new ModelAndView("pollPage");
        Integer voteOption = 0;
        Poll poll = PollRepo.findById(pollId).orElse(null);
        
        String question = poll.getQuestion();
        String option1 = poll.getOption1();
        String option2 = poll.getOption2();
        String option3 = poll.getOption3();
        String option4 = poll.getOption4();

        pollPage.addObject("question", question);
        pollPage.addObject("option1", option1);
        pollPage.addObject("option2", option2);
        pollPage.addObject("option3", option3);
        pollPage.addObject("option4", option4);

        List<Vote> voteHistoryList = VoteRepo.readByPollIdAndUsernameOrderByCreatedAtDesc(pollId, principal.getName());
        if(voteHistoryList.size() != 0){
            voteOption = voteHistoryList.get(0).getVoteOption();
        }
        pollPage.addObject("histories", voteHistoryList);

        List<PollComment> commentsSet = poll.getComments();
        List<Vote> voteList = poll.getVotes();
        Integer[] voteOptionTotal = {0,0,0,0};
        List<String> userList = VoteRepo.findDistinctUsername();


        for(String username:userList){
            Vote lastestVote = VoteRepo.findFirstByPollIdAndUsernameOrderByCreatedAtDesc(pollId,username);
            Integer option = lastestVote.getVoteOption();
            if(option ==1){
                voteOptionTotal[0] += 1;
            }
            if(option == 2){
                voteOptionTotal[1] += 1;
            }
            if(option == 3){
                voteOptionTotal[2] += 1;
            }
            if(option == 4){
                voteOptionTotal[3] += 1;
            }
        }
        
        
        System.out.println("voteOptionTotal: "+voteOptionTotal);
        pollPage.addObject("voteOptionTotal", voteOptionTotal);  
        pollPage.addObject("voteOption", voteOption);    
        pollPage.addObject("comments", commentsSet);
        //pollPage.addObject("vote", new Vote());
        return pollPage;
    }

    @GetMapping("/create")
    public ModelAndView pollCreateForm(){
        ModelAndView pollForm = new ModelAndView("pollForm","poll",new Form());
        pollForm.addObject("action", "createForm");
        return pollForm;
    }

    @PostMapping("/create")
        public String pollCreate(ModelMap map, @ModelAttribute("poll") Form form) 
                        throws Exception{

            Poll poll = new Poll(form.getQuestion(), form.getOption1(), form.getOption2(), form.getOption3(), form.getOption4());
            PollRepo.save(poll);
            System.out.println("poll inserted");

            return "redirect:../";
        }

    @GetMapping("/{pollId}/delete")
    public String deletePoll(@PathVariable("pollId") Integer pollId) throws Exception{
        PollRepo.delete(PollRepo.findById(pollId).orElse(null));

        return "redirect:../";
    }

    @GetMapping("/{pollId}/comment/create")
    public ModelAndView commentCreateForm(){
        // ModelAndView pollCommentForm = new ModelAndView("pollCommentForm","Poll",new Poll());
        return new ModelAndView("pollCommentForm","comment",new CommentForm());
    }

    @PostMapping("/{pollId}/comment/create")
    public String commentCreate(@PathVariable("pollId") Integer pollId, 
                                @ModelAttribute("comment") CommentForm commentForm,
                                Principal principal)
                    throws Exception{
        PollCommentService.createPollComment(pollId, principal.getName(), commentForm.getContent());      
        return "redirect:..";
        
    }

    @GetMapping("/{pollId}/comment/{commentId}/delete")
    public String commentDeleteOne(@PathVariable("pollId") Integer pollId, @PathVariable("commentId") Integer commentId)
                        throws Exception{

        PollCommentService.deletePollComment(pollId, commentId);
        System.out.println(commentId + " Comment deleted");
        return "redirect:../../";

    }

    @PostMapping("/{pollId}/vote")
    public String voteCreate(@PathVariable("pollId") Integer pollId, 
                                @ModelAttribute("vote") Vote theVote,
                                Principal principal)
                    throws Exception{
        String username = principal.getName();
        VoteService.createVote(pollId, username, theVote.getVoteOption());

      
        return "redirect:../../";
        
    }
/*
    @PostMapping("/{pollId}/vote/edit")
    public String voteEdit(@PathVariable("pollId") Integer pollId, 
                                @ModelAttribute("vote") Vote theVote,
                                Principal principal)
                    throws Exception{
        String username = principal.getName();
        Poll poll = PollRepo.findById(pollId).orElse(null);
        List<Vote> votes = poll.getVotes();
for (Vote vote: votes){
if(vote.getUsername().equals(username)){
vote.setVoteOption(theVote.getVoteOption());
vote.setCreatedAt(new Date());
}

}
        VoteService.createVote(pollId, username, theVote.getVoteOption());

      
        return "redirect:../../";
        
    }


    private ResultSet getPoll(String pollId)
                    throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement(); 

        String thePollQuery = "SELECT * FROM polls WHERE poll_id = "+ Integer.parseInt(pollId) ;
        ResultSet rs = stmt.executeQuery(thePollQuery);
        System.out.println("Poll"+ pollId +" found: " + rs);
        return rs;
    }

    private ResultSet getComment(String pollId) throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement(); 

        String commentQuery = "SELECT * FROM comments WHERE poll_id = "+ Integer.parseInt(pollId);
        ResultSet rs = stmt.executeQuery(commentQuery);
        System.out.println("comment found: "+ rs);
        return rs;
    }

    private ResultSet getVote(String pollId, String username) throws Exception{
        //Registering the driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Getting the Connection object
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        //Creating the Statement object
        Statement stmt = conn.createStatement(); 


        String voteQuery = "SELECT * FROM votes WHERE poll_id = "+ Integer.parseInt(pollId) +
                            "AND username = '" + username +"'";
        ResultSet rs = stmt.executeQuery(voteQuery);
        System.out.println("vote found: "+ rs);
        return rs;
    }

    private void insertPoll(Poll poll) throws Exception{
        //Registering the driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Getting the Connection object
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        //Creating the Statement object
        Statement stmt = conn.createStatement(); 

        String question = poll.getQuestion();
        String username = poll.getUsername();
        String option1 = poll.getOption1();
        String option2 = poll.getOption2();
        String option3 = poll.getOption3();
        String option4 = poll.getOption4();

        String pollQuery = "INSERT INTO polls (question, username, option1, option2, option3, option4) VALUES "
                        + "('"+question+"', '" 
                        + username+"','"
                        + option1+"','"
                        + option2 +"',' "
                        + option3+"', '"
                        + option4+"')";
        stmt.execute(pollQuery);
        System.out.println("poll inserted");
    }

    private void insertComment(Comment comment) throws Exception{
        //Registering the driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Getting the Connection object
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        //Creating the Statement object
        Statement stmt = conn.createStatement(); 

        Integer pollId = comment.getPollId();
        String username = comment.getUsername();
        String content = comment.getContent();

        String commentQuery = "INSERT INTO comments (poll_id, username, content) VALUES "
                        + "("+Integer.toString(pollId)+",'" 
                        + username + "','"
                        + content + "')";
        stmt.execute(commentQuery);
        System.out.println("poll comment inserted");
    }

    private void insertVote(Vote vote) throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement(); 

        Integer pollId = vote.getPollId();
        String username = vote.getUsername();
        Integer voteOption = Integer.parseInt(vote.getVoteOption());

        String voteQuery = "INSERT INTO votes VALUES "
                        + "("+pollId+",'" 
                        + username + "',"
                        + voteOption + ")";
        stmt.execute(voteQuery);
        System.out.println("poll vote inserted");
    }

    private void editVote(String pollId, String username, String voteOption) throws Exception{
        //Registering the driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Getting the Connection object
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        //Creating the Statement object
        Statement stmt = conn.createStatement(); 


        String voteEditQuery = "UPDATE votes SET voteOption ="
                        + Integer.parseInt(voteOption)
                        +"WHERE poll_id = " + Integer.parseInt(pollId)
                        +"AND username = '" + username + "'";
        int num = stmt.executeUpdate(voteEditQuery);
        System.out.println(num + " poll vote updated");

    }
   
    private void deleteComment(String pollId, Integer commentId) throws Exception{
        //Registering the driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Getting the Connection object
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        //Creating the Statement object
        Statement stmt = conn.createStatement(); 
        String commentDeleteQuery = "DELETE FROM comments WHERE comment_id = "
                            + commentId;
        if(commentId == 0){//DO NOT have specific commentId
            commentDeleteQuery = "DELETE FROM comments WHERE poll_id = "
                            + Integer.parseInt(pollId);
        }
        int num = stmt.executeUpdate(commentDeleteQuery);
        System.out.println(num + " poll comment deleted");
    }

    private void deleteVote(String pollId) throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String URL = "jdbc:derby://localhost:1527/Poll;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        Statement stmt = conn.createStatement(); 
        
        String voteDeleteQuery = "DELETE FROM votes WHERE poll_id = "
                            + Integer.parseInt(pollId);
        int num = stmt.executeUpdate(voteDeleteQuery);   
        System.out.println(num + " poll vote deleted");
    }   
*/
}

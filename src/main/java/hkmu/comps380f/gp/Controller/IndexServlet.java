package hkmu.comps380f.gp.Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import hkmu.comps380f.gp.Model.Lecture;
import hkmu.comps380f.gp.Model.Poll;
import hkmu.comps380f.gp.dao.LectureRepository;
import hkmu.comps380f.gp.dao.PollRepository;
import hkmu.comps380f.gp.service.LectureService;
import hkmu.comps380f.gp.service.PollService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author emilylau
 */
//@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
@Controller
public class IndexServlet extends HttpServlet {
    @Autowired
    PollService PollService;
    @Autowired
    PollRepository PollRepo;    
    @Autowired
    LectureService LectureService;
    @Autowired
    LectureRepository LectureRepo; 
    

    @GetMapping("")
    public ModelAndView index() throws Exception{
        ModelAndView index = new ModelAndView("index");
        List<Poll> pollList = PollRepo.findAll();
        List<Lecture> lectureList = LectureRepo.findAll();
        //Poll pollList = PollRepo.findById(11).orElse(null);
        index.addObject("pollList", pollList);
        index.addObject("lectureList", lectureList);
        return index;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.service;

import hkmu.comps380f.gp.Model.Poll;
import hkmu.comps380f.gp.dao.PollRepository;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author emilylau
 */
@Service
public class PollService{ 
    @Resource
    PollRepository PollRepo;
    
    public void updatePoll(Integer pollId, String question, String username, String option1, String option2, String option3, String option4){
        Poll poll = PollRepo.findById(pollId).orElse(null);
        poll.setQuestion(question);
        poll.setUsername(username);
        poll.setOption1(option1);
        poll.setOption2(option2);
        poll.setOption3(option3);
        poll.setOption4(option4);
        PollRepo.save(poll);
    }
}

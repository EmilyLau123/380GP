/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.service;

import hkmu.comps380f.gp.Model.Vote;
import hkmu.comps380f.gp.dao.VoteRepository;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author emilylau
 */
@Service
public class VoteService{ 
    @Resource
    VoteRepository VoteRepo;
    
    public void updateVote(Integer pollId, String username, String voteOption){
        Vote vote = VoteRepo.findByPollIdAndUsername(pollId, username);
        vote.setVoteOption(voteOption);
        VoteRepo.save(vote);
    }
}

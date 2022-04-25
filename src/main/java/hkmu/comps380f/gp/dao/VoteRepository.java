/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.dao;

import hkmu.comps380f.gp.Model.Vote;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author emilylau
 */
public interface VoteRepository extends JpaRepository<Vote, String> { 
    public List<Vote> readByPollIdAndUsernameOrderByCreatedAtDesc(Integer pollId, String username);


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.dao;

import hkmu.comps380f.gp.Model.Vote;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author emilylau
 */
public interface VoteRepository extends JpaRepository<Vote, String> { 
    public List<Vote> readByPollIdAndUsernameOrderByCreatedAtDesc(Integer pollId, String username);
    //@Query("select count(distinct v.voteoption) as total,v.voteoption from votes v inner join (select username, max(created_at) as MaxDate from votes group by username) tm on v.username = tm.username and v.created_at = tm.MaxDate group by v.voteoption")
    //public ResultSet CountTotalVoteOfOption(Integer voteOption);
    @Query("SELECT DISTINCT username FROM Vote")
    public List<String> findDistinctUsername();
    public Vote findFirstByPollIdAndUsernameOrderByCreatedAtDesc(Integer pollId, String username);

}

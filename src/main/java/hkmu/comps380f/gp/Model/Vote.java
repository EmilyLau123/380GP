/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author emilylau
 */
@Entity
@Table(name = "votes")
public class Vote implements Serializable{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String username;
        private Integer voteOption;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at")
        private Date createdAt;
        //@Column(insertable = false, updatable = false)
       // private Integer pollId;
        @ManyToOne
        @JoinColumn(name = "poll_id")
        private Poll poll;

        public Vote(){}

        public Vote(String username, Integer voteOption){
            this.username = username;
            this.voteOption = voteOption;
        } 

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public Integer getVoteOption() {
            return voteOption;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Poll getPoll() {
            return poll;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setVoteOption(Integer voteOption) {
            this.voteOption = voteOption;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public void setPoll(Poll poll) {
            this.poll = poll;
        }

    }



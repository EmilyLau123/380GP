/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author emilylau
 */
@Entity
@Table(name = "votes")
public class Vote implements Serializable{
        @Id
        private String username;
        private String voteOption;
        @OneToOne
        @JoinColumn(name = "poll_id")
        private Poll poll;

        public String getUsername() {
            return username;
        }

        public String getVoteOption() {
            return voteOption;
        }

        public Poll getPoll() {
            return poll;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setVoteOption(String voteOption) {
            this.voteOption = voteOption;
        }

        public void setPoll(Poll poll) {
            this.poll = poll;
        }

    }



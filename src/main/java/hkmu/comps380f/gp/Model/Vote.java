/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;

/**
 *
 * @author emilylau
 */
public class Vote implements Serializable{
        private Integer pollId;
        private String username;
        private String voteOption;

        public Integer getPollId() {
            return pollId;
        }

        public String getUsername() {
            return username;
        }

        public String getVoteOption() {
            return voteOption;
        }

        public void setPollId(Integer pollId) {
            this.pollId = pollId;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setVoteOption(String voteOption) {
            this.voteOption = voteOption;
        }
    }



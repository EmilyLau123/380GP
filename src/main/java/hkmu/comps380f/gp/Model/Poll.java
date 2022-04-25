/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author emilylau
 */
@Entity
@Table(name = "polls")
public class Poll implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<PollComment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Vote> votes = new ArrayList<>();

    public Poll(){}

    public Poll(String question, String option1, String option2, String option3, String option4){
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    //Setter
    public void setPollId(Integer id) {
        this.id = id;
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

    public void setComments(List<PollComment> comments) {
        this.comments = comments;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }



    //Getter
    public Integer getPollId() {
        return id;
    }

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

    public List<PollComment> getComments() {
        return comments;
    }

    public List<Vote> getVotes() {
        return votes;
    }
    

    public void deleteComment(PollComment pollComment) {
        pollComment.setPoll(null);
        this.comments.remove(pollComment);
    }
}

    
   


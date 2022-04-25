/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author emilylau
 */
@Entity
@Table(name = "lecture_comments")
public class LectureComment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer commentId;
    private String username;
    private String content;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    public LectureComment(){}
    public LectureComment(String username, String content){
        this.username = username;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

}
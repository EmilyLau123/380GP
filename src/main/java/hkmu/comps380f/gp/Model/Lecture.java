/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author emilylau
 */
@Entity
@Table(name = "lectures")
public class Lecture implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String title;
    @OneToMany(mappedBy = "lecture", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<LectureComment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "lecture", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Material> materialList = new ArrayList<>();

    public Lecture(){}

    public Lecture(String title){
        this.title = title;
    }

    public Integer getLectureId() {
        return id;
    }

    public void setLectureId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LectureComment> getComments() {
        return commentList;
    }

    public void setComments(List<LectureComment> comments) {
        this.commentList = comments;
    }

    public List<Material> getMaterials() {
        return materialList;
    }

    public void addMaterial(Material material) {
        this.materialList.add(material);
    }

    public void deleteComment(LectureComment lectureComment) {
        lectureComment.setLecture(null);
        this.commentList.remove(lectureComment);
    }

    public void deleteMaterial(Material material) {
        material.setLecture(null);
        this.materialList.remove(material);
    }

}
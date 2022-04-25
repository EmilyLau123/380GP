/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hkmu.comps380f.gp.service;

import hkmu.comps380f.gp.Model.Lecture;
import hkmu.comps380f.gp.Model.Material;
import hkmu.comps380f.gp.dao.LectureRepository;
import hkmu.comps380f.gp.dao.MaterialRepository;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author emilylau
 */
@Service
public class LectureService{ 
    @Resource
    LectureRepository LectureRepo;
    @Resource
    MaterialRepository MaterialRepo;
    
    @Transactional
    public void createLecture(String title, List<MultipartFile> materialList) throws IOException {
        Lecture lecture = new Lecture(); 
        lecture.setTitle(title); 
        for (MultipartFile filePart : materialList) {
            Material material = new Material(); 
            material.setName(filePart.getOriginalFilename()); 
            material.setMimeContentType(filePart.getContentType()); 
            material.setContents(filePart.getBytes()); 
            material.setLecture(lecture);

            if (material.getName() != null) {
                lecture.addMaterial(material); 
                System.out.println(lecture.getMaterials());
            }
        }
        LectureRepo.save(lecture); 
    }
   
}
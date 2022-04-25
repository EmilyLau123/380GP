/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hkmu.comps380f.gp.service;

import hkmu.comps380f.gp.Model.Material;
import hkmu.comps380f.gp.dao.MaterialRepository;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author emilylau
 */
@Service
public class MaterialService{ 
    @Resource
    private MaterialRepository MaterialRepo;
    @Transactional
    public Material getMaterial(Integer lectureId, String name) {
        return MaterialRepo.findByLectureIdAndFilename(lectureId, name);
    }
}
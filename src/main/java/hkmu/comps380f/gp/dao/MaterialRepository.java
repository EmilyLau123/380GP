/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.dao;

import hkmu.comps380f.gp.Model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author emilylau
 */
public interface MaterialRepository extends JpaRepository<Material, Integer> { 
    public Material findByLectureIdAndFilename(Integer lectureId, String name);


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author emilylau
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id

    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserRole role;
    
    public User(){}

    public User(String username, String password, String fullname, String address, String phone, String role){
        this.username = username;
        this.password = "{noop}"+password;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.role = new UserRole(this, role);
    }




    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public UserRole getRole() {
        return role;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = new UserRole(this, role);
    }

    public void setAddress(String address) {
        this.address = address;
    }



}

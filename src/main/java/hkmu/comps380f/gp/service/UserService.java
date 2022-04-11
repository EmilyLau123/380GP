/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.service;

import hkmu.comps380f.gp.dao.UserRepository;
import hkmu.comps380f.gp.Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author emilylau
 */
@Service
public class UserService implements UserDetailsService { 
    @Resource
    UserRepository UserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) 
        throws UsernameNotFoundException {
    User user = UserRepo.findById(username).orElse(null); 
    if (user == null) {
        throw new UsernameNotFoundException("User '" + username + "' not found."); 
    }
    List<GrantedAuthority> authorities = new ArrayList<>(); 
    authorities.add(new SimpleGrantedAuthority(user.getRole().getRole())); 
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities); 
    }

    public void updateUser(String username, String newUsername, String password, String fullname, String phone, String address, String role){
        User user = UserRepo.findById(username).orElse(null);
        user.setUsername(newUsername);
        user.setFullname(fullname);
        user.setPassword("{noop}" + password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        UserRepo.save(user);
    }

}

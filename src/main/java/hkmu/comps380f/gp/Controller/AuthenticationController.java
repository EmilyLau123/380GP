/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.gp.Controller;

import hkmu.comps380f.gp.Controller.UserServlet.Form;
import hkmu.comps380f.gp.Model.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author emilylau
 */

@Controller
public class AuthenticationController extends HttpServlet{
    private ModelAndView indexRedirect() {
        return new ModelAndView(new RedirectView("", true));
    }

    @GetMapping("/sign_up")
    public ModelAndView signUpFor(HttpSession session) {
        if (session.getAttribute("username") != null) { 
            return indexRedirect();

        }
        ModelAndView signUpMV = new ModelAndView("signUp"); 
        signUpMV.addObject("signUpFailed", false); 
        signUpMV.addObject("user", new Form()); 
        return signUpMV;
    }

    @PostMapping("/sign_up")
    public ModelAndView signUpCheck(HttpSession session, HttpServletRequest request, User user)
            throws Exception{
        if (session.getAttribute("username") != null){
            return indexRedirect();
        }
        if(user.getUsername() == null ||
           user.getPassword() == null ||
           user.getFullname() == null ||
           user.getPhone() == null ||
           user.getAddress() == null 
          ){
            ModelAndView signUpMV = new ModelAndView("signUp"); 
            signUpMV.addObject("signUpFailed", true);
            return signUpMV; 
        } 
        insertUser(user);
        return indexRedirect();  
    }

   @GetMapping("/login")
   public ModelAndView login(HttpSession session) {
        if (session.getAttribute("username") != null) { 
            return indexRedirect();

        }
        ModelAndView loginMV = new ModelAndView("login"); 
        loginMV.addObject("loginFailed", false); 
        loginMV.addObject("user", new Form()); 
        return loginMV;
        }


    @GetMapping("/logout")
        public String logout(HttpSession session){
            session.invalidate();
            return "redirect:/login";
    }

    private void insertUser(User user) throws Exception{
        //Registering the driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Getting the Connection object
        String URL = "jdbc:derby://localhost:1527/Account;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);

        //Creating the Statement object
        Statement stmt = conn.createStatement(); 

        String username = user.getUsername();
        String password = user.getPassword();
        String fullname = user.getFullname();
        String phone = user.getPhone();
        String address = user.getAddress();

        String userQuery = "INSERT INTO users VALUES "
                        + "('"+username+"', '" 
                        + "{noop}"+password+"','"
                        + fullname +"',' "
                        + phone+"', '"
                        + address+"')";
        String userRoleQuery = "INSERT INTO user_roles("
                        + "username, role) VALUES "
                        + "('"+username+"', '" 
                        +"ROLE_STUDENT')";
        stmt.execute(userQuery);
        stmt.execute(userRoleQuery);
        System.out.println("user inserted");
    }

 }

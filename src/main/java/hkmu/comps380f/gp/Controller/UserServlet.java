/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package hkmu.comps380f.gp.Controller;

import hkmu.comps380f.gp.dao.UserRepository;
import hkmu.comps380f.gp.Model.User;
import hkmu.comps380f.gp.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author emilylau
 */
@Controller
@RequestMapping("/user")
public class UserServlet extends HttpServlet {
    @Autowired
    UserRepository UserRepo;
    @Autowired
    UserService UserService;

    public static class Form {
        private String username;
        private String password;
        private String fullname;
        private String address;
        private String phone;
        private String role;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getFullname() {
            return fullname;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        public String getRole() {
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

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setRole(String role) {
            this.role = role;
        }



    }
   @GetMapping("")
   public ModelAndView userList() throws Exception{
        List<User> userList = UserRepo.findAll();
        ModelAndView userListMV = new ModelAndView("userList");
        
        userListMV.addObject("users", userList); 
        return userListMV;
   }

    @GetMapping("/{username}")
    public ModelAndView userDetail(@PathVariable("username") String username) throws Exception{
        ModelAndView userMV = new ModelAndView("userPage");
        User userRs = UserRepo.findById(username).orElse(null);
        userMV.addObject("username",userRs.getUsername());
        userMV.addObject("password",userRs.getPassword().substring(6));
        userMV.addObject("fullname",userRs.getFullname());
        userMV.addObject("phone",userRs.getPhone());
        userMV.addObject("address",userRs.getAddress());
        userMV.addObject("role",userRs.getRole().getRole().substring(5).toLowerCase());

        
        return userMV;
    }

    @GetMapping("/{username}/delete")
    public String userDeleteOne(@PathVariable("username") String username, 
                                    HttpServletRequest request)
                        throws Exception{
        UserRepo.delete(UserRepo.findById(username).orElse(null));
        System.out.println(username + " deleted");
        return "redirect:../../";
    }

    @GetMapping("/create")
    public ModelAndView createUserForm() throws Exception{
        return new ModelAndView("createUser","user", new Form());
    }  

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") Form form, HttpServletRequest request) throws Exception{
        User user = new User(form.getUsername(), form.getPassword(), form.getFullname(), form.getPhone(), form.getAddress(), form.getRole());
        UserRepo.save(user);
        System.out.println("user role inserted");
        return "redirect:./";
    }     

    @GetMapping("/{username}/edit")
    public ModelAndView editUserForm(@PathVariable("username") String username) throws Exception{
        ModelAndView userEditFrom = new ModelAndView("editUser","user",new Form());
        User userRs = UserRepo.findById(username).orElse(null);
        userEditFrom.addObject("username",userRs.getUsername());
        userEditFrom.addObject("password",userRs.getPassword().substring(6));
        userEditFrom.addObject("fullname",userRs.getFullname());
        userEditFrom.addObject("phone",userRs.getPhone());
        userEditFrom.addObject("address",userRs.getAddress());
        userEditFrom.addObject("role",userRs.getRole().getRole());
        
        return userEditFrom;
    }

    @PostMapping("/{username}/edit")
    public ModelAndView editUser(@PathVariable("username") String username, HttpServletRequest request, @ModelAttribute("user") Form form) throws Exception{
        String newUsername = form.getUsername();
        String password = form.getPassword();
        String fullname = form.getFullname();
        String phone = form.getPhone();
        String address = form.getAddress();
        String role = form.getRole();
        UserService.updateUser(username, newUsername, password, fullname, phone, address, role);
        //UserRepo.updateUser(username, password, fullname, phone, address, role);
        return userList();
    }
/*
    private ResultSet getUser(String username) throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String URL = "jdbc:derby://localhost:1527/Account;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement(); 
        String usersQuery = "SELECT * FROM users";
        if(userId != 0){
            usersQuery = "SELECT u.*, r.role FROM users u, user_roles r WHERE u.user_id = r. user_id AND u.user_id = " + userId;
        }
        
        ResultSet rs = stmt.executeQuery(usersQuery);
        System.out.println("User found: " + rs);
        return rs;
    }

    private void updateUser(String userId, String username, String password, String fullname, String phone, String address, String role) 
            throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String URL = "jdbc:derby://localhost:1527/Account;create=false;user=nbuser;password=nbuser";
        Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement(); 
        String usersQuery = "SELECT * FROM users";
        
        
        String userEditQuery = "UPDATE users SET username = '" + username
                               + "', password = '{noop}" + password
                               + "', fullname = '" + fullname
                               + "', phone = '" + phone
                               + "', address = '" + address
                               +"' WHERE user_id = " + Integer.parseInt(userId);
        if(!role.equals('0')){
            String roleEditQuery = "UPDATE user_roles SET role = 'ROLE_" + role
                               +"' WHERE user_id = " + Integer.parseInt(userId);   
            int roleNum = stmt.executeUpdate(roleEditQuery);
            System.out.println(roleNum + " role updated");    
        }

        int userNum = stmt.executeUpdate(userEditQuery);
        System.out.println(userNum + " user updated");
    }
*/
}

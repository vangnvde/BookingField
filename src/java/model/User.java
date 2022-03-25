/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Vang Nguyen
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String image;
    private boolean isManager;
    private boolean isAdmin;
    private boolean activate;
    
    
   
    public User() {
    }

    public User(int id, String username, String password, String fullname, String email, String phone, String image, boolean isManager, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.isManager = isManager;
        this.isAdmin = isAdmin;
    }

    public User(int id, String username, String password, String fullname, String email, String phone, String image, boolean isManager, boolean isAdmin, boolean activate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.isManager = isManager;
        this.isAdmin = isAdmin;
        this.activate = activate;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", image=" + image + ", isManager=" + isManager + ", isAdmin=" + isAdmin + '}';
    }
    

    
    
    
    
}

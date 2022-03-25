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
public class ServerField {
    private int id;
    private String county;
    private String manager;
    private String nameServer;
    private String email;
    private String phone;
    private String address;
    private String linkmaps;
    private String image;
    private boolean activate;

    public ServerField() {
    }

    public ServerField(int id, String county, String manager, String nameServer, String email, String phone, String address, String linkmaps, String image) {
        this.id = id;
        this.county = county;
        this.manager = manager;
        this.nameServer = nameServer;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.linkmaps = linkmaps;
        this.image = image;
    }

    public ServerField(int id, String county, String manager, String nameServer, String email, String phone, String address, String linkmaps, String image, boolean activate) {
        this.id = id;
        this.county = county;
        this.manager = manager;
        this.nameServer = nameServer;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.linkmaps = linkmaps;
        this.image = image;
        this.activate = activate;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkmaps() {
        return linkmaps;
    }

    public void setLinkmaps(String linkmaps) {
        this.linkmaps = linkmaps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
    
    
    @Override
    public String toString() {
        return "ServerField{" + "id=" + id + ", county=" + county + ", manager=" + manager + ", nameServer=" + nameServer + ", email=" + email + ", phone=" + phone + ", address=" + address + ", linkmaps=" + linkmaps + ", image=" + image + '}';
    }

    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Comment {
    private String id;
    private String user;
    private String image;
    private String idServerField;
    private String comment;

    public Comment(String id, String user, String image, String idServerField, String comment) {
        this.id = id;
        this.user = user;
        this.image = image;
        this.idServerField = idServerField;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdServerField() {
        return idServerField;
    }

    public void setIdServerField(String idServerField) {
        this.idServerField = idServerField;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", user=" + user + ", image=" + image + ", idServerField=" + idServerField + ", comment=" + comment + '}';
    }

   
    
}

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
public class Field {
    private int id;
    private String nameField;
    private String createDate;
    private int typeField;
    private boolean status;
    public Field() {
    }

    public Field(int id, String nameField, String createDate, int typeField) {
        this.id = id;
        this.nameField = nameField;
        this.createDate = createDate;
        this.typeField = typeField;
    }

    public Field(int id, String nameField, String createDate, int typeField, boolean status) {
        this.id = id;
        this.nameField = nameField;
        this.createDate = createDate;
        this.typeField = typeField;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getTypeField() {
        return typeField;
    }

    public void setTypeField(int typeField) {
        this.typeField = typeField;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Field{" + "id=" + id + ", nameField=" + nameField + ", createDate=" + createDate + ", typeField=" + typeField + ", status=" + status + '}';
    }

    
    
    

    
    
    
}

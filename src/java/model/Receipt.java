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
public class Receipt {
    private String id;
    private String userName;
    private String nameServerField;
    private String nameField;
    private String date;
    private String timeStart;
    private String timeEnd;
    private String price;
    private String status;

    public Receipt() {
    }

    public Receipt(String id, String userName, String nameServerField, String nameField, String date, String timeStart, String timeEnd, String price, String status) {
        this.id = id;
        this.userName = userName;
        this.nameServerField = nameServerField;
        this.nameField = nameField;
        this.date = date;
        this.timeStart = timeStart.substring(0,5);
        this.timeEnd = timeEnd.substring(0,5);
        this.price = price;
        this.status = status;
    }
    public Receipt(String id, String nameServerField, String nameField, String date, String timeStart, String timeEnd, String price,String status) {
        this.id = id;
        this.nameServerField = nameServerField;
        this.nameField = nameField;
        this.date = date;
        this.timeStart = timeStart.substring(0,5);
        this.timeEnd = timeEnd.substring(0,5);
        this.price = price;
        this.status = status;
    }
    public Receipt(String id, String userName, String nameField, String date, String timeStart, String timeEnd, String price) {
        this.id = id;
        this.userName = userName;
        this.nameField = nameField;
        this.date = date;
        this.timeStart = timeStart.substring(0, 5);
        this.timeEnd = timeEnd.substring(0, 5);
        this.price = price;

    }
    public Receipt(String nameServerField, String nameField, String date, String timeStart, String timeEnd, String price) {
        this.nameServerField = nameServerField;
        this.nameField = nameField;
        this.date = date;
        this.timeStart = timeStart.substring(0,5);
        this.timeEnd = timeEnd.substring(0,5);
        this.price = price;
       
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getNameServerField() {
        return nameServerField;
    }

    public String getNameField() {
        return nameField;
    }

    public String getDate() {
        return date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Receipt{" + "id=" + id + ", userName=" + userName + ", nameServerField=" + nameServerField + ", nameField=" + nameField + ", date=" + date + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", price=" + price + ", status=" + status + '}';
    }
         
 
   

    
    
    
}

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
public class TimeField {
    private int id;
    private int idField;
    private String date;
    private String timeStart;
    private String timeEnd;
    private boolean isEmpty;
    private int price;

    public TimeField() {
    }

    public TimeField(int id, int idField, String date, String timeStart, String timeEnd, boolean isEmpty, int price) {
        this.id = id;
        this.idField = idField;
        this.date = date;
        this.timeStart = timeStart.substring(0, 5);
        this.timeEnd = timeEnd.substring(0, 5);
        this.isEmpty = isEmpty;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdField() {
        return idField;
    }

    public void setIdField(int idField) {
        this.idField = idField;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean isIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TimeField{" + "id=" + id + ", idField=" + idField + ", date=" + date + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", isEmpty=" + isEmpty + ", price=" + price + '}';
    }

    
    
}

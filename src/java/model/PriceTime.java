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
public class PriceTime {
    private int id;
    private int idField;
    private String timeStart;
    private String timeEnd;
    private int price;

    public PriceTime() {
    }

    public PriceTime(int id, int idField, String timeStart, String timeEnd, int price) {
        this.id = id;
        this.idField = idField;
        this.timeStart = timeStart.substring(0,8);
        this.timeEnd = timeEnd.substring(0,8);
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TimePrice{" + "id=" + id + ", idField=" + idField + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", price=" + price + '}';
    }
    
    
}

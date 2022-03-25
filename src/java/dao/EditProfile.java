/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author DELL
 */
public class EditProfile {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public User showInfor(String id) {
        String query = "select * from [dbo].[User] where ID = ?";
        try {
            con = DBContext.getInstance().con;
            stm = con.prepareStatement(query);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editInfor(String id, String fullname, String image, String email, String phone) {
        String query = "Update [dbo].[User] Set fullname = ?,image = ?, email = ?, phone = ? Where ID = ?";
        try {
            con = DBContext.getInstance().con;
            stm = con.prepareStatement(query);
            stm.setString(1, fullname);
            stm.setString(2, image);
            stm.setString(3, email);
            stm.setString(4, phone);
            stm.setString(5, id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
  
    public static void main(String[] args) {
        EditProfile edit = new EditProfile();
        User user = edit.showInfor("29");
        System.out.println(user);
    }
}

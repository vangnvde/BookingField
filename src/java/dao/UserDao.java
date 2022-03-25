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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Field;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
public class UserDao {

    public User checkLogin(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [User] where username=? and [password]=? and isActivate = 1";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getBoolean(9));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {

            rs.close();
            ps.close();
        }
        return null;
    }

    public boolean checkUsernameExit(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [User] where username=?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {

            rs.close();
            ps.close();
        }
        return false;
    }

    public boolean checkPhoneNumberExit(String phone, String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [User] where [phone]=? and id != ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {

            rs.close();
            ps.close();
        }
        return false;
    }

    public boolean checkEmailExit(String email, String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [User] where [email]=? and id != ? ";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {

            rs.close();
            ps.close();
        }
        return false;
    }

    public boolean addNewUser(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into [User] (username, [password]) values (?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {

            ps.close();
        }
        return false;
    }

    public User addNewGoogleUser(String username, String password, String fullname, String email, String image) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into [User] (username, [password], fullname, email, image) values (?,?,?,?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setString(5, image);
            if (ps.executeUpdate() > 0) {
                return checkLogin(username, password);
            }

        } catch (Exception e) {
            System.out.println("Google Error" + e.getMessage());
        } finally {

            ps.close();
        }
        return null;
    }

    public boolean updateUserInfoConfirm(String username, String phone, String email, String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update [User] set fullname =?, phone = ?, email = ? where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, id);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {

            ps.close();
        }
        return false;
    }

    public boolean updateManagerUser(String id, String status) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update [User] set isManager =? where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("manager" + e.getMessage());
        } finally {

            ps.close();
        }
        return false;
    }

    public boolean updateActivateUser(String id, String status) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update [User] set isActivate =? where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Activate" + e.getMessage());
        } finally {
            ps.close();
        }
        return false;
    }

    public User checkUser(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [User] where id = ? ";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getBoolean(9));
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            rs.close();
            ps.close();
        }
        return null;
    }

    public boolean updatePassUser(int id, String passNew) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update [User] set password = ? where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, passNew);
            ps.setInt(2, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("update pass " + e.getMessage());
        } finally {
            ps.close();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new UserDao().checkLogin("vangnv", "123").toString());
    }
}

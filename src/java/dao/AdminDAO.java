package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class AdminDAO {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "select * from [User] where isAdmin=0";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getBoolean(9),
                        rs.getBoolean(10))
                );
            }
        } catch (Exception e) {
        }

        return list;
    }
//        public void deleteUser(String ID){
//            String query = "delete from [User]\n" +
//                "where ID = ?";
//            try {
//            conn = DBContext.getInstance().con;
//            ps = conn.prepareStatement(query);
//            ps.setString(1, ID);
//            ps.executeUpdate();
//            } catch (Exception e) {
//            }
//        }

    public User getUserById(String id) {
        String query = "select * from [User]\n"
                + "where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getBoolean(9),
                        rs.getBoolean(10)));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateUserByID(String id, String isManager, String action) {
        String query = "update [user]\n"
                + "set isManager = ?\n"
                + "isActivate = ?\n"
                + "where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            ps.setString(1, isManager);
            ps.setString(2, action);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}

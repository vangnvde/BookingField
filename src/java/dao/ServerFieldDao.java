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
import model.County;
import model.ServerField;

/**
 *
 * @author Vang Nguyen
 */
public class ServerFieldDao {

    public List<ServerField> getAllServerField() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ServerField> listSF = new ArrayList<>();
        String sql = "select a.*, b.NameCounty, c.fullname  from ServerField a, County b, [User] c where a.IDCounty = b.id and a.IDUser = c.id and a.isActivate = 1";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listSF.add(new ServerField(rs.getInt(1),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!! asd" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listSF;
    }

    public ServerField getServerFieldById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select a.*, b.NameCounty, c.fullname  from ServerField a, County b, [User] c where a.IDCounty = b.id and a.IDUser = c.id and a.id=? and a.isActivate = 1";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new ServerField(rs.getInt(1),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public ServerField getServerFieldByUserId(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select a.*, b.NameCounty  from ServerField a, County b where a.IDCounty = b.id and a.IdUser = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new ServerField(rs.getInt(1),
                        rs.getString(11),
                        "",
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public ServerField getServerFieldByIdField(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select a.*, b.NameCounty, c.fullname  from ServerField a, County b, [User] c where a.IDCounty = b.id and a.IDUser = c.id and a.id = (select top 1 IdserverField a from Field a where a.id = ?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new ServerField(rs.getInt(1),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public boolean updateServerFieldByManager(String name, String county, String email, String phone, String address, String images,String status ,String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "update ServerField set NameServer = ?,IDCounty=?, Email = ?, Phone =?, [Address] =?, images=?, isActivate = ? where IDUser = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, county);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, images);
            ps.setString(7, status);
            ps.setString(8, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("change serverfield" + e.getMessage());
        } finally {
            
            ps.close();
        }
        return false;
    }

    // Get Country
    public List<County> getAllCounty() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from county ";
        List<County> listC = new ArrayList<>();

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new County(rs.getInt(1), rs.getString(2)));
            }
            return listC;
        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public List<ServerField> searchServerField(String name, int county) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ServerField> listSF = new ArrayList<>();
        String sql = "select a.*, b.NameCounty, c.fullname  from ServerField a, County b, [User] c where a.IDCounty = b.id and a.IDUser = c.id and a.isActivate = 1";
        if(!name.equals("")) {
            sql += " and lower(a.NameServer) like N'%"+name+"%' ";
        }
        if(county > 0){
            sql += " and a.IDCounty = "+county;
        }
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
            listSF.add(new ServerField(rs.getInt(1),
                                       rs.getString(11),
                                       rs.getString(12),
                                       rs.getString(4),
                                       rs.getString(5),
                                       rs.getString(6),
                                       rs.getString(7),
                                       rs.getString(8),
                                       rs.getString(9)));             
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listSF;
    }
    
    
    public boolean addNewServerField(String idUser) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "insert into ServerField (IDUser,IDCounty) values (?,0)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idUser);          
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("add serverfield" + e.getMessage());
        } finally {
            
            ps.close();
        }
        return false;
    }
    
    public boolean checkPhoneNumberExit(String phone, String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        String sql = "select * from ServerField where [phone]=? and idUser != ?";
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
        String sql = "select * from ServerField where [email]=? and idUser != ?";
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
    
    public static void main(String[] args) throws SQLException {
        try {
            System.out.println(new ServerFieldDao().getAllCounty().toString());
        } catch (SQLException e) {
            System.out.println("QW" + e.getMessage());
        }
    }

}

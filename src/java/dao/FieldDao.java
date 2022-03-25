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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.Field;
import model.PriceTime;
import model.TimeField;

/**
 *
 * @author Vang Nguyen
 */
public class FieldDao {
 
    public List<Field> getAllActivateField() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Field> listF = new ArrayList<>();
        String sql = "select * from Field where isActivate = 1";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listF.add(new Field(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6)
                ));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listF;
    }

    public boolean changeFieldById(String id, String status) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "update Field set isActivate = ? where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("field " + e.getMessage());
        } finally {
            

            ps.close();
        }
        return false;
    }

    public Field getFieldById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Field where id =? ";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Field(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public List<Field> getActivateFieldByIdServerFiled(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Field> listF = new ArrayList<>();
        String sql = "select * from Field where IDserverfield  =? and isActivate = 1";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listF.add(new Field(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listF;
    }

    public List<Field> getFieldByIdManager(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Field> listF = new ArrayList<>();
        String sql = "select * from Field where IDserverfield in (select DISTINCT id from ServerField where IdUser =?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listF.add(new Field(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listF;
    }
    
    public String getIdServerFieldByUser(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
      
        String sql = "select DISTINCT id from ServerField where IdUser =?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public List<Field> getLastFiveField() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Field> listF = new ArrayList<>();
        String sql = "select top 4 a.*,b.NameServer from Field a, ServerField b where a.IDserverfield = b.ID ORDER BY a.Id desc ";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listF.add(new Field(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getInt(5)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listF;
    }

    public boolean checkNameFieldExitByIdServerField(String idSF, String nameF) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Field where IDserverfield=? and NameField = ? ";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idSF);
            ps.setString(2, nameF);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("field " + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return false;
    }
    
    public boolean addNewFeildByIdServerField(String idSF, String name,String type) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into Field (NameField,IDserverfield,typeField) values (?,?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, idSF);
            ps.setString(3, type);

            if (ps.executeUpdate()>0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("field " + e.getMessage());
        } finally {
            
            ps.close();
        }
        return false;
    }
    
    /* End Field Part */

 /* PriceTime Part */
    public List<PriceTime> getPriceTimeFieldByIdServerFiled(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PriceTime> listPT = new ArrayList<>();
        String sql = "select * from PriceTime where IdField in (select id from Field where IDserverfield =?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listPT.add(new PriceTime(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listPT;
    }

    public List<PriceTime> getPriceTimeFieldByIdFiled(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PriceTime> listPT = new ArrayList<>();
        String sql = "select * from PriceTime where IdField =?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listPT.add(new PriceTime(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }

        } catch (Exception e) {
            System.out.println("prices time " + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listPT;
    }

    public boolean deletePriceTimeById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from PriceTime where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("prices time " + e.getMessage());
        } finally {
            

            ps.close();
        }
        return false;
    }
    public boolean checkPriceTimeConflit(String idF, String start, String end) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from PriceTime where IDField = ? and (([end] > ? and [end] < ? )or ([Start] < ? and [Start] > ?))";
        try {
           conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idF);
            ps.setString(2, start+":00");
            ps.setString(3, end+":00");
            ps.setString(4, end+":00");
            ps.setString(5, start+":00");
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("prices time conflit " + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return false;
    }
    
    public boolean checkUpdatePriceTimeConflit(String idF, String start, String end, String idPT) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from PriceTime where IDField = ? and (([end] > ? and [end] < ? )or ([Start] < ? and [Start] > ?)) and id != ?";
        try {
           conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idF);
            ps.setString(2, start+":00");
            ps.setString(3, end+":00");
            ps.setString(4, end+":00");
            ps.setString(5, start+":00");
            ps.setString(6, idPT);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("prices time conflit " + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return false;
    }
    
    public boolean addNewPriceTimeByIdF(String idF, String start, String end, String price) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "insert into PriceTime (IDField,[Start],[End],priceTime) values (?,?,?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idF);
            ps.setString(2, start+":00");
            ps.setString(3, end+":00");
            ps.setInt(4, Integer.parseInt(price));
         
            if (ps.executeUpdate()>0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("prices time add " + e.getMessage());
        } finally {
                     
            ps.close();
        }
        return false;
    }
    
    public boolean updatePriceTimeById(String idPT, String start, String end, String price) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "update PriceTime set [Start]=? , [End] = ?, priceTime = ? where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, start+":00");
            ps.setString(2, end+":00");
            ps.setInt(3, Integer.parseInt(price));
            ps.setString(4, idPT);
         
            if (ps.executeUpdate()>0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("prices time up " + e.getMessage());
        } finally {
                     
            ps.close();
        }
        return false;
    }

    /* PriceTime Part End */
 /* TimeField Part */
    public List<TimeField> getTimeFieldByIdServerFiled(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TimeField> listTF = new ArrayList<>();
        String sql = "select * from TimeField where IdField in (select id from Field where IDserverfield =?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listTF.add(new TimeField(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getInt(7)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listTF;
    }

    public TimeField getTimeFieldById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from TimeField where Id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new TimeField(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getInt(7));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }

    public List<TimeField> getTimeFieldByIdFiledAndDay(String idF, String day) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TimeField> listTF = new ArrayList<>();
        String sql = "select * from TimeField where IdField = ? and date = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idF);
            ps.setString(2, day);
            rs = ps.executeQuery();

            while (rs.next()) {
                listTF.add(new TimeField(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getInt(7)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listTF;
    }

    public List<TimeField> getEmptyTimeFieldByIdFiledAndDay(String idF, String day) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TimeField> listTF = new ArrayList<>();
        String sql = "select * from TimeField where IdField = ? and date = ? and isEmpty='true'";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idF);
            ps.setString(2, day);
            rs = ps.executeQuery();

            while (rs.next()) {
                listTF.add(new TimeField(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getInt(7)));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listTF;
    }

    public boolean addTimeFieldByDay(String idField, String day, String timeStart, String timeEnd) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into TimeField (IDField,[date],TimeStart,TimeEnd,IsEmpty) values (?,?,?,?,1)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idField);
            ps.setString(2, day);
            ps.setString(3, timeStart);
            ps.setString(4, timeEnd);

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

    /* TimeField Part End */
    public static void main(String[] args) throws SQLException {
        System.out.println(new FieldDao().getAllActivateField().toString());
    }
}

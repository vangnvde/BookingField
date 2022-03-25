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
import model.Receipt;

/**
 *
 * @author Vang Nguyen
 */
public class ReceiptDao {

    /* Receipt For Manager */
    public List<Receipt> getAllWaitingReceiptByManagerId(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receipt> listR = new ArrayList<>();
        String sql = "select d.id, a.username, namefield, c.[date], c.TimeStart, c.TimeEnd, price from [User] a, Field b, TimeField c, Receipt d "
                + "where b.ID = c.IDField and c.id = d.TimeFieldId and a.ID = d.UserId and d.statusID = 1 "
                + "and d.TimeFieldId in (select id from TimeField where TimeField.IDField in ( select id from Field where Field.IDserverfield in (select id from ServerField where ServerField.IDUser = ? ) ) ) order by d.id desc";

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listR.add(new Receipt(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));

            }

        } catch (Exception e) {
            System.out.println("manager receipt" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listR;
    }

    public List<Receipt> getAllHistoryReceiptByManagerId(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receipt> listR = new ArrayList<>();
        String sql = "  select d.id, a.username, namefield, c.[date], c.TimeStart, c.TimeEnd, price,case when d.statusId = 2 then N'Đã Xác Nhận' when d.statusId = 3 then N'Đã Hoàn Thành' else N'Đã Hủy' end as statusReceipt from [User] a, Field b, TimeField c, Receipt d \n"
                + "               where b.ID = c.IDField and c.id = d.TimeFieldId and a.ID = d.UserId and d.statusID in (2,3,5)\n"
                + "                and d.TimeFieldId in (select id from TimeField where TimeField.IDField in ( select id from Field where Field.IDserverfield in (select id from ServerField where ServerField.IDUser = ? ) ) ) order by d.id desc";

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listR.add(new Receipt(
                        rs.getString(1),
                        rs.getString(2),
                        "",
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));

            }

        } catch (Exception e) {
            System.out.println("manager receipt" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listR;
    }

    public boolean confirmReceipt(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update Receipt set statusID = 2 where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("manager confirm receipt" + e.getMessage());
        } finally {
            
            ps.close();
        }
        return false;
    }

    public boolean refuseReceipt(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update Receipt set statusID = 5 where id = ?";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("manager refuse receipt" + e.getMessage());
        } finally {
            
            ps.close();
        }
        return false;
    }

    /* End Receipt For Manager */

 /* Receipt For User */
    
    public List<Receipt> getAllReceiptByUserID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receipt> listR = new ArrayList<>();
        String sql = "select d.id,a.NameServer,b.NameField,c.[date],c.TimeStart,c.TimeEnd,c.price,e.[status] from ServerField a, Field b, TimeField c, Receipt d, ReceiptStatus e\n"
                + "                			where a.id = b.IDserverfield and b.ID = c.IDField and d.statusID = e.id and c.id in (select TimeFieldId from Receipt where UserId = ?) and d.TimeFieldId = c.id order by d.id desc";

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listR.add(new Receipt(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listR;
    }

    
    public List<Receipt> getLastFourReceiptByUserID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receipt> listR = new ArrayList<>();
        String sql = "select top 4 d.id,a.NameServer,b.NameField,c.[date],c.TimeStart,c.TimeEnd,c.price,e.[status] from ServerField a, Field b, TimeField c, Receipt d, ReceiptStatus e\n"
                + "                			where a.id = b.IDserverfield and b.ID = c.IDField and d.statusID = e.id and c.id in (select TimeFieldId from Receipt where UserId = ?) and d.TimeFieldId = c.id order by d.id desc";

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                listR.add(new Receipt(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listR;
    }

    public boolean checkHaveWaitingReciept(String idU) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receipt> listR = new ArrayList<>();
        String sql = "select * from Receipt where UserID=? and [statusID]=1";

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idU);
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

    
    public boolean addNewReceipt(String idUser, String idTimeField) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        List<Receipt> listR = new ArrayList<>();
        String sql = "insert into Receipt　(UserId, TimeFieldId) values  (?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idUser);
            ps.setString(2, idTimeField);

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

    public Receipt getReceiptByTimeFieldID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select a.NameServer,b.NameField,c.[date],c.TimeStart,c.TimeEnd,c.price from ServerField a, Field b, TimeField c \n"
                + "				where  a.id = b.IDserverfield and b.ID = c.IDField and c.id = ?";

        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Receipt(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(new ReceiptDao().getAllHistoryReceiptByManagerId("15").toString());
    
    }
}

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

/**
 *
 * 
 */
public class CountyDao {
    public List<County> getAllCounty() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<County> listCT = new ArrayList<>();
        String sql = "select * from County";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
            listCT.add(new County(rs.getInt(1),
                                rs.getString(2)));             
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        } finally {
            
            rs.close();
            ps.close();
        }
        return listCT;
    }
}

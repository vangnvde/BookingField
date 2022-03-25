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
import java.util.ArrayList;
import java.util.List;
import model.Comment;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
public class CommentDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Comment> getAllCommentByIdServerfield(String id) {
        List<Comment> list = new ArrayList<>();
        String query = "select a.*,b.fullname,b.[image] from [Command] a, [User] b where IDServerField=? and a.IDUser = b.ID and IDReply IS NULL";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Comment(rs.getString(1),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Comment> getReplyCommentByIdComment(String id) {
        List<Comment> list = new ArrayList<>();
        String query = "select a.*,b.fullname,b.[image] from [Command] a, [User] b where  IDReply =? and a.IDUser = b.ID";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Comment(rs.getString(1),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public boolean addNewComment(String idUser, String idSF, String content) {
        List<Comment> list = new ArrayList<>();
        String query = "insert into [Command] (IDUser,IDServerField,CMT) values (?,?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            ps.setString(1, idUser);
            ps.setString(2, idSF);
            ps.setString(3, content);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean addReplyComment(String idUser, String idSF, String content, String idReply) {
        List<Comment> list = new ArrayList<>();
        String query = "insert into [Command] (IDUser,IDServerField,CMT,IDReply) values (?,?,?,?)";
        try {
            conn = DBContext.getInstance().con;
            ps = conn.prepareStatement(query);
            ps.setString(1, idUser);
            ps.setString(2, idSF);
            ps.setString(3, content);
            ps.setString(4, idReply);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }
    public static void main(String[] args) {
        System.out.println(new CommentDAO().getAllCommentByIdServerfield("8"));
    }
}

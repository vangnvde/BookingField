/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Comment;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "LoadComment", urlPatterns = {"/load-comment"})
public class LoadComment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idSF = request.getParameter("idSF");

        List<Comment> listC = new ArrayList<>();
        List<Comment> listReplyC = new ArrayList<>();
        listC = new CommentDAO().getAllCommentByIdServerfield(idSF);
        listReplyC = new CommentDAO().getAllCommentByIdServerfield(idSF);
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");
        System.out.println(user+"123123131233");
        try (PrintWriter out = response.getWriter()) {

            if (!listC.isEmpty()) {
                for (Comment c : listC) {

                    out.println("<li class=\"comment\">"
                            + "<div class=\"vcard bio\">\n"
                            + "                        <img src=\"" + (c.getImage() == null ? "images/avatar.png" : c.getImage()) + "\">\n"
                            + "                    </div>\n"
                            + "                    <div class=\"comment-body\">\n"
                            + "                        <h3>" + c.getUser() + "</h3>\n"
                            + "                        <p>" + c.getComment() + "</p>\n"
                            + "                        <div id=\"comment-" + c.getId() + "\" class=\"comment-reply\">\n"
                            + "                            <button id=\"btn-show" + c.getId() + "\" onclick=\"loadReply(" + c.getId() + ")\"class=\"none-background-btn\">Xem phản hồi</button>\n"
                            + "                        </div>\n"
                            + "<div id=\"comment-form-" + c.getId() + "\" class=\"comment-form-wrap pt-5\">");
                    if (user != null) {
                        out.print("                        <button id=\"btn-add" + c.getId() + "\" style=\"display=\"none\"\" onclick=\"loadCommentForm(" + c.getId() + ")\" class=\"reply btn \">Phản Hồi</button>\n");
                    }
                    out.print("                    </div>"
                            + "                    </div>"
                            + "</li>");
                }
            }

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "LoadCommentForm", urlPatterns = {"/load-comment-form"})
public class LoadCommentForm extends HttpServlet {

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
        String id = request.getParameter("id");
       
       
        try (PrintWriter out = response.getWriter()) {
            if (id!=null) {
                out.print("         <div id=\"form-cmt-"+id+"\">   <div  class=\"form-group \">\n"
                        + "                <label for=\"message\">Nội dung</label>\n"
                        + "                <textarea name=\"\" id=\"message-"+id+"\" cols=\"15\" rows=\"3\" class=\"form-control\"></textarea>\n"
                        + "            </div>\n"
                        + "            <div id=\"result-"+id+"\" class=\"form-group\">\n"
                        + "                <button onclick=\"addComment("+id+")\"  class=\"btn py-3 px-4 btn-primary\">Gửi phản hồi</button>\n"
                        + "            </div></div>\n"
                        + "\n");
            } else {
                out.print("          <div id=\"form-cmt-0\">  <div  class=\"form-group \">\n"
                        + "                <label for=\"message\">Nội dung</label>\n"
                        + "                <textarea name=\"\" id=\"message\" cols=\"15\" rows=\"3\" class=\"form-control\"></textarea>\n"
                        + "            </div>\n"
                        + "            <div id=\"result-0\" class=\"form-group\">\n"
                        + "                <button onclick=\"addComment(0)\"  class=\"btn py-3 px-4 btn-primary\">Gửi phản hồi</button>\n"
                        + "            </div></div>\n"
                        + "\n");
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

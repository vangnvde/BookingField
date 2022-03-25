/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "SignupControll", urlPatterns = {"/signup"})
public class SignupControll extends HttpServlet {

   

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
        request.setCharacterEncoding("UTF-8");
        try{                        
            request.setAttribute("title", "Đăng Ký");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }catch(NullPointerException ex){
            out.println("asd"+ ex.getMessage() );
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
         try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            boolean error = false;
            UserDao userDao = new UserDao();
            
            if (userDao.checkUsernameExit(username)) {
                error = true;
                request.setAttribute("NAMEERROR", "Tên đăng nhập đã tồn tại");
            }
            if (!password.equalsIgnoreCase(rePassword)) {
                error = true;
                request.setAttribute("PASSWORDERROR", "Mật khẩu không trùng");
            }
            if (error) {
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else{
                new UserDao().addNewUser(username, password);
                response.sendRedirect("login");
            }
        }catch(NullPointerException ex){
            out.println("asd"+ ex.getMessage());
        }catch (SQLException ex) {
            Logger.getLogger(SignupControll.class.getName()).log(Level.SEVERE, null, ex);
        }
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

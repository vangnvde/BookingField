/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.UserDao;
import java.io.IOException;

import static java.lang.System.out;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "loginControll", urlPatterns = {"/login"})
public class LoginControll extends HttpServlet {

  
    
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
        request.setCharacterEncoding("UTF-8");
        try{                        
            request.setAttribute("title", "Đăng Nhập");
            //get user,pass from cookie
            Cookie arr[] = request.getCookies();
            if (arr != null) {
            for (Cookie o : arr){
                if (o.getName().equals("userC")){
                    request.setAttribute("username", o.getValue());
                }
                if (o.getName().equals("passC")){
                    request.setAttribute("password", o.getValue());
                }
            }
            }
            // set user,pass to login form
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       String remember = request.getParameter("remember");
        try{         
            User user = new UserDao().checkLogin(username, password);
            if(user != null){
                HttpSession s=request.getSession();
                s.setAttribute("ACCOUNT",user);
                 //sava account  in cookie
                Cookie u = new Cookie("userC", username);
                Cookie p = new Cookie("passC", password);
                u.setMaxAge(60);
                if(remember != null){
                    p.setMaxAge(60);
                }else{
                    p.setMaxAge(0);
                }
                
                response.addCookie(u);// save u and p in chrome
                response.addCookie(p);
                response.sendRedirect("home");
            } else{
                request.setAttribute("ERRORMASSAGE","Tài khoản mât khẩu không chính xác hoặc tài khoản đã bị khóa!");
                request.getRequestDispatcher("login.jsp").forward(request, response);           
            }
            
        }catch(NullPointerException ex){
            out.println("asd"+ ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(LoginControll.class.getName()).log(Level.SEVERE, null, ex);
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

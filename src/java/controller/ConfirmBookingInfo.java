/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReceiptDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Receipt;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "confirmBookingInfo", urlPatterns = {"/confirmBookingInfo"})
public class ConfirmBookingInfo extends HttpServlet {

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
        String idTF = request.getParameter("idTF");

        try {
            Receipt receipt = new ReceiptDao().getReceiptByTimeFieldID(idTF);

            request.setAttribute("Receipt", receipt);
            request.setAttribute("idTF", idTF);
            request.setAttribute("title", "Xác Nhận Đặt Sân");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmBookingInfo.class.getName()).log(Level.SEVERE, null, ex);
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
        String idTF = request.getParameter("id");
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");     
        try {
            PrintWriter out = response.getWriter();
            if (new ReceiptDao().checkHaveWaitingReciept(""+user.getId())) {
                out.println("<div class=\"alert alert-warning\">\n"
                        + "            <strong>Đặt sân không thành công!</strong> Bạn đang có yêu cầu đang chờ không thể đặt thêm.\n"
                        + "        </div>");
            } else if(new ReceiptDao().addNewReceipt("" + user.getId(), idTF)){              
                out.println("<div class=\"alert alert-success\">\n"
                        + "            <strong>Đặt sân thành công!</strong> Xem lại thông tin chi tiết trong lịch sử.\n"
                        + "        </div>");
            } else{
                out.println("<div class=\"alert alert-danger\">\n"
                        + "            <strong>Đặt sân không thành công!</strong> Có lỗi xảy ra trong khi đặt sân.\n"
                        + "        </div>");
            }
        } catch (Exception ex) {
            Logger.getLogger(ConfirmBookingInfo.class.getName()).log(Level.SEVERE, null, ex);
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

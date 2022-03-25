/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReceiptDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "LoadHistoryReceiptUser", urlPatterns = {"/load-history-receipt-user"})
public class LoadHistoryReceiptUser extends HttpServlet {

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
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");
        HashMap<String, String> status = new HashMap<String, String>();
        status.put("Đang chờ", "<td class=\"total\"><a class=\"\" style=\"color: blue\">Đang Chờ</a></td>\n");
        status.put("Đã Xác Nhận", "<td class=\"total\"><a class=\" text-success\" style=\"\">Đã Xác Nhận</a></td>\n");
        status.put("Đã Hủy", "<td class=\"total\"><a class=\"text-danger\" style=\"\">Đã Hủy</a></td>\n");
        try (PrintWriter out = response.getWriter()) {
            List<Receipt> listR = new ArrayList<>();
            listR = new ReceiptDao().getAllReceiptByUserID("" + user.getId());
            out.println("<h2 class=\"heading\">Lịch Sử Đặt Sân</h2>\n"
                    + "                <table class=\"table\" style=\"width: 90%\">\n"
                    + "                    <thead class=\"thead-primary\">\n"
                    + "                        <tr class=\"text-center\">\n"
                    + "                            <th>Trung Tâm</th>\n"
                    + "                            <th>Mã Sân</th>\n"
                    + "                            <th>Ngày Đặt</th>\n"
                    + "                            <th>Thời Gian</th>\n"
                    + "                            <th>Giá</th>\n"
                    + "                            <th>Trạng Thái</th>\n"
                    + "                            <th>Tùy Chọn</th>\n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <tbody>    ");
            if (!listR.isEmpty()) {
                for (Receipt r : listR) {
                    out.println("<tr id=\"receipt-" + r.getId() + "\" class=\"text-center\">\n"
                            + "                            <td class=\"total\">" + r.getNameServerField() + "</td>\n"
                            + "                            <td class=\"total\">" + r.getNameField() + "</td>\n"
                            + "                            <td class=\"total\">" + r.getDate() + "</td>\n"
                            + "                            <td class=\"total\">" + r.getTimeStart().substring(0, 5) + "-" + r.getTimeEnd().substring(0, 5) + "</td>\n"
                            + "                            <td class=\"total\">" + r.getPrice() + " vnd</td>\n"
                            + status.get(r.getStatus()) + "");
                    if (r.getStatus().equalsIgnoreCase("Đang chờ")) {
                        out.print("                        <td>\n"
                                + "                             <button class=\"btn btn-outline-danger\"  onclick=\"cancerReceipt(" + r.getId() + ")\" href=\"\">Hủy</button>\n"
                                + "                        </td>\n"
                                + "                        </tr>");
                    }

                }
                out.println(" </tbody>\n"
                        + "</table>");
            } else {
                out.print("<tr><td>Không Có Yêu Cầu Nào Đang Chờ</td></tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadHistoryReceiptUser.class.getName()).log(Level.SEVERE, null, ex);
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
        String idR = request.getParameter("idReceipt");
        try {
            new ReceiptDao().refuseReceipt(idR);
        } catch (SQLException ex) {
            Logger.getLogger(LoadHistoryReceiptUser.class.getName()).log(Level.SEVERE, null, ex);
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

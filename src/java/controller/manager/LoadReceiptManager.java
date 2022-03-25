/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dao.ReceiptDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "LoadReceiptManager", urlPatterns = {"/manager-waiting-receipt"})
public class LoadReceiptManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");
        try (PrintWriter out = response.getWriter()) {
            List<Receipt> listR = new ArrayList<>();
            listR = new ReceiptDao().getAllWaitingReceiptByManagerId("" + user.getId());
            out.println("<h2 class=\"heading\">Yêu Cầu Đặt Sân</h2>\n"
                    + "                <table class=\"table\">\n"
                    + "                    <thead class=\"thead-primary\">\n"
                    + "                        <tr class=\"text-center\">\n"
                    + "                            <th>Người Đặt</th>\n"
                    + "                            <th>Mã Sân</th>\n"
                    + "                            <th>Ngày Đặt</th>\n"
                    + "                            <th>Thời Gian</th>\n"
                    + "                            <th>Giá</th>\n"
                    + "                            <th>Tùy Chọn</th>\n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <tbody>    ");
            if (!listR.isEmpty()) {
                for (Receipt r : listR) {
                    out.println("<tr id=\"receipt-" + r.getId() + "\" class=\"text-center\">\n"
                            + "                            <td class=\"total\">" + r.getUserName() + "</td>\n"
                            + "                            <td class=\"total\">" + r.getNameField() + "</td>\n"
                            + "                            <td class=\"total\">" + r.getDate() + "</td>\n"
                            + "                            <td class=\"total\">" + r.getTimeStart().substring(0, 5) + "-" + r.getTimeEnd().substring(0, 5) + "</td>\n"
                            + "                            <td class=\"total\">" + r.getPrice() + " vnd</td>                         \n"
                            + "                            <td>\n"
                            + "                                <button class=\"btn btn-outline-primary\" style=\"margin-right: 5px\" onClick=\"confirmReceipt(" + r.getId() + ")\"href=\"\">Xác Nhận</button>\n"
                            + "                                <button class=\"btn btn-outline-danger\"  onClick=\"refuseReceipt(" + r.getId() + ")\" href=\"\">Hủy</button>\n"
                            + "                            </td>\n"
                            + "                        </tr>");
                }
                out.println(" </tbody>\n"
                        + "</table>");
            } else {
                out.print("<tr><td>Không Có Yêu Cầu Nào Đang Chờ</td></tr>");
            }
        }
    }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoadReceiptManager.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoadReceiptManager.class.getName()).log(Level.SEVERE, null, ex);
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

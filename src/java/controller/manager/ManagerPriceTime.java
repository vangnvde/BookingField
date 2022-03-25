/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dao.FieldDao;
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
import model.Field;
import model.PriceTime;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "ManagerPriceTime", urlPatterns = {"/manager-price-time"})
public class ManagerPriceTime extends HttpServlet {

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
        String[] listTime = {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00",};
        response.setContentType("text/html;charset=UTF-8");
        String idF = request.getParameter("idF");
        System.out.println("!!!!!!!!!!!!!!!!!"+ idF);
        try (PrintWriter out = response.getWriter()) {
            List<PriceTime> listPT = new ArrayList<>();
            listPT = new FieldDao().getPriceTimeFieldByIdFiled(idF);

            out.println("<h2 class=\"heading\">Sân Đang Có Ở Trung Tâm</h2>\n"
                    + "                <table class=\"table\">\n"
                    + "                    <thead class=\"thead-primary\">\n"
                    + "                        <tr class=\"text-center\">                                                    \n"
                    + "                            <th>Từ</th>\n"
                    + "                            <th>Đến</th>\n"
                    + "                            <th>Giá</th>\n"
                    + "                            <th>Xác Nhận</th>\n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <tbody > ");
            for (PriceTime pt : listPT) {
                out.println(" <tr class=\"text-center\">                         \n"
                        + "                            <td>\n"
                        + "                                <div class=\"select-wrap\" >\n"
                        + "                                    <select id=\"time-start-" + pt.getId() + "\" class=\"form-control\">\n");
                for (String time : listTime) {
                    if (time.equalsIgnoreCase(pt.getTimeStart().substring(0, 5))) {
                        out.println("<option value=\"" + time + "\" selected=\"selected\">" + time + "</option>\n");
                    } else {
                        out.println("<option value=\"" + time + "\">" + time + "</option>\n");
                    }
                    if (time.equalsIgnoreCase(pt.getTimeEnd().substring(0, 5))) {
                        break;
                    }
                }
                out.println("                                    </select>\n"
                        + "                                </div>\n"
                        + "                            </td>\n"
                        + "                            <td>\n"
                        + "                                <div class=\"select-wrap\">\n"
                        + "                                    <select id=\"time-end-" + pt.getId() + "\" class=\"form-control\">\n");
                for (String time : listTime) {
                    if (time.equalsIgnoreCase(pt.getTimeEnd().substring(0, 5))) {
                        out.println("<option value=\"" + time + "\" selected=\"selected\">" + time + "</option>\n");
                    } else {
                        out.println("<option value=\"" + time + "\">" + time + "</option>\n");
                    }
                }
                out.println("                                    </select>\n"
                        + "                                </div>\n"
                        + "                            </td>\n"
                        + "                            <td class=\"total\"><input id=\"price-"+pt.getId()+"\" class=\"form-control\" value=\"" + pt.getPrice() + "\" type=\"number\" required=\"\"></td> \n"
                        + "                            <td>\n"
                        + "                                <button class=\"btn btn-outline-primary\" onclick=\"updatePriceTime(" + pt.getId()+","+idF+")\" style=\"margin-right: 5px\" >Xác Nhận</button>                              \n"
                        + "                                <button class=\"btn btn-outline-danger\" onclick=\"deletePriceTime("  + pt.getId()+","+idF+")\"  >Xóa</button> \n"
                        + "                            </td>\n"
                        + "                        </tr>");

            }
            // add new form

            out.println(" <tr id=\"add-time-form\" class=\"text-center\" style=\"display:none\">                         \n"
                    + "                            <td>\n"
                    + "                                <div class=\"select-wrap\" >\n"
                    + "                                    <select id=\"add-time-start\" class=\"form-control\">\n");
            for (String time : listTime) {

                out.println("<option value=\"" + time + "\">" + time + "</option>\n");

            }
            out.println("                                    </select>\n"
                    + "                                </div>\n"
                    + "                            </td>\n"
                    + "                            <td>\n"
                    + "                                <div class=\"select-wrap\">\n"
                    + "                                    <select id=\"add-time-end\" class=\"form-control\">\n");
            for (String time : listTime) {

                out.println("<option value=\"" + time + "\">" + time + "</option>\n");

            }
            out.println("                                    </select>\n"
                    + "                                </div>\n"
                    + "                            </td>\n"
                    + "                            <td class=\"total\"><input id=\"add-price\" class=\"form-control\" value=\"\" type=\"number\" required=\"\"></td> \n"
                    + "                            <td>\n"
                    + "                                <button onclick=\"addPriceTime(" + idF + ")\" class=\"btn btn-primary\" style=\"margin-right: 5px\" >Thêm</button>                              \n"
                    + "                                <button id=\"add-time\"class=\"btn btn-danger\" onclick=\"addTimeForm()\"  >Hủy bỏ</button> \n"
                    + "                            </td>\n"
                    + "                        </tr>");
            out.println(" </tbody>\n"
                    + "</table>\n"
                    + "<button id=\"add\" onclick=\"addTimeForm()\" class=\"btn btn-primary\" >Thêm</button>\n"
                    + "<div id=\"add-message\" ></div>");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPriceTime.class.getName()).log(Level.SEVERE, null, ex);
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
        String idF = request.getParameter("idF");
        String idPT = request.getParameter("idPT");
        String timeStart = request.getParameter("timeStart");
        String timeEnd = request.getParameter("timeEnd");
        String price = request.getParameter("price");

        FieldDao fd = new FieldDao();
        try (PrintWriter out = response.getWriter()) {
            if (fd.checkUpdatePriceTimeConflit(idF, timeStart, timeEnd,idPT) || timeStart.equalsIgnoreCase(timeEnd) || Integer.parseInt(timeStart.substring(0, 2)) > Integer.parseInt(timeEnd.substring(0, 2))) {
                out.println("<p class=\"text-center text-danger\"><strong>Khung giờ đã sung đột với các khung giờ khác </strong></p>");
            } else {
                if (fd.updatePriceTimeById(idPT, timeStart, timeEnd, price)) {
                    out.print("<p class=\"text-center text-success\"><strong>Thay Đổi Thành Công</strong></p>");
                } else {
                    out.println("<p class=\"text-center text-danger\"><strong>Đã có lỗi sảy ra vui lòng thử lại</strong></p>");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerPriceTime.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dao.ServerFieldDao;
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
import model.County;
import model.Receipt;
import model.ServerField;
import model.User;
import util.ValidationInput;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "ServerFieldControll", urlPatterns = {"/manager-server-field"})
public class ServerFieldControll extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            List<County> listC = new ArrayList<>();
            ServerField sf = new ServerFieldDao().getServerFieldByUserId("" + user.getId());
            if (sf == null) {
                new ServerFieldDao().addNewServerField("" + user.getId());
                sf = new ServerFieldDao().getServerFieldByUserId("" + user.getId());
            }
            listC = new ServerFieldDao().getAllCounty();
            out.println("<h2 class=\"heading\">Thông Tin Trung Tâm</h2>\n"
                    + "                <div class=\"cart-total cart-detail row\">\n"
                    + "                    <center>\n"
                    + "                        <img src=\"" + sf.getImage() + "\" class=\"img-detail\" style=\"width: 300px\">   \n"
                    + "                    </center>\n"
                    + "                    <div class=\"row align-items-end\">\n"
                    + "                        <div class=\"col-md-12\">\n"
                    + "                        <div class=\"form-group\">\n"
                    + "                            <label for=\"emailaddress\">Link Ảnh</label>\n"
                    + "                            <input type=\"text\"  class=\"form-control\" id=\"linkImage\" value=\"" + sf.getImage() + "\" class=\"form-control\"  required=\"\">\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                        <div class=\"col-md-12\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"emailaddress\">Tên Trung Tâm</label>\n"
                    + "                                <input type=\"text\" class=\"form-control\"id=\"name\"  value=\"" + sf.getNameServer() + "\" class=\"form-control\"  required=\"\">\n"
                    + "                        </div>\n"
                    + "                    </div> \n"
                    + "                    <div class=\"col-md-4\">\n"
                    + "                        <div class=\"form-group\">\n"
                    + "                            <label for=\"firstname\">Email</label>\n"
                    + "                            <input type=\"text\"  class=\"form-control\" id=\"email\" value=\"" + sf.getEmail() + "\" class=\"form-control\"  required=\"\">\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-md-4\">\n"
                    + "                        <div class=\"form-group\">\n"
                    + "                            <label for=\"phone\">Số Điện Thoại</label>\n"
                    + "                            <input type=\"text\"  class=\"form-control\" id=\"phone\" value=\"" + sf.getPhone() + "\" class=\"form-control\" required=\"\">\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-md-4\">\n"
                    + "                        <div class=\"form-group\">\n"
                    + "                            <label for=\"phone\">Quận</label>\n"
                    + "                            <div class=\"select-wrap\">\n"
                    + "                                <select id=\"county\" class=\"form-control\">\n");
            for (County c : listC) {
                if (c.getName().equalsIgnoreCase(sf.getCounty())) {
                    out.println("<option value=\"" + c.getId() + "\" selected=\"selected\">" + sf.getCounty() + "</option>\n");
                } else {
                    out.println("<option value=\"" + c.getId() + "\">" + c.getName() + "</option>\n");
                }
            }

            out.println("                                </select>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-md-12\">\n"
                    + "                        <div class=\"form-group\">\n"
                    + "                            <label for=\"emailaddress\">Địa Chỉ</label>\n"
                    + "                            <input type=\"text\"  class=\"form-control\" id=\"address\" value=\"" + sf.getAddress() + "\" class=\"form-control\"  required=\"\">\n"
                    + "                        </div>\n"
                    + "                    </div>  \n"
                    + "                    <div class=\"col-md-12\">\n"
                    + "                        <div class=\"form-group\">\n"
                    + "                            <label for=\"emailaddress\">Link Vị Trí Trên Google Map</label>\n"
                    + "                            <input type=\"text\"  class=\"form-control\" id=\"ggmap\" value=\"\" class=\"form-control\"  required=\"\">\n"
                    + "                        </div>\n"
                    + "                    </div> \n");
            if (sf.isActivate()) {
                out.println("<div class=\"col-md-12\">\n"
                        + "<div class=\"form-group\">\n"
                        + "<label style=\"margin-right:10px\" for=\"emailaddress\">Trạng Thái</label>\n<label class=\"switch\">\n"
                        + "  <input type=\"checkbox\" id=\"status\" checked>\n"
                        + "  <span class=\"slider round\"></span>\n"
                        + "</label>\n"
                        + "</div>"
                        + "</div>");
            } else {
                out.println("<div class=\"col-md-12\">\n"
                        + "<div class=\"form-group\">\n"
                        + "<label style=\"margin-right:10px\" for=\"emailaddress\">Trạng Thái</label>\n<label class=\"switch\">\n"
                        + "  <input type=\"checkbox\" id=\"status\" >\n"
                        + "  <span class=\"slider round\"></span>\n"
                        + "</label>\n"
                        + "</div>"
                        + "</div>");
            }
            out.print("                    <button onclick=\"changeServerField()\" class=\"btn btn-primary py-3 px-4\" style=\"width: 30%\">Thay Đổi</button>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "<div id=\"error-input\" >\n"
                    + "                     </div>");

        } catch (SQLException ex) {
            Logger.getLogger(ServerFieldControll.class.getName()).log(Level.SEVERE, null, ex);
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
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");
        try (PrintWriter out = response.getWriter()) {
            String image = request.getParameter("image");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String county = request.getParameter("county");
            String address = request.getParameter("address");
            String status = request.getParameter("status");
            boolean error = false;
            String message = "";
            if (!ValidationInput.checkInputEmail(email, "" + user.getId(), 2)) {
                error = true;
                message = message + "Email";
            }
            if (!ValidationInput.checkInputPhone(phone, "" + user.getId(), 2)) {
                error = true;
                if (message == "") {
                    message = message + "Số điện thoại";
                } else {
                    message = message + " và Số điện thoại";
                }
            }
            if (error) {
                out.printf("<p class=\"text-center text-danger\"><strong>%s đã tồn tại hoặc không chính xác!</strong></p>", message);
            } else {
                if (new ServerFieldDao().updateServerFieldByManager(name, county, email, phone, address, image, status, "" + user.getId())) {
                    out.println("<p class=\"text-center text-success\"><strong>Thay thay đổi thành công!</strong></p>");
                } else {
                    out.println("<p class=\"text-center text-danger\"><strong>Thay thay đổi không thành công!</strong></p>");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerFieldControll.class.getName()).log(Level.SEVERE, null, ex);
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

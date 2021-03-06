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
import javax.servlet.http.HttpSession;
import model.Field;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "ManagerFieldControll", urlPatterns = {"/manager-field-controll"})
public class ManagerFieldControll extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession s = request.getSession();
            User user = (User) s.getAttribute("ACCOUNT");
            try (PrintWriter out = response.getWriter()) {
                List<Field> listF = new ArrayList<>();
                listF = new FieldDao().getFieldByIdManager("" + user.getId());

                out.println("<h2 class=\"heading\">S??n ??ang C?? ??? Trung T??m</h2>\n"
                        + "             <table class=\"table\">\n"
                        + "                    <thead class=\"thead-primary\">\n"
                        + "                        <tr class=\"text-center\">                          \n"
                        + "                            <th>M?? S??n</th>\n"
                        + "                            <th>Ng??y T???o</th>\n"
                        + "                            <th>Lo???i S??n</th>\n"
                        + "                            <th>Tr???ng Th??i</th>\n"
                        + "                            <th>T??y Ch???n</th>\n"
                        + "                        </tr>\n"
                        + "                    </thead>\n"
                        + "                    <tbody> ");
                for (Field f : listF) {
                    out.println(" <tr class=\"text-center\">                         \n"
                            + "             <td class=\"total\">" + f.getNameField() + "</td>\n"
                            + "             <td class=\"total\">" + f.getCreateDate() + "</td>\n"
                            + "             <td class=\"total\">" + f.getTypeField() + " Ng?????i</td>\n");
                    if (f.isStatus()) {
                        out.println("<td class=\"total\"><label class=\"switch\">\n"
                                + "  <input type=\"checkbox\" id=\"status-" + f.getId() + "\" onclick=\"changeStatusField(" + f.getId() + ")\" checked>\n"
                                + "  <span class=\"slider round\"></span>\n"
                                + "</label></td>");
                    } else {
                        out.println("<td class=\"total\"><label class=\"switch\">\n"
                                + "  <input type=\"checkbox\" id=\"status-" + f.getId() + "\" onclick=\"changeStatusField(" + f.getId() + ")\">\n"
                                + "  <span class=\"slider round\"></span>\n"
                                + "</label></td>");
                    }
                    out.println("             <td>\n"
                            + "              <button class=\"btn btn-outline-primary\" onclick=\"managerPriceTime(" + f.getId() + ")\" style=\"margin-right: 5px\" >Ch???nh S???a</button>\n"
                            + "             </td>\n"
                            + "</tr>");
                }
                out.println(" </tbody>\n"
                        + "</table>\n"
                        + "<button onclick=\"addFieldForm()\" id=\"add\" class=\"btn btn-primary\" >Th??m</button>\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFieldControll.class.getName()).log(Level.SEVERE, null, ex);
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
            response.setContentType("text/html;charset=UTF-8");
            String idF = request.getParameter("idF");
            String status = request.getParameter("status");
            System.out.println(idF + "-" + status);
            new FieldDao().changeFieldById(idF, status);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFieldControll.class.getName()).log(Level.SEVERE, null, ex);
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

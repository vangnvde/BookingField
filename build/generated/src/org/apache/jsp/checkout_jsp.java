package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class checkout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"hero-wrap hero-bread\" style=\"background-image: url('images/bg_1.jpg');\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row no-gutters slider-text align-items-center justify-content-center\">\n");
      out.write("                <div class=\"col-md-9 ftco-animate text-center\">\n");
      out.write("                    <p class=\"breadcrumbs\"><span class=\"mr-2\"><a href=\"index.html\">Home</a></span> <span>Checkout</span></p>\n");
      out.write("                    <h1 class=\"mb-0 bread\">Checkout</h1>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <section class=\"ftco-section\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row justify-content-center\">\n");
      out.write("                <div class=\"col-xl-7 ftco-animate\">\n");
      out.write("                    <form action=\"#\" class=\"billing-form\">\n");
      out.write("                        <h3 class=\"mb-4 billing-heading\">Xác Nhận Thông Tin Đặt Sân</h3>\n");
      out.write("                        <div class=\"row align-items-end\">\n");
      out.write("                            <div class=\"col-md-6\">\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <label for=\"firstname\">Họ Và Tên</label>\n");
      out.write("                                    <input type=\"text\" id=\"fullname\" class=\"form-control\" placeholder=\"\" required=\"\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-6\">\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <label for=\"phone\">Số Điện Thoại</label>\n");
      out.write("                                    <input type=\"text\" id=\"phone\" class=\"form-control\" placeholder=\"\" required=\"\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"w-100\"></div>\n");
      out.write("                            <div class=\"col-md-6\">\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <label for=\"emailaddress\">Email Address</label>\n");
      out.write("                                    <input type=\"text\" id=\"email\" class=\"form-control\" placeholder=\"\" required=\"\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>                                       \n");
      out.write("\n");
      out.write("                            <div class=\"col-md-6\">\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <label for=\"streetaddress\">Địa Chỉ</label>\n");
      out.write("                                    <input type=\"text\" id=\"address\"class=\"form-control\"  placeholder=\"Tùy chọn\">\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                            <input type=\"submit\" class=\"btn btn-primary py-3 px-4\" value=\"Xác Nhận\">\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </form><!-- END -->\n");
      out.write("                </div>\n");
      out.write("                    <div class=\"col-xl-5\">\n");
      out.write("                        <div class=\"row mt-5 pt-3\">\n");
      out.write("                            <div class=\"col-md-12 d-flex mb-5\">\n");
      out.write("                                <div class=\"cart-detail cart-total p-3 p-md-4\">\n");
      out.write("                                    <h3 class=\"billing-heading mb-4\">Thông Tin Sân</h3>\n");
      out.write("                                    <p class=\"d-flex\">\n");
      out.write("                                        <span>Trung Tâm</span>\n");
      out.write("                                        <span>$20.60</span>\n");
      out.write("                                    </p>\n");
      out.write("                                    <p class=\"d-flex\">\n");
      out.write("                                        <span>Mã Sân</span>\n");
      out.write("                                        <span>$0.00</span>\n");
      out.write("                                    </p>\n");
      out.write("                                    <p class=\"d-flex\">\n");
      out.write("                                        <span>Thời Gian</span>\n");
      out.write("                                        <span>$3.00</span>\n");
      out.write("                                    </p>\n");
      out.write("                                    <hr>\n");
      out.write("                                    <p class=\"d-flex total-price\">\n");
      out.write("                                        <span>Giá</span>\n");
      out.write("                                        <span>$17.60</span>\n");
      out.write("                                    </p>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div> <!-- .col-md-8 -->\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("    </section> <!-- .section -->\n");
      out.write("\n");
      out.write("    <section class=\"ftco-section ftco-no-pt ftco-no-pb py-5 bg-light\">\n");
      out.write("        <div class=\"container py-4\">\n");
      out.write("            <div class=\"row d-flex justify-content-center py-5\">\n");
      out.write("                <div class=\"col-md-6\">\n");
      out.write("                    <h2 style=\"font-size: 22px;\" class=\"mb-0\">Subcribe to our Newsletter</h2>\n");
      out.write("                    <span>Get e-mail updates about our latest shops and special offers</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-6 d-flex align-items-center\">\n");
      out.write("                    <form action=\"#\" class=\"subscribe-form\">\n");
      out.write("                        <div class=\"form-group d-flex\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Enter email address\">\n");
      out.write("                            <input type=\"submit\" value=\"Subscribe\" class=\"submit px-3\">\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </section>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

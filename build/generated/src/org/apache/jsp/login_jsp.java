package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <div class=\"hero-wrap hero-bread\" style=\"background-image: url('images/banner1.jpg');\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row no-gutters slider-text align-items-center justify-content-center\">\n");
      out.write("                <div class=\"col-md-9 ftco-animate text-center\">\n");
      out.write("                    <p class=\"breadcrumbs\"><span class=\"mr-2\"><a href=\"index.html\">Home</a></span> <span>Contact us</span></p>\n");
      out.write("                    <h1 class=\"mb-0 bread\">Contact us</h1>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <section class=\"ftco-section contact-section bg-light\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row block-9\">\n");
      out.write("                <div class=\"col-md-6 order-md-last d-flex\">\n");
      out.write("                    <form action=\"#\" class=\"bg-white p-5 contact-form\">\n");
      out.write("                        <h2 class=\"login\">Đăng Nhập</h2>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Your Name\" required>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Your Password\" required>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <a href=\"#\">Quên mật khẩu</a>   \n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group row\">\n");
      out.write("                            <input type=\"submit\" value=\"Đăng Nhập\" class=\"btn btn-primary col-md-6 py-3 px-5\" >                            \n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group row\">\n");
      out.write("                        <p>Chưa có tài khoản <a href=\"\">Đăng Ký</a></p>\n");
      out.write("                        </div>\n");
      out.write("                    </form>        \n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-6 d-flex\">                  \n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </section>\n");
      out.write("\n");
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

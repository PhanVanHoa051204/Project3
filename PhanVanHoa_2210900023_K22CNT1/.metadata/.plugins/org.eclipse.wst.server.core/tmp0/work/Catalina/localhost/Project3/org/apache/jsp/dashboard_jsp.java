/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.100
 * Generated at: 2025-03-14 12:34:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<title>Admin</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"style.css\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<!-- Sidebar -->\r\n");
      out.write("		<div class=\"sidebar\">\r\n");
      out.write("			<div class=\"logo\">Admin</div>\r\n");
      out.write("			<ul>\r\n");
      out.write("				<li class=\"active\"><a href=\"#\">Quản trị</a></li>\r\n");
      out.write("				<li><a class=\"\"\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/NguoiDung/users.jsp\">Quản\r\n");
      out.write("						lý người dùng</a></li>\r\n");
      out.write("				<li><a class=\"\"\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/ChiTieu/categories.jsp\">Quản\r\n");
      out.write("						lý chi tiêu</a></li>\r\n");
      out.write("				<li><a class=\"\"\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/NganSach/budgets.jsp\">Quản\r\n");
      out.write("						lý ngân sách</a></li>\r\n");
      out.write("				<li><a\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/GiaoDich/transactions.jsp\">Quản\r\n");
      out.write("						lý giao dịch</a></li>\r\n");
      out.write("				<li><a\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/GDDinhKy/recurringtransactions.jsp\">Quản\r\n");
      out.write("						lý giao dịch định kỳ</a></li>\r\n");
      out.write("				<li><a\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/TietKiem/goals.jsp\">Tiết\r\n");
      out.write("						kiệm</a></li>\r\n");
      out.write("				<li><a href=\"https://web.facebook.com/PhanHoa05122004\">Liên\r\n");
      out.write("						hệ</a></li>\r\n");
      out.write("\r\n");
      out.write("			</ul>\r\n");
      out.write("		</div>\r\n");
      out.write("\r\n");
      out.write("		<!-- Main Content -->\r\n");
      out.write("		<div class=\"main-content\">\r\n");
      out.write("			<header>\r\n");
      out.write("				<div class=\"\">\r\n");
      out.write("					<!-- <input type=\"\" placeholder=\"\"> -->\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"submit-section\">\r\n");
      out.write("					<a href=\"login.jsp\" class=\"logout-btn\">Đăng xuất</a>\r\n");
      out.write("				</div>\r\n");
      out.write("			</header>\r\n");
      out.write("\r\n");
      out.write("			<!-- <!-- Dashboard Content -->\r\n");
      out.write("			<div class=\"dashboard-content\">\r\n");
      out.write("				<h2>Chào mừng đến với trang Admin</h2>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("				<div class=\"cards\">\r\n");
      out.write("					<div class=\"card\">\r\n");
      out.write("						<h3>Sales Today</h3>\r\n");
      out.write("						<p>145</p>\r\n");
      out.write("						<span class=\"increase\">12% increase</span>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"card\">\r\n");
      out.write("						<h3>Revenue This Month</h3>\r\n");
      out.write("						<p>$3,264</p>\r\n");
      out.write("						<span class=\"increase\">8% increase</span>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"card\">\r\n");
      out.write("						<h3>Customers This Year</h3>\r\n");
      out.write("						<p>1,244</p>\r\n");
      out.write("						<span class=\"decrease\">12% decrease</span>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				Reports Chart\r\n");
      out.write("				<div class=\"chart-section\">\r\n");
      out.write("					<h3>Reports | Today</h3>\r\n");
      out.write("					<canvas id=\"reportsChart\"></canvas>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				Recent Activity\r\n");
      out.write("				<div class=\"recent-activity\">\r\n");
      out.write("					<h3>Recent Activity | Today</h3>\r\n");
      out.write("					<ul>\r\n");
      out.write("						<li><span class=\"dot green\"></span>32 min - Quia quaerere rem\r\n");
      out.write("							explicabo officiis beatae</li>\r\n");
      out.write("						<li><span class=\"dot red\"></span>56 min - Voluptatem\r\n");
      out.write("							blanditiis blanditiis eveniet</li>\r\n");
      out.write("						<li><span class=\"dot blue\"></span>2 hrs - Voluptates corrupti\r\n");
      out.write("							molestias voluptatem</li>\r\n");
      out.write("						<li><span class=\"dot yellow\"></span>1 day - Tempore autem\r\n");
      out.write("							saepe occaecati voluptatem tempore</li>\r\n");
      out.write("					</ul>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				Budget Report\r\n");
      out.write("				<div class=\"budget-report\">\r\n");
      out.write("					<h3>Budget Report | This Month</h3>\r\n");
      out.write("					<canvas id=\"budgetChart\"></canvas>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				Recent Sales\r\n");
      out.write("				<div class=\"recent-sales\">\r\n");
      out.write("					<h3>Recent Sales | Today</h3>\r\n");
      out.write("					<table>\r\n");
      out.write("						<thead>\r\n");
      out.write("							<tr>\r\n");
      out.write("								<th>ID</th>\r\n");
      out.write("								<th>Product</th>\r\n");
      out.write("								<th>Price</th>\r\n");
      out.write("								<th>Time</th>\r\n");
      out.write("							</tr>\r\n");
      out.write("						</thead>\r\n");
      out.write("						<tbody>\r\n");
      out.write("							<tr>\r\n");
      out.write("								<td>#123</td>\r\n");
      out.write("								<td>Product A</td>\r\n");
      out.write("								<td>$50</td>\r\n");
      out.write("								<td>10:00 AM</td>\r\n");
      out.write("							</tr>\r\n");
      out.write("\r\n");
      out.write("						</tbody>\r\n");
      out.write("					</table>\r\n");
      out.write("					<div class=\"table-footer\">\r\n");
      out.write("						<select>\r\n");
      out.write("							<option>10 entries per page</option>\r\n");
      out.write("						</select> <input type=\"text\" placeholder=\"Search...\">\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

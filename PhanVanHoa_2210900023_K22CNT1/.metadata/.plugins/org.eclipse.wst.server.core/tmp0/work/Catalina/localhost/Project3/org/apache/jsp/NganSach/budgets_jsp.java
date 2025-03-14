/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.100
 * Generated at: 2025-03-14 14:26:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.NganSach;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.financemanager.model.Budget;
import com.financemanager.dao.BudgetDAO;

public final class budgets_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = new java.util.LinkedHashSet<>(4);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.financemanager.model.Budget");
    _jspx_imports_classes.add("com.financemanager.dao.BudgetDAO");
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
      out.write("\r\n");

BudgetDAO budgetDAO = new BudgetDAO();
List<Budget> budgets = budgetDAO.getAllBudgets();

if (budgets.isEmpty()) {
	out.println("<p class='text-danger'>Không có dữ liệu ngân sách hoặc lỗi kết nối.</p>");
}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Quản lý Ngân sách</title>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<div class=\"container mt-4\">\r\n");
      out.write("		<h2>Danh sách Ngân sách</h2>\r\n");
      out.write("\r\n");
      out.write("		<table class=\"table table-bordered mt-3\">\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>ID</th>\r\n");
      out.write("				<th>ID Người dùng</th>\r\n");
      out.write("				<th>ID Danh mục</th>\r\n");
      out.write("				<th>Số tiền</th>\r\n");
      out.write("				<th>Thời gian</th>\r\n");
      out.write("				<th>Hành động</th>\r\n");
      out.write("			</tr>\r\n");
      out.write("			");

			for (Budget budget : budgets) {
			
      out.write("\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>");
      out.print(budget.getBudgetId());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(budget.getUserId());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(budget.getCategoryId());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(budget.getAmount());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print((budget.getMonth() != null) ? budget.getMonth().toString() : "N/A");
      out.write("</td>\r\n");
      out.write("				<td><a\r\n");
      out.write("					href=\"updatebudget.jsp?budget_id=");
      out.print(budget.getBudgetId());
      out.write("\"\r\n");
      out.write("					class=\"btn btn-warning\">Sửa</a> <a\r\n");
      out.write("					href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/budgets?action=delete&budget_id=");
      out.print( budget.getBudgetId() );
      out.write("\"\r\n");
      out.write("					class=\"btn btn-danger btn-sm\"\r\n");
      out.write("					onclick=\"return confirm ('Ban muon xoa chu');\">Xoá</a></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			");

			}
			
      out.write("\r\n");
      out.write("		</table>\r\n");
      out.write("		<a href=\"addbudget.jsp\" class=\"btn btn-success\">Thêm Ngân sách</a>\r\n");
      out.write("		 <a\r\n");
      out.write("			href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/dashboard.jsp\"\r\n");
      out.write("			class=\"btn btn-primary\">Quay lại</a>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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

package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import utility.SecurityPassword;

@WebServlet("/login.adm")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "";
	
	public AdminLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pw = SecurityPassword.encoding(request.getParameter("pw"));

		AdminDAO mdao = new AdminDAO();
		String result = mdao.AdminIdPwSearch(id, pw);

		// page < request < session < application < project
		HttpSession session = request.getSession();

		if (result.equals(0) || result.equals(-1)) {
			request.setAttribute("msg", "계정을 다시 확인하세요");
			url = "admin/login.jsp";
			fff(request, response);
		} else {
			session.setAttribute("userid", id);
			session.setAttribute("accessToken", result);
			response.sendRedirect("main.adm");
		}
	}

	protected void fff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}

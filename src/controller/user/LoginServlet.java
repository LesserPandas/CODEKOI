package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import utility.SecurityPassword;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "";

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("user/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pw = SecurityPassword.encoding(request.getParameter("pw"));

		MemberDAO mdao = new MemberDAO();

		int result = mdao.memberIdPwSearch(id, pw);

		// page < request < session < application < project
		HttpSession session = request.getSession();

		if (result == 1) {
			session.setAttribute("userid", id);
			response.sendRedirect("Main.do");
		} else if (result == 0) {
			request.setAttribute("msg", "비밀번호를 확인하세요");
			url = "user/login.jsp";
			fff(request, response);
		} else if (result == -1) {
			request.setAttribute("msg", "일치하는 회원이 없습니다.");
			url = "user/login.jsp";
			fff(request, response);
		}
	}

	protected void fff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}

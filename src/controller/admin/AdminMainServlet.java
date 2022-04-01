package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dto.MemberVO;

@WebServlet("/main.adm")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminMainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		AdminDAO adao = new AdminDAO();
		
		
		HttpSession session = request.getSession();
		String accessToken = null;
		if (session.getAttribute("accessToken") != null) {
			accessToken = (String) session.getAttribute("accessToken");
		}
		if (adao.checkToken(accessToken)) {
			
			//여기 아래로 실행 구문넣기

			List<MemberVO> list = adao.userSelect();
			request.setAttribute("userList", list);
			
			int count = adao.userCounting();
			request.setAttribute("count", count);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("admin/index.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("login.adm");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

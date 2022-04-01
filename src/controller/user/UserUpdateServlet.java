package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberVO;
import utility.SecurityPassword;

@WebServlet("/userUpdate.do")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserUpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = (String) request.getSession().getAttribute("userid");
		MemberVO mvo = new MemberDAO().selectUserID(id);

		request.setAttribute("user", mvo);

		RequestDispatcher rd = request.getRequestDispatcher("user/myPageUpdate.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println(bno);
		String pw = request.getParameter("pw1");

		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		System.out.println(pw + " " + phone + ' ' + email);
		MemberVO mvo = new MemberVO();
		mvo.setBno(bno);
		if (pw != null && !pw.equals("")) {
			pw = SecurityPassword.encoding(pw);
			mvo.setPw(pw);
		}
		mvo.setPhone(phone);
		mvo.setEmail(email);
		MemberDAO dao = new MemberDAO();
		dao.memberUpdate(mvo);

		response.sendRedirect("Main.do");
	}

}

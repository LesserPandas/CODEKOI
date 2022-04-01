package controller.user;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

@WebServlet("/checkUserID.do")
public class CheckUserIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckUserIDServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		MemberDAO mdao = new MemberDAO();
		int result = mdao.checkUserID(id);
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}

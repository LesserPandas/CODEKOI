package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/myPage.do")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("user/myPage.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String certinumber = request.getParameter("certinumber");
		String AuthenticationKey = (String) request.getSession().getAttribute("AuthenticationKey");
		
		JSONObject obj = new JSONObject();
		
		if(!AuthenticationKey.equals(certinumber)) {
			obj.put("msg", "인증번호가 일치하지 않습니다.");
			obj.put("check", "nok");
		} else {
			obj.put("msg", "회원정보를 수정합니다.");
			obj.put("check", "ok");
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json, charset=utf-8");
		response.getWriter().print(obj);
	}

}

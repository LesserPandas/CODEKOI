package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;

import dao.MemberDAO;
import dto.MemberVO;
import utility.SecurityPassword;

/**
 * Servlet implementation class SocialLoginServlet
 */
@WebServlet("/socialLogin.do")
public class SocialLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SocialLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		NetHttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = new GsonFactory();

		String idTokenString = request.getParameter("id_token");
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections
						.singletonList(""))
				.build();

		Gson gson = new Gson();
		GoogleIdToken idToken = null; 
		PrintWriter out = response.getWriter();
		
		try {
			idToken = verifier.verify(idTokenString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (idToken != null) { 
			Payload payload = idToken.getPayload();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			if (emailVerified) {

				MemberVO mvo = new MemberVO();
				MemberDAO dao = new MemberDAO();
				String userId = payload.getSubject();
				String email = payload.getEmail();
				String name = (String) payload.get("name");
				userId = "g-" + userId.substring(0,6);

				int result = dao.checkUserID(userId);
				if (result == 0) { // DB에 ID가 없으면 저장
					mvo.setId(userId);
					mvo.setPw(SecurityPassword.encoding(userId));
					mvo.setEmail(email);
					mvo.setName(name);
					dao.memberInsertByGoogle(mvo);
				} else {
					System.out.println("존재하는 아이디입니다.");
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("userid", userId);
				out.print(userId);
			}
		} else { // 유효하지 않은 토큰
			System.out.println("invaild token");
			out.print("잘못된 연결입니다.");
		} // end else
	}

}

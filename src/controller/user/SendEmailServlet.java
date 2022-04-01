package controller.user;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/sendEmail.do")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendEmailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("");
		System.out.println(email);
		
		String host = "";
		String user = ""; // 자신의 네이버 계정
		String password = "";// 자신의 네이버 패스워드

		
		// 메일 받을 주소
		String to_email = email;

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", );
		props.put("mail.smtp.auth", "");
		props.put("mail.smtp.ssl.enable", "");
		props.put("mail.smtp.ssl.protocols", "");
		props.put("mail.smtp.timeout", );
		
		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString();// 인증번호 10개 생성됨!
		System.out.println(AuthenticationKey); // 생성된게 출력 : (인증키)

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password); // naver 접속 인증 허가
			}
		});
		session.setDebug(true); // DEBUG
		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "CODEKOI"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("안녕하세요! CODEKOI 마이페이지 인증 메일입니다.");
			// 메일 내용
			msg.setText("인증 번호 : " + temp);

			Transport.send(msg);
			System.out.println("이메일 전송 완료");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		HttpSession saveKey = request.getSession(); // 모든 페이지에서 사용하기 위해 session 객체 사용
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);

		JSONObject obj = new JSONObject();
		obj.put("msg", "입력하신 이메일로 인증이 전송되었습니다");

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json, charset=utf-8");
		response.getWriter().print(obj); // 인증완료
	}

}

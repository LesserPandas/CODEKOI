package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import dao.ReplyDAO;
import dto.ReplyVO;

@WebServlet("/reply.do")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int pbno = Integer.parseInt(request.getParameter("pbno"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		ReplyVO rvo = new ReplyVO();
		rvo.setContent(content);
		rvo.setPbno(pbno);
		rvo.setWriter(writer);
		
		ReplyDAO rdao = new ReplyDAO();
		rdao.replyInsert(rvo);
		rdao.replyCounting(pbno, true);
		List<ReplyVO> rplist = rdao.replySelect(pbno);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json, charset=utf-8");
		
		Gson gson = new Gson();
		
		try {
			response.getWriter().print(gson.toJson(rplist));
		} catch(JsonIOException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}

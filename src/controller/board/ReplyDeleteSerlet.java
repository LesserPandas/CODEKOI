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

@WebServlet("/replyDel.do")
public class ReplyDeleteSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyDeleteSerlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bno = 0;
		ReplyDAO rdao = new ReplyDAO();
		
		if(request.getParameter("bno")  != null) {
			bno = Integer.parseInt(request.getParameter("bno"));
			rdao.replyDelete(bno);
			
		}
		int pbno = Integer.parseInt(request.getParameter("pbno"));
		List<ReplyVO> rplist = new ReplyDAO().replySelect(pbno);
		
		rdao.replyCounting(pbno, false);
		
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

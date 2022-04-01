package controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardVO;

@WebServlet("/boardModify.do")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO bvo = new BoardDAO().boardSelectbyBno(bno);
		
		request.setAttribute("view", bvo);
		
		RequestDispatcher rd = request.getRequestDispatcher("board/boardModify.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO bvo = new BoardVO();
		bvo.setBno(bno);
		bvo.setTitle(title);
		bvo.setContent(content);
		
		new BoardDAO().boardModify(bvo);
		
		response.sendRedirect("boardView.do?bno="+bno);
	}

}

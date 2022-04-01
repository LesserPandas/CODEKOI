package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.ReplyDAO;
import dto.BoardVO;
import dto.ReplyVO;

@WebServlet("/boardView.do")
public class BoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = bdao.boardSelectbyBno(bno);
		ReplyDAO rdao = new ReplyDAO();
		
		List<ReplyVO> rpList = rdao.replySelect(bno);
		
		request.setAttribute("rpList", rpList);
		request.setAttribute("boardView", bvo);
		bdao.boardCount(bno);
		
		RequestDispatcher rd = request.getRequestDispatcher("board/boardView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

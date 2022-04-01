package controller.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.PdsDAO;
import dao.WordDAO;
import dto.BoardVO;
import dto.PdsVO;
import dto.WordVO;

@WebServlet("/Main.do")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		BoardDAO bdao = new BoardDAO();
		List<BoardVO> qnaList = bdao.boardSelect();
		List<BoardVO> myList = bdao.myBoardSelect(id);
		int myCount = bdao.boardMyCount(id);
		
		WordDAO wdao = WordDAO.getInstance();
		List<WordVO> wordList = wdao.selectWord();
		
		PdsDAO pdao = new PdsDAO();
		List<PdsVO> pdslist = pdao.pdsSelect();
		
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("myList", myList);
		request.setAttribute("myCount", myCount);
		request.setAttribute("wordList", wordList);
		request.setAttribute("pdslist"	, pdslist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

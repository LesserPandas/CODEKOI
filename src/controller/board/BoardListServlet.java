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
import dto.BoardVO;
import dto.PageVO;
import utility.Criteria;


@WebServlet("/board.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String sel="";
		String word="";
		String query="";
		
		int pageNum = 1;
		int amount = 10;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}

		if(request.getParameter("sel")!= null && !request.getParameter("word").equals("")){
			sel = request.getParameter("sel");
			word = request.getParameter("word");
			query= sel + " like '%" + word + "%'";
			System.out.println(query);
		}
		Criteria cri = new Criteria();
		
		cri.setPageNum(pageNum);
		cri.setAmount(amount);
		cri.setKeyword(sel);
		cri.setType(word);
		
		System.out.println(cri.getPageNum());
		System.out.println(cri.getAmount());
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.boardSelect(cri, query);
		int pageCount = dao.boardAllCount();
		PageVO pvo =  new PageVO(cri, pageCount);
		
		request.setAttribute("pageMaker", pvo);	
		request.setAttribute("list", list);
		request.setAttribute("count", pageCount);
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/board.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

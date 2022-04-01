package controller.pds;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PdsDAO;
import dto.PageVO;
import dto.PdsVO;
import utility.Criteria;

@WebServlet("/pds.do")
public class PdsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PdsListServlet() {
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
		
		PdsDAO pdao =  new PdsDAO();
		List<PdsVO> pdslist =pdao.pdsSelect();
		int pageCount = pdao.pdsAllCount();
		PageVO pvo =  new PageVO(cri, pageCount);
		request.setAttribute("pdslist", pdslist);
		request.setAttribute("pageMaker", pvo);
		request.setAttribute("count", pageCount);
		
		RequestDispatcher rd = request.getRequestDispatcher("pds/board.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

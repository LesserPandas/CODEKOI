package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewsDAO;
import dto.NewsVO;

@WebServlet("/news.do")
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewsListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		NewsDAO ndao = new NewsDAO();
		
		List<NewsVO> list = ndao.newsSelect();
		request.setAttribute("newsList", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("board/news.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

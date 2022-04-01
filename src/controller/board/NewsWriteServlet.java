package controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NewsDAO;
import dto.NewsVO;

@WebServlet("/newsWrite.do")
public class NewsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewsWriteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("board/newsWrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String savepath = "upload";
		int maxPostSize = 20 * 1024 * 1024;
		String enctype = "utf-8";

		ServletContext context = getServletContext();
		String path = context.getRealPath(savepath);
		System.out.println("서버 상의 실제 디렉토리 : " + path);

		MultipartRequest multi = new MultipartRequest(request, path, maxPostSize, enctype,
				new DefaultFileRenamePolicy());
		String title = multi.getParameter("title");
		String writer = "";
		if (request.getSession().getAttribute("userid") != null)
			writer = (String) request.getSession().getAttribute("userid");
		else
			writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		String imgurl = multi.getFilesystemName("imgurl");
		System.out.println(imgurl);

		NewsVO nvo = new NewsVO();
		nvo.setTitle(title);
		nvo.setWriter(writer);
		nvo.setContent(content);
		nvo.setImgurl(imgurl);

		NewsDAO ndao = new NewsDAO();
		ndao.newsInsert(nvo);

		response.sendRedirect("news.do");
	} // post
}

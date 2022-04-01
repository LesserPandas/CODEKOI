package controller.pds;

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

import dao.PdsDAO;
import dto.PdsVO;

@WebServlet("/pdsWrite.do")
public class PdsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PdsWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("pds/boardWrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String savepath = "pdsUpload";
		int maxPostSize = 20 * 1024 * 1024;
		String enctype = "utf-8";

		ServletContext context = getServletContext();
		String path = context.getRealPath(savepath);
		System.out.println("서버 상의 실제 디렉토리 : " + path);

		MultipartRequest multi = new MultipartRequest(request, path, maxPostSize, enctype,
				new DefaultFileRenamePolicy());
		String title = multi.getParameter("title");
		String writer = (String) request.getSession().getAttribute("userid");
		String content = multi.getParameter("content");
		String file = multi.getFilesystemName("filename");
		System.out.println(file);

		PdsVO pvo = new PdsVO();
		pvo.setTitle(title);
		pvo.setWriter(writer);
		pvo.setContent(content);
		pvo.setFilename(file);

		PdsDAO pdao = new PdsDAO();
		pdao.pdsInsert(pvo);

		response.sendRedirect("pds.do");
		
		
	}

}

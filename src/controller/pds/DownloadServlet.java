package controller.pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downPds.do")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	      String filename = request.getParameter("filename");
	      String directory = this.getServletContext().getRealPath("pdsUpload");
	      //업로드 폴더의 절대경로를 구한다
	      
	      File file = new File(directory+"/"+filename);
	      //자바.io 패키지는 기존의 파일이니 폴더에 대한 제어를 사용하는 file클래스를 제공한다
	      
	      String mimeType = getServletContext().getMimeType(file.toString());
	      //파일 클래스 객체의 투스트링() 메서드는 자신이 가진 해당 경로값을 리턴한다
	      
	      if(mimeType== null) {
	         response.setContentType("application/octet-stream");
	      }
	      
	      String downloadName = null;
	      if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
	         //request.getHeader("user-agent") 를 이용한 웹 브라우저 종류 확인하기
	         //indexof(문자열 찾기) msie라는 글자가 user-agent라는 곳 안에 포함되어 있으면~ 0보다 큰 값이 저장됨
	         downloadName = new String(filename.getBytes("UTF-8"),"8859_1");
	         //브라우저에 따른 한글 인코딩 문제 처리 (getbutes: 1바이트씩 읽어와라ㅋ)
	      } else {
	         downloadName = new String(filename.getBytes("EUC-KR"),"8859_1");
	      }
	      
	      response.setHeader("Content-Disposition", "attachment; filename=\""
		            + downloadName + "\";");
		     // 일반적인 http 응답에서 Content-Disposition 헤더는 컨텐츠가 브라우저에 
			// inline 되어야하는 웹페이지 자체이거나 웹페이지의 일부분인지 
			// 아니면 attachment 로서 다운로드 되거나 로컬에 저장될 용도로 쓰이는 것인지 알려주는 
			// 헤더이다 
	      
	      FileInputStream fileinputStream = new FileInputStream(file);
	      ServletOutputStream servletOutputStream = response.getOutputStream();
	      
	      byte b[] = new byte [1024];
	      int data= 0;
	      while((data= (fileinputStream.read(b,0,b.length))) != -1) {
	         servletOutputStream.write(b,0,data);
	      }
	      
	      servletOutputStream.flush();
	      servletOutputStream.close();
	      fileinputStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

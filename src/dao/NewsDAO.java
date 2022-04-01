package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.NewsVO;

public class NewsDAO {

	DBManager dbm = DBManager.getInstance();

	public void newsInsert(NewsVO nvo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into P1_IT_NEWS (bno, title, content, writer, imgurl) "
				+ "values (P1_IT_NEWS_SEQ.nextval,?,?,?,?)";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nvo.getTitle());
			pstmt.setString(2, nvo.getContent());
			pstmt.setString(3, nvo.getWriter());
			pstmt.setString(4, nvo.getImgurl());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}

	
	public List<NewsVO> newsSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from P1_IT_NEWS order by bno desc";
		
		List<NewsVO> newsList = new ArrayList<>();
		
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NewsVO nvo = new NewsVO();
				nvo.setBno(rs.getInt(1));
				nvo.setTitle(rs.getNString(2));
				nvo.setContent(rs.getNString(3));
				nvo.setWriter(rs.getNString(4));
				nvo.setWdate(rs.getNString(5));
				nvo.setViewcount(rs.getInt(6));
				nvo.setImgurl(rs.getNString(7));
				
				newsList.add(nvo);
			}
		} catch(Exception e) {
			
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return newsList;
	}
}

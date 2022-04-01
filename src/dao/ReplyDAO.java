package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.ReplyVO;

public class ReplyDAO {
	DBManager dbm = DBManager.getInstance();

	public int replyInsert(ReplyVO rvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;
		String sql = "insert into p1_it_qna_reply " + "(bno, writer, content, pbno) "
				+ "values (p1_it_qna_reply_seq.nextval, ?, ?, ?)";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (conn != null)
				System.out.println("ok");
			pstmt.setString(1, rvo.getWriter());
			pstmt.setString(2, rvo.getContent());
			pstmt.setInt(3, rvo.getPbno());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}

	public List<ReplyVO> replySelect(int pbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String sql = "select * from p1_it_qna_reply where pbno = ? order by bno";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pbno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyVO rvo = new ReplyVO();
				rvo.setBno(rs.getInt("bno"));
				rvo.setContent(rs.getString("content"));
				rvo.setPbno(rs.getInt("pbno"));
				rvo.setWdate(rs.getNString("wdate"));
				rvo.setWriter(rs.getNString("writer"));
				list.add(rvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}

	public void replyDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;
		String sql = "delete p1_it_qna_reply where bno = ?";
		
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void replyCounting(int bno, boolean controller) {
		Connection conn =  null;
		PreparedStatement pstmt = null;
		System.out.println(bno);
		System.out.println(controller);
		String sql = null;
		if( controller == true) {
			sql = "update  p1_it_qna set replycount =  replycount + 1 where bno = ?";
		} if(controller == false ) {
			sql = "update  p1_it_qna set replycount =  replycount - 1 where bno = ?";
		}
		
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}
}

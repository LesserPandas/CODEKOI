package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.BoardVO;
import dto.PdsVO;

public class PdsDAO {
	DBManager dbm = DBManager.getInstance();

	public void pdsInsert(PdsVO pvo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into P1_pds (bno, title, content, writer, filename) "
				+ "values (P1_pds_SEQ.nextval,?,?,?,?)";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getTitle());
			pstmt.setString(2, pvo.getContent());
			pstmt.setString(3, pvo.getWriter());
			pstmt.setString(4, pvo.getFilename());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}

	
	public List<PdsVO> pdsSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from P1_PDS order by bno desc";
		
		List<PdsVO> pdsList = new ArrayList<>();
		
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PdsVO pvo = new PdsVO();
				pvo.setBno(rs.getInt("bno"));
				pvo.setTitle(rs.getNString("title"));
				pvo.setContent(rs.getNString("content"));
				pvo.setWriter(rs.getNString("writer"));
				pvo.setWdate(rs.getNString("wdate"));
				pvo.setViewcount(rs.getInt("viewcount"));
				pvo.setFilename(rs.getNString("filename"));
				
				pdsList.add(pvo);
			}
		} catch(Exception e) {
			
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return pdsList;
	}


	public int pdsAllCount() {
		
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from P1_PDS order by bno desc";
	
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count++;
			}
		} catch(Exception e) {
			
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}


	public PdsVO pdsSelectbyBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from P1_pds where bno = ?";
		PdsVO bvo = new PdsVO();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bvo.setBno(rs.getInt("bno"));
				bvo.setTitle(rs.getNString("title"));
				bvo.setContent(rs.getNString("content"));
				bvo.setWriter(rs.getNString("writer"));
				bvo.setWdate(rs.getNString("wdate"));
				bvo.setViewcount(rs.getInt("viewcount"));
				bvo.setFilename(rs.getString("filename"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return bvo;
	}
}

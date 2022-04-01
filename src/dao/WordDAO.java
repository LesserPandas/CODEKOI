package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.WordVO;

public class WordDAO {
	private WordDAO() {
	}

	private static WordDAO instance = new WordDAO();

	public static WordDAO getInstance() {
		return instance;
	}
	
	DBManager dbm = DBManager.getInstance();
		
	public void insertWord(WordVO wvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into p1_word "
				+ "(bno, jpword, yomigana, krword, pumsa, jlpt) "
				+ "values (p1_word_seq.nextval, ?,?,?,?,?)";
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt   = conn.prepareStatement(sql);
			pstmt.setNString(1, wvo.getJpword());
			pstmt.setNString(2, wvo.getYomigana());
			pstmt.setNString(3, wvo.getKrword());
			pstmt.setNString(4, wvo.getPumsa());
			pstmt.setNString(5, wvo.getJlpt());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void deleteWord() {}
	
	
	public List<WordVO> selectWord() {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * FROM (select * from p1_word order by dbms_random.value) where rownum <= 5";
		List<WordVO> wordlist = new ArrayList<>();
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt   = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				WordVO wvo = new WordVO();
				wvo.setBno(rs.getInt("bno"));
				wvo.setJpword(rs.getNString("jpword"));
				wvo.setYomigana(rs.getNString("yomigana"));
				wvo.setKrword(rs.getNString("krword"));
				wvo.setPumsa(rs.getNString("pumsa"));
				wvo.setJlpt(rs.getNString("jlpt"));
				wordlist.add(wvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		return wordlist;
	}
}
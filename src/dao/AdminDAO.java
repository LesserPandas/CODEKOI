package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.MemberVO;

public class AdminDAO {
	DBManager dbm = DBManager.getInstance();

	
	//어드민 로그인 (return 액세스토큰)
	public String AdminIdPwSearch(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select pw, access_token from admin where id=?";
		String accessToken = null;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("pw") != null && rs.getString("pw").equals(pw)) {
					accessToken = rs.getNString("access_token");
				} else
					accessToken = "0";
			} else {
				accessToken = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return accessToken;
	}
	
	//액세스토큰 체크
	public boolean checkToken(String accessToken) {
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from admin where access_token=?";
		boolean result = false;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, accessToken);
			rs = pstmt.executeQuery();

			if (rs.next()) result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//유저 리스트 출력	
	public List<MemberVO> userSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from p1_user";
		List<MemberVO> list = new ArrayList<MemberVO>();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setBno(rs.getInt("bno"));
				String id = rs.getNString("id");
				mvo.setId(id);
				mvo.setPw(rs.getNString("pw"));
				mvo.setName(rs.getNString("name"));
				mvo.setPhone(rs.getNString("phone"));
				mvo.setEmail(rs.getNString("email"));
				mvo.setPost(countPost(id));
				mvo.setReply(countReply(id));
				list.add(mvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int userCounting() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bno from p1_user order by bno";
		int count = 0;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public int countPost(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bno from p1_it_qna where writer = ?";
		int count = 0;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public int countReply(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bno from p1_it_qna_reply where  writer = ?";
		int count = 0;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public void userDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete p1_user where  bno = ?";

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

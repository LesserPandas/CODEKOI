package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbm.DBManager;
import dto.MemberVO;

public class MemberDAO {
	DBManager dbm = DBManager.getInstance();

	public int checkUserID(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select id from p1_user where id=?";
		int result = 0;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}

	public int memberIdPwSearch(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select pw from p1_user where id=?";
		int result = 0;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("pw") != null && rs.getString("pw").equals(pw)) {
					result = 1;
				} else
					result = 0;
			} else {
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}

	public void memberInsert(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into p1_user " + "(bno, name, id, pw, phone, email) "
				+ "values (member_seq.nextval,?,?,?,?,?)";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, mvo.getName());
			pstmt.setNString(2, mvo.getId());
			pstmt.setNString(3, mvo.getPw());
			pstmt.setNString(4, mvo.getPhone());
			pstmt.setNString(5, mvo.getEmail());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}

	public MemberVO selectUserID(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from p1_user where id=?";
		MemberVO mvo = new MemberVO();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mvo.setBno(rs.getInt("bno"));
				mvo.setId(rs.getNString("id"));
				mvo.setPw(rs.getString("pw"));
				mvo.setName(rs.getString("name"));
				mvo.setPhone(rs.getNString("phone"));
				mvo.setEmail(rs.getNString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return mvo;
	}

	public void memberUpdate(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		if(mvo.getPw() != null) sql = "update p1_user set pw = ?, phone = ?, email = ? where bno = ?";
		else sql = "update p1_user set phone = ?, email = ? where bno = ?";
		
		try {
			int num = 1;
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(mvo.getPw() != null) pstmt.setNString(num++, mvo.getPw());
			pstmt.setNString(num++, mvo.getPhone());
			pstmt.setNString(num++, mvo.getEmail());
			pstmt.setInt(num++, mvo.getBno());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}

	public void memberInsertByGoogle(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into p1_user (bno, name, id, pw, phone, email) values (member_seq.nextval, ?,?,?,?,?)";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, mvo.getName());
			pstmt.setNString(2, mvo.getId());
			pstmt.setNString(3, mvo.getPw());
			pstmt.setNString(4, "000-0000-0000");
			pstmt.setNString(5, mvo.getEmail());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}
}
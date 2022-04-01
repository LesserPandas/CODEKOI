package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.BoardVO;
import utility.Criteria;

public class BoardDAO {

	DBManager dbm = DBManager.getInstance();

	// 게시글 입력
	public void boardInsert(BoardVO bvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into P1_IT_QNA (BNO,TITLE,CONTENT,WRITER) " + "values (BOARD_QNA_SEQ.nextval,?,?,?)";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContent());
			pstmt.setString(3, bvo.getWriter());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}

	// 게시글 리스트 출력 (Main용)
	public List<BoardVO> boardSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from p1_it_qna order by bno desc";
		List<BoardVO> list = new ArrayList<>();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO bno = new BoardVO();
				bno.setBno(rs.getInt(1));
				bno.setTitle(rs.getNString(2));
				bno.setContent(rs.getNString(3));
				bno.setWriter(rs.getNString(4));
				bno.setWdate(rs.getNString(5));
				bno.setViewcount(rs.getInt(6));
				bno.setAnswer(rs.getNString(7));
				bno.setAnswercount(rs.getInt(8));
				bno.setReplycount(rs.getInt(9));
				list.add(bno);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}

	// 내가 작성한 게시글 리스트 출력
	public List<BoardVO> myBoardSelect(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from p1_it_qna where writer = ? order by bno desc";
		List<BoardVO> list = new ArrayList<>();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO bno = new BoardVO();
				bno.setBno(rs.getInt(1));
				bno.setTitle(rs.getNString(2));
				bno.setContent(rs.getNString(3));
				bno.setWriter(rs.getNString(4));
				bno.setWdate(rs.getNString(5));
				bno.setViewcount(rs.getInt(6));
				bno.setAnswer(rs.getNString(7));
				bno.setAnswercount(rs.getInt(8));
				bno.setReplycount(rs.getInt(9));
				list.add(bno);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}

	// 내가 작성한 게시글 수 체크
	public int boardMyCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(bno) from P1_IT_QNA where writer = ?";
		int count = 0;

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}

	// 게시판 페이징 기법 적용
	public List<BoardVO> boardSelect(Criteria cri, String query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;

		if (query != "") {
			sql = "select * from (" + "select /*+ index_desc(p1_it_qna p1_BOARD_QNA_PK) */ rownum rn, n.* "
					+ "from p1_it_qna n " + "where (" + query + ") and rownum <= ? * ? " + "order by bno desc)"
					+ "where rn > (? -1) * ?";
		} else {
			sql = "select * from (" + "select /*+ index_desc(p1_it_qna p1_BOARD_QNA_PK) */ rownum rn, n.* "
					+ "from p1_it_qna n " + "where rownum <= ? * ? " + "order by bno desc)" + "where rn > (? -1) * ?";
		}
		List<BoardVO> list = new ArrayList<>();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageNum());
			pstmt.setInt(2, cri.getAmount());
			pstmt.setInt(3, cri.getPageNum());
			pstmt.setInt(4, cri.getAmount());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO bno = new BoardVO();
				bno.setBno(rs.getInt("bno"));
				bno.setTitle(rs.getNString("title"));
				bno.setContent(rs.getNString("content"));
				bno.setWriter(rs.getNString("writer"));
				bno.setWdate(rs.getNString("wdate"));
				bno.setViewcount(rs.getInt("viewcount"));
				bno.setAnswer(rs.getNString("answer"));
				bno.setAnswercount(rs.getInt("answercount"));
				bno.setReplycount(rs.getInt("replycount"));

				list.add(bno);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}

	// 게시글 뷰
	public BoardVO boardSelectbyBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from P1_IT_QNA where bno = ?";
		BoardVO bvo = new BoardVO();

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bvo.setBno(rs.getInt(1));
				bvo.setTitle(rs.getNString(2));
				bvo.setContent(rs.getNString(3));
				bvo.setWriter(rs.getNString(4));
				bvo.setWdate(rs.getNString(5));
				bvo.setViewcount(rs.getInt(6));
				bvo.setAnswer(rs.getNString(7));
				bvo.setAnswercount(rs.getInt(8));
				bvo.setReplycount(rs.getInt(9));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return bvo;
	}

	// 게시글 조회수 카운
	public void boardCount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update P1_IT_QNA set viewcount = viewcount + 1 where bno = ?";

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

	// 총 게시글 체크
	public int boardAllCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bno from P1_IT_QNA";
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

	// 게시글 삭제
	public void boardDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete P1_IT_QNA where bno = ? ";

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

	// 게시글 수정
	public void boardModify(BoardVO bvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update P1_IT_QNA set title = ?, content = ? where bno = ?";

		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, bvo.getTitle());
			pstmt.setNString(2, bvo.getContent());
			pstmt.setInt(3, bvo.getBno());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
	}
}
//	public void methodName(Type value) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//				
//		String sql = "";
//
//		try {
//			conn = dbm.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.executeUpdate();
//			rs = pstmt.executeQuery();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			dbm.close(conn, pstmt);
//			dbm.close(conn, pstmt, rs);
//		}
//	}

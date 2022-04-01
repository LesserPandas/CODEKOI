<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ include file="/sub_title.jsp" %>
<!doctype html>
<link href="css/boardWrite.css" rel="stylesheet">

<section class="board_area">
	<article>
		<form name="boardWrite" method="post" action="board/boardPhotoWrite.jsp">

			<table>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="writer"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content" class="withPhoto"></textarea></td>
					</tr>
					<tr>
						<td>파일첨부</td>
						<td><input type="file"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn btn-blue">제출하기</button>
							<button type="reset" class="btn btn-red">다시쓰기</button>
							<button type="button" class="btn" onclick="location.href='board.do'">뒤로가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<form></form>

	</article>
	<aside></aside>
</section>
<%@ include file="/footer.jsp"%>
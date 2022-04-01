<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ include file="/sub_title.jsp" %>
<!doctype html>
<link href="css/boardWrite.css" rel="stylesheet">

<section class="board_area">
	<article>
		<form name="boardWrite" enctype="multipart/form-data" method="post" action="newsWrite.do">

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
						<td><input type="file" name="imgurl" id="isFile">></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn btn-blue">제출하기</button>
							<button type="reset" class="btn btn-red">다시쓰기</button>
							<button type="button" class="btn" onclick="location.href='news.do'">뒤로가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<form></form>

	</article>
	<aside></aside>
</section>

<script>
	function check() {
		if (portfolio.title.value == "") {
			alert("제목을 입력하세요");
			portfolio.title.focus();
			return false;
		}
		if (portfolio.content.value == "") {
			alert("내용을 입력하세요");
			portfolio.content.focus();
			return false;
		}

/* 		if (portfolio.imgurl.value == "") {
			alert("파일을 첨부하세요");
			portfolio.imgurl.focus();
			return false;
		} */

		var imgFile = portfolio.imgurl.value;
		var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
		var maxSize = 20 * 1024 * 1024;
		var fileSize;

		if (imgFile != "" && imgFile != null) {
			fileSize = document.getElementById("isFile").files[0].size;

			if (!imgFile.match(fileForm)) {
				alert("이미지 파일만 업로드 가능합니다.");
				return false;
			} else if (fileSize > maxSize) {
				alert("파일 사이즈는 5MB까지 가능");
				return false;
			}
		}
		return true;
	}
</script>

<%@ include file="/footer.jsp"%>
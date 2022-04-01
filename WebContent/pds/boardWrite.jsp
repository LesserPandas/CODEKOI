<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ include file="/sub_title.jsp"%>
<!doctype html>
<link href="css/boardWrite.css" rel="stylesheet">

<section class="board_area">
	<article>
		<form name="pdsWrite" method="post" enctype="multipart/form-data"
			action="pdsWrite.do" onSubmit="">

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
						<td><input type="text" name="writer" value="${userid}"
							readonly></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content"></textarea></td>
					</tr>
					<tr>
						<td>파일</td>
						<td><input type="file" name="filename" id="isFile"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn btn-blue">제출하기</button>
							<button type="reset" class="btn btn-red">다시쓰기</button>
							<button type="button" class="btn"
								onclick="location.href='pds.do'">뒤로가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

	</article>
	<aside></aside>
</section>

<script>
	function check() {
		if (pdsWrite.title.value == "") {
			alert("제목을 입력하세요");
			portfolio.title.focus();
			return false;
		}
		if (pdsWrite.content.value == "") {
			alert("내용을 입력하세요");
			portfolio.content.focus();
			return false;
		}

		if (pdsWrite.filename.value == "") {
			alert("파일을 첨부하세요");
			portfolio.filename.focus();
			return false;
		}

		var fileType = pdsWrite.filename.value;
		var fileForm = /(.*?)\.(md|bash|exe)$/;
		var maxSize = 50 * 1024 * 1024;
		var fileSize;

		if (fileType != "" && fileType != null) {
			fileSize = document.getElementById("isFile").files[0].size;

			if (fileType.match(fileForm)) {
				alert("허용된 파일타입이 아닙니다.");
				return false;
			} else if (fileSize > maxSize) {
				alert("파일 사이즈는 50MB까지 가능");
				return false;
			}
		}
		return true;
	}
</script>

<%@ include file="/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/board.css " rel="stylesheet">
<link href="css/reply.css" rel="stylesheet">
</head>
<body>
	<!-- SUB TITLE Start -->
	<div class="title">
		<h1>IT</h1>
		<h2 class="subtitle">QnA</h2>
		<div id="search_bar">
			<form id="search_form" method="get" action="board.do">
				<div id="sub_search_box" class="bg-8">
					<div id="search_text" class="bg-8">
						<select name="sel" class="select_common">
							<option value="title" selected>제목</option>
							<option value="content">내용</option>
						</select> <input type="text" name="word" placeholder="Search">
					</div>
					<div id="sub_search_button">
						<button type="submit" style="border: none">
							<i class="fas fa-search" style="font-size: 18px"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
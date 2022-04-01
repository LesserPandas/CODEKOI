<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODEKOI - 관리자모드</title>
<!-- CSS -->
<link href="css/admin/index.css" rel="stylesheet">
</head>
<body>
	<header id="header">
		<nav class="navbar">
			<div class="title">
				<h2>CODEKOI</h2>
				<h4>- 관리자 모드</h4>
			</div>
			<ul id="menu">
				<li>유저 관리</li>
				<li>게시글 관리</li>
				<li>단어 추가</li>
				<li>이벤트 로그</li>
				<li>설정</li>
				<li><a href="logout.do">로그아웃</a></li>
			</ul>
		</nav>
	</header>

	<section>
		<table id="user-table">
			<colgroup>
				<col style="width: 5%">
				<col style="width: 10%">
				<col style="width: 12%">
				<col style="width: 13%">
				<col style="width: 20%">
				<col style="width: 14%">
				<col style="width: 14%">
				<col style="width: 12%">
			</colgroup>
			<tbody>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>ID</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>작성한 게시글</th>
					<th>작성한 댓글</th>
					<th>설정</th>
				</tr>
				<c:set var="num" value="1" />
				<c:forEach var="list" items="${userList}">
					<tr>
						<td>${num}</td>
						<td>${list.name}</td>
						<td>${list.id}</td>
						<td>${list.phone}</td>
						<td>${list.email}</td>
						<td>${list.post}</td>
						<td>${list.reply}</td>
						<td><button type="button" class="btn_common"
								onclick="oneMoreCheck(${list.bno})">삭제</button></td>
					</tr>
					<c:set var="num" value="${num + 1 }" />
				</c:forEach>
			</tbody>
		</table>
	</section>

	<footer id="footer">
		<h3>Editer : Chanho Kim</h3>
		<p>Copyright 2021. All reserved.</p>
	</footer>
</body>

<script>
	function oneMoreCheck(num) {
		if (!confirm("정말 삭제하시겠습니까?")) {
		} else {
			alert("삭제되었습니다.");
			location.href="userDel.adm?bno="+num;
		}
	}
</script>

</html>



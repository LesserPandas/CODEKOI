<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link href="css/sub.css" rel="stylesheet">

<div class="sub_container bg-img1">
	<div class="login_box">
		<div class="member_boxL">
			<h2>관리자 로그인</h2>
			<div class="login_form">
				<form id="login" method="post">
					<div class="fl_clear">
						<label for="id">아이디</label> <input name="id" id="id" type="text">
					</div>
					<div class="fl_clear">
						<label for="pw">비밀번호</label> <input name="pw" id="pw"
							type="password">
					</div>
					<div class="btn_login btn_Blue">
						<a href="javascript:fn_login();">Log In</a>
					</div>
					<div id="msg">${msg}</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	function fn_login() {
		var log = document.getElementById("login");
		log.action = "login.adm";
		log.method = "post";
		log.submit();
	}
</script>


<%@ include file="../footer.jsp"%>
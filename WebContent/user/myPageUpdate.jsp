<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<link href="css/sub.css" rel="stylesheet">

<div class="sub_container">
	<div class="con_title">
		<h1>내정보(개인회원)</h1>
		<p>HOME / 마이페이지 / 내정보(개인회원)</p>
	</div>
	<div id="signUp_box">
		<form name="member" id="member" method="post" onsubmit="return checkUser()"
			action="userUpdate.do">
			<input type="hidden" name="bno" value="${user.bno }">
			<table class="table_write02">
				<caption>
					회원 정보 <span class="must">&nbsp;* 필수입력</span>
				</caption>
				<colgroup>
					<col width="160px">
					<col width="auto">
				</colgroup>
				<tbody id="joinDataBody">
					<tr>
						<th><label for="name">이름<span class="must">&nbsp;*</span></label></th>
						<td><input type="text" name="name" id="name" class="w350"
							value="${user.name}" readonly></td>
					</tr>
					<tr>
						<th><label for="id">아이디<span class="must">&nbsp;*</span></label></th>
						<td><input type="text" name="id" id="id" class="w350"
							value="${user.id}" readonly> <span id="msg"
							style="color: #f00"></span></td>
					</tr>
					<tr>
						<th><label for="pw">비밀번호<span class="must">&nbsp;*</span></label></th>
						<td><input type="password" name="pw1" id="pw1" class="w350">
							<p class="guideTxt">
								<span id="pw_point" class="tc_point">영문(대문자 구분), 숫자,
									특수문자의 조합으로 9~13자로 작성해 주십시오.</span>
							</p></td>
					</tr>
					<tr>
						<th><label for="pw_confirm">비밀번호확인<span class="must">&nbsp;*</span></label></th>
						<td><input type="password" name="pw2" id="pw2" class="w350">
							<p class="guideTxt">
								<span class="tc_point">비밀번호 미입력시 기존비밀번호가 유지됩니다.</span>
							</p></td>
					</tr>

					<tr>
						<th><label for="phone">전화번호</label></th>
						<td><input type="text" name="phone" id="phone" class="w350"
							value="${user.phone}"></td>
					</tr>
					<tr>
						<th><label for="email">이메일</label></th>
						<td><input type="email" name="email" id="email" class="w350"
							value="${user.email}"></td>
					</tr>
			</table>
			<div class="m-auto btn-box">
				<button type="submit" class="btn_common" id="btn_ok">
					<b>확인</b>
				</button>
				<button type="button" class="btn_common"
					onclick="location.href='Main.do'">
					<b>취소</b>
				</button>
			</div>
		</form>
	</div>
</div>

<script>
	function checkUser() {		
		if (!$("#name").val()) {
			alert("이름 입력");
			return false;
		};

		if ($("#pw1").val() != $("#pw2").val()) {
			alert("패스워드확인");
			$("#pw2").focus();
			return false;
		}
		var regEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // ""붙이면 절대 안됨
		if (!regEmail.test($("#email").val())) {
			alert("이메일을 확인하세요");
			$("#email").focus();
			return false;
		}
		
		alert("정상적으로 수정되었습니다.");
		return true;
	}
</script>


<%@ include file="../footer.jsp"%>
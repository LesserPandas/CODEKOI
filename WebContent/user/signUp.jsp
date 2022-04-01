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
		<div id="agree_box">
			<div>
				<h2>안내사항</h2>
				<p>
					회원정보는 회원님의 명백한 동의 없이 공개 또는 제3자에게 제공되지 않습니다.<br> 본 사이트는 개인 학습 및
					코딩 실력 향상을 위한 목적으로 제작되었습니다. 따라서 개인정보 및 민감한 정보는<br> 사이트 가입 및
					게시글에는 작성하지 마시기 바랍니다.
				</p>
				<input type="checkbox" id="agree" name="agree" value="t"><span>&nbsp;동의합니다.</span>
			</div>
		</div>
		<form name="member" id="member" method="post" onsubmit="return checkForm()"
			action="signUp.do">
			<table class="table_write02"
				summary="회원가입을 위한 이름, 아이디, 비밀번호, 비밀번호확인, 소속, 유선전화번호, 휴대전화번호, 이메일, 주소, 본인확인질문, 본인확인답, 주활용사이트, 알림여부 정보 입력">
				<caption>
					회원가입 정보 <span class="must">&nbsp;* 필수입력</span>
				</caption>
				<colgroup>
					<col width="160px">
					<col width="auto">
				</colgroup>
				<tbody id="joinDataBody">
					<tr>
						<th><label for="name">이름<span class="must">&nbsp;*</span></label></th>
						<td><input type="text" name="name" id="name" class="w350">
						</td>
					</tr>
					<tr>
						<th><label for="id">아이디<span class="must">&nbsp;*</span></label></th>
						<td><input type="text" name="id" id="id" class="w350">
							<span id="msg" style="color: #f00"></span></td>
					</tr>
					<tr>
						<th><label for="pw">비밀번호<span class="must">&nbsp;*</span></label></th>
						<td><input type="password" name="pw1" id="pw1" class="w350">
							<p class="guideTxt">
								<span id="pw_point" class="tc_point">영문(대문자 구분), 숫자, 특수문자의 조합으로 9~13자로
									작성해 주십시오.</span>
							</p></td>
					</tr>
					<tr>
						<th><label for="pw_confirm">비밀번호확인<span class="must">&nbsp;*</span></label></th>
						<td><input type="password" name="pw2" id="pw2" class="w350">
							<p class="guideTxt">
								<span class="tc_point"><!-- 비밀번호 미입력시 기존비밀번호가 유지됩니다. --></span>
							</p></td>
					</tr>

					<tr>
						<th><label for="phone">전화번호</label></th>
						<td><input type="text" name="phone" id="phone" class="w350">
						</td>
					</tr>
					<tr>
						<th><label for="email">이메일</label></th>
						<td><input type="email" name="email" id="email" class="w350">
						</td>
					</tr>
			</table>
			<div class="m-auto btn-box">
				<button type="submit" id="btn_ok" class="btn_common"><b>확인</b></button>
				<button type="button" onclick="javascript:history.go(-1);" class="btn_common"><b>취소</b></button>
			</div>
		</form>
	</div>
</div>

<script>
	$("#id").blur(function() {
		$.ajax({
			type : "post",
			url : "checkUserID.do",
			data : {
				id : $("#id").val()
			},
			success : function(data) {
				console.log("ajax");
				if (data != 1) {
					if ($("#id").val() != "") {
						$("#msg").html("사용 가능한 아이디입니다.");
					}
				} else {
					if ($("#id").val() != "") {
						$("#msg").html("사용 불가능한 아이디입니다.");
						$("#id").val("");
						$("#id").focus();
					}
				}
			},
			error : function() {
				alert("통신에러");
			}
		})
	});

	function checkForm() {
		if ($("#agree").is(":checked") == false) {
			alert("약관동의 체크 하세요.");
			return false;
		}
		;
		if (!$("#name").val()) {
			alert("이름 입력");
			$("#name").focus();
			return false;
		}
		;
		if (!$("#id").val()) {
			alert("아이디 입력");
			$("#id").focus();
			return false;
		}
		;

		if (!$("#pw1").val() || !$("#pw2").val()) {
			alert("패스워드 필수입력");
			$("#pw1").focus();
			return false;
		}
		;

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

		alert("정상적으로 회원가입되셨습니다! ")
		return true;
	}
</script>


<%@ include file="../footer.jsp"%>
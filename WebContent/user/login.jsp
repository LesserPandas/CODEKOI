<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link href="css/sub.css" rel="stylesheet">

<div class="sub_container bg-img1">
	<div class="login_box">
		<div class="member_boxL">
			<h2>회원 로그인</h2>
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
				</form>
			</div>
			<div id="msg">${msg}</div> <a href="signUp.do" id="join_btn">Don't
				have an account?</a>
		</div>
		<div class="social_login">
			<div id="loginBtn"></div>
			<div id="kakao" class="socialBtn">Sign with KAKAO</div>
			<div id="line" class="socialBtn">Sign with LINE</div>
			<div id="apple" class="socialBtn">Sign with APPLE</div>
		</div>
	</div>
</div>
<script>
	function fn_login() {
		var log = document.getElementById("login");
		log.action = "login.do";
		log.method = "post";
		log.submit();
	}
</script>

<script>
	function init() {
		gapi.load('auth2',
			function() {
				window.gauth = gapi.auth2
					.init({
						client_id : ""
					});
				gauth.then(function() {
					console.log("구글 로그인 성공");
				}, function() {
					console.log("구글 로그인 실패");
				})
				attachHandler(document.getElementById('loginBtn'));
			});
		gapi.signin2.render('loginBtn', {
			scope : 'profile email',
			width : 300,
			height : 50,
			longtitle : true,
			theme : 'light'
		})
	}

	function attachHandler(element) {
		gauth.attachClickHandler(element, {}, 
			function(googleUser) {
			var id_token = googleUser.getAuthResponse().id_token;
			$.ajax({
				type: 'post',
				url: 'socialLogin.do',
				data: {
					'id_token':  id_token
				},
				success: function(data){
					alert(data + "님 반갑습니다.");
					window.location.href = 'Main.do';
					
				},
				error: function(error){
					console.log("error")
				}
			})
		}, function(error) {
			console.log(JSON.stringify(error, undefined, 2));
		})
	}
</script>

<script src="https://apis.google.com/js/platform.js?onload=init" async
	defer></script>

<%@ include file="../footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link href="css/sub.css" rel="stylesheet">

<div class="sub_container">
	<div class="member_boxXL">
		<h2>메일 인증</h2>
		<div class="login_form">
			<form id="login" method="post">
				<div class="input_line">
					<label for="email">이메일</label> <input name="email" id="email"
						type="text" placeholder="이메일주소 입력"> <a class="btn_common"
						href="javascript:void(0)" id="btn_email">이메일전송</a>
				</div>
				<div class="input_line">
					<label for="certinumber">인증번호</label> <input name="certinumber"
						id="certinumber" type="text"> <a class="btn_common"
						href="javascript:void(0)" id="btn_ok">확인</a>
				</div>
			</form>
		</div>
	</div>

</div>
<!-- end container -->

<script>
	$(function() {
		$("#btn_email").on("click", function(e) {
			var email = $("#email").val();
			if (email == "") {
				alert("이메일을 입력하세요.");
				$("#email").focus();
			} else {
				$.ajax({
					type : 'post',
					dataType : 'json',
					data : {
						"email" : $("#email").val()
					},
					url : 'sendEmail.do',
					success : function(data) {
						alert(data.msg);
					},
					error : function() {
						alert("email error");
					}

				}) // ajax
			}
		}); // btn_email function

		$("#btn_ok").on("click", function() {
			var certinumber = $("#certinumber").val();

			//ID가 certinumber 값을 변수에 저장
			if (certinumber == "") { //공백과 같으면
				alert("인증번호를 입력하세요"); //메시지 출력
				$("#certinumber").focus(); //커서를 #certinumber인 위치로 이동 
			} else {
				$.ajax({
					type : "post",
					url : "myPage.do",
					data : {
						"certinumber" : certinumber
					},
					dataType : "json", // 서버에서 처리된 결과를 JSON 형식으로 받음.
					async : false,
					success : function(data) {
						alert(data.msg);
						if (data.check == "ok") {
							// loacation.href = "membeupdate.do;"
							$(location).attr("href", "userUpdate.do");
							//memberupdate.do 를 href속성에 넣고 location을 실행한다.
						} else {
							$(location).attr("href", "mypage.do");
						}
					},
					error : function() {
						alert("통신에러");
					}
				}) // ajax
			} // else
		}) // btn_ok function
	})
</script>

<%@ include file="../footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_title.jsp"%>
<link href="css/boardView.css" rel="stylesheet">

<!doctype html>
<section class="board_area">
	<article>
		<div>
			<c:set var="view" value="${boardView}" />
			<div id="viewTitle">
				<h1>${view.title }</h1>
				<p class="writer">${view.writer}</p>
				<fmt:parseDate var="DateString" value="${view.wdate}"
					pattern="yyyy-MM-dd HH:mm" />

				<p class="datetype">
					<fmt:formatDate value="${DateString}" pattern="yyyy/MM/dd hh:mm" />
				</p>
			</div>
			<div id="viewContent">
				<p>${view.content}</p>
			</div>
			<div id="table_footer">
				<div id="board_ctrl">
					<a href="#" aria-label="Previous"> <span aria-hidden="true"><i
							class="fas fa-chevron-left"></i></span>
					</a>
				</div>
				<div id="write_button">

					<a href="#" aria-label="Next" style="float: right"> <span
						aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</div>

				<!-- pagination Start -->
				<div id="pagination">
					<c:choose>
						<c:when test="${userid == view.writer }">

							<button type="button" class="btn_common block mg-auto"
								style="float: left;" onclick="location.href='board.do'">목록</button>
							<button type="button" class="btn_common"
								onclick="location.href='boardModify.do?bno=${view.bno}'">수정</button>
							<button type="button" class="btn_common"
								onclick="location.href='boardDelete.do?bno=${view.bno}'">삭제</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn_common block mg-auto noFloat"
								onclick="location.href='board.do'">목록</button>
						</c:otherwise>
					</c:choose>

				</div>
				<!-- pagination End -->
			</div>
			<div id="viewReply">
				<div class="reply_area">
					<h2>댓글</h2>
					<c:choose>
						<c:when test="${not empty userid}">
							<form method="post" action="">
								<div class="reply_form">
									<input type="hidden" id="pbno" value="${view.bno}">
									<div class="reply_profile">
										<p>
											<input id="writer" type="hidden" value="${userid}" readonly>${userid}</p>
									</div>
									<div class="reply_content">
										<textarea id="input_reply"></textarea>
									</div>
									<div class="reply_button">
										<input id="submit" class="reply_btn" value="등록" type="button"
											style="width: 145px">
									</div>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<div class="alert">로그인을 한 후 댓글을 입력하실 수 있습니다.</div>
						</c:otherwise>
					</c:choose>
					<ul id="reply_list">
						<c:forEach var="rlist" items="${rpList}">
							<li class="reply_box">
								<div>
									<div class="reply_profile">
										<p>${rlist.writer}</p>
									</div>
									<div class="reply_content">
										<fmt:parseDate var="replyDate" value="${rlist.wdate}"
											pattern="yyyy-MM-dd HH:mm" />
										<p>
											<fmt:formatDate value="${replyDate}"
												pattern="yyyy/MM/dd HH:mm" />
										</p>
										<p>${rlist.content}</p>
									</div>
									<div class="reply_button">
										<button id="reply_del" class="reply_btn"
											onclick="replyDelete(${rlist.bno})" style="width: 145px">삭제</button>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</article>
	<aside></aside>
</section>

<script>
function viewReply(data) {
	$("#input_reply").val("");
	var output = "";
	$.each(data, function(index, item) {
		var writer = item.writer;
		var content = item.content;
		var wdate = item.wdate.substring(0, 10);
		var bno = item.bno;
		console.log(bno);
		output += "<li class='reply_box'>";
		output += "<div><div class='reply_profile'>";
		output += "<p>" + writer + "</p></div>";
		output += "<div class='reply_content'>";
		output += "<p>" + wdate + "</p><p>" + content + "</p></div>";
		output += "<div class='reply_button'>";
		output += "<button id='reply_del' onclick='replyDelete(" + bno + ")' class='reply_btn' style='width: 145px'>삭제</button>";
		output += "</div></div></li>";
	});

	$("#reply_list").html(output);
}

	$("#submit").on("click", function() {
		var pbno = $("#pbno").val();
		var writer = $(".reply_form #writer").val();
		var content = $(".reply_form #input_reply").val();
		$.ajax({
			type : "post",
			url : "reply.do",
			dataType : "json",
			data : {
				pbno : pbno,
				writer : writer,
				content : content,
			},
			success : function(data) {
				viewReply(data);
				
			},
			error : function(xhr, status, error) {
				alert("통신에러");
			}
		})
	})
	
	function replyDelete(data){
		var pbno = $("#pbno").val();
		var bno = data;
		console.log("phno: "+ pbno);
		console.log("bno: " + bno);
		$.ajax({
			type : "get",
			url : "replyDel.do",
			dataType:"json",
			data : {
				pbno : pbno,
				bno : bno,
			},
			success : function(data) {
				viewReply(data);
				
			},
			error : function(xhr, status, error) {
				alert("통신에러");
			}
		})
	}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_title.jsp"%>
<!doctype html>
<section class="board_area">
	<article>
		<div>
			<c:set var="view" value="${boardView}" /> 
			<div id="viewTitle">
			<h1>${view.title }</h1>
			<p> ${view.writer}</p>
			<p> ${view.wdate}</p>
			</div>
			<div id="viewContent">
			<p> ${view.content}</p>
			</div>
			<div id="viewReply"></div>
		</div>


		<div id="table_footer">
			<div id="board_ctrl"></div>
			<div id="write_button">
				<button type="button" onclick="location.href='boardWrite.do'">글쓰기</button>
			</div>

			<!-- pagination Start -->
			<div id="pagination">
				<ul>
					<li><a href="#" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<li><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</div>
			<!-- pagination End -->
		</div>
	</article>
	<aside></aside>
</section>
<%@ include file="/footer.jsp"%>
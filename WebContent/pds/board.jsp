<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ include file="/sub_title.jsp"%>

<!doctype html>
<section class="board_area">
	<article>
		<div id="allCountPage">총 게시글 수 : ${count}</div>
		<table>
			<thead></thead>
			<tbody>
				<tr>
					<th>NO</th>
					<th>제목</th>
					<th>작성자</th>
					<th>파일</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:set var="pageCnt"
					value="${count-((pageMaker.cri.pageNum -1) * 10)}" />
				<c:forEach var="pdslist" items="${pdslist}">
					<tr>
						<td>${pageCnt}</td>
						<td><a
							href="pdsView.do?bno=${pdslist.bno}&pageNum=${pageMaker.cri.pageNum }&amount=${pageMaker.cri.amount }">${pdslist.title }
						</a></td>
						<td>${pdslist.writer }</td>
						<td><a href="downPds.do?filename=${pdslist.filename }">${pdslist.filename }</a></td>
						<fmt:parseDate var="dateString" value="${pdslist.wdate }"
							pattern="yyyy-MM-dd" />
						<td><fmt:formatDate value="${dateString}"
								pattern="yyyy-MM-dd" /></td>
						<td>${pdslist.viewcount }</td>
					</tr>
					<c:set var="pageCnt" value="${pageCnt-1}" />
				</c:forEach>
			</tbody>
		</table>
		<div id="table_footer">
			<div id="board_ctrl">

				<select>
					<option selected>작성일순</option>
					<option>추천순</option>
					<option>조회수순</option>
				</select>
			</div>
			<div id="write_button">
				<c:if test="${not empty userid}">
					<button type="button" onclick="location.href='pdsWrite.do'">글쓰기</button>
				</c:if>
			</div>

			<!-- pagination Start -->
			<div id="pagination">
				<ul>
					<c:if test="${pageMaker.prev}">
						<li><a href="${pageMaker.startPage-1}" aria-label="Previous">
								<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
						</a></li>
					</c:if>
					<c:forEach var="num" begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}">
						<li><a href="${num}"
							class="${pageMaker.cri.pageNum == num? 'active' : ''}">${num}</a></li>
					</c:forEach>
					<c:if test="${pageMaker.next}">
						<li><a href="${pageMaker.startPage+1}" aria-label="Next">
								<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
						</a></li>
					</c:if>
				</ul>
			</div>
			<!-- pagination End -->
		</div>
	</article>
	<aside></aside>
</section>

<form id="actionForm" action="board.do" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
	<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
</form>

<script>
	var actionForm = $("#actionForm");
	$("#pagination > ul > li > a").on("click", function(e) {
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	})
</script>

<%@ include file="/footer.jsp"%>
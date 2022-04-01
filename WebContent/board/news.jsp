<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ include file="/sub_title.jsp"%>
<!doctype html>
<section class="board_area">
	<article>
		<ul>
		<c:forEach var="nlist" items="${newsList}" end="4">
			<li class="photo_board_list">
				<div>
					<div class="date">
						<fmt:parseDate var="dateString" value="${nlist.wdate}" pattern="yyyy-MM-dd" />
						<p><fmt:formatDate value="${dateString}" pattern="dd" /></p>
						<p><fmt:formatDate value="${dateString}" pattern="yyyy.MM" /></p>
					</div>
					<img src=" ${pageContext.request.contextPath }/upload/${nlist.imgurl}" alt="">
					<div class="news_content">
						<p>조회수:  ${nlist.viewcount} </p>
						<h1>${nlist.title}</h1>
						<p>${nlist.content}</p>
					</div>
				</div>
			</li>
		</c:forEach>
		</ul>
		<div id="table_footer">
			<div id="board_ctrl">

				<select>
					<option>추천순</option>
					<option>작성일순</option>
					<option>조회수순</option>
				</select>
			</div>
			<div id="write_button">
				<button type="button" onclick="location.href='newsWrite.do'">글쓰기</button>
			</div>

			<!-- pagination Start -->
			<div id="pagination">
				<ul>
					<li><a href="#" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<li class=""><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>

					<li><a href="#">5</a></li>
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
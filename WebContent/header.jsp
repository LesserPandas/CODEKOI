<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODEKOI</title>
<link href="css/common.css" rel="stylesheet">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/dc5568f70e.js"
	crossorigin="anonymous"></script>
<script src="js/common.js"></script>
</head>
<body>
	<!-- LEFT NAVBAR Start -->
	<div id="navi" class="bg-n4">
		<div id="logo" style="color: #f2f2f2 !important;">
			<h4>
				<a href="/CODEKOI/" class="fc-n5">コドコイ</a>
			</h4>
		</div>   
		<nav id="menu" class="bg-n4">
			<ul style="min-height: 545px">
				<li value="1"><a onclick="movePage(1);"> <i
						class="fas fa-search"></i> &nbsp;&nbsp;&nbsp;サーチ
				</a></li>
				<li value="2"><a onclick="movePage(2);"> <i
						class="fas fa-laptop"></i> &nbsp;&nbsp;&nbsp;IT
				</a></li>
				<li value="3"><a onclick="movePage(3);"> <i
						class="fas fa-torii-gate"></i> &nbsp;&nbsp;&nbsp;日本語
				</a></li>
				<li value="4"><a onclick="movePage(4);"> <i
						class="fas fa-users"></i> &nbsp;&nbsp;&nbsp;コミュニティ
				</a></li>
				<li value="5"><a onclick="movePage(5);"> <i
						class="fas fa-link"></i> &nbsp;&nbsp;&nbsp;リンク
				</a></li>
			</ul>
		</nav>
		<div id="loginJoin">
			<c:choose>
				<c:when test="${userid == null}">

					<button id="login_btn" type="button"
						onclick="location.href='login.do'">
						<i class="fas fa-user-circle"></i> <span>&nbsp;Login</span>
					</button>
					<button id="join_btn" type="button"
						onclick="location.href='signUp.do'">Don't have an
						account?</button>

				</c:when>
				<c:when test="${userid != null}">
					<div class="mgl10">
						<button id="mypage_btn" type="button" class="btn-2rd"
							onclick="location.href='myPage.do'">
							<span>&nbsp;MyPage</span>
						</button>
						<button id="logout_btn" type="button" class="btn-2rd"
							onclick="location.href='logout.do'">
							<span>&nbsp;Logout</span>
						</button>
					</div>
				</c:when>
			</c:choose>
		</div>
	</div>
	<!-- LEFT NAVBAR End -->

	<!-- SUB TITLE End -->
	<!--
	**** CONTENT AREA ****
	<section class="board_area">
		<article></article>
		<aside></aside>
	</section>
	-->
<%@ include file="/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<!-- CSS -->
<link href="slick/slick.css" rel="stylesheet" type="text/css">
<link href="slick/slick-theme.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet">
<!-- JS -->
<script type="text/javascript" src="slick/slick.min.js"></script>
<script src="js/main.js" type="text/javascript"></script>
<script src="js/slick.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<div id="video_area">
			<video id="background_video" src="videos/commu.mp4" preload="auto"
				autoplay="autoplay" loop="loop" muted="muted"></video>
		</div>
		<div id="title">
			<h1 class="fc-n5">コドコイ</h1>
			<h2 class="fc-n5">私たちもできる！</h2>
			<div id="search_bar">
				<form id="search_form" method="get" action="">
					<div id="search_box" class="bg-n5">
						<div id="search_text" class="bg-n4">
							<input type="text">
						</div>
						<div id="search_button" class="bg-n5">
							<i class="fas fa-search fa-2x fc-n4"></i>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="container" id="IT">
		<a name="IT"></a>
		<div id="empty_box"></div>
		<article id="content" id="IT">
			<header>
				<div id="subtitle">
					<h1 class="bg-1">IT</h1>
				</div>
			</header>
			<section>
				<div id="QnA_box" class="">
					<div class="box_title">
						<h2>
							<a href="board.do">QnA</a>
						</h2>
						<a href="board.do"> <i class="fas fa-plus"></i>
						</a>
					</div>
					<ul>
						<c:set var="num" value="11" />
						<c:if test="${not empty userid}">
							<c:choose>
								<c:when test="${myCount < 5}">
									<c:set var="num" value="${num - myCount - 2}" />
								</c:when>
								<c:otherwise>
									<c:set var="num" value="${num - 4}" />
									<c:set var="myCount" value="4" />
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:forEach var="qnaList" items="${qnaList}" end="${num}">
							<fmt:parseDate var="qnaDate" value="${qnaList.wdate}"
								pattern="yyyy-MM-dd" />
							<li>
								<ul>
									<li><fmt:formatDate value="${qnaDate}"
											pattern="yyyy/MM/dd" /></li>
									<li class="articleTitle"><a
										href="boardView.do?bno=${qnaList.bno }"><p>
												${qnaList.title}</p></a></li>
									<c:if test="${qnaList.answer == 'NO'}">
										<li><div class="answer bg-red">${qnaList.answer}</div></li>
									</c:if>
									<c:if test="${qnaList.answer == 'YES'}">
										<li><div class="answer bg-green">${qnaList.answer}</div></li>
									</c:if>
								</ul>
							</li>
						</c:forEach>

					</ul>
					<c:if test="${not empty userid && myCount != 0}">
						<div class="divide_line">
							<div class="box_title">
								<a href="board.do?userid=${userid}">
									<h2>My</h2>
								</a> <a href="board.do?userid=${userid}"> <i class="fas fa-plus"></i>
								</a>
							</div>
							<ul>
								<c:forEach var="myList" items="${myList}" end="${myCount}">
									<fmt:parseDate var="qnaDate" value="${myList.wdate}"
										pattern="yyyy-MM-dd" />
									<li>
										<ul>
											<li><fmt:formatDate value="${qnaDate}"
													pattern="yyyy/MM/dd" /></li>
											<li class="articleTitle"><a
												href="boardView.do?bno=${myList.bno }"><p>
														${myList.title}</p></a></li>
											<c:if test="${myList.answer == 'NO'}">
												<li><div class="answer bg-red">${myList.answer}</div></li>
											</c:if>
											<c:if test="${myList.answer == 'YES'}">
												<li><div class="answer bg-green">${myList.answer}</div></li>
											</c:if>
										</ul>
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<c:choose>
						<c:when test="${not empty userid}">
							<button class="questionBtn bg-1"
								onclick="location.href='boardWrite.do'">질문 등록</button>
						</c:when>
						<c:otherwise>

							<button class="questionBtn bg-1"
								onclick="location.href='login.do'">질문 등록</button>
						</c:otherwise>
					</c:choose>
				</div>
				<div
					style="width: 585px; height: 633px; margin-left: 30px; float: left">
					<div id="it_news_box" class="">
						<div class="box_title">
							<a href="news.do"><h2>News</h2></a> <a href="news.do"><p>
									<i class="fas fa-plus"></i>
								</p></a>
						</div>
						<nav class="news_menu">
							<ul>
								<li>전체</li>
								<li>최신기술</li>
								<li>전자기기</li>
								<li>보안이슈</li>
							</ul>
						</nav>
						<div class="news_view">
							<div class="news_view_info">
								<div style="float: left">
									<h2>메타버스의 의미, 제대로 알고 있나요?</h2>
									<p>최근 메타버스란 용어가 핫이슈이다. 그러나 그 정의를 명확히 알고 있는</p>
									<p>사람은 극히 드물다. 과연 메타버스는 무엇이길래 이렇게 많은 곳에서...</p>
								</div>
								<div class="arrow-right">
									<i class="fas fa-arrow-right fa-2x"></i>
								</div>
							</div>
						</div>
						<ul>
							<li>
								<ul>
									<li>[전자기기]</li>
									<li class="articleTitle">iPhone13, iPad mini 6 출시!</li>
									<li>21/09/30</li>
								</ul>
							</li>
							<li>
								<ul>
									<li>[최신기술]</li>
									<li class="articleTitle">4차 산업혁명, 그 다음은?</li>
									<li>21/09/30</li>
								</ul>
							</li>
							<li>
								<ul>
									<li>[보안이슈]</li>
									<li class="articleTitle">Reversing기술을 악용한 중고폰 사진 유출피해 급증</li>
									<li>21/09/30</li>
								</ul>
							</li>
						</ul>
					</div>
					<div id="codeReview_box" class="">
						<div class="box_title">
							<h2>Code Review</h2>
							<p>
								<i class="fas fa-plus"></i>
							</p>
						</div>
						<div class="autoplay">
							<div class="codeLang" style="color: #8B0000">JAVA</div>
							<div class="codeLang" style="color: #006400">Spring</div>
							<div class="codeLang" style="color: #4B0082">JavaScript</div>
							<div class="codeLang" style="color: #B8860B">HTML5+CSS3</div>
							<div class="codeLang" style="color: #1F7FAF">PYTHON</div>
							<div class="codeLang" style="color: #666">DB</div>
						</div>
					</div>
				</div>
			</section>
		</article>
	</div>
	<div class="container  bg-n5" id="Japanese">
		<div id="empty_box"></div>
		<article id="content">
			<header>
				<div id="subtitle">
					<h1 class="bg-1">日本語</h1>
				</div>
			</header>
			<section>
				<div id="word_box" class="">
					<div class="box_title">
						<h2>今日の単語</h2>
						<p>
							<i class="fas fa-plus"></i>
						</p>
					</div>
					<ul>
						<c:forEach var="word" items="${wordList}">
							<li>
								<div id="wd_side1">
									<div id="wd_level" value="${word.jlpt}">N${word.jlpt}</div>
								</div>
								<div id="wd_content">
									<p id="wd0">${word.yomigana}</p>
									<p id="wd1">${word.jpword}</p>
									<div id="wd2">
										<div id="wd_type">${word.pumsa}</div>
										<div id="wd_mean">${word.krword}</div>
									</div>
								</div> <c:if test="${not empty userid }">
									<div id="wd_side2">
										<div id="wd_forget">
											<i class="fas fa-plus-square"></i>
										</div>
										<div id="wd_remember">
											<i class="fas fa-minus-square"></i>
										</div>
									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div id="jp_news_box" class="">
					<div class="box_title">
						<h2>今日のニュース</h2>
						<p>
							<i class="fas fa-plus"></i>
						</p>
					</div>
					<nav class="news_menu">
						<ul>
							<li>전체</li>
							<li>경제</li>
							<li>사회문화</li>
							<li>생활</li>
						</ul>
					</nav>
					<div class="news_group">
						<div class="jp_news_view bg-news01">
							<div class="news_view_info">
								<div style="float: left">
									<p>[10月4日 12時00分]</p>
									<h2>10月から食べ物など生活に必要な物の値段が上がる</h2>
									<p>10月から、食べ物や生活で必要な物の値段が上がります。。</p>
									<p>材料の値段が高くなっていることなどが理由です。。。</p>
									<button class="">More</button>
								</div>
							</div>
						</div>
						<div class="jp_news_view  bg-news02">
							<div class="news_view_info">
								<div style="float: left">
									<p>[10月4日 12時00分]</p>
									<h2>大リーグの大谷選手 最後の試合で46本目のホームランを打つ</h2>
									<p>10月から、食べ物や生活で必要な物の値段が上がります。。</p>
									<p>材料の値段が高くなっていることなどが理由です。。。</p>
									<button class="">More</button>
								</div>
							</div>
						</div>
						<div class="jp_news_view bg-news03">
							<div class="news_view_info">
								<div style="float: left">
									<p>[10月4日 12時00分]</p>
									<h2>北海道で魚やウニがたくさん死ぬ</h2>
									<p>10月から、食べ物や生活で必要な物の値段が上がります。。</p>
									<p>材料の値段が高くなっていることなどが理由です。。。</p>
									<button class="">More</button>
								</div>
							</div>
						</div>
						<div class="jp_news_view bg-news04">
							<div class="news_view_info">
								<div style="float: left">
									<p>[10月4日 12時00分]</p>
									<h2>岸田文雄さんが自民党の新しい総裁になる</h2>
									<p>10月から、食べ物や生活で必要な物の値段が上がります。。</p>
									<p>材料の値段が高くなっていることなどが理由です。。。</p>
									<button class="">More</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</article>
	</div>
	<div class="container bg-n4" id="Community">
		<div id="empty_box"></div>
		<article id="content">
			<header>
				<div id="subtitle">
					<h1 class="bg-1">コミュニティー</h1>
				</div>
			</header>
			<section>
				<div id="commu_grp1">
					<div id="commu_grp21" class="bg-n6" style="padding: 20px">

						<div class="box_title" style="margin-bottom:40px">
							<h2 class="fc-n5">공지사항</h2>
							<p class="fc-n5">
								<i class="fas fa-plus"></i>
							</p>
						</div>

						<div>
							<div class="fc-n6 fw700" style="margin-bottom:20px">21/10/20&nbsp;[공지] 사이트 이용안내</div>
							<div class="fc-n6 fw700" style="margin-bottom:20px">21/10/20&nbsp;[이벤트] 도전! 출석왕!</div>
							<div class="fc-n6 fw700" style="margin-bottom:20px">21/10/20&nbsp;[공지] 게시글 작성 유의사항</div>
							<div class="fc-n6 fw700" style="margin-bottom:20px">21/10/20&nbsp;[공지] 사이트 서버 점검 안내</div>
							<div class="fc-n6 fw700" style="margin-bottom:20px">21/10/20&nbsp;[공지] 사이트 ver.1 오픈</div>
							
						</div>

					</div>
					<div id="commu_grp22">
						<div class="commu_box_long bg-n5">
							<div class="box_title">
								<h2>
									<a href="pds.do">자료실</a>
								</h2>
								<p>
									<a href="pds.do"><i class="fas fa-plus"></i></a>
								</p>
							</div>
							<table id="file_table">
								<colgroup>
									<col width="10%">
									<col width="60%">
									<col width="15%">
									<col width="15%">
								</colgroup>
								<thead>
									<tr>
										<th>번호</th>
										<th class="">제목</th>
										<th>파일</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pdslist" items="${pdslist }" begin="0" end="5">
										<tr>
											<td>${pdslist.bno }</td>
											<td>${pdslist.title }</td>
											<td><a href="downPds.do?filename=${pdslist.filename }"><i
													class="fas fa-file"></i></a></td>
											<td>${pdslist.viewcount }</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
						<div class="commu_box bg-n4">
							<div class="box_title">
								<h2 class="fc-n5">스터디</h2>
								<p class="fc-n5">
									<i class="fas fa-plus"></i>
								</p>
							</div>
							
							<div class="fc-n6" style="margin-top:100px;　font-size:34px; text-align:center">준비중</div>
						</div>
						<div class="commu_box bg-n3">
							<div class="box_title">
								<h2 class="fc-n5">일본생활</h2>
								<p class="fc-n5">
									<i class="fas fa-plus"></i>
								</p>
							</div>
							
							<div class="fc-n6" style="margin-top:100px;　font-size:34px; text-align:center">준비중</div>
						</div>
					</div>
					<div id="commu_grp21">
						<div class="commu_box bg-4">
							<div class="box_title">
								<h2>기업구직</h2>
								<p>
									<i class="fas fa-plus"></i>
								</p>
							</div>
							
							<div style="margin-top:100px;　font-size:34px; text-align:center">준비중</div>
						</div>
						<div class="commu_box bg-2">
							<div class="box_title">
								<h2 class="fc-n5">IT학습</h2>
								<p class="fc-n5">
									<i class="fas fa-plus"></i>
								</p>
							</div>
							
							<div class="fc-n6" style="margin-top:100px;　font-size:34px; text-align:center">준비중</div>
						</div>
					</div>
				</div>
			</section>
		</article>
	</div>
	<div class="container" id="Link">
		<div id="empty_box"></div>
		<article id="content">
			<header>
				<div id="subtitle">
					<h1 class="bg-1">リンク</h1>
				</div>
			</header>
			<section>
				<dl class="link_box">
					<dt>FrontEnd</dt>
					<dd class="link_grp">
						<div id="link_card">
							<a href="https://www.w3schools.com"><img src="images/w3.png"
								alt="">
								<h2>W3Schools</h2></a>
							<p>HTML+CSS+JS까지 총정리</p>
						</div>
						<div id="link_card">
							<a href="https://developer.mozilla.org/ko/docs/Web/JavaScript"><img
								src="images/js.png" alt="">
								<h2>Mozilla.org</h2></a>
							<p>HTML+CSS+JS까지 총정리</p>
						</div>
					</dd>

					<dt>BackEnd</dt>
					<dd class="link_grp hidden">
						<div id="link_card">
							<a
								href="https://www.youtube.com/channel/UC1IsspG2U_SYK8tZoRsyvfg"><img
								src="images/java.png" alt="">
								<h2>자바의 정석 강의</h2></a>
							<p>남궁성 저자의 무료강의</p>
						</div>
						<div id="link_card">
							<a href=""><img src="images/apache.png" alt="">
								<h2>Apache.org</h2></a>
							<p>Apache 공식 사이트</p>
						</div>
						<div id="link_card">
							<a href=""><img src="images/www.png" alt="">
								<h2>생활코딩</h2></a>
							<p>무료 강의 사이트</p>
						</div>
						<div id="link_card">
							<a href="https://goddaehee.tistory.com/156"><img
								src="images/spring.png" alt="">
								<h2>갓대희의 작은 공간</h2></a>
							<p>Spring 이외에도 많은 정리글이</p>
							<p>있습니다.</p>
						</div>

					</dd>

					<dt>Database</dt>
					<dd class="link_grp hidden">
						<div id="link_card">
							<a href=""><img src="images/www.png" alt="">
								<h2>구루비</h2></a>
							<p>꿈꾸는 개발자, DBA 커뮤니티 구루비</p>
						</div>
						<div id="link_card">
							<a href=""><img src="images/oracledb.jpeg" alt="">
								<h2>Oracle</h2></a>
							<p>Oracle 공식 사이트</p>
						</div>

					</dd>

					<dt>Infomation</dt>
					<dd class="link_grp hidden">
						<div id="link_card">
							<a href=""><img src="images/github.png" alt="">
								<h2>Github</h2></a>
							<p>Github 공식 사이트</p>
						</div>
						<div id="link_card">
							<a href=""><img src="images/okky.png" alt="">
								<h2>Okky.kr</h2></a>
							<p>개발자 커뮤니티</p>
						</div>
					</dd>
				</dl>
			</section>
		</article>
	</div>
</body>
</html>
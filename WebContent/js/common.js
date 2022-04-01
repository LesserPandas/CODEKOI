window.page = 1;

// Main 외 페이지에서 Main으로 이동
function movePage(viewNum) {
		var url = window.document.URL;
		if (url.substring(0,37) == 'http://localhost:7979/CODEKOI/Main.do' ||
		url == 'http://localhost:7979/CODEKOI/' ){
			movePosition(viewNum);
		} else {
			window.location.replace('http://localhost:7979/CODEKOI/Main.do?pageNum='+viewNum);
		}
};

// 애니메이션 있는 위치 이동
function movePosition(viewNum){
		page = viewNum;
		var posTop = (page -1 ) * $(window).height();
		$html.animate({scrollTop : posTop});
}

// 애니메이션 없는 위치 이동
function movePositionNoAction(){
		var posTop = (page -1 ) * $(window).height();
		$html.animate({scrollTop : posTop}, 0);
}
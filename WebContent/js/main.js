// 첫로딩시 페이지 파라미터 추출
window.onload = function() {
 	page = getParam("pageNum");
	console.log("parameter page = " + page);
	if (page > 0) {
		movePositionNoAction();
	}
}

// 페이지 파라미터 추출 
function getParam(sname) {
	var params = location.search.substr(location.search.indexOf("?") + 1);
	var sval = "";
	params = params.split("&");
	for (var i = 0; i < params.length; i++) {
		temp = params[i].split("=");
		if ([temp[0]] == sname) { sval = temp[1]; }
	}
	if (sval == "") sval = 0;
	return sval;
}

// 스크롤 액션
window.addEventListener("wheel", function(e) {
	e.preventDefault();
}, { passive: false });

var $html = $("html");
var lastPage = $(".container").length;
lastPage = 5;
console.log("페이지수:  " + lastPage);

$(window).on("wheel", function(e) {
	if ($html.is(":animated")) return;
	if (e.originalEvent.deltaY > 0) {
		if (page == lastPage) return;
		page++;
	} else if (e.originalEvent.deltaY < 0) {
		if (page == 1) return;
		page--;
	};
	console.log("scroll전 page : " + page	);
	var posTop = (page - 1) * $(window).height();
	$html.animate({ scrollTop: posTop });
});



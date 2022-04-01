$(document).ready(function() {
		$('.autoplay').slick({
			slidesToShow : 3,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
		});
		$(".link_box > dt").click(function() { // 탭메뉴 클릭 이벤트
			$(".link_box > dd").addClass("hidden");
			$(this).next().removeClass("hidden");
		});
	});
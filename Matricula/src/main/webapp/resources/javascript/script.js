$(document).on('ready', main);

function main() {
	$("#Menu a").on('click', goTo);
	$(window).scroll(scrollMenu);
}

function goTo() {
	var section = $(this).attr("href");
	$("body,html").animate({
		scrollTop : $(section).offset().top - 180
	}, 800);

	return false;

}

function scrollMenu() {

	var sections = [ $("#start").offset().top, $("#services").offset().top,
			$("#information").offset().top, $("#questions").offset().top,
			$("#mainContact").offset().top, $("body").height() ];

	if ($(this).scrollTop() > sections[0] - 200
			&& $(this).scrollTop() < sections[1] - 200) {
		$("#Menu a").eq(2).addClass("selected");
	} else {
		$("#Menu a").eq(2).removeClass("selected");
	}
	if ($(this).scrollTop() > sections[1] - 200
			&& $(this).scrollTop() < sections[2] - 200) {
		$("#Menu a").eq(0).addClass("selected");
	} else {
		$("#Menu a").eq(0).removeClass("selected");
	}
	if ($(this).scrollTop() > sections[2] - 200
			&& $(this).scrollTop() < sections[3] - 200) {
		$("#Menu a").eq(1).addClass("selected");
	} else {
		$("#Menu a").eq(1).removeClass("selected");
	}
	if ($(this).scrollTop() > sections[3] - 200
			&& $(this).scrollTop() < sections[4] - 200) {
		$("#Menu a").eq(3).addClass("selected");
	} else {
		$("#Menu a").eq(3).removeClass("selected");
	}
	if ($(this).scrollTop() > sections[4] - 200) {
		$("#Menu a").eq(4).addClass("selected");
	} else {
		$("#Menu a").eq(4).removeClass("selected");
	}

}
$("#mobile-button").click(function() {
    $('.nav-item').toggleClass('m-mobile-hide');
});

$('.ui.dropdown').dropdown({
    on: "hover"
});

$('#toTopButton').click(function() {
    $(window).scrollTo(0, 1000);
});
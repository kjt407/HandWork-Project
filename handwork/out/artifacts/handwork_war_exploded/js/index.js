$(document).ready(function(){
$('.board-slider').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 1500,
    });
});
$('.slide').slick({
    dots: true,
    infinite: true,
    autoplay: true,
    autoplaySpeed: 3000,
    speed: 500,
    fade: true,
    cssEase: 'linear'
});

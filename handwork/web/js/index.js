// $(window).load(function(){
//     // 페이지 로드시 동작 실행 부분
//     initStars();
// });

$(document).ready(function(){
    $('.board-slider').slick({
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 1500,
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
    $('.request-slide').slick({
        dots: true,
        infinite: true,
        autoplay: true,
        autoplaySpeed: 1500,
        speed: 500,
        vertical:true
    });
    initStars();
});




function initStars(){
    let starWrap = $('.star-wrap').get();
    for( let i = 0 ; i < starWrap.length; i++){
        let num = $(starWrap[i]).text();
        $(starWrap[i]).empty();
        for (let z=0; z<5; z++){
            if(z < num){
                $(starWrap[i]).append('<img class="star-item" src="'+getContextPath()+'/images/star.png"/>');
            } else{
                $(starWrap[i]).append('<img class="star-item" src="'+getContextPath()+'/images/star_empty.png"/>');
            }
        }
    }
}

function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}
// $(document).ready(function(){
// window.onload = function(){
$(window).load(function(){
    // 페이지 로드시 동작 실행 부분
    initStars();
    imgSlider($('.img-li').eq(0).children('img'));
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

function radioStarInit(){
    $('input:radio[name=radio-stars]').click(function (){
        const score = $('input:radio[name=radio-stars]:checked').val();
        let stars = $('.radio-star-label').get();
        for(let i=0; i<5; i++){
            $(stars[i]).css("background",'url('+getContextPath()+'/images/star_empty.png)');
            $(stars[i]).css("background-size",'contain');
        }
        for(let i=0; i<score; i++){
            $(stars[i]).css("background",'url('+getContextPath()+'/images/star.png)');
            $(stars[i]).css("background-size",'contain');
        }
    })
}
function btnWriteReview(ele) {
    if(!$('.review-li.write').length){
        $('.review-ul').prepend('<li class="review-li write">\n' +
            '                            <p>리뷰 작성하기</p>\n' +
            '                            <form action="'+getContextPath()+'/review/write" method="post">\n' +
            '                                <div class="radio-star-wrap">\n' +
            '                                    <input type="radio" name="radio-stars" id="star-1" value="1" class="radio-stars">\n' +
            '                                    <label class="radio-star-label"for="star-1"></label>\n' +
            '                                    <input type="radio" name="radio-stars" id="star-2" value="2" class="radio-stars" >\n' +
            '                                    <label class="radio-star-label"for="star-2"></label>\n' +
            '                                    <input type="radio" name="radio-stars" id="star-3" value="3" class="radio-stars" >\n' +
            '                                    <label class="radio-star-label"for="star-3"></label>\n' +
            '                                    <input type="radio" name="radio-stars" id="star-4" value="4" class="radio-stars" >\n' +
            '                                    <label class="radio-star-label"for="star-4"></label>\n' +
            '                                    <input type="radio" name="radio-stars" id="star-5" value="5" class="radio-stars" checked>\n' +
            '                                    <label class="radio-star-label"for="star-5"></label>\n' +
            '                                </div>\n' +
            '                                <textarea name="review-subs" class="review-subs" required></textarea>\n' +
            '                                <input type="submit" value="확인">\n' +
            '                                <input type="button" value="취소" onclick="btnCancelReview()">\n' +
            '                            </form>\n' +
            '                        </li>');
        radioStarInit();
        $(ele).addClass('hide');
    }
}
function btnCancelReview() {
    $('.review-li.write').remove();
    $('.btn-write-review').removeClass('hide');
}

function btnImg(action){
    let index = $('.img-item.select').parents('li').index();
    const lengthLi = $('.img-li').get().length-1;
    let resultIndex;
    if (action == 'prev'){
        if (index != 0){
            resultIndex = index-1;
        }else if (index == 0){
            resultIndex = lengthLi;
        }
    } else if(action == 'next') {
        if (index != lengthLi){
            resultIndex = index+1;
        }else if (index == lengthLi){
            resultIndex = 0;
        }
    }
    let resultEle = $('.img-li').eq(resultIndex).children('img');
    imgSlider(resultEle);
}

function sliderHover(ele){
    imgSlider(ele);
}

function imgSlider(ele) {
    $('.img-item').removeClass('select')
    $(ele).addClass('select');
    let imgView = $('#img-main');
    imgView.attr('src',$(ele).attr('src'));
}

function plzLogin(){
    alert("로그인을 해주세요");
}
function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

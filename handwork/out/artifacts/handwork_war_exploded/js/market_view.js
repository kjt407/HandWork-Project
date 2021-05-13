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
        // $(starWrap[i]).empty();
        for (let z=0; z<5; z++){
            if(z < num){
                $(starWrap[i]).append('<img class="star-item" src="'+getContextPath()+'/images/star.png"/>');
            } else{
                $(starWrap[i]).append('<img class="star-item" src="'+getContextPath()+'/images/star_empty.png"/>');
            }
        }
    }
}

// 리뷰 작성 부분
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
        switch (score) {
            case '1' :
                $('.score-label').text("1점 (별로에요)");
                break;
            case '2' :
                $('.score-label').text("2점 (그저그래요)");
                break;
            case '3' :
                $('.score-label').text("3점 (평범해요)");
                break;
            case '4' :
                $('.score-label').text("4점 (좋아요)");
                break;
            case '5' :
                $('.score-label').text("5점 (최고에요)");
                break;
        }
    })
}
function btnWriteReview(ele,board_num) {
    if(!$('.review-li.write').length){
        $('.review-ul').prepend('<li class="review-li write">\n' +
            '                            <p>리뷰 작성하기</p>\n' +
            '                            <form action="'+getContextPath()+'/review/write" method="post">\n' +
            '                                <div class="radio-star-wrap">\n' +
            '                                    <input type="hidden" name="board-num" id="board-num" value="'+board_num+'" class="radio-stars">\n' +
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
            '                                    <span class="score-label">5점 (최고에요)</span>\n' +
            '                                </div>\n' +
            '                                <textarea name="review-subs" class="review-subs" required></textarea>\n' +
            '                                <div class="review-write-control">\n' +
            '                                   <input type="submit" class="btn-write-submit" value="확인">\n' +
            '                                   <input type="button" class="btn-write-cancel" value="취소" onclick="btnCancelReview()">\n' +
            '                                </div>\n' +
            '                            </form>\n' +
            '                        </li>');
        radioStarInit();

        $('.btn-write-review').addClass('hide');
        $('.review-control').addClass('hide');
    }
}
function btnCancelReview() {
    $('.review-li.write').remove();
    $('.btn-write-review').removeClass('hide');
    $('.review-control').removeClass('hide');
}
// 리뷰 작성부분

//리뷰 수정 부분
function reviewEdit(ele,board_num,review_idx) {
    let parent = $(ele).parents('li.review-li');
    const index = $(parent).find('div.star-wrap').text()-1;
    const  subs = $(parent).find('p.review-subs').text();
    parent.after('<li class="review-li edit">\n' +
        '                            <p>리뷰 수정하기</p>\n' +
        '                            <form action="'+getContextPath()+'/review/update" method="post">\n' +
        '                                <div class="radio-star-wrap">\n' +
        '                                    <input type="hidden" name="review-idx" id="review-idx" value="'+review_idx+'">\n' +
        '                                    <input type="hidden" name="board-num" id="board-num" value="'+board_num+'">\n' +
        '                                    <input type="radio" name="radio-stars" id="star-1" value="1" class="radio-stars">\n' +
        '                                    <label class="radio-star-label"for="star-1"></label>\n' +
        '                                    <input type="radio" name="radio-stars" id="star-2" value="2" class="radio-stars" >\n' +
        '                                    <label class="radio-star-label"for="star-2"></label>\n' +
        '                                    <input type="radio" name="radio-stars" id="star-3" value="3" class="radio-stars" >\n' +
        '                                    <label class="radio-star-label"for="star-3"></label>\n' +
        '                                    <input type="radio" name="radio-stars" id="star-4" value="4" class="radio-stars" >\n' +
        '                                    <label class="radio-star-label"for="star-4"></label>\n' +
        '                                    <input type="radio" name="radio-stars" id="star-5" value="5" class="radio-stars">\n' +
        '                                    <label class="radio-star-label"for="star-5"></label>\n' +
        '                                    <span class="score-label"></span>\n' +
        '                                </div>\n' +
        '                                <textarea name="review-subs" class="review-subs" required>'+subs+'</textarea>\n' +
        '                                <div class="review-write-control">\n' +
        '                                   <input type="submit" class="btn-write-submit" value="확인">\n' +
        '                                   <input type="button" class="btn-write-cancel" value="취소" onclick="btnCancelEdit()">\n' +
        '                                </div>\n' +
        '                            </form>\n' +
        '                        </li>');
    $(parent).addClass('hide');
    $('.btn-write-review').addClass('hide');
    $('.review-control').addClass('hide');
    radioStarInit();
    let stars = $('.radio-stars').get();
    $(stars[index]).trigger('click');
    $(stars[index]).trigger('click');
}
function btnCancelEdit() {
    $('.review-li.edit').remove();
    $('li.review-li').removeClass('hide');
    $('.btn-write-review').removeClass('hide');
    $('.review-control').removeClass('hide');
}
//리뷰 수정 부분

// 이미지 뷰 부분
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
// 이미지 뷰 부분

function plzLogin(){
    alert("로그인을 해주세요");
}
function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

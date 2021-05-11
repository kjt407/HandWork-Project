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

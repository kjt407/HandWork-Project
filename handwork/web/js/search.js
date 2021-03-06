let type;
let param;
let mpage = 1;
let rpage = 1;

$(window).load(function(){
    let boardTitle = $('.board-title-section')
    // 페이지 로드시 동작 실행 부분
    if(getParameterByName('c') != null && getParameterByName('c') != '') {
        type = 'c';
        param = getParameterByName('c');
        boardTitle.addClass('category');
        boardTitle.children('div').text('"'+param+'" 카테고리 검색결과 입니다.');
    } else if(getParameterByName('q') != null && getParameterByName('q') != '') {
        type = 'q';
        param = getParameterByName('q');
        boardTitle.addClass('search');
        boardTitle.children('div').text('"'+param+'" 키워드 검색결과 입니다.');
    }

    console.log(type);
    init();
});

// 페이지 로드시 실행되는 함수
function init() {
    loadMarket();
    loadReq();
}

function loadReq(){
    const json = {
        type:type,
        query:param,
        op:'request',
        page: rpage
    };
    ajaxFunc(json);
}

function loadMarket(){
    const json = {
        type:type,
        query:param,
        op:'market',
        page: mpage
    };
    ajaxFunc(json);
}

// AJAX 통신 부분
function ajaxFunc(json) {
    const url = getContextPath()+"/search";

    $.ajax({
        type:"GET",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:json,
        success:function(data){
            if(json.op == 'market'){
                createMarket(data);
            } else if( json.op == 'request') {
                createRequest(data);
            }
            console.log(data);
        },
        error:function(){
            alert('서버에서 정보를 받아올 수 없습니다');
        }
    })
}

function createMarket(data) {
    if(data == null || data.list == null || data.list.length < 1){
        $('div.board-content-list.market').append(
            '<div class="empty-container">\n' +
            ' <p class="list-empty">작성한 게시글이 없습니다</p>' +
            '</div>'
        )
    } else {
        for (let i = 0; i < data.list.length; i++) {
            const idx = data.list[i].idx;
            const writer = data.list[i].writer;
            const title = data.list[i].title;
            const category = data.list[i].category;
            const location = data.list[i].location;
            const period = data.list[i].period;
            const price = data.list[i].price;
            const content = data.list[i].content;
            const regdate = data.list[i].regdate;
            const hit = data.list[i].hit;
            const filename = data.list[i].filename;
            let imgpath = '/images/noImage.png';
            let classname = 'no-img';
            if (filename != null && filename != '') {
                imgpath = '/upload/marketBoard/' + filename.split('/')[0];
                classname = '';
            }
            let state = data.list[i].state;
            if (state != null && state == 0) {
                state = 'sell';
            } else if (state != null && state == 1) {
                state = 'out';
            }
            const reviewnum = data.list[i].reviewnum;
            const starAvg = data.list[i].starAvg;
            const topReview = data.list[i].topReview;

            $('div.board-content-list.market').append(
                '<div class="board-items market">\n' +
                '                    <a href="' + getContextPath() + '/market/detail?id=' + idx + '" class="img-wrap"><img src="' + getContextPath() + imgpath + '" class="' + classname + '" alt="제품이미지"></a>\n' +
                '                    <div class="item-container market">\n' +
                '                        <div class="item-row">\n' +
                '                            <a class="item-writer">' + writer + '</a>\n' +
                '                            <a class="item-location">' + location + '</a>\n' +
                '                        </div>\n' +
                '                        <a href="' + getContextPath() + '/market/detail?id=' + idx + '" class="item-title">' + title + '</a>\n' +
                '                        <a href="' + getContextPath() + '/market/detail?id=' + idx + '" class="item-price state ' + state + '">' + price + '원</a>\n' +
                '                        <p class="item-review-num">' + reviewnum + '</p>\n' +
                '                        <div class="item-review">\n' +
                '                            <div class="star-wrap">' + starAvg + '</div>\n' +
                '                            <span class="review-text">' + topReview + '</span>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>'
            )
        }
        initStars();
    }
    $('div.board-content-list.market').next('#btn-market-more').remove();
    if(data.next){
        $('div.board-content-list.market').after('<input type="button" id="btn-market-more" class="btn-more" onclick="btnMoreMarket()" value="↓">');
    }
}

function createRequest(data) {
    if(data == null || data.list == null || data.list.length < 1){
        $('ul.board-content-list.request').append(
            '<div class="empty-container">\n' +
            ' <p class="list-empty">작성한 게시글이 없습니다</p>' +
            '</div>'
        )
    } else {
        for (let i = 0; i < data.list.length; i++) {
            const idx = data.list[i].idx;
            const writer = data.list[i].writer;
            const title = data.list[i].title;
            const category = data.list[i].category;
            const location = data.list[i].location;
            const deadline = data.list[i].deadline;
            const price = data.list[i].price;
            const content = data.list[i].content;
            const regdate = data.list[i].regdate;
            const hit = data.list[i].hit;
            const filename = data.list[i].filename;
            let imgpath = '/images/noImage.png';
            let classname = 'view-img no-img';
            const comment = data.list[i].comment;
            if (filename != null && filename != '') {
                imgpath = '/upload/requestBoard/' + filename.split('/')[0];
                classname = 'view-img';
            }
            let state = data.list[i].state;
            if (state != null && state == 0) {
                state = 'auction';
            } else if (state != null && state == 1) {
                state = 'complete';
            }
            const writer_id = data.list[i].writer_id;

            $('ul.board-content-list.request').append('<li>\n' +
                '                    <div class="board-items">\n' +
                '                        <a href="' + getContextPath() + '/request/detail?id=' + idx + '" class="img-container">\n' +
                '                                    <img src="' + getContextPath() + imgpath + '" class="' + classname + '">\n' +
                '                        </a>\n' +
                '                        <div class="board-list-main">\n' +
                '                            <div class="row-wrapper">\n' +
                '                                <a class="board-list-category">' + category + '</a>\n' +
                '                                <a class="board-list-locale">' + location + '</a>\n' +
                '                            </div>\n' +
                '                            <a href="' + getContextPath() + '/request/detail?id=' + idx + '" class="board-list-title">' + title + '</a>\n' +
                '                            <a href="' + getContextPath() + '/request/detail?id=' + idx + '" class="board-list-subs">' + content + '</a>\n' +
                '                            <div class="row-wrapper">\n' +
                '                                        <a href="' + getContextPath() + '/request/detail?id=' + idx + '" class="board-list-price state ' + state + '">' + price + '원</a>\n' +
                '                                <div>\n' +
                '                                    <a class="board-list-writer">' + writer + '</a>\n' +
                '                                    <p class="board-list-writedate">' + regdate + '</p>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="board-list-aside">\n' +
                '                            <p class="board-list-deadline">' + deadline + '</p>\n' +
                '                            <span class="board-list-comment">' + comment + '</span>\n' +
                '                            <span class="board-list-views">' + hit + '</span>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </li>')
        }
    }
    $('ul.board-content-list.request').next('#btn-request-more').remove();
    if(data.next){
        $('ul.board-content-list.request').after('<input type="button" id="btn-request-more" class="btn-more" onclick="btnMoreReqest()" value="↓">');
    }
}
function btnMoreMarket(){
    mpage += 1;
    loadMarket();
}

function btnMoreReqest(){
    rpage += 1;
    loadReq();
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? null : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

function initStars(){
    let starWrap = $('.star-wrap').get();
    for( let i = 0 ; i < starWrap.length; i++){
        let num = $(starWrap[i]).text();
        $(starWrap[i]).find('img').remove();
        for (let z=0; z<5; z++){
            if(z < num){
                $(starWrap[i]).append('<img class="star-item" src="'+getContextPath()+'/images/star.png"/>');
            } else{
                $(starWrap[i]).append('<img class="star-item" src="'+getContextPath()+'/images/star_empty.png"/>');
            }
        }
    }

}

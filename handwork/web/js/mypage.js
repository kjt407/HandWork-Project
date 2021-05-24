let bpage = 1;
let rpage = 1;

$(window).load(function(){
    init();
});

function init() {
    loadBoard();
    loadReply();
}

function loadBoard(){
    const json = {
        op:'board',
        page: bpage
    };
    ajaxFunc(json);
}

function loadReply(){
    const json = {
        op:'reply',
        page: rpage
    };
    ajaxFunc(json);
}

// AJAX 통신 부분
function ajaxFunc(json) {
    const url = getContextPath()+"/mypage";

    $.ajax({
        type:"post",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:json,
        success:function(data){
            alert('AJAX 통신성공');
            if(json.op == 'board'){
                createBoard(data);
                console.log(data);
            } else if( json.op == 'reply') {
                createReply(data);
                console.log(data);
            }
        },
        error:function(){
            alert('서버에서 정보를 받아올 수 없습니다');
        }
    })
}

function createBoard(data) {
    for(let i=0; i<data.list.length; i++){
        const idx = data.list[i].boardnum;
        const type = data.list[i].boardname;
        const title = data.list[i].title;
        const regdate = data.list[i].regdate;
        let typeKor;
        if (type == 'market'){
            typeKor = "수제공방";
        }else if (type == 'request'){
            typeKor = "제작의뢰";
        }

        $('ul.log-ul.board').append(
            '<li class="log-li board">\n' +
            '                            <a href="'+getContextPath()+'/'+type+'/detail?id='+idx+'">\n' +
            '                                <div class="wrap">\n' +
            '                                    <p class="log-li-type '+type+' board">'+typeKor+'</p>\n' +
            '                                    <p class="log-li-title board">'+title+'</p>\n' +
            '                                </div>\n' +
            '                                <p class="log-li-date board">'+regdate+'</p>\n' +
            '                            </a>\n' +
            '                        </li>'
        )
    }
    $('ul.log-ul.board').next('#btn-more-board').remove();
    if(data.next){
        $('ul.log-ul.board').after('<input type="button" id="btn-more-board" class="btn-more" onclick="btnMoreBoard()" value="↓">');
    }
}

function createReply(data) {
    for(let i=0; i<data.list.length; i++){
        const idx = data.list[i].boardnum;
        const type = data.list[i].boardname;
        const title = data.list[i].title;
        const regdate = data.list[i].regdate;
        let typeKor;
        if (type == 'review'){
            typeKor = "리뷰";
        }else if (type == 'comment'){
            typeKor = "댓글";
        }

        $('ul.board-content-list.request').append(
            '<li class="log-li reply">\n' +
            '                            <a href="">\n' +
            '                                <p class="log-li-type reply">제작의뢰</p>\n' +
            '                                <p class="log-li-title reply">제목입니다</p>\n' +
            '                                <p class="log-li-date reply">제목입니다</p>\n' +
            '                            </a>\n' +
            '                        </li>'
        )
    }
    $('ul.board-content-list.request').next('#btn-request-more').remove();
    if(data.next){
        $('ul.board-content-list.request').after('<input type="button" id="btn-request-more" class="btn-more" onclick="btnMoreReply()" value="↓">');
    }
}
function btnMoreBoard(){
    bpage += 1;
    loadBoard();
}

function btnMoreReply(){
    rpage += 1;
    loadReply();
}

function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

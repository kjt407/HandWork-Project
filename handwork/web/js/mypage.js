let bpage = 1;
let rpage = 1;

$(window).load(function(){
    init();
    $('#edit-profile-img').change(function(){
        imgUpload();
    });
});

function init() {
    loadBoard();
    loadReply();
    loadInfo();
    // createInfo({userName:'김종태'});
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

function loadInfo(){
    const json = {
        op:'info'
    };
    ajaxFunc(json);
}

function editInfo(col,ele){
    const data = $(ele).parent().find('.edit-data').val();
    const json = {
        op:'update',
        col:col,
        data:data
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
            if(json.op == 'board'){
                createBoard(data);
            } else if( json.op == 'reply') {
                createReply(data);
            } else if( json.op == 'info' || json.op == 'update') {
                createInfo(data);
            }
        },
        error:function(){
            alert('서버에서 정보를 받아올 수 없습니다');
        }
    });
}
function imgUpload(){
    if($("#edit-profile-img").val() != null && $("#edit-profile-img").val() != ""){
        console.log('업로드 발생함'+$("#edit-profile-img").val());
        console.log($("#edit-profile-img").val());
        $("#img-upload-form").ajaxForm({
            url : "/mypage/user-img",
            enctype : "multipart/form-data",
            dataType : "json",
            error : function(){
                alert("에러") ;
            },
            success : function(result){
                alert("성공") ;
            }
        });
        $("#img-upload-form").submit() ;
    }
}


출처: https://fruitdev.tistory.com/199 [과일가게 개발자]

// 동작 메서드
function createInfo(data){

    const userName = data.userName;
    const userImg = data.userImg;
    const userEmail = data.userEmail;
    const userPhone = data.userPhone;

    $('p.info-item').removeClass('null');
    $('p.info-item.name').text(userName);
    if (userName == null || userName.replaceAll(" ","") == ""){
        $('p.info-item.name').addClass('null');
    }
    $('p.info-item.email').text(userEmail)
    if (userEmail == null || userEmail.replaceAll(" ","") == ""){
        $('p.info-item.email').addClass('null');
    }
    $('p.info-item.phone').text(userPhone);
    if (userPhone == null || userPhone.replaceAll(" ","") == ""){
        $('p.info-item.phone').addClass('null');
    }
    if (userImg != null || userImg.replaceAll(" ","") != ""){
        $('img#img-profile').attr('src',getContextPath()+'/upload/profile/'+userImg);
    }

}

function createBoard(data) {
    if(data == null || data.list == null || data.list.length < 1){
        $('ul.log-ul.board').append(
            '<li class="log-li board">\n' +
            ' <p class="list-empty">작성한 게시글이 없습니다</p>' +
            '</li>'
        )
    } else{
        for(let i=0; i<data.list.length; i++){
            const idx = data.list[i].boardnum;
            const type = data.list[i].boardname;
            const title = data.list[i].title;
            let regdate = data.list[i].regdate;
            regdate = regdate.substring(0,regdate.length-3);
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
    }
    $('ul.log-ul.board').next('#btn-more-board').remove();
    if(data.next){
        $('ul.log-ul.board').after('<input type="button" id="btn-more-board" class="btn-more" onclick="btnMoreBoard()" value="↓">');
    }
}

function createReply(data) {
    if(data == null || data.list == null || data.list.length < 1){
        $('ul.log-ul.reply').append(
            '<li class="log-li board">\n' +
            ' <p class="list-empty">작성한 게시글이 없습니다</p>' +
            '</li>'
        )
    } else {
        for (let i = 0; i < data.list.length; i++) {
            const idx = data.list[i].board_num;
            const type = data.list[i].replyname;
            const content = data.list[i].content;
            let regdate = data.list[i].regdate;
            regdate = regdate.substring(0, regdate.length - 3);
            let typeKor;
            let typeBoard;
            if (type == 'review') {
                typeKor = "리뷰";
                typeBoard = 'market';
            } else if (type == 'comment') {
                typeKor = "댓글";
                typeBoard = 'request';
            }

            $('ul.log-ul.reply').append(
                '<li class="log-li reply">\n' +
                '                            <a href="' + getContextPath() + '/' + typeBoard + '/detail?id=' + idx + '">\n' +
                '                                <div class="wrap">\n' +
                '                                   <p class="log-li-type ' + type + ' reply">' + typeKor + '</p>\n' +
                '                                   <p class="log-li-title reply">' + content + '</p>\n' +
                '                                </div>\n' +
                '                                <p class="log-li-date reply">' + regdate + '</p>\n' +
                '                            </a>\n' +
                '                        </li>'
            )
        }
    }
    $('ul.log-ul.reply').next('#btn-more-reply').remove();
    if(data.next){
        $('ul.log-ul.reply').after('<input type="button" id="btn-more-reply" class="btn-more" onclick="btnMoreReply()" value="↓">');
    }
}
function btnEdit(ele,option){
    const parent = $(ele).parents('div.info-row');
    const content = $(parent).find('p.info-item').text();
    $(parent).find('p.info-item, input.btn-edit').addClass('hide');
    $(parent).append('<div style="display: flex;"><input type="text" class="edit-data" value="'+content+'"><input type="button" value="수정" onclick="editInfo(\''+option+'\',this)"><input type="button" value="취소" onclick="btnEditCancel(this)"></div>');
}
function btnEditCancel(ele){
    const parentRow = $(ele).parents('div.info-row');
    const parent= $(ele).parent('div');
    $(parentRow).children().removeClass('hide');
    parent.remove();
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

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
    btnEditCancel(ele);
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
            } else if( json.op == 'info') {
                createInfo(data);
            } else if( json.op == 'update') {
                loadInfo();
            } else if( json.op == 'check-account') {
                if(data != null && data.result != null && data.result){
                    createPwCheck();
                } else {
                    alert('소셜계정은 해당 플랫폼에서 변경이 가능합니다');
                }
            } else if( json.op == 'check-pw') {
                if(data != null && data.result != null && data.result){
                    createPwEdit();
                } else {
                    alert('비밀번호를 다시 확인해주세요');
                }
            } else if( json.op == 'edit-pw') {
                if(data != null && data.result != null && data.result){
                    btnPwCancel();
                    alert('비밀번호가 변경되었습니다')
                } else {
                    alert('비밀번호 변경에 실패했습니다');
                }
            }
        },
        error:function(){
            if( json.op == 'check-account') {
                btnPwCancel();
            } else if ( json.op == 'board' ) {
                bpage--;
            } else if ( json.op == 'reply' ) {
                rpage--;
            }
            alert('서버에서 정보를 받아올 수 없습니다');
        }
    });
}
function imgUpload(){
    if($("#edit-profile-img").val() != null && $("#edit-profile-img").val() != ""){
        $("#img-upload-form").ajaxForm({
            url : getContextPath()+"/mypage/userimg",
            enctype : "multipart/form-data",
            dataType : "json",
            success : function(data){
                loadInfo();
            },
            error : function(){
                alert("이미지를 업로드 에러\n로그인 상태를 확인하세요") ;
            }
        });
        $("#img-upload-form").submit() ;
    }
}

function createInfo(data){

    const userName = data.userName;
    const userImg = data.userImg;
    const userEmail = data.userEmail;
    const userPhone = data.userPhone;
    
    $('.header-username').text(userName+'님');
    $('p.info-item').removeClass('null');
    if (userName == null || userName.replaceAll(" ","") == ""){
        $('p.info-item.name').addClass('null');
    } else {
        $('p.info-item.name').text(userName);
    }
    if (userEmail == null || userEmail.replaceAll(" ","") == ""){
        $('p.info-item.email').addClass('null');
    } else {
        $('p.info-item.email').text(userEmail)
    }
    if (userPhone == null || userPhone.replaceAll(" ","") == ""){
        $('p.info-item.phone').addClass('null');
    } else {
        $('p.info-item.phone').text(userPhone);
    }
    if (userImg != null || userImg.replaceAll(" ","") != ""){
        $('img#img-profile').attr('src',getContextPath()+'/upload/profile/'+userImg);
    }
}

function createPwCheck(){
    const parent = $('div.info-row.passwd');
    parent.children('.info-item.passwd').addClass('hide');
    parent.children('.btn-edit.passwd').addClass('hide');
    parent.append('<div class="check-pw"><input type="password" id="check-pw" placeholder="현재 비밀번호를 입력해주세요"><input type="button" value="확인" onclick="btnPwCheck(this)"><input type="button" value="취소" onclick="btnPwCancel()"></div>')
}

function createPwEdit(){
    const parent = $('div.info-row.passwd');
    parent.children('div.check-pw').remove();
    parent.append('<div class="edit-pw"><input type="password" id="edit-pw" placeholder="변견하실 비밀번호를 입력해주세요"><input type="password" id="edit-pw-re" placeholder="한번 더 입력해 주세요"><input type="button" value="확인" onclick="btnPwEdit(this)"><input type="button" value="취소" onclick="btnPwCancel()"></div>')
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

function btnPw(){
    const json = {
        op:'check-account',
    };
    ajaxFunc(json);
}

function btnPwCheck(ele){
    const parent = $(ele).parent('div.check-pw');
    const data = parent.children('input#check-pw').val();
    if(data == null || data.length < 1 || data.includes(" ")){
        alert('비밀번호 형식이 잘못되었습니다');
        return;
    }
    const json = {
        op:'check-pw',
        data:data
    };
    ajaxFunc(json);
}

function btnPwEdit(ele){
    const parent = $(ele).parent('div.edit-pw');
    const data = parent.children('input#edit-pw').val();
    const data_re = parent.children('input#edit-pw-re').val();
    if(data != data_re) {
        alert('비밀번호가 일치하지 않습니다');
        return;
    } else if(data == null || data.length < 1 || data.includes(" ")){
        alert('비밀번호 형식이 올바르지 않습니다');
        return;
    }
    const json = {
        op:'edit-pw',
        data:data
    };
    ajaxFunc(json);
}

function btnPwCancel(){
    const parent = $('div.info-row.passwd');
    parent.children().removeClass('hide');
    parent.children('input.btn-edit.passwd').nextAll().remove();
}

function btnEdit(ele,option){
    const parent = $(ele).parents('div.info-row');
    const content = $(parent).find('p.info-item').text();
    $(parent).find('p.info-item, input.btn-edit').addClass('hide');
    $(parent).append('<div style="display: flex;"><input type="text" class="edit-data '+option+'" value="'+content+'"><input type="button" value="수정" onclick="editInfo(\''+option+'\',this)"><input type="button" value="취소" onclick="btnEditCancel(this)"></div>');
    addEvent();
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

function addEvent(){
    $('input.edit-data.phone').attr('maxlength',13);
    $('input.edit-data.phone').keyup(function(){
        this.value = autoHypenPhone( this.value );
    })
}

var autoHypenPhone = function(str){
    str = str.replace(/[^0-9]/g, '');
    var tmp = '';
    if( str.length < 4){
        return str;
    }else if(str.length < 7){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3);
        return tmp;
    }else if(str.length < 11){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 3);
        tmp += '-';
        tmp += str.substr(6);
        return tmp;
    }else{
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 4);
        tmp += '-';
        tmp += str.substr(7);
        return tmp;
    }

    return str;
}

function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

// $(document).ready(function(){
// window.onload = function(){
$(window).load(function(){
    // 페이지 로드시 동작 실행 부분
    commentLoad();
});

// 단순 댓글 불러오는 함수
function commentLoad() {
    const boardNum = $('#board-id').val();
    // $('#board-id').css("background","red");
    // alert(boardNum);
    const json = {
        requestType:'load',
        boardNum:boardNum
    };
    ajaxPost(json);
}


// 로그인 체크
function loginCheck(cWriterId) {
    const sessionID = $('#session-id').val();
    // alert('세션 값: '+sessionID);
    if(sessionID == null || sessionID === "") {
        return 0; // 로그인 X
    } else if(sessionID != cWriterId) {
        return 1; // 댓글 작성자와 일치하지 않음
    } else if(sessionID == cWriterId) {
        return 2; // 댓글 작성자와 일치
    }
}

// 해당 댓글 삭제 온클릭 함수
function commentDelete(ele) {
    // const sessionIDtrim = sessionStorage.getItem('id');
    const parentLi = $(ele).parents('.comment-li');


    switch (loginCheck(parentLi.children('.comment-writerid').val())){
        case 0 :
            alert('로그인을 해주세요');
            return;
            break;
        case 1 :
            alert('댓글 작성자만 수정/삭제 할 수 있습니다');
            return;
            break;
        case 2 :
            // alert('세션 확인 완료')
            break;
    }
    const commentIndex = parentLi.children('.comment-index').val();
    const boardNum = $('#board-id').val();
    // alert('삭제할 댓글NUM : '+commentIndex);
    const json = {
        requestType:'delete',
        commentIndex:commentIndex,
        boardNum:boardNum
    };
    ajaxPost(json);
}

// 해당 댓글 수정 온클릭 함수
function commentEdit(ele) {
    const parentLi = $(ele).parents('.comment-li');

    switch (loginCheck(parentLi.children('.comment-writerid').val())){
        case 0 :
            alert('로그인을 해주세요');
            return;
            break;
        case 1 :
            alert('댓글 작성자만 수정/삭제 할 수 있습니다');
            return;
            break;
        case 2 :
            // alert('세션 확인 완료')
            break;
    }
    const commentIndex = parentLi.children('.comment-index').val();
    const boardNum = $('#board-id').val();

    parentLi.addClass('hide');
    parentLi.after('<li class="comment-edit-li"> <p class="comment-edit-title">댓글 수정하기</p> <input type="hidden" class="comment-index" value="'+commentIndex+'">   <div class="comment-edit-row"></div>   <div class="comment-edit-row"><span class="comment-edit-label subs">제안내용</span><textarea class="comment-edit-subs">'+parentLi.find('.comment-subs').html()+'</textarea></div>         <div class="comment-edit-row"><input type="button" class="comment-edit-btn confirm" value="확인" onclick="editAction(0,this)"> <input type="button" class="comment-edit-btn" value="취소" onclick="editAction(1,this)"/></div> </li>')
}

function editAction(action,ele){
    const parentLi = $(ele).parents('.comment-edit-li');

    if (action != null && action === 0) {
        const commentIndex = parentLi.find('.comment-index').val();
        const subs = parentLi.find('.comment-edit-subs').val();
        const boardNum = $('#board-id').val();
        const json = {
            requestType:'edit',
            commentIndex:commentIndex,
            boardNum:boardNum,
            subs: subs
        };
        ajaxPost(json);
    } else if (action != null && action === 1) {
        parentLi.prev().removeClass('hide');
        parentLi.remove();
    }
}


// 댓글 등록 온클릭 함수
function commentRegister(sessionID, boardNum){
    let sessionIDtrim = sessionID.trim();
    let subs = $('#suggest-comment').val();

    //댓글 입력 폼 미기입시 예외처리
   if (subs == null || subs === "") {
        alert('제안 내용을 입력해주세요');
        return;
    }
    const json = {
        requestType:'register',
        sessionID:sessionIDtrim,
        boardNum:boardNum,
        subs: subs
    };
    ajaxPost(json);
    $('#suggest-comment').val(null);
}


// AJAX 통신 부분
function ajaxPost(json) {
    const url = getContextPath()+"/fcomment";

    $.ajax({
        type:"POST",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:json,
        success:function(data){
            createEle(data);
        },
        error:function(){
            if(json.requestType == 'edit'){
                alert('잘못된 정보를 입력하셨습니다');
            } else {
                alert('Ajax 통신 에러');
            }
        }
    })
}

function createEle(data) {
    const jsonLength = data.length;
    $('#comment-ul').children().remove();
    for(let i=0; i<jsonLength; i++) {
        const cIdx = data[i].idx;
        const cWriterID = data[i].userid;
        const cWriterName = data[i].name;
        const cWriteTime = data[i].time;
        const cSubs = data[i].content;
        let profile_img = data[i].profile_img;
        if (profile_img == null || profile_img.replaceAll(" ","") == "") {
            profile_img = "noProfile.png"
        }
        $('#comment-ul').append('<li class="comment-li"> <input type="hidden" class="comment-index" value='+cIdx+'> <input type="hidden" class="comment-writerid" value='+cWriterID+'> <div class="comment-li-container"> <div class="user-img-wrap"> <img src="'+getContextPath()+'/upload/profile/'+profile_img+'" alt="댓글 작성자 프로필" class="user-img"> </div> <div class="comment-content-wrap"> <div class="comment-content-row comment-header"> <div class="header-top">                     <div class="top-left"><span class="comment-writername" >' + cWriterName + '</span> </div>                      <div class="top-right"><input type="button" onclick="commentEdit(this)" value="수정">                  <input type="button" onclick="commentDelete(this)" value="삭제"></div></div>                     <div class="header-bottom"><span class="comment-writedate" >' + cWriteTime.substring(0,16) + '</span></div> </div> <div class="comment-content-row comment-body">                     <p class="comment-subs">' + cSubs + '</p>                     <div class="comment-control">                                          </div> </div> </div> </div> </li>');
    }
}


function plzLogin(){
    alert("로그인을 해주세요");
}
function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

// $(document).ready(function(){
// window.onload = function(){
$(window).load(function(){
    // 페이지 로드시 동작 실행 부분
    $('li.notice-content-li').click(function (){
        const content = $(this).find('div.notice-content');
        if(content.length) {
            content.remove();
        } else {
            console.log($(this).find('input.id').val());
            const id = $(this).find('input.id').val();
            const json = {
                id:id
            };
            ajaxPost(json,this);
        }
    })
});


// AJAX 통신 부분
function ajaxPost(json,parent) {
    const url = getContextPath()+"/notice/detail";

    $.ajax({
        type:"GET",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:json,
        success:function(data){
            createContent(data,parent,json.id);
        },
        error:function(){
            alert('서버에서 정보를 받아오지 못했습니다');
        }
    })
}

function createContent(data, parent,id) {
    if(data != null){
        const content = data.content.replaceAll('\r\n','<br/>');
        console.log(content);
        const isadmin = data.admin;

        $(parent).append('<div class="notice-content">\n' +
            '                                <p class="board-list-content">'+content+'</p>\n' +
            '                            </div>');
        if(isadmin){
            $(parent).find('div.notice-content').append('<a href="'+getContextPath()+'/notice/update?id='+id+'" class="btn-edit" >수정</a>\n' +
                '                        \t\t<a href="'+getContextPath()+'/notice/delete?id='+id+'" class="btn-edit"}>삭제</a>');
        }
    }
}

function getContextPath() {

    var hostIndex = location.href.indexOf( location.host ) + location.host.length;

    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}

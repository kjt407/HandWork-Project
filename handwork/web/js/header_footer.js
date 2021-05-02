$(document).ready(function(){
});

function footerCopyUrl(ele){
    // window.clipboardData.setData('Text', location.href);
    // var f = document.clipboard.url;
    // f.value = document.location.href;
    // f.select() ;
    // therange=f.createTextRange() ;
    // var url = $(ele).select();
    // document.execCommand('copy');
    // // therange.execCommand("Copy");
    // alert("클립보드로 URL이 복사되었습니다."+url);
    var link = document.location.href; 
    const tempEle = document.createElement('textarea');
    tempEle.value = link;
    document.body.appendChild(tempEle);
    tempEle.select();
    document.execCommand('copy');
    document.body.removeChild(tempEle);
    alert('핸드워크 페이지의 주소가 복사되었습니다\n이용해 주셔서 감사합니다')
}
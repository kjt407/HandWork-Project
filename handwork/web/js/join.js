function selectEmail(ele){
    var $ele = $(ele);
    var $email2 = $('input[name=email2]'); 
    // '1'인 경우 직접입력
    if($ele.val() == "direct"){
            $email2.attr('readonly', false);
            $email2.val('');
    } else { 
        $email2.attr('readonly', true);
        $email2.val($ele.val()); 
    } 
}

function pwCheck(){
    var pw = $('#join-pw').val();
    var pwRe = $('#join-pw-check').val();
    var pwReNotice = $('.pw_check_notice');
    var submit = $('input[type=submit]')
    if(pw==pwRe) {
        pwReNotice.html("비밀번호 확인 일치");
        submit.attr("disabled",false);
        submit.removeClass('disable');
        pwReNotice.css('color','green');
    } else {
        pwReNotice.html("비밀번호 확인 불일치");
        submit.attr("disabled",true);
        submit.addClass('disable');
        pwReNotice.css('color','tomato');
    }
    
}

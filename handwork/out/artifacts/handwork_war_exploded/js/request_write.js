// 마감일자 현재날짜 이전 선택 못하도록 하는 쿼리
var localeDate = new Date().toLocaleDateString("ko-KR",{year:"numeric", month:"2-digit", day:"2-digit"});
var replaceDate = localeDate.replaceAll(".","").replaceAll(" ","-");
document.getElementById('board-write-daedline').value = replaceDate;
document.getElementById('board-write-daedline').min = replaceDate;

// 이미지 업로드 폼 동적으로 바꾸는 쿼리
function imgChange(ele) {
    var imgName = ele.files[0].name;
    var parentRow = $(ele).parent('li');


    parentRow.children(".img-path").text(imgName); 
}

function addImg() {
    var img_ul = $('#add-img-ul');
    indexReOrder();
    var index = newIndexNum();
    if(index > 7){
        alert("이미지는 최대 7개 까지 업로드 가능합니다");
    } else {
        img_ul.append('<li class="img-ul-li new-upload"><input type="file" name="img_path'+index+'" class="board-write-img hide" id="img-upload'+index+'" placeholder="이미지 업로드" accept="image/png, image/jpeg, image/jpg" onchange="imgChange(this)" required/><label for="img-upload'+index+'">업로드</label><p id="img-path'+index+'" class="img-path" >선택된 이미지 없음</p><input type="button" id="btn-del-img'+index+'" class="btn-del-img" onClick="delRow(this)"/></li>');
    }
}

function delRow(ele) {
    var parentRow = $(ele).parent('li');
    parentRow.remove();
    indexReOrder();
}

function newIndexNum() {
    var length = $('#add-img-ul').children('li.img-ul-li').length;
    return length+1;
}

function indexReOrder() {
    for(var i = 0; i<newIndexNum()-1; i++) {
        var li = $('.img-ul-li.new-upload').eq(i);
        index = i+1;
        li.children('.board-write-img').attr('name','img_path'+index);
        li.children('.board-write-img').attr('id','img_upload'+index);
        li.children('label').attr('for','img_upload'+index);
        li.children('.img-path').attr('id','img_path'+index);
        li.children('.btn-del-img').attr('id','btn-del-img'+index);
    }
}

function delFile(ele) { //이미 등록된 이미지 삭제 (del-btn.onClick)
    var img_ul = $('#add-img-ul');
    var parentRow = $(ele).parent('li');
    var inputHidden = parentRow.children('input[name="edit_filename"]');
    var del_filename;
    if(inputHidden.length){
    	//이미 요소가 존재하는 경우 (length가 0보다 크면 true)
    	del_filename = inputHidden.val().replace(/ /g,"*");	
    } else { 
    	//요소가 존재하지 않는경우
    	del_filename = parentRow.children('.img-path').text();
    }
	del_filename = del_filename.replace(/ /g,"*");
    img_ul.append('<input type="hidden" name="del_filename" value='+del_filename+'></input>');
    parentRow.remove();
}

function editFile(ele) { //이미 등록된 이미지 수정 (onChange)
    var img_ul = $('#add-img-ul');
    var parentRow = $(ele).parent('li');
    var del_filename = parentRow.children('.img-path').text();
    del_filename = del_filename.replace(/ /g,"*");
    var new_filename = ele.files[0].name;
    parentRow.children(".img-path").text(new_filename);
    var inputHidden = parentRow.children('input[name="edit_filename"]');
    if(inputHidden.length){
    	//이미 요소가 존재하는 경우 (length가 0보다 크면 true)
    	
    } else { 
    	//요소가 존재하지 않는경우
    	parentRow.append('<input type="hidden" name="edit_filename" value='+del_filename+'/>');
    }
}



// 금액 입력시 3자리 단위 콤마 입력 쿼리
function inputNumberFormat(obj) {
    var realInput = $('#real-input-price');
     obj.value = obj.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
     obj.value = comma(uncomma(obj.value));
     realInput.val(uncomma(obj.value));
 }

 function comma(str) {
     str = String(str);
     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
 }

 function uncomma(str) {
     str = String(str);
     return str.replace(/[^\d]+/g, '');
 }

$(window).load(function(){
    const json = {type:'init'};
    ajaxInit(json);
    $('.main-map-container').css('min-height','800px');
    $('.main-map-container').height(window.innerHeight-80);z
});

let initData = [];
let map;
var locationBtnHtml = '<a href="#" class="btn_mylct hide"><span class="spr_trff spr_ico_mylct hide">현재위치</span></a>';


function initMap(initLatlng){
    var latlnginit = JSON.parse(initLatlng.latlng);

    let mapOptions = {
        center: new naver.maps.LatLng(latlnginit.lat, latlnginit.lng),
        zoom: 15
    };

    map = new naver.maps.Map('map', mapOptions);

    let markers = [],
        infoWindows = [];

    for(let key in initData) {
        var latlngMarker = JSON.parse(initData[key].latlng);
        markers.push(
            new naver.maps.Marker({
                position: new naver.maps.LatLng(latlngMarker["lat"], latlngMarker["lng"]),
                map: map
            }));
        infoWindows.push();
    }

    function getClickHandler(seq) {
        const str = {"board-num":initData[seq].board_num, "type":"click"};
        return function(e) {
            if (infoWindows[seq] == null || !infoWindows[seq].getMap()) {
                var marker = markers[seq];
                var data = ajaxClick(str,markers[seq],map);
                console.log(data);
                const board_num =  data[0].board_num;
                const title =  data[0].title;
                const writer =  data[0].writer;
                const category =  data[0].category;
                const location =  data[0].location;
                const period =  data[0].period;
                const price =  data[0].price;
                const content =  data[0].content;
                const regdate =  data[0].regdate;
                const filename =  data[0].filename;
                const how =  data[0].how;
                const hits =  data[0].hit;
                const state =  data[0].state;
                const starAvg =  data[0].starAvg;

                infoWindows[seq] = new naver.maps.InfoWindow({
                    content:
                        '<div class="info-window">'+
                        '<img src="'+getContextPath()+imgInit(filename)+'" alt="" class="info-img '+isNull(filename)+'">'+
                        '<div class="wrap-left">'+
                        '   <p class="info-title">'+title+'</p>'+
                        '   <div><p class="info-price">'+price+'원</p>'+
                        '   <div class="star-wrap">'+starAvg+'</div></div>\n' +
                        '</div>',
                    backgroundColor: "#fff",
                    borderColor: "#FF6347FF",
                    borderWidth: 3,
                    anchorSize: new naver.maps.Size(30, 10),
                    anchorSkew: true,
                    anchorColor: "#fff",
                    pixelOffset: new naver.maps.Point(0, -20)
                });
                $('.detail-section').empty();
                $('.detail-section').append(
                    '<div class="wrap">\n' +
                    '                    <div class="img-container">\n' +
                    '                        <img src="" alt="" id="img-main" class="img-main '+isNull(filename)+'">\n' +
                    '                        <input type="button" value="" class="btn-img prev" onclick="btnImg(\'prev\')">\n' +
                    '                        <input type="button" value="" class="btn-img next" onclick="btnImg(\'next\')">\n' +
                    '                        <ul class="img-ul">\n'  +
                    imgListInit(filename)+
                    '                        </ul>\n' +
                    '                    </div>\n' +
                    '                    <div class="main-panel">\n' +
                    '                        <div class="item-row">\n' +
                    '                            <p href="" class="item-writer">'+writer+'</p>\n' +
                    '                            <p href="" class="item-category">'+category+'</p>\n' +
                    '                        </div>\n' +
                    '                        <p href="" class="item-title">'+title+'</p>\n' +
                    '                        <p href="" class="item-price state '+
                    stateInit(state)
                                            +'">'+price+'원</p>\n' +
                    '                        <div class="star-wrap">'+starAvg+'</div>\n' +
                    '                        <p class="item-deadline subs">'+period+'</p>\n' +
                    '                        <p href="" class="item-location subs">'+location+'</p>\n' +
                    '                        <p class="item-ship subs">'+how+'</p>\n' +
                    '                        <div class="board-info-wrap" style="display: flex; justify-self: flex-end; margin: 10px 0 0 0; !important;">\n' +
                    '                            <p class="item-hits">'+hits+'</p>\n' +
                    '                            <p class="item-writedate">'+regdate+'</p>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            <a href="'+getContextPath()+'/market/detail?id='+board_num+'" class="btn-link-board">게시글 이동하기</a>'
                );
                infoWindows[seq].open(map, marker);
                initStars();
                imgSlider($('.img-li').eq(0).children('img'));
            } else if(infoWindows[seq].getMap()){
                infoWindows[seq].close();
            }
        }
    }

    for (var i=0, ii=markers.length; i<ii; i++) {
        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
    }

    naver.maps.Event.once(map, 'init_stylemap', function () {
        //customControl 객체 이용하기
        var customControl = new naver.maps.CustomControl(locationBtnHtml, {
            position: naver.maps.Position.TOP_LEFT,
        });
        customControl.setMap(map);

        var domEventListener = naver.maps.Event.addDOMListener(customControl.getElement(), 'click', function() {
            if(navigator.geolocation){
                navigator.geolocation.getCurrentPosition(function onSuccessGeolocation(position) {
                    map.setCenter(new naver.maps.LatLng(position.coords.latitude, position.coords.longitude));
                    map.setZoom(15, true);
                    },
                    function onErrorGeolocation() {
                    alert("위치 권한을 허용 해주세요");
                });
            }
        });

    });
}


function ajaxInit(str) {
    const url = getContextPath()+"/location";

    $.ajax({
        type:"GET",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:str,
        success:function(data){
            console.log("AjaxINIT 통신 완료");
            console.log(data);
            for (let col in data){
                initData.push(data[col]);
            }
            initMap(initData[initData.length-1]);
        },
        error:function(){
            alert('Ajax 통신 에러');
        }
    })
}

function ajaxClick(str,seq,map) {
    const url = getContextPath()+"/location";
    let returnData;
    $.ajax({
        type:"GET",
        url:url,
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:str,
        success:function(data){
            console.log("AjaxCLick 통신 완료");
            console.log(data);
            returnData = data;
            // openInfoWindow(data,seq,map);
        },
        error:function(){
            alert('Ajax 통신 에러');
        }
    });
    return returnData;
}

function isNull(filename){
    if(filename == null || filename.replaceAll("/",'') == "" || !filename.includes("/")){
        return true;
    }else {
        return false;
    }
}

function imgInit(filename){
    let imgName = "";
    if(isNull(filename)){
        imgName = '/images/noImage.png';
    }else {
        imgName = '/upload/marketBoard/'+filename.split("/")[0];
    }

    return imgName;
}

function imgListInit(filenames){
    console.log(filenames);
    let result = "";
    if(isNull(filenames)){
        result += '<li class="img-li">\n' +
    '                       <img src="'+getContextPath()+'/images/noImage.png'+'" alt="" class="img-item">\n' +
    '                  </li>';
        return result;
    }

    for(let i=0; i<filenames.split('/').length-1; i++){
            result += '<li class="img-li">\n' +
    '                       <img src="'+getContextPath()+'/upload/marketBoard/'+filenames.split('/')[i]+'" alt="" class="img-item">\n' +
    '                  </li>';
    }
    return result;
}

function stateInit(state){
    if(state){
        return "out"
    }else{
        return "sell"
    }
}

function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

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

function imgSlider(ele) {
    $('.img-item').removeClass('select')
    $(ele).addClass('select');
    let imgView = $('#img-main');
    imgView.attr('src',$(ele).attr('src'));
}


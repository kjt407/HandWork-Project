$(window).load(function(){
    const json = {type:'init'};
    ajaxInit(json);
});

let initData = [];
let map;
var locationBtnHtml = '<a href="#" class="btn_mylct"><span class="spr_trff spr_ico_mylct">현재위치</span></a>';


function initMap(initLatlng){

    alert('좌표'+initLatlng.x+initLatlng.y);
    let mapOptions = {
        center: new naver.maps.LatLng(initLatlng.x, initLatlng.y),
        zoom: 15
    };

    map = new naver.maps.Map('map', mapOptions);

    let markers = [],
        infoWindows = [];

    for(let key in initData) {
        markers.push(
            new naver.maps.Marker({
                position: new naver.maps.LatLng(initData[key].x, initData[key].y),
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
                infoWindows[seq] = new naver.maps.InfoWindow({
                    content:
                        '<div style="width:150px;text-align:center;padding:10px;">'+
                        '<img src="'+getContextPath()+'/upload/'+data[0].filename.split('/')[0]+'" alt="" style="width: 50px; height: 50px;">'+
                        '<p>'+data[0].title+'</p>'+
                        '<p>'+data[0].content+'</p>'+
                        '<p>'+data[0].price+'</p>'+
                        '<p>'+data[0].state+'</p>'+
                        '<p>'+data[0].writer_id+'</p>'+
                        '<p>'+data[0].starAvg+'</p>'+
                        '</div>'
                });
                infoWindows[seq].open(map, marker);
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
            if(navigator.geolocation){
                navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
            }
        },
        error:function(){
            alert('Ajax 통신 에러');
        }
    })
}

function ajaxClick(str,seq,map) {
    const url = getContextPath()+"/location";
    let returnData;
    alert("ajax 시작");
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
            alert("ajax 종료");
            returnData = data;
            // openInfoWindow(data,seq,map);
        },
        error:function(){
            alert('Ajax 통신 에러');
        }
    });
    return returnData;
}

function openInfoWindow(data,markerParam,mapParam){
    var marker = markerParam;
    var infoWindow = new naver.maps.InfoWindow({
        content:
            '<div style="width:150px;text-align:center;padding:10px;">'+
            '<img src="'+getContextPath()+'/upload/'+data[0].filename.split('/')[0]+'" alt="" style="width: 50px; height: 50px;">'+
            '<p>'+data[0].title+'</p>'+
            '<p>'+data[0].content+'</p>'+
            '<p>'+data[0].price+'</p>'+
            '<p>'+data[0].state+'</p>'+
            '<p>'+data[0].writer_id+'</p>'+
            '<p>'+data[0].starAvg+'</p>'+
            '</div>'
    });
    if (infoWindow.getMap()) {
        infoWindow.close();
    } else {
        infoWindow.open(mapParam, marker);
    }
}

function onSuccessGeolocation(position) {
    alert('지오그레이션 성공');
    let initLatlng = {"x":position.coords.latitude,"y":position.coords.longitude};
    // var location = new naver.maps.LatLng(position.coords.latitude,
    //     position.coords.longitude);
    // map.setCenter(location);
    console.log(location);
    initMap(initLatlng);
}
function onErrorGeolocation() {
    alert("지오그레이션 실패");
    initMap(initData[initData.length-1]);
}


function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}
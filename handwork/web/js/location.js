$(window).load(function(){
    const json = {type:'init'};
    ajaxInit(json);

});

let initData = [];

function initMap(initLatlng){

    alert('좌표'+initLatlng.x+initLatlng.y);
    let mapOptions = {
        center: new naver.maps.LatLng(initLatlng.x, initLatlng.y),
        zoom: 15
    };

    let map = new naver.maps.Map('map', mapOptions);

    let markers = [],
        infoWindows = [];
    for(let key in initData) {
        markers.push(
            new naver.maps.Marker({
                position: new naver.maps.LatLng(initData[key].x, initData[key].y),
                map: map
            }));
        // var infoWindow = new naver.maps.InfoWindow({
        //     content: '<div style="width:150px;text-align:center;padding:10px;">The Letter is <b>"'+ key.substr(0, 1) +'"</b>.</div>'
        // });
        // infoWindows.push(infoWindow);
    }

    function getClickHandler(seq) {
        const data = {"board-num":initData[seq].board_num, "type":"click"};
        return function(e) {
            let result = ajaxClick(data);
            var marker = markers[seq],
                infoWindow = new naver.maps.InfoWindow({
                         content: '<div style="width:150px;text-align:center;padding:10px;">'+initData[seq].board_num+result.title+'</div>'
                     });
            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open(map, marker);
            }
        }
    }

    for (var i=0, ii=markers.length; i<ii; i++) {
        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
    }
}

// let contentString = [
//     '<div class="iw_inner">',
//     '   <h3>서울특별시청</h3>',
//     '   <p>서울특별시 중구 태평로1가 31 | 서울특별시 중구 세종대로 110 서울특별시청<br />',
//     '       02-120 | 공공,사회기관 &gt; 특별,광역시청<br />',
//     '       <a href="http://www.seoul.go.kr" target="_blank">www.seoul.go.kr/</a>',
//     '   </p>',
//     '</div>'
// ].join('');


function ajaxInit(str) {
    const url = getContextPath()+"/location";

    $.ajax({
        type:"GET",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:str,
        success:function(data){
            alert("Ajax 통신 완료"+data);
            console.log(data)
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

function ajaxClick(str) {
    const url = getContextPath()+"/location";

    $.ajax({
        type:"GET",
        url:url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:str,
        success:function(data){
            alert("Ajax 통신 완료"+data);
            console.log(data);
            return data;
        },
        error:function(){
            alert('Ajax 통신 에러');
        }
    })
}

function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
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
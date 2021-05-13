$(window).load(function(){
    const json = {
        type:'init'
    };
    ajaxGet(json);
});

let locations = [
    {'x':'37.49099729728258', 'y':'126.81345371145218'},
    {'x':'37.49099729728258', 'y':'126.81345371145218'},
    {'x':'37.3595704', 'y':'127.105399'},
    {'x':'37.2595704', 'y':'127.105399'},
    {'x':'37.1595704', 'y':'127.105399'},
    {'x':'37.0595704', 'y':'127.105399'},
    {'x':'37.9595704', 'y':'127.105399'},
    {'x':'37.8595704', 'y':'127.105399'},
    {'x':'37.7595704', 'y':'127.105399'},
]

let contentString = [
    '<div class="iw_inner">',
    '   <h3>서울특별시청</h3>',
    '   <p>서울특별시 중구 태평로1가 31 | 서울특별시 중구 세종대로 110 서울특별시청<br />',
    '       02-120 | 공공,사회기관 &gt; 특별,광역시청<br />',
    '       <a href="http://www.seoul.go.kr" target="_blank">www.seoul.go.kr/</a>',
    '   </p>',
    '</div>'
].join('');

let mapOptions = {
    center: new naver.maps.LatLng(37.49099729728258, 126.81345371145218),
    zoom: 17
};

let map = new naver.maps.Map('map', mapOptions);

let markers = [],
    infoWindows = [];
for(let key in locations) {
    markers.push(
        new naver.maps.Marker({
            position: new naver.maps.LatLng(locations[key].x, locations[key].y),
            map: map
        }));
    var infoWindow = new naver.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:10px;">The Letter is <b>"'+ key.substr(0, 1) +'"</b>.</div>'
    });
    infoWindows.push(infoWindow);
}

function getClickHandler(seq) {
    return function(e) {
        var marker = markers[seq],
            infoWindow = infoWindows[seq];

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

function ajaxGet(str) {
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

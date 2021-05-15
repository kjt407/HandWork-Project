<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/location.css">
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ff931k1pgc"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <title>Title</title>
</head>
<body>
    <h1 class="hide">수제공방</h1>
    <header>
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </header>
    <section class="market board-section">
        <h2 class="hide">수제공방 게시판</h2>
        <div class="main-map-container" style="display: flex; width: 100%;">
            <div id="map"></div>
            <div class="detail-section">
<%--                <div class="init-container" style="display: flex; justify-content: center; width:100%; height:100%; align-items: center; ">--%>
<%--                    <p style="color: black">원하는 지역의 마커를 눌러주세요<br>이곳에 정보가 표시됩니다</p>--%>
<%--                </div>--%>
                <div class="wrap">
                    <div class="img-container">
                        <img src="" alt="" id="img-main">
                        <input type="button" value="" class="btn-img prev" onclick="btnImg('prev')">
                        <input type="button" value="" class="btn-img next" onclick="btnImg('next')">
                        <ul class="img-ul">
                            <%--최대 7개 까지만 업로드 하도록--%>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/item.png" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/carousel_bg3.jpg" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/item.png" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/carousel_bg3.jpg" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/item.png" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/carousel_bg3.jpg" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                        </ul>
                    </div>
                    <div class="main-panel">
                        <div class="item-row">
                            <a href="" class="item-writer">촤콜렛하우스</a>
                            <a href="" class="item-category">식품/잡화</a>
                        </div>
                        <p href="" class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛 </p>
                        <p href="" class="item-price state sell">15000원</p>
                        <div class="star-wrap">2</div>
                        <p class="item-deadline subs">7</p>
                        <a href="" class="item-location subs">서울특별시</a>
                        <p class="item-ship subs">택배+방문수령</p>
                        <div class="board-info-wrap" style="display: flex; justify-self: flex-end; margin: 10px 0 0 0; !important;">
                            <p class="item-hits">11</p>
                            <p class="item-writedate">2021.05.11</p>
                        </div>
                    </div>
                </div>
            <a href="" class="btn-link-board" style="display: flex; justify-content: center; align-items: center; width: 100%; background: tomato; padding: 20px; font-size: 18px; color: white;">게시글 이동하기</a>
<%--                    여기까지--%>
            </div>
        </div>
    </section>
    <footer>
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/location.js"></script>
</body>
</html>
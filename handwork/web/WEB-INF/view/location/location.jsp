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
    <title>핸드워크:주변공방</title>
</head>
<body>
    <h1 class="hide">수제공방</h1>
    <header>
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </header>
    <section class="market board-section">
        <h2 class="hide">수제공방 주변공방</h2>
        <div class="main-map-container" style="display: flex; width: 100%;">
            <div id="map"></div>
            <div class="detail-section">
                <div class="init-container">
                    <img src="${pageContext.request.contextPath}/images/location_detail.png" alt="">
                    <p>지도의 마커를 클릭 해주세요<br>이곳에 정보가 표시됩니다</p>
                </div>
            </div>
        </div>
    </section>
    <footer>
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/location.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.handwork.request.entity.Request"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
    <title>핸드워크: 수제공방</title>
</head>
<body>
<h1 class="hide">수제공방</h1>
    <header>
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </header>

    <section id="main-section">
        <div class="wrap-main">
            <section id="my-info-section">
                <p class="section-title">내정보</p>
                <img src="${pageContext.request.contextPath}/images/market_background.jpg" alt="" id="img-profile">
                <p class="info-name">김종태</p>
            </section>
            <section id="my-log-section">
                <p class="section-title">활동로그</p>
                <div class="log-container board">
                    <p class="list-title board">작성한 게시글</p>
                    <ul class="log-ul board">
                        <li class="log-li board">
                            <a href="">
                                <p class="log-li-board board">수제공방</p>
                                <p class="log-li-title board">제목입니다</p>
                                <p class="log-li-date board">제목입니다</p>
                            </a>
                        </li>
                    </ul>
                    <input type="button" value="+" class="btn-more" id="btn-more-board">
                </div>
                <div class="log-container reply">
                    <p class="list-title reply">작성한 댓글/리뷰</p>
                    <ul class="log-ul reply">
                        <li class="log-li reply">
                            <a href="">
                                <p class="log-li-board reply">제작의뢰</p>
                                <p class="log-li-title reply">제목입니다</p>
                                <p class="log-li-date reply">제목입니다</p>
                            </a>
                        </li>
                    </ul>
                    <input type="button" value="+" class="btn-more" id="btn-more-reply">
                </div>
            </section>
        </div>
    </section>

    <footer>
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
</footer>
</body>
</html>

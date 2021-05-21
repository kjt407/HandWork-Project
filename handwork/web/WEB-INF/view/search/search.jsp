<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.handwork.request.entity.Request"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/search.js"></script>
    <title>핸드워크</title>
</head>
<body>
<h1 class="hide">핸드워크</h1>
<header>
    <jsp:include page="/WEB-INF/view/header.jsp"/>
</header>
<section class="board-title-section">
    <div>"asfasdfsad"를 검색하신 결과입니다.</div>
</section>

<section class="search board-section">
    <h2 class="hide">제작의뢰 게시판</h2>
    <div class="board-container">
        <section id="search-market">
            <p class="section-title market">수제공방</p>
            <div class="board-content-list market">
            </div>
        </section>
        <section id="search-request">
            <p class="section-title request">제작의뢰</p>
            <ul class="board-content-list request">
            </ul>
        </section>
    </div>
</section>
<footer>
    <jsp:include page="/WEB-INF/view/footer.jsp"/>
</footer>
</body>
</html>
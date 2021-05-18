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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <title>핸드워크</title>
</head>
<body>
<h1 class="hide">핸드워크</h1>
<header>
    <jsp:include page="/WEB-INF/view/header.jsp"/>
</header>


<section class="request board-section">
    <h2 class="hide">제작의뢰 게시판</h2>
    <div class="board-container">
        <%--게시판 목록 include--%>
        <div class="board-main request-main">
            <h3 class="hide">게시판 메인</h3>
            <ul class="board-content-list">
                <c:forEach var="r" items="${list}">
                    <li>
                        <div class="board-items">

                            <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="img-container">
                                <c:choose>
                                    <c:when test="${empty r.filename }">
                                        <img src="${pageContext.request.contextPath}/images/noImage.png" class="view-img no-img">
                                    </c:when>
                                    <c:when test="${r.filename eq '/'}">
                                        <img src="${pageContext.request.contextPath}/images/noImage.png" class="view-img no-img">
                                    </c:when>

                                    <c:otherwise>
                                        <c:set var="doneLoop" value="false"/>
                                        <c:forTokens var="itemFN" items="${r.filename}" delims="/">
                                            <c:if test="${not doneLoop}">
                                                <img src="${pageContext.request.contextPath }/upload/requestBoard/${itemFN}" class="view-img">

                                                <c:set var="doneLoop" value="true"/>
                                            </c:if>
                                        </c:forTokens>


                                    </c:otherwise>
                                </c:choose>

                            </a>
                            <div class="board-list-main">
                                <div class="row-wrapper">
                                    <a href="" class="board-list-category">${r.kategorie}</a>
                                    <a href="" class="board-list-locale">${r.location}</a>
                                </div>
                                <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="board-list-title">${r.title}</a>
                                <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="board-list-subs">${r.content}</a>
                                <div class="row-wrapper">
                                    <c:choose>
                                        <c:when test="${r.state eq 0}">
                                            <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="board-list-price state auction">${r.price}원</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="board-list-price state complete">${r.price}원</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <div>
                                        <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="board-list-writer">${r.writer}</a>
                                        <p class="board-list-writedate">${fn:substring(r.regdate, 0, 16)}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="board-list-aside">
                                <p class="board-list-deadline">${r.deadline}</p>
                                <span class="board-list-comment">${r.count}</span>
                                <span class="board-list-views">${r.hit}</span>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</section>
<footer>
    <jsp:include page="/WEB-INF/view/footer.jsp"/>
</footer>
</body>
</html>
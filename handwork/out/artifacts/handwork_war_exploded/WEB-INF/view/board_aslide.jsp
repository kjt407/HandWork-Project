<%--
  Created by IntelliJ IDEA.
  User: kjt407
  Date: 2021-05-07
  Time: 오후 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="board-aside request-aside">
    <h3 class="hide">게시판 목록</h3>
    <p class="board-list-title">게시판</p>
    <p class="title-deco">HandWork</p>
    <ul class="board-list">
        <c:set var="context" value="${ pageContext.request.servletPath }"/>
            <li <c:if test="${fn:contains(context, '/board/market')}"> class="crt-page" </c:if> > <a href="">수제공방</a></li>
            <li <c:if test="${fn:contains(context, '/board/request')}"> class="crt-page" </c:if> > <a href="${pageContext.request.contextPath}/request">제작의뢰</a></li>
            <li <c:if test="${fn:contains(context, '/board/notice')}"> class="crt-page" </c:if> > <a href="">공지사항</a></li>
            <li <c:if test="${fn:contains(context, '/board/review')}"> class="crt-page" </c:if> > <a href="">리뷰게시판</a></li>
            <li <c:if test="${fn:contains(context, '/board/qna')}"> class="crt-page" </c:if> > <a href="">문의게시판</a></li>
    </ul>
    <div class="filter">
        <p>FILTER</p>
    </div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.handwork.free.entity.Free"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel=" shortcut icon" href="${pageContext.request.contextPath}/images/handwork-fav.ico">
    <link rel="icon" href="${pageContext.request.contextPath}/images/handwork-fav.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/free.css">
    <script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
        src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <title>핸드워크: 자유게시판</title>
</head>
<body>
    <h1 class="hide">핸드워크</h1>
    <header>
		<jsp:include page="/WEB-INF/view/header.jsp"/>
	</header>


    <section class="board-title-section">
        <div>자유 게시판</div>
    </section>

    <section class="request board-section">
        <h2 class="hide">자유 게시판</h2>
        <div class="board-bg">
            <div class="left"></div>
            <div class="right"></div>
        </div>
        <div class="board-container">
            <%--게시판 목록 include--%>
            <jsp:include page="/WEB-INF/view/board_aslide.jsp"/>
            <div class="board-main request-main">
               <h3 class="hide">게시판 메인</h3>
                <form action="" class="board-search">
                    <select name="f">
                        <option ${(param.f == "title")?"selected":""} value="title">제목</option>
						<option ${(param.f == "writer")?"selected":""} value="writer">작성자</option>
					</select>
                    <input type="text" class="search-text" name="q" value="${param.q}">
                    <input type="submit" class="search-submit" value="검색">
                </form>



                <%--                        <div class="board-items">--%>
                <%--                            <div class="board-list-main">--%>
                <%--                                <a href="${pageContext.request.contextPath}/free/detail?id=${r.id}" class="board-list-title">${r.title}</a>--%>
                <%--                                <a href="${pageContext.request.contextPath}/free/detail?id=${r.id}" class="board-list-subs">${r.content}</a>--%>
                <%--                                <div class="row-wrapper">--%>
                <%--                                    <div>--%>
                <%--                                        <a href="${pageContext.request.contextPath}/free/detail?id=${r.id}" class="board-list-writer">${r.writer}</a>--%>
                <%--                                        <p class="board-list-writedate">${fn:substring(r.regdate, 0, 16)}</p>--%>
                <%--                                    </div>--%>
                <%--                                </div>--%>
                <%--                            </div>--%>
                <%--                            <div class="board-list-aside">--%>
                <%--                                <span class="board-list-comment">${r.count}</span>--%>
                <%--                                <span class="board-list-views">${r.hit}</span>--%>
                <%--                            </div>--%>
                <%--                        </div>--%>
				 <ul class="board-content-list">
				 	<c:forEach var="r" items="${list}">
                    <li class="notice-content-li" onclick="location.href='${pageContext.request.contextPath}/free/detail?id=${r.id}'">
                            <div class="notice-title">
                                <div class="list-row title">
                                    <p class="board-list-title">${r.title}</p><span class="board-list-comment">${r.count}</span>
                                </div>
                                <div class="list-row">
                                    <p class="board-list-writer">${r.writer}</p>
                                    <div style="display: flex">
                                        <p class="board-list-hit">${r.hit}</p>
                                        <p class="board-list-writedate">${fn:substring(r.regdate, 0, 16)}</p>
                                    </div>
                                </div>
                            </div>
                    </li>
                    </c:forEach>
                </ul>
                
                <c:set var="page" value="${(empty param.p) ? 1:param.p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum"
					value="${fn:substringBefore(Math.ceil(count/5),'.')}" />
				
                
				<div class="foot-menu">

					<ul class="board-pager">
					<c:if test="${startNum-1>0}">
							<a href="?p=${startNum-1}&t=&q=" class="btn btn-prev">이전</a>
						</c:if>
						<c:if test="${startNum-1<=1}">
							<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
						</c:if>

						<c:forEach var="i" begin="0" end="4">
							<c:if test="${(startNum+i)<=lastNum }">
								<li><a
									class="-text- ${(page==(startNum+i))?'on':''} bold"
									href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${startNum+4<lastNum}">
							<a href="?p=${startNum+5}&t=&q=" class="btn btn-next">다음</a>
						</c:if>
						<c:if test="${startNum+4>=lastNum}">
							<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
						</c:if>

					</ul>
                   
                    <c:choose>
				<c:when test="${empty id}">

					 <a onclick="alert('로그인을 해주세요');" class="btn-write">글작성</a>

                </c:when>
				<c:otherwise>
			
				 <a href="${pageContext.request.contextPath}/free/write" class="btn-write">글작성</a>
				</c:otherwise>
			</c:choose> 
				</div>
                
            </div>
        </div>
    </section>
    <footer>
    	<jsp:include page="/WEB-INF/view/footer.jsp"/>
    </footer>
</body>
</html>
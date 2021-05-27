<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel=" shortcut icon" href="${pageContext.request.contextPath}/images/handwork-fav.ico">
    <link rel="icon" href="${pageContext.request.contextPath}/images/handwork-fav.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board-write.css">
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
    <section class="board-title-section">
        <div>공지사항 게시판</div>
    </section>

    <section class="request board-section">
        <h2 class="hide">제작의뢰 게시판</h2>
        <div class="board-bg">
            <div class="left"></div>
            <div class="right"></div>
        </div>
        <div class="board-container">
            <%--게시판 목록 include--%>
            <jsp:include page="/WEB-INF/view/board_aslide.jsp"/>

			<c:choose>
				<c:when test="${empty r.id}">
				<%-- ${r.id}write --%>
					<form class="board-main request-write-main" action="write" method="post">
				</c:when>
				<c:otherwise>
				<%-- ${r.id}update --%>
					<form class="board-main request-write-main" action="update" method="post">
				</c:otherwise>
			</c:choose> 
			
            	<input type="hidden" name="id" value="${r.id}"/>

                <h3 class="hide">게시글 작성페이지</h3>
                <p class="board-write-header-title">공지사항 글쓰기</p>
                <div class="board-write-header">
                    <div class="board-write-row">
                        <div class="board-write-col" style="width: 20%;">
                            <p class="input-label">구분</p>
                            <select name="category" id="category-select" class="category-select" class="board-write-category">
                                <option value="필독" selected>필독</option>
                                <option value="중요">중요</option>
                                <option value="일반">일반</option>
                            </select>
                        </div>
                        <div class="board-write-col">
                            <p class="input-label">글 제목</p>
                            <input type="text" name="title" class="board-write-title" value="${r.title}"placeholder="제목을 입력해 주세요" maxlength="40" required/>
                        </div>
                    </div>    


                    <textarea id="subs" cols="30" rows="10" name="content" class="board-write-subs" placeholder="공지사항 작성하기" required>${r.content}</textarea>
                    <div class="btn-submit-group">
                        <input type="submit" class="btn-submit submit" value="작성완료"/>
                        <c:choose>
                       <c:when test="${empty r.id }">
                          <a href="${pageContext.request.contextPath}/notice" class="btn-submit cancel" >취소</a>
                       </c:when>
                       <c:otherwise>
                          <a href="${pageContext.request.contextPath}/notice/detail?id=${r.id}"  class="btn-submit cancel" >취소</a>
                       </c:otherwise>
                    </c:choose> 
                    </div>
                </div>
            </form>
        </div>
    </section>
    <footer>
    	<jsp:include page="/WEB-INF/view/footer.jsp"/>
    </footer>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/request_write.js"></script>

</body>
</html>
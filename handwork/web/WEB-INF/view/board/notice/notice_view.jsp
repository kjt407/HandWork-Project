<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Date" %>
<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
      pageContext.setAttribute("br", "<br/>"); //br 태그
%> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_view.css">
    <script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
        src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/request_view.js"></script>

    <title>핸드워크</title>
</head>
<body>

    <input type="hidden" id="board-id" value="${r.id}">
    <input type="hidden" id="board-writer-id" value="${r.writer_id}">
    <input type="hidden" id="session-id" value="${id}">

    <h1 class="hide">핸드워크</h1>
    <header>
      <jsp:include page="/WEB-INF/view/header.jsp"/>
    </header>

    <section class="board-title-section">
        <div>제작의뢰 게시판</div>
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
            <div class="board-main request-view-main">
                <h3 class="hide">게시글 상세페이지</h3>
                <div class="request-view-header">
                    <p class="board-view-title">${r.title}</p>
                    <div class="header-row">
                        <div class="writer-info">
                            <a href="">${r.writer}</a>
                        </div>
                        <div class="post-info">
                            <p class="board-view-views">${r.hit}</p>
                            <p class="board-view-writedate">${r.regdate}</p>
                        </div>
                    </div>
                </div>
                <div class="request-view-body">
                    <div class="body-row">
                        <span class="sub-title">의뢰사항 :</span>
                        <p class="subs">${fn:replace(r.content, crcn, br)}</p>
                    </div>
                </div>      
                <div class="request-view-control">
             
                
                    <div class="control-row btn-group">
                       <c:choose>
							<c:when test="${id eq 'HANDWORK'}">
								<a href="${pageContext.request.contextPath}/notice/update?id=${r.id}" class="btn-edit" >수정</a>
                        		<a href="${pageContext.request.contextPath}/notice/delete?id=${r.id}" class="btn-edit"}>삭제</a>
			                </c:when>
							<c:otherwise>
								<a onclick="alert('관리자만 수정 및 삭제를 할 수 있습니다.');" class="btn-edit" >수정</a>
                        		<a onclick="alert('관리자만 수정 및 삭제를 할 수 있습니다.');" class="btn-edit"}>삭제</a>
							</c:otherwise>
						</c:choose>
                        <a href="${pageContext.request.contextPath}/notice" class="btn-list">목록으로</a>
                    </div>

                
                    <div class="prev-next-group">
                        <c:choose>
                           <c:when test="${empty nr.id}">
                           </c:when>
                           <c:otherwise>
                              <a href="${pageContext.request.contextPath}/notice/detail?id=${nr.id}" class="control-board-items board-next">
                              <p class="control-col">다음글</p>

                              <div class="control-col title-subs">
                                  <p class="view-control-title">${nr.title}</p>
                              </div>
                              <p class=" view-control-writer board-list-writer control-col">${nr.writer}</p>
                              </a>
                        
                           </c:otherwise>
                        </c:choose>
                        
                        <c:choose>
                           <c:when test="${empty pr.id}">
                           </c:when>
                           <c:otherwise>
                              <a href="${pageContext.request.contextPath}/notice/detail?id=${pr.id}" class="control-board-items board-next">
                              <p class="control-col">이전글</p>
                              <div class="control-col title-subs">
                                  <p class="view-control-title">${pr.title}</p>
                              </div>
                              <p class=" view-control-writer board-list-writer control-col">${pr.writer}</p>
                              </a>
                           </c:otherwise>
                        </c:choose>           
                     </div>
                  </div>


            </div>
        </div>
    </section>
    <footer>
    	<jsp:include page="/WEB-INF/view/footer.jsp"/>
    </footer>
</body>
</html>
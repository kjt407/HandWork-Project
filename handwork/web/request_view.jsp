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
    <input type="hidden" id="board-state" value="${r.state}">
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
            <div class="board-aside request-aside">
                <h3 class="hide">게시판 목록</h3>
                <p class="board-list-title">게시판</p>
                <p class="title-deco">HandWork</p>
                <ul class="board-list">
                    <li><a href="">수제공방</a></li>
                    <li class="crt-page"><a href="/request">제작의뢰</a></li>
                    <li><a href="">공지사항</a></li>
                    <li><a href="">공지사항</a></li>
                    <li><a href="">리뷰게시판</a></li>
                    <li><a href="">문의게시판</a></li>
                </ul>
                <div class="filter">
                    <p>FILTER</p>
                </div>
            </div>
            <div class="board-main request-view-main">
                <h3 class="hide">게시글 상세페이지</h3>
                <div class="request-view-header">
                    <p class="board-view-category">${r.kategorie}</p>
                    <p class="board-view-title">${r.title}</p>
                    <div class="header-row">
                        <div class="writer-info">
                            <img src="${pageContext.request.contextPath}/images/noProfile.png" alt="작성자 프로필">
                            <a href="">${r.writer}</a>
                        </div>
                        <div class="post-info">
                            <p class="board-view-comment">${r.count}</p>
                            <p class="board-view-views">${r.hit}</p>
                            <p class="board-view-writedate">${r.regdate}</p>
                        </div>
                    </div>
                </div>
                <div class="request-view-body">
                    <div class="body-row">
                        <div class="image-slide-main">
                        
                        <c:forTokens var="itemFN" items="${r.filename}" delims="/">
                        <%-- 	${itemFN} --%>
                      		<div><img src="${pageContext.request.contextPath }/upload/${itemFN}" class="view-img"></div>
                        </c:forTokens>
                            
                        </div>
                    </div>
                    <div class="body-row">
                        <span class="body-price sub-title">희망가격 :</span><p class="subs">${r.price}</p>
                    </div>
                    <div class="body-row">
                        <span class="body-price sub-title">마감일자 :</span><p class="subs">${r.deadline}</p>
                    </div>
                    <div class="body-row">
                        <span class="body-location sub-title">수령지역 :</span><p class="subs">${r.location}</p>
                    </div>
                    <div class="body-row">
                        <span class="body-shipping sub-title">수령방법 :</span><p class="subs">${r.how}</p>
                    </div>
                    <div class="body-row">
                        <span class="sub-title">의뢰사항 :</span>
                        <p class="subs">${fn:replace(r.content, crcn, br)}</p>
                    </div>
                </div>      
                <div class="request-view-control">
             
                
                    <div class="control-row btn-group">
                       <c:choose>
							<c:when test="${id eq r.writer_id}">
							
								<a href="update?id=${r.id}" class="btn-edit" >수정</a>
                        		<a href="delete?id=${r.id}" class="btn-edit"}>삭제</a>
			               
			                </c:when>
							<c:otherwise>
								
								<a href="../plzWriter.jsp" class="btn-edit" >수정</a>
                        		<a href="../plzWriter.jsp" class="btn-edit"}>삭제</a>
                        	 
							
							</c:otherwise>
						</c:choose> 
                        
                        <a href="/request" class="btn-list">목록으로</a>
                    </div>

                
                    <div class="prev-next-group">
                        <c:choose>
                           <c:when test="${empty nr.id}">
                           </c:when>
                           <c:otherwise>
                              <a href="detail?id=${nr.id}" class="control-board-items board-next">
                              <p class="control-col">다음글</p>
                                 <div class="img-wrap">
                                  <c:choose>
                                    <c:when test="${empty nr.filename }">
                                       <img src="${pageContext.request.contextPath}/images/noImage.png" class="view-img" style="width: 50px; height:50px;">
                           
                                    </c:when>
                           
                                    <c:otherwise>
                                       <c:set var="doneLoop" value="false"/>
                                          <c:forTokens var="itemFN" items="${nr.filename}" delims="/">
                                             <c:if test="${not doneLoop}">
                                                <img src="${pageContext.request.contextPath }/upload/${itemFN}" class="view-img">
                              
                                                <c:set var="doneLoop" value="true"/>
                                             </c:if>
                                          </c:forTokens>
                              
                              
                                    </c:otherwise>
                                 </c:choose>
                              </div>
                              <div class="control-col title-subs">
                                  <p class="view-control-title">${nr.title}</p>
                              </div>
                              <p class="view-control-price board-list-price state complete control-col">${nr.price}</p>
                              <p class=" view-control-writer board-list-writer control-col">${nr.writer}</p>
                              </a>
                        
                           </c:otherwise>
                        </c:choose>
                        
                        <c:choose>
                           <c:when test="${empty pr.id}">
                           </c:when>
                           <c:otherwise>
                              <a href="detail?id=${pr.id}" class="control-board-items board-next">
                              <p class="control-col">이전글</p>
                                 <div class="img-wrap">
                     
                                 <c:choose>
                                    <c:when test="${empty pr.filename }">
                                       <img src="${pageContext.request.contextPath}/images/noImage.png" class="view-img" style="width: 50px; height:50px;">
                           
                           
                                    </c:when>
                           
                                    <c:otherwise>
                                       <c:set var="doneLoop" value="false"/>
                                          <c:forTokens var="itemFN" items="${pr.filename}" delims="/">
                                             <c:if test="${not doneLoop}">
                                                <img src="${pageContext.request.contextPath }/upload/${itemFN}" class="view-img">
                           
                                                <c:set var="doneLoop" value="true"/>
                                             </c:if>
                                          </c:forTokens>
                                       </c:otherwise>
                                 </c:choose>
                     
                              </div>
                              <div class="control-col title-subs">
                                  <p class="view-control-title">${pr.title}</p>
                              </div>
                              <p class="view-control-price board-list-price state complete control-col">${pr.price}</p>
                              <p class=" view-control-writer board-list-writer control-col">${pr.writer}</p>
                              </a>
                           </c:otherwise>
                        </c:choose>           
                     </div>
                  </div>

<%--                댓글 작성하는 폼 부분--%>
                <div class="request-view-comment">
                    <p id="comment-ul-title">제안댓글</p>
                    <ul class="comment-ul" id="comment-ul">
                        <%--AJAX 스크립트 처리--%>
                    </ul>
                    <c:choose>
                        <c:when test="${r.state eq 1}">
                            <p style="display: flex; justify-content: center; align-content: center; background: whitesmoke; padding: 60px 0; font-size: 25px; width: 100%">채결 완료되어 댓글을 작성 할 수 없습니다</p>
                        </c:when>
                        <c:otherwise>
                            <div class="comment-write">
                                <div class="comment-write-row">
                                    <p>제안하기</p>
                                    <div class="suggest-price-group">
                                        <p>제안가격 : </p>
                                        <input type="number" class="suggest-price" id="suggest-price" placeholder="제안가격">
                                    </div>
                                </div>
                                <div class="comment-write-row write">
                                    <textarea name="suggest_comment" id="suggest-comment" required></textarea>
                                    <c:choose>
                                        <c:when test="${id eq null}">
                                            <input type="button" value="등 록" id="comment-register-btn" onclick="plzLogin()">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="button" value="등 록" id="comment-register-btn" onclick="commentRegister(&#34 ${id} &#34,${r.id })">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
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
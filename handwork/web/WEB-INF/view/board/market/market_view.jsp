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
    <link rel=" shortcut icon" href="${pageContext.request.contextPath}/images/handwork-fav.ico">
    <link rel="icon" href="${pageContext.request.contextPath}/images/handwork-fav.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_view.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/market_view.css">
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/market_view.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

    <title>핸드워크: 수제공방</title>
</head>
<body>
<h1 class="hide">핸드워크</h1>
<header>
    <jsp:include page="/WEB-INF/view/header.jsp"/>
</header>

<section class="board-title-section">
    <div>수제공방 게시판</div>
</section>
<section class="market board-section">
    <h2 class="hide">수제공방 게시판</h2>
    <div class="board-bg">
        <div class="left"></div>
        <div class="right"></div>
    </div>
    <div class="board-container">
        <jsp:include page="/WEB-INF/view/board_aslide.jsp"/>
        <div class="board-main market-view-main">
            <h3 class="hide">게시글 상세페이지</h3>
            <div class="market board-header">
                <div class="img-container">
                    <c:choose>
                        <c:when test="${empty r.filename }">
                            <img src="${pageContext.request.contextPath}/images/noImage.png" alt="" id="img-main" class="img-main no-img">
                        </c:when>
                        <c:when test="${r.filename eq '/'}">
                            <img src="${pageContext.request.contextPath}/images/noImage.png" alt="" id="img-main" class="img-main no-img">
                        </c:when>
                        <c:otherwise>
                                <img src="" alt="" id="img-main">
                        </c:otherwise>
                    </c:choose>
                    <input type="button" value="" class="btn-img prev" onclick="btnImg('prev')">
                    <input type="button" value="" class="btn-img next" onclick="btnImg('next')">
                    <ul class="img-ul">
                        <%--최대 7개 까지만 업로드 하도록--%>
                        <c:forTokens var="itemFN" items="${r.filename}" delims="/">
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath }/upload/marketBoard/${itemFN}" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                        </c:forTokens>
                    </ul>
                </div>
                <div class="main-panel-wrap">
                    <div class="main-panel">
                        <div class="item-row">
                            <p href="" class="item-writer">${r.writer}</p>
                            <a href="${pageContext.request.contextPath }/search?c=${r.kategorie}" class="item-category">${r.kategorie}</a>
                        </div>
                        <p href="" class="item-title">${r.title}</p>
                        <c:choose>
                            <c:when test="${r.state eq 0}">
                                <p href="" class="item-price state sell">${r.price}원</p>
                            </c:when>
                            <c:otherwise>
                                <p href="" class="item-price state out">${r.price}원</p>
                            </c:otherwise>
                        </c:choose>
                        <div class="star-wrap">${r.starAvg}</div>
                        <p class="item-deadline subs">${r.period}</p>
                        <p href="" class="item-location subs">${r.location}</p>
                        <p class="item-ship subs">${r.how}</p>

                        <c:choose>
                            <c:when test="${r.state eq 1}">
                                <input type="button" class="btn-contact" onclick="alert('품절된 상품입니다');" value="판매자와 연락하기" disabled></input>
                            </c:when>
                            <c:when test="${empty id}">
                                <input type="button" class="btn-contact" onclick="alert('로그인을 해주세요');" value="판매자와 연락하기"></input>
                            </c:when>
                            <c:when test="${id eq r.writer_id}">
                                <input type="button" class="btn-contact" onclick="alert('본인이 작성한 게시글입니다');" value="판매자와 연락하기"></input>
                            </c:when>
                            <c:otherwise>
                                <div id="email-send-wrap">
                                    <input type="button" class="btn-contact" value="판매자와 연락하기" onclick="sendEmail(this)">
                                    <input type="hidden" name="to" value="wnsgudchl0302@naver.com">
                                    <input type="hidden" name="from" value="ambirion0302@gmail.com">
                                    <input type="hidden" name="title" value="${r.title}">
                                    <input type="hidden" name="content" value="${r.content}">
                                    <input type="hidden" name="name" value="${r.writer}">
                                    <input type="hidden" name="writer_id" value="${r.writer_id}">
                                    <input type="hidden" name="price" value="${r.price}">
                                    <input type="hidden" name="board-num" value="${r.id}">
                                    <input type="hidden" name="toId" value="${id}">
                                </div>

                            </c:otherwise>
                        </c:choose>


                        <div class="board-info-wrap" style="display: flex; justify-self: flex-end; margin: 10px 0 0 0; !important;">
                            <p class="item-hits">${r.hit}</p>
                            <p class="item-writedate">${r.regdate}</p>
                        </div>
                    </div>

                    <div class="control-row btn-group">
                        <c:choose>
                            <c:when test="${id eq r.writer_id}">
                                <a href="${pageContext.request.contextPath}/market/update?id=${r.id}" class="btn-edit" >수정</a>
                                <a href="${pageContext.request.contextPath}/market/delete?id=${r.id}" class="btn-edit"}>삭제</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="alert('게시글 작성자만 수정 및 삭제를 할 수 있습니다.');" class="btn-edit" >수정</a>
                                <a onclick="alert('게시글 작성자만 수정 및 삭제를 할 수 있습니다.');" class="btn-edit"}>삭제</a>
                            </c:otherwise>
                        </c:choose>
                        <a href="${pageContext.request.contextPath}/market" class="btn-list">목록으로</a>
                    </div>
                </div>
            </div>
            <div class="content-section market">
                <p class="content-title section-title">제품 상세정보</p>
                <p class="content-view">${fn:replace(r.content, crcn, br)}</p>
            </div>
            <div class="review-section market">
                <div class="section-title">
                    <div class="title-wrap">
                        <span class="review-title">구매후기 (${r.count})</span>
                        <c:choose>
                            <c:when test="${empty id}">
                                <input type="button" value="+ 리뷰작성" class="btn-write-review" onclick="alert('로그인을 해주세요');"></input>
                            </c:when>
                            <c:when test="${id eq r.writer_id}">
                                <input type="button" value="+ 리뷰작성" class="btn-write-review" onclick="alert('작성자는 리뷰를 남길 수 없습니다');"></input>
                            </c:when>
                            <c:otherwise>
                                <input type="button" value="+ 리뷰작성" class="btn-write-review" onclick="btnWriteReview(this,${r.id})"></input>
                            </c:otherwise>
                        </c:choose>


                    </div>
                </div>

                <ul class="review-ul">
                    <c:forEach var="re" items="${reviewsList}">
                    <li class="review-li">
                        <div class="review-row">
                            <div class="user-info-wrap">
                                <c:choose>
                                    <c:when test="${empty re.profile_img}">
                                        <img src="${pageContext.request.contextPath}/images/noProfile.png" alt="리뷰 작성자 프로필" class="user-img">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/upload/profile/${re.profile_img}" alt="작성자 프로필" class="user-img">
                                    </c:otherwise>
                                </c:choose>
                                <div class="write-info">
                                    <span class="user-name" >${re.name}</span>
                                    <span class="user-date" >${re.regdate}</span>
                                </div>
                            </div>
                            <div class="star-wrap">${re.star}</div>
                        </div>
                        <div class="review-row">
                            <p class="review-subs">${re.content}</p>
                            <div class="review-control">
                                <c:choose>
                                    <c:when test="${id eq re.user_id}">

                                        <input type="button" onclick="reviewEdit(this, ${r.id}, ${re.idx})" value="수정">

                                        <form action="/handwork/review/delete" method="post">
                                            <input type="hidden" name="board-num" value="${r.id}">
                                            <input type="hidden" name="idx" value="${re.idx}">
                                            <input type="submit"  value="삭제">
                                       </form>

                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" onclick="alert('게시글 작성자만 수정 및 삭제를 할 수 있습니다.');" value="수정">
                                        <input type="button" onclick="alert('게시글 작성자만 수정 및 삭제를 할 수 있습니다.');" value="삭제">
                                    </c:otherwise>
                                </c:choose>

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
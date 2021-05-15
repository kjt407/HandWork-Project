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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/market_view.css">
    <script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
        src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/market_view.js"></script>

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
                        <img src="" alt="" id="img-main">
                        <input type="button" value="" class="btn-img prev" onclick="btnImg('prev')">
                        <input type="button" value="" class="btn-img next" onclick="btnImg('next')">
                        <ul class="img-ul">
                        <%--최대 7개 까지만 업로드 하도록--%>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/item.png" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/carousel_bg3.jpg" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/item.png" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/carousel_bg3.jpg" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/item.png" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                            <li class="img-li">
                                <img src="${pageContext.request.contextPath}/images/carousel_bg3.jpg" alt="" class="img-item" onmouseover="sliderHover(this)">
                            </li>
                        </ul>
                    </div>
                    <div class="main-panel">
                        <div class="item-row">
                            <a href="" class="item-writer">촤콜렛하우스</a>
                            <a href="" class="item-category">식품/잡화</a>
                        </div>
                        <p href="" class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛 </p>
                        <p href="" class="item-price state sell">15000원</p>
                        <div class="star-wrap">2</div>
                        <p class="item-deadline subs">7</p>
                        <a href="" class="item-location subs">서울특별시</a>
                        <p class="item-ship subs">택배+방문수령</p>
                        <input type="button" class="btn-contact" value="판매자와 연락하기">
                        <div class="board-info-wrap" style="display: flex; justify-self: flex-end; margin: 10px 0 0 0; !important;">
                            <p class="item-hits">11</p>
                            <p class="item-writedate">2021.05.11</p>
                        </div>
                    </div>
                </div>
                <div class="content-section market">
                    <p class="content-title section-title">제품 상세정보</p>
                    <p class="content-view">상세한 내용이 들어가야제 상세한 내용이 들어가야제 상세한 내용이 들어가야제 상세한 내용이 들어가야제 상세한 내용이 들어가야제 상세한 내용이 들어가야제</p>
                </div>
                <div class="review-section market">
                    <div class="section-title">
                        <div class="title-wrap">
                            <span class="review-title">구매후기 (12)</span>
                            <input type="button" value="+ 리뷰작성" class="btn-write-review" onclick="btnWriteReview(this)"></input>
                        </div>
                    </div>
                    <ul class="review-ul">
                        <li class="review-li">
                            <div class="review-row">
                                <div class="user-info-wrap">
                                    <img src="${pageContext.request.contextPath}/images/noProfile.png" alt="리뷰 작성자 프로필" class="user-img">
                                    <div class="write-info">
                                        <span class="user-name" >김종태</span>
                                        <span class="user-date" >2021.05.12</span>
                                    </div>
                                </div>
                                <div class="star-wrap">3</div>
                            </div>
                            <div class="review-row">
                                <p class="review-subs">리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제</p>
                                <div class="review-control">
                                    <input type="button" onclick="reviewEdit(this)" value="수정">
                                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/review/delete'" value="삭제">
                                </div>
                            </div>
                        </li>
                        <li class="review-li">
                            <div class="review-row">
                                <div class="user-info-wrap">
                                    <img src="${pageContext.request.contextPath}/images/noProfile.png" alt="리뷰 작성자 프로필" class="user-img">
                                    <div class="write-info">
                                        <span class="user-name" >김종태</span>
                                        <span class="user-date" >2021.05.12</span>
                                    </div>
                                </div>
                                <div class="star-wrap">3</div>
                            </div>
                            <div class="review-row">
                                <p class="review-subs">리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제</p>
                                <div class="review-control">
                                    <input type="button" onclick="reviewEdit(this)" value="수정">
                                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/review/delete'" value="삭제">
                                </div>
                            </div>
                        </li>
                        <li class="review-li">
                            <div class="review-row">
                                <div class="user-info-wrap">
                                    <img src="${pageContext.request.contextPath}/images/noProfile.png" alt="리뷰 작성자 프로필" class="user-img">
                                    <div class="write-info">
                                        <span class="user-name" >김종태</span>
                                        <span class="user-date" >2021.05.12</span>
                                    </div>
                                </div>
                                <div class="star-wrap">3</div>
                            </div>
                            <div class="review-row">
                                <p class="review-subs">리뷰의 내용이 들어와야제 리뷰의 내용이 들어와야제 </p>
                                <div class="review-control">
                                    <input type="button" onclick="reviewEdit(this)" value="수정">
                                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/review/delete'" value="삭제">
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <footer>
    	<jsp:include page="/WEB-INF/view/footer.jsp"/>
    </footer>
</body>
</html>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/slick/slick.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/slick/slick-theme.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
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

	<section class="slide">
		<div class="slide-items">
			<div class="slide-label">" 정성 가득한 수제품들을 <br/>지금 핸드워크에서 만나보세요 "</div>
		</div>
		<div class="slide-items">
			<div class="slide-label">" 나에게 딱맞는 제품을 찾나요?<br/>제작의뢰 게시판을 이용 해보세요 "</div>
		</div>
		<div class="slide-items">
			<div class="slide-label">" 주변 공방 게시판에서 집 근처 <br/>숨겨진 공방을 찾아보세요 "</div>
		</div>
	</section>

	<section class="sell-section">
		<div class=board-title>
			<a href="${pageContext.request.contextPath}/market" class="title">수제공방 게시판</a> <span class="sub">전국 수많은
				수제품 공예가들의 작품을 지금 확인하세요</span>
		</div>
		<div class="board-slider"
			data-slick='{"slidesToShow": 4, "slidesToScroll": 1}'
			style="width: 1200px; height: 500px;">
			<c:forEach var="m" items="${m_list}">
			<div>
				<a href="${pageContext.request.contextPath}/market/detail?id=${m.id}">
					<div class="board-items market prev-container">
						<div class="img-wrap">
							<c:choose>
								<c:when test="${empty m.filename }">
									<img src="${pageContext.request.contextPath}/images/noImage.png" class="view-img no-img">
								</c:when>
								<c:when test="${m.filename eq '/'}">
									<img src="${pageContext.request.contextPath}/images/noImage.png" class="view-img no-img">
								</c:when>

								<c:otherwise>
									<c:set var="doneLoop" value="false"/>
									<c:forTokens var="itemFN" items="${m.filename}" delims="/">
										<c:if test="${not doneLoop}">
											<img src="${pageContext.request.contextPath }/upload/marketBoard/${itemFN}" class="view-img">

											<c:set var="doneLoop" value="true"/>
										</c:if>
									</c:forTokens>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">${m.writer}</p>
								<p class="item-location">${m.location}</p>
							</div>
							<p class="item-title">${m.title}</p>
							<p class="item-price state sell">${m.price}원</p>
							<p class="item-review-num">${m.count}</p>
							<div class="item-review">
								<div class="star-wrap">${m.starAvg}</div>
								<span class="review-text">${m.topReview}</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			</c:forEach>


		</div>
	</section>
	<section class="order-section">
		<div class="rayer"></div>
		<div class="left">
			<p>
				원하시는 수제품이 없으신가요?</br> 제작의뢰 기능을 사용 해보세요
			</p>
			<a href="${pageContext.request.contextPath}/request">제작의뢰 하기</a>
		</div>
		<div class="right">
			<div class="right-container">
				<div class=board-title>
					<a href="${pageContext.request.contextPath}/request" class="title">제작의뢰 게시판</a> <span class="sub">전국
						수많은 수제품 공예가들의 작품을 지금 확인하세요</span>
				</div>
<%--				제작의뢰 slick 박스 구성--%>
				<div class="request-slide" data-slick='{"slidesToShow": 4, "slidesToScroll": 1}'>
					<c:forEach var="r" items="${list}">
					<div class="slider-item">
						<a href="${pageContext.request.contextPath}/request/detail?id=${r.id}" class="item-wrap">
							<c:choose>
								<c:when test="${empty r.filename }">
									<img src="${pageContext.request.contextPath}/images/noImage.png" class="item-img no-img" alt="제작의뢰 게시글 이미지">
								</c:when>
								<c:when test="${r.filename eq '/'}">
									<img src="${pageContext.request.contextPath}/images/noImage.png" class="item-img no-img" alt="제작의뢰 게시글 이미지">
								</c:when>

								<c:otherwise>
									<c:set var="doneLoop" value="false"/>
									<c:forTokens var="itemFN" items="${r.filename}" delims="/">
										<c:if test="${not doneLoop}">
											<img src="${pageContext.request.contextPath }/upload/requestBoard/${itemFN}" class="item-img" alt="제작의뢰 게시글 이미지">

											<c:set var="doneLoop" value="true"/>
										</c:if>
									</c:forTokens>


								</c:otherwise>
							</c:choose>
							<span class="item-title">${r.title}</span>
							<span class="item-price">${r.price}</span>
							<span class="item-writer">${r.writer}</span>
						</a>
					</div>
					</c:forEach>
				</div>
<%--				<table>--%>
<%--					<thead>--%>
<%--						<tr onclick="location.href=''">--%>
<%--							<td>제목</td>--%>
<%--							<td>내용</td>--%>
<%--							<td>제작가</td><td>작성자</td>--%>
<%--							<td>작성일</td>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr onclick="location.href=''">--%>
<%--							<td>제목입니다</td>--%>
<%--							<td>내용입니다입니다입니다</td>--%>
<%--							<td>12000원</td>--%>
<%--							<td>종태</td>--%>
<%--							<td>1998-05-06</td>--%>
<%--						</tr>--%>
<%--						<tr onclick="location.href=''">--%>
<%--							<td>제목입니다</td>--%>
<%--							<td>내용입니다입니다입니다</td>--%>
<%--							<td>12000원</td>--%>
<%--							<td>종태</td>--%>
<%--							<td>1998-05-06</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--				</table>--%>
			</div>
		</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/view/footer.jsp"/>
	</footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/slick/slick.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
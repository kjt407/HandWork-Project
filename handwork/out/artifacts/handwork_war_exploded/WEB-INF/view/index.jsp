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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/slick/slick.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/slick/slick-theme.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
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
			<div class="slide-label">핸드워커</div>
		</div>
		<div class="slide-items">
			<div class="slide-label">핸드워커</div>
		</div>
		<div class="slide-items">
			<div class="slide-label">핸드워커</div>
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
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div>
				<a href="">
					<div class="board-items market prev-container">
						<div class="img-wrap"><img src="${pageContext.request.contextPath}/images/item.png" alt="제품이미지"></div>
						<div class="item-container market">
							<div class="item-row">
								<p class="item-writer">촤콜렛하우스</p>
								<p class="item-location">서울특별시</p>
							</div>
							<p class="item-title">찰리의 촤칼렛 공장에서 만드는 수제 쳐컬렛</p>
							<p class="item-price state sell">15000원</p>
							<p class="item-review-num">1</p>
							<div class="item-review">
								<div class="star-wrap">2</div>
								<span class="review-text">가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다가장 최근의 리뷰가 보여집니다</span>
							</div>
						</div>
					</div>
				</a>
			</div>
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
					<div class="slider-item">
						<a href="" class="item-wrap">
							<img src="${pageContext.request.contextPath}/images/item.png" class="item-img" alt="제작의뢰 게시글 이미지">
							<span class="item-title">제작의뢰 게시글 제목입니다</span>
							<span class="item-price">12000</span>
							<span class="item-writer">김종태</span>
						</a>
					</div>
					<div class="slider-item">
						<a href="" class="item-wrap">
							<img src="${pageContext.request.contextPath}/images/item.png" class="item-img" alt="제작의뢰 게시글 이미지">
							<span class="item-title">제작의뢰 게시글 제목입니다제작의뢰 게시글 제목입니다제작의뢰 게시글 제목입니다</span>
							<span class="item-price">120000000</span>
							<span class="item-writer">김종태</span>
						</a>
					</div>
					<div class="slider-item">
						<a href="" class="item-wrap">
							<img src="${pageContext.request.contextPath}/images/item.png" class="item-img" alt="제작의뢰 게시글 이미지">
							<span class="item-title">제작의뢰 게시글 제목입니다</span>
							<span class="item-price">12000</span>
							<span class="item-writer">김종태</span>
						</a>
					</div>
					<div class="slider-item">
						<a href="" class="item-wrap">
							<img src="${pageContext.request.contextPath}/images/item.png" class="item-img" alt="제작의뢰 게시글 이미지">
							<span class="item-title">제작의뢰 게시글 제목입니다</span>
							<span class="item-price">12000</span>
							<span class="item-writer">김종태</span>
						</a>
					</div>
					<div class="slider-item">
						<a href="" class="item-wrap">
							<img src="${pageContext.request.contextPath}/images/items.png" class="item-img" alt="제작의뢰 게시글 이미지">
							<span class="item-title">제작의뢰 게시글 제목입니다</span>
							<span class="item-price">12000</span>
							<span class="item-writer">김종태</span>
						</a>
					</div>
					<div class="slider-item">
						<a href="" class="item-wrap">
							<img src="${pageContext.request.contextPath}/images/items.png" class="item-img" alt="제작의뢰 게시글 이미지">
							<span class="item-title">제작의뢰 게시글 제목입니다</span>
							<span class="item-price">12000</span>
							<span class="item-writer">김종태</span>
						</a>
					</div>
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
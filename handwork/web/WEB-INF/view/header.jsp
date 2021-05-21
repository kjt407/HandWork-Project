<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="header-top">
			<div class="left">
				<h2 class="hide">
					핸드워크 헤더
					</h2>
					<a href="${pageContext.request.contextPath}/" class="main-logo"> <!-- <img src="./images/2.png" alt="메인페이지 로고"> -->
						<p>HANDWORK</p>
					</a>
					<ul class="header-menu-title">
						<li>카테고리
							<div class="header-sub">
								<ul class="inner-ul">
									<li><a href="${pageContext.request.contextPath}/search?c=패션/뷰티">패션/뷰티</a></li>
									<li><a href="${pageContext.request.contextPath}/search?c=가구/인테리어">가구/인테리어</a></li>
									<li><a href="${pageContext.request.contextPath}/search?c=식품">식품</a></li>
									<li><a href="${pageContext.request.contextPath}/search?c=문구/잡화">문구/잡화</a></li>
									<li><a href="${pageContext.request.contextPath}/search?c=생활용품">생활용품</a></li>
								</ul>
							</div>
						</li>
						<li>장터
							<div class="header-sub">
								<ul class="inner-ul">
									<li><a href="${pageContext.request.contextPath}/market">수제공방</a></li>
									<li><a href="${pageContext.request.contextPath}/location">주변공방</a></li>
									<li><a href="${pageContext.request.contextPath}/request">제작의뢰</a></li>
								</ul>
							</div>
						</li>
						<li>커뮤니티
							<div class="header-sub">
								<ul class="inner-ul">
									<li><a href="">공지사항</a></li>
									<li><a href="">자유게시판</a></li>
								</ul>
							</div>
						</li>
					</ul>
			</div>
			<form class="search" action="search" method="">
				<input class="text" name="q" type="text" placeholder="검색어를 입력 해주세요">
				<input class="btn" type="submit" value="검색">
			</form>


			<c:choose>
				<c:when test="${sessionScope.id eq null}">
					<ul class="login">
						<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="login">
						<li><a class="header-username" disable>${name}님</a></li>
						<li><a href="">마이페이지</a></li>
						<li><a href="${pageContext.request.contextPath}/logout" class="header-logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="footer-wrap">
	<div class="footer-container1">
        <div class="container-col-top">
            <p class="page-name">HANDWORK</p>
            <ul class="dfn-ul">
                <li class="dfn-li">
                    <dfn class="dfn-dfn">상호</dfn>
                    <span class="dfn-subs">HANDWORK(핸드워크)</span>
                </li>
                <li class="dfn-li">
                    <dfn class="dfn-dfn">대표자명</dfn>
                    <span class="dfn-subs">최준형, 김종태</span>
                </li>
                <li class="dfn-li">
                    <dfn class="dfn-dfn">소속</dfn>
                    <span class="dfn-subs">대림대학교 컴퓨터정보학부</span>
                </li>
            </ul>
            <ul class="dfn-ul">
                <li class="dfn-li">
                    <dfn class="dfn-dfn">연락처</dfn>
                    <span class="dfn-subs">010-4073-1142</span>
                </li>
                <li class="dfn-li">
                    <dfn class="dfn-dfn">이메일</dfn>
                    <span class="dfn-subs">20174137@email.daelim.ac.kr</span>
                </li>
            </ul>
            <ul class="dfn-ul">
                <li class="dfn-li"> 
                    <dfn class="dfn-dfn">주소</dfn>
                    <span class="dfn-subs">경기도 안양시 동안구 임곡로 29 대림대학교 전산관 5층</span>
                </li>
            </ul>
        </div>
        <div class="container-col-bottom">
            Copyright&copyHandWorkProject
        </div>
    </div>
    <div class="footer-container2">
        <p class="page-name">장터</p>
        <ul class="footer-board market">
            <li class="board-li"><a href="">수제공방</a></li>
            <li class="board-li"><a href="${pageContext.request.contextPath}/request">제작의뢰</a></li>
            <li class="board-li"><a href="">지도로 보기</a></li>
        </ul>
    </div>
    <div class="footer-container3">
        <p class="page-name">게시판</p>
        <ul class="footer-board board">
            <li class="board-li"><a href="">공지사항</a></li>
            <li class="board-li"><a href="">이용후기</a></li>
            <li class="board-li"><a href="">자유게시판</a></li>
        </ul>
    </div>
    <div class="footer-container4">
        <a class="copy-btn page-name" onclick="footerCopyUrl(this)">주소 복사하기</a>
    </div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.handwork.request.entity.Request"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js">
    </script><script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
    <title>핸드워크: 수제공방</title>
</head>
<body>
<h1 class="hide">수제공방</h1>
    <header>
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </header>

    <section id="main-section">
        <div class="wrap-main">
            <section id="my-info-section">
                <p class="section-title">내정보</p>
                <form id="img-upload-form"  enctype="multipart/form-data" action="${pageContext.request.contextPath}/mypage/user-img" method="post">
                    <img src="${pageContext.request.contextPath}/upload/profile/noProfile-Big.png" alt="" id="img-profile">
                    <input type="file" name="img" id="edit-profile-img" class="hide" placeholder="이미지 업로드" accept="image/png, image/jpeg, image/jpg">
                    <label class="edit-profile-img label" for="edit-profile-img"><img src="${pageContext.request.contextPath}/images/edit2.png" id="label-img" alt=""></label>
                </form>
                <div class="info-row">
                    <p class="info-item name"></p>
                    <input type="button" class="btn-edit name" onclick="btnEdit(this,'name')"/>
                </div>
                <div class="info-container">
                    <div class="info-row">
                        <p class="info-title">이메일</p>
                        <p class="info-item email"></p>
                        <input type="button" class="btn-edit email" onclick="btnEdit(this,'email')"/>
                    </div>
                    <div class="info-row">
                        <p class="info-title">전화번호</p>
                        <p class="info-item phone"></p>
                        <input type="button" class="btn-edit phone" onclick="btnEdit(this,'phone')"/>
                    </div>
                    <div class="info-row">
                        <p class="info-title">비밀번호</p>
                        <p class="info-item passwd">**********</p>
                        <input type="button" class="btn-edit passwd"/>
                    </div>
                </div>

            </section>
            <section id="my-log-section">
                <p class="section-title">활동로그</p>
                <div class="log-container board">
                    <p class="list-title board">작성한 게시글</p>
                    <ul class="log-ul board">
                    </ul>
                </div>
                <div class="log-container reply">
                    <p class="list-title reply">작성한 댓글/리뷰</p>
                    <ul class="log-ul reply">
                    </ul>
                </div>
            </section>
        </div>
    </section>

    <footer>
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
</footer>
</body>
</html>

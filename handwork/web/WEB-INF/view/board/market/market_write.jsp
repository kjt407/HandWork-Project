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
<h1 class="hide">핸드워크: 수제공방</h1>
<header>
    <jsp:include page="/WEB-INF/view/header.jsp"/>
</header>
<section class="board-title-section market">
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

        <c:choose>
        <c:when test="${empty r.id}">
            <%-- ${r.id}write --%>
        <form class="board-main market-write-main"  enctype="multipart/form-data" action="write" method="post">
            </c:when>
            <c:otherwise>
                <%-- ${r.id}update --%>
            <form class="board-main market-write-main" enctype="multipart/form-data" action="update" method="post">
                </c:otherwise>
                </c:choose>

                    <input type="hidden" name="id" value="${r.id}"/>
                <h3 class="hide">게시글 작성페이지</h3>
                <p class="board-write-header-title">수제공방 글쓰기</p>
                <div class="board-write-header">
                    <div class="board-write-row state-select-row">
                        <div class="board-write-col state-select-col">
                            <p class="input-label">판매상태</p>
                            <select name="state" id="state-select" class="state-select">
                                <%--                            update 시에는 해당하는 옵션에 select 주면됨--%>
                                <option value="0" selected>판매중</option>
                                <option value="1">품절</option>
                            </select>
                        </div>
                    </div>
                    <div class="board-write-row">
                        <div class="board-write-col category-select-col">
                            <p class="input-label">카테고리</p>
                            <select name="kategorie" id="category-select" class="category-select" class="board-write-category">
                                <option value="패션/뷰티" selected>패션/뷰티</option>
                                <option value="가구/인테리어">가구/인테리어</option>
                                <option value="식품">식품</option>
                                <option value="문구/잡화">문구/잡화</option>
                                <option value="패션/뷰티">패션/뷰티</option>
                                <option value="생활용품">생활용품</option>
                            </select>
                        </div>
                        <div class="board-write-col location-select-col">
                            <p class="input-label">의뢰지역</p>
                            <select name="location" id="location-select" class="location-select" class="board-write-location">
                                <option value="서울특별시" selected>서울특별시</option>
                                <option value="경기도">경기도</option>
                                <option value="강원도">강원도</option>
                                <option value="충청도">충청도</option>
                                <option value="전라도">전라도</option>
                                <option value="경상도">경상도</option>
                                <option value="제주도">제주도</option>
                            </select>
                        </div>
                    </div>
                    <div class="board-write-row">
                        <div class="board-write-col">
                            <p class="input-label">글 제목</p>
                            <input type="text" name="title" class="board-write-title" value="${r.title}"placeholder="제목을 입력해 주세요" required/>
                        </div>
                    </div>
                    <div class="board-write-row">
                        <div class="board-write-col price-col">
                            <p class="input-label">희망가격</p>
                            <%-- <input type="text" name="price" class="board-write-price" value="${r.price}"placeholder="가격을 원단위로 입력해 주세요 "/> --%>
                            <input type="text" class="board-write-price" value="${r.price}" placeholder="가격을 원단위로 입력해 주세요"  oninput="inputNumberFormat(this)" required/>
                            <input type="hidden" id="real-input-price" name="price" value="${r.price}" />
                        </div>
                        <div class="board-write-col period-col">
                            <p class="input-label">제작기간</p>
                            <input type="number"name="period" placeholder="기간을 일단위로 입력해 주세요" value="${r.period}"class="board-write-period" id="board-write-period" required>
                        </div>
                    </div>


                    <div class="board-write-col img-col">
                        <p class="input-label">참고 이미지</p>
                        <ul class="add-img-list" id="add-img-ul">
                            <c:choose>
                                <c:when test="${r.isUpdate}">
                                    <c:forTokens var="itemFN" items="${r.filename}" delims="/" varStatus="status">
                                        <li class="img-ul-li add-img-li-update">
                                            <input type="file" name="img_update${status.count}" class="board-write-img hide" id="img-update${status.count}" placeholder="이미지 업로드" accept="image/png, image/jpeg, image/jpg" onchange="editFile(this)"/><label for="img-update${status.count}">변경</label><p id="img-update-path${status.count}" class="img-path" >${itemFN}</p><input type="button" class="btn-del-img" onClick="delFile(this)"/>
                                        </li>
                                    </c:forTokens>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                        <div class="add-btn-container">
                            <a type="button" onclick="addImg()" id="btn-add-img">+</a>
                        </div>
                    </div>

                    <div class="board-write-col price-col">
                        <p class="input-label">배송방법</p>
                        <div class="board-write-row delivery-container">
                            <input type="radio" name="how" id="d_radio1" value="방문수령" class="delivery-radio hide" checked><label for="d_radio1" class="delivery-radio-label">방문수령</label>
                            <input type="radio" name="how" id="d_radio2" value="택배" class="delivery-radio hide"><label for="d_radio2" class="delivery-radio-label">택배</label>
                            <input type="radio" name="how" id="d_radio3" value="방문수령+택배" class="delivery-radio hide"><label for="d_radio3" class="delivery-radio-label">방문수령+택배</label>
                        </div>
                    </div>
                    <textarea id="subs" cols="30" rows="10" name="content" class="board-write-subs" placeholder="의뢰 요청 사항 (상세하게 입력해주세요)" required>${r.content}</textarea>
                    <div class="btn-submit-group">
                        <input type="submit" class="btn-submit submit" value="작성완료"/>
                        <c:choose>
                            <c:when test="${empty r.id }">
                                <a href="../market" class="btn-submit cancel" >취소</a>
                            </c:when>
                            <c:otherwise>
                                <a href="detail?id=${r.id}"  class="btn-submit cancel" >취소</a>
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
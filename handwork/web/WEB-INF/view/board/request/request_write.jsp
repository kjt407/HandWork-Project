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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board-write.css">
    <script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
        src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <title>핸드워크: 제작의뢰</title>
</head>
<body>
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

			<c:choose>
				<c:when test="${empty r.id}">
				<%-- ${r.id}write --%>
					<form class="board-main request-write-main"  enctype="multipart/form-data" action="write" method="post">
				</c:when>
				<c:otherwise>
				<%-- ${r.id}update --%>
					<form class="board-main request-write-main" enctype="multipart/form-data" action="update" method="post">
				</c:otherwise>
			</c:choose> 
			
            	<input type="hidden" name="id" value="${r.id}"/>

                        
            
                <h3 class="hide">게시글 작성페이지</h3>
                <p class="board-write-header-title">제작의뢰 글쓰기</p>
                <div class="board-write-header">
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
                            <input type="text" name="location" id="location" class="board-write-location" value="${r.location}" required/>
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
                        <div class="board-write-col deadline-col">
                            <p class="input-label">마감일자</p>
                            <input type="date" value="xxx" min="yyy" max="zzz" name="deadline" value="${r.deadline}"class="board-write-daedline" id="board-write-daedline" required>
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
                        <p class="input-label">수령방법</p>
                        <div class="board-write-row delivery-container">
                            <input type="radio" name="how" id="d_radio1" value="직접수령" class="delivery-radio hide" checked><label for="d_radio1" class="delivery-radio-label">직접수령</label>
                            <input type="radio" name="how" id="d_radio2" value="택배" class="delivery-radio hide"><label for="d_radio2" class="delivery-radio-label">택배</label>
                            <input type="radio" name="how" id="d_radio3" value="직접수령+택배" class="delivery-radio hide"><label for="d_radio3" class="delivery-radio-label">직접수령+택배</label>
                        </div>
                    </div>
                    <textarea id="subs" cols="30" rows="10" name="content" class="board-write-subs" placeholder="의뢰 요청 사항 (상세하게 입력해주세요)" required>${r.content}</textarea>
                    <div class="btn-submit-group">
                        <input type="submit" class="btn-submit submit" value="작성완료"/>
                        <c:choose>
                       <c:when test="${empty r.id }">
                          <a href="${pageContext.request.contextPath}/request" class="btn-submit cancel" >취소</a>
                       </c:when>
                       <c:otherwise>
                          <a href="${pageContext.request.contextPath}/request/detail?id=${r.id}"  class="btn-submit cancel" >취소</a>
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
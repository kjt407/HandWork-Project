<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>

<title>핸드워크: 회원가입</title>
</head>
<body>
	<h1 class="hide">회원가입</h1>
	<header>
		<jsp:include page="/WEB-INF/view/header.jsp" />
	</header>

	<section class="join-section main-section">
		<h2 class="hide">회원가입 영역</h2>
		<div class="join-container inner-container">
			<p class="horizontal-join horizontal-title">회원가입</p>
			<form action="join" method="post">
				<p class="form-subs">사용자 유형</p>
				<div class="radio-group">
					<input type="radio" name="user-type" id="customer" value="customer"
						class="radio-type-user" checked> <label class=""
						for="customer">구매자</label> <input type="radio" name="user-type"
						id="seller" value="seller" class="radio-type-user"> <label
						class="" for="seller">제작자</label>
				</div>
				<p class="form-subs-join">회원정보 입력</p>
				<div class="join-input-row">
					<label for="join-id"><span>아이디</span></label> <input type="text"
						class="text-id-join" name="id" id="join-id"
						placeholder="아이디를 입력하세요." required>
				</div>
				<div class="join-input-row">
					<label for="join-pw"><span>비밀번호</span></label> <input
						type="password" class="text-id-join" name="pw" id="join-pw"
						placeholder="비밀번호를 입력하세요." onChange="pwCheck()" required>
				</div>
				<div class="join-input-row">
					<label for="join-pw-check"><span>비밀번호 확인</span> <span class="pw_check_notice"></span></label>
					 <input type="password"
						class="text-id-join" name="pw_re" id="join-pw-check"
						placeholder="비밀번호를 다시 입력하세요" onChange="pwCheck()" required>
				</div>
				<div class="join-input-row">
					<label for="join-name"><span>이름</span></label> <input type="text"
						class="text-id-join" name="name" id="join-name"
						placeholder="사용자 이름을 입력하세요" required>
				</div>
				<div class="join-input-row">
					<label for="join-email"><span>이메일</span></label>
					<div class="email-group" id="join-email">
						<input type="text" class="text-id-join" name="email1"
							placeholder="email" required> <span>@</span> <input
							type="text" class="text-id-join" name="email2" placeholder="직접입력"
							required>
					</div>
				</div>
				<div class="join-input-row">
					<select name="email_select" id="email_select" class="email-select"
						onChange="selectEmail(this)">
						<option value="direct" selected>직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
						<option value="hanmail.com">hanmail.com</option>
						<option value="yahoo.co.kr">yahoo.co.kr</option>
					</select>
				</div>
				<input type="submit" class="btn-join btn-submit" value="회원가입">
			</form>
		</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/view/footer.jsp"/>
	</footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/join.js"></script>

</body>
</html>
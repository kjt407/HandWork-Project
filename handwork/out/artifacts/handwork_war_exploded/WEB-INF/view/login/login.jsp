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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<title>로그인</title>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header_footer.js"></script>

<script>
	window.Kakao.init("47d80a797066ad48e74a8224ca2a7e23");
	
	var id = null;
	var email = null;
	var name = null;
	
	function kakaoLogin(){
		window.Kakao.Auth.loginForm({
			scope:'profile, account_email',
			success: function(authObj){
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success: res => {
						const kakao_account = res.kakao_account;
						console.log(kakao_account);
						id = res.id;
						email = res.kakao_account.email;
						name = res.properties.nickname;
						console.log(id);
						console.log(email);
						console.log(name);

						$.ajax({

							url: "kakaoLogin",

							dataType: "json",

							Type: "post",

							data: { "id": id, "email": email, "name": name },
							success: function (data) {
<<<<<<< HEAD

								alert("완료!")
=======
								window.location.href = "${pageContext.request.contextPath}/";
>>>>>>> e1a895c63ac88a8c7e267617c9ae2fe515e64791
							}
						});
					}
				});
			}
		});
	}
</script>

</head>
<body>
	<h1 class="hide">로그인페이지</h1>
	<header>
		<jsp:include page="/WEB-INF/view/header.jsp"/>
	</header>


	<section class="login-section main-section">
		<h2 class="hide">로그인영역</h2>
		<div class="login-container inner-container">
			<p class="horizontal-login horizontal-title">로그인</p>
			<form action="" method="post">
				<input type="text" name="id" class="text-id"
					placeholder="아이디를 입력하세요."> <input type="password" name="pw"
					class="text-pw" placeholder="비밀번호를 입력해주세요"> <input
					type="submit" class="btn-login btn-submit" value="로그인"> <a
					href="${pageContext.request.contextPath}/join">회원가입</a>
			</form>
			<p class="horizontal-or">OR</p>
			 <a href="javascript:kakaoLogin();" class="btn-login-kakao"></a>

		</div>
	</section>

	<footer>
		<jsp:include page="/WEB-INF/view/footer.jsp"/>
	</footer>

	
</body>
</html>
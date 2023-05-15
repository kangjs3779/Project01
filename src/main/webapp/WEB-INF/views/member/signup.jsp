<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="d" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

	<d:navBar current="signup"></d:navBar>
	<d:alert state="danger"></d:alert>
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<h1>회원 가입</h1>
				<form method="post">
					<div class="mb-3">
						<label for="inputId" class="form-label">아이디</label>
						<div class="input-group mb-3">
							<input type="text" class="form-control" name="id" id="inputId" value="${member.id }" />
							<button type="button" id="checkIdButton" class="btn btn-outline-secondary">중복확인</button>
						</div>
						<div class="d-none form-text text-primary" id="availableIdMessage">
							<i class="fa-solid fa-check"></i>
							사용가능한 ID입니다.
						</div>
						<div class="d-none form-text text-danger" id="notAvailableMessage">
							<i class="fa-solid fa-triangle-exclamation"></i>
							사용 불가능한 ID입니다.
						</div>


					</div>
					<div class="mb-3">
						<label for="inputPassword" class="form-label">패스워드</label>
						<input type="password" class="form-control" name="password" id="inputPassword" value="${member.password }" />
					</div>
					<div class="mb-3">
						<label for="inputPasswordCheck" class="form-label">패스워드 확인</label>
						<input type="password" class="form-control" id="inputPasswordCheck" />

						<div id="passwordSuccessText" class="d-none form-text text-primary">
							<i class="fa-solid fa-check"></i>
							패스워드가 일치 합니다.
						</div>
						<div id="passwordFailText" class="d-none form-text text-danger">
							<i class="fa-solid fa-triangle-exclamation"></i>
							패스워드가 일치하지 않습니다.
						</div>
						<!-- 실제 업무에서는 패스워드가 일치하는지 컨트롤러에서 확인을 해야 한다 -->
						<!-- 백엔드에서는 프론트를 믿으면 안된다 꼼꼼하게 확인을 해야 한다 -->
					</div>

					<div class="mb-3">
						<label for="inputNickname" class="form-label">별명</label>
						<div class="input-group mb-3">
							<input type="text" class="form-control" name="nickName" id="inputNickname" value="${member.nickName }" />
							<button type="button" id="checkNickNameButton" class="btn btn-outline-secondary">중복확인</button>
						</div>
						<div class="d-none form-text text-primary" id="availableNickNameMessage">
							<i class="fa-solid fa-check"></i>
							사용 가능한 별명입니다
						</div>
						<div class="d-none form-text text-danger" id="notAvailableNickNameMessage">
							<i class="fa-solid fa-triangle-exclamation"></i>
							사용 불가능한 별명입니다
						</div>
					</div>

					<div class="mb-3">
						<label for="inputEmail" class="form-label">이메일</label>
						<div class="input-group mb-3">
							<input type="email" class="form-control" name="email" id="inputEmail" value="${member.email }" />
							<button type="button" id="checkEmailButton" class="btn btn-outline-secondary">중복확인</button>
						</div>
						<div class="d-none form-text text-primary" id="availableEmailMessage">
							<i class="fa-solid fa-check"></i>
							사용 가능한 이메일입니다
						</div>
						<div class="d-none form-text text-danger" id="notAvailableEmailMessage">
							<i class="fa-solid fa-triangle-exclamation"></i>
							사용 불가능한 이메일입니다
						</div>

					</div>
					<div class="d-grid mb-3">
						<input id="signupSubmit" type="submit" class="btn btn-outline-primary" disabled value="가입" />
					</div>
				</form>
			</div>
		</div>
	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="/js/member/signup.js"></script>

</body>
</html>
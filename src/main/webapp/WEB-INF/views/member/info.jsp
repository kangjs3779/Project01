<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="d" tagdir="/WEB-INF/tags" %>
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
	<d:navBar></d:navBar>
	
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<h1>${member.nickName }님 정보</h1>
				<div>
					<label for="id" class="form-label"> 아이디 : </label>
					<input type="text" class="form-control" value="${member.id }" id="id" readonly />
				</div>
				<div>
					<label for="password" class="form-label"> 비밀번호 : </label>
					<input type="password" class="form-control" value="${member.password }" id="password" readonly />
				</div>
				<div>
					<label for="nickName" class="form-label"> 닉네임 : </label>
					<input type="text" class="form-control" value="${member.nickName }" id="nickName" readonly />
				</div>
				<div>
					<label for="email" class="form-label"> 이메일 : </label>
					<input type="email" class="form-control" value="${member.email }" id="email" readonly />
				</div>
				<div>
					<label for="inserted" class="form-label"> 회원가입날짜 : </label>
					<input type="datetime-local" class="form-control" value="${member.inserted }" id="inserted" readonly />
				</div>
				
			</div>
		</div>
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<script>

	</script>
</body>
</html>
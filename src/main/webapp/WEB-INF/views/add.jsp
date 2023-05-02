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
</head>
<body>

	<d:navBar current="add" />
	
	
	<div class="container-lg">
		<h1>게시물 작성</h1>
		<form action="" method="post" id="addForm" enctype="multipart/form-data">
			<!-- action은 써도 되고 안써도 되고 아예 없애도 되고 -->
			<!-- 내가 원하는데로 폼을 꾸며보기 -->
			<div>
				제목 :
				<input type="text" name="title" value="${board.title }" />
			</div>
			<div>
				본문 :
				<textarea name="body">${board.body }</textarea>
			</div>
			
			<div>
				파일 : <input type="file" multiple="multiple" name="files" accept="image/*"/>
			</div>
			
			<div>
				작성자 :
				<input type="text" name="writer" value="${board.writer }" />
			</div>
		</form>
		<button type="submit" form="addForm" id="addButton">추가하기</button>
	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script type="text/javascript">
		$("#addButton").click(function(e) {
			e.preventDefault();

			const res = confirm("게시글을 추가 하시겠습니까?");
			if (res) {
				//서브밋 실행
				$("#addForm").submit();
			}
		});
	</script>

	

</body>
</html>
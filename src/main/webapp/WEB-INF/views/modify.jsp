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
	<d:navBar />


	<div class="container-lg">
		<h1>${board.id }게시물수정</h1>
		<form method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${board.id }" />
			<div>
				제목 :
				<input type="text" name="title" value="${board.title }" />
			</div>
			<!-- 첨부 그림 보이기 -->
			<div class="mb-3">
				<c:forEach items="${board.fileName }" var="fileName" varStatus="status">
					<div class="form-check form-switch">
						<i class="fa-solid fa-trash-can"></i>
						<input class="form-check-input" type="checkbox" name="removeFiles" role="switch" value="${fileName }" id="removeCheckbox${status.index }">
					</div>
					<label class="form-check-label" for="removeCheckbox${status.index }">
						<div class="mb-3">
							<%--localhost:8081/image/게시물번호/fileName --%>
							<img src="${bucketUrl }/${board.id }/${fileName}" alt="" />
						</div>
					</label>
				</c:forEach>
			</div>

			<div>
				본문 :
				<textarea name="body"></textarea>
			</div>
			<div>
				작성자 :
				<input type="text" name="writer" value="${board.writer }" />
			</div>
			<div>
				작성일시 :
				<input type="text" value="${board.inserted }" readonly />
			</div>
			<!-- 새그림 추가 -->
			<div>
				파일 :
				<input type="file" multiple="multiple" name="files" accept="image/*" />
				<div class="form-text">
					총 20MB, 하나의 파일은 1MB를 초과할 수 없습니다.
				</div>
			</div>
			<div>
				<input type="submit" value="수정" />
			</div>
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<c:if test="${not empty param.fail }">
		<script>
			alert("게시물이 수정되지 않았습니다")
		</script>
	</c:if>



</body>
</html>
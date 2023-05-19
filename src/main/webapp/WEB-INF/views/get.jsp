<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="d" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
	<d:alert />

	<!-- toast -->
	<div class="toast-container top-0 start-50 translate-middle-x p-3">
		<div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
			<div class="toast-header">
				<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
			</div>
			<div class="toast-body"></div>
		</div>
	</div>

	<!-- 본문 -->
	<div class="container-lg">

		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<div class="">
					<h1>
						<span id="boardIdText"> ${board.id }</span>
						번게시물보기
					</h1>
				</div>
				<div>
					<h1>
						<span id="likeIcon">
							<c:if test="${board.liked }">
								<i class="fa-solid fa-heart"></i>
							</c:if>
							<c:if test="${not board.liked }">
								<i class="fa-regular fa-heart"></i>
							</c:if>
						</span>
						<span id="likeNumber"> ${board.likeCount } </span>
					</h1>
				</div>
				<div>
					<label for="title" class="form-label"> 제목 : </label>
					<input type="text" class="form-control" value="${board.title }" id="title" readonly />
				</div>
				<!-- 그림 파일 출력 -->
				<div class="mb-3">
					<c:forEach items="${board.fileName }" var="fileName">
						<div class="mb-3">
							<%--localhost:8081/image/게시물번호/fileName --%>
							<img src="${bucketUrl }/${board.id }/${fileName}" alt="" />
						</div>
					</c:forEach>
				</div>


				<div>
					<label for="body" class="form-label"> 본문 : </label>
					<textarea type="text" class="form-control" id="body" readonly rows="10">${board.body }</textarea>
				</div>
				<div>
					<label for="writer" class="form-label"> 작성자 : </label>
					<input type="text" class="form-control" value="${board.writer }" id="writer" readonly />
				</div>
				<div>
					<label for="inserted" class="form-label"> 작성일시 : </label>
					<input type="datetime-local" class="form-control" value="${board.inserted }" id="inserted" readonly />
				</div>
				<br />

				<!-- 댓글창 -->
				<div id="commentContainer">

					<div id="addCommentContainer">
						<h6>입력</h6>
						<textarea id="commentTextArea"></textarea>
						<button id="sendCommentBtn">전송</button>
					</div>
					<div id="updateCommentContainer">
						<h6>수정</h6>
						<input type="hidden" id="commentUpdateIdInput" />
						<textarea id="commentUpdateTextArea"></textarea>
						<button id="updateCommentBtn">수정</button>
					</div>


					<div id="commentListContainer">
						<div>댓글1 내용 : 누가 : 언제</div>
						<div>댓글2 내용 : 누가 : 언제</div>
						<div>댓글3 내용 : 누가 : 언제</div>
					</div>
				</div>

				<div>
					<sec:authorize access="isAuthenticated() ">
						<sec:authentication property="name" var="userId" />
						<c:if test="${userId eq board.writer }">
							<a class="btn btn-secondary" href="/modify/${board.id }">수정하기</a>
						</c:if>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<button id="removeButton" class="btn btn-danger" form="removeForm" type="submit">삭제하기</button>
					</sec:authorize>
				</div>
				<br />
			</div>
		</div>
	</div>


	<!-- 숨겨져있는 폼을 만들어서 작성함, 자바스크립트로도 작성할 수 있음 -->
	<div class="d-none">
		<form action="/remove" method="post" id="removeForm">
			<input id="inputId" type="hidden" name="id" value="${board.id }" />
		</form>
	</div>

	<!-- 삭제 확인하는 모달 추가 -->
	<!-- 버튼이 removeForm을 전달하도록 해야함 -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script type="text/javascript">
		$("#removeButton").click(function(e) {
			//서브밋 진행 이벤트 막기
			e.preventDefault();

			const res = confirm("삭제 하시겠습니까?");
			if (res) {
				//서브밋 실행
				$("#removeForm").submit();
			}
		});
	</script>
	<script src="/js/board/like.js"></script>
	<script src="/js/board/comment.js"></script>
</body>
</html>
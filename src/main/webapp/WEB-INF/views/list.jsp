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

	<d:navBar current="list" />
	<d:alert state="danger" />

	<div class="w-75 p-3 mx-auto">
		<h1>게시물 목록 보기</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>title</th>
					<th>writer</th>
					<th>updated</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${boardList }" var="boardList">
					<tr>
						<td>${boardList.id }</td>
						<td>
							<a href="/id/${boardList.id }"> ${boardList.title } </a>
						</td>
						<td>${boardList.writer }</td>
						<td>${boardList.inserted }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="/add">
			<input class="btn btn-secondary" type="submit" value="글쓰기" />
		</form>
	</div>

	<br />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>




</body>
</html>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ attribute name="current"%>

<div style="margin-bottom: 80px"></div>
<nav class="navbar navbar-expand-lg bg-dark mb-5 fixed-top" data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/list">
		<img alt="" src="/img/jjanggu.png" width="40">
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link ${current == 'list' ? 'active' : '' }" href="/list">목록</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${current == 'add' ? 'active' : '' }" href="/add">글작성</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${current == 'signup' ? 'active' : '' }" href="/member/signup">회원가입</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${current == 'memberlist' ? 'active' : '' }" href="/member/list">회원목록</a>
				</li>
			</ul>
			<form class="d-flex" role="search">
				<!-- select option -->
				<div class="input-group">
					<select class="form-select flex-grow-0" style="width: 100px;" " name="type">
						<option value="all">전체</option>
						<option value="title" ${param.type == 'title' ? 'selected' : '' }>제목</option>
						<option value="body" ${param.type == 'body' ? 'selected' : '' }>본문</option>
						<option value="writer" ${param.type == 'writer' ? 'selected' : '' }>작성자</option>
					</select>
					<!-- search -->
					<input value="${param.search }" name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</form>
		</div>
	</div>
</nav>
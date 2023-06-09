<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ attribute name="current"%>

<div style="margin-bottom: 80px">

</div>
<nav class="navbar navbar-expand-lg bg-dark mb-5 fixed-top" data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/list">중앙</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link ${current == 'list' ? 'active' : '' }" href="/list">목록</a></li>
				<li class="nav-item"><a class="nav-link ${current == 'add' ? 'active' : '' }" href="/add">글작성</a></li>
			</ul>
			<form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>
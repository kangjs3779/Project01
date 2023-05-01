<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="d" tagdir="/WEB-INF/tags"%>
<%@ attribute name="pageNum" %>

<c:url value="/list" var="pageLink">
	<!-- 파라미터를 붙여주는 태그 -->
	<c:param name="page" value="${pageNum }" />
	<c:if test="${not empty param.search  }">
		<c:param name="search" value="${param.search }" />
	</c:if>
	<c:if test="${not empty param.type }">
		<c:param name="type" value="${param.type }" />
	</c:if>
</c:url>
<li class="page-item  ${pageNum == pageInfo.currentPageNum ? 'active' : '' }">
	<a class="page-link " href="${pageLink }">
		<jsp:doBody />
	</a>
</li>
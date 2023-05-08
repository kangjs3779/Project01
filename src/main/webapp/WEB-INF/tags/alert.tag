<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="state"%>

<c:if test="${not empty message }">
	<div class="alert alert-primary d-flex align-items-center" role="alert">
		<i class="fa-solid fa-triangle-exclamation"></i>
		<div>${message }</div>
	</div>
</c:if>
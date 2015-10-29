<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form class="navbar-form navbar-right"
	action="<c:url value='/logout' /> " method="post">
	<input type="submit" class="btn" value="Logout" /> <input type="hidden"
		name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
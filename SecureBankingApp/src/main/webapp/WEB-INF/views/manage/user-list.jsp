<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>XYZ Bank</title>

<!-- include css -->
<jsp:include page="../css.jsp"></jsp:include>

<!-- include js -->
<jsp:include page="../js.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>

	<div class="container col-sm-8 col-sm-offset-2 ">

		<h3>Users</h3>

		<c:if test="${fn:length(users) gt 0}">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Type</th>
						<th>Email</th>
						<th>Status</th>
						<th>Account</th>
						<th>Update</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user" varStatus="loop">
						<tr>
							<td><c:out value="${loop.index + 1}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${roles[user.userType]}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${status[user.status]}" /></td>
							<td><a
								href="<c:url value='/manage/account/${user.userId}'/>"
								class="btn btn-default btn-sm">Account</a></td>
							<td><a
								href="<c:url value='/manage/user/update/${user.userId}'/>"
								class="btn btn-default btn-sm">Update</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${fn:length(users) eq 0}">
			<div class="well">No users found</div>
		</c:if>

	</div>

</body>

</html>
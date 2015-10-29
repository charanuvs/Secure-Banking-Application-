<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

		<c:if test="${fn:length(accounts) gt 0}">
			<h3>
				Account information - <span class="text-muted"><c:out
						value="${accounts[0].user.name}" /> </span>
			</h3>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>Type</th>
						<th>Balance</th>
						<th>Opening date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${accounts}" var="account" varStatus="loop">
						<tr>
							<td><c:out value="${account.accountNum}" /></td>
							<td><c:out value="${accountTypes[account.accountType]}" /></td>
							<td><fmt:formatNumber value="${account.balance}"
									type="currency" /></td>
							<td><fmt:formatDate value="${account.openingDate}"
									pattern="dd MMMM yyyy" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${fn:length(accounts) eq 0}">
			<div class="well">No accounts found for this user</div>
		</c:if>
		<div class="container">
			<a href="<c:url value='/manage/account/add/${username}' />"
				class="btn btn-default">Add account</a>
		</div>

	</div>

</body>

</html>
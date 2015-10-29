<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<div class="container col-sm-6 col-sm-offset-3 ">


		<div class="well">
			<c:url var="formAction" value="/manage/account/add" />
			<form:form method="post" class="form-horizontal"
				commandName="account" action="${formAction}">
				<legend>Add User</legend>
				<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="accountType">Account
						type</label>

					<div class="col-sm-7">
						<form:select class="form-control" path="accountType"
							items="${accountTypes}">
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label" for="balanceString">Initial
						Deposit ($)</label>

					<div class="col-sm-7">
						<form:input path="balanceString" class="form-control" type="text" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-4"></div>
					<div class="col-sm-7">
						<input type="submit" id="submit"
							class="btn btn-primary btn-lg btn-sm-10" value="Add Account">
					</div>
				</div>

			</form:form>

		</div>
	</div>

</body>

</html>
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
	<jsp:include page="../nav.jsp"></jsp:include>

	<div class="container col-sm-6 col-sm-offset-3 ">

		<div class="well">
			<c:url var="formAction" value="/all/pwd" />
			<form:form method="post" class="form-horizontal" commandName="pwd"
				action="${formAction}">
				<legend>Update Password</legend>
				<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>

				<div class="form-group">
					<label class="col-sm-4 control-label" for="currentPassword">Current
						Password</label>

					<div class="col-sm-7">
						<form:password path="currentPassword" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label" for="password">Password</label>

					<div class="col-sm-7">
						<form:password path="password" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label" for="confirmPassword">Confirm
						Password</label>

					<div class="col-sm-7">
						<form:password path="confirmPassword" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-4"></div>
					<div class="col-sm-7">
						<input type="submit" id="submit"
							class="btn btn-primary btn-lg btn-sm-10" value="Update">
					</div>
				</div>

			</form:form>

		</div>
	</div>

</body>

</html>
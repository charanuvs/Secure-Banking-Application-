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

		<div class="alert alert-info">We have emailed you an OTP to your
			mail address. Please enter the OTP to change your password.</div>

		<div class="well">
			<c:url var="formAction" value="/all/pwd/otp" />
			<form:form method="post" class="form-horizontal"
				action="${formAction}">
				<legend>Update Password</legend>

				<div class="form-group">
					<label class="col-sm-4 control-label" for="otp">OTP</label>

					<div class="col-sm-7">
						<input type="password" id="otp" name="otp" class="form-control">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-4"></div>
					<div class="col-sm-7">
						<input type="submit" id="submit"
							class="btn btn-primary btn-lg btn-sm-10" value="Submit">
					</div>
				</div>

			</form:form>
		</div>
	</div>

</body>

</html>
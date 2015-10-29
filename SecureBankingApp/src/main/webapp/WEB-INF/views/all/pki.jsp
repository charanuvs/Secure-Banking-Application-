<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>XYZ Bank</title>

<!-- include css -->
<jsp:include page="../css.jsp"></jsp:include>

<!-- include js -->
<jsp:include page="../js.jsp"></jsp:include>
</head>
<body>
	<div class="container col-sm-6 col-sm-offset-3 ">

		<div class="alert alert-info"></div>

		<div class="well">
			<c:url var="formAction" value="/all/key" />
			<form:form method="post"
				class="form-horizontal"
				action="${formAction}">
				<input type="submit" class="btn" value="Logout" /> <input type="hidden"
		name="${_csrf.parameterName}" value="${_csrf.token}" />
				<legend>Please decrypt the following string using your Private key and enter the result below.
				
				 </legend>

				<div class="form-group">
					Challenge String: <textarea rows="5" cols="50" value=""><c:out value="${challengeString}"></c:out></textarea><br />
					Secret Code: <input type="text" name="secret"><br /> <br />
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
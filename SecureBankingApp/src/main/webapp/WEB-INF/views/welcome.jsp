<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>XYZ Bank</title>
		
		<!-- include css -->
		<jsp:include page="css.jsp"></jsp:include>
		
		<!-- include js -->
		<jsp:include page="js.jsp"></jsp:include>
	</head>
	<body>
        <nav class="navbar navbar-default navbar-static-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" 
                    	data-toggle="collapse" 
                    	data-target="#navbar"
                    	aria-expanded="false"
                    	aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value='/'></c:url>">XYZ Bank</a>
                </div>
            </div>
            <!--/.container-fluid -->
        </nav>

        <div class="container">
            <div class="jumbotron">
                <h1>XYZ Secure Online Banking</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, ex. Maxime, autem. Repellat quod autem quos delectus! Possimus at eius atque dolores corrupti, fugiat accusamus qui, vel incidunt itaque, ipsam.</p>
                <p>
                    <a class="btn btn-lg btn-primary" href="<c:url value='/login' />" role="button">Sign In to Access »</a>
                    <a class="btn btn-lg btn-primary" href="<c:url value='/apply' />" role="button">Create an account »</a>
                </p>
            </div>
        </div>
    </body>

</html>
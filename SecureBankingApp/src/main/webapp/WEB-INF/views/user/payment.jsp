<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
        <c:url var="formAction" value="/user/payment/confirm"/>
        <form:form method="post"
                   class="form-horizontal" commandName="transaction" action="${formAction}">
            <legend>Update Info</legend>
            <form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="fromAccountNumber">From Account</label>

                <div class="col-sm-7">
                    <form:input path="fromAccountNumber" class="form-control" type="text"
                            />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="toAccountNumber">To Account</label>

                <div class="col-sm-7">
                    <form:input path="toAccountNumber" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="amountString">Amount</label>

                <div class="col-sm-7">
                    <form:input path="amountString" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="transactionType">Amount</label>

                <div class="col-sm-7">
                    <form:select path="transactionType" items="${types}" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-7">
                    <input type="submit" id="submit" class="btn btn-primary btn-lg btn-sm-10"
                           value="Submit">
                </div>
            </div>

        </form:form>

    </div>
</div>

</body>

</html>
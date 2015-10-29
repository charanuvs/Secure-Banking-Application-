<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<jsp:include page="nav.jsp"></jsp:include>

<div class="container col-sm-8 col-sm-offset-2 ">

    <h3>Pending non-critical transactions</h3>

    <c:if test="${fn:length(transactions) gt 0}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Transaction Type</th>
                <th>From Account</th>
                <th>To Account</th>
                <th>Requested Amount</th>
                <th>Available Balance</th>
                <th>Approve</th>
                <th>Deny</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${transactions}" var="transaction" varStatus="loop">
                <tr>
                    <td><c:out value="${transaction.transactionType}"/></td>
                    <td><c:out value="${transaction.fromAccount.accountNum}"/></td>
                    <td><c:out value="${transaction.toAccount.accountNum}"/></td>
                    <td><c:out value="${transaction.amount}"/></td>
                    <td><c:out value="${transaction.fromAccount.balance}"/></td>
                    <td><a href="<c:url value='/emp/transactions/approve/${transaction.transactionId}'/>"
                           class="btn btn-default btn-sm">Approve</a></td>
                    <td><a href="<c:url value='/emp/transactions/deny/${transaction.transactionId}'/>"
                           class="btn btn-default btn-sm">Deny</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${fn:length(transactions) eq 0}">
        <div class="well">No pending transactions</div>
    </c:if>

</div>

</body>

</html>
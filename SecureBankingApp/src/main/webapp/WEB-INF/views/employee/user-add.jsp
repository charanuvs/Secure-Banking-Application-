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
<jsp:include page="nav.jsp"></jsp:include>

<div class="container col-sm-6 col-sm-offset-3 ">


    <div class="well">
        <c:url var="formAction" value="/manage/user/add"/>
        <form:form method="post"
                   class="form-horizontal" commandName="user" action="${formAction}">
            <legend>Add User</legend>
            <form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="userId">Username</label>

                <div class="col-sm-7">
                    <form:input path="userId" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="userType">Type</label>

                <div class="col-sm-7">
                    <form:select class="form-control" path="userType" items="${roles}"></form:select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="name">Full name</label>

                <div class="col-sm-7">
                    <form:input path="name" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group" id="dobCont">
                <label class="col-sm-4 control-label" for="dateString">Date of birth (YYYY-MM-DD)</label>

                <div class="col-sm-7">
                    <form:input path="dateString" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="email">Email</label>

                <div class="col-sm-7">
                    <form:input path="email" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="address">Address</label>

                <div class="col-sm-7">
                    <form:input path="address" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" for="phoneNumber">Phone</label>

                <div class="col-sm-7">
                    <form:input path="phoneNumber" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group" id="ssnCont">
                <label class="col-sm-4 control-label" for="ssn">SSN</label>

                <div class="col-sm-7">
                    <form:input path="ssn" class="form-control" type="text"/>
                </div>
            </div>

            <div class="form-group" id="genderCont">
                <label class="col-sm-4 control-label" for="gender">Gender</label>

                <div class="col-sm-7">
                    <form:select class="form-control" path="gender" items="${genders}"></form:select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-7">
                    <input type="submit" id="submit" class="btn btn-primary btn-lg btn-sm-10"
                           value="Add User">
                </div>
            </div>

        </form:form>

    </div>
</div>

<script type="application/javascript">
    (function () {
        $gender = $("#genderCont");
        $ssn = $("#ssnCont");
        $userType = $('#userType');
        $dob = $('#dobCont');
        $userType.change(function () {
            update();
        });

        function update() {
            var val = $userType.val();
            if (val === 'ROLE_MERCHANT') {
                $ssn.hide();
                $gender.hide();
                $dob.hide();
            } else {
                $ssn.show();
                $gender.show();
                $dob.show();
            }
        }

        update();
    })();
</script>
</body>

</html>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-28
  Time: 오후 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="login.title"/></title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css"> <!-- load bootstrap css -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"> <!-- load fontawesome -->
</head>
<body>
<div class="container">
    <div class="col-sm-6 col-sm-offset-3">
    <form:form modelAttribute="loginCommand">
        <form:errors/>
        <p>
            <label>
                <spring:message code="email"/><br/>
                <form:input path="email" cssClass="form-control"/><br/>
                <form:errors path="email"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="password"/><br/>
                <form:password path="password" cssClass="form-control"/><br/>
                <form:errors path="password"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="rememberEmail"/><br/>
                <form:checkbox path="rememberEmail" cssClass="checkbox-inline"/><br/>
            </label>
        </p>
        <input type="submit" class="btn btn-warning btn-lg" value="<spring:message code="login.btn"/>"/>
    </form:form>
    </div>
</div>
</body>
</html>

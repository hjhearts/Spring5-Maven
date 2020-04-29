<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-29
  Time: 오후 4:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title><spring:message code="change.pwd.title"/></title>
    <style>
        .error{color:red;}
    </style>
</head>
<body>
    <form:form modelAttribute="command">
        <p>
            <label>
                <spring:message code="currentPassword"/><br/>
                <form:password path="currentPassword"/><br/>
                <form:errors path="currentPassword" cssClass="error"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="newPassword"/><br/>
                <form:password path="newPassword"/><br/>
                <form:errors path="newPassword" cssClass="error"/>
            </label>
        </p>
        <input type="submit" value="<spring:message code="change.btn"/>"/>
    </form:form>
</body>
</html>

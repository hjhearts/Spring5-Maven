<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-27
  Time: 오후 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="member.register"/></title>
    <style>
        .notice{color:red}
    </style>
</head>
<body>
    <h2><spring:message code="member.info"/></h2>
    <form:form action="step3" modelAttribute="registerRequest">
        <p>
            <label>
                <spring:message code="email"/><br/>
                <form:input path="email"/>
                <form:errors path="email" element="span class='notice'"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="name"/><br/>
                <form:input path="name"/>
                <form:errors path="name" element="span class='notice'"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="password"/><br/>
                <form:password path="password"/>
                <form:errors path="password" element="span class='notice'"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="password.confirm"/><br/>
                <form:password path="confirmPassword"/>
                <form:errors path="confirmPassword" element="span class='notice'"/>
            </label>
        </p>
        <input type="submit" value="<spring:message code="register.btn"/>"/>
    </form:form>
</body>
</html>

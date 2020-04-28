<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-27
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <spring:message code="register.done">
        <spring:argument value="${registerRequest.name}"/>
        <spring:argument value="${registerRequest.email}"/>
    </spring:message>
</p>
    <p><a href="<c:url value="/main"/>">[<spring:message code="go.main"/>]</a></p>
</body>
</html>

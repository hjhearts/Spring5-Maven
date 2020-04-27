<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-27
  Time: 오후 5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>응답 내용</p>
    <ul>
        <c:forEach var="response" items="${ansData.responses}" varStatus="status">
            <li>${status.index+1}번 문항: ${response}</li>
        </c:forEach>
    </ul>
    <p>응답자 위치 : ${ansData.res.location}</p>
    <p>응답자 나이 : ${ansData.res.age}</p>
</body>
</html>

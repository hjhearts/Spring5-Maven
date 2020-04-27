<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-27
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>설문조사</h2>
    <form method="post">
        <c:forEach var="q" items="${questions}" varStatus="status">
            <p>
            ${status.index + 1}. ${q.title}<br/>
            <c:if test="${q.choice}">
            <c:forEach var="option" items="${q.options}">
                <label><input type="radio" name="responses[${status.index}]" value="${option}"/>${option}</label>
            </c:forEach>
            </c:if>
            </p>
        </c:forEach>
        <p>
            <label>응답자 위치<br/>
            <input type="text" name="res.location"/></label>
        </p>
        <p>
            <label>응답자 나이<br/>
            <input type="text" name="res.age"/></label>
        </p>
        <input type="submit" value="전송"/>
    </form>
</body>
</html>

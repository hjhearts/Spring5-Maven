<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-05-04
  Time: 오전 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>회원 조회</title>
    <style>
        .inline{display: inline-block}
        .error{color:red}
    </style>
</head>
<body>
    <form:form modelAttribute="cmd">
        <div>
            <div class="inline">
                <label>
                    from:<form:input path="from"/><br/>
                    <form:errors path="from" cssClass="error"/>
                </label>
            </div>
            <div class="inline">
            ~
            </div>
            <div class="inline">
                <label>
                    to:<form:input path="to"/><input type="submit" value="조회"/><br/>
                    <form:errors path="to" cssClass="error"/>
                </label>
            </div>
        </div>
    </form:form>
    <c:if test="${not empty members}">
        <table>
            <tr>
                <th>아이디</th><th>이메일</th>
                <th>이름</th><th>가입일</th>
            </tr>
            <c:forEach var="mem" items="${members}">
                <tr>
                    <td>${mem.id}</td>
                    <td>${mem.email}</td>
                    <td>${mem.name}</td>
                    <td><tf:formatDateTime value="${mem.registerDateTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>

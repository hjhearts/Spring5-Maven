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
<html>
<head>
    <title>Title</title>
    <style>
        .notice{color:red}
    </style>
</head>
<body>
    <h2>회원 정보 입력</h2>
    <form:form action="step3" modelAttribute="registerRequest">
        <p>
            <label>이메일<br/>
            <form:input path="email"/>
            </label>
            <c:if test="${registerRequest.notice eq 'duplicated'}">
                <br/><span class="notice">이미 가입된 이메일입니다.</span>
            </c:if>
        </p>
        <p>
            <label>이름<br/>
            <form:input path="name"/></label>
        </p>
        <p>
            <label>패스워드<br/>
            <form:password path="password"/></label>
            <c:if test="${registerRequest.notice eq 'checkPassword'}">
                <br/><span class="notice">패스워드를 다시 확인하세요.</span>
            </c:if>
        </p>
        <p>
            <label>패스워드 확인<br/>
            <form:password path="confirmPassword"/></label>
        </p>
        <input type="submit" value="가입 완료"/>
    </form:form>
</body>
</html>

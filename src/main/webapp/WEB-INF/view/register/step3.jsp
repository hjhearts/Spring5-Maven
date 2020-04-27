<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-27
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><strong>${formData.name}님</strong>회원 가입을 완료했습니다.</p>
    <p><a href="<c:url value="/main"/>">[첫 화면 이동]</a></p>
</body>
</html>

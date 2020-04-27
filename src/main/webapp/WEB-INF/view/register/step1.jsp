<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2020-04-27
  Time: 오후 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>약관</h2>
    <p>약관 내용</p>
    <form action="step2" method="post">
        <label>
            <input type="checkbox" name="agree" value="true"/>약관동의
        </label>
        <input type="submit" value="다음 단계"/>
    </form>
</body>
</html>

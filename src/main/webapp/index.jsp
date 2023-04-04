<%--
  Created by IntelliJ IDEA.
  User: 26591
  Date: 2023/3/11
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
   <title>Welcome</title>
</head>

<body>
    <div style="text-align: center">
        Welcome to my ssm project<br>
        <a href="${pageContext.servletContext.contextPath}/admin/login.jsp">登录</a>
    </div>
</body>
</html>

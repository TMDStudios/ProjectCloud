<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<c:forEach var="comment" items="${project.comments}">
	<p><strong><c:out value="${comment.user.username}"></c:out></strong></p>
	<p><c:out value="${comment.text}"></c:out></p>
	<hr>
</c:forEach>
</body>
</html>
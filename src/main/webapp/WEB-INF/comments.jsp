<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<c:forEach var="comment" items="${project.comments}">
	<p>
		<small>${comment.user.username} - <fmt:formatDate value="${comment.createdAt}" pattern="MM/dd/yy hh:mm"/></small>
	</p>
	<p><c:out value="${comment.text}"></c:out></p>
	<hr>
</c:forEach>
</body>
</html>
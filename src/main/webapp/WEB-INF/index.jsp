<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Project Cloud</title>
<!-- Favicon -->
<link rel="icon" href="../../resources/static/img/favicon.ico">
</head>
<body>

<ul class="navbar">
    <li class="nav_item"><a class="nav_link" href="/home">Home</a></li>
    <li class="nav_item"><a class="nav_link" href="/projects">View Projects</a></li>
    <c:if test="${userId!=null}">
    	<li class="nav_item"><a class="nav_link" href="/projects/new">Propose New Project</a></li>
		<li class="nav_item"><a class="nav_link" href="/my-projects">My Projects</a></li>
    	<li class="nav_login"><a class="nav_link" href="/logout">Log Out</a></li>
	</c:if>
	<c:if test="${userId==null}">
		<li class="nav_item"><a class="nav_link" href="/login">Log In</a></li>
		<li class="nav_login"><a class="nav_link" href="/register">Register</a></li>
	</c:if>    
</ul>

<h3>Welcome to Project Cloud!</h3>
<p>The goal of this website is to help users come up with great project ideas for their portfolio.</p>
<p>This is an open source project hosted on heroku.</p>
<p>For the source code, please visit <a href="https://github.com/TMDStudios/ProjectCloud">Project Cloud</a> on GitHub.</p>

<script type="text/javascript" src="../js/app.js"></script>

</body>
</html>
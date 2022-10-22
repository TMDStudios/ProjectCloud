<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>New Project</title>
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
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

<h3>New Project</h3>

<form:form action="/projects/new" method="post" modelAttribute="project">
	<input type="hidden" id="creator" name="creator" value="${userId}">

	<table>
	    <thead>
	    	<tr>
	            <td class="float-left">Project Name:</td>
	            <td class="float-left">
	            	<form:errors path="name" class="text-danger"/>
					<form:input class="input" path="name"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Project Summary:</td>
	             <td class="float-left">
	            	<form:errors path="summary" class="text-danger"/>
					<form:textarea rows="4" class="input" path="summary"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Project Description:</td>
	             <td class="float-left">
	            	<form:errors path="description" class="text-danger"/>
					<form:textarea rows="8" class="input" path="description"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Languages:</td>
	            <td class="float-left">
					<input class="input" name="listOfLanguages" id="listOfLanguages"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Tags (Example: game, match_3, puzzle):</td>
	            <td class="float-left">
					<input class="input" name="listOfTags" id="listOfTags"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=2><input class="input" class="button" type="submit" value="Submit"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>

<script type="text/javascript" src="../js/app.js"></script>

</body>
</html>
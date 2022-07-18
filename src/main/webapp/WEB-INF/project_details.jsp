<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Project Details</title>
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

<h3>Project Details</h3>
<p>Project Name: ${project.name}</p>
<p>Creator: ${project.creator.username}</p>
<p>Summary: ${project.summary}</p>
<p>Description: ${project.description}</p>
<p>Contributors: ${project.contributors.size()}</p>
<ul>
	<c:forEach var="contributor" items="${project.contributors}">
	    <li>${contributor.username}</li>
	</c:forEach>
</ul>

<iframe src="/comments/${id}" title="Activities Iframe"></iframe>
<br>
<p class="text-danger">${error}</p>
<h3>New Comment:</h3>
<form:form action="/project/${id}/comment" method="post" modelAttribute="comment">
	<input type="hidden" id="project" name="project" value="${project.id}">
	<table>
	    <thead>
	        <tr>
	            <td class="float-left">Comment:</td>
	            <td class="float-left">
	            	<form:errors path="text" class="text-danger"/>
					<form:input class="input" path="text"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=2><input class="input" class="button" type="submit" value="Submit"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>


<p><a href="/project/${id}/add">Join/Leave</a></p>
<c:if test="${project.creator.id==userId}">
	<p><a class="nav_link" href="/projects/${id}/edit">Edit Project</a></p>
</c:if>  

<script type="text/javascript" src="../js/app.js"></script>

</body>
</html>
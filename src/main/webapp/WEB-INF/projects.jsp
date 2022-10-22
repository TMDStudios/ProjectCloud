<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Projects</title>
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

<h3>Projects</h3>
<table>
    <thead> 
    	<tr>
    		<th>Project Name</th>
    		<th>Creator</th>
    		<th>Languages</th>
    		<th>Tags</th>
    		<th>Contributors</th>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="project" items="${projects}">
		<tr>
		    <td><a href="/projects/${project.id}">${project.name}</a></td>
		    <td>${project.creator.username}</td>
		    <td>
		    	<c:forEach var="language" items="${project.languages}">
		    		<button type="button">${language.name}</button>
		    	</c:forEach>
		    </td>
		    <td>
		    	<c:forEach var="tag" items="${project.tags}">
		    		<button type="button">${tag.name}</button>
		    	</c:forEach>
		    </td>
		    <td>${project.contributors.size()}</td>
		</tr>	
		</c:forEach>
    </tbody>
</table>

<script type="text/javascript" src="../js/app.js"></script>

</body>
</html>
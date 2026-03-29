<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Services</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/client/home">Client Home</a><br>
	<a href="${pageContext.request.contextPath}/admin/home">Admin Home</a><br><br>
	
	<h1>All Services</h1><br>
	
	<c:forEach var="service"  items="${listOfServices}">
	
			<img style="width:250px; height:auto" src="${pageContext.request.contextPath}/img/services/${service.filename}" alt=""><br>   
			<h4>${service.title}</h4>
			<p>${service.description}</p>
			
			
			<a href="${pageContext.request.contextPath}/admin/deleteService?id=${service.id}&filename=${service.filename}">Delete</a>   
			<a href="${pageContext.request.contextPath}/admin/updateService?id=${service.id}">Update</a> <br><br>
	
	</c:forEach>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/client/home">Client Home</a><br><br>
	<a href="${pageContext.request.contextPath}/admin/home">Admin Home</a><br><br>
	
	<h2>All Contacts</h2>
	
	<c:forEach var="contacts" items="${contactData}">
		${contacts.id}<br>
		${contacts.name}<br>
		${contacts.email}<br>
		${contacts.subject}<br>
		${contacts.message}<br>
		${contacts.datetime}<br>
		<a href="${pageContext.request.contextPath}/admin/deleteContactById?id=${contacts.id}">Delete</a> <br><br>
				 
	</c:forEach>
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"   %>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ADD SERVICE</h1>
		
		<a href="${pageContext.request.contextPath}/client/home">Client Home</a><br><br>
		<a href="${pageContext.request.contextPath}/admin/home">Admin Home</a><br><br>
	
	
		${result}
		
		<br><br>
	
		<form action="${pageContext.request.contextPath}/admin/addService" method="post" enctype="multipart/form-data" >
			
			<c:forEach var="err" items="${errors}">
				<c:if test="${err.field == 'title'}"> ${err.defaultMessage} </c:if>
			</c:forEach> 
			<br><input type="text" name="title" placeholder="Enter title"><br>
			
			
			
			<c:forEach var="err" items="${errors}">
					<c:if test="${err.field == 'description'}"> ${err.defaultMessage} </c:if>
			</c:forEach> 
			<br><textarea name="description" placeholder="Enter description"></textarea><br>
			
			${fileError}
			<br><input type="file" name="serviceFile"><br><br>
			<button>Save</button>
		</form>
</body>
</html>
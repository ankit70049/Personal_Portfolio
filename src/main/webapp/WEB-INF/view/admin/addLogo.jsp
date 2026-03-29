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
	<h1>ADD LOGO'S</h1>
	
		<a href="${pageContext.request.contextPath}/client/home">Client Home</a><br>
		<a href="${pageContext.request.contextPath}/admin/home">Admin Home</a><br><br>
	
	
		${result}
		
		<form action="${pageContext.request.contextPath}/admin/addLogo" method="post" enctype="multipart/form-data" >
		
			<input type="file" name="logo"><br>
			<button>Upload</button>
			
		</form>
</body>
</html>
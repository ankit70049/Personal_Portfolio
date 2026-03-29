<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		
		<a href="${pageContext.request.contextPath}/client/home">Client Home</a><br>
		<a href="${pageContext.request.contextPath}/admin/home">Admin Home</a><br><br>
		
		
		<form action="${pageContext.request.contextPath}/admin/updateService" method="post" enctype="multipart/form-data" >
			
			<input type="hidden" name="id" value="${serviceData.id}"><br>
			<input type="hidden" name="oldFileName" value="${serviceData.filename}"><br>
			<input type="text" name="title" value="${serviceData.title}"><br>
			<textarea name="description" >${serviceData.description}</textarea><br>
			<input type="file" name="serviceFile"><br><br>
			<button>Save</button>
			
		</form>



</body>
</html>
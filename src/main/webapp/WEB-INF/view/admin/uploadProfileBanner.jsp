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
	
		${result}
		<form action="${pageContext.request.contextPath}/admin/uploadProfileBanner" method="post" enctype="multipart/form-data">
		
			<input type="file" name="profileBanner"><br>
			<button>upload</button>
		
		</form>
		
	
</body>
</html>
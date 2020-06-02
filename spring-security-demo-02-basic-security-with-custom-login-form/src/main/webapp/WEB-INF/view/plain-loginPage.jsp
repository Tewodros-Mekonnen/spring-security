
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Page</title>
</head>
<body>
	<h3>My Custom Login Page</h3>
	
	<!-- ${pageContext.request.contextPath}/authenticateTheUser = gives us dynamic access to the name of context path. 
	     contextPath = "http://localhost:8081/spring-security-demo-02-basic-security". Since we are not hardCoding, 
	     even if we change our contextPath, program will run! 
	     authenticateTheUser = is defined in DemoSecurityConfig.java file-->
	<form:form  action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" >

		<p>
			User name: <input type="text" name="username">
			<!-- name has to be username and password. Spring will look for these name 
			when processing the form since they are default names -->
		</p>

		<p>
			Password: <input type="password" name="password">
		</p>
		
			<input type="submit" value="Login" >
			
	</form:form>
</body>
</html>
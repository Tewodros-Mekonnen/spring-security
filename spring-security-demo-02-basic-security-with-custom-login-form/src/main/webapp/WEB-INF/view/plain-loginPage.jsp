
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Page</title>

<style type="text/css">

	.header{
		color: blue;
	}
	.error-message{
		color: red;
		font-weight:100;
		font-size: large;
		letter-spacing: .03em;
	}
	
</style>

</head>
<body>
	<h3 class="header" >My Custom Login Page</h3>
	
	<!-- ${pageContext.request.contextPath}/authenticateTheUser = gives us dynamic access to the name of context path. 
	     contextPath = "http://localhost:8081/spring-security-demo-02-basic-security". Since we are not hardCoding, 
	     even if we change our contextPath, program will run! 
	     authenticateTheUser = is defined in DemoSecurityConfig.java file-->
	     
	     <div id="form" class="container">
			<form:form  action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" >
			
			<!-- Check for login error. By default, spring security will send the user back to the login page and 
				 append an error(?error) parameter to the URL
				 . e.x. http://localhost:8081/spring-security-demo-02-basic-security-with-custom-login-form/showMyLoginPage?error -->
				 <c:if test="${param.error != null}">
				 	<i class="error-message"  >Sorry! invalid username/password!</i>
				 </c:if>
		
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
	</div>
</body>
</html>
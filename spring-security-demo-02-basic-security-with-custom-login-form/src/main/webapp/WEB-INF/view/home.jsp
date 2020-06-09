<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>luv2code Company Home Page</title>
</head>
<body>
	<h2>luv2code Company Home Page</h2>
	<p>Welcome to luv2code Company Home Page!</p>
	
	<!-- display user name and role. This requires adding dependency inside pom.xml file and we need to add 
	     security tag library on top of this file -->
	     <hr> <!-- horizontal rule -->
		     <p>
		     	User: <security:authentication property="principal.username"/>
		     	<br><br>
		     	Role(s): <security:authentication property="principal.authorities"/> <!-- authorities is same as role! -->
		     </p>
	     
	     
	     	<!-- by adding  <security:authorize access="hasRole('MANAGER')"> </security:authorize>, we can prevent displaying 
	     	     content that can not be accessed by the user. All we need to do is just add the above tag in this page, no other 
	     	     setup!-->
		     <!-- add a link to point to " /leaders " ... this is for the managers! -->
		     <security:authorize access="hasRole('MANAGER')" >
		    	 <p>
			     	<a href="${pageContext.request.contextPath}/leaders" >LeaderShip Meeting</a>
			     	(Only for Managers!)
			     </p>
		     </security:authorize>
			     
			     
			  <!-- add a link to point to "/systems"... this is for system administers only --> 
			  <security:authorize access="hasRole('ADMIN')" >
				  <p>
				 	 <a href="${pageContext.request.contextPath}/systems" >IT Systems Meeting </a>(Only for IT Administrators!)
				  </p>
			  </security:authorize>
	     <hr>

	<!-- add logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout"/>

	</form:form>


</body>
</html>
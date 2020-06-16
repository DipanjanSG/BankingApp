<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body style=background-color:orange;>
<form action="LoginPageServlet" >
<p>

<h2> Bank of Edureka  </h2> <br> <br>

<label for="uname">Username:</label>  <input type = "text" id = "uname" name = "uname"> <br> <br>
	<label for="password">Password:</label>  <input type = "password" id = "password" name = "password"> <br> <br>
	
	 <c:if test="${ invalidCredentials eq true}">
         <h5 style="color:red;"> Invalid Credentials </h5>
 	 </c:if>
 
    <br><br>
	<input type="submit" value="Submit">
</form>

</body>
</html>
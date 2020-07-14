<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<%@ page import="com.banking.constants.Constants" %>

<html>
<head>
<title>Insert title here</title>
</head>
<body style=background-color:orange;>
<div style="margin-left: 40em";>
<form action="LoginPageServlet" method="post">


<h2 style="margin-left: 2em";> Bank of Edureka  </h2> <br> <br> 

<label  for="uname">Username:</label>  <input  type = "text" id = "uname" name = "uname" placeholder="neil"> <br> <br>
<label  for="password">Password:</label>  <input  style="margin-left: .3em"; type = "password" id = "password" name = "password" placeholder="neil123"> <br> <br>
	
	 <c:if test="${ invalidCredentials eq true}">
         <h5 style="background-color:black;color: white;"> ${Constants.LOGIN_INVALID_CREDENTIALS} </h5>
 	 </c:if>
    <br><br>
	<input style="margin-left: 8em"; type="submit" value="Submit">
</form>
<c:if test="${ failedDBConnection eq true}">
         <h5 style="background-color:black;color: red;"> ${Constants.UNREACHABLE_SERVER} </h5>
 	 </c:if>
 	 </div>
</body>
</html>
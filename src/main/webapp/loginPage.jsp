<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body style=background-color:orange;>
<div style="margin-left: 40em";>
<form action="LoginPageServlet" method="post">


<h2 style="margin-left: 2em";> Bank of Edureka  </h2> <br> <br> 

<label  for="uname">Username:</label>  <input  type = "text" id = "uname" name = "uname"> <br> <br>
<label  for="password">Password:</label>  <input  style="margin-left: .3em"; type = "password" id = "password" name = "password"> <br> <br>
	
	 <c:if test="${ invalidCredentials eq true}">
         <h5 style="background-color:black;color: white;"> Invalid Credentials </h5>
 	 </c:if>
    <br><br>
	<input style="margin-left: 8em"; type="submit" value="Submit">
</form>
<c:if test="${ failedDBConnection eq true}">
         <h5 style="background-color:black;color: red;"> Unable to Connect to Database , check your Internet Connection or try after sometime </h5>
 	 </c:if>
 	 </div>
</body>
</html>
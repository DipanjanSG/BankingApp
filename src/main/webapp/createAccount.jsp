<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title> Bank of Edureka</title>
</head>
<body style='background-color:orange;'>
<form action='createAccountServlet' method="post">
<h2> Account Creation Screen </h2>

<c:if test="${customerNumber ge 0}"> <h5> Account created successfully with customer Id - ${customerNumber} </h5>
</c:if>

<label for='uname'>Name:     </label>  <input type = 'text' id ='uName' name ='uName'> <br> <br>
<label for='dateOfBirth'>DOB:      </label>  <input type = 'text' id ='dateOfBirth'name ='dateOfBirth'> <br> <br>
<label for='address'>Address:  </label>  <input type = 'text' id ='address' name ='address'> <br> <br>
<label for='emailId'>Email Id: </label>  <input type = 'text' id ='emailId' name ='emailId'> <br> <br>
<label for='bankAccountType'>Type Of Account:</label> <select name='bankAccountType' size='1'>
		  <option value='volvo'>Savings</option>
		  <option value='current'>Current</option>
</select> <br>
<c:if test="${ allDetailsNotEntered eq true}"> <h5 style="color:red;"> All Information not entered </h5> </c:if>
<input type='submit' value='Create Account'>
<c:if test="${accountNotCreated eq true}"> <h5 style="color:red;"> Account has not been created for - ${userName}</h5></c:if>
</body>
</form>
		

</html>
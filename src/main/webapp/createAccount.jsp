<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title> Bank of Edureka</title>
</head>
<body style='background-color:orange;'>
<div style="margin-left: 40em";>
<form action='createAccountServlet' method="post">
<h2 input> Account Creation Screen </h2>

<c:if test="${accountNumber ge 0}"> <h5 style="background-color:black;color: white;"> Account created successfully with Account Number - ${accountNumber} </h5></c:if>
<c:if test="${not empty credentials}"> <h5 style="background-color:black;color: white;">${credentials}</h5></c:if>

<c:if test="${not empty existingFields}"> <h5 style="background-color:black;color: red;"> Values entered against fields - ${existingFields} , ALREADY EXISTS. Enter different values</h5></c:if>
<label for='uname'>Name:     </label>  <input  style="margin-left: 1.7em"; type = 'text' id ='uName' name ='uName' placeholder="Dipanjan Sengupta"> <br> <br>
<label for='dateOfBirth'>DOB:      </label>  <input  style="margin-left: 2em"; type = 'text' id ='dateOfBirth'name ='dateOfBirth' placeholder="1994-01-01"> <br> <br>
<label for='address'>Address:      <br>              <textarea style="margin-left: 5.4em"; name ='address' cols="40" rows="5" id ='address' placeholder="FG-324/7 Belladur Bangalore-700097"></textarea></label><br><br>
<label for='emailId'>Email Id: </label>  <input  style="margin-left: .5em"; type = 'text' id ='emailId' name ='emailId' placeholder="dipanjan.sen@gmail.com"> <br> <br>
<label for='bankAccountType'>Type Of Account:</label> <select name='bankAccountType' size='1'>
		  <option value='savings'>Savings</option>
		  <option value='current'>Current</option>
</select> <br>
<c:if test="${ allDetailsNotEntered eq true}"> <h5 style="background-color:black;color: red;"> All Information not entered </h5> </c:if>
<br>
<input type='submit' value='Create Account'>
<c:if test="${accountNotCreated eq true}"> <h5 style="background-color:black;color: red;"> Account has not been created for - ${userName}</h5></c:if>
</body>
</form>
<c:if test="${ failedDBConnection eq true}">
         <h5 style="background-color:black;color: red;"> Unable to Connect to Database , check your Internet Connection or try after sometime </h5>
 	 </c:if>		
</div>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<%@ page import="com.banking.constants.Constants" %>
<html>
<head>
<title>Insert title here</title>
<style>
.labelClass{
    white-space:pre;
}
</style>
</head>

<body style=background-color:orange;>
<form action="displayStatementServlet" >
<p>
<h2 input style="margin-left: 30em";> Display Statement </h2> <br> <br> 
<c:if test="${ failedDBConnection eq true}">
         <h5 style="background-color:black;color: red;"> ${Constants.ACCOUNT_CREATION_SUCCESSFUL} </h5>
 	 </c:if>
Date Range: <br>
<label for="accountNumber">From:</label>  <input type = "text" id = "dateFrom" name = "dateFrom" placeholder="2020-01-23">
<label class = "labelClass" for="amount">          To:</label>           <input type = "text" id = "dateTo" name = "dateTo" placeholder="2020-12-31"> <br> <br>
<input type="submit" value="Display">
</p>
</form>

<c:if test="${not empty bankStatements}">
    

<table id = "accountDetails" border="3">
<tr> <th> Sl No.</th>  <th>Date</th>  <th>Description</th>  <th>Cheque No.</th> <th>Withdraw</th> <th>Deposit</th>  <th>Available Balance</th> </tr>


<c:forEach items="${bankStatements}" var ="bankStatement" varStatus="count">     
       <tr><td>${count.count}</td>
       <td>${bankStatement.dateOfTransaction}</td>
       <td>${bankStatement.description}</td>
       <td>${bankStatement.checqueNumber}</td>
           <c:choose>
           		<c:when test="${bankStatement.transactionType == \"debit\"}">
       				<td></td>
           			<td>${bankStatement.amount}</td>
    			</c:when>
    			
    			<c:otherwise>

           			<td>${bankStatement.amount}</td>
           			<td></td>
    			</c:otherwise>
		   </c:choose>
          
          <c:choose>
           		<c:when test="${bankStatement.fromAccount == loggedInUsersAccountNumber }">
       				<td>${bankStatement.fromAccountAmt}</td>
           			
    			</c:when>
    			
    			<c:otherwise>
           			<td>${bankStatement.toAccountAmt}</td>
    			</c:otherwise>
		   </c:choose>
           
       </tr>       
 </c:forEach>
 
 </c:if>
 <c:if test="${ not empty dateRange}">
      <h3 style="background-color:black;color: white;"> ${Constants.TRANSACTIONS_NOT_FOUND} "${dateRange}"<h3>
  </c:if>
 
</table> 

 
</body>
</html>
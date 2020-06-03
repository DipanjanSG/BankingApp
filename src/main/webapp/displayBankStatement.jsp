<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="displayStatementServlet" >
<p>
<a href = "dummy"></a> <br>

<h2> Display Statement </h2> <br> <br>

Date Range: <br>
<label for="accountNumber">From:</label>  <input type = "text" id = "dateFrom" name = "dateFrom"> 
<label for="amount">To:</label>           <input type = "text" id = "dateTo" name = "dateTo"> <br> <br>
<input type="submit" value="Display">
</p>
</form>
<table id = "accountDetails" border="1">
<tr> <th> Sl No.</th>  <th>Date</th>  <th>Description</th>  <th>Cheque No.</th> <th>Deposit</th>  <th>Available Balance</th> </tr>

 <c:forEach items="${bankStatements}" var ="bankStatement" >
      
       <tr><td>${bankStatement.transactionId}</tr></td>
       
 </c:forEach>
 
</table>

 
</body>
</html>
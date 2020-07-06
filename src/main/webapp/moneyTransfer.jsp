<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<%@ page import="com.banking.constants.Constants" %>

<head>
<title> Bank of Edureka</title>
</head>
<body style='background-color:orange;'>

<h2 input style="margin-left: 30em";> Perform Debit or Credit Operation </h2> 
<form action = "PerformTransactionsServlet" method = "post">
<label for="accountNumber">Account Number:</label>  <input type = "text" id = "accountNumber"   name = "accountNumber" placeholder="1"> <br> <br>
<label for="amount">Amount:</label>                 <input  style="margin-left: 4.4em"; type = "text" id = "amount"          name = "amount" placeholder="1000"> <br> <br>
                                                    <input type = "hidden" id = "transactionType" name = "transactionType" value = "notSet">
<button type="submit" onclick="setValueDepositOrWithDraw(this)" value="null" name = "debit" >Debit From</button>
<button type="submit" onclick="setValueDepositOrWithDraw(this)" value="null" name = "credit">Credit To</button>
</form>
<c:choose>
 				<c:when test="${not empty invalidDetails}">			
       				<h5 style="background-color:black;color: red;">${invalidDetails}</h5>       				
    			</c:when>
    			
           		<c:when test="${transactionSuccessful eq true}">				
       				<h5 style="background-color:black;color: white;">${Constants.FINANCIAL_TRANSACTION_OK}</h5>
    			</c:when>
    		
</c:choose>

<c:if test="${ failedDBConnection eq true}">
         <h5 style="background-color:black;color: red;"> ${Constants.UNREACHABLE_SERVER} </h5>
 	 </c:if>
</body>
<script type="text/javascript" >
function setValueDepositOrWithDraw(button) {
	          document.getElementById("transactionType").value = button.name;
          }        
</script>
</html>
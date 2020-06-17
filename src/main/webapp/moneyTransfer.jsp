<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<head>
<title> Bank of Edureka</title>
</head>
<body style='background-color:orange;'>
<p>
<h3> Perform Debit or Credit Operation </h3>
<form action = "PerformTransactionsServlet" method = "get">
<label for="accountNumber">Account Number:</label>  <input type = "text" id = "accountNumber"   name = "accountNumber"> <br> <br>
<label for="amount">Amount:</label>                 <input type = "text" id = "amount"          name = "amount"> <br> <br>
                                                    <input type = "hidden" id = "transactionType" name = "transactionType" value = "notSet">
<button type="submit" onclick="setValueDepositOrWithDraw(this)" value="null" name = "debit" >Debit From</button>
<button type="submit" onclick="setValueDepositOrWithDraw(this)" value="null" name = "credit">Credit To</button>
</form>
<c:choose>
 				<c:when test="${invalidDetails eq true}">			
       				<h5 style="color:red;">Invalid Details Entered</h5>       				
    			</c:when>
    			
           		<c:when test="${transactionSuccessful eq true}">				
       				<h5>Transaction successful</h5>
    			</c:when>
    		
</c:choose>
</p>
<c:if test="${ failedDBConnection eq true}">
         <h5 style="color:red;"> Unable to Connect to Database , check your Internet Connection or try after sometime </h5>
 	 </c:if>
</body>
<script type="text/javascript" >
function setValueDepositOrWithDraw(button) {
	          document.getElementById("transactionType").value = button.name;
          }        
</script>
</html>
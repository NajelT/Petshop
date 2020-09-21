<%@ page import="com.buseduc.javacourse.petshop.Petshop" %>
<%@ page import="com.buseduc.javacourse.petshop.users.Customer" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.String" %>
<%
  Petshop shop = (Petshop) request.getAttribute("petshop");
  Map<String, Customer> customerList = (Map<String, Customer>) request.getAttribute("customerList");
  if(customerList == null) {
    customerList = new HashMap<>();
  }
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<h1>Welcome to petshop: <%=shop.getName()%></h1>
<form method="POST" action="/shop">
    <p>Enter your name:</p>
    <p><input type="text" name="customerName"></p>
    <p>Enter your balance:</p>
    <p><input type="text" name="amount"></p>
    <p>Enter your age:</p>
    <p><input type="number" name="age"></p>
    <p><button type="submit" value="SEND">SEND</button></p>
</form>
<ol>
<% for(Customer customer: customerList.values()) { %>
    <li><%=customer.getName()%> - <%=customer.getAvailableMoney()%> EUR - <%=customer.getAge()%></li>
<% } %>
</ol>
</body></html>

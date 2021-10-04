<html>
<head>
<title>Sac</title>
</head>
<body>
<%@ page import="web.Bag" %>
<%@ page session="true" %>
<% Bag myBag = (Bag)request.getAttribute("bag"); %>
<h1>sac</h1>
<% if (myBag!=null) myBag.print(out);%>
<form method='POST' action='bag'>
  <label for='ref'>Ref</label>
  <input type='text' id='ref' name='ref'/><br/>
  <label for='qty'>Qty</label>
  <input type='text' id='qty' name='qty'/><br/>
  <input type='submit' value='send'>
</form>
</body>
</html>

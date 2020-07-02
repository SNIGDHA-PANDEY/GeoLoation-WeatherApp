<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location Details</title>
</head>
<body>
<nav>
<a href="index.jsp">Home</a> | <a href="about.jsp">About</a> | <a href="loc.jsp">Location</a></nav>

<div align='center'>
<h4>Get Location</h4>
<form action="fetch" method="get">

   <label>Enter a place:</label><input type="text" name="loc"></br>
   <input type="submit" name="Find">
</form>
</div>
</body>
</html>
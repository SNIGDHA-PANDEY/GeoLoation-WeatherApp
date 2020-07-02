<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Details</title>
</head>
<body>
<nav>
<a href="index.jsp">Home</a> | <a href="about.jsp">About</a> | <a href="loc.jsp">Location</a></nav>
<div align="center">
<h3>Current Weather Details</h3>
<table >
 <tr>
    <th>Location</th>
    <td>${loc}</td> 
    </tr>
    <tr>
    <th>Temperature</th>
     <td>${temp} K</td> 
    </tr>
     <tr>
    <th>Minimum Temperature</th>
     <td>${min} K</td> </tr>
     
     <tr>
    <th>Maximum Temperature</th>
     <td>${max} K</td> </tr>
    <tr>
    <th>Pressure</th>
     <td>${pre} </td> </tr>
  
    
     <tr>
    <th>Humidity</th>
     <td>${hum} </td> </tr>
  
 
</table></br>
<a href="loc.jsp"><button>Enter a new Location</button></a>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather App</title>
</head>
<body>
<nav>
<a href="index.jsp?location=${location}">Home</a> | <a href="about.jsp">About</a> | <a href="loc.jsp">Location</a></nav>
<div align="center">
<p>Geo-Coordinates for ${location}</p>
<p >Longitude : ${Longitude}</p>
<p>Latitude : ${Latitude}</p>
</div>
<div align='center'>
<form action="weather" method="get">
   <input type="hidden" name="location" value="${location}" />
    <input type="hidden" name="Longitude" value="${Longitude}" />
     <input type="hidden" name="Latitude" value="${Latitude}" /></br>
   <input type="submit" name="Find Weather" value="Find Weather">   
</form>
<div align='center'><a href="loc.jsp"><button>Enter a new Location</button></a></div>
</div>
</body>
</html>
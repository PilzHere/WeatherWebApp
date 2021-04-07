<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Index</title>

<link href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css" rel="stylesheet">
  <script src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>

</head>
<body>

<form action="<%= request.getContextPath() %>/OWServlet" method="get">
<h3>
City: <input name="cityName" type="text">
Country: <input name="countryName" type="text">
</h3>
		<button type="submit" class="mdc-button mdc-button--raised">  <span class="mdc-button__ripple"></span> Show weather</button>
</form>

</body>
</html>
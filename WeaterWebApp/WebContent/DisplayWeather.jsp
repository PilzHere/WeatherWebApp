<%@page import="model.WeatherBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display weather</title>

<link href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css" rel="stylesheet">
  <script src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>

</head>
<body>

<%
WeatherBean weatherBean = (WeatherBean)request.getAttribute("weatherBean");
Cookie cookies[] = request.getCookies();

out.print("<h3>" + (weatherBean.getCountryName()) + "<br />");
out.print((weatherBean.getCityName() + "<br />"));
final float weatherInCelsius = Float.parseFloat(weatherBean.getTempKelvin()) - 273.15f;
out.print(weatherInCelsius + "°C<br />");
out.print(weatherBean.getDate().substring(0, weatherBean.getDate().length() - 9) + "<br />");
out.print(weatherBean.getCloudsStr() +"</h3>");

out.println("<br />Cookies: " + "<br />");
for (int i = 0; i < cookies.length; i++) {
	if(!cookies[i].getName().equals("JSESSIONID")){
		String[] splitCookie = cookies[i].getValue().split("/");
		
		out.println(splitCookie[0]);
		out.println(splitCookie[1]);
		out.println(splitCookie[2]);
	}
}
%>

<br />
<br />
<a href= "Index.jsp">
<button class="mdc-button mdc-button--raised">  <span class="mdc-button__ripple"></span> Search again</button>
</a>

</body>
</html>
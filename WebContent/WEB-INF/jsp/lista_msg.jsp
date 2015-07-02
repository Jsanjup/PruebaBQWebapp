<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bq.prueba.Mensaje"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
<h1> ${title}</h1>

<h2>Hay ${mensajes.size()} mensajes </h2>

<c:forEach items="${mensajes}" var="msg">     
   <h2>${msg.getAuthor()}</h2>
   <h3>${msg.getText()}</h3>
</c:forEach>



</body>
</html>
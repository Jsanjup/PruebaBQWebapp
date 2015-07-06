<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="bq.prueba.model.Mensaje"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
<h1> ${title}</h1>

<h2>Hay ${mensajes.size()} mensajes de ${autores.size()} autores distintos</h2>

<c:forEach items="${mensajes}" var="msg">     
   <h2>${msg.getAuthor()}</h2>
   <h3>${msg.getText()}</h3>
</c:forEach>



Filtrar por autor:

<form method="post" action="filterAuthor">
<select name="authorlist">
<option value="none" disabled selected>Seleccione un usuario</option>
<c:forEach items="${autores}" var="autor">   
  <option value="${autor}">${autor}</option>
</c:forEach>
<option value="all">Ver todos</option>
</select>
<input type="submit">
</form>

<form method="GET" action="csvExport">
<input type="submit" value="Exportar CSV"> 
</form>
</body>
</html>
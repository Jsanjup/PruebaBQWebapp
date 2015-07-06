<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>${title}</title>
</head>
<body>
<h1> ${title}</h1>
<form method="post" action= "addMessage">
  Autor: <input type="text" name="author"><br>
  Texto: <input type="text" name="text"><br>
  <input type="submit" value="Submit">
</form>
</body>
<footer>${alert_msg}</footer>
</html>
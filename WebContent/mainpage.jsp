<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">
</head>
<body  bgcolor="#F0F8FF">
<center><font size="4"><b><bean:message key="mainpage"/></b></font></center>

<div class="iti">
	<b><bean:write name="LoginForm" property="namae" /><bean:message key="sama"/></b><br />
	<b><bean:message key="loginend"/></b>
</div>

</body>
</html:html>
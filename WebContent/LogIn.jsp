<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
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
<body bgcolor="#f0f8ff">
<html:form action="/login">

	<table border="0" class="layout">

		<tr><th align="left" colspan=2><font size=4><b> <bean:message key="system"/> </b></font></th></tr>
		<tr><td align="left" colspan=2><font size=4><b> <bean:message key="loginga"/> </b></font></td></tr>
		<tr><td align="left" > <bean:message key="userid"/> </td>
			<td>
				<font size=4>
				<html:text property="id" maxlength="5" size="27" style="font-size: 17px;"/>
				</font>
			</td>
		</tr>
		<tr>
			<td align="left" >
				<bean:message key="pass"/>
			</td>
			<td>
				<html:password property="pass" size="30" maxlength="20"/>
			</td>
		</tr>
		<tr><td align="right" colspan=2> <html:submit styleClass="button3"> <bean:message key="login"/> </html:submit></td></tr>
	</table>

	<font style="color:red"><html:errors/></font>
</html:form>
</body>
</html:html>
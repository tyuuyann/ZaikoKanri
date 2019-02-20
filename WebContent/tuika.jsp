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
<body bgcolor="#F0F8FF">
<bean:message key="tuikazikkou"/>

<html:form action="/tuika">
	<table border="0" class="layout">
		<tr>
			<td class="jutyu"><bean:message key="maker"/></td>
			<td class="jutyu">
				<html:text property="maker" value=""></html:text>
			</td>
		</tr>
		
		<tr>
			<td><bean:message key="code"/></td>
			<td>
				<html:text property="code" value=""></html:text>
			</td>
		</tr>
		
		<tr>
			<td><bean:message key="name"/></td>
			<td>
				<html:text property="s_name" value=""></html:text>
			</td>
		</tr>
		
		<tr>
			<td><bean:message key="tanka"/></td>
			<td> <html:text property="tanka" value=""></html:text> </td>
		</tr>
		
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
		
		<tr><td align="center" colspan=2><html:submit><bean:message key="tuika"/> </html:submit></td></tr>
		<tr>
			<td colspan="2">
				<center><font style="color: red"> <html:errors/></font></center>
				<html:messages id="msg" message="true">
					<center><bean:write name="msg" ignore="true"/></center>
				</html:messages>
			</td>
		</tr>
	</table>
	</html:form>
</body>
</html:html>
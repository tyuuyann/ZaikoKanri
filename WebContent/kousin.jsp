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
<body bgcolor="#f0f8ff">
<bean:message key="skenter"/>
<html:form action="/kousinend">

 <table border="0" class="layout">
 	<tr>
 		<td class="jutyu"><bean:message key="maker"/></td>
		<TD class="jutyu"><html:text name="SyoukaiForm" property="makena"  /> <html:hidden name="SyoukaiForm" property="makena"/> </TD>
	</tr>
	
	<tr>
 		<td><bean:message key="code"/></td>
		<TD><html:text name="SyoukaiForm" property="syoco"  /><html:hidden name="SyoukaiForm" property="syoco"/></TD>
	</tr>
	
	<tr>
 		<td><bean:message key="name"/></td>
		<TD><html:text name="SyoukaiForm" property="syona"  /><html:hidden name="SyoukaiForm" property="syona"/></TD>
	</tr>
	
	<tr>
 		<td><bean:message key="tanka"/></td>
		<TD><html:text name="SyoukaiForm" property="tan"  /><html:hidden name="SyoukaiForm" property="tan"/></TD>
	</tr>
	
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr><td colspan="2" align="center"> <html:submit> <bean:message key="kousin"/> </html:submit> </td></tr>
	
	<tr>
		<td colspan="2">
			<center><font style="color: red"> <html:errors/> </font></center>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>
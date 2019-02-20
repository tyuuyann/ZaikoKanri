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
<body  bgcolor="#F0F8FF">
<html:form action="/syoukai">
	<logic:equal name="selectSyoukai" value="ZAIKOSYOUKAI"> <bean:message key="zaikokensaku"/> </logic:equal>
	<logic:equal name="selectSyoukai" value="SYOUKAI"> <bean:message key="syoukaikensaku"/> </logic:equal>
	<logic:equal name="selectSyoukai" value="KOUSIN"> <bean:message key="kousinkensaku"/> </logic:equal>
	<logic:equal name="selectSyoukai" value="SAKUJO"> <bean:message key="sakujokensaku"/> </logic:equal>


		<table border="0" class="layout" >
			<tr>
				<td  ><bean:message key="maker"/></td>
				<td  ><html:text property="maker" value=""/></td>
				<td  ><html:radio property="fuku0"  value="0" /><bean:message key="fukumu"/></td>
				<td  ><html:radio property="fuku0" value="1" /><bean:message key="nofukumu"/></td>
			</tr>
			<tr>
				<td><bean:message key="code"/></td>
				<td><html:text property="code" value=""/></td>
				<td><html:radio property="fuku1" value="0" /><bean:message key="fukumu"/></td>
				<td><html:radio property="fuku1" value="1" /><bean:message key="nofukumu"/></td>
			</tr>
			<tr>
				<td><bean:message key="name"/></td>
				<td><html:text property="s_name" value=""/></td>
				<td><html:radio property="fuku2" value="0" /><bean:message key="fukumu"/></td>
				<td><html:radio property="fuku2" value="1" /><bean:message key="nofukumu"/></td>
			</tr>
			<tr>
				<td><bean:message key="tanka"/></td>
				<td><html:text property="tanka" value=""/></td>
				<td><html:radio property="ijou0" value="3" /><bean:message key="ijou"/></td>
				<td><html:radio property="ijou0" value="4" /><bean:message key="miman"/></td>
			</tr>
			<logic:equal name="selectSyoukai" value="ZAIKOSYOUKAI">
			<tr>
				<td><bean:message key="zaikosu"/></td>
				<td><html:text property="zaikosuu" value=""/></td>
				<td><html:radio property="ijou1" value="3" /><bean:message key="ijou"/></td>
				<td><html:radio property="ijou1" value="4" /><bean:message key="miman"/></td>
			</tr>
			</logic:equal>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			
			<tr>
				<td colspan="3">&nbsp;</td>
				<td><html:submit> <bean:message key="kensaku"/> </html:submit></td>
			</tr>
			<tr>
				<td colspan="4">
					<FONT color="red"><html:errors/></FONT>
					<html:messages id="msg" message="true">
				 		<center><bean:write name="msg" ignore="true"/></center>
					</html:messages>
				</td>
			</tr>
		</table>

<html:hidden value="KENSAKU" property="command"/>
</html:form>
</body>
</html:html>
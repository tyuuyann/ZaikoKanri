<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
<script type="text/javascript"><!--
	function kakuninn(){
		return confirm('削除しますか？');
	}
// --></script>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">
</head>
<body bgcolor="#f0f8ff">
<logic:equal name="selectSyoukai" value="ZAIKOSYOUKAI"> <bean:message key="zsitiran"/> </logic:equal>
<logic:equal name="selectSyoukai" value="SYOUKAI"> <bean:message key="syoukaiitiran"/> </logic:equal>
<logic:equal name="selectSyoukai" value="KOUSIN"> <bean:message key="kousinitiran"/> </logic:equal>
<logic:equal name="selectSyoukai" value="SAKUJO"> <bean:message key="sakujoitiran"/> </logic:equal>


	<table class="tableSample">
		<tr class="width" >
			<th class="thSample"> <bean:message key="maker"/> </th>
			<th class="thSample"><bean:message key="code"/></th>
			<th class="thSample"><bean:message key="name"/></th>
			<th class="thSample"><bean:message key="tanka"/></th>
			<logic:equal name="selectSyoukai" value="ZAIKOSYOUKAI">
			<th class="thSample"><bean:message key="zaikosu"/></th>
			</logic:equal>
		</tr>
		
			<html:form action="/kou_senta">
				<logic:iterate id="syo" name="syoukai" length="10" offset="start">
					<tr style="background-color: #ffffff ">
						<td class="tdSample"> <bean:write name="syo" property="makena"/> <html:hidden name="syo" property="makena"/> </td>
						<td class="tdSample"> <bean:write name="syo" property="syoco" /> <html:hidden name="syo" property="syoco"/></td>
						<td class="tdSample"> <bean:write name="syo" property="syona" /> <html:hidden name="syo" property="syona"/></td>
						<td class="tdSample"> <bean:write name="syo" property="tan" /> <html:hidden name="syo" property="tan"/></td>
						
						<logic:equal name="selectSyoukai" value="ZAIKOSYOUKAI">
						<td class="tdSample"> <bean:write name="syo" property="zai" /></td>
						</logic:equal>
						
						<logic:equal name="selectSyoukai" value="KOUSIN">
						<td class="tdSample"> <html:submit> <bean:message key="sentaku"/></html:submit></td>
						<html:hidden property="kousin" value="on" />
						</logic:equal>
						
						<logic:equal name="selectSyoukai" value="SAKUJO">
						<td class="tdSample"> <html:submit onclick="return kakuninn()"> <bean:message key="sentaku"/> </html:submit></td>
						<html:hidden property="kousin" value="delete" />
						</logic:equal>
					</tr>
				</logic:iterate>
				
			</html:form>
		
		<html:form action="/syoukai">
			<tr >
				<td colspan="3" align="right" bgcolor="#f0f8ff" ><logic:equal name="SyoukaiForm" property="backButton" value="true">
					<html:submit property="command" value="BACK"/>
				</logic:equal></td> 
				<td bgcolor="#f0f8ff"><logic:equal name="SyoukaiForm" property="nextButton" value="true">
					<input type="submit" name="command" value="NEXT"/>
					</logic:equal>
				</td>
			</tr>
	</table>
	<html:hidden name="SyoukaiForm" property="nowPage" />
</html:form>
</body>
</html:html>
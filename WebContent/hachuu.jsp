<%--
!!!!	受注と発注は ほとんど同じ画面ですので、共通化しましょう。
!!!!	また、検索画面や一覧画面など、似たような画面はできるだけ
!!!!	共通化してみましょう。

!!!!	ソース中にコメント以外の日本語を記述しないようにしましょう。
!!!!	MessageResources.propertiesファイルに設定しておき、
!!!!	<bean:message>で表示します。
--%>

<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">
<script type="text/javascript">
<!--
	function test(Maker){
		// 引数の値をselectListにセット
		document.TasuForm.selectList.value = Maker;
		
		// formをsubmit
		document.TasuForm.submit();
	}
//-->
</script>
</head>
<body bgcolor="#F0F8FF">

<html:form action="/hachuu">
<logic:equal name="TasuForm" property="selectSub" value="HATYU"><bean:message key="hatoga"/></logic:equal>
<logic:equal name="TasuForm" property="selectSub" value="JUTYU"><bean:message key="jutoga"/></logic:equal>

	<table border="0" class="layout">
		<tr>
			<td class="jutyu"> <bean:message key="maker"/> </td>
			<td class="jutyu">
				<html:select property="makerName" onchange="test('0')" styleClass="haba" >
					<html:optionsCollection name="makerList"/>
				</html:select>
			</td>
		</tr>
		
 		<tr>
			<td> <bean:message key="code"/> </td>
			<td>
				<html:select property="shouhinCode" onchange="test('1')" styleClass="haba" >
					<html:optionsCollection name="syoucodeList"/>
				</html:select>
			</td>
		</tr>
		
		<tr>
			<td><bean:message key="name"/></td>
			<td>
				<html:select property="shouhinName" onchange="test('2')" styleClass="haba" >
					<html:optionsCollection name="syounameList"/>
				</html:select>
			</td>
		</tr>
		
		<tr>
			<td>
				<logic:equal name="TasuForm" property="selectSub" value="HATYU">
					<bean:message key="hattyunum"/>
				</logic:equal>
				<logic:equal name="TasuForm" property="selectSub" value="JUTYU">
					<bean:message key="juttyunum"/>
				</logic:equal>
			</td>
			<td> <html:text name="TasuForm" property="jutyu"/> </td>
		</tr>
		
		<tr><td align="center" colspan=2><html:button property="touroku" onclick="test('3')" > <bean:message key="touroku"/> </html:button></td></tr>
		<tr>
			<td align="center" colspan="2">
				<font style="color: red"> <html:errors/></font>
				<html:messages id="msg" message="true">
					<bean:write name="msg" ignore="true"/>
				</html:messages>
			</td>
		</tr>
		
	</table>
 		<html:hidden name="TasuForm" property="selectList"/> 

</html:form>
</body>
</html>
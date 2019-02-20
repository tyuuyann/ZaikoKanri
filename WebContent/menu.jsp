<%--
!!!!	ファイル名を分かりやすい名前にしておきましょう。
!!!!	ファイル名を見ただけでどんな画面かが分かるような名前にすると良いです。

!!!!	strutsのタグを使用するようにしてください。
!!!!		<form> --→ <html:form>
!!!!		<input type="submit"> --→ <html:submit>

!!!!	ボタンの形式をstyle属性でそれぞれに記述していますが、外部ファイルに定義し
!!!!	それを読み込む形での設定もできます。
!!!!	また、直書きと外部ファイルの両方を併用する事もできます。
!!!!	よって 複数の画面で共通する内容は外部ファイルに定義し、ある画面に特化した
!!!!	内容はJSPに直接記述すると良いでしょう。
!!!!
!!!!	■外部ファイルのstylesheetを利用する方法■
!!!!	１、xxx.cssのファイルを作成し、stylesheetの設定情報を記述する。
!!!!	(ex.)
!!!!	span.font2 {
!!!!		font-size: 120%;
!!!!		font-weight: bold;
!!!!	}
!!!!	２、各JSPの該当するタグに、指定したいstylesheetの名前を記述する。
!!!!	（ex.）<span class="font2">
--%>

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
<body bgcolor="#F0F8FF">
	<table border="0" align=left>
		<!-- <tr><th align=center><font size=4><bean:message key="zaikokanri"/></font></th></tr> -->
		<tr><td align=center><font size=5><bean:message key="menu"/><br></font></td></tr>

    <!-- 営業者メニュー -->
    <logic:equal name="LoginForm" property="authority" value="01">
	<html:form action="/syoukai" target="right">
		<tr><td align = "center"><html:submit  styleClass="button3"><bean:message key="zaikos"/></html:submit></td></tr>
		<html:hidden value="ZAIKOSYOUKAI" property="selectSyoukai" />
		<html:hidden value="1" property="hanbetu" />
	</html:form>

	<html:form action="/hachuu" target="right">
		<tr><td align = "center"><html:submit styleClass="button3"><bean:message key="jutyuk"/></html:submit></td></tr>
		<html:hidden value="JUTYU" property="selectSub" />
		<html:hidden value="on" property="push"/>
	</html:form>

	<html:form action="/hachuu" target="right">
		<tr><td align = "center"><html:submit styleClass="button3"><bean:message key="hatyuk"/></html:submit></td></tr>
		<html:hidden value="HATYU" property="selectSub" />
		<html:hidden name="TasuForm" value="on" property="push"/>
	</html:form>

	<html:form action="/kengen">
		<tr><td align = "center"><html:submit property="super0" styleClass="button3"><bean:message key="syouhink"/></html:submit></td></tr>
	</html:form>

	<!--
	<html:form action="/syoukai" target="right">
		<tr><td align=center><html:submit styleClass="button3"><bean:message key="syoukai"/> </html:submit></td></tr>
		<html:hidden value="SYOUKAI" property="selectSyoukai" />
		<html:hidden value="1" property="hanbetu" />
	</html:form>

	<html:form action="/tuika" target="right">
		<tr><td align=center><html:submit styleClass="button3"><bean:message key="tuika"/> </html:submit></td></tr>
		<html:hidden value="TUIKA" property="selectSyoukai"/>
	</html:form>

	<html:form action="/syoukai" target="right">
		<tr><td align=center><html:submit styleClass="button3"><bean:message key="kousin"/> </html:submit></td></tr>
		<html:hidden value="KOUSIN" property="selectSyoukai" />
		<html:hidden value="1" property="hanbetu" />
	</html:form>

	<html:form action="/syoukai" target="right">
		<tr><td align=center><html:submit styleClass="button3"><bean:message key="sakujo"/> </html:submit></td></tr>
		<html:hidden value="SAKUJO" property="selectSyoukai" />
		<html:hidden value="1" property="hanbetu" />
	</html:form>
	 -->
	</logic:equal>

    <!-- 技術者メニュー -->
    <logic:equal name="LoginForm" property="authority" value="02">
    <html:form action="/syoukai" target="right">
        <tr><td align = "center"><html:submit  styleClass="button3"><bean:message key="zaikos"/></html:submit></td></tr>
        <html:hidden value="ZAIKOSYOUKAI" property="selectSyoukai" />
        <html:hidden value="1" property="hanbetu" />
    </html:form>

    <html:form action="/hachuu" target="right">
        <tr><td align = "center"><html:submit styleClass="button3"><bean:message key="jutyuk"/></html:submit></td></tr>
        <html:hidden value="JUTYU" property="selectSub" />
        <html:hidden value="on" property="push"/>
    </html:form>

    <html:form action="/hachuu" target="right">
        <tr><td align = "center"><html:submit styleClass="button3"><bean:message key="hatyuk"/></html:submit></td></tr>
        <html:hidden value="HATYU" property="selectSub" />
        <html:hidden name="TasuForm" value="on" property="push"/>
    </html:form>

    <html:form action="/kengen">
        <tr><td align = "center"><html:submit property="super0" styleClass="button3"><bean:message key="syouhink"/></html:submit></td></tr>
    </html:form>
    </logic:equal>

	<html:form action="/logout" target="_top">
		<tr><td align = "center" top = "50"><html:submit property="log" styleClass="button3"> <bean:message key="logout"/> </html:submit></td></tr>
	</html:form>
	</table>

</body>
</html:html>
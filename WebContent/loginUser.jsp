<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
</head>
<body bgcolor="#F0F8FF">
    <table border="0" align=left>
        <tr>
            <td rowspan = "2" style="vertical-align: baseline; width: 250px;">
                <b><font size=4><bean:message key="businessSupport"/></font></b>
            </td>
            <td rowspan = "2" style="width: 300px;">
                <bean:write name="LoginForm" property="corpname" />
            </td>
            <td style="text-align:right; width:200px;">
                <bean:message key="logInUserID"/><bean:write name="LoginForm" property="id" />
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">
                <bean:message key="logInName"/><bean:write name="LoginForm" property="namae" />
            </td>
        </tr>
	</table>
</body>
</html>
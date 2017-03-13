<%@ page language="java" pageEncoding="UTF-8"%> 
<!DOCTYPE>
<html>

<head>
<%@ include file="/common/base.jsp"%>
<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/kindeditor.js"></script>
<link rel="stylesheet" href="${rootPath}kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="${rootPath}kindeditor-4.1.10/plugins/code/prettify.css" />
	<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/plugins/code/prettify.js"></script>
	
<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>
</head>

<body>
<form name="form" action="${rootPath}admin/login.do" method="post">
<table width="300" border="1">
    <tr>
        <td colspan="2">登录窗口</td>
    </tr>
    <tr>
            <td>用户名</td>
            <td><input type="text" name="name" size="10"></td>
    </tr>
    <tr>
            <td>密码</td>
            <td><input type="password" name="password" size="10"></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" name="submit" value="登录"> </td>
    </tr>
</table>
</form>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<%@ include file="/common/base.jsp"%>
<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/kindeditor-all-min.js"></script>
<link rel="stylesheet" href="${rootPath}kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="${rootPath}kindeditor-4.1.10/plugins/code/prettify.css" />
	<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/plugins/code/prettify.js"></script>
<!DOCTYPE>
<body>
<h2>Hello World!1111111111111111111111111</h2><c:if test="${1 == 1 }">xxxxxxxxxxxx</c:if>
<input id ="a" value="<%=basePath%>" >
<input id ="a" value="XXXXXXXXXXXXXX" >
<textarea id="content" name="content" style="width: 580px; height: 250px; visibility: hidden;"></textarea>
<br />
</body>

<form name="example" method="post" action="demo.jsp">
		<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
</html>
<script>
$("#a").click(function(){
	alert("x111111111111111xx");
})

KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '${rootPath}kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '${rootPath}upload_json.do',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
		});
</script>

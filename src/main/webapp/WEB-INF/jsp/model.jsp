<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<%@ include file="/common/base.jsp"%>
<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/kindeditor.js"></script>
<link rel="stylesheet" href="${rootPath}kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="${rootPath}kindeditor-4.1.10/plugins/code/prettify.css" />
	<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${rootPath}kindeditor-4.1.10/plugins/code/prettify.js"></script>
<!DOCTYPE>
<body>
<h2>Hello World!1111111111111111111111111</h2><c:if test="${1 == 1 }">xxxxxxxxxxxx</c:if>
<input id ="a" value="<%=basePath%>" >
<input id ="a" value="XXXXXXXXXXXXXX" >
<br />
</body>

<form name="example" method="post" action="aa.do">
		<textarea name="content" cols="100" rows="8" style="width:1700px;height:800px;visibility:hidden;">${content }</textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
</html>
<script>
$("#a").click(function(){
	alert("x111111111111111xx");
})

KindEditor.ready(function(K) {
			var editor = K.create('textarea[name="content"]', {
				cssPath : '${rootPath}kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '${rootPath}file/upload_json.do',
				fileManagerJson : '${rootPath}/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {/* 渲染完成之后 */
					this.sync();
					K.ctrl(document, 13, function() {
					});
					K.ctrl(this.edit.doc, 13, function() {
					});
				},
			});
		});
</script>

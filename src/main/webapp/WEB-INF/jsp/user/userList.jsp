<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>

<form id="pagerForm" method="post" action="${rootPath}/user/userList.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${data.pageNum}" />
	<input type="hidden" name="numPerPage" value="${data.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${rootPath}/user/userList.do" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>名字：</label>
				<input type="text" name="name" value="${user.name }"/>
			</li>
			<li>
				<label>手机号：</label>
				<input type="text" name="phone" value="${user.phone }"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="list" width="100%" layoutH="88">
		<thead>
			<tr>
				<th width="40">序号</th>
				<th width="80">用户名</th>
				<th width="80">手机号</th>
				<th width="80">状态</th>
				<th width="80">创建时间</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${data.list}" var="user" varStatus="xh">
			<tr>
				<td>${xh.count }</td>
				<td>${user.name }</td>
				<td>${user.phone }</td>
				<c:if test="${user.status == 1}">
				<td>正常</td>
				</c:if>
				<td><fmt:formatDate value="${user.createTime}" pattern="yyyy/MM/dd  HH:mm:ss"/></td>
				<td>
				<!-- <a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
				<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a> -->
			</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${data.total}条</span>
		</div>
	
	<div class="pagination" targetType="navTab" totalCount="${data.total}" numPerPage="${data.numPerPage}" pageNumShown="10" currentPage="${data.pageNum}"></div>
	</div>
	
</div>
<script type="text/javascript">
$(function(){
	$(".combox").val('${data.numPerPage}');
});
</script>

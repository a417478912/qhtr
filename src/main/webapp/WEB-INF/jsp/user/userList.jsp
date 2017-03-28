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
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		<table class="searchContent">
			<tr>
				<td>
					我的客户：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
					</select>
				</td>
				<td>
					建档日期：<input type="text" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="list" width="100%" layoutH="118">
		<thead>
			<tr>
				<th width="40">序号</th>
				<th width="80">用户名</th>
				<th>手机号</th>
				<th width="80">状态</th>
				<th width="80">创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${data.list}" var="user" varStatus="xh">
			<tr>
				<td>${xh.count }</td>
				<td>${user.name }</td>
				<td>${user.phone }</td>
				<td>${user.status }</td>
				<td><fmt:formatDate value="${user.createTime}" pattern="yyyy/MM/dd  HH:mm:ss"/></td>
				<td>
				<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
				<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
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

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
	<form onsubmit="return navTabSearch(this);" action="${rootPath}/admin/withdrawApplyList.do" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>店铺名：</label>
				<input type="text" name="storeName" value="${storeName }"/>
			</li>
			<li>
				<label>店铺手机号：</label>
				<input type="text" name="storePhone" value="${storePhone }"/>
			</li>
			<li>
				<label>申请状态 :</label>
				<select name="status">
				<option value="0">全部</option>
				<option value="1">待审核</option>
				<option value="2">已转账</option>
				<option value="3">审核失败</option>
				</select>
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
				<th width="5">序号</th>
				<th width="40">店铺名</th>
				<th width="40">店铺手机号</th>
				<th width="40">提现钱数(元)</th>
				<th width="40">提现方式</th>
				<th width="40">申请时间</th>
				<th width="40">申请状态</th>
				<th width="40">转账时间</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${data.list}" var="apply" varStatus="xh">
			<tr>
				<td>${xh.count }</td>
				<td>${apply.storeName }</td>
				<td>${apply.storePhone }</td>
				<td>${apply.money/100 }</td>
				<c:if test="${apply.payType == 1}">
					<td>支付宝</td>
				</c:if>
				<c:if test="${apply.payType == 2}">
					<td>微信</td>
				</c:if>
				<c:if test="${apply.payType == 3}">
					<td>银行卡</td>
				</c:if>
				<td><fmt:formatDate value="${apply.createTime}" pattern="yyyy/MM/dd  HH:mm:ss"/></td>
				<c:if test="${apply.status == 1}">
					<td>待审核</td>
				</c:if>
				<c:if test="${apply.status == 2}">
					<td>已转账</td>
				</c:if>
				<c:if test="${apply.status == 3}">
					<td>审核失败</td>
				</c:if>
				<td><fmt:formatDate value="${apply.transferAccountsTime}" pattern="yyyy/MM/dd  HH:mm:ss"/></td>
				<td>
				<%-- <a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a> 
				<a title="编辑" target="navTab" href="${rootPath}admin/applyDetails.do?id=${apply.id}" class="btnEdit">编辑</a> --%>
				<a class = "buttonActive" width="800" height="600" href="${rootPath}admin/applyDetails.do?id=${apply.id}" target="dialog"><span>详情</span></a>
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
	if('${status}' == ""){
		$("select[name='status']").val(0);
	}else{
		$("select[name='status']").val('${status}');
	}
});
</script>

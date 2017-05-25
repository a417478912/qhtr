<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>

<div class="pageContent">
		<div class="pageFormContent" layoutH="57">
			<dl>
				<dt>店铺名 ：</dt>
				<dd><input class="required" type="text" size="30" value="${apply. storeName}" readonly="readonly"/></dd>
			</dl>
			<dl>
				<dt>店铺手机号 ：</dt>
				<dd><input class="required" type="text" size="30" value="${apply. storePhone}" readonly="readonly"/></dd>
			</dl>
			<dl>
				<dt>提现钱数(元)  ：</dt>
				<dd><input class="required" type="text" size="30" value="${apply. money/100}" readonly="readonly"/></dd>
			</dl>
			<dl>
				<dt>申请时间：</dt>
				<dd><input class="required" type="text" size="30" value='<fmt:formatDate value="${apply. createTime}" pattern="yyyy/MM/dd  HH:mm:ss"/>' readonly="readonly"/></dd>
			</dl>
			<dl>
				<dt>申请状态 ：</dt>
				<dd>
				<c:if test="${apply. status == 1}"><input class="required" type="text" size="30" value="待审核" readonly="readonly"/></c:if>
				<c:if test="${apply. status == 2}"><input class="required" type="text" size="30" value="已转账" readonly="readonly"/></c:if>
				<c:if test="${apply. status == 3}"><input class="required" type="text" size="30" value="审核失败" readonly="readonly"/></c:if>
				</dd>
			</dl>
			<dl>
				<dt>转账时间 ：</dt>
				<dd><input class="required" type="text" size="30" value='<fmt:formatDate value="${apply. transferAccountsTime}" pattern="yyyy/MM/dd  HH:mm:ss"/>' readonly="readonly" /></dd>
			</dl>
			<c:if test="${apply. payType == 1}">
			<dl>
				<dt>支付类型 ：</dt>
				<dd>
				<input class="required" type="text" size="30" value="支付宝" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt>支付宝账号 ：</dt>
				<dd>
				<input class="required" type="text" size="30" value="${apply.alipayName }" readonly="readonly"/>
				</dd>
			</dl>
			</c:if>
			<c:if test="${apply. payType == 2}">
			<dl>
				<dt>支付类型 ：</dt>
				<dd>
				<input class="required" type="text" size="30" value="微信" readonly="readonly"/>
				</dd>
			</dl>
			</c:if>
			<c:if test="${apply. payType == 3}">
			<dl>
				<dt>支付类型 ：</dt>
				<dd>
				<input class="required" type="text" size="30" value="银行卡" readonly="readonly"/>
				</dd>
			</dl>
				<dl>
				<dt>银行名字 ：</dt>
				<dd><input class="required" type="text" size="30" value="${apply. applyBankName}" readonly="readonly"/></dd>
			</dl>
			<dl>
				<dt>持卡人姓名 ：</dt>
				<dd><input class="required" type="text" size="30" value="${apply. applyName}" readonly="readonly"/></dd>
			</dl>
			<dl>
				<dt>银行卡号 ：</dt>
				<dd><input class="required" type="text" size="30" value="${apply. applyCode}" readonly="readonly"/></dd>
			</dl>
			</c:if>
			<dl>
				<dt>通过/失败原因 ：</dt>
				<dd><textarea id="reasonId" rows=4  cols=90 <c:if test='${apply. status != 1}'> readonly="readonly" </c:if>>${apply.reason }</textarea></dd>
			</dl>
		</div>
		<c:if test="${apply. status == 1 }">
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button id="successBtId" type="submit">转账成功</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button id="failBtId" type="button" class="close">审核失败</button></div></div></li>
			</ul>
		</div>
		</c:if>
</div>
<script type="text/javascript">
	$("#successBtId").click(function() {
		$.post("${rootPath}admin/applyResult.do", {
			id : "${apply.id}",
			type : 1,
			reason : $("#reasonId").val()
		}, function(data) {
			if (data.code == 200) {
				alertMsg.correct("转账成功!");
				$.pdialog.closeCurrent();
				navTab.reloadFlag("withdrawApplyListRel");
			}
		});
	});

	$("#failBtId").click(function() {
		$.post("${rootPath}admin/applyResult.do", {
			id : "${apply.id}",
			type : 2,
			reason : $("#reasonId").val()
		}, function(data) {
			if (data.code == 200) {
				alertMsg.correct("审核失败!");
				$.pdialog.closeCurrent();
				navTab.reloadFlag("withdrawApplyListRel");
			}
		});
	});
</script>

<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<%@ include file="common/base.jsp"%>
<script language="javascript" src="<%=basePath%>js/jquery-2.1.4.min.js"> </script>
<!DOCTYPE>
<body>
<h2>Hello World!</h2><c:if test="${1 == 1 }">xxxxxxxxxxxx</c:if>
<input id ="a" value="xxxxxxxxxxxxx" >
<input id ="b" value="yyyyyyyyyyyyyyyy" >
<input id ="c" value="zzzzzzzzzzzzzzz" >

<form action="${rootPath}/app_user/updateUser.do" id="d_id" method="post" enctype="multipart/form-data">
<input id ="d" name="avatar"  type="file">
<img alt="" src="/upload/userAvatar/20170328/20170328153240_249.png">
<input name="id" value="3" type="hidden">
<input type="submit" value="提交图片 ">
</form>

<form action="${rootPath}app_buycart/addBuyCart.do" method="post" >
	<input type="text" name="skuId" value=100>
	<input type="text" name="goodsId" value=1010>
	<input type="text" name=num value=12>
	<input type="submit" value="确定">
	
</form>
<div style="display: none" id="qq">xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</div>

<form action="${rootPath}app_user/updateAvatar.do" method="post" enctype="multipart/form-data">
	<div>
	<input type="file" name="avatar">
	<input type="text" name="id" value=1>
	<input type="submit" value="确定">
	<img alt="" src="${rootPath}/upload/userAvatar/20170316/20170316/20170316181239_832.png">
	</div>
	</form>
	
	<input type="radio" name="a" value=“你才1" >
	<input type="radio" name="a" value=“你才1" disabled="disabled">
	<input type="radio" name="a" value=“你才1" id="aaa_1">
</body>
</html>
<script>
$(function(){
	alert($("#qq").html());
	alert("撒地方"+"Asda"+"阿斯顿"+"爱飞");
	$("#aaa_1").attr("disabled","disabled");
});
var a = new Object();
$("#c").click(function(){
	var customerArray = new Array();
    customerArray.push({orderCode: "QHTRSO0000000120170324173451295",userRemark:"df方式撒的"}); 
    customerArray.push({orderCode: "QHTRSO0000000120170324173451387",userRemark:"不得不告诉你"});
    alert(JSON.stringify(customerArray))
	$.ajax({
        url: "${rootPath}app_order/addOrders.do?userId=1",
        type: "POST",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data: JSON.stringify(customerArray),    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
        success: function(data){
            alert(data);
        },
        error: function(res){
            alert(res.responseText);
        }
    });
})

$("#a").click(function(){
	var customerArray = new Array();
/*     customerArray.push({id: "1", goodsId: "333", skuId: "33333"});
    customerArray.push({id: "2", goodsId: "444", skuId: "44444"}); */
    customerArray.push({storeName: "QHTRSO0000000120170323151438565",storeAvatar:"333"}); 
    customerArray.push({storeOrder: "QHTRSO0000000120170323151438823",storeAvatar:"5555"});
    a.dto1List = customerArray;
    a.addressId = 1;
    a.userId = 1;
    alert(JSON.stringify(a))
	$.ajax({
        url: "${rootPath}app_order/addGoodsOrders.do",
        type: "POST",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data: JSON.stringify(a),    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
        success: function(data){
            alert(data);
        },
        error: function(res){
            alert(res.responseText);
        }
    });
})

$("#b").click(function(){
	$.ajax({
        url: "${rootPath}app_user/login.do",
        type: "POST",
        data: "phone=124111&password=222",  /*   //将Json对象序列化成Json字符串，JSON.stringify()原生态方法 */
        success: function(data){
           	if(data.success){
           		alert("成功！！！！");
           	}else{
           		alert("失败!");
           	}
        },
        error: function(res){
            alert(res.responseText);
        }
    });
})

</script>

<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<%@ include file="common/base.jsp"%>
<!DOCTYPE>
<body>
<h2>Hello World!</h2><c:if test="${1 == 1 }">xxxxxxxxxxxx</c:if>
<input id ="a" value="xxxxxxxxxxxxx" >
<input id ="a" value="XXXXXXXXXXXXXX" >
<form action="${rootPath}app_buycart/addBuyCart.do" method="post">
	<input type="text" name="skuId" value=100>
	<input type="text" name="goodsId" value=1010>
	<input type="text" name=num value=12>
	<input type="submit" value="确定">
</form>
</body>
</html>
<script>
$("#a").click(function(){
	var customerArray = new Array();
    customerArray.push({id: "1", goodsId: "333", skuId: "33333"});
    customerArray.push({id: "2", goodsId: "444", skuId: "44444"});
    alert(JSON.stringify(customerArray))
	$.ajax({
        url: "${rootPath}app_buycart/updateBatchBuyCart.do",
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
</script>

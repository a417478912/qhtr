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

<form action="${rootPath}/sell_picture/updateAvatar.do" id="d_id" method="post" enctype="multipart/form-data">
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
	var ajaxData = {
			id: 20,
			storeId: 19,
			descript:"简介简介简介简介简介简介简介简介",
			name:"商品名称xxxx",
			detail_pictures:"http://www.xiaoguang.com/image-1xxx.jpg,http://www.xiaoguang.com/image-2xxx.jpg",
			thumb:"http://www.xiaoguang.com/thumb.jpg",
			resultPicture:"http://www.xiaoguang.com/result.jpg",
			classId:"1,2",
			sku:'[{"id":"39","attrDetails":"红色","stock":"33","price":28},{"id":"40","attrDetails":"白色","stock":"88","price":35},{"id":"41","attrDetails":"黑色","stock":"99","price":42.5}]',
			activityId:"1,2"
		}

		$.ajax({
	        url: "${rootPath}sell_goods/updateGoods.do?userId=1",
	        type: "POST",
	        dataType:"json",
	        data: ajaxData,    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
	        success: function(data){
	            alert(data);
	        },
	        error: function(res){
	            alert(res.responseText);
	        }
	    });
	  	alert(JSON.stringify(detail));
		    
	    
	 /* $.post("${rootPath}sell_picture/upLoad.do",
			{ picture: "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", 
				type: "1"
				},
			   function(data){
				console.log(data);
			     alert("Data Loaded: " + data.data);
			   });  */
	/*  $.post("${rootPath}sell_stroe/updateStore.do",
	{ id: "1", 
		picture1: '[{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"product","id":"1"}},{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"store","id":"2"}},{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"category","id":"3"}},{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"promotion","id":"1"}}]'
		},
	   function(data){
		alert(data);
		console.log(data);
		var a = data.data.picture1;
		var b = eval(a)
		alert(b[0].imageURL + "*******" + b[0].link.type + "++++++++" +b[0].link.id   + "--------------------------"+ b[3].imageURL + "*******" + b[3].link.type + "++++++++" +b[3].link.id)
		for(i in a){
			//alert(i)
		}
	   });  */
	   
	/*  $.post("${rootPath}app_store/getStoresByDistance.do",
	{ longitude: "113.657658", 
		 latitude: "34.787778"
		},
	   function(data){
		alert(data.data[0].bannerPic);
		console.log(data.data);
		var a = data.data.picture1;
		var b = eval(a)
		for(i in a){
			//alert(i)
		}
	   });    */
	
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

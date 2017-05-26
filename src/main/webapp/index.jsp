<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<%@ include file="common/base.jsp"%>
<script language="javascript" src="<%=basePath%>js/jquery-2.1.4.min.js"> </script>
<script type="text/JavaScript" src="<%=basePath%>/js/comet4j.js"></script>
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
消息数量：<span id="msgCount"></span>
消息数据：<span id="msgData"></span>
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
<script type="text/javascript">
var count = 0;
window.onload = function(){
    // 建立连接，conn 即web.xml中 CometServlet的<url-pattern>
    JS.Engine.start('${rootPath}conn');
    <%  
         //保存用户id到session中
         session.setAttribute("currentStoreId","1");
    %>  
    // 监听后台某个频道
    JS.Engine.on(
         { 
            // 对应服务端 “频道1” 的值 msgCount
            msgCount : function(msgCount){
                $("#msgCount").html(msgCount);
                alert(msgCount)
            },
            // 对应服务端 “频道2” 的值 msgData
            msgData : function(msgData){
            	alert(msgData);
                $("#msgData").html(msgData);
            },
        }
    );
}
</script>

<script>
$(function(){
	 /* var a ; //全局变量
	$.ajax({
        url: "${rootPath}/sell_weixin/getAuthorizeUrl.do",
        type: "get",
        success: function(data){
            alert(data);
            a = data.data.url;
            $.ajax({
                url: a,
                type: "get",
                dataType:"jsonp",
                success: function(data){
                	alert("!1");
                    alert(data);
                },
                error: function(res){
                	alert("22");
                	console.log(res)
                    alert(res.status);
                }
            }); 
            
            $.ajax({
                url: "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx00527dd179ff7578&redirect_uri=http%3A%2F%2Fwww.7htr.com%2Fqhtr%2Fsell_weixin%2Fresult.do&response_type=code&scope=snsapi_base",
                type: "get",
                dataType:"jsonp",
                success: function(data){
                	alert("33");
                    alert(data);
                },
                error: function(res){
                	console.log(res)
                	alert("44");
                    alert(res);
                }
            }); 
        },
        error: function(res){
            alert(res.responseText);
        }
    }); */
	
	
	/* function test()
	{
	  if(false){
		  a =1 ;
		  return a;
	  }
	  if(true){
		  a=2;
		  return a;
	  }
	  return a; 
	}
	a=test(); //将函数返回值赋给全局变量a */
	/* var ajaxData = {
			id: 28,
			storeId: "19",
			descript: "简介简介简介简介简介简介简介简介xxxxxx",
			name: "商品名称xxx",
			detail_pictures: "http://www.xiaoguang.com/imageeee-1.jpg,http://www.xiaoguang.com/imageeee-2.jpg",
			thumb: "http://www.xiaoguang.com/thumbs.jpg",
			resultPicture: "http://www.xiaoguang.com/resultsss.jpg",
			classId: "16",
			sku: '[{"id":"63","attrDetails":"红色xxx","stock":"33","price":28,"status":1},{"id":"64","attrDetails":"白色dd","stock":"88","price":35,"status":1},{"id":"65","attrDetails":"黑色aa","stock":"99","price":42.5,"status":2},{"attrDetails":"编辑新增","stock":"99","price":42.5,"status":1}]',
			activityId: "1,2"
		}*/
	 /* var ajaxData = {
			id: 28,
			storeId: "19",
			descript: "简介简介简介简介简介简介简介简介xxxxxx",
			name: "商品名称xxx",
			detail_pictures: "http://www.xiaoguang.com/imageeee-1.jpg,http://www.xiaoguang.com/imageeee-2.jpg",
			thumb: "http://www.xiaoguang.com/thumbs.jpg",
			resultPicture: "http://www.xiaoguang.com/resultsss.jpg",
			classId: "16",
			sku: '[{"id":"63","attrDetails":"红色xxx","stock":"33","price":28.12,"status":1},{"id":"64","attrDetails":"白色dd","stock":"88","price":35,"status":1},{"id":"65","attrDetails":"黑色aa","stock":"99","price":42.5,"status":2},{"attrDetails":"编辑新增","stock":"99","price":42.5,"status":1}]',
			activityId: "1,2"
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
	    }); */
	
	/*  var ajaxData = {
			"orderId":"8",
			"expressName":"aaab",
			"expressCode":"123235435434"
		}

		$.ajax({
	        url: "${rootPath}sell_order/addExpress.do",
	        type: "POST",
	        dataType:"json",
	        data: ajaxData,    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
	        success: function(data){
	            alert(data.message);
	        },
	        error: function(res){
	            alert(res.responseText);
	        }
	    }); */
		    
	  	/* var ajaxData = {
	  			picture:'data:image/jpeg;base64,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',
	  			type:3,
	  			width:220,
	  			height:220
	  		}
	  	$.ajax({
	        url: "${rootPath}sell_picture/upLoad.do",
	        type: "POST",
	        dataType:"json",
	        data: ajaxData,    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
	        success: function(data){
	            alert(data);
	        },
	        error: function(res){
	            alert(res.responseText);
	        }
	    }); */
	    
	 	/* $.post("${rootPath}sell_picture/upLoad.do",
			{ picture: "iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAJYklEQVR4Xu2d/7ltMxCG51ZAB1cHqAAVoAJUgApQASpABagAFaADOqADzzz2em7uvvvslcxOMpPMe/49+THzfvlO1p6TvfJM+IEABJ4k8Aw2EIDA0wQwCKsDAncIYBCWBwQwCGsAAjYC7CA2bvRKQgCDJBGaNG0EMIiNG72SEMAgSYQmTRsBDGLjRq8kBDBIEqFJ00YAg9i40SsJAQySRGjStBHAIDZu9EpCAIMkEZo0bQQwiI0bvZIQwCBJhCZNGwEMYuNGryQEMEgSoUnTRgCD2LjRKwkBDJJEaNK0EcAgNm70SkIAgyQRmjRtBDCIjRu9khDAIEmEJk0bAQxi40avJAQwSBKhSdNGAIPYuNErCQEMkkRo0rQRwCA2bvRKQgCDJBGaNG0EMIiNG72SEMAgSYQmTRsBDGLjRq8kBDBIEqFJ00YAg9i40SsJAQySRGjStBHAIDZu9EpCAIMkEZo0bQQwiI0bvZIQwCBJhCZNGwEMYuNGryQEMEgSoUnTRgCD2LjRKwkBDJJEaNK0EcAgNm70SkIAgyQRmjRtBDCIjRu9khDAIEmEJk0bAQxi40avJARGGeSdJPxIMx6Bv0Xkr15hjTLINyLyaa8gGQcClQT+FZG3VjCI5vOTiLxfmRjNINCDwNsi8kePgY4xRu0gOv7rIvKriLzZM2DGgsATBD4Rke970xlpEI31jYujX+sdOONBoCDwrYh8NoLIaINozPpMqDsJJhmhIGP+LCIfjMIwwyAa+8ci8t2oJBg3LYE/ReRdEflnFIFZBtH4vxSRL0YlwrjpCGjFSh/hh5lDic40iM6nH6I+SiclCfcmoObQnaNrxepWkLMNQmWr91LJOd6QilUEg2gMahL9Tycf2nMu7kez/uryuP7oOFX9Z+8gR1BUtqrkodEVgR8uBZ9pYLwMoglqae7HaZky0eoEhlesojxilXHoP3e+Xl054h9OQA8g6lPH0IpVRINQ2Rq+tpafYFrFKqpBNC4t13Fma/m1PCSBDy8HX4cMfjao52eQMjatbKlJnp8FzO9TEfhcRPSrE24/UQyiAKhsuS2DkBNPr1hFfsQ6YqOyFXKtTg9KK1b6B9P9J9IOcsDgYKP7snANwK1itcIOcsTImS3XNeo2uWvFaiWDaKz6HRJe/uC2Vl0mfu+iu8vkqxmEg41hlsmUQKYdQGzJJuJnkDJ+Klstaq7bNkTFarUd5IhXz/3/sq72RH5C4LfLdztCgoq+g1DZCrlsugXlcgCxJfpVDKI5UdlqUTZ+23AVq1Ufscq4eRld/IVfG2G4itUOBqGyVbv8YrcLWbHawSCaAy+ji734z6Ib9pK3s4ktv1/pM8h1+fd3S8L0cSUw9CVvIzJb1SDKgjNbI1bEuDHDV6x2ecQq8+CahXELuufI3a8l6BncvbFW3kGOvKhszVot9nm6X0tgD6Wt5w4GobLVpvns1stUrHZ8xDpy4mV0s5d93XxLVax2NojmxsHGukU7q1XYA4gtAHZ4xCrzpbLVov64tktWrHbfQY78uGZh3MKvGXnKtQQ1gfRos9sOcjDhYGOP1dE+xhIHEFvS2tUgVLZaVkG/tktXrLI8YlHZ6rfgW0aaei1BS2CPtN11BzmYUNl6ZHXU992iYpVtBzny5WV09Qvd0jLMS94swZ/12X0HOfLnmoWzlWD7faiXvNlSuN8ri0GUApWtvitou4pV1kesMm+uWehnEtdrCfqlwQ5SEuCahT4ry/1agj5pnI+S6RGLytb5eqhpsW3FikesFwSobNVY4dU2W1esMMjLBDjY2GaSbQ4gtqSd8RGr5ENlq261pKhYsYPcXgxcs3BukiVe8naeRnuL7DuIEuNg4/11s90BxBabYJD/aXFm6/aqSVWx4hHr/p8Orll4mU/oawladoFH2rKDUNm6tX5SVqzYQer+lGR/GV3aihUGqTOItsr8MrplX/JWL299Sx6xbrPKWtlKXbFiB6n/w6Ets12zsPxL3trkrWvNDnKfk5Z/M1yzsNy1BHXL+/FWGOSc4e5ntqhY3VkDGOTcINpi18rWstcS1Mn2eCsMUs9wx4ONVKxO9Mcg9QbZrbJFxapCewxSAalosss1C1u+5K1NyrrWGKSOU9lq9YON6Q8gtkiOQVpovWi7amWLilWj3hikEVjRfLVrFra6lsAuW1tPDNLG67r1KpUtDiAadcYgRnCXbqtUtqhYGXXGIEZwV5UtfWPj88eHGjICFasHsGKQB+AVXaNWtqhYPagvBnkQYNE92svo0r3krZ+UL0bCIH2pRrlmYftrCfrK9vRoGKQ/ae/KFhWrjppikI4wi6E8r1lIcS3BGNleHRWDjCHtdc1CmmsJxsiGQWZx1XlmV7aoWA1Qlx1kANRiyFkvo+Mlb4N0xCCDwBbDjj7YyAHEgRpikIFwi6FHVbaoWA3WD4MMBlwMP+KahbTXEsySDYPMIt3/mgUOIE7QDoNMgFxM0auyRcVqkm4YZBLojpUtKlYTNcMgE2F3qGxRsZqsFwaZDLyYrvVldLzkzUErDOIAvZiy5ZoFXvLmoBUGcYBeTFn7lV0qVk46YRAn8MW0Z9cscC2Bo0YYxBH+Vfn31jULXEvgrA8GcRbgTmWLilUAbTBIABFuVLaoWAXRBYMEEeLKJHq4Ub+VyI8zAQziLADTxyaAQWLrQ3TOBDCIswBMH5sABomtD9E5E8AgzgIwfWwCGCS2PkTnTACDOAvA9LEJYJDY+hCdMwEM4iwA08cmgEFi60N0zgQwiLMATB+bAAaJrQ/RORPAIM4CMH1sAhgktj5E50wAgzgLwPSxCWCQ2PoQnTMBDOIsANPHJoBBYutDdM4EMIizAEwfmwAGia0P0TkTwCDOAjB9bAIYJLY+ROdMAIM4C8D0sQlgkNj6EJ0zAQziLADTxyaAQWLrQ3TOBDCIswBMH5sABomtD9E5E8AgzgIwfWwCGCS2PkTnTACDOAvA9LEJYJDY+hCdMwEM4iwA08cmgEFi60N0zgQwiLMATB+bAAaJrQ/RORPAIM4CMH1sAhgktj5E50wAgzgLwPSxCWCQ2PoQnTMBDOIsANPHJoBBYutDdM4EMIizAEwfmwAGia0P0TkTwCDOAjB9bAIYJLY+ROdMAIM4C8D0sQlgkNj6EJ0zAQziLADTxyaAQWLrQ3TOBDCIswBMH5sABomtD9E5E8AgzgIwfWwCGCS2PkTnTOA/hu7jyUc2GX4AAAAASUVORK5CYII=", 
				type: "1"
				},
			   function(data){
				console.log(data);
			     alert("Data Loaded: " + data.data);
			   }); */
	/*  $.post("${rootPath}sell_stroe/updateStore.do",
	{ id: "1", 
		picture1: '[{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"product","id":"1"}},{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"store","id":"2"}},{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"category","id":"3"}},{"imageURL":"http://www.xiaoguang.com/bannerpic.jpg","link":{"type":"promotion","id":"1"}}]'
		},
	   function(data){
		alert(data);
		console.log(data);
		var a = data.data.bannerPic;
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

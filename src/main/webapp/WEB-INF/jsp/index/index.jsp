<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html> 
  <head>
</script>

    <meta http-equiv='Content-type' content='text/html; charset=utf-8'>
 
     <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
     <meta http-equiv="x-dns-prefetch-control" content="on" />
    <title>测试</title>
    <link href="http://xg.dweike.com/css/font.css" rel="stylesheet" />
    <link href="http://xg.dweike.com/css/public.css" rel="stylesheet" />
    <link href="http://xg.dweike.com/css/style.css" rel="stylesheet" />
    <link href="http://xg.dweike.com/css/xiaoguang.css" rel="stylesheet" />
    
    <!-- 此处输出当前登录用户的信息 -->
    <script>
	    var currentUser = '${user}';
		alert(currentUser);
	    
	    //此处修改登录验证的api接口, 直接返回字符串“success”或“fail"就行。
	    var loginRequestURL = "/sell_stroe/login.do";
	    
	</script>
    <!-- 结束 -->
    
    <script type="text/javascript" src="http://xg.dweike.com/lib/jquery-1.12.3.min.js" ></script>
    <script type="text/javascript" src="http://xg.dweike.com/lib/jquery.slides.min.js" ></script>
  </head>
  <body>
	 
	<header class="box">
		 
		<div class="flex head-title">
			登录
		</div>
		 
	</header>
	 
  	<div id="main">
	  	<div class="section-holder page-top">
			<section class="test-section">
				<div class="field">
					<label  class="field-title">
						手机号
					</label>
					<div class="field-input">
						<input id="tel" type="text"  placeholder="请填写手机号" />
					</div>
				</div>
				<div class="field">
					<label class="field-title">
						密码
					</label>
					<div class="field-input">
						<input id="password" type="password" placeholder="8-16位登录密码" />
					</div>
				</div>
				 
				<div class="field-button margin-top-large">
					<button class="full submit">登录</button>
				</div>
				<div class="field-info right no-padding">
					<a href="http://xg.dweike.com/forget-password-1.php">忘记密码？</a>
				</div>
			</section>
		</div>
  	</div>
  	<script type="text/javascript" src="http://xg.dweike.com/lib/xiaoguang.js">
	</script>
	<script>
	if(currentUser && currentUser.login){
		$(".head-title").html("首页");
		$(".test-section").html("<h2>用户已经登录</h2>");
	}else{
		$(".submit").on("click", function(){
			$.ajax({
				url: loginRequestURL,
				data: {
					phone: $("#tel").val(),
					password: $("#password").val()
				},
				type: "post",
				success: function(res){
					if(res.code == "200"){
						alert("登录成功");
						window.location = window.location;
					}else{
						alert("登录验证失败");
					}
				}
			});
		});
	}
	</script>
  </body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<%@ include file="common/base.jsp"%>
<script language="javascript" src="<%=basePath%>js/jquery-2.1.4.min.js"> </script>
<script type="text/JavaScript" src="<%=basePath%>/js/comet4j.js"></script>
<!DOCTYPE>
<body>
</body>
</html>
<script type="text/javascript">
var count = 0;
window.onload = function(){
    // 建立连接，conn 即web.xml中 CometServlet的<url-pattern>
    JS.Engine.start('${rootPath}conn');
    <%  
         //保存用户id到session中
         session.setAttribute("currentStoreId","2");
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

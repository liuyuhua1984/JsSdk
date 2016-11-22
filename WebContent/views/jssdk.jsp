<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>Insert title here</title>
<script type="text/javascript" src="${path}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script>
// 使用 $(function(){}) 相当于 onload="某个方法"
/**$(function(){
    // 这里写你要执行的代码吧
})**/
	$(function() {
		sendSignAjax();
	});

	/**
	 * 发送sendSignAjax
	 */
	function sendSignAjax() {
	//	alert("你好");
		var aj = $.ajax({
			url : "sign.html",// 跳转到 action  
			data : {
				"url" : window.location.href
			},
			type : 'post',//提交方式 
			cache : false,
			dataType : 'json',
			async : true,
			success : function(data) {
				//			var last=data.toJSONString(); //将JSON对象转化为JSON字符

				alert("成功::" + data.signature);
				
				wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId:data.appId, // 必填，公众号的唯一标识
				    timestamp:data.timestamp, // 必填，生成签名的时间戳
				    nonceStr: data.nonceStr, // 必填，生成签名的随机串
				    signature: data.signature,// 必填，签名，见附录1
				    jsApiList: [ 'checkJsApi',
				                    'openLocation',
				                    'getLocation',
				                    'onMenuShareTimeline',
				                    'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});

				wx.ready(function () {
					
					alert("我是对的!");
					wx.checkJsApi({
					    jsApiList: [
					        'getLocation',
					        'onMenuShareTimeline',
					        'onMenuShareAppMessage'
					    ],
					    success: function (res) {
					        alert(JSON.stringify(res));
					    }
					});
					
				});
			},

			error : function(data) {
				// view("异常！");  
				alert("错误:" + data);
			}
		});
	}
</script>

</head>

<body>他
</body>
</html>

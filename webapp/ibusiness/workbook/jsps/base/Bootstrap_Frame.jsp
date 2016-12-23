<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作日志</title>
<jsp:include page="head_base.jsp"></jsp:include>
<style type="text/css">
</style>
<body class="container panel">
	<div class="window-header" style="height:50px"></div>
	<!-- 左窗体 -->
	<div class="window-left col-md-2">
			
	</div>
	<!-- 窗体延展按钮 -->
	<div class="window-mid col-md-1" style="padding-right:0px;margin-right:0px;">
		<div style="text-align:right;margin-right:0px;padding-right:0px;padding-top:215px;height:455px;">
			<a id="window-left-hide" class="btn btn-info" style="margin:0px;padding:0px;height:40px;padding-top:10px;">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
		</div>
	</div>
	<!-- 右窗体 -->
	<div class="window-right col-md-9" style="padding-left:0px;margin-left:0px;margin-right:0px;padding-right:0px;">
		<div class="panel panel-success" style="margin-left:0px;padding-left:0px;margin-right:0px;padding-right:0px;padding-bottom:0px;margin-bottom:15px">
			<!-- 窗体全屏按钮 -->
			<div class="panel-heading" style="text-align:right">
				<a id="body-size" class="btn-link"><span class="glyphicon glyphicon-th-large"></span></a>
			</div>
			<div class="panel-body" style="height:455px;">
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){	
		//窗体延展按钮点击事件
		$('a#window-left-hide').bind('click',function(){
			var tree_hide_a_icon = $('a#window-left-hide span').attr('class');
			if(tree_hide_a_icon == 'glyphicon glyphicon-chevron-left'){
				$('div.window-left').hide();
				$('div.window-right').removeClass('col-md-9').addClass('col-md-11');
				$('a#window-left-hide span').removeClass('glyphicon-chevron-left').addClass('glyphicon-chevron-right');
			}else if(tree_hide_a_icon == 'glyphicon glyphicon-chevron-right'){
				$('div.window-left').show();
				$('div.window-right').removeClass('col-md-11').addClass('col-md-9');
				$('a#window-left-hide span').removeClass('glyphicon-chevron-right').addClass('glyphicon-chevron-left');
			}
		});
			
		//窗体全屏按钮点击事件
		$('a#body-size').bind('click',function(){
			var body_size_a_icon = $('a#body-size span').attr('class');
			if(body_size_a_icon == 'glyphicon glyphicon-th-large'){
				$('body').removeClass('container');
				$('a#body-size span').removeClass('glyphicon-th-large').addClass('glyphicon-th');
			}else if(body_size_a_icon == 'glyphicon glyphicon-th'){
				$('body').addClass('container');
				$('a#body-size span').removeClass('glyphicon-th').addClass('glyphicon-th-large');
			}
		});
	});
</script>
</html>
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
<body>
	
</body>
<script type="text/javascript">
	$(function(){
		$('a.tree-hide').bind('click',function(){
			var show_icon_text = $(this).children('span').attr('class');
			if(show_icon_text == 'glyphicon glyphicon-chevron-down'){
				$(this).parents('table').next('div.children').hide('slowly');
				$(this).children('span').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right');
				$(this).parents('tr').children('td.folder-icon').children('span').removeClass('glyphicon-folder-open').addClass('glyphicon-folder-close');
			}else if(show_icon_text == 'glyphicon glyphicon-chevron-right'){
				$(this).parents('table').next('div.children').show('slowly');
				$(this).children('span').removeClass('glyphicon-chevron-right').addClass('glyphicon-chevron-down');
				$(this).parents('tr').children('td.folder-icon').children('span').removeClass('glyphicon-folder-close').addClass('glyphicon-folder-open');
			}
		});
	});
</script>
</html>
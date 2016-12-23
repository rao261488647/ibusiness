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
<title>动态菜单展示</title>
<jsp:include page="${basePath}base/head_base.jsp"></jsp:include>
<style type="text/css">
	
</style>
</head>
<body>
	<!--
										             工作流程说明
	
		动态菜单的分类：
			一级菜单：导航条，包含两层结构，非叶子项目用来展示其叶子菜单项目，叶子菜单项目有action
			二级菜单：手风琴，包含两层结构，非叶子项目用来展示其叶子菜单项目，叶子菜单项目有action
		--------------------------------------------------------------------------------------- 
		动态菜单的连接请求格式为：
			请求动作连接?一级菜单序号?当前点击的一级菜单叶子菜单项目的序号
		---------------------------------------------------------------------------------------
		获取请求一级菜单的流程：
			根据传递的一级菜单序号，拿到一级菜单中没有父菜单项目的菜单项目，由于hibernate维持对象之间
		的关联关系，根据这些项目可以获取到其所有的叶子菜单项目，继而获取一级菜单。
			首次访问时一级菜单的参数应该根据登陆用户的ID来设置
			后期访问时一级菜单的参数不发生变化
		获取请求二级菜单的流程：
			根据传递的一级菜单项目序号，可以拿到一个一级项目叶子菜单项目，根据对象间的关联关系可以拿到
		该叶子菜单项目所对应的二级菜单中没有父菜单项目的二级菜单项目，之后同上一请求流程。
			首次访问时二级菜单的参数可以根据一级菜单中的主页菜单项目序号来设置
			后期访问是二级菜单的参数根据用户点击一级菜单中的菜单项目序号来设置
	 -->
	<jsp:include page="${basePath}templets/templet-navbar-threelevels.jsp"></jsp:include>
	
</body>

<script type="text/javascript">
	$(function(){
		
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/bootstrap/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/bootstrap/css/bootstrap-theme.min.css"></link>
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/mCustomScrollbar/jquery.mCustomScrollbar.css"></link>
<script type="text/javascript" src="<%=basePath %>plugin/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/mCustomScrollbar/jquery.mCustomScrollbar.js"></script>
</head>
<body>

</body>
</html>
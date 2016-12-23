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
<style type="text/css">
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #ccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #fff;
}

.dropdown-submenu.pull-left {
	float: none;
}
</style>
</head>
<body>
<!-- start of header bar -->
<div class="navbar navbar-default">
	<div class="navbar-header">
		<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-responsive-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#/">天翔</a>
	</div>
	<div class="navbar-collapse collapse navbar-responsive-collapse">
		<!-- 动态部分 -->
		<ul class="nav navbar-nav">
			<c:forEach items="${menuItemList}" var="item">
				<c:choose>
					<c:when test="${item.chiledItems == '[]'}">
						<li class=""><a href="#">${item.strMenuItemName}</a></li>
					</c:when>
					<c:when test="${item.chiledItems != '[]'}">
						<li class="dropdown activ"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#">${item.strMenuItemName} <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:forEach items="${item.chiledItems}" var="son">
									<c:choose>
										<c:when test="${son.chiledItems == '[]'}">
											<li class="divider"></li>
											<li><a>${son.strMenuItemName}</a></li>
										</c:when>
										<c:when test="${son.chiledItems != '[]'}">
											<li class="dropdown-submenu"><a href="#"><i class="icon-user"></i>${son.strMenuItemName}</a>
												<ul class="dropdown-menu  panel-body nav nav-list">
													<c:forEach items="${son.chiledItems}" var="grandson">
														<li><a href="#"><i class="icon-user"></i>${grandson.strMenuItemName}</a></li>
													</c:forEach>
												</ul>
											</li>
										</c:when>
										<c:otherwise>解析出错！</c:otherwise>
									</c:choose>	
								</c:forEach>
							</ul>
						</li>
					</c:when>
					<c:otherwise>解析出错</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<!-- 静态部分 -->
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown ${currentHeader == 'manage' ? 'active' : ''}">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">选项<b class="caret"></b></a>
                	<ul class="dropdown-menu multi-level">
                		<li class="divider"></li>
						<li class="dropdown-submenu"><a href="#"><i class="icon-user"></i>一键换肤</a>
	                    	<ul class="dropdown-menu  panel-body nav nav-list">
		                    	  <li class="divider"></li>
				                  <li><a href="">Cerulean</a></li>
				                  <li><a href="">Slate</a></li>
				                  <li><a href="">Cosmo</a></li>
				                  <li><a href="">Cyborg</a></li>
				                  <li><a href="">Darkly</a></li>
				                  <li><a href="">Flatly</a></li>
				                  <li><a href="">Superhero</a></li>
								  <li><a href="">United</a></li>
								  <li><a href="">Yeti</a></li>
				                  <li class="divider"></li>
				                  <li><a href="">Journal</a></li>
				                  <li><a href="">Lumen</a></li>
				                  <li><a href="">Paper</a></li>
				                  <li><a href="">Readable</a></li>
				                  <li><a href="">Sandstone</a></li>
				                  <li><a href="">Simplex</a></li>
				                  <li><a href="">Spacelab</a></li>
				                  <li class="divider"></li>
	                        </ul>
	                   </li>
	                   <li class="divider"></li>
	                   <li><a>编辑菜单</a></li>
	                   <li class="divider"></li>
					</ul>
				</li>
		   </ul>
	</div><!-- /.nav-collapse -->
</div><!-- /navbar-inner -->
<!-- end of header bar -->
</body>
</html>
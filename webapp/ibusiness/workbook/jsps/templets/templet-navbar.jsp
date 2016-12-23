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
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid"><!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Logo</a>
			</div>
			
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</ul>
				<c:forEach items="${menuItemsOfLevelOne}" var="menuItem">
					<c:choose>
						<c:when test="${menuItem.chiledItems!='[]'}">
							<ul class="nav navbar-nav">
								<li id="accordion${menuItem.intMenuItemId}" class="dropdown">
									<a href="#" class="dropdown-toggle"data-toggle="dropdown">${menuItem.strMenuItemName} <span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${menuItem.chiledItems}" var="childItem">
											<li class="divider"></li>
											<li id="accordion${childItem.intMenuItemId}"><a href="${childItem.strMenuItemAction}?strFirstParam=${childItem.intMenuId}&strSecondParam=${childItem.intMenuItemId}">${childItem.strMenuItemName}</a></li>
										</c:forEach>
										<li class="divider"></li>
									</ul>
								</li>
							</ul>
						</c:when>
						<c:when test="${menuItem.chiledItems=='[]'}">
							<ul class="nav navbar-nav">
								<li id="accordion${menuItem.intMenuItemId}" class=""><a href="${menuItem.strMenuItemAction}?strFirstParam=${menuItem.intMenuId}&strSecondParam=${menuItem.intMenuItemId}">${menuItem.strMenuItemName}</a></li>
							</ul>
						</c:when>
						<c:otherwise>解析出错啦</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 编辑按钮 -->
				<!-- Single button -->
				<c:forEach items="${menuItemsOfLevelOne}" var="getMenulOfLevelOneId" begin="0" end="0">
					<ul class="nav navbar-nav pull-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							编辑 <span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li class="divider"/>
								<li><a href="<%=basePath %>dynamicmenu/getCompleteMenus?RequestParamForMenuOfLevelOne=${getMenulOfLevelOneId.intMenuId}">编辑菜单</a></li>
								<li class="divider"/>
							</ul>
						</li>
					</ul>
				</c:forEach>	
			</div>
		</div>
		<!-- /.navbar-collapse -->
		<!-- /.container-fluid -->
	</nav>
</body>
</html>
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
<title>菜单文本编辑器</title>
<jsp:include page="${basePath}base/head_base.jsp"></jsp:include>
<style type="text/css">
	 td>a[type=submit],td>a.dropdown-button{
		padding:0px;
		margin:0px;
		border:0px;
	}
</style>
</head>
<body class="container">
	<c:forEach items="${menuItemsOfLevelOne}" var="getMenulOfLevelOneId" begin="0" end="0">
		<div class="modal fade" id="myModal">
			<div class="col-md-12">
				<div style="height:30px;"></div>
				<div class="col-md-12">
					<div class="modal-content">
						<div class="modal-header">
							<a type="button" class="close" href="<%=basePath %>dynamicmenu/getMenus?strFirstParam=${getMenulOfLevelOneId.intMenuId}&strSecondParam=1">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</a>
							<h4 class="modal-title"><span class="glyphicon glyphicon-edit"></span><strong>&nbsp;菜单设置</strong></h4>
						</div>
						<div class="modal-body">
							<!-- 标签页 -->
							<div id="myTab" class="bs-example bs-example-tabs">
								<ul class="nav nav-tabs" role="tablist">
								    <li class="active"><a href="#firstEditor" role="tab" data-toggle="tab">一级菜单设置</a></li>
								    <li><a href="#secondEditor" role="tab" data-toggle="tab">二级菜单设置</a></li>
								</ul>
								<div id="myTabContent" class="tab-content">
								    <div class="tab-pane fade in active" id="firstEditor">
										<!-- 标签页1 -->
										<jsp:include page="${basePath}templetjsps/templet-completshow-menulevelone.jsp"></jsp:include>
								    </div>
								    <div class="tab-pane fade " id="secondEditor">
										<!-- 标签页2 -->
										<jsp:include page="${basePath}templetjsps/templet-completshow-menuleveltwo.jsp"></jsp:include>
								    </div>
								</div>
							</div>
							<!-- 标签页 -->
						</div>
							<div class="modal-footer">
								<a type="button" href="<%=basePath %>dynamicmenu/getMenus?strFirstParam=${getMenulOfLevelOneId.intMenuId}&strSecondParam=1" class="btn btn-default">关闭对话框</a>
								<a type="button" class="btn btn-primary">添加新项目</a>
							</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
				</div>
			</div><!-- /.modal -->	
		</c:forEach>
	</body>
	<script type="text/javascript">
		$(function(){
			//页面展示时默认展示模态框
			$('#myModal').modal({
				keyboard: false,
				backdrop:false,
				show:true
			});

			//设置模态框滚动条的样式
			$("#myModal .modal-body").mCustomScrollbar({
				setHeight:405,
				theme:"dark-3",
				horizontalScroll:true
			});
			//设置标签页的滚动条样式
			$("#myTab .tab-pane").mCustomScrollbar({
				scrollButtons:{
					enable:true
				},
				setHeight:310,
				theme:"dark-3"
			});

			//默认隐藏子菜单项目列表，state属性为hide
			$('tr[data-parent]').hide();
			//点击下拉按钮来展示子菜单项目的显示和隐藏
			$('a.dropdown-button').bind('click',function(){
				var me = $(this);
				var parent = me.parents('tr').first();
				if($('tr[data-parent='+parent.attr('id')+']').attr('state')=='show'){
					$('tr[data-parent='+parent.attr('id')+']').hide('slow');
					me.children('span').first().attr('class','glyphicon glyphicon-chevron-down');
					$('tr[data-parent='+parent.attr('id')+']').attr('state','hide');
				}else {
					$('tr[data-parent='+parent.attr('id')+']').show('slow');
					$('tr[data-parent='+parent.attr('id')+']').attr('state','show');
					me.children('span').first().attr('class','glyphicon glyphicon-chevron-up');
				}
			});

			//设置菜单项目的下拉按钮的活性，没有子菜单的菜单项目的下拉按钮是非活性的
			$('tbody tr[isLeaf=TRUE] a.dropdown-button').each(function(){
				$(this)	.addClass('btn').addClass('disabled');
			});
			//设置菜单项目的下拉按钮的活性，有子菜单的菜单项目的下拉按钮是活性的
			$('tbody tr[isLeaf=FALSE] a.dropdown-button').each(function(){
				$(this).removeClass('btn').removeClass('disabled');
			});
			//编辑按钮点击，插入按钮其他按钮变为非活性，表格变为可以编辑的状态
			$('td.alterButton a').bind('click',function(){
				alert($(this).siblings('td.addChildItem').first().html());
			});
			
		});
	</script>
	
</html>
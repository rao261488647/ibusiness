<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>资源列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = { id: 'menuGrid', pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount: ${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		        'filter_LIKES_value': '${param.filter_LIKES_value}',
	        	'menuLevel': '${menuLevel}',
	        	'menuLevelOne': '${menuLevelOne}',
		        'menuLevelTwo':'${menuLevelTwo}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'menuGridForm'
		};
		var table;
		$(function() {
		    table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
		});
		
		function getlevelTwo(){
			//向服务端获取二级菜单列表
			$.ajax({
				url:'menu-getlevelTwo.do',
				data: {
					menuleveloneID:$("#select_levelone").val()
	    		},
				success:function(responseText){
					//构建select
					$("#menu_leveltwo option").remove();
					$('#menu_leveltwo').append(responseText);
					//设置二级菜单ID
					$(hidden_menuLevelTwo).val($("#menu_leveltwo").val());
					$('#menu_leveltwo').focus();
				}
			});
		}
		
		// 下拉菜单
		$(document).ready(function(){
			// 一级菜单
			$("#select_levelone").change(function() {
				$(hidden_menuLevelOne).val($("#select_levelone").val());
				
			});
			// 二级菜单
			$("#menu_leveltwo").change(function() {
				$(hidden_menuLevelTwo).val($("#menu_leveltwo").val());
			});
		});
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

	<div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
        <!-- tabs  -->
        <ul class="nav nav-tabs">
		  <li class="${menuLevel == '1' ? 'active' : ''}"><a href="${ctx}/menu/menu-list.do?menuLevel=1&menuLevelOne=0&menuLevelTwo=0" >一级菜单</a></li>
		  <li class="${menuLevel == '2' ? 'active' : ''}"><a href="${ctx}/menu/menu-list.do?menuLevel=2&menuLevelOne=0&menuLevelTwo=0" >二级菜单</a></li>
		  <li class="${menuLevel == '3' ? 'active' : ''}"><a href="${ctx}/menu/menu-list.do?menuLevel=3&menuLevelOne=0&menuLevelTwo=0">三级菜单</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <!-- 三级菜单 -->
		  <div id="levelthree" class="tab-pane fade active in">
		      <div class="panel-body">
		            <input id="hidden_menuLevelOne" type="hidden" name="menuLevelOne" value="${menuLevelOne}">
		            <input id="hidden_menuLevelTwo" type="hidden" name="menuLevelTwo" value="${menuLevelTwo}">
				            <div class="form-group">
								  <c:if test="${menuLevel != 1}">
							            <button class="btn btn-primary  btn-sm" onclick="location.href='menu-list.do?menuLevel=${menuLevel}&menuLevelOne='+$(hidden_menuLevelOne).val()+'&menuLevelTwo='+$(hidden_menuLevelTwo).val() ">查询</button>
							  	  </c:if>
								  <button class="btn btn-primary  btn-sm" onclick="location.href='menu-input.do?menuLevel=${menuLevel}&menuLevelOne='+$(hidden_menuLevelOne).val()+'&menuLevelTwo='+$(hidden_menuLevelTwo).val()">新建</button>
								  <button class="btn btn-primary  btn-sm a-remove" onclick="table.removeAll()"><spring:message code="core.list.delete" text="删除"/></button>
								  <!-- 一级父菜单 -->
								  <c:if test="${menuLevel != 1}">
								      <label class="col-lg-1 control-label" for="select_levelone">一级菜单:</label>
								      <div class="col-lg-2">
										  <select class="form-control"  id="select_levelone" name="menuLevelOne" onchange="getlevelTwo();">
										      <option value="" selected>请选择</option>
											  <c:forEach items="${levelOneInfos}" var="item">
											    <option value="${item.id}" ${item.id==menuLevelOne ? 'selected' : ''}>${item.menuName}</option>
											  </c:forEach>
										  </select>
									  </div>
							      </c:if>
								  <!-- 二级父菜单 -->
								  <c:if test="${menuLevel == 3}">
								      <label class="col-lg-1 control-label" for="menu_leveltwo">二级菜单:</label>
								      <div class="col-lg-2">
										  <select class="form-control"  id="menu_leveltwo" name="menuLevelTwo">
										      <option value="" selected>请选择</option>
											  <c:forEach items="${levelTwoInfos}" var="item">
											    <option value="${item.id}" ${item.id==menuLevelTwo ? 'selected' : ''}>${item.menuName}</option>
											  </c:forEach>
										  </select>
									  </div>
							      </c:if>
							      
						  	</div>
				        <div class="pull-right">
						  每页显示
						  <select class="m-page-size">
						    <option value="10">10</option>
						    <option value="20">20</option>
						    <option value="50">50</option>
						  </select>
						  条
						</div>
			      </div>
			      <form id="menuGridForm" name="menuGridForm" method='post' action="menu-remove.do" class="m-form-blank">
						  <table id="menuGrid" class="table table-hover table-striped">
						      <thead>
						        <tr>
						          <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
						          <th class="sorting" ><spring:message code="auth.access.list.type" text="菜单名"/></th>
						          <th class="sorting" ><spring:message code="auth.access.list.value" text="菜单级别"/></th>
						          <th class="sorting" ><spring:message code="auth.access.list.perm" text="URL"/></th>
						          <th class="sorting" ><spring:message code="auth.access.list.priority" text="排序"/></th>
						          <th width="30">&nbsp;</th>
						        </tr>
						      </thead>
						      <tbody>
						        <c:forEach items="${page.result}" var="item">
						        <tr>
						          <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						          <td>${item.menuName}</td>
						          <td>${item.menuLevel}</td>
						          <td>${item.menuUrl}</td>
						          <td>${item.menuOrder}</td>
						          <td>
						            <a href="menu-input.do?id=${item.id}&menuLevel=${menuLevel}&menuLevelOne=0&menuLevelTwo=${menuLevelTwo}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
						          </td>
						        </tr>
						        </c:forEach>
						      </tbody>
					    </table>
					</form>
					<div class="m-page-info pull-left">
						  		共100条记录 显示1到10条记录
					</div>
					<div class="btn-group m-pagination pull-right">
							  <button class="btn btn-primary btn-sm">&lt;</button>
							  <button class="btn btn-primary btn-sm">1</button>
							  <button class="btn btn-primary btn-sm">&gt;</button>
					</div>

		  </div>
		</div>
        
    </div>
	<!-- end of main -->

  </body>
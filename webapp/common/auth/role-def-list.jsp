<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.roleDef.list.title" text="角色列表"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'roleDefGrid',
    pageNo: ${page.pageNo},
    pageSize: ${page.pageSize},
    totalCount: ${page.totalCount},
    resultSize: ${page.resultSize},
    pageCount: ${page.pageCount},
    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
    asc: ${page.asc},
    params: {
        'filter_LIKES_name': '${param.filter_LIKES_name}'
    },
	selectedItemClass: 'selectedItem',
	gridFormId: 'roleDefGridForm',
	exportUrl: 'role-def-export.do'
};

var table;

$(function() {
    table = new Table(config);
    table.configPagination('.m-pagination');
    table.configPageInfo('.m-page-info');
    table.configPageSize('.m-page-size');
});
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title">角色列表</h4></div>
        <div class="panel-body">
	        <div class="pull-left">
			  <button class="btn btn-default btn-sm a-insert" onclick="location.href='role-def-input.do'"><spring:message code="core.list.create" text="新建"/></button>
			  <button class="btn btn-default btn-sm a-remove" onclick="table.removeAll()"><spring:message code="core.list.delete" text="删除"/></button>
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
        <form id="roleDefGridForm" name="roleDefGridForm" method='post' action="role-def-remove.do" class="m-form-blank">
		    <table id="roleDefGrid" class="table table-hover table-bordered">
		      <thead>
		        <tr>
		          <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
		          <th class="sorting" name="id"><spring:message code="auth.roleDef.list.id" text="编号"/></th>
		          <th class="sorting" name="name"><spring:message code="auth.roleDef.list.name" text="名称"/></th>
		          <th width="200">&nbsp;</th>
		        </tr>
		      </thead>
		      <tbody>
		        <c:forEach items="${page.result}" var="item">
		        <tr>
		          <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
		          <td>${item.id}</td>
		          <td>${item.name}</td>
		          <td>
		            <a href="role-def-input.do?id=${item.id}" class="a-update"><spring:message code="core.list.edit" text="编辑"/></a>&nbsp;&nbsp;
		            <a href="role-menu-input.do?id=${item.id}" class="a-config"><spring:message code="auth.roleDef.list.perm" text="菜单权限"/></a>&nbsp;&nbsp;
		            <a href="role-perm-input.do?id=${item.id}" class="a-config"><spring:message code="auth.roleDef.list.perm" text="标签权限"/></a>
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
		  <button class="btn btn-default btn-sm">&lt;</button>
		  <button class="btn btn-default btn-sm">1</button>
		  <button class="btn btn-default btn-sm">&gt;</button>
		</div>
	<!-- end of main -->
	</div>
  </body>
</html>

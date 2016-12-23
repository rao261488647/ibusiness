<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "table");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>表存储列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'tableModelGrid',
			selectedItemClass: 'selectedItem',
			gridFormId: 'tableGridForm'
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
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
          <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">表存储模型</h4></div>
          <div class="panel-body">
                <div id="tableCategorySearch" class="content content-inner">
				  	<button class="btn btn-primary btn-sm a-insert" onclick="location.href='conf-table-insert.do?packageName=${packageName}&tableName=&isBpmTable=2'">新建</button>
				  	<button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()">删除</button>
				</div>
            </div>
            <form id="tableGridForm" name="tableGridForm" method='post' action="conf-table-remove.do?packageName=${packageName}" class="m-form-blank">
				  <table class="table table-hover table-striped" id=“tableModelGrid” >
						<thead>
							<tr>
							    <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
								<th width="30">&nbsp;</th>
								<th>表名</th>
								<th>标题</th>
								<th>所在模块</th>
								<th>唯一标识</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${tableInfoList}" var="item">
						    <tr>
							    <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
								<td>
						          <a href="conf-table-column-list.do?tableName=${item.tableName}&isBpmTable=2" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
						        </td>
								<td><a href="conf-table-insert.do?packageName=${packageName}&tableName=${item.tableName}&isBpmTable=2" class="a-update">${item.tableName}</a></td>
								<td>${item.tableNameComment}</td>
								<td>${item.packageName}</td>
								<td>${item.id}</td>
						    </tr>
						  </c:forEach>
						</tbody>
				  </table>
			</form>
     </div>
     
	<!-- end of main -->
  </body>
</html>
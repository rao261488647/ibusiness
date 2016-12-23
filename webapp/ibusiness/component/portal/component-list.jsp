<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>业务模块组件</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'codeGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount:${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? '' : page.orderBy}',
		    asc: ${page.asc},
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm'
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
    <div class="row">
	<%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>
	
	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">业务模块</h4></div>
        <div class="panel-body">
              <div class="pull-left">
		           <p>
					  <button class="btn btn-primary  btn-sm a-insert" onclick="location.href='component-input.do'">新建</button>
					  <button class="btn btn-primary  btn-sm a-remove" onclick="table.removeAll()">删除</button>
				  </p>
			  </div>
			  <div class="pull-right">
				  每页显示
				  <select class="m-page-size">
				    <option value="10">10</option>
				    <option value="50">50</option>
				    <option value="100">100</option>
				  </select>
				  条
			  </div>
	   </div>
	   <div class="content">
				  <form id="gridForm" name="gridForm" method='post' action="serviceModule-remove.do" class="m-form-blank">
						  <table id="codeGrid" class="table table-hover table-striped">
						    <thead>
						      <tr>
						        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					                <th class="sorting" name="packagename">模块包名(小写)</th>
					                <th class="sorting" name="modulename">模块分类名</th>
					                <th class="sorting" name="parentid">父节点ID</th>
					                <th class="sorting" name="typeid">类型ID</th>
						        <th width="30">&nbsp;</th>
						      </tr>
						    </thead>
						    <tbody>
						      <c:forEach items="${page.result}" var="item">
						      <tr>
						        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
							        <td>${item.packagename}</td>
							        <td>${item.modulename}</td>
							        <td>${item.parentid}</td>
							        <td>${item.typeid}</td>
						        <td>
						          <a href="component-input.do?id=${item.id}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
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
						  <button class="btn btn-primary  btn-sm">&lt;</button>
						  <button class="btn btn-primary  btn-sm">1</button>
						  <button class="btn btn-primary  btn-sm">&gt;</button>
				  </div>
          </div>
      </div>
	<!-- end of main -->
	</div>
  </body>
</html>

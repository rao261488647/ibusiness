<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>表单列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
			    id: 'formGrid',
			    pageNo: ${page.pageNo},
			    pageSize: ${page.pageSize},
			    totalCount: ${page.totalCount},
			    resultSize: ${page.resultSize},
			    pageCount: ${page.pageCount},
			    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
			    asc: ${page.asc},
			    params: {
			        'packageName': '${packageName}'
			    },
				selectedItemClass: 'selectedItem',
				gridFormId: 'formGridForm'
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">表单列表</h4></div>
          <div class="panel-body">
			    <div class="pull-left">
			        <p>
					    <button class="btn btn-primary btn-sm a-insert" onclick="location.href='conf-form-input.do?packageName=${packageName}&isBpmForm=1'">新建</button>
					    <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()">删除</button>
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
				<div class="m-clear"></div>
	    </div>
		<div class="content">
			<form id="formGridForm" name="formGridForm" method='post' action="conf-form-remove.do?packageName=${packageName}" class="m-form-blank">
			  <table id="formGrid" class="table table-hover table-striped">
			    <thead>
			      <tr>
			        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			        <th class="sorting"><spring:message code="user.user.list.packageName" text="包名"/></th>
			        <th class="sorting" ><spring:message code="user.user.list.formName" text="表单名"/></th>
			        <th class="sorting"><spring:message code="user.user.list.formTitle" text="表单标题"/></th>
			        <th width="30">&nbsp;</th>
			      </tr>
			    </thead>
			    
			    <tbody>
			      <c:forEach items="${page.result}" var="item">
			      <tr>
			        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
			        <td>${item.packageName}</td>
			        <td>${item.formName}</td>
			        <td>${item.formTitle}</td>
			        <td>
			          <a href="conf-form-input.do?formId=${item.id}&packageName=${packageName}&isBpmForm=1" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
			        </td>
			      </tr>
			      </c:forEach>
			    </tbody>
			  </table>
			</form>
        </div>
	  <article>
	    <div class="m-page-info pull-left">
		  共100条记录 显示1到10条记录
		</div>
		<div class="btn-group m-pagination pull-right">
		  <button class="btn btn-primary btn-sm">&lt;</button>
		  <button class="btn btn-primary btn-sm">1</button>
		  <button class="btn btn-primary btn-sm">&gt;</button>
		</div>
	    <div class="m-clear"></div>
      </article>
    </div>
    
	<!-- end of main -->
  </body>
</html>
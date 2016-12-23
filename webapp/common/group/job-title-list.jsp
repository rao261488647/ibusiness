<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "job");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>职位名称管理</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'orgGrid',
    pageNo: ${page.pageNo},
    pageSize: ${page.pageSize},
    totalCount: ${page.totalCount},
    resultSize: ${page.resultSize},
    pageCount: ${page.pageCount},
    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
    asc: ${page.asc},
    params: {
        'filter_LIKES_orgname': '${param.filter_LIKES_orgname}',
        'filter_EQI_status': '${param.filter_EQI_status}'
    },
	selectedItemClass: 'selectedItem',
	gridFormId: 'orgGridForm',
	exportUrl: 'job-title-export.do'
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
        <div class="panel-heading"><h4 class="panel-title">职位名称管理</h4></div>
        <div class="panel-body">
            <div class="pull-left">
	          <button class="btn btn-default btn-sm a-insert" onclick="location.href='job-title-input.do'">新建</button>
			  <button class="btn btn-default btn-sm a-remove" onclick="table.removeAll()">删除</button>
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
        <form id="orgGridForm" name="orgGridForm" method='post' action="job-title-remove.do" class="m-form-blank">
			  <table id="orgGrid" class="table table-hover table-striped">
			    <thead>
			      <tr>
			        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			        <th class="sorting" name="orders">排序</th>
			        <th class="sorting" name="name">名称</th>
			        <th width="30">&nbsp;</th>
			      </tr>
			    </thead>
			
			    <tbody>
			      <c:forEach items="${page.result}" var="item">
			      <tr>
			        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
			        <td>${item.orders}</td>
			        <td>${item.name}</td>
			        <td>
			          <a href="job-title-input.do?id=${item.id}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
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
    </div>
	<!-- end of main -->
  </body>
</html>

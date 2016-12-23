<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>栏目列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'cmsCatalogGrid',
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
	gridFormId: 'cmsCatalogGridForm',
	exportUrl: 'cms-catalog-export.do'
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
    <div class="row">
    <div class="col-lg-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
          <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
          <div class="panel-body">
        	  <div id="cmsCatalogSearch" class="content content-inner">
				  <form name="cmsCatalogForm" method="post" action="cms-catalog-list.do" class="form-inline">
				    <label for="cmsCatalog_name"><spring:message code='cmsCatalog.cmsCatalog.list.search.name' text='账号'/>:</label>
				    <input type="text" id="cmsCatalog_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">
					<button class="btn btn-primary btn-small" onclick="document.cmsCatalogForm.submit()">查询</button>
				  </form>
			  </div>
		</div>
		<div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">文章列表</h4></div>
          <div class="panel-body">
			    <div class="pull-left">
			        <p>
					    <button class="btn btn-primary btn-sm a-insert" onclick="location.href='cms-catalog-input.do'">新建</button>
					    <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()">删除</button>
				    </p>
				</div>
				<div class="pull-right">
				  每页显示
				  <select class="m-page-size">
				    <option value="5">5</option>
				    <option value="10">10</option>
				    <option value="50">50</option>
				    <option value="100">100</option>
				  </select>
				  条
				</div>
				<div class="m-clear"></div>
	    </div>
	    <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">栏目列表</h4></div>
        <div class="panel-body">
	          <div class="content">
				<form id="cmsCatalogGridForm" name="cmsCatalogGridForm" method='post' action="cms-catalog-remove.do" class="m-form-blank">
				  <table id="cmsCatalogGrid" class="table table-hover table-striped">
				    <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
				        <th class="sorting" ><spring:message code="cmsCatalog.cmsCatalog.list.id" text="编号"/></th>
				        <th class="sorting" >名称</th>
				        <th class="sorting" >类型</th>
				        <th width="30">&nbsp;</th>
				      </tr>
				    </thead>
				
				    <tbody>
				      <c:forEach items="${page.result}" var="item">
				      <tr>
				        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
				        <td>${item.id}</td>
				        <td>${item.name}</td>
				        <td>${item.catalogType}</td>
				        <td>
				          <a href="cms-catalog-input.do?id=${item.id}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
				        </td>
				      </tr>
				      </c:forEach>
				    </tbody>
				  </table>
				</form>
		    </div>
        </div>
	  <article>
	    <div class="m-page-info pull-left">
		  共100条记录 显示1到10条记录
		</div>
		<div class="btn-group m-pagination pull-right">
		  <button class="btn btn-small">&lt;</button>
		  <button class="btn btn-small">1</button>
		  <button class="btn btn-small">&gt;</button>
		</div>
	    <div class="m-clear"></div>
      </article>
    </div>
	<!-- end of main -->
	</div>

  </body>

</html>

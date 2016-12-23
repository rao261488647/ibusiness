<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "doc");%>
<%pageContext.setAttribute("currentMenu", "doc");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="dev.doc-info.list.title" text="列表"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'doc-infoGrid',
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
			gridFormId: 'doc-infoGridForm',
			exportUrl: 'doc-info-export.do'
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
	          <div id="doc-infoSearch" class="content content-inner">
				  <form name="doc-infoForm" method="post" action="doc-info-list.do" class="form-inline">
				    <label for="doc-info_name"><spring:message code='doc-info.doc-info.list.search.name' text='名称'/>:</label>
				    <input type="text" id="doc-info_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">
					<button class="btn btn-primary btn-small a-search" onclick="document.doc-infoForm.submit()">查询</button>&nbsp;
				  </form>
			  </div>
	      </div>
		  <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">文件列表</h4></div>
          <div class="panel-body">
			    <div class="pull-left">
			        <p>
					    <button class="btn btn-primary btn-sm a-insert" onclick="location.href='doc-info-input.do'">新建</button>
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
		
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">列表</h4></div>
        <div class="panel-body">
	          <div class="content">
				<form id="doc-infoGridForm" name="doc-infoGridForm" method='post' action="doc-info-remove.do" class="m-form-blank">
				  <table id="doc-infoGrid" class="table table-hover table-striped">
				    <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
				        <th width="60">&nbsp;</th>
				        <th class="sorting" >类别</th>
				        <th class="sorting" >名称</th>
				        <th class="sorting" >创建时间</th>
				      </tr>
				    </thead>
				
				    <tbody>
				      <c:forEach items="${page.result}" var="item">
				      <tr>
				        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
				        <td>
				          <a href="doc-info-input.do?id=${item.id}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
				          <a href="doc-info-download.do?filename=${item.name}&path=${item.name}"><span class="glyphicon glyphicon-download"></span></a>
				        </td>
				        <td>${item.docType}</td>
				        <td>${item.name}</td>
				        <td>${item.createTime}</td>
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

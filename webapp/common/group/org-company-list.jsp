<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "group");%>
<!doctype html>
<html lang="en">
   
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>公司列表</title>
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
	exportUrl: 'org-company-export.do'
};

var table;

$(function() {
	table = new Table(config);
    table.configPagination('.m-pagination');
    table.configPageInfo('.m-page-info');
    table.configPageSize('.m-page-size');
});


// 导入excel
function importExcel(){
	 $("#msgInfoForm").submit();
}
 
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="col-lg-1"></div>
    
	<!-- start of main -->
    <div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">公司列表</h4></div>
        <div class="panel-body">
		    <div class="pull-left">
		        <div class="form-group">
			    	<button class="btn btn-primary btn-sm" onclick="location.href='org-company-input.do'">新建</button>
				    <button class="btn btn-primary btn-sm" onclick="table.removeAll()">删除</button>
				    <button class="btn btn-primary btn-sm" onclick="table.exportExcel()">导出Excel</button>
				    <button class="btn btn-primary btn-sm"  onclick="importExcelAdd.click()">导入Excel</button>
				    <form id="msgInfoForm" method="post" action="org-company-importExcel.do" class="form-horizontal" enctype="multipart/form-data">
                        <input id="importExcelAdd" type="file" name="attachment"  style="display:none;" onChange="importExcel()"> 
                    </form>
				</div>
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
	    	<div class="m-clear"></div>
	    </div>
			<form id="orgGridForm" name="orgGridForm" method='post' action="org-company-remove.do" class="form-horizontal">
			  <table id="orgGrid" class="table table-hover table-striped">
			    <thead>
			      <tr>
			        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			        <th class="sorting" name="id"><spring:message code="org.org.list.id" text="编号"/></th>
			        <th class="sorting" name="name">名称</th>
			        <th width="30">&nbsp;</th>
			      </tr>
			    </thead>
			
			    <tbody>
			      <c:forEach items="${page.result}" var="item">
			      <tr>
			        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
			        <td>${item.id}</td>
			        <td>${item.name}</td>
			        <td>
			          <a href="org-company-input.do?id=${item.id}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
			        </td>
			      </tr>
			      </c:forEach>
			    </tbody>
			  </table>
			</form>

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
      <div class="m-spacer"></div>
	<!-- end of main -->
	</div>

  </body>

</html>

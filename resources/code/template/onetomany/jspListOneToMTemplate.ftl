<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>${confForm.formTitle}列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'codeGrid',
		    pageNo: ${"$" + "{page.pageNo}"},
		    pageSize: ${"$" + "{page.pageSize}"},
		    totalCount:${"$" + "{page.totalCount}"},
		    resultSize: ${"$" + "{page.resultSize}"},
		    pageCount: ${"$" + "{page.pageCount}"},
		    orderBy: '${"$" + "{page.orderBy == null ? "+ "''" +" : page.orderBy}"}',
		    asc: ${"$" + "{page.asc}"},
		    params: {
		        'filter_LIKES_id': '${"$" + "{param.filter_LIKES_id}"}'
		        <#list formTableColumnList as formTableColumn>
				    <#if  '1' ==formTableColumn.fcQuery >
		                ,'filter_LIKES_${formTableColumn.tableColumnLower}': '${'$' + '{param.filter_LIKES_${formTableColumn.tableColumnLower}}'}'
		            </#if>
		        </#list>
		    },
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
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10 col-md-10 col-sm-10 ">
	    <!-- 查询条件 -->
	    <#if  confForm.isQuery=1 >
	          <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
	          <div class="panel-body">
		          <div id="search" class="content content-inner">
					  <form name="cgForm" method="post" action="${entityName?uncap_first}-list.do" class="form-inline">
					    <div class="form-group">
					        <#list formTableColumnList as formTableColumn>
					            <#if  '1' ==formTableColumn.fcQuery >
					                <label for="code_table_${formTableColumn.tableColumnLower}">${formTableColumn.formColumnTitle}:</label>
					                <input type="text" id="code_table_${formTableColumn.tableColumnLower}" name="filter_LIKES_${formTableColumn.tableColumnLower}" value="${'$' + '{param.filter_LIKES_${formTableColumn.tableColumnLower}}'}">
					            </#if>
					        </#list>
						    <button class="btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
						</div>
					 </form>
				  </div>
			  </div>
	      </#if>
		      <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">${confForm.formTitle}列表</h4></div>
		      <div class="panel-body">
				    <div class="pull-left">
					    <button class="btn btn-primary btn-sm a-insert" onclick="location.href='${entityName?uncap_first}-input.do?flowId=${'$' + '{flowId}'}'"><span class="glyphicon glyphicon-tasks"></span>新建</button>
					    <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()"><span class="glyphicon glyphicon-trash"></span>删除</button>
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
			    <div class="content">
					<form id="gridForm" name="gridForm" method='post' action="${entityName?uncap_first}-remove.do" class="m-form-blank">
					  <table id="codeGrid" class="table table-hover table-striped">
					      <thead>
						      <tr>
						          <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
						          <th width="30">&nbsp;</th>
						          <#list columns as column>
						              <#if column.fcDisplay="1">
				                          <th class="sorting">${column.filedComment}</th>
				                      </#if>
				                  </#list>
						          
						      </tr>
						    </thead>
							    <tbody>
							      <c:forEach items="${'$' + '{page.result}'}" var="item" varStatus="status">
							      <tr class="${'$' + '{status.index%2==1? \'active\':\'\'}'}">
							        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${'$' + '{item.id}'}"></td>
							        <td>
							          <a href="${entityName?uncap_first}-input.do?id=${'$' + '{item.id}'}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
							        </td>
							        <#list columns as column>
							            <#if column.fcDisplay="1">
				                            <td>${'$' + '{item.${column.fieldName}}'}</td>
				                        </#if>
				                    </#list>
							        
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
					  <button class="btn btn-small">${"&lt"};</button>
					  <button class="btn btn-small">1</button>
					  <button class="btn btn-small">${"&gt"};</button>
					</div>
				    <div class="m-clear"></div>
			      </article>
			  </div>
			  
	    </div>
	<!-- end of main -->
	</div>
  </body>
</html>

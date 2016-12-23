<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>表列字段设置</title>
    <!-- Bootstrap -->
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
		});
    </script>
  </head>
  <body>
      <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
	  <%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>
	      <!-- start of main -->
	      <div class="panel panel-default col-lg-10">
              <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">表列字段</h4></div>
               <div class="panel-body">
	               <div id="tableCategorySearch" class="content content-inner">
	                     <button class="btn btn-primary btn-sm a-insert" onclick="location.href='conf-table-column-input.do?tableName=${tableName}&columnValue='">新建</button>
					  	 <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()">删除</button>
					  	 <label class="control-label" >${tableName}表:</label>
					</div>
               </div>
               <form id="tableGridForm" name="tableGridForm" method='post' action="conf-table-columns-remove.do?tableName=${tableName}" class="m-form-blank">
			          <table class="table table-hover table-striped">
						<thead>
						  <tr>
						    <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
							<th>字段</th>
							<th>字段标题</th>
							<th>类型</th>
							<th>长度</th>
							<th>允许空</th>
							<th>默认值</th>
							<th>排序</th>
							<th width="30">&nbsp;</th>
						  </tr>
						</thead>
						<tbody>
						<c:forEach items="${tableInfoList}" var="item">
						  <tr>
						    <td>
							    <c:if test="${item.columnValue!='ID' && item.columnValue!='SCOPEID' && item.columnValue!='CREATEDATEBPM'  && item.columnValue!='EXECUTIONID'  && item.columnValue!='NODENAME'  && item.columnValue!='TASKTITLE' && item.columnValue!='ASSIGNEEUSER' && item.columnValue!='USERNAME' && item.columnValue!='DONEFLAG' && item.columnValue!='PARENTID'}">
							    	<input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.columnValue}">
							    </c:if>
						    </td>
						    <td>${item.columnValue}</td>
							<td>${item.columnName}</td>
							<td>${item.columnType}</td>
							<td>${item.columnSize}</td>
							<td>${item.isNull}</td>
							<td>${item.defaultValue}</td>
							<td>${item.columnNo}</td>
							<td>
								<c:if test="${item.columnValue!='ID' && item.columnValue!='SCOPEID' && item.columnValue!='CREATEDATEBPM'  && item.columnValue!='EXECUTIONID'  && item.columnValue!='NODENAME'  && item.columnValue!='TASKTITLE' && item.columnValue!='ASSIGNEEUSER' && item.columnValue!='USERNAME' && item.columnValue!='DONEFLAG' && item.columnValue!='PARENTID'}">
						            <a href="conf-table-column-input.do?tableName=${tableName}&columnValue=${item.columnValue}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
					            </c:if>
					        </td>
						  </tr>
						  </c:forEach>
						</tbody>
					  </table>
					</form>
            </div>
    <!-- end of main -->
  </body>
</html>

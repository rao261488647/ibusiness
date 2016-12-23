<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>流程节点配置列表</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>

	<div class="row">
	<%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
		<div class="panel panel-default col-lg-10">
	          <div class="panel panel-primary">
              		  <!-- 子表 -->
					  <c:if test="${null != bpmNodeTables}">
					  <c:forEach items="${bpmNodeTables}" var="confNodeColumns">
						      <form   method="post" action="save-conf-node-colums.do?packageName=${packageName}&flowId=${flowId}&nodeId=${nodeId}" class="form-horizontal">
								  <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">表字段设置：</h4></div>
				        		  <div class="panel-body">
						        	      <table class="table table-hover table-striped" id=“tableModelGrid” >
												<thead>
													<tr>
													    <th>字段</th>
													    <th>显示标题</th>
													    <th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)">是否显示</th>
													    <th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems2(this.checked)">是否编辑</th>
														<th width="120">&nbsp;</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach items="${confNodeColumns.bpmNodeColumsList}" var="item" varStatus="status">
												    <input  type="hidden" name="bpmNodeColumsList[${status.index}].id" value="${item.id}">
												    <tr>
												        <td><input  type="hidden" name="bpmNodeColumsList[${status.index}].formColumn" value="${item.formColumn}">${item.formColumn}</td>
												        <td>${item.formColumnTitle}</td>
												        <td><input type="checkbox" class="selectedItem a-check" name="bpmNodeColumsList[${status.index}].fcDisplay" value="1"  ${item.fcDisplay==1 ? 'checked' : ''}>显示</td>
														<td><input type="checkbox" class="selectedItem2 a-check" name="bpmNodeColumsList[${status.index}].fcEdit" value="1"  ${item.fcEdit==1 ? 'checked' : ''}>可编辑</td>
														<td>
												            <a href="conf-formLabel-input.do?packageName=${item.packageName}&formName=${item.formName}&formColumn=${item.formColumn}" class="a-update"><spring:message code="core.list.edit" text="编辑"/></a>
												        </td>
												    </tr>
												  </c:forEach>
												</tbody>
										  </table>
							  	   </div>
								  <!-- 保存 -->
								  <div class="col-lg-10 col-lg-offset-2">
								      <button  class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
								      <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
								  </div>
							  </form>
					  </c:forEach>
					  </c:if>
					  
			 </div>
	    </div>
	<!-- end of main -->
	</div>

  </body>

</html>

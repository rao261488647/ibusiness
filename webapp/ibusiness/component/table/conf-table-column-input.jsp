<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑表列字段信息</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		$(function() {
		    $("#tableForm").validate({
		        submitHandler: function(form) {
					bootbox.animate(false);
					var box = bootbox.dialog('<div class="progress" ><div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"> 60% </div></div>');
					form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		})
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
	<%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
          <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑列字段</h4></div>
          <div class="panel-body">
	          <form id="tableForm" method="post" action="conf-table-columns-update.do?tableName=${tableName}" class="form-horizontal">
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnValue">字段名:</label>
				      <c:if test="${beanInfo != null}">
				          <label id="table-columnValue" class="control-label">${beanInfo.columnValue}</label>
				          <input id="hidden-columnValue" type="hidden" name="columnValue" value="${beanInfo.columnValue}">
				      </c:if>
				      <c:if test="${beanInfo == null}">
					  	  <input id="table-columnValue" type="text" name="columnValue" value="${beanInfo.columnValue}"  class="text required" >
				      </c:if>
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnName">字段标题:</label>
					  <input id="table-columnName" type="text" name="columnName" value="${beanInfo.columnName}"  class="text required" >
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnType">字段类型:</label>
				      <div class="col-lg-3">
							 <select class="form-control"  id="table-columnType" name="columnType" >
							      <option ${beanInfo.columnType == "VARCHAR" ? 'selected' : ''} >VARCHAR</option>
							      <option ${beanInfo.columnType == "NUMBER" ? 'selected' : ''} >NUMBER</option>
							      <option ${beanInfo.columnType == "DATE" ? 'selected' : ''} >DATE</option>
							 </select>
				       </div>
				   </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnSize">长度:</label>
					  <input id="table-columnSize" type="text" name="columnSize" value="${beanInfo.columnSize}"  class="text required" >
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-isNull">允许空</label>
					<div class="col-lg-3">
						<select class="form-control"  id="table-isNull" name="isNull"  >
						      <option ${beanInfo.isNull == "是" ? 'selected' : ''}  value="是">是</option>
						      <option ${beanInfo.isNull == "否" ? 'selected' : ''}  value="否">否</option>
						  </select>
				    </div>
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-defaultValue">默认值:</label>
					  <input id="table-defaultValue" type="text" name="defaultValue" value="${beanInfo.defaultValue}">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnNo">字段排序编号:</label>
					  <c:if test="${beanInfo != null}">
					      <input id="table-columnNo" type="text" name="columnNo" value="${beanInfo.columnNo}">
					  </c:if>
					  <c:if test="${beanInfo == null}">
					      <input id="table-columnNo" type="text" name="columnNo" value="${columnNo}">
					  </c:if>
				  </div>
				  
				  <div class="form-group">
				      <div class="col-lg-10 col-lg-offset-2">
					      <button id="submitButton" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
					      <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				      </div>
				  </div>
				</form>
          </div>
     </div>
	<!-- end of main -->
  </body>
</html>

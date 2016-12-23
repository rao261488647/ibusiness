<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑评论</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		$(function() {
		    $("#tableCommentForm").validate({
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">新建表存储模型</h4></div>
        <div class="panel-body">
        
			<div class="content content-inner">
				<form id="tableCommentForm" method="post" action="conf-table-save.do" class="form-horizontal">
				   <input id="table-isBpmTable" type="hidden" name="isBpmTable" value="${isBpmTable}">
				  <c:if test="${model != null}">
				      <input  type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-packageName">所在模块:</label>
					<div class="controls">
					    <label class="control-label"  id="table-packageName" >${packageName}</label>
					    <input id="hidden-packageName" type="hidden" name="packageName" value="${packageName}">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-tableName">表名称(大写):</label>
					<div class="controls">
					    <c:if test="${model == null}">
					        IB_<input id="table-tableName" type="text" name="tableName" value="${model.tableName}"  class="text required"  >
					    </c:if>
					    <c:if test="${model != null}">
						    <label class="control-label"  >${model.tableName}</label>
						    <input type="hidden" name="tableName" value="${model.tableName}">
					    </c:if>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-tableNameComment">标 题:</label>
					<div class="controls">
					  <input id="table-tableNameComment" type="text" name="tableNameComment" value="${model.tableNameComment}"  class="text required"  >
				    </div>
				  </div>
				  <!--  -->
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-type">表类型:</label>
					<div class="col-lg-3">
				          <select id="table-type" name="tableType"  class="form-control">
						        <option value="1" ${model.tableType == 1 ? 'selected' : ''}>单表/主表</option>
							    <option value="2" ${model.tableType == 2 ? 'selected' : ''}>子表</option>
						   </select>
				    </div>
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-mainTable">子表对应主表:</label>
					  <div class="controls">
					      <input id="table-mainTable" type="text" name="mainTable" value=""  class="text"  >
				      </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="controls">
				      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn btn-primary btn-sm a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				      <c:if test="${model != null}">
				          <a href="conf-table-data-synchronization.do?packageName=${packageName}&tableName=${model.tableName}&isBpmTable=${isBpmTable}" class="btn btn-primary btn-sm">表结构同步</a>
				      </c:if>
				    </div>
				  </div>
				</form>
			</div>
        </div>
    </div>
	<!-- end of main -->

  </body>

</html>

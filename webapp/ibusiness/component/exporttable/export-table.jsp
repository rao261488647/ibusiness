<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>导出表</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <script type="text/javascript">
		$(function() {
		    $("#cgForm").validate({
		        submitHandler: function(form) {
		            if (typeof(bootbox) != 'undefined') {
					    bootbox.animate(false);
					    var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
					}
					form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		})
    </script>
    <div class="col-lg-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="export-sql.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>

				<div class="form-group">
                          <label class="col-lg-2 control-label" for="code-formid">表单名:</label>
                          <div class="col-lg-3">    <select id="code-formid" name="formId" class="form-control required" >          <option value="" >请选择</option>        <c:forEach items="${formidItems}" var="item">          <option value="${item.key}" >${item.value}</option>        </c:forEach>    </select></div>
                          <label class="col-lg-2 control-label" for="code-flowid">流程名:</label>
                          <div class="col-lg-3">    <select id="code-flowid" name="flowId" class="form-control " >          <option value="" >请选择</option>        <c:forEach items="${flowidItems}" var="item">          <option value="${item.key}" >${item.value}</option>        </c:forEach>    </select></div>
                </div>
                <div class="form-group">
                          <label class="col-lg-2 control-label" for="code-filepath">文件路径:</label>
                          <div class="col-lg-6">    <textarea class="form-control required" id="code-filepath" name="filePath" rows="1" >${filepath}</textarea></div>
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

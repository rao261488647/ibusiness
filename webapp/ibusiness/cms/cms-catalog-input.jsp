<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑栏目</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#cmsCatalogForm").validate({
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
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="col-lg-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑栏目</h4></div>
        <div class="panel-body">
			<div class="content content-inner">
				<form id="cmsCatalogForm" method="post" action="cms-catalog-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				  <input id="cms-catalog_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >名称</label>
					  <input  type="text" name="name" value="${model.name}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >别名</label>
					  <input  type="text" name="code" value="${model.code}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >图标</label>
					  <input  type="text" name="logo" value="${model.logo}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >类型</label>
					  <input  type="text" name="catalogType" value="${model.catalogType}" size="40" class="text number"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >首页模板</label>
					  <input  type="text" name="templateIndex" value="${model.templateIndex}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >列表页模板</label>
					  <input  type="text" name="templateList" value="${model.templateList}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >详情页模板</label>
					  <input  type="text" name="templateDetail" value="${model.templateDetail}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" >描述</label>
					  <input  type="text" name="description" value="${model.description}" size="40" class="text required"  maxlength="50">
				  </div>
				  <div class="form-group">
				    <div class="controls">
				      <button id="submitButton" class="btn a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
	<!-- end of main -->
	</div>
	
  </body>

</html>

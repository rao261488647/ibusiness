<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.perm.input.title" text="编辑标签级权限"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#permForm").validate({
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
        <div class="panel-heading"><h4 class="panel-title">编辑标签级权限</h4></div>
        <div class="panel-body">
      		<form id="permForm" method="post" action="perm-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				  		<input id="perm_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="perm_code">标签value:</label>
					  <div class="col-lg-3">
				          <input id="perm_code" type="text" name="code" value="${model.code}" class="text">
				      </div>
				  </div>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="perm_name">名称:</label>
					  <div class="col-lg-3">
				          <input id="perm_name" type="text" name="name" value="${model.name}" class="text">
				      </div>
				  </div>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="access_perm">权限分类:</label>
					  <div class="col-lg-3">
						  <select class="form-control" id="access_perm" name="permTypeId">
						    <c:forEach items="${permTypes}" var="item">
						    <option value="${item.id}" ${model.permType.id==item.id ? 'selected' : ''}>${item.name}</option>
							</c:forEach>
						  </select>
					  </div>
				  </div>
				  <div class="form-group">
                      <div class="col-lg-10 col-lg-offset-2">
					      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
					      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
			</form>
        </div>
    </div>
	<!-- end of main -->
	
  </body>
</html>
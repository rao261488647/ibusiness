<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.roleDef.input.title" text="编辑角色"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#roleDefForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress" ><div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"> 60% </div></div>');
			form.submit();
        },
        errorClass: 'validate-error',
        rules: {
            name: {
                remote: {
                    url: 'role-def-checkName.do',
                    data: {
                        <c:if test="${model != null}">
                        id: function() {
                            return $('#roleDef_id').val();
                        }
                        </c:if>
                    }
                }
            }
        },
        messages: {
            name: {
                remote: "<spring:message code='auth.roleDef.input.duplicate' text='存在重复名称'/>"
            }
        }
    });
})
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title">编辑角色</h4></div>
        <div class="panel-body">
     		  <form id="roleDefForm" method="post" action="role-def-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				  <input id="roleDef_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <p>
					  <label class="control-label" for="roleDef_name"><spring:message code='auth.roleDef.input.name' text='名称'/></label>
				      <input id="roleDef_name" type="text" name="name" value="${model.name}"  class="text required"  maxlength="50">
				  </p>
				  <p>
				      <label class="control-label" for="roleDef_descn"><spring:message code='auth.roleDef.input.description' text='描述'/></label>
				      <textarea id="roleDef_descn" name="descn" maxlength="60" rows="4">${model.descn}</textarea>
				  </p>
				  <p>
				      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
				  </p>
			</form>
        </div>
	<!-- end of main -->
	</div>
  </body>
</html>

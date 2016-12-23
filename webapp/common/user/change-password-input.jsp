<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="user.user.changepassword.title" text="修改密码"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $('#userForm').validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
            form.submit();
        },
        errorClass: 'validate-error'
    });
});
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="row">
	    <div class="col-lg-1"></div>
	
		<!-- start of main -->
		<div class="panel panel-default col-lg-10"> 
	        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">修改密码</h4></div>
	        <div class="panel-body">
	          <form id="userForm" method="post" action="change-password-save.do?operationMode=STORE" class="form-horizontal">
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="oldPassword"><spring:message code="user.user.changepassword.old" text="原密码"/>:</label>
					<div class="controls">
					  <input id="oldPassword" name="oldPassword" type="password" value="" class="col-lg-3 required" maxlength="20">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="oldPassword"><spring:message code="user.user.changepassword.new" text="新密码"/>:</label>
					<div class="controls">
				      <input id="newPassword" name="newPassword" type="password" value="" class="col-lg-3 required" maxlength="20">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="confirmPassword"><spring:message code="user.user.changepassword.confirm" text="确认密码"/>:</label>
					<div class="controls">
				      <input id="confirmPassword" name="confirmPassword" type="password" value="" class="col-lg-3 required" maxlength="20" equalTo="#newPassword">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="controls">
				      <button id="submitButton" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" onclick="history.back();" class="btn btn-primary"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
			  </form>
	        </div>
		<!-- end of main -->
		</div>
    </div>
  </body>

</html>

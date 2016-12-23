<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "user");%>
<%pageContext.setAttribute("currentMenu", "user");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>修改信息</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#userForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
            form.submit();
        },
        errorClass: 'validate-error',
        rules: {
            username: {
                remote: {
                    url: 'user-base-checkUsername.do',
                    data: {
                        <c:if test="${model != null}">
                        id: function() {
                            return $('#user-base_id').val();
                        }
                        </c:if>
                    }
                }
            }
        },
        messages: {
            username: {
                remote: "<spring:message code='user.user.input.duplicate' text='存在重复账号'/>"
            }
        }
    });
})
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="span=1"></div>

	<!-- start of main -->
	<div class="panel panel-default span11">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑用户</h4></div>
          <div class="panel-body">
              <div class="content content-inner">
		          <form id="userForm" method="post" action="profile-save.do?operationMode=STORE" class="form-horizontal">
						<input id="user-base_userRepoId" type="hidden" name="userRepoId" value="1">
						<c:if test="${model != null}">
						  	<input id="user-base_id" type="hidden" name="id" value="${model.id}">
						</c:if>
						<div class="form-group">
						      <label class="control-label col-lg-2" for="user-base_username"><spring:message code="user.user.input.username" text="账号"/></label>
							  <div style="padding-top:5px;">${model.username}</div>
						</div>
						<div class="form-group">
						      <label class="control-label col-lg-2" for="user-base_displayName">显示名</label>
							  <input id="user-base_displayName" type="text" name="displayName" value="${model.displayName}"  class="text required" minlength="2" maxlength="50">
						</div>
					    <div class="form-group">
						      <label class="control-label col-lg-2" for="user-base_email">邮箱</label>
							  <input id="user-base_email" type="text" name="email" value="${model.email}">
						</div>
						<div class="form-group">
						      <label class="control-label col-lg-2" for="user-base_mobile">手机</label>
							  <input id="user-base_mobile" type="text" name="mobile" value="${model.mobile}">
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
      </div>
      
      
      
      
    <section id="m-main" class="span12">

      <article class="m-widget">
        <header class="header">
		  <h4 class="panel-title glyphicon glyphicon-paperclip"><spring:message code="user.user.input.title" text=""/></h4>
		</header>
		<div class="content content-inner">
			
		</div>
      </article>

    </section>
	<!-- end of main -->
	</div>

  </body>

</html>

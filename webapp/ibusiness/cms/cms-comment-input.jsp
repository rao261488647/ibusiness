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
    $("#cmsCommentForm").validate({
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑评论</h4></div>
        <div class="panel-body">
			<div class="content content-inner">
				<form id="cmsCommentForm" method="post" action="cms-comment-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				      <input id="cms-comment_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <div class="form-group">
				    <label class="control-label" for="cms-comment_cmsCommentname">标题</label>
					<div class="controls">
					  <input id="cms-comment_cmsCommentname" type="text" name="title" value="${model.title}" size="40" class="text required"  maxlength="50">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label" for="cms-comment_cmsCommentname">内容</label>
					<div class="controls">
					  <input id="cms-comment_cmsCommentname" type="text" name="content" value="${model.content}" size="40" class="text required"  maxlength="50">
				    </div>
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

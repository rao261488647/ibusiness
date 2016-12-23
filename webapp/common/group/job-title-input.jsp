<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "job");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑职位名称</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#orgForm").validate({
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
        <div class="panel-heading"><h4 class="panel-title">编辑职位名称</h4></div>
        <div class="panel-body">
	        <form id="orgForm" method="post" action="job-title-save.do" class="form-horizontal">
			  <c:if test="${model != null}">
			  <input id="org_id" type="hidden" name="id" value="${model.id}">
			  </c:if>
				<div class="form-group">
					<label class="col-lg-2 control-label" for="code-name">名称:</label>
					<div class="col-lg-3">
						<input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm">
					</div>
					<label class="col-lg-2 control-label" for="code-orders">排序:</label>
					<div class="col-lg-3">
						<input id="code-orders" type="text" name="orders" value="${model.orders}" class="text form-control input-sm number">
					</div>
				</div>
			    <div class="form-group">
	                  <div class="col-lg-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="history.back();" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span>返回</button>
	                  </div>
                </div>
			</form>
        
        </div>
    </div>
	<!-- end of main -->

  </body>

</html>

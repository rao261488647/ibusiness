<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "job");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑职务类型</title>
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
        <div class="panel-heading"><h4 class="panel-title">编辑职务类型</h4></div>
        <div class="panel-body">
        
        <form id="orgForm" method="post" action="job-type-save.do" class="form-horizontal">
		  <c:if test="${model != null}">
		  <input id="org_id" type="hidden" name="id" value="${model.id}">
		  </c:if>
		  <p>
		      <label class="control-label" for="org_orgname"><spring:message code="org.org.input.orgname" text="名称"/>:</label>
			  <input id="org_orgname" type="text" name="name" value="${model.name}"  class="text required"  maxlength="50">
		  </p>
		  <p>
		    <label class="control-label" for="job_level">上级:</label>
			  <select class="form-control" id="job_level" name="jobTypeId">
			      <option value=""></option>
				  <c:forEach items="${jobTypes}" var="item">
				    <option value="${item.id}">${item.name}</option>
				  </c:forEach>
			  </select>
		  </p>
		  <p>
		      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
		      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
		  </p>
		</form>
        
        </div>
    </div>
	<!-- end of main -->
  </body>

</html>

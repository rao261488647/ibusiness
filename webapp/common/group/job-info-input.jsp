<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "job");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑职务</title>
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
        <div class="panel-heading"><h4 class="panel-title">编辑职务</h4></div>
        <div class="panel-body">
	        <form id="orgForm" method="post" action="job-info-save.do" class="form-horizontal">
			  <c:if test="${model != null}">
			  	<input id="org_id" type="hidden" name="id" value="${model.id}">
			  </c:if>
			  <div class="form-group">
                    <label class="col-lg-2 control-label" for="job_title"><spring:message code="org.org.input.orgname" text="职务名称"/>:</label>
                    <div class="col-lg-3">
                         <select id="job_title" name="jobTitleId" class="form-control required" >
                               <option value="" >请选择</option>
                            <c:forEach items="${jobTitles}" var="item">
                               <option value="${item.id}" ${item.id==model.jobTitle.id? 'selected':''} >${item.name}</option>
                            </c:forEach>
                         </select>
                     </div>
              </div>
              <div class="form-group">
                    <label class="col-lg-2 control-label" for="job_type"><spring:message code="org.org.input.orgname" text="职称分类"/>:</label>
                    <div class="col-lg-3">
                         <select id="job_type" name="jobTypeId" class="form-control required" >
                               <option value="" >请选择</option>
                            <c:forEach items="${jobTypes}" var="item">
                               <option value="${item.id}" ${item.id==model.jobType.id? 'selected':''} >${item.name}</option>
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
	<!-- end of main -->
	</div>

  </body>

</html>

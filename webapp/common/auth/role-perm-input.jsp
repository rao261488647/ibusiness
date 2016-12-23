<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.role.perm.title" text="设置权限"/></title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title">设置权限</h4></div>
        <div class="panel-body">
       	 <form id="roleForm" method="post" action="role-perm-save.do" class="form-horizontal">
			  <input type="hidden" name="id" value="${id}">
				  <c:forEach items="${permTypes}" var="permType">
					  <div class="form-group">
							<label class="control-label"><strong>${permType.name}:</strong></label>
						    <div class="controls">
							      <c:forEach items="${permType.perms}" var="item">
							        <input id="selectedItem-${item.id}" type="checkbox" name="selectedItem" value="${item.id}" <tags:contains items="${selectedItem}" item="${item.id}">checked</tags:contains>>
							        <label for="selectedItem-${item.id}" style="display:inline;">${item.name}</label>
									&nbsp;
							      </c:forEach>
						    </div>
					  </div>
				  </c:forEach>
				  <div class="form-group">
				    <div class="controls">
				      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
			</form>
        </div>
     </div>
     
	<!-- end of main -->
  </body>
</html>

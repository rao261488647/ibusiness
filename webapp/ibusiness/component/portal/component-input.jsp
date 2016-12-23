<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "serviceModule");%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="user.user.input.title" text="编辑分类"/></title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <div class="col-lg-1"></div>
	<!-- start of main -->
    <div class="panel panel-default col-lg-10 " >
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑分类</h4></div>
        <div class="panel-body">
		    <div class="content content-inner">
				<form id="userForm" method="post" action="serviceModule-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				      <input id="code_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
					  <div class="form-group">
					    <label class="col-lg-2 control-label" for="code-packagename">模块包名(全小写):</label>
						<div class="col-lg-3">
						  <input id="code-packagename" type="text" name="packagename" value="${model.packagename}"  class="text required"  >
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-lg-2 control-label" for="code-modulename">模块分类名(中文):</label>
						<div class="col-lg-3">
						  <input id="code-modulename" type="text" name="modulename" value="${model.modulename}"  class="text required"  >
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-lg-2 control-label" for="code-parentid">父节点ID:</label>
						<div class="col-lg-3">
						  <input id="code-parentid" type="text" name="parentid" value="0"  class="text required" readonly>
					    </div>
					  </div>
				  <div class="form-group">
					    <label class="col-lg-2 control-label" for="code-typeid">类型ID:</label>
						<div class="col-lg-3">
						  <input id="code-typeid" type="text" name="typeid" value="sModule"  class="text required" readonly>
					    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-lg-10 col-lg-offset-2">
				      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn btn-primary btn-sm a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
    </div>
	<!-- end of main -->
  </body>
</html>

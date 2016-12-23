<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.access.input.title" text="菜单管理"/></title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

	<div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">菜单管理</h4></div>
        <div class="panel-body">
              <form id="menuForm" method="post" action="menu-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				      <input id="menu_id" type="hidden" name="id" value="${model.id}">
				      <input id="menu_level" type="hidden" name="menuLevel" value="${model.menuLevel}">
				      <input id="menu_parentId" type="hidden" name="parentId" value="${parentId}">
				  </c:if>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="menu_name">菜单名称:</label>
					  <div class="col-lg-3">
				          <input id="menu_name" type="text" name="menuName" value="${model.menuName}"  class="form-control" >
				      </div>
				  </div>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="menu_url">URL:</label>
					  <div class="col-lg-3">
					      <textarea class="form-control" id="menu_url" name="menuUrl" rows="3">${model.menuUrl}</textarea>
					  </div>
				  </div>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="menu_order">菜单排序:</label>
					  <div class="col-lg-3">
					      <input id="menu_order" type="text" name="menuOrder" value="${model.menuOrder}"  class="form-control" >
					  </div>
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-isNull">是否桌面显示</label>
					  <div class="col-lg-3">
						  <select id="table-isNull" name="desktopIcon"  class="form-control">
						        <option ${model.desktopIcon == "2" ? 'selected' : ''}  value="2">否</option>
							    <option ${model.desktopIcon == "1" ? 'selected' : ''}  value="1">是</option>
						   </select>
					  </div>
				  </div>
				  <div class="form-group">
					  <label class="col-lg-2 control-label" for="menu_iconUrl">显示图标:</label>
					  <div class="col-lg-3">
					      <input id="menu_iconUrl" type="text" name="iconUrl" value="${model.iconUrl}"  class="form-control" >
					  </div>
				  </div>
				  <div class="form-group">
				    <div class="controls col-lg-10 col-lg-offset-2">
				      <button id="submitButton" class=" btn btn-primary btn-sm a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" onclick="history.back();" class="btn btn-primary btn-sm"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
        </div>
    </div>
    
	<!-- end of main -->
  </body>
</html>

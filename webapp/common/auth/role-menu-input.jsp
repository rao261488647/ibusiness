<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.role.perm.title" text="菜单设置权限"/></title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
	<script>
		function allChecked() {
			var checkObj = document.all("selectedItem");
			for (var i = 0; i < checkObj.length; i++) {
				if (checkObj[i].checked == true)
					checkObj[i].checked = false;
				else
					checkObj[i].checked = true;
			}
		}
	</script>
    <div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title">菜单设置权限</h4></div>
        <div class="panel-body">
       	 <form id="roleForm" method="post" action="role-menu-save.do" class="form-horizontal">
			  <input type="hidden" name="id" value="${id}">
				  <c:forEach items="${menus}" var="item">
					  <div class="form-group">
					        <input id="selectedItem-${item.id}" type="checkbox" name="selectedItem" value="${item.id}" <tags:contains items="${selectedItem}" item="${item.id}">checked</tags:contains>>
					        <label for="selectedItem-${item.id}" style="display:inline;"><a href="#${item.menuName}" data-toggle="collapse" > ${item.menuName}</a></label>
							<c:if test="${item.chiledItems != '[]'}">
							    <div class="controls" id="${item.menuName}"  >
								      <c:forEach items="${item.chiledItems}" var="levelTwo">
								     		 &nbsp;&nbsp;&nbsp;&nbsp;
								      		<input id="selectedItem-${levelTwo.id}" type="checkbox" name="selectedItem" value="${levelTwo.id}" <tags:contains items="${selectedItem}" item="${levelTwo.id}">checked</tags:contains>>
									        <label for="selectedItem-${levelTwo.id}" style="display:inline;"><a href="#${levelTwo.menuName}" data-toggle="collapse" >${levelTwo.menuName}</a></label>
											<c:if test="${levelTwo.chiledItems != '[]'}">
											        <div class="controls" id="${levelTwo.menuName}"  >
												        <c:forEach items="${levelTwo.chiledItems}" var="levelThree">
												        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												            <input id="selectedItem-${levelThree.id}" type="checkbox" name="selectedItem" value="${levelThree.id}" <tags:contains items="${selectedItem}" item="${levelThree.id}">checked</tags:contains>>
									                        <label for="selectedItem-${levelThree.id}" style="display:inline;">${levelThree.menuName}</label>
												        </c:forEach>
											        </div>
											</c:if>
								      </c:forEach>
							    </div>
						    </c:if>
					  </div>
				  </c:forEach>
				  <div class="form-group">
				    <div class="controls">
				      <input type="button" class="btn btn-default"  onClick="allChecked()" value="全选" />
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

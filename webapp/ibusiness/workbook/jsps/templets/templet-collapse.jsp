<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<c:forEach items="${menuItemsOfLevelTwo}" begin="0" end="0" var="findMenuOneId">
		<div class="panel-group" id="accordion${findMenuOneId.menuItemLevelOne.intMenuItemId}">
			<c:forEach items="${menuItemsOfLevelTwo}" var="menulItemTwo">
				<div class="panel panel-default">
					<c:choose>
						<c:when test="${menulItemTwo.chiledItems=='[]'}">
							<div class="panel-heading">
							    <h4 class="panel-title">
								      ${menulItemTwo.strMenuItemName}
							    </h4>
						    </div>
						</c:when>
						<c:when test="${menulItemTwo.chiledItems!='[]'}">
							<div class="panel-heading">
						        <h4 class="panel-title">
							        <a data-toggle="collapse" data-parent="#accordion${findMenuOneId.menuItemLevelOne.intMenuItemId}" href="#collapse${menulItemTwo.intMenuItemId}">
							        	${menulItemTwo.strMenuItemName}
							        </a>
						        </h4>
						    </div>
						    <div id="collapse${menulItemTwo.intMenuItemId}" class="panel-collapse collapse list-group">
						    	<c:forEach items="${menulItemTwo.chiledItems}" var="childItemTwo">
						    		<a href="${childItemTwo.strMenuItemAction}" class="list-group-item">${childItemTwo.strMenuItemName}</a>
						    	</c:forEach>
						    </div>
						</c:when>
						<c:otherwise>解析出错啦</c:otherwise>
				    </c:choose>
				</div>
			</c:forEach>
	  	</div>
	  	</c:forEach>
</body>
</html>
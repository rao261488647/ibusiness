<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<body>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th>项目序号</th>
						<th>项目名称</th>
						<th>项目连接</th>
						<th>项目图标</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menuItemsOfLevelOne}" var="menuItem">
						<tr id="${menuItem.intMenuItemId}" isLeaf="${menuItem.enumIsLeafItem}">
							<td class="dropdownButton"><a class="dropdown-button" href="#"><span
								class="glyphicon glyphicon-chevron-down"></span></a></td>
							<td class="addChildItem"><a><span class="glyphicon glyphicon-plus-sign"></span></a></td>
							<td class="itemId">${menuItem.intMenuItemId}</td>
							<td class="itemName">${menuItem.strMenuItemName}</td>
							<td class="itemAction">${menuItem.strMenuItemAction}</td>
							<td class="itemIcon"><span class="${menuItem.strMenuItemIcon}"></span></td>
							<td class="alterButton"><a href="#" class=""><span class="glyphicon glyphicon-pencil"></span></a></td>
							<td class="insertButton"><a href="#" class=""><span class="glyphicon glyphicon-ok"></span></a></td>
							<td class="deletButton"><a href="#" class=""><span class="glyphicon glyphicon-remove"></span></a></td>
						</tr>
						<c:if test="${menuItem.chiledItems!='[]'}">
							<c:forEach items="${menuItem.chiledItems}" var="childItem">
								<tr id="${childItem.intMenuItemId}" data-parent="${menuItem.intMenuItemId}" state="hide">
									<td></td>
									<td></td>
									<td class="itemId">${childItem.intMenuItemId}</td>
									<td class="itemName">${childItem.strMenuItemName}</td>
									<td class="itemAction">${childItem.strMenuItemAction}</td>
									<td class="itemIcon"><span class="${childItem.strMenuItemIcon}"></span></td>
									<td class="alterButton"><a href="#" class=""><span class="glyphicon glyphicon-pencil"></span></a></td>
									<td class="insertButton"><a href="#" class=""><span class="glyphicon glyphicon-ok"></span></a></td>
									<td class="deletButton"><a href="#" class=""><span class="glyphicon glyphicon-remove"></span></a></td>
								</tr>
							</c:forEach>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
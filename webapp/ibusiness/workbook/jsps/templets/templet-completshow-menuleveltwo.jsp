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
						<th>一级菜单项目名称</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${completeMenuItemsOfLevelTwo}" var="menuOfLevelTwoItem">
						<tr id="${menuOfLevelTwoItem.intMenuItemId}" isLeaf="${menuOfLevelTwoItem.enumIsLeafItem}">
							<td class="dropdownButton"><a class="dropdown-button" href="#"><span
								class="glyphicon glyphicon-chevron-down"></span></a></td>
							<td class="addChildItem"><a><span class="glyphicon glyphicon-plus-sign"></span></a></td>
							<td class="itemId">${menuOfLevelTwoItem.intMenuItemId}</td>
							<td class="itemName">${menuOfLevelTwoItem.strMenuItemName}</td>
							<td class="itemAction">${menuOfLevelTwoItem.strMenuItemAction}</td>
							<td class="itemIcon"><span class="${menuOfLevelTwoItem.strMenuItemIcon}"></span></td>
							<td class="itemNameOfLevelOne">${menuOfLevelTwoItem.menuItemLevelOne.strMenuItemName}</td>
							<td class="alterButton"><a href="#" class=""><span class="glyphicon glyphicon-pencil"></span></a></td>
							<td class="insertButton"><a href="#" class=""><span class="glyphicon glyphicon-ok"></span></a></td>
							<td class="deletButton"><a href="#" class=""><span class="glyphicon glyphicon-remove"></span></a></td>
						</tr>
						<c:if test="${menuOfLevelTwoItem.chiledItems!='[]'}">
							<c:forEach items="${menuOfLevelTwoItem.chiledItems}" var="menuOfLevelTwoChildItem">
								<tr id="${menuOfLevelTwoChildItem.intMenuItemId}" data-parent="${menuOfLevelTwoItem.intMenuItemId}" state="hide">
									<td></td>
									<td></td>
									<td class="itemId">${menuOfLevelTwoChildItem.intMenuItemId}</td>
									<td class="itemName">${menuOfLevelTwoChildItem.strMenuItemName}</td>
									<td class="itemAction">${menuOfLevelTwoChildItem.strMenuItemAction}</td>
									<td class="itemIcon"><span class="${menuOfLevelTwoChildItem.strMenuItemIcon}"></span></td>
									<td class="itemNameOfLevelOne">${menuOfLevelTwoChildItem.menuItemLevelOne.strMenuItemName}</td>
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
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>已办流程列表</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="row">
    <div class="col-lg-1"></div>
	<!-- start of main -->
    <div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">待办流程列表</h4></div>
        <div class="panel-body">
			  <table id="demoGrid" class="table table-hover table-striped">
			    <thead>
			      <tr>
			        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			        <th class="sorting" >流程标题</th>
			        <th class="sorting" >节点名称</th>
			        <th class="sorting" >创建时间</th>
			        <th class="sorting" >负责人</th>
			        <th class="sorting" >状态</th>
			        <th width="50">&nbsp;</th>
			        <th width="50">&nbsp;</th>
			      </tr>
			    </thead>
			
			    <tbody>
			      <c:forEach items="${tasks}" var="item">
			      <tr>
			        <td><input type="checkbox" class="selectedItem" name="selectedItem" value="${item.id}"></td>
				    <td>${item.flowTitle}</td>
				    <td>${item.name}</td>
				    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				    <td><tags:user userId="${item.assignee}"/></td>
				    <td>${item.suspended ? '挂起' : '激活'}</td>
			        <td>
			          <a href="${ctx}${item.flowUrl}">办理</a>
			        </td>
			        <td>
			          <a href="workspace-viewHistory.do?processInstanceId=${item.processInstanceId}">历史</a>
			        </td>
			      </tr>
			      </c:forEach>
			    </tbody>
			  </table>
        </div>
        </div>
	<!-- end of main -->
	</div>

  </body>

</html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "bpm-workspace");%>
<%pageContext.setAttribute("currentMenu", "bpm-task");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>流程列表</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
      <!-- start of main -->
      <div class="col-lg-1"></div>
      <div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">已办流程列表</h4></div>
        <div class="panel-body">
			  <table id="demoGrid" class="table table-hover table-striped">
			    <thead>
			      <tr>
			        <th class="sorting" >流程标题</th>
			        <th class="sorting" >节点名称</th>
			        <th class="sorting" >开始时间</th>
			        <th class="sorting" >结束时间</th>
			        <th class="sorting" >负责人</th>
			        <th class="sorting" >处理结果</th>
			        <th width="60">&nbsp;</th>
			      </tr>
			    </thead>
			
			    <tbody>
			      <c:forEach items="${historicTasks}" var="item">
			      <tr>
				    <td>${item.flowTitle}</td>
				    <td>${item.name}</td>
				    <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				    <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				    <td><tags:user userId="${item.assignee}"/></td>
				    <td>${item.deleteReason}</td>
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

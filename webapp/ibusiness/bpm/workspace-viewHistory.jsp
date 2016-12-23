<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "bpm-workspace");%>
<%pageContext.setAttribute("currentMenu", "bpm-process");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>流程历史信息列表</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="row">

	<!-- start of main -->
	<div class="col-lg-1"></div>
    <div class="panel panel-default col-lg-10">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">流程图</h4></div>
        <div class="panel-body">
	        <div id="demoSearch" class="content">
			    <img src="workspace-graphHistoryProcessInstance.do?processInstanceId=${param.processInstanceId}">
			</div>
		    <div class="content">
			  <table id="demoGrid" class="table table-hover table-striped">
			    <thead>
			      <tr>
			        <th class="sorting" >流程标题</th>
			        <th class="sorting" >名称</th>
			        <th class="sorting" >开始时间</th>
			        <th class="sorting" >结束时间</th>
			        <th class="sorting" >负责人</th>
			        <th class="sorting" >处理结果</th>
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
			      </tr>
			      </c:forEach>
			    </tbody>
			  </table>
            </div>
      </div>
<!-- 
      <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">表单</h4></div>
      <div class="panel-body">
		    <div class="content">
				  <table id="demoGrid" class="table table-hover table-striped">
				    <thead>
				      <tr>
				        <th class="sorting" >名称</th>
				        <th class="sorting" >值</th>
				      </tr>
				    </thead>
				
				    <tbody>
				      <c:forEach items="${historicVariableInstances}" var="item">
				      <tr>
					    <td>${item.variableName}</td>
					    <td>${item.value}</td>
				      </tr>
				      </c:forEach>
				    </tbody>
				  </table>
              </div>
      </div>
 -->
         <div class="col-lg-10 col-lg-offset-5">
		      <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
		  </div>
    </div>
	<!-- end of main -->
	</div>
  </body>
</html>

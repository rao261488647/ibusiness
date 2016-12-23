<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>流程节点配置列表</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>

	<div class="row">
	<%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">流程配置</h4></div>
        <div class="panel-body">
		<div class="content content-inner">
				<table class="table">
			      <thead>
			        <tr>
			          <th>编号</th>
			          <th>类型</th>
			          <th>节点</th>
			          <th>人员</th>
			          <th>事件</th>
			          <th>规则</th>
			          <th>表单</th>
			          <th>操作</th>
			          <th>提醒</th>
			        </tr>
			      </thead>
			      <tbody>
			        <c:forEach items="${bpmFlowNodes}" var="item">
			        <tr>
			          <td>${item.id}</td>
					  <td>${item.nodeType}</td>
			          <td>${item.nodeName}</td>
			          <td>
					    <c:if test="${item.confUser == 0}">
						  <a href="bpm-conf-user-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						<c:if test="${item.confUser == 1}">
						  <a href="bpm-conf-user-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						&nbsp;
				      </td>
			          <td>
					    <c:if test="${item.confListener == 0}">
						  <a href="bpm-conf-listener-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					    <c:if test="${item.confListener == 1}">
						  <a href="bpm-conf-listener-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						&nbsp;
				      </td>
			          <td>
					    <c:if test="${item.confRule == 0}">
						  <a href="bpm-conf-rule-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					    <c:if test="${item.confRule == 1}">
						  <a href="bpm-conf-rule-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						&nbsp;
				      </td>
			          <td>
					    <c:if test="${item.confForm == 0}">
						  <a href="bpm-conf-form-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					    <c:if test="${item.confForm == 1}">
						  <a href="bpm-conf-form-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						&nbsp;
				      </td>
			          <td>
					    <c:if test="${item.confOperation == 0}">
						  <a href="bpm-conf-operation-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					    <c:if test="${item.confOperation == 1}">
						  <a href="bpm-conf-operation-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						&nbsp;
				      </td>
			          <td>
					    <c:if test="${item.confNotice == 0}">
						  <a href="bpm-conf-notice-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					    <c:if test="${item.confNotice == 1}">
						  <a href="bpm-conf-notice-list.do?bpmConfNodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
						&nbsp;
				      </td>
			        </tr>
			        </c:forEach>
			      </tbody>
				  </tbody>
				</table>
		</div>
    </div>
    </div>
	<!-- end of main -->
	</div>

  </body>

</html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <div class="row">
	<%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>
	
	<!-- ================================================================================= -->
	<!-- ================================================================================= -->
	<script type="text/javascript">
		$(function() {
		    $("#bpmBaseForm").validate({
		        submitHandler: function(form) {
					bootbox.animate(false);
					var box = bootbox.dialog('<div class="progress" ><div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"> 60% </div></div>');
					form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		})
    </script>
	<!-- start of main -->
	<div class="panel panel-default col-md-10 " > 
		    <!-- tabs  -->
	        <ul class="nav nav-pills">
			  <li class="${tabType == 'bpmBase' ? 'active' : ''} "><a href="${ctx}/bpm-process/bpm-process-input.do?packageName=${packageName}&bpmId=${bpmId}" >流程基础信息</a></li>
			  <li class="${tabType == 'confNode' ? 'active' : ''}"><a href="${ctx}/bpm-process/bpm-conf-node-list.do?packageName=${packageName}&flowVersionId=${model.versionId}&bpmId=${bpmId}" >流程设置</a></li>
			</ul>
			<div id="tabContent" class="tab-content">
			    <!-- ========================== 流程基础信息 =================================================== -->
			    <div id=bpmBase class="tab-pane fade ${tabType == 'bpmBase' ? 'active in' : ''}">
			    <c:if test="${tabType == 'bpmBase'}">
			        <div class="panel-body">
						<div class="content content-inner">
								<form id="bpmBaseForm" method="post" action="bpm-process-save.do" class="form-horizontal">
								  <input id="user-base_userRepoId" type="hidden" name="userRepoId" value="1">
								  <c:if test="${model != null}">
								      <input id="code_id" type="hidden" name="id" value="${bpmId}">
								      <input id="code_packageName" type="hidden" name="packageName" value="${model.packageName}">
								  </c:if>
							      <div class="form-group">
									      <label class="col-lg-2 control-label" for="code-packageName">包名:</label>
									      <label class="control-label" id="code-packageName">${model.packageName}</label>
							       </div>
								   <div class="form-group">
									      <label class="col-lg-2 control-label" for="code-flowName">流程名:</label>
									      <c:if test="${model.flowName == null }">
										      <div class="col-lg-3">
											      <input id="code-flowName" type="text" name="flowName" value="${model.flowName}"  class="form-control" >
										      </div>
									      </c:if>
									      <c:if test="${model.flowName != null}">
									           <label class="control-label" >${model.flowName}</label>
									           <input id="code-flowName" type="hidden" name="flowName" value="${model.flowName}">
									      </c:if>
								   </div>
								   <div class="form-group">
									    <label class="col-lg-2 control-label" for="code-flowTitle">流程标题:</label>
										<div class="col-lg-3">
										  <input id="code-flowTitle" type="text" name="flowTitle" value="${model.flowTitle}"  class="form-control" >
									    </div>
								    </div>
								    <!-- 流程关联表单 -->
								    <div class="form-group">
									      <label class="col-lg-2 control-label" for="role_def">关联表单:</label>
									      <div class="col-lg-3">
											  <select id="role_def" name="formId" class="form-control required">
											        <option value="" >请选择</option>
												  <c:forEach items="${formList}" var="item">
												      <option value="${item.id}" ${item.id==model.formId ? 'selected' : ''}>${item.formTitle}</option>
												  </c:forEach>
											  </select>
										  </div>
									 </div>
								    <div class="form-group">
									    <label class="col-lg-2 control-label" for="code-flowForm">URL:</label>
										<div class="col-lg-3">
										    <textarea class="form-control" id="code-flowForm" name="flowUrl" rows="1">${model.flowUrl}</textarea>
									    </div>
									</div>
									<c:if test="${bpmId != null}">
									        <div class="form-group">
											    <label class="col-lg-2 control-label" >配置:</label>
												<div class="col-lg-3">
												    <!-- style="display:none" -->
												    <a class="btn btn-primary"  href="${ctx}/flowchart/init-flow-chart.do?packageName=${packageName}&bpmId=${bpmId}" >配置</a>
											    </div>
											</div>
									</c:if>
									<c:if test="${bpmId == null}">
											<div class="form-group">
											    <label class="col-lg-2 control-label" for="init-task-node">初始设置流程节点:<br/>(逗号,分割)<br/>例如: 发起,审批</label>
												<div class="col-lg-6">
												    <textarea class="form-control" id="init-task-node" name="initTaskNode" rows="2">${model.initTaskNode}</textarea>
											    </div>
											</div>
									</c:if>
									<c:if test="${bpmId != null}">
											<div class="form-group">
											    <label class="col-lg-2 control-label" >图形:</label>
												<div class="col-lg-3">
												    <!--  -->
												    <a class="btn btn-primary" target="_blank" href="${ctx}/bpm-process/bpm-process-graph.do?bpmProcessId=${model.id}"  >图形</a>
											    </div>
											</div>
									</c:if>
								  
								  <div class="col-lg-10 col-lg-offset-2">
								      <button id="submitButton" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
								      <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
								  </div>
								</form>
						</div>
                    </div>
                </c:if>
            </div>
            <!-- ==================== 流程设置 ===================================================== -->
        	<div id="flowChart" class="tab-pane fade ${tabType == 'confNode' ? 'active in' : ''}">
        	<c:if test="${tabType == 'confNode'}">
        	    <input  type="hidden" name="packageName" value="${packageName}">
        	    <input  type="hidden" name="bpmId" value="${bpmId}">
        	    
        	        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">流程配置</h4></div>
			        <div class="panel-body">
					<div class="content content-inner">
							<table class="table">
						      <thead>
						        <tr>
						        <!--   <th>编号</th>   -->
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
						        <!--   <td>${item.id}</td>  -->
								  <td>${item.nodeType}</td>
						          <td>${item.nodeName}</td>
						          <td>
								    <c:if test="${item.confUser == 0}">
									  <a href="#" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
									<c:if test="${item.confUser == 1}">
									  <a href="#" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
									&nbsp;
							      </td>
						          <td>
								    <c:if test="${item.confListener == 0}">
									  <a href="#" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
								    <c:if test="${item.confListener == 1}">
									  <a href="#" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
									&nbsp;
							      </td>
						          <td>
								    <c:if test="${item.confRule == 0}">
									  <a href="#" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
								    <c:if test="${item.confRule == 1}">
									  <a href="#" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
									&nbsp;
							      </td>
						          <td>
								    <c:if test="${item.confForm == 0}">
									  <a href="conf-node-colums.do?packageName=${packageName}&flowId=${bpmId}&nodeId=${item.id}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
								    <c:if test="${item.confForm == 1}">
									  <a href="conf-node-colums.do?packageName=${packageName}&flowId=${bpmId}&nodeId=${item.id}" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
									&nbsp;
							      </td>
						          <td>
								    <c:if test="${item.confOperation == 0}">
									  <a href="#" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
								    <c:if test="${item.confOperation == 1}">
									  <a href="#" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
									&nbsp;
							      </td>
						          <td>
								    <c:if test="${item.confNotice == 0}">
									  <a href="#" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
									</c:if>
								    <c:if test="${item.confNotice == 1}">
									  <a href="#" class="btn btn-primary btn-primary"><i class="glyphicon glyphicon-edit"></i></a>
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
        	        
        	</c:if>
        	</div>
	    </div>
    </div>
	<!-- end of main -->
	</div>
  </body>
</html>

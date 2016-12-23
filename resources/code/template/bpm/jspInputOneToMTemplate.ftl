<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>${confForm.formTitle}编辑</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		<#list subTab as sub>
        // 实例化config对象
        var ${sub.entityName?uncap_first}Config = {
		    id: '${sub.entityName?uncap_first}CodeGrid',
		    pageNo: ${"$" + "{${sub.entityName?uncap_first}Page.pageNo}"},
		    pageSize: ${"$" + "{${sub.entityName?uncap_first}Page.pageSize}"},
		    totalCount:${"$" + "{${sub.entityName?uncap_first}Page.totalCount}"},
		    resultSize: ${"$" + "{${sub.entityName?uncap_first}Page.resultSize}"},
		    pageCount: ${"$" + "{${sub.entityName?uncap_first}Page.pageCount}"},
		    orderBy: '${"$" + "{${sub.entityName?uncap_first}Page.orderBy == null ? "+ "''" +" : ${sub.entityName?uncap_first}Page.orderBy}"}',
		    asc: ${"$" + "{${sub.entityName?uncap_first}Page.asc}"},
		    params: {
		        'id': '${"$" + "{model.id}"}'
		    },
			selectedItemClass: '${sub.entityName?uncap_first}SelectedItem',
			gridFormId: '${sub.entityName?uncap_first}GridForm',
			exportUrl: '${sub.entityName?uncap_first}-export.do'
		};
        // 实例化table对象
		var ${sub.entityName?uncap_first}Table;
		$(function() {
			${sub.entityName?uncap_first}Table = new Table(${sub.entityName?uncap_first}Config);
		    ${sub.entityName?uncap_first}Table.configPagination('.${sub.entityName?uncap_first}M-pagination');
		    ${sub.entityName?uncap_first}Table.configPageInfo('.${sub.entityName?uncap_first}M-page-info');
		    ${sub.entityName?uncap_first}Table.configPageSize('.${sub.entityName?uncap_first}M-page-size');
		});
		</#list>
		
		// 提交方法--通过传入路径 提交到不同的controller
		function mainFormSubmit(path){
			${"$" + "('#mainForm').attr('action', path).submit()"};
		}
		
		// 表单验证JS
		$(function() {
		    $("#mainForm").validate({
		        submitHandler: function(form) {
		            if (typeof(bootbox) != 'undefined') {
					    bootbox.animate(false);
					    var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
		            }
		            form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		})
		
		<#if  confForm.isImportExport=1 >
		// 导入excel
		<#list subTab as sub>
		function import${sub.entityName?uncap_first}Excel(){
			 $("#${sub.entityName?uncap_first}excelForm").submit();
		}
		</#list>
	    </#if>
    </script>
  </head>
  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
	<div class="col-lg-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10"> 
	    <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">${confForm.formTitle}流程控制</h4></div>
	    <div class="panel-body">
	        <div class="pull-left">
	            <a href="#nextTaskUserDiv" role="button" class="btn btn-primary btn-sm" data-toggle="modal">办理</a>
			    <button class="btn btn-primary btn-sm a-submit" onclick="mainFormSubmit('${entityName?uncap_first}-save-draft.do')">草稿</button>
			    <button class="btn btn-primary btn-sm" onclick="location.href='${entityName?uncap_first}-rollback.do?executionId=${'$' + '{model.executionid}'}&flowId=${'$' + '{flowId}'}&flowType=0'">回退</button>
			    <button class="btn btn-primary btn-sm a-remove" onclick="location.href='${entityName?uncap_first}-list.do?flowId=${'$' + '{flowId}'}&flowType=0'">返回</button>
			</div>
	   </div>
	   
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">流程内容</h4></div>
        <div class="panel-body">
		  <div class="content content-inner">
		       <form id="mainForm" method="post" action="${entityName?uncap_first}-complete.do" class="form-horizontal">
				   <input type="hidden" name="flowId" value="${'$' + '{flowId}'}">
				   <c:if test="${'$' + '{model != null}'}">
				       <input type="hidden" name="id" value="${'$' + '{model.id}'}">
				       <input type="hidden" name="executionid" value="${'$' + '{model.executionid}'}">
				       <input type="hidden" name="createdatebpm" value="${'$' + '{model.createdatebpm}'}">
				       <input type="hidden" name="assigneeuser" value="${'$' + '{model.assigneeuser}'}">
				       <input type="hidden" name="nodename" value="${'$' + '{model.nodename}'}">
				       <input type="hidden" name="doneflag" value="${'$' + '{model.doneflag}'}">
				   </c:if>
				   <#list columns as po>
					   <#if po.fcDisplay="1">
				         <#if po_index%2==0>
                           <div class="form-group">
                         </#if>
						      <label class="control-label  col-lg-2" for="code-${po.fieldName}">${po.filedComment}:</label>
                              ${po.jspTagInfo}
	                                      
                          <#if po_index%2 ==1 || (po_index+1==columns?size)>
                            </div>
                          </#if>
						 
					   </#if>
					</#list>
					
					<!--  选择下一节点办理人弹出层  -->
				    <div id="nextTaskUserDiv" class="modal fade" tabindex="-1" style="top:20%;" >
				        <div class="modal-dialog">
						    <div class="modal-content">
							      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal"><span >&times;</span><span class="sr-only">Close</span></button>
								        <h4 class="modal-title">办理下一节点</h4>
							      </div>
							      <div class="modal-body">
									    <div class="form-group">
                                            <label class="col-lg-2 control-label" for="code-remark">办理人:</label>
                                            <div class="col-lg-4">
												  <select id="code-sex" name="userId" class="form-control">
												        <option value="" >请选择</option>
													  <c:forEach items="${'$' + '{userItems}'}" var="item">
													    <option value="${'$' + '{item.id}'}"  ${"$" + "{item.id == userId ? 'selected' : ''}"}>${'$' + '{item.displayName}'}</option>
													  </c:forEach>
												  </select>
										      </div>
						                </div>
						           </div>
						           <div class="modal-footer">
						                    <button class="btn btn-primary btn-sm a-insert">办理</button>
						                    <button class="btn btn-primary btn-sm" data-dismiss="modal" >关闭</button>
						           </div>
					            </div><!-- /.modal-content -->
						  </div><!-- /.modal-dialog -->
				    </div>
					
				</form>
			<#list columns as po>
			    <#if po.fcDisplay="1">
			        <#list po.jspFormList as jspForm>
			            ${jspForm}
			        </#list>
			    </#if>
			</#list>
		  </div>
        </div> 
        
        <#list subTab as sub>
        <!-- ==================== 子表 ========================================== -->
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">列表</h4></div>
	    <div class="panel-body">
			    <div class="pull-left">
			    <c:if test="${'$' + '{model != null && model.id != null && model.datetime != null}'}">
				    <button class="btn btn-primary btn-sm a-insert" onclick="location.href='${sub.entityName?uncap_first}-input.do?id=${'$' + '{model.id}'}&subId=&flowId=${'$' + '{flowId}'}'"><span class="glyphicon glyphicon-tasks"></span>新建</button>
				    <button class="btn btn-primary btn-sm a-remove" onclick="${sub.entityName?uncap_first}Table.removeAll()"><span class="glyphicon glyphicon-trash"></span>删除</button>
			    </c:if>
			    <#if  confForm.isExcelExport=1 >
				    <button class="btn btn-primary btn-sm" onclick="${sub.entityName?uncap_first}Table.exportExcel()">导出Excel</button>
				</#if>
			    <#if  confForm.isImportExport=1 >
				    <button class="btn btn-primary btn-sm"  onclick="importExcelAdd.click()">导入Excel</button>
				    <form id="${sub.entityName?uncap_first}excelForm" method="post" action="${sub.entityName?uncap_first}-importExcel.do?flowId=${'$' + '{flowId}'}&parentid=${'$' + '{model.id}'}" class="form-horizontal" enctype="multipart/form-data">
	                    <input id="importExcelAdd" type="file" name="attachment"  style="display:none;" onChange="import${sub.entityName?uncap_first}Excel()"> 
	                </form>
	            </#if>
	            
				</div>
				<div class="pull-right">
				  每页显示
				  <select class="${sub.entityName?uncap_first}M-page-size">
				    <option value="10">10</option>
				    <option value="20">20</option>
				    <option value="50">50</option>
				  </select>
				  条
				</div>
			    <div class="m-clear"></div>
		   </div>
		   <div class="content">
				<form id="${sub.entityName?uncap_first}GridForm" name="${sub.entityName?uncap_first}GridForm" method='post' action="${sub.entityName?uncap_first}-remove.do" class="m-form-blank">
				  <input type="hidden" name="flowId" value="${'$' + '{flowId}'}">
				  <table id="${sub.entityName?uncap_first}CodeGrid" class="table table-hover table-striped">
				      <thead>
					      <tr>
					          <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					          <th width="30">&nbsp;</th>
				          <#list sub.columns as subItem>
		                      <th class="sorting">${subItem.filedComment}</th>
		                  </#list>
					      </tr>
					    </thead>
						    <tbody>
						      <c:forEach items="${'$' + '{${sub.entityName?uncap_first}Page.result}'}" var="item" varStatus="status">
								  <tr class="${'$' + '{status.index%2==1? \'active\':\'\'}'}">
								        <td><input type="checkbox" class="${sub.entityName?uncap_first}SelectedItem a-check" name="${sub.entityName?uncap_first}SelectedItem" value="${'$' + '{item.id}'}"></td>
									    <td>
								            <a href="${sub.entityName?uncap_first}-input.do?id=${'$' + '{model.id}'}&subId=${'$' + '{item.id}'}&flowId=${'$' + '{flowId}'}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
								        </td>
							        <#list sub.columns as subItem>
							            <td>${'$' + '{item.${subItem.fieldName}}'}</td>
							        </#list>
								  </tr>
						      </c:forEach>
						    </tbody>
						  </table>
						</form>
		        </div>
			  <article>
			    <div class="${sub.entityName?uncap_first}M-page-info pull-left">
				  共100条记录 显示1到10条记录
				</div>
				<div class="btn-group ${sub.entityName?uncap_first}M-pagination pull-right">
				  <button class="btn btn-small">${"&lt"};</button>
				  <button class="btn btn-small">1</button>
				  <button class="btn btn-small">${"&gt"};</button>
				</div>
			    <div class="m-clear"></div>
		      </article>
		  </#list>
      </div>
	<!-- end of main -->
	</div>
  </body>
</html>

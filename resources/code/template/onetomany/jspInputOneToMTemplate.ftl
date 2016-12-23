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
	<div class="col-lg-1 col-md-1 col-sm-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10 col-md-10 col-sm-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">${confForm.formTitle}流程</h4></div>
        <div class="panel-body">
		<div class="content content-inner">
		       <form id="mainForm" method="post" action="${entityName?uncap_first}-save.do" class="form-horizontal">
		           <div class="controls">
					      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
					      <button type="button" onclick="location.href='${entityName?uncap_first}-list.do'" class="btn btn-primary btn-sm a-cancel"><span class="glyphicon glyphicon-log-out"></span>返回</button>
				   </div>
				   <input type="hidden" name="flowId" value="${'$' + '{flowId}'}">
				   <c:if test="${'$' + '{model != null}'}">
				       <input type="hidden" name="id" value="${'$' + '{model.id}'}">
				   </c:if>
				   
				   <#list columns as po>
				       <#if po.fcDisplay="1">
				          <#if po_index%2==0>
                             <div class="form-group">
                          </#if>
						      <label class="control-label col-lg-2 col-md-2 col-sm-2" for="code-${po.fieldName}">${po.filedComment}:</label>
                               ${po.jspTagInfo}
                               
	                    <#if po_index%2 ==1 || (po_index+1==columns?size)>
                            </div>
                        </#if>
							
					    </#if>
					</#list>
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
			    <c:if test="${'$' + '{model != null && model.id != null}'}">
			        <button class="btn btn-primary btn-sm a-insert" href="${sub.entityName?uncap_first}-input.do?id=${'$' + '{model.id}'}&subId=&flowId=${'$' + '{flowId}'}'" data-target="#${sub.entityName?uncap_first}ModalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>新建</button>
				    <button class="btn btn-primary btn-sm a-remove" onclick="${sub.entityName?uncap_first}Table.removeAll()"><span class="glyphicon glyphicon-trash"></span>删除</button>
			    </c:if>
			    <#if  confForm.isExcelExport=1 >
				    <button class="btn btn-primary btn-sm" onclick="${sub.entityName?uncap_first}Table.exportExcel()"><span class="glyphicon glyphicon-export"></span>导出Excel</button>
				</#if>
			    <#if  confForm.isImportExport=1 >
				    <button class="btn btn-primary btn-sm"  onclick="importExcelAdd.click()"><span class="glyphicon glyphicon-import"></span>导入Excel</button>
				    <form id="${sub.entityName?uncap_first}excelForm" method="post" action="${sub.entityName?uncap_first}-importExcel.do?parentid=${'$' + '{model.id}'}" class="form-horizontal" enctype="multipart/form-data">
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
				  <table id="${sub.entityName?uncap_first}CodeGrid" class="table table-hover table-striped">
				      <thead>
					      <tr>
					          <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					          <#list sub.columns as subItem>
			                      <th class="sorting">${subItem.filedComment}</th>
			                  </#list>
					          <th width="30">&nbsp;</th>
					      </tr>
					    </thead>
						    <tbody>
						      <c:forEach items="${'$' + '{${sub.entityName?uncap_first}Page.result}'}" var="item">
								  <tr>
								        <td><input type="checkbox" class="${sub.entityName?uncap_first}SelectedItem a-check" name="${sub.entityName?uncap_first}SelectedItem" value="${'$' + '{item.id}'}"></td>
									        <#list sub.columns as subItem>
									            <td>${'$' + '{item.${subItem.fieldName}}'}</td>
									        </#list>
								        <td>
								          <a href="${sub.entityName?uncap_first}-input.do?id=${'$' + '{model.id}'}&subId=${'$' + '{item.id}'}" class="a-update" data-target="#${sub.entityName?uncap_first}ModalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-pencil"></span></a>
								        </td>
								  </tr>
						      </c:forEach>
						    </tbody>
						  </table>
						</form>
		        </div>
		        
		      <!-- 模态框 -->
			  <div id="${sub.entityName?uncap_first}ModalInput" class="modal fade" tabindex="-1" style="display: none;" data-backdrop="static">
				  <div class="modal-dialog modal-lg">
					  <div class="modal-content" style="text-align: center;height: 600px">
					  </div>
				  </div>
			  </div>
			  <div class="panel-body">
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
			  </div>
		  </#list>
      </div>
	<!-- end of main -->
	</div>
  </body>
</html>

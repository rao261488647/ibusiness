<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑子表</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <script type="text/javascript">
        // 表单验证JS
		$(function() {
		    $("#subForm").validate({
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
    </script>
	<!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12">
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑子表</h4></div>
        <div class="panel-body">
			<div class="content content-inner">
					<form id="subForm" method="post" action="${entityName?uncap_first}-save.do?parentid=${'$' + '{parentid}'}&flowId=${'$' + '{flowId}'}" class="form-horizontal">
					  <c:if test="${'$' + '{model != null}'}">
					      <input id="code_id" type="hidden" name="id" value="${'$' + '{model.id}'}">
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
					  
					  <div class="form-group">
					    <div class="controls">
					      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
					      <button type="button" onclick="location.href='${mainEntityName?uncap_first}-input.do?id=${'$' + '{parentid}'}'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span>返回</button>
					    </div>
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
    </div>
	<!-- end of main -->
  </body>
</html>

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
    <%@include file="/ibusiness/header/header-portal.jsp"%>
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
	<div class="col-lg-1"></div>
	<!-- start of main -->
    <div class="panel panel-default col-lg-10">
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
                              <label class="control-label col-lg-2" for="code-${po.fieldName}">${po.filedComment}:</label>
                              ${po.jspTagInfo}
                              
                          <#if po_index%2 ==1 || (po_index+1==columns?size)>
                            </div>
                          </#if>    
	                          
	                       </#if>
					  </#list>
					  
					  <div class="form-group">
					    <div class="col-lg-10 col-lg-offset-2">
					      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><spring:message code='core.input.save' text='保存'/></button>
					      <button type="button" onclick="history.back();" class="btn btn-primary btn-sm a-cancel"><spring:message code='core.input.back' text='返回'/></button>
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

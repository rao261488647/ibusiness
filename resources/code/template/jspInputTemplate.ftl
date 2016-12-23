<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>${confForm.formTitle}编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <script type="text/javascript">
		$(function() {
		    $("#cgForm").validate({
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
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">${confForm.formTitle}编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="${entityName?uncap_first}-save.do" class="form-horizontal">
                  <c:if test="${'$' + '{model != null}'}">
                      <input id="code_id" type="hidden" name="id" value="${'$' + '{model.id}'}">
                  </c:if>
                  
                  <#list columns as po>
                      <#if po.fcDisplay="1">
                        <#if po_index%2==0>
                          <div class="form-group">
                        </#if>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-${po.fieldName}">${po.filedComment}:</label>
                          ${po.jspTagInfo}
                          
                      <#if po_index%2 ==1 || (po_index+1==columns?size)>
                            </div>
                          </#if>
                        </#if>
                  </#list>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='${entityName?uncap_first}-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
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
    <!-- end of main -->
  </body>
</html>

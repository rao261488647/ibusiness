<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>全景图表单编辑</title>
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
    <div class="col-lg-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">全景图表单编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="panorama-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                              <label class="col-lg-2 control-label" for="code-name">全景图名:</label>
                              <div class="col-lg-3">   <input id="code-name" type="text" name="name" value="${model.name}" class="text required" ></div>
                          </div>
                          <div class="form-group">
                              <label class="col-lg-2 control-label" for="code-panoramaaddress">全景图地址:</label>
                              <div class="col-lg-6">    <textarea class="form-control " id="code-panoramaaddress" name="panoramaaddress" rows="1" >${model.panoramaaddress}</textarea></div>
                          </div>
                            
                          <div class="form-group">
                              <label class="col-lg-2 control-label" for="code-panoramaurl">全景图URL地址:</label>
                              <div class="col-lg-6">    <textarea class="form-control required" id="code-panoramaurl" name="panoramaurl" rows="1" >${model.panoramaurl}</textarea></div>
                          </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='panorama-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

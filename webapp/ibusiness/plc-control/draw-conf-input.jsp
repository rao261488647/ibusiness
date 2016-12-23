<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>工艺图绘图设备对象编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
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
    <div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">工艺图绘图设备对象编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="drawConf-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 control-label" for="code-itemid">对象编号:</label>
                          <div class="col-lg-3">   <input id="code-itemid" type="text" name="itemid" value="${model.itemid}" class="text required" ></div>
                          
                          <label class="col-lg-2 control-label" for="code-itemname">对象名:</label>
                          <div class="col-lg-3">   <input id="code-itemname" type="text" name="itemname" value="${model.itemname}" class="text required" ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 control-label" for="code-itemtype">对象类型:</label>
                          <div class="col-lg-3">   <input id="code-itemtype" type="text" name="itemtype" value="${model.itemtype}" class="text required" ></div>
                          
                          <label class="col-lg-2 control-label" for="code-itemtitle">标题:</label>
                          <div class="col-lg-3">   <input id="code-itemtitle" type="text" name="itemtitle" value="${model.itemtitle}" class="text required" ></div>
                          
                            </div>
                          <div class="form-group">
					<label class="col-lg-2 control-label" for="code-imghurl">横版图片路径:</label>
					<div class="col-lg-3">
						<input id="code-imghurl" type="text" name="imghurl" value="${model.imghurl}" class="text required">
						<a href="#" class="btn btn-primary btn-sm">上传</a>
					</div>

					<label class="col-lg-2 control-label" for="code-imgvurl">竖版图片路径:</label>
					<div class="col-lg-3">
						<input id="code-imgvurl" type="text" name="imgvurl" value="${model.imgvurl}" class="text required">
					    <a href="#" class="btn btn-primary btn-sm">上传</a>
					</div>

				</div>
                          <div class="form-group">
                          <label class="col-lg-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-6">    <textarea class="form-control " id="code-remark" name="remark" rows="1" >${model.remark}</textarea></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><spring:message code='core.input.save' text='保存'/></button>
	                      <button type="button" onclick="history.back();" class="btn btn-primary btn-sm a-cancel"><spring:message code='core.input.back' text='返回'/></button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

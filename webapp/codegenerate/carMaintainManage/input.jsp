<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>维修厂管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">维修厂管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-maintainname">维修厂名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-maintainname" type="text" name="maintainname" value="${model.maintainname}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-type">类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-type" type="text" name="type" value="${model.type}" class="text form-control input-sm required"  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-address">地址:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3"><input id="code-address" type="text" name="address" value="${model.address}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-phone">电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-phone" type="text" name="phone" value="${model.phone}" class="text form-control input-sm required"  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-linkman">联系人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-linkman" type="text" name="linkman" value="${model.linkman}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-linkmanphone">联系人电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-linkmanphone" type="text" name="linkmanphone" value="${model.linkmanphone}" class="text form-control input-sm required"  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-maintainproject">维修项目:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-maintainproject" type="text" name="maintainproject" value="${model.maintainproject}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <textarea class="form-control" id="code-remark" name="remark" rows="2" >${model.remark}</textarea></div>
                          
                       </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
			            <!-- 上传组件form提交 --><form id="uploadurlUploadForm" method="post" action="uploadurl-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">     function uploadurlUpload() {       if( window.confirm("您确认要上传吗?") == true ){         var $form=$('#uploadurlUploadForm');         $form.ajaxSubmit({             url: $form.attr('action'),             type: 'POST',             dataType: 'text',             beforeSubmit: function () {},             success: function (data) {                 $('#code_table_uploadurl').attr('value',data);                 $('#code_table_uploadurl_text').attr('value',data);                 $('#code_img_uploadurl').attr('src','${ctx}/ibresources/ibimg/'+data);             },             error: function () {                 alert('请求数据出错了!');             }         });     }}  </script>    <input id="file_uploadurlupload" type="file" name="attachment" style="display:none;" onChange="uploadurlUpload();"> </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

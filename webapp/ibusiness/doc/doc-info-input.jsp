<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="msg-info.msg-info.input.title" text="编辑"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#msg-infoForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress" ><div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"> 60% </div></div>');
			form.submit();
        },
        errorClass: 'validate-error'
    });
})
    </script>
  </head>

  <body>
  <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="col-lg-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑文档</h4></div>
        <div class="panel-body">
				<form id="msg-infoForm" method="post" action="doc-info-save.do" class="form-horizontal" enctype="multipart/form-data">
				  <c:if test="${model != null}">
				  <input id="msg-info_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <div class="form-group">
					    <label class="col-lg-2 control-label" for="code-docType">文件类型:</label>
						<div class="col-lg-3">
							<select id="code-docType" name="docType" class="form-control input-sm required">
								<option value="">请选择</option>
								<c:forEach items="${nameItems}" var="item">
									<option value="${item.key}" ${item.key==model.docType? 'selected':''}>${item.value}</option>
								</c:forEach>
							</select>
						</div>
						
						<label class="col-lg-2 control-label" for="code-docName">文件名称:</label>
                        <div class="col-lg-3">   <input id="code-docName" type="text" name="docName" value="${model.docName}" class="text form-control input-sm required"  ></div>
					</div>
					<div class="form-group">
					<!-- 
					    <label class="col-lg-2 control-label" for="msg-info_address">上传文件:</label>
						<div class="col-lg-3">
						  <input id="msg-info_address" type="file" name="attachment" value="${model.name}" size="40" class="text required">
					    </div>
				    -->
					    <label class="col-lg-2 control-label" for="code-name">上传文件:</label>
						<div class="col-lg-1">
							  <a href="#" class="btn btn-primary btn-sm" onclick="$('#file_nameupload').click()">
							  <span class="glyphicon glyphicon-upload"></span>上传</a>
							   <input id="code_doc_name" type="hidden" name="name" value="${model.name}">
					    </div>
					    <div class="col-lg-6">
							   <input class="text form-control input-sm" id="code_doc_name_text" type="text" value="${model.name}">
						</div>
					    
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="docInfo_descn">备注:</label>
					<div class="col-lg-8">
						<textarea class="form-control required" id="docInfo_descn" name="descn" rows="2">${model.descn}</textarea>
					</div>
				  </div>
				  <div class="form-group">
				    <div class="controls">
				      <button type="submit" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" class="btn btn-primary a-cancel" onclick="history.back();"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
				
				<!-- 上传组件form提交 -->
				<form id="nameUploadForm" method="post" action="docinfo-upload.do" class="form-horizontal" enctype="multipart/form-data">
					<script type="text/javascript">
						function nameUpload() {
							if( window.confirm("您确认要上传吗?") == true ){
								var $form=$('#nameUploadForm');
								$form.ajaxSubmit({
									url: $form.attr('action'),
									type: 'POST',
									dataType: 'text',
									beforeSubmit: function () {},
									success: function (data) {
										$('#code_doc_name').attr('value',data);
										$('#code_doc_name_text').attr('value',data);
										},
										error: function () {
											alert('请求数据出错了!');
										}
									});
							}
						}
					</script>
					<input id="file_nameupload" type="file" name="attachment" style="display: none;" onChange="nameUpload();">
				</form>
        </div>

	  <!-- end of main -->
	</div>

  </body>

</html>

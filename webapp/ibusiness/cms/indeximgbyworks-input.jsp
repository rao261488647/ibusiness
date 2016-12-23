<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<%@include file="/common/meta.jsp"%>
<title>作品展示页面编辑</title>
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
		<div class="panel-heading">
			<h4 class="panel-title glyphicon glyphicon-paperclip">作品展示页面编辑</h4>
		</div>
		<div class="panel-body">
			<form id="cgForm" method="post" action="indeximgbyworks-save.do" class="form-horizontal">
				<c:if test="${model != null}">
					<input id="code_id" type="hidden" name="id" value="${model.id}">
				</c:if>
                <div class="form-group">
					<label class="col-lg-2 control-label" for="code-imginfo">图片信息描述:</label>
					<div class="col-lg-8">
						<textarea class="form-control " id="code-imginfo" name="imginfo" rows="2">${model.imginfo}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">图片1:</label>
					<div class="col-lg-4">
					    <img id="code_img_name" height="150" width="200" src="${ctx}/ibresources/imgworks/${model.imgurl}" />
						<a href="#" class="btn btn-primary btn-sm" onclick="$('#file_imgurlupload').click()"><span
							class="glyphicon glyphicon-upload"></span>上传</a>
					    <input id="code_table_imgurl" type="hidden" name="imgurl" value="${model.imgurl}">
					</div>
					<!-- 图片2 -->
					<label class="col-lg-2 control-label">图片2:</label>
					<div class="col-lg-4">
					    <img id="code_img_name2" height="150" width="200" src="${ctx}/ibresources/imgworks/${model.imgurl2}" />
						<a href="#" class="btn btn-primary btn-sm" onclick="$('#file_imgurlupload2').click()"><span
							class="glyphicon glyphicon-upload"></span>上传</a>
					    <input id="code_table_imgurl2" type="hidden" name="imgurl2" value="${model.imgurl2}">
					</div>
				</div>
				<div class="form-group">
					<!-- 图片3 -->
					<label class="col-lg-2 control-label">图片3:</label>
					<div class="col-lg-4">
					    <img id="code_img_name3" height="150" width="200" src="${ctx}/ibresources/imgworks/${model.imgurl3}" />
						<a href="#" class="btn btn-primary btn-sm" onclick="$('#file_imgurlupload3').click()"><span
							class="glyphicon glyphicon-upload"></span>上传</a>
					    <input id="code_table_imgurl3" type="hidden" name="imgurl3" value="${model.imgurl3}">
					</div>
					<!-- 图片4 -->
					<label class="col-lg-2 control-label">图片4:</label>
					<div class="col-lg-4">
					    <img id="code_img_name4" height="150" width="200" src="${ctx}/ibresources/imgworks/${model.imgurl4}" />
						<a href="#" class="btn btn-primary btn-sm" onclick="$('#file_imgurlupload4').click()"><span
							class="glyphicon glyphicon-upload"></span>上传</a>
					    <input id="code_table_imgurl4" type="hidden" name="imgurl4" value="${model.imgurl4}">
					</div>
				</div>
				<div class="form-group">
					<!-- 图片5 -->
					<label class="col-lg-2 control-label">图片5:</label>
					<div class="col-lg-4">
					    <img id="code_img_name5" height="150" width="200" src="${ctx}/ibresources/imgworks/${model.imgurl5}" />
						<a href="#" class="btn btn-primary btn-sm" onclick="$('#file_imgurlupload5').click()"><span
							class="glyphicon glyphicon-upload"></span>上传</a>
					    <input id="code_table_imgurl5" type="hidden" name="imgurl5" value="${model.imgurl5}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label" for="code-showflag">是否显示标记:</label>
					<div class="col-lg-3">
						<select id="code-showflag" name="showflag"
							class="form-control input-sm required">
							<option value="">请选择</option>
							<c:forEach items="${showflagItems}" var="item">
								<option value="${item.key}"
									${item.key==model.showflag? 'selected':''}>${item.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button id="submitButton" class="btn btn-primary btn-sm a-submit">
							<span class="glyphicon glyphicon-floppy-save"></span>保存
						</button>
						<button type="button"
							onclick="location.href='indeximgbyworks-list.do'"
							class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-log-out"></span> 关闭
						</button>
					</div>
				</div>
			</form>
			
			<script type="text/javascript">
				function imgurlUpload(str) {
					if( window.confirm("您确认要上传吗?") == true ){
						var $form=$('#imgurlUploadForm'+str);
						$form.ajaxSubmit({
							url: $form.attr('action'),
							type: 'POST',
							dataType: 'text',
							beforeSubmit: function () {},
							success: function (data) {
								$('#code_table_imgurl'+str).attr('value',data);
								$('#code_table_imgurl_text'+str).attr('value',data);
								$('#code_img_name'+str).attr('src','${ctx}/ibresources/imgworks/'+data);
							},
							error: function () {
								alert('请求数据出错了!');
							}
						});
					}
				}
			</script>
			<!-- 上传组件form提交 -->
			<form id="imgurlUploadForm" method="post" action="imgurl-upload.do" class="form-horizontal" enctype="multipart/form-data">
				<input id="file_imgurlupload" type="file" name="attachment" style="display: none;" onChange="imgurlUpload('');">
			</form>
			<form id="imgurlUploadForm2" method="post" action="imgurl-upload.do" class="form-horizontal" enctype="multipart/form-data">
				<input id="file_imgurlupload2" type="file" name="attachment" style="display: none;" onChange="imgurlUpload('2');">
			</form>
			<form id="imgurlUploadForm3" method="post" action="imgurl-upload.do" class="form-horizontal" enctype="multipart/form-data">
				<input id="file_imgurlupload3" type="file" name="attachment" style="display: none;" onChange="imgurlUpload('3');">
			</form>
			<form id="imgurlUploadForm4" method="post" action="imgurl-upload.do" class="form-horizontal" enctype="multipart/form-data">
				<input id="file_imgurlupload4" type="file" name="attachment" style="display: none;" onChange="imgurlUpload('4');">
			</form>
			<form id="imgurlUploadForm5" method="post" action="imgurl-upload.do" class="form-horizontal" enctype="multipart/form-data">
				<input id="file_imgurlupload5" type="file" name="attachment" style="display: none;" onChange="imgurlUpload('5');">
			</form>
		</div>
	</div>
	<!-- end of main -->
</body>
</html>

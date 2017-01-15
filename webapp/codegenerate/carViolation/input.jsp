<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>车辆违章管理编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>

    <script type="text/javascript">
		$(function() {
			$.validator.addMethod('disposedateCheck', function(value, element) {
				var statusValue = $('select[name=isdispose]').val();
				if (statusValue == '已处理') {
					if ($("#code-disposedate").val()== null || $("#code-disposedate").val()== "") {
						return false;
					} else {
						return true;
					}
				} else {
					return true;
				}
			}, '处理日期必须填写');
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
		    $('select').comboSelect();
		})
    </script>
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">车辆违章管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carid">车牌号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <select m='search' id="code-carid" name="carid" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${caridItems}" var="item">          <option value="${item.key}" ${item.key==model.carid? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-violationdate">违章日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <div class="input-append datepicker date">  <span class="add-on">    <input id="code-violationdate" type="text" name="violationdate" value="${model.violationdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-violationtype">违章类别:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-violationtype" name="violationtype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${violationtypeItems}" var="item">          <option value="${item.key}" ${item.key==model.violationtype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-violationproject">违章项目:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-violationproject" type="text" name="violationproject" value="${model.violationproject}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-violationaddress">违章地址:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-violationaddress" type="text" name="violationaddress" value="${model.violationaddress}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-points">扣分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-points" type="text" name="points" value="${model.points}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-finemoney">罚款金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-finemoney" type="text" name="finemoney" value="${model.finemoney}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-isdispose">是否已处理:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <select id="code-isdispose" name="isdispose" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${isdisposeItems}" var="item">          <option value="${item.key}" ${item.key==model.isdispose? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-disposedate">处理日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <div class="input-append datepicker date">  <span class="add-on">    <input id="code-disposedate" type="text" name="disposedate" value="${model.disposedate}" placeholder="点击选择" class="form-control input-sm disposedateCheck" readonly >  </span>  </div></div>
                          
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

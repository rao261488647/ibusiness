<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>维修保养录入管理编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <script type="text/javascript">
		$(function() {
			$.validator.addMethod('maintainstatusCheck', function(value, element) {
				var statusValue = $('select[name=enterfactorytype]').val();
				if (statusValue != '保养') {
					var statusValue1 = $('select[name=maintainstatus]').val();
					if (statusValue1== null || statusValue1== "") {
						return false;
					} else {
						return true;
					}
				} else {
					return true;
				}
			}, '维修状态填写');
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">维修保养录入管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-typename">车牌号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <select id="code-carid" name="carid" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${caridItems}" var="item">          <option value="${item.key}" ${item.key==model.carid? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-enterfactorydate">进厂日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <div class="input-append datepicker date">  <span class="add-on">    <input id="code-enterfactorydate" type="text" name="enterfactorydate" value="${model.enterfactorydate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-enterfactorytype">进厂类别:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-enterfactorytype" name="enterfactorytype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${enterfactorytypeItems}" var="item">          <option value="${item.key}" ${item.key==model.enterfactorytype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-maintainid">维修厂:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-maintainid" name="maintainid" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${maintainidItems}" var="item">          <option value="${item.key}" ${item.key==model.maintainid? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-maintaincontent">维修内容:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <input id="code-maintaincontent" type="text" name="maintaincontent" value="${model.maintaincontent}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-isreplacecar">替换车:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-isreplacecar" name="isreplacecar" class="form-control input-sm" >          <option value="" >请选择</option>        <c:forEach items="${isreplacecarItems}" var="item">          <option value="${item.key}" ${item.key==model.isreplacecar? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-leavefactorydate">出厂日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <div class="input-append datepicker date">  <span class="add-on">    <input id="code-leavefactorydate" type="text" name="leavefactorydate" value="${model.leavefactorydate}" placeholder="点击选择" class="form-control input-sm" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <textarea class="form-control" id="code-remark" name="remark" rows="2" >${model.remark}</textarea></div>
                          
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-maintainstatus">维修状态:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   
                              <select id="code-maintainstatus" name="maintainstatus" class="form-control input-sm maintainstatusCheck" >
                                      <option value="" >请选择</option>
                                      <option value="维修中" ${'维修中'==model.maintainstatus? 'selected':''} >维修中</option>
                                      <option value="正常" ${'正常'==model.maintainstatus? 'selected':''} >正常</option>
                              </select>
                          </div>
                          
                          
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

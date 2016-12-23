<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>车型管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">车型管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="car_type_info-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-typename">车型名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-typename" type="text" name="typename" value="${model.typename}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-powertype">动力类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-powertype" name="powertype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${powertypeItems}" var="item">          <option value="${item.key}" ${item.key==model.powertype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cartype">车辆类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-cartype" name="cartype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${cartypeItems}" var="item">          <option value="${item.key}" ${item.key==model.cartype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carriagecount">车辆箱数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-carriagecount" name="carriagecount" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${carriagecountItems}" var="item">          <option value="${item.key}" ${item.key==model.carriagecount? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-gearbox">变速箱:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-gearbox" name="gearbox" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${gearboxItems}" var="item">          <option value="${item.key}" ${item.key==model.gearbox? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-seatcount">座位数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-seatcount" name="seatcount" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${seatcountItems}" var="item">          <option value="${item.key}" ${item.key==model.seatcount? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-swept">排量:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-swept" type="text" name="swept" value="${model.swept}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-displayment">发动机功率:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-displayment" type="text" name="displayment" value="${model.displayment}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-listyear">上市年份:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-listyear" type="text" name="listyear" value="${model.listyear}" class="text form-control input-sm "  ></div>
                          
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-uploadurl">图片URL:</label>
                          <img class="col-lg-4 col-md-4 col-sm-4 " id="code_img_uploadurl" height="150" width="200" src="${ctx}/ibresources/ibimg/${model.uploadurl}" />
                          <div class="col-lg-2 col-md-2 col-sm-2 ">  <a href="#" class="btn btn-primary btn-sm" onclick="$('#file_uploadurlupload').click()"><span class="glyphicon glyphicon-upload"></span>上传</a>  <input id="code_table_uploadurl" type="hidden" name="uploadurl" value="${model.uploadurl}"></div>
                       </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='car_type_info-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
			            <!-- 上传组件form提交 --><form id="uploadurlUploadForm" method="post" action="uploadurl-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">     function uploadurlUpload() {       if( window.confirm("您确认要上传吗?") == true ){         var $form=$('#uploadurlUploadForm');         $form.ajaxSubmit({             url: $form.attr('action'),             type: 'POST',             dataType: 'text',             beforeSubmit: function () {},             success: function (data) {                 $('#code_table_uploadurl').attr('value',data);                 $('#code_table_uploadurl_text').attr('value',data);                 $('#code_img_uploadurl').attr('src','${ctx}/ibresources/ibimg/'+data);             },             error: function () {                 alert('请求数据出错了!');             }         });     }}  </script>    <input id="file_uploadurlupload" type="file" name="attachment" style="display:none;" onChange="uploadurlUpload();"> </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

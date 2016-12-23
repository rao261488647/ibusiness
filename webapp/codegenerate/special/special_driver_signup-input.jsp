<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>专车司机报名编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">专车司机报名编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="special_driver_signup-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                      <input id="code_inputtype" type="hidden" name="inputtype" value="${model.inputtype}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-name">姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cell">电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-cell" type="text" name="cell" value="${model.cell}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-submitdate">提交日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-submitdate" type="text" name="submitdate" value="${model.submitdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ordertype">预约类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-ordertype" name="ordertype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${ordertypeItems}" var="item">          <option value="${item.key}" ${item.key==model.ordertype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cartype">车辆型号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-cartype" name="cartype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${cartypeItems}" var="item">          <option value="${item.key}" ${item.key==model.cartype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-selfcaruseyear">自有车已使用年限:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-selfcaruseyear" type="text" name="selfcaruseyear" value="${model.selfcaruseyear}" class="text form-control input-sm "  ></div>
                          
                            </div>
                <!--
				<div class="form-group">
					<label class="col-lg-2 col-md-2 col-sm-2 control-label"
						for="code-carphote">车辆图片:</label> <img id="code_img_carphote"
						height="150" width="300"
						src="${ctx}/ibresources/ibimg/${model.carphote}" />
					<div class="col-lg-4 col-md-4 col-sm-4 ">
						<a href="#" class="btn btn-primary btn-sm"
							onclick="$('#file_carphoteupload').click()"><span
							class="glyphicon glyphicon-upload"></span>上传</a> <input
							id="code_table_carphote" type="hidden" name="carphote"
							value="${model.carphote}">
					</div>
				</div>
				-->
				
				<div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-platformto">想加入的平台:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-platformto" type="text" name="platformto" value="${model.platformto}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-precontractdate">欲签合同期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-precontractdate" type="text" name="precontractdate" value="${model.precontractdate}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-preregisterdate">期望注册日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-preregisterdate" type="text" name="preregisterdate" value="${model.preregisterdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-preregistertime">期望注册的时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-preregistertime" name="preregistertime" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${preregistertimeItems}" var="item">          <option value="${item.key}" ${item.key==model.preregistertime? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealperson">处理人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-dealperson" type="text" name="dealperson" value="${model.dealperson}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealtime">处理时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-dealtime" type="text" name="dealtime" value="${model.dealtime}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealresult">处理结果:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-dealresult" type="text" name="dealresult" value="${model.dealresult}" class="text form-control input-sm "  ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='special_driver_signup-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
			            <!-- 上传组件form提交 --><form id="carphoteUploadForm" method="post" action="carphote-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">     function carphoteUpload() {       if( window.confirm("您确认要上传吗?") == true ){         var $form=$('#carphoteUploadForm');         $form.ajaxSubmit({             url: $form.attr('action'),             type: 'POST',             dataType: 'text',             beforeSubmit: function () {},             success: function (data) {                 $('#code_table_carphote').attr('value',data);                 $('#code_table_carphote_text').attr('value',data);                 $('#code_img_carphote').attr('src','${ctx}/ibresources/ibimg/'+data);             },             error: function () {                 alert('请求数据出错了!');             }         });     }}  </script>    <input id="file_carphoteupload" type="file" name="attachment" style="display:none;" onChange="carphoteUpload();"> </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

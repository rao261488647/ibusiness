<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>车辆库存管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">车辆库存管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="car_mgr-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-intype">入库类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-intype" name="intype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${intypeItems}" var="item">          <option value="${item.key}" ${item.key==model.intype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-typename">型号名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-typename" name="typename" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${typenameItems}" var="item">          <option value="${item.key}" ${item.key==model.typename? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carframenum">车架号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carframenum" type="text" name="carframenum" value="${model.carframenum}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-caroutfaydate">车辆出厂日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-caroutfaydate" type="text" name="caroutfaydate" value="${model.caroutfaydate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-signdate">上牌日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-signdate" type="text" name="signdate" value="${model.signdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-savefactory">存放仓库:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-savefactory" name="savefactory" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${savefactoryItems}" var="item">          <option value="${item.key}" ${item.key==model.savefactory? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          <!-- 
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cartype">车辆类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-cartype" name="cartype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${cartypeItems}" var="item">          <option value="${item.key}" ${item.key==model.cartype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                           -->
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-targetcompany">指标公司:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-targetcompany" name="targetcompany" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${targetcompanyItems}" var="item">          <option value="${item.key}" ${item.key==model.targetcompany? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carcolor">车辆颜色:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-carcolor" name="carcolor" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${carcolorItems}" var="item">          <option value="${item.key}" ${item.key==model.carcolor? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-buyfex">购置税:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-buyfex" type="text" name="buyfex" value="${model.buyfex}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-signnumfee">上牌费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-signnumfee" type="text" name="signnumfee" value="${model.signnumfee}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-firstsecurefee">初次保险费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-firstsecurefee" type="text" name="firstsecurefee" value="${model.firstsecurefee}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-firstsecuredate">初次保险到期日:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-firstsecuredate" type="text" name="firstsecuredate" value="${model.firstsecuredate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-registereid">登记证号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-registereid" type="text" name="registereid" value="${model.registereid}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-yearcheckdate">年审到期日:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-yearcheckdate" type="text" name="yearcheckdate" value="${model.yearcheckdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-infactorydate">入库日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">
                              <!-- 
                              <input id="code-registereid" type="text" name="infactorydate" value="${model.infactorydate}" class="text form-control input-sm " readonly >
                               -->
                              <div class="input-append datepicker date"><span class="add-on"><input id="code-infactorydate" type="text" name="infactorydate" value="${model.infactorydate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div>
                              
                          </div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carstatus">车辆状态:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">
                              <input id="code-registereid" type="text" name="carstatus" value="${model.carstatus}" pattern="yyyy-MM-dd" class="text form-control input-sm " readonly >
			                  <!-- 
			                  <select id="code-intype" name="carstatus" class="form-control input-sm required" >
                                      <option value="" >请选择</option>
                                      <option value="已入库" ${'已入库'==model.carstatus? 'selected':''} >已入库</option>
                                      <option value="已出库" ${'已出库'==model.carstatus? 'selected':''} >已出库</option>
                              </select>
                                -->
                           </div>
                        </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control " id="code-remark" name="remark" rows="2" >${model.remark}</textarea></div>
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-uploadprcture">上传图片:</label>
                          <img class="col-lg-4 col-md-4 col-sm-4 " id="code_img_uploadprcture" height="150" width="200" src="${ctx}/ibresources/ibimg/${model.uploadprcture}" />
                          <div class="col-lg-1 col-md-1 col-sm-1 ">  <a href="#" class="btn btn-primary btn-sm" onclick="$('#file_uploadprctureupload').click()"><span class="glyphicon glyphicon-upload"></span>上传</a>  <input id="code_table_uploadprcture" type="hidden" name="uploadprcture" value="${model.uploadprcture}"></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='car_mgr-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
			            <!-- 上传组件form提交 --><form id="uploadprctureUploadForm" method="post" action="uploadprcture-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">     function uploadprctureUpload() {       if( window.confirm("您确认要上传吗?") == true ){         var $form=$('#uploadprctureUploadForm');         $form.ajaxSubmit({             url: $form.attr('action'),             type: 'POST',             dataType: 'text',             beforeSubmit: function () {},             success: function (data) {                 $('#code_table_uploadprcture').attr('value',data);                 $('#code_table_uploadprcture_text').attr('value',data);                 $('#code_img_uploadprcture').attr('src','${ctx}/ibresources/ibimg/'+data);             },             error: function () {                 alert('请求数据出错了!');             }         });     }}  </script>    <input id="file_uploadprctureupload" type="file" name="attachment" style="display:none;" onChange="uploadprctureUpload();"> </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>指标公司管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">指标公司管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="target_compant_mgr-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-companyname">公司名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-companyname" type="text" name="companyname" value="${model.companyname}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-companyexecutor">公司法人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-companyexecutor" type="text" name="companyexecutor" value="${model.companyexecutor}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-executorcell">法人联系方式:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-executorcell" type="text" name="executorcell" value="${model.executorcell}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-lottarytarget">摇号指标数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-lottarytarget" type="text" name="lottarytarget" value="${model.lottarytarget}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-updatetargetcount">更新指标数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-updatetargetcount" type="text" name="updatetargetcount" value="${model.updatetargetcount}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-othertargetcount">其他指标数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-othertargetcount" type="text" name="othertargetcount" value="${model.othertargetcount}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-registerdate">注册日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-registerdate" type="text" name="registerdate" value="${model.registerdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-isreturn">是否转让:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-isreturn" name="isreturn" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${isreturnItems}" var="item">          <option value="${item.key}" ${item.key==model.isreturn? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control" id="code-remark" name="remark" rows="2" >${model.remark}</textarea></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='target_compant_mgr-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

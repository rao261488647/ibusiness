<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>专车使用车型管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">专车使用车型管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="specialcar_use_type_mgr-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cartype">车型名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-cartype" name="cartype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${cartypeItems}" var="item">          <option value="${item.key}" ${item.key==model.cartype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-neworold">新旧程度:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-neworold" name="neworold" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${neworoldItems}" var="item">          <option value="${item.key}" ${item.key==model.neworold? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contrforthreemth">3个月合同月租金:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-contrforthreemth" type="text" name="contrforthreemth" value="${model.contrforthreemth}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contrforsixmth">6个月合同月租金:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-contrforsixmth" type="text" name="contrforsixmth" value="${model.contrforsixmth}" class="text  form-control input-sm number required" ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contrforsoneyear">1年合同月租金:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-contrforsoneyear" type="text" name="contrforsoneyear" value="${model.contrforsoneyear}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contrforstwoyear">2年合同月租金:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-contrforstwoyear" type="text" name="contrforstwoyear" value="${model.contrforstwoyear}" class="text  form-control input-sm number required" ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cardeposit">汽车押金:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-cardeposit" type="text" name="cardeposit" value="${model.cardeposit}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-illegaldeposit">违章押金:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-illegaldeposit" type="text" name="illegaldeposit" value="${model.illegaldeposit}" class="text  form-control input-sm number required" ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-status">状态:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-status" name="status" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${statusItems}" var="item">          <option value="${item.key}" ${item.key==model.status? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-plat">专车使用平台:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-plat" type="text" name="plat" value="${model.plat}" class="text form-control input-sm required"  ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='specialcar_use_type_mgr-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

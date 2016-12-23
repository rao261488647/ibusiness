<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>电卡管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">电卡管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="electricitycard-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-electriccardno">电卡卡号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-electriccardno" type="text" name="electriccardno" value="${model.electriccardno}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">领用日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="eventdate" value="${model.eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-initialamount">初始金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-initialamount" type="text" name="initialamount" value="${model.initialamount}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-chargeamount">充值金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-chargeamount" type="text" name="chargeamount" value="${model.chargeamount}" class="text  form-control input-sm number " ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-chargedate">充值日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-chargedate" type="text" name="chargedate" value="${model.chargedate}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-refundamount">退款金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-refundamount" type="text" name="refundamount" value="${model.refundamount}" class="text  form-control input-sm number " ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-refunddate">退款日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-refunddate" type="text" name="refunddate" value="${model.refunddate}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-electricstatus">电卡状态:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-electricstatus" name="electricstatus" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${electricstatusItems}" var="item">          <option value="${item.key}" ${item.key==model.electricstatus? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivername">使用人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-drivername" type="text" name="drivername" value="${model.drivername}" class="text form-control input-sm required"  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cardbalance">卡面余额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-cardbalance" type="text" name="cardbalance" value="${model.cardbalance}" class="text  form-control input-sm number " ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='electricitycard-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

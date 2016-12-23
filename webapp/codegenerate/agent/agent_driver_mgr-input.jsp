<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>代驾司机列表管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">代驾司机列表管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="agent_driver_mgr-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-num">司机编号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-num" type="text" name="num" value="${model.num}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-name">司机姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm required"  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-workcell">司机工作手机:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-workcell" type="text" name="workcell" value="${model.workcell}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cell">司机手机:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-cell" type="text" name="cell" value="${model.cell}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eid">司机身份证号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-eid" type="text" name="eid" value="${model.eid}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-sex">性别:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-sex" name="sex" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${sexItems}" var="item">          <option value="${item.key}" ${item.key==model.sex? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-address">地址:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control required" id="code-address" name="address" rows="2" >${model.address}</textarea></div>
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-publiahgrd">发证机关:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-publiahgrd" type="text" name="publiahgrd" value="${model.publiahgrd}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-birth">出生日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-birth" type="text" name="birth" value="${model.birth}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-nation">民族:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-nation" type="text" name="nation" value="${model.nation}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivernum">司机驾驶证编号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-drivernum" type="text" name="drivernum" value="${model.drivernum}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-driverfilenum">司机驾驶证档案号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-driverfilenum" type="text" name="driverfilenum" value="${model.driverfilenum}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-predriverdate">驾照初领日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-predriverdate" type="text" name="predriverdate" value="${model.predriverdate}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-precartype">准驾车型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-precartype" type="text" name="precartype" value="${model.precartype}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-validstartdate">有效起始日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-validstartdate" type="text" name="validstartdate" value="${model.validstartdate}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-validtrem">有效期限:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-validtrem" type="text" name="validtrem" value="${model.validtrem}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carname">车型名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carname" type="text" name="carname" value="${model.carname}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contracterm">合同期限:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-contracterm" type="text" name="contracterm" value="${model.contracterm}" class="text form-control input-sm "  ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='agent_driver_mgr-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

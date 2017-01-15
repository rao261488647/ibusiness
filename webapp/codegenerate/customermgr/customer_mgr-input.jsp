<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>客户管理编辑</title>
    <%@include file="/common/center_input.jsp"%>
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
		    //入职意向 选项
		    changeYX();
		});
		//入职意向 选项
		function changeYX(){
			var tsstr = $("#code-userstatus-selector").val();
			if ("已签约"== tsstr || "已离职"==tsstr) {
			    $("#code-intention").attr("disabled","disabled");
			} else {
				$("#code-intention").removeAttr("disabled");
			}
		}
		
		
    </script>
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">客户管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="customer_mgr-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                      <input id="code_token" type="hidden" name="token" value="${model.token}">
                      <input id="code_password" type="hidden" name="password" value="${model.password}">
                  </c:if>
                  
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-customername">姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-customername" type="text" name="customername" value="${model.customername}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-sex">性别:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-sex" name="sex" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${sexItems}" var="item">          <option value="${item.key}" ${item.key==model.sex? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                      </div>
                      <div class="form-group">    
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-customercell">联系电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-customercell" type="text" name="customercell" value="${model.customercell}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-origin">籍贯:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-origin" type="text" name="origin" value="${model.origin}" class="text form-control input-sm required"  ></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-occupation">职业:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-occupation" type="text" name="occupation" value="${model.occupation}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-idcard">身份证号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-idcard" type="text" name="idcard" value="${model.idcard}" class="text form-control input-sm required"  ></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-iscrime">有无犯罪记录:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    
	                          <select id="code-iscrime" name="iscrime" class="form-control input-sm required" > 
		                          <option value="" >请选择</option>
		                          <option value="有" ${'有'==model.iscrime? 'selected':''} >有</option>
		                          <option value="无" ${'无'==model.iscrime? 'selected':''} >无</option>
	                          </select>
                          </div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-tosource">客户来源:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">
                              <select id="code-tosource" name="tosource"   class="form-control input-sm required" onchange="" > 
	                              <option value="" >请选择</option>
	                              <c:forEach items="${userstatusItems}" var="item">          
	                              <option value="${item.key}" ${item.key==model.userstatus? 'selected':''} >${item.value}</option>        
	                              </c:forEach>    
                              </select>
                          
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractstatus">签约状况:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-contractstatus" name="contractstatus" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${contractstatusItems}" var="item">          <option value="${item.key}" ${item.key==model.contractstatus? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractplan">签约方案:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-contractplan" type="text" name="contractplan" value="${model.contractplan}" class="text form-control input-sm required"  ></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-userstatus">客户状态:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    
                          <select id="code-userstatus-selector" name="userstatus" class="form-control input-sm required" onchange="changeYX()">          
                          <option value="" >请选择</option>   
                          <c:forEach items="${tosourceItems}" var="item">          
                          <option value="${item.key}" ${item.key==model.tosource? 'selected':''} >${item.value}</option>        
                          </c:forEach>    
                          </select>
                          </div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" >入职意向:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-intention" name="intention" class="form-control input-sm "  >          <option value="" >请选择</option>        <c:forEach items="${intentionItems}" var="item">          <option value="${item.key}" ${item.key==model.intention? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                      </div>
                      <!--  -->
                      <div class="form-group">   
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-salesman">业务员:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-salesman" type="text" name="salesman" value="${model.salesman}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-unitname">单位名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-unitname" type="text" name="unitname" value="${model.unitname}" class="text form-control input-sm "  ></div>
                          <!-- 
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-post">职务:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-post" type="text" name="post" value="${model.post}" class="text form-control input-sm "  ></div>
                          -->
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-customeradd">客户地址:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control required" id="code-customeradd" name="customeradd" rows="2" >${model.customeradd}</textarea></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-customertype">客户分类:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-customertype" name="customertype" class="form-control input-sm required" >          <option value="" >请选择</option>  
                                <option value="单位" ${'单位'==model.customertype? 'selected':''} >单位</option>
                                <option value="个人" ${'个人'==model.customertype? 'selected':''} >个人</option> 
                           </select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-recommendman">推荐人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-recommendman" type="text" name="recommendman" value="${model.recommendman}" class="text form-control input-sm "  ></div>
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-customertobe">客户归属:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-customertobe" name="customertobe" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${customertobeItems}" var="item">          <option value="${item.key}" ${item.key==model.customertobe? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-createdatetime">新建日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-createdatetime" type="text" name="createdatetime" value="<fmt:formatDate value="${model.createdatetime}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span></div></div>
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-device">设备型号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-device" type="text" name="device" value="${model.device}" class="text form-control input-sm" readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-imei">设备唯一标识:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-imei" type="text" name="imei" value="${model.imei}" class="text form-control input-sm" readonly ></div>
                       </div>
                       <div class="form-group">
                         <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-appsysversion">系统版本:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-appsysversion" type="text" name="appsysversion" value="${model.appsysversion}" class="text form-control input-sm " readonly ></div>
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remarks">备注:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control " id="code-remarks" name="remarks" rows="2" >${model.remarks}</textarea></div>
                       </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='customer_mgr-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

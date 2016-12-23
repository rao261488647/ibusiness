<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>财务结算页面编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">财务结算页面编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="financial_settlement-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-customername">客户:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-customername" type="text" name="customername" value="${model.customername}" class="text form-control input-sm required" readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#customernameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changeValue(name,cellphone,drivertype,carname,carnum){   	$("#code-customername").val(name);   	$("#code-telephone").val(cellphone);   	$("#code-driverclass").val(drivertype);   	$("#code-carmodel").val(carname);   	$("#code-carnum").val(carnum);$('#customernameSInputDiv').modal('hide');        }function searchOwnername(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr +$("#code_table_name").val(), 	 dataType:"json",     data:"filter_LIKES_name="+$("#code_table_name").val(), 	 success: function(jsonData){ 	   $("#customernameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue(\''+item.name+'\',\''+item.cellphone+'\',\''+item.drivertype+'\',\''+item.carname+'\',\''+item.carnum+'\')" >选择</a></td>  <td>'+item.name+'</td>  <td>'+item.cellphone+'</td>  <td>'+item.drivertype+'</td>  <td>'+item.carname+'</td>  <td>'+item.carnum+'</td>           	     </tr>';          $('#customernameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="customernameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#customernameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_name">司机姓名:</label>              <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnername('financial_settlement/name-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">司机姓名</th>   <th class="sorting">司机电话</th>   <th class="sorting">司机签约类型</th>   <th class="sorting">车型名称</th>   <th class="sorting">车牌号码</th>           	   </tr>           	</thead>           	<tbody id='customernameRowadd'>           	   <c:forEach items="${customernamePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue('${item.name}','${item.cellphone}','${item.drivertype}','${item.carname}','${item.carnum}')" >选择</a></td>  <td>${item.name}</td>  <td>${item.cellphone}</td>  <td>${item.drivertype}</td>  <td>${item.carname}</td>  <td>${item.carnum}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#customernameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-telephone">电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-telephone" type="text" name="telephone" value="${model.telephone}" class="text form-control input-sm " readonly ></div>
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-driverclass">司机类别:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-driverclass" type="text" name="driverclass" value="${model.driverclass}" class="text form-control input-sm required" readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carmodel">司机车辆型号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carmodel" type="text" name="carmodel" value="${model.carmodel}" class="text form-control input-sm " readonly ></div>
                       </div>
                       <div class="form-group">
                           <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm required" readonly ></div>
                       </div>
                        <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-settlementtype">结算类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-settlementtype" name="settlementtype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${settlementtypeItems}" var="item">          <option value="${item.key}" ${item.key==model.settlementtype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-setbusinesstype">结算业务类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-setbusinesstype" name="setbusinesstype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${setbusinesstypeItems}" var="item">          <option value="${item.key}" ${item.key==model.setbusinesstype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-transactionno">交易编号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-transactionno" type="text" name="transactionno" value="${model.transactionno}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="eventdate" value="${model.eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-amount">金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-amount" type="text" name="amount" value="${model.amount}" class="text form-control input-sm "  ></div>
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-monthdate">租金所属月份:</label>
                          <script type="text/javascript">$(".form_datetime").datepicker({format: 'yyyy-mm'});</script><div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker_yyyymm date">  <span class="add-on">    <input id="code-monthdate" type="text" name="monthdate" value="${model.monthdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-documentnum">凭证号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-documentnum" type="text" name="documentnum" value="${model.documentnum}" class="text form-control input-sm required"  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control" id="code-remark" name="remark" rows="2" >${model.remark}</textarea></div>
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='financial_settlement-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

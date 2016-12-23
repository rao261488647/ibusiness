<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>纯租编辑</title>
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
		});
		
		//方案 选项
		$("#code-optionplan").change(function() {
			var opstr = $("#code-optionplan").val();
			if ("1"== opstr) {
			    $("#code-carname").val("腾势");
			    $("#code-fixedvalue").val("5999");
			} else if ("2"== opstr) {
			    $("#code-carname").val("腾势");
			    $("#code-fixedvalue").val("6999");
			} else if ("3"== opstr) {
			    $("#code-carname").val("E5");
			    $("#code-fixedvalue").val("5200");
			} else if ("4"== opstr) {
			    $("#code-carname").val("E5");
			    $("#code-fixedvalue").val("5800");
			}
			// 计算超出公里数
			var ikm = $("#code-initialkm").val();
			var ekm = $("#code-endmonthkm").val();
			if (ikm > 0 && ekm >0) {
		        thankm(ikm, ekm);
			}
		});
		// 超出公里数计算
		$("#code-initialkm").change(function() {
			var ikm = $("#code-initialkm").val();
			var ekm = $("#code-endmonthkm").val();
			if (ikm > 0 && ekm >0) {
				// 计算超出公里数
		        thankm(ikm, ekm);
			}
		});
        $("#code-endmonthkm").change(function() {
        	var ikm = $("#code-initialkm").val();
			var ekm = $("#code-endmonthkm").val();
			if (ikm > 0 && ekm >0) {
				// 计算超出公里数
		        thankm(ikm, ekm);
			}
		});
        // 计算超出公里数
        function thankm(ikm, ekm){
        	var thankm = ekm - ikm- 8000;
        	var amount = $("#code-fixedvalue").val();
        	if (amount < 0) {
        		amount =0;
        	}
			if (thankm < 0) {
				thankm=0;
			} else if (thankm < 2000) {
				amount = amount*1 + thankm*0.5;
			} else if (thankm >= 2000) {
				amount = amount*1 + thankm*1;
			}
			$("#code-thankm").val(thankm);
			
			$("#code-amount").val(amount);
    	};
		
    </script>
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">纯租编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="carrental-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivername">司机姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-drivername" type="text" name="drivername" value="${model.drivername}" class="text form-control input-sm required" readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changenameValue(name,carnum){   	$("#code-drivername").val(name);   	$("#code-carnum").val(carnum);$('#drivernameSInputDiv').modal('hide');        }function searchOwnernamename(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr, 	 dataType:"json",     data:"filter_LIKES_name="+$("#code_table_name").val(), 	 success: function(jsonData){ 	   $("#drivernameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue(\''+item.name+'\',\''+item.carnum+'\')" >选择</a></td>  <td>'+item.name+'</td>  <td>'+item.carnum+'</td>           	     </tr>';          $('#drivernameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="drivernameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_name">司机姓名:</label>              <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamename('carrental/name-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">司机姓名</th>   <th class="sorting">车牌号码</th>           	   </tr>           	</thead>           	<tbody id='drivernameRowadd'>           	   <c:forEach items="${drivernamePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue('${item.name}','${item.carnum}')" >选择</a></td>  <td>${item.name}</td>  <td>${item.carnum}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm " readonly ></div>
                            </div>
                          <div class="form-group">
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-optionplan">方案:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-optionplan" name="optionplan" class="form-control input-sm required" >
	                                    <option value="" >请选择</option>
	                                    <option value="1" ${'1'==model.optionplan? 'selected':''} >腾势半年5999</option>
	                                    <option value="2" ${'2'==model.optionplan? 'selected':''} >腾势月租6999</option>
	                                    <option value="3" ${'3'==model.optionplan? 'selected':''} >比亚迪E5半年5200</option>
	                                    <option value="4" ${'4'==model.optionplan? 'selected':''} >比亚迪E5月租5800</option>
                                            </select></div>
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carname">车型:</label>
                         	  <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carname" type="text" name="carname" value="${model.carname}" class="text form-control input-sm " readonly ></div>
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-fixedvalue">固定值:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-fixedvalue" type="text" name="fixedvalue" value="${model.fixedvalue}" class="text  form-control input-sm number required" readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">提车日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="eventdate" value="${model.eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-initialkm">初始公里数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-initialkm" type="text" name="initialkm" value="${model.initialkm}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-endmonthkm">月末公里数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-endmonthkm" type="text" name="endmonthkm" value="${model.endmonthkm}" class="text  form-control input-sm number required" ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-thankm">超出公里数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-thankm" type="text" name="thankm" value="${model.thankm}" class="text  form-control input-sm number " readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-amount">扣款金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-amount" type="text" name="amount" value="${model.amount}" class="text  form-control input-sm number " readonly></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='carrental-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

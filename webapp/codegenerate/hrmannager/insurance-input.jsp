<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>出险管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">出险管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="insurance-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivername">司机姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-drivername" type="text" name="drivername" value="${model.drivername}" class="text form-control input-sm required" readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changenameValue(name,cellphone,carname,carnum){   	$("#code-drivername").val(name);   	$("#code-driverphone").val(cellphone);   	$("#code-carname").val(carname);   	$("#code-carnum").val(carnum);$('#drivernameSInputDiv').modal('hide');        }function searchOwnernamename(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr, 	 dataType:"json",     data:"filter_LIKES_name="+$("#code_table_name").val(), 	 success: function(jsonData){ 	   $("#drivernameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue(\''+item.name+'\',\''+item.cellphone+'\',\''+item.carname+'\',\''+item.carnum+'\')" >选择</a></td>  <td>'+item.name+'</td>  <td>'+item.cellphone+'</td>  <td>'+item.carname+'</td>  <td>'+item.carnum+'</td>           	     </tr>';          $('#drivernameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="drivernameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_name">司机姓名:</label>              <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamename('insurance/name-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">司机姓名</th>   <th class="sorting">司机电话</th>   <th class="sorting">车型名称</th>   <th class="sorting">车牌号码</th>           	   </tr>           	</thead>           	<tbody id='drivernameRowadd'>           	   <c:forEach items="${drivernamePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue('${item.name}','${item.cellphone}','${item.carname}','${item.carnum}')" >选择</a></td>  <td>${item.name}</td>  <td>${item.cellphone}</td>  <td>${item.carname}</td>  <td>${item.carnum}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-driverphone">联系方式:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-driverphone" type="text" name="driverphone" value="${model.driverphone}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carname">车型名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carname" type="text" name="carname" value="${model.carname}" class="text form-control input-sm " readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">出险日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="eventdate" value="${model.eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-responsibility">责任:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-responsibility" name="responsibility" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${responsibilityItems}" var="item">          <option value="${item.key}" ${item.key==model.responsibility? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-mainttime">停运天数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-mainttime" type="text" name="mainttime" value="${model.mainttime}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carflag">替换车使用:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-carflag" name="carflag" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${carflagItems}" var="item">          <option value="${item.key}" ${item.key==model.carflag? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-newcarname">替换车型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-newcarname" type="text" name="newcarname" value="${model.newcarname}" class="text form-control input-sm " readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#newcarnameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changecarnumValue(typename,carnum){   	$("#code-newcarname").val(typename);   	$("#code-newcarnum").val(carnum);$('#newcarnameSInputDiv').modal('hide');        }function searchOwnernamecarnum(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr, 	 dataType:"json",     data:"filter_LIKES_carnum="+$("#code_table_carnum").val(), 	 success: function(jsonData){ 	   $("#newcarnameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changecarnumValue(\''+item.typename+'\',\''+item.carnum+'\')" >选择</a></td>  <td>'+item.typename+'</td>  <td>'+item.carnum+'</td>           	     </tr>';          $('#newcarnameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="newcarnameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#newcarnameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_carnum">车牌号:</label>              <input type="text" id="code_table_carnum" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamecarnum('insurance/carnum-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">型号名称</th>   <th class="sorting">车牌号</th>           	   </tr>           	</thead>           	<tbody id='newcarnameRowadd'>           	   <c:forEach items="${newcarnamePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changecarnumValue('${item.typename}','${item.carnum}')" >选择</a></td>  <td>${item.typename}</td>  <td>${item.carnum}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#newcarnameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-newcarnum">替换车牌:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-newcarnum" type="text" name="newcarnum" value="${model.newcarnum}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='insurance-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

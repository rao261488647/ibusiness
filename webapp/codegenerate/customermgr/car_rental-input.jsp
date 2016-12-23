<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>租车预订信息编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">租车预订信息编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="car_rental-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-name">姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm required" readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changeValue(customername,customercell){   	$("#code-name").val(customername);   	$("#code-telephone").val(customercell);$('#nameSInputDiv').modal('hide');        }function searchOwnername(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr +$("#code_table_customername").val(), 	 dataType:"json",     data:"filter_LIKES_customername="+$("#code_table_customername").val(), 	 success: function(jsonData){ 	   $("#nameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue(\''+item.customername+'\',\''+item.customercell+'\')" >选择</a></td>  <td>'+item.customername+'</td>  <td>'+item.customercell+'</td>           	     </tr>';          $('#nameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="nameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_customername">客户姓名:</label>              <input type="text" id="code_table_customername" name="filter_LIKES_customername" value="${param.filter_LIKES_customername}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnername('car_rental/customername-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">客户姓名</th>   <th class="sorting">客户电话</th>           	   </tr>           	</thead>           	<tbody id='nameRowadd'>           	   <c:forEach items="${namePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue('${item.customername}','${item.customercell}')" >选择</a></td>  <td>${item.customername}</td>  <td>${item.customercell}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-telephone">电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-telephone" type="text" name="telephone" value="${model.telephone}" class="text form-control input-sm required" readonly ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-rentaltype">预约类型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-rentaltype" type="text" name="rentaltype" value="${model.rentaltype}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-submitdate">提交日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-submitdate" type="text" name="submitdate" value="${model.submitdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cardate">用车日期时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-cardate" type="text" name="cardate" value="${model.cardate}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-tripinfo">行程:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control " id="code-tripinfo" name="tripinfo" rows="2" >${model.tripinfo}</textarea></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealperson">处理人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-dealperson" type="text" name="dealperson" value="${model.dealperson}" class="text form-control input-sm "  ></div>
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealresult">处理结果:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control " id="code-dealresult" name="dealresult" rows="2" >${model.dealresult}</textarea></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealtime">处理时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-dealtime" type="text" name="dealtime" value="${model.dealtime}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-platform">租车平台:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-platform" type="text" name="platform" value="${model.platform}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-termdate">合同期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-termdate" type="text" name="termdate" value="${model.termdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-signdate">预约上门签约时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-signdate" type="text" name="signdate" value="${model.signdate}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-regtime">到场注册时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-regtime" type="text" name="regtime" value="${model.regtime}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='car_rental-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

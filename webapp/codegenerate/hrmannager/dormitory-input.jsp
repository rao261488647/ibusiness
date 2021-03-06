<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>宿舍管理编辑</title>
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
		    changeSelfAmount();
		})
		function changeSelfAmount(){
			var useflag = $("#code-useflag").val();
			if(useflag == "自费"){
				$("#code-selfamount-label").css('display','block');
				$("#code-selfamount").css('display','block');
			}else{
				$("#code-selfamount-label").css('display','none');
				$("#code-selfamount").css('display','none');
				$("#code-selfamount").val("");
			}
		}
    </script>
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">宿舍管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="dormitory-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-name">姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm required" readonly >   
                          <c:if test="${model.name == null || model.name == ''}">
	                          <a href="#" class="btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('show');" >选择</a> 
                          </c:if>    
                          <script type="text/javascript">   	function changenameValue(name,cellphone,carname,carnum){   	$("#code-name").val(name);   	$("#code-callphone").val(cellphone);   	$("#code-carname").val(carname);   	$("#code-carnum").val(carnum);$('#nameSInputDiv').modal('hide');        }function searchOwnernamename(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr, 	 dataType:"json",     data:"filter_LIKES_name="+$("#code_table_name").val(), 	 success: function(jsonData){ 	   $("#nameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue(\''+item.name+'\',\''+item.cellphone+'\',\''+item.carname+'\',\''+item.carnum+'\')" >选择</a></td>  <td>'+item.name+'</td>  <td>'+item.cellphone+'</td>  <td>'+item.carname+'</td>  <td>'+item.carnum+'</td>           	     </tr>';          $('#nameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="nameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_name">司机姓名:</label>              <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamename('dormitory/name-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">司机姓名</th>   <th class="sorting">司机电话</th>   <th class="sorting">车型名称</th>   <th class="sorting">车牌号码</th>           	   </tr>           	</thead>           	<tbody id='nameRowadd'>           	   <c:forEach items="${namePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue('${item.name}','${item.cellphone}','${item.carname}','${item.carnum}')" >选择</a></td>  <td>${item.name}</td>  <td>${item.cellphone}</td>  <td>${item.carname}</td>  <td>${item.carnum}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-callphone">联系方式:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-callphone" type="text" name="callphone" value="${model.callphone}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eid">身份证号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-eid" type="text" name="eid" value="${model.eid}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-entrydate">入职时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  
                          <span class="add-on">    
                          <input id="code-entrydate" type="text" name="entrydate" value="<fmt:formatDate value="${model.entrydate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-checkintime">入住时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    
                          <input id="code-checkintime" type="text" name="checkintime" value="<fmt:formatDate value="${model.checkintime}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-adderss">宿舍地址:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-adderss" type="text" name="adderss" value="${model.adderss}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-roomno">房号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-roomno" type="text" name="roomno" value="${model.roomno}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-useflag">使用方式:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-useflag" name="useflag" class="form-control input-sm required" onchange="changeSelfAmount()" >          <option value="" >请选择</option>        <c:forEach items="${useflagItems}" var="item">          <option value="${item.key}" ${item.key==model.useflag? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-departuredate">搬离日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    
                          <input id="code-departuredate" type="text" name="departuredate" value="<fmt:formatDate value="${model.departuredate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-selfamount" id="code-selfamount-label">自费金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-selfamount" type="text" name="selfamount" value="${model.selfamount}" class="text form-control input-sm "  ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='dormitory-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

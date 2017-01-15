<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>停车场出入库管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">停车场出入库管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="parkinglot-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-typename">型号名称:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-typename" type="text" name="typename" value="${model.typename}" class="text form-control input-sm required" readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#typenameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changecarnumValue(typename,carnum){   	$("#code-typename").val(typename);   	$("#code-carnum").val(carnum);$('#typenameSInputDiv').modal('hide');        }function searchOwnernamecarnum(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr, 	 dataType:"json",     data:"filter_LIKES_carnum="+$("#code_table_carnum").val(), 	 success: function(jsonData){ 	   $("#typenameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changecarnumValue(\''+item.typename+'\',\''+item.carnum+'\')" >选择</a></td>  <td>'+item.typename+'</td>  <td>'+item.carnum+'</td>           	     </tr>';          $('#typenameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="typenameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#typenameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_carnum">车牌号:</label>              <input type="text" id="code_table_carnum" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamecarnum('parkinglot/carnum-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">车型名称</th>   <th class="sorting">车牌号</th>           	   </tr>           	</thead>           	<tbody id='typenameRowadd'>           	   <c:forEach items="${typenamePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changecarnumValue('${item.typename}','${item.carnum}')" >选择</a></td>  <td>${item.typename}</td>  <td>${item.carnum}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#typenameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm " readonly ></div>
                          </div>
                          <div class="form-group">
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ininfo">入库原因:</label>
	                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control required" id="code-ininfo" name="ininfo" rows="2" >${model.ininfo}</textarea></div>
                          </div>
                          <div class="form-group">
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-intime">入库时间:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-intime" type="text" name="intime" value="${model.intime}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-inmil">入库里程:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-inmil" type="text" name="inmil" value="${model.inmil}" class="text  form-control input-sm number required" ></div>
                          </div>
                          <div class="form-group">
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-outinfo">出库原因:</label>
	                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control " id="code-outinfo" name="outinfo" rows="2" >${model.outinfo}</textarea></div>
                          </div>
                          <div class="form-group">
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-outime">出库时间:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-outime" type="text" name="outime" value="${model.outime}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
	                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-outmil">出库里程:</label>
	                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-outmil" type="text" name="outmil" value="${model.outmil}" class="text  form-control input-sm number " ></div>
                          </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='parkinglot-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

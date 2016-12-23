<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>专车司机记录管理编辑</title>
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
		})
		// 发送
		function send(){
			$("#cgForm").attr("action","special_record_mgr-send.do");
			$("#cgForm").submit();
		}
		
    </script>
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">专车司机记录管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="special_record_mgr-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-eventdate" type="text" name="eventdate" value="<fmt:formatDate value="${model.eventdate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" class="text form-control input-sm required" readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-monthdate">年月:</label>
                          <script type="text/javascript">$(".form_datetime").datepicker({format: 'yyyy-mm'});</script><div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker_yyyymm date">  <span class="add-on">    <input id="code-monthdate" type="text" name="monthdate" value="${model.monthdate}" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-sentperson">发送方:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-sentperson" name="sentperson" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${sentpersonItems}" var="item">          <option value="${item.key}" ${item.key==model.sentperson? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-infotype">信息类别:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-infotype" name="infotype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${infotypeItems}" var="item">          <option value="${item.key}" ${item.key==model.infotype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cellphone">司机电话:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-cellphone" type="text" name="cellphone" value="${model.cellphone}" class="text form-control input-sm required" readonly ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivername">司机姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">
                             <input id="code-drivername" type="text" name="drivername" value="${model.drivername}" class="text form-control input-sm required" readonly >
                                <a href="#" class="btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('show');" >选择</a>
                                   <script type="text/javascript">
                                   function changeValue(customername,customercell){
                                	   $("#code-drivername").val(customername);
                                	   $("#code-cellphone").val(customercell);
                                	   $('#drivernameSInputDiv').modal('hide');
                                   }
                                   function searchOwnername(urlStr) {
                                	   $.ajax({
                                		   type: "POST",
                                		   url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr,
                                		   dataType:"json",
                                		   data:"filter_LIKES_customercell="+$("#code_table_customercell").val(),
                                		   success: function(jsonData){
                                			   $("#drivernameRowadd tr").remove();
                                		       $.each(jsonData, function(i, item) {
                                		    	   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue(\''+item.customername+'\',\''+item.customercell+'\')" >选择</a></td>  <td>'+item.customername+'</td>  <td>'+item.customercell+'</td>           	     </tr>';          $('#drivernameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="drivernameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>
                                		    	              <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>
                           		    	                         <div class="form-group">
                        		    	                               <label for="code_table_customercell">客户电话:</label>
                        		    	                                <input type="text" id="code_table_customercell" name="filter_LIKES_customercell" value="${param.filter_LIKES_customercell}">
                     		    	                                    <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnername('special_record_mgr/name-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>
                     		    	                              </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">客户姓名</th>   <th class="sorting">客户电话</th>           	   </tr>           	</thead>           	<tbody id='drivernameRowadd'>           	   <c:forEach items="${drivernamePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue('${item.customername}','${item.customercell}')" >选择</a></td>  <td>${item.customername}</td>  <td>${item.customercell}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#drivernameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-content">内容:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control required" id="code-content" name="content" rows="2" >${model.content}</textarea></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-account">金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-account" type="text" name="account" value="${model.account}" class="text  form-control input-sm number required" ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealperson">处理人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-dealperson" type="text" name="dealperson" value="${model.dealperson}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-dealcontent">处理内容:</label>
                          <div class="col-lg-8 col-md-8 col-sm-8">    <textarea class="form-control " id="code-dealcontent" name="dealcontent" rows="2" >${model.dealcontent}</textarea></div>
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-sendflag">发送标识:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-sendflag" type="text" name="sendflag" value="${model.sendflag}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                     <c:if test="${model.sendflag != '发送成功'}">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="send();" class="btn btn-primary btn-sm" >发送</button>
	                     </c:if>
	                      <button type="button" onclick="location.href='special_record_mgr-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

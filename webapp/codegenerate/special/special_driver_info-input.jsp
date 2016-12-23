<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>专车司机信息编辑</title>
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
    <div class="panel panel-default col-lg-10 col-md-10 col-sm-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">专车司机信息编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="special_driver_info-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                    <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-account">账号:</label>
					<div class="col-lg-3 col-md-3 col-sm-3">
						<input id="code-account" type="text" name="account"
							value="${model.account}" class="text required" readonly>
						<a href="#" class="btn btn-primary btn-sm" onclick="$('#accountSInputDiv').modal('show');">选择</a>
						<script type="text/javascript">
							function changeValue_zh(customercell, customername, customercell, recommended, salesman, tosource) {
								$("#code-account").val(customercell);
								$("#code-name").val(customername);
								$("#code-cellphone").val(customercell);
								$("#code-recommended").val(recommended);
								$("#code-tosource").val(tosource);
								$("#code-salesman").val(salesman);
								$('#accountSInputDiv').modal('hide');
							}
							function searchOwnername_zh(urlStr) {
								$.ajax({ type : "POST",
											url : "/" + window.location.pathname.split("/")[1] + "/" + urlStr,
											dataType : "json",
											data : "filter_LIKES_customername=" + $( "#code_table_customername").val(),
											success : function(jsonData) {
												$("#accountRowadd tr").remove();
												$.each(jsonData, function(i, item) {
																	var newRow = '  <tr> <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue_zh(\''
																			+ item.customercell	+ '\',\''
																			+ item.customername + '\',\''
																			+ item.customercell + '\',\''
																			+ item.recommendman + '\',\''
																			+ item.salesman + '\',\''
																			+ item.tosource + '\')" >选择</a></td>  <td>'
																			+ item.customercell + '</td>  <td>'
																			+ item.customername + '</td>  <td>'
																			+ item.customercell + '</td>  <td>'
																			+ item.recommendman + '</td>  <td>'
																			+ item.salesman + '</td>  <td>'
																			+ item.tosource + '</td> </tr>';
																	$('#accountRowadd')
																			.append(
																					newRow);
																});
											},
											error : function(XMLHttpRequest,
													textStatus, errorThrown) {
												alert('请求数据出错了!');
											}
										});
							}
						</script>
						<div id="accountSInputDiv" class="modal fade" tabindex="-1"
							style="top: 20%;">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<a href="#" class="close btn btn-primary btn-sm"
											onclick="$('#accountSInputDiv').modal('hide');"><span>&times;</span><span
											class="sr-only">Close</span></a>
										<h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>
										<div class="form-group">
											<label for="code_table_customername">客户姓名:</label> <input
												type="text" id="code_table_customername"
												name="filter_LIKES_customername"
												value="${param.filter_LIKES_customername}"> <a
												class="btn btn-primary btn-sm" href="#"
												onclick="searchOwnername_zh('special_driver_info/customername-list.do')"><span
												class="glyphicon glyphicon-search"></span>查询</a>
										</div>
									</div>
									<div class="modal-body">
										<div class="content">
											<table id="codeGrid" class="table table-hover table-bordered">
												<thead>
													<tr>
														<th width="80">&nbsp;</th>
														<th >客户姓名</th>
														<th >客户电话</th>
														<th >客户姓名</th>
														<th >推荐人</th>
														<th >业务员</th>
														<th >客户来源</th>
													</tr>
												</thead>
												<tbody id='accountRowadd'>
													<c:forEach items="${accountPage.result}" var="item">
														<tr>
															<td><a href="#" class="btn btn-primary btn-sm"
																onClick="changeValue_zh('${item.customercell}','${item.customername}','${item.customercell}','${item.recommendman}','${item.salesman}','${item.tosource}')">选择</a></td>
															<td>${item.customercell}</td>
															<td>${item.customername}</td>
															<td>${item.customercell}</td>
															<td>${item.recommendman}</td>
															<td>${item.salesman}</td>
															<td>${item.tosource}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn btn-primary btn-sm"
											onclick="$('#accountSInputDiv').modal('hide');">关闭</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					         <label class="col-lg-1 col-md-1 col-sm-1 control-label" for="code-name">司机姓名:</label>
                             <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm required" readonly ></div>
                          
                             <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-logintel">报名登录手机号:</label>
	                         <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-logintel" type="text" name="logintel" value="${model.logintel}" class="text form-control input-sm " readonly ></div>
                       </div>
                       
                       
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cellphone">司机电话:</label>
	                      <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-cellphone" type="text" name="cellphone" value="${model.cellphone}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eid">司机身份证号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-eid" type="text" name="eid" value="${model.eid}" class="text form-control input-sm " maxlength="18" ></div>
                          <label class="col-lg-1 col-md-1 col-sm-1 control-label" for="code-sex">性别:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">    <select id="code-sex" name="sex" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${sexItems}" var="item">          <option value="${item.key}" ${item.key==model.sex? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                      </div>
                      
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-address">地址:</label>
                          <div class="col-lg-9 col-md-9 col-sm-9">    <textarea class="form-control" id="code-address" name="address" rows="2" >${model.address}</textarea></div>
                          
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-publiahgrd">发证机关:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-publiahgrd" type="text" name="publiahgrd" value="${model.publiahgrd}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-1 col-md-1 col-sm-1 control-label" for="code-birth">出生日期:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-birth" type="text" name="birth" value="<fmt:formatDate value='${model.birth}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-nation">民族:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-nation" type="text" name="nation" value="${model.nation}" class="text form-control input-sm "  ></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eidurl">身份证附件:</label>
                          <div class="col-lg-4 col-md-4 col-sm-4 ">  <input id="code_table_eidurl_text" type="text" value="">  <a href="#" class="btn btn-primary btn-sm" onclick="$('#file_eidurlupload').click()"><span class="glyphicon glyphicon-upload"></span>上传</a>  <input id="code_table_eidurl" type="hidden" name="eidurl" value="${model.eidurl}"></div><div class="col-lg-4 col-md-4 col-sm-4 ">  <c:if test="${model.eidurl != null && model.eidurl !=''}">    <a href="eidurl-download.do?filename=${model.eidurl}&path=${model.eidurl}">${model.eidurl}</a>  </c:if></div>
                       </div>
                       
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivernum">驾驶证编号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-drivernum" type="text" name="drivernum" value="${model.drivernum}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-driverfilenum">驾驶证档案号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-driverfilenum" type="text" name="driverfilenum" value="${model.driverfilenum}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-predriverdate">驾照初领日期:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-predriverdate" type="text" name="predriverdate" value="<fmt:formatDate value='${model.predriverdate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                       </div>
                      
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-precartype">驾照类别:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">    <select id="code-precartype" name="precartype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${precartypeItems}" var="item">          <option value="${item.key}" ${item.key==model.precartype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-validstartdate">有效起始日期:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-validstartdate" type="text" name="validstartdate" value="<fmt:formatDate value='${model.validstartdate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" placeholder="点击选择" class="form-control input-sm " readonly >  </span>  </div></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-validtrem">有效期限:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-validtrem" type="text" name="validtrem" value="${model.validtrem}" class="text form-control input-sm "  ></div>
                       </div>
                       
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivernumurl">驾驶证扫描附件:</label>
                          <div class="col-lg-4 col-md-4 col-sm-4 ">  <input id="code_table_drivernumurl_text" type="text" value="">  <a href="#" class="btn btn-primary btn-sm" onclick="$('#file_drivernumurlupload').click()"><span class="glyphicon glyphicon-upload"></span>上传</a>  <input id="code_table_drivernumurl" type="hidden" name="drivernumurl" value="${model.drivernumurl}"></div><div class="col-lg-4 col-md-4 col-sm-4 ">  <c:if test="${model.drivernumurl != null && model.drivernumurl !=''}">    <a href="drivernumurl-download.do?filename=${model.drivernumurl}&path=${model.drivernumurl}">${model.drivernumurl}</a>  </c:if></div>
                       </div>
                          
                      <hr>
                         <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carname">车型名称:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carname" type="text" name="carname" value="${model.carname}" class="text form-control input-sm " readonly >   
                             <script type="text/javascript">
                                 function changeValue_cx(typename,carnum,carframenum,carcolor){   	$("#code-carname").val(typename);   	$("#code-carnum").val(carnum);   	$("#code-carframenum").val(carframenum);   	$("#code-carcolor").val(carcolor);$('#carnameSInputDiv').modal('hide');        }
                                 function searchOwnername_cx(urlStr) {
                                     $.ajax({  	 type: "POST",
                                    	         url : "/" + window.location.pathname.split("/")[1] + "/" + urlStr,
			                                	 dataType:"json",
			                                	 data:"filter_LIKES_typename="+$("#code_table_typename").val()+"&filter_LIKES_carnum="+$("#code_table_carnum").val(),
			                                	 success: function(jsonData){
			                                		 $("#carnameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changeValue_cx(\''+item.typename+'\',\''+item.carnum+'\',\''+item.carframenum+'\',\''+item.carcolor+'\')" >选择</a></td>  <td>'+item.typename+'</td>  <td>'+item.carnum+'</td>  <td>'+item.carframenum+'</td>  <td>'+item.carcolor+'</td>           	     </tr>';          $('#carnameRowadd').append(newRow);         });     },
			                                	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }
                             </script>
                             <div id="carnameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#carnameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">
                                           <label for="code_table_typename">型号名称:</label>
                                           <input type="text" id="code_table_typename" name="filter_LIKES_typename" value="${param.filter_LIKES_typename}">
                                           <label >车牌号:</label>
                                           <input type="text" id="code_table_carnum" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}">
                                           <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnername_cx('special_driver_info/typename-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">
												<thead>
													<tr>
														<th width="80">&nbsp;</th>
														<th>型号名称</th>
														<th>车牌号</th>
														<th>车架号</th>
														<th>车辆颜色</th>
													</tr>
												</thead>
												<tbody id='carnameRowadd'>
													<c:forEach items="${carnamePage.result}" var="item">
														<tr>
															<td><a href="#" class="btn btn-primary btn-sm"
																onClick="changeValue_cx('${item.typename}','${item.carnum}','${item.carframenum}','${item.carcolor}')">选择</a></td>
															<td>${item.typename}</td>
															<td>${item.carnum}</td>
															<td>${item.carframenum}</td>
															<td>${item.carcolor}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#carnameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div>
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1">
								    <a href="#" class="btn btn-primary btn-sm" onclick="$('#carnameSInputDiv').modal('show');" >选择</a>
								</div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号码:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm " readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carframenum">车架号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-carframenum" type="text" name="carframenum" value="${model.carframenum}" class="text form-control input-sm " readonly ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carcolor">颜色:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-carcolor" type="text" name="carcolor" value="${model.carcolor}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                          <div class="form-group">
                          </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivertype">司机签约类型:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">    <select id="code-drivertype" name="drivertype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${drivertypeItems}" var="item">          <option value="${item.key}" ${item.key==model.drivertype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractid">合同编号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-contractid" type="text" name="contractid" value="${model.contractid}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractterm">合同期限:</label>
                          <div class="col-lg-1 col-md-1 col-sm-1">   <input id="code-contractterm" type="text" name="contractterm" value="${model.contractterm}" class="text form-control input-sm number " maxlength="3" ></div>
                          <label class="col-lg-1 col-md-1 col-sm-1" >月</label>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractsumdeposit">合同总押金:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-contractsumdeposit" type="text" name="contractsumdeposit" value="${model.contractsumdeposit}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractvalidday">合同生效日:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-contractvalidday" type="text" name="contractvalidday" value="${model.contractvalidday}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractoverdate">合同终止日期:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-contractoverdate" type="text" name="contractoverdate" value="${model.contractoverdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-permonthrent">签订租金:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-permonthrent" type="text" name="permonthrent" value="${model.permonthrent}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-permonthday">每月交租日:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-permonthday" type="text" name="permonthday" value="${model.permonthday}" class="text form-control input-sm number " maxlength="2" ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-cardeposit">车辆押金:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-cardeposit" type="text" name="cardeposit" value="${model.cardeposit}" class="text form-control input-sm "  ></div>
                          
                        </div>
                        <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-contractcopyupload">合同扫描件上传:</label>
                          <div class="col-lg-4 col-md-4 col-sm-4 ">  <input id="code_table_contractcopyupload_text" type="text" value="">  <a href="#" class="btn btn-primary btn-sm" onclick="$('#file_contractcopyuploadupload').click()"><span class="glyphicon glyphicon-upload"></span>上传</a>  <input id="code_table_contractcopyupload" type="hidden" name="contractcopyupload" value="${model.contractcopyupload}"></div><div class="col-lg-4 col-md-4 col-sm-4 ">  <c:if test="${model.contractcopyupload != null && model.contractcopyupload !=''}">    <a href="contractcopyupload-download.do?filename=${model.contractcopyupload}&path=${model.contractcopyupload}">${model.contractcopyupload}</a>  </c:if></div>
                        </div>
        <hr>
        <!--  -->
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-companybankact">公司银行账号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-companybankact" type="text" name="companybankact" value="${model.companybankact}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-companybank">公司银行开户行:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-companybank" type="text" name="companybank" value="${model.companybank}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-payrollbankact">工资卡银行账号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-payrollbankact" type="text" name="payrollbankact" value="${model.payrollbankact}" class="text form-control input-sm "  ></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-payrollbank">工资卡开户行:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-payrollbank" type="text" name="payrollbank" value="${model.payrollbank}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-udnumber">U盾号码:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-udnumber" type="text" name="udnumber" value="${model.udnumber}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-udusername">U盾用户名:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-udusername" type="text" name="udusername" value="${model.udusername}" class="text form-control input-sm "  ></div>
                      </div>
                      <div class="form-group">   
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ubername">Ubername:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-ubername" type="text" name="ubername" value="${model.ubername}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-uberpassword">Uberpassword:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-uberpassword" type="text" name="uberpassword" value="${model.uberpassword}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-uberonline">Uber上线状态:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">    <select id="code-uberonline" name="uberonline" class="form-control input-sm required" > <option value="" >请选择</option><option value="是" ${'是'==model.uberonline? 'selected':''} >是</option><option value="否" ${'否'==model.uberonline? 'selected':''} >否</option></select></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ddonline">滴滴出行上线状态:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">    <select id="code-ddonline" name="ddonline" class="form-control input-sm required" > <option value="" >请选择</option><option value="是" ${model.ddonline=='是'? 'selected':''} >是</option><option value="否" ${'否'==model.ddonline? 'selected':''}>否</option></select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-wageplan">工资(合作/合同)方案:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-wageplan" type="text" name="wageplan" value="${model.wageplan}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ttcard">通途电卡卡号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-ttcard" type="text" name="ttcard" value="${model.ttcard}" class="text form-control input-sm "  ></div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-bydcard">BYD电卡编号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-bydcard" type="text" name="bydcard" value="${model.bydcard}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ptdkcard">普天电卡卡号:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-ptdkcard" type="text" name="ptdkcard" value="${model.ptdkcard}" class="text form-control input-sm "  ></div>
                          <label class="col-lg-1 col-md-1 col-sm-1 control-label" for="code-sbcard">社保:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-sbcard" type="text" name="sbcard" value="${model.sbcard}" class="text form-control input-sm "  ></div>
                       </div>
        <hr>
                        <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-status">状态:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">    <select id="code-status" name="status" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${statusItems}" var="item">          <option value="${item.key}" ${item.key==model.status? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-platformto">加入平台:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-platformto" type="text" name="platformto" value="${model.platformto}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-tosource">客户来源:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-tosource" type="text" name="tosource" value="${model.tosource}" class="text form-control input-sm required"  ></div>
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-recommended">推荐人:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-recommended" type="text" name="recommended" value="${model.recommended}" class="text form-control input-sm required"  ></div>
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-salesman">业务员:</label>
                          <div class="col-lg-2 col-md-2 col-sm-2">   <input id="code-salesman" type="text" name="salesman" value="${model.salesman}" class="text form-control input-sm required"  ></div>
                       </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remarks">备注:</label>
                          <div class="col-lg-9 col-md-9 col-sm-9">    <textarea class="form-control" id="code-remarks" name="remarks" rows="2" maxlength="255" >${model.remarks}</textarea></div>
                        </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='special_driver_info-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
			            <!-- 上传组件form提交 --><form id="eidurlUploadForm" method="post" action="eidurl-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">		function eidurlUpload() {       if( window.confirm("您确认要上传吗?") == true ){			var $form=$('#eidurlUploadForm');	        $form.ajaxSubmit({	            url: $form.attr('action'),	            type: 'POST',	            dataType: 'text',	            beforeSubmit: function () {},	            success: function (data) {	            	$('#code_table_eidurl').attr('value',data);	            	$('#code_table_eidurl_text').attr('value',data);	            },	            error: function () {	            	alert('请求数据出错了!');	            }	        });		}}	 </script>    <input id="file_eidurlupload" type="file" name="attachment" style="display:none;" onChange="eidurlUpload();"> </form>
			            <!-- 上传组件form提交 --><form id="drivernumurlUploadForm" method="post" action="drivernumurl-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">		function drivernumurlUpload() {       if( window.confirm("您确认要上传吗?") == true ){			var $form=$('#drivernumurlUploadForm');	        $form.ajaxSubmit({	            url: $form.attr('action'),	            type: 'POST',	            dataType: 'text',	            beforeSubmit: function () {},	            success: function (data) {	            	$('#code_table_drivernumurl').attr('value',data);	            	$('#code_table_drivernumurl_text').attr('value',data);	            },	            error: function () {	            	alert('请求数据出错了!');	            }	        });		}}	 </script>    <input id="file_drivernumurlupload" type="file" name="attachment" style="display:none;" onChange="drivernumurlUpload();"> </form>
			            <!-- 上传组件form提交 --><form id="contractcopyuploadUploadForm" method="post" action="contractcopyupload-upload.do" class="form-horizontal" enctype="multipart/form-data">    <script type="text/javascript">		function contractcopyuploadUpload() {       if( window.confirm("您确认要上传吗?") == true ){			var $form=$('#contractcopyuploadUploadForm');	        $form.ajaxSubmit({	            url: $form.attr('action'),	            type: 'POST',	            dataType: 'text',	            beforeSubmit: function () {},	            success: function (data) {	            	$('#code_table_contractcopyupload').attr('value',data);	            	$('#code_table_contractcopyupload_text').attr('value',data);	            },	            error: function () {	            	alert('请求数据出错了!');	            }	        });		}}	 </script>    <input id="file_contractcopyuploadupload" type="file" name="attachment" style="display:none;" onChange="contractcopyuploadUpload();"> </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

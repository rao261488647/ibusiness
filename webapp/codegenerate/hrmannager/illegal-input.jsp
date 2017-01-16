<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>违章管理编辑</title>
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">违章管理编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="illegal-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号码:</label>
					<div class="col-lg-3 col-md-3 col-sm-3">
						<input id="code-carnum" type="text" name="carnum"
							value="${model.carnum}"
							class="text form-control input-sm required" readonly>
							<c:if test="${model.carnum == null || model.carnum == ''}">
								<a href="#" class="btn btn-primary btn-sm"
								onclick="$('#carnumSInputDiv').modal('show');">选择</a>
                          </c:if>  
						<script type="text/javascript">
							function changecarnumValue(carnum, name) {
								$("#code-carnum").val(carnum);
								$("#code-drivername").val(name);
								$('#carnumSInputDiv').modal('hide');
							}
							function searchOwnernamecarnum(urlStr) {
								$.ajax({
											type : "POST",
											url : "/" + window.location.pathname.split("/")[1]
													+ "/" + urlStr,
											dataType : "json",
											data : "filter_LIKES_carnum=" + $("#code_table_carnum2").val(),
											success : function(jsonData) {
												$("#carnumRowadd tr").remove();
												$
														.each(
																jsonData,
																function(i,
																		item) {
																	var newRow = '            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changecarnumValue(\''
																			+ item.carnum
																			+ '\',\''
																			+ item.name
																			+ '\')" >选择</a></td>  <td>'
																			+ item.carnum
																			+ '</td>  <td>'
																			+ item.name
																			+ '</td>           	     </tr>';
																	$(
																			'#carnumRowadd')
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
						<div id="carnumSInputDiv" class="modal fade" tabindex="-1"
							style="top: 20%;">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<a href="#" class="close btn btn-primary btn-sm"
											onclick="$('#carnumSInputDiv').modal('hide');"><span>&times;</span><span
											class="sr-only">Close</span></a>
										<h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>
										<div class="form-group">
											<label >车牌号码:</label>
											    <input type="text" id="code_table_carnum2" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}" onchange="aaa();">
												<a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamecarnum('illegal/carnum-list.do')">
												<span class="glyphicon glyphicon-search"></span>查询</a>
										</div>
									</div>
									<div class="modal-body">
										<div class="content">
											<table id="codeGrid" class="table table-hover table-bordered">
												<thead>
													<tr>
														<th width="80">&nbsp;</th>
														<th class="sorting">车牌号码</th>
														<th class="sorting">司机姓名</th>
													</tr>
												</thead>
												<tbody id='carnumRowadd'>
													<c:forEach items="${carnumPage.result}" var="item">
														<tr>
															<td><a href="#" class="btn btn-primary btn-sm"
																onClick="changecarnumValue('${item.carnum}','${item.name}')">选择</a></td>
															<td>${item.carnum}</td>
															<td>${item.name}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn btn-primary btn-sm"
											onclick="$('#carnumSInputDiv').modal('hide');">关闭</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-drivername">使用人:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   
                          <input id="code-drivername" type="text" name="drivername" value="${model.drivername}" <c:if test="${model.drivername != null && model.drivername != ''}"> disabled</c:if>  class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">违章时间:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker date">  <span class="add-on">    
                          <input id="code-eventdate" type="text" name="eventdate" value="<fmt:formatDate value="${model.eventdate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-amount">罚款金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">  <input id="code-amount" type="text" name="amount" value="${model.amount}" class="text  form-control input-sm number required" ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-procestype">处理方式:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-procestype" name="procestype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${procestypeItems}" var="item">          <option value="${item.key}" ${item.key==model.procestype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-amounttype">费用方式:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-amounttype" name="amounttype" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${amounttypeItems}" var="item">          <option value="${item.key}" ${item.key==model.amounttype? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ispoints">是否扣分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">    <select id="code-ispoints" name="ispoints" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${ispointsItems}" var="item">          <option value="${item.key}" ${item.key==model.ispoints? 'selected':''} >${item.value}</option>        </c:forEach>    </select></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-points">扣分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-points" type="text" name="points" value="${model.points}" class="text form-control input-sm "  ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='illegal-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

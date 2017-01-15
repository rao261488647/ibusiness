<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>专车司机信息列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'codeGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount:${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? '' : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		        'filter_LIKES_salesman': '${param.filter_LIKES_salesman}'
                ,'filter_LIKES_cellphone': '${param.filter_LIKES_cellphone}'
                ,'filter_LIKES_name': '${param.filter_LIKES_name}'
                ,'filter_LIKES_tosource': '${param.filter_LIKES_tosource}'
               	,'filter_LIKES_drivertype': '${param.filter_EQS_drivertype}'
            	,'filter_LIKES_carname': '${param.filter_LIKES_carname}'
           	    ,'filter_LIKES_remarks': '${param.filter_LIKES_remarks}'
           		,'filter_LIKES_status': '${param.filter_LIKES_status}'
          		,'filter_LIKES_carnum': '${param.filter_LIKES_carnum}'
           		,'filter_GED_contractvalidday': '${param.filter_GED_contractvalidday}'
            	,'filter_LED_contractvalidday': '${param.filter_LED_contractvalidday}'
            	,'filter_GED_dimissiondate': '${param.filter_GED_dimissiondate}'
                ,'filter_LED_dimissiondate': '${param.filter_LED_dimissiondate}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm',
	        exportUrl: 'special_driver_info-export.do'
		};

		var table;
		$(function() {
			table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
		    // 解决翻页乱码问题
		    table.changePageNo=function(pageNo) {
				if (pageNo != this.config.pageNo) {
					$("#pageNo").val(pageNo);
					
					$("form")[0].submit();
				}
				return false;
			};
		});
		
		// 群发
		function sendmass(url) {
			var obj = $('.selectedItem:checked');
			if (obj.length == 0) {
				alert("发送用户能为空,请选择用户～！");
				return false;
			}
			var s = "";
			for(var i=0; i<obj.length; i++){ 
				s+=obj[i].value+','; //如果选中，将value添加到变量s中 
			}
			url = url+"\'"+s+"\'";
			// =============================================================
			$("#modalInput").modal({
			    remote: url
			});
			$('#modalInput').modal('show');
		};
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10 col-md-10 col-sm-10">
	<!-- 查询条件 -->
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
          <div class="panel-body">
	          <div id="search" class="content content-inner">
				  <form name="cgForm" method="post" action="special_driver_info-list.do" class="form-inline">
				      <input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}">
	                  <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}">
				    <div class="form-group">
		                <label for="code_table_cellphone">司机电话:</label>
		                <input type="text" id="code_table_cellphone" name="filter_LIKES_cellphone" value="${param.filter_LIKES_cellphone}">
		                <label for="code_table_name">司机姓名:</label>
		                <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">
		                <label for="code_table_precartype">客户来源:</label>
		                <input type="text" id="code_table_tosource" name="filter_LIKES_tosource" value="${param.filter_LIKES_tosource}">
		                <label for="code_table_precartype">业务员:</label>
		                <input type="text" id="code_table_salesman" name="filter_LIKES_salesman" value="${param.filter_LIKES_salesman}">
		                <label >备注:</label>
		                <input type="text" id="code_table_remarks" name="filter_LIKES_remarks" value="${param.filter_LIKES_remarks}">
		                <label >车辆类型</label>
                                <select id="code-carname" name="filter_LIKES_carname" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${typenameItems}" var="item">          <option value="${item.key}" ${item.key==param.filter_LIKES_carname? 'selected':''} >${item.value}</option>        </c:forEach>    </select>
								</label>
		                <%-- <input type="text" id="code_table_carname" name="filter_LIKES_carname" value="${param.filter_LIKES_carname}"> --%>
		                <label >车牌号码:</label>
		                <input type="text" id="code_table_carnum" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}">
		                
		                 <button class="btn btn-primary btn-sm pull-right" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
		             </div>
		             <div class="form-group col-lg-12 col-md-12 col-sm-12">           
		                <div class="col-lg-3 col-md-3 col-sm-3">
		                    <label >司机类型:</label>
		                    <select id="code-drivertype" name="filter_EQS_drivertype" class="form-control input-sm required" >
                                <option value="" >请选择</option>
                                <option value="自营司机" ${'自营司机'==param.filter_EQS_drivertype? 'selected':''} >自营司机</option>
                                <option value="租车司机" ${'租车司机'==param.filter_EQS_drivertype? 'selected':''} >租车司机</option>
                                <option value="以租代购" ${'以租代购'==param.filter_EQS_drivertype? 'selected':''} >以租代购</option>
		                    </select>
		                </div>
		                <div class="col-lg-3 col-md-3 col-sm-3">
			                <label class="col-lg-5 col-md-5 col-sm-5" >专车状态:</label>
			                <div class="col-lg-6 col-lg-6 col-lg-6">
			                    <select id="code-status" name="filter_LIKES_status" class="form-control input-sm required" >
		                              <option value="" >请选择</option>
		                              <option value="正常" ${'正常'==param.filter_LIKES_status? 'selected':''} >正常</option>
		                              <option value="已解约" ${'已解约'==param.filter_LIKES_status? 'selected':''} >已解约</option>
			                    </select>
			                </div>
		                </div>
		                <div class="form-group col-lg-3 col-md-3 col-sm-3">
					        <label class="col-lg-4 col-md-4 col-sm-4 control-label" >合同生效日期:</label>
					        <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_GED_contractvalidday" value="${param.filter_GED_contractvalidday}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
		                </div>
		                <div class="form-group col-lg-3 col-md-3 col-sm-3">
			                <label class="col-lg-4 col-md-4 col-sm-4 control-label" >到:</label>
			                <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_LED_contractvalidday" value="${param.filter_LED_contractvalidday}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
	                    </div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-12">
					<div class="form-group col-lg-3 col-md-3 col-sm-3">
					        <label class="col-lg-4 col-md-4 col-sm-4 control-label" >解约日期:</label>
					        <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_GED_dimissiondate" value="${param.filter_GED_dimissiondate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
		            </div>
		            <div class="form-group col-lg-3 col-md-3 col-sm-3">
			            <label class="col-lg-4 col-md-4 col-sm-4 control-label" >到:</label>
			            <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_LED_dimissiondate" value="${param.filter_LED_dimissiondate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
	                </div>
					</div>
				 </form>
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">专车司机信息列表</h4></div>
       <div class="panel-body">
		    <div class="pull-left">
			    <a class="btn btn-primary btn-sm a-insert" href="special_driver_info-input.do" ><span class="glyphicon glyphicon-tasks"></span>新建</a>
			    <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()"><span class="glyphicon glyphicon-trash"></span>删除</button>
			    <button class="btn btn-primary btn-sm" onclick="table.exportExcel()"><span class="glyphicon glyphicon-export"></span>导出Excel</button>
			    <button id="sendmassid" class="btn btn-primary btn-sm" onclick="sendmass('special_record_mgr-mass-input.do?selectedItem=')" ><span class="glyphicon glyphicon-tasks"></span>群发通知</button>
				<!-- 
				<button id="sendmassid" class="btn btn-primary btn-sm" href="" onclick="sendmass('special_record_mgr-mass-input.do?selectedItem=')" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>群发通知</button>
			     -->
			</div>
			<div class="pull-right">
			  每页显示
			  <select class="m-page-size">
			    <option value="10">10</option>
			    <option value="20">20</option>
			    <option value="50">50</option>
			  </select>
			  条
			</div>
		    <div class="m-clear"></div>
	   </div>
	   <div class="content">
			<form id="gridForm" name="gridForm" method='post' action="special_driver_info-remove.do" class="m-form-blank">
			  <table id="codeGrid" class="table table-hover table-striped" style="table-layout:fixed;" >
			      <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			                <th width="60">姓名</th>
			                <th width="95">电话</th>
			                <th>司机类型</th>
			                
			                <th>合同期限</th>
			                <th>合同生效日</th>
			                <th width="90">车型名称</th>
			                <th>车牌号码</th>
			                <th>总押金</th>
			                <th width="45">状态</th>
			                <th>车辆押金</th>
			                <th>客户来源</th>
			                <th>业务员</th>
			                <th width="80">备注</th>
				        <th width="50">操作</th>
				      </tr>
				    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item" varStatus="status">  
					      <tr class="${status.index%2==1? 'active':''}">
					        	<td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
					            <td>${item.name}</td>
					            <td>${item.cellphone}</td>
					            <td>${item.drivertype}</td>
					            
					            <td>${item.contractterm}</td>
					            <td><fmt:formatDate value="${item.contractvalidday}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
					            <td>${item.carname}</td>
					            <td>${item.carnum}</td>
					            <td>${item.contractsumdeposit}</td>
					            <td>${item.status}</td>
					            <td>${item.cardeposit}</td>
					            <td>${item.tosource}</td>
					            <td>${item.salesman}</td>
					            <td width="80" nowrap style="overflow:hidden;text-overflow:ellipsis;" >${item.remarks}</td>
						        <td>
						          <a href="special_driver_info-input.do?id=${item.id}" class="a-update" >编辑</a>
						          <a href="special_driver_info-renew.do?id=${item.id}" class="a-update" >续约</a>
						        </td>
					      </tr>
					      </c:forEach>
					    </tbody>
					  </table>
					</form>
	        </div>
		  <article>
		    <div class="m-page-info pull-left">
			  共100条记录 显示1到10条记录
			</div>
			<div class="btn-group m-pagination pull-right">
			  <button class="btn btn-small">&lt;</button>
			  <button class="btn btn-small">1</button>
			  <button class="btn btn-small">&gt;</button>
			</div>
		    <div class="m-clear"></div>
	      </article>
	  </div>
	  <!-- 模态框 -->
	  <div id="modalInput" class="modal fade" tabindex="-1" style="display: none;" data-backdrop="static">
		  <div class="modal-dialog modal-lg">
			  <div class="modal-content" style="text-align: center;height: 600px">
			  </div>
		  </div>
	  </div>
	<!-- end of main -->
	</div>
  </body>
</html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>客户管理列表</title>
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
		        'filter_LIKES_remarks': '${param.filter_LIKES_remarks}'
                ,'filter_LIKES_unitname': '${param.filter_LIKES_unitname}'
                ,'filter_LIKES_customername': '${param.filter_LIKES_customername}'
                ,'filter_LIKES_customertobe': '${param.filter_LIKES_customertobe}'
                ,'filter_LIKES_customertype': '${param.filter_LIKES_customertype}'
               	,'filter_LIKES_salesman': '${param.filter_LIKES_salesman}'
               	,'filter_LIKES_customercell': '${param.filter_LIKES_customercell}'
             	,'filter_GED_createdatetime': '${param.filter_GED_createdatetime}'
             	,'filter_LED_createdatetime': '${param.filter_LED_createdatetime}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm',
	        exportUrl: 'customer_mgr-export.do'
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
				  <form name="cgForm" method="post" action="customer_mgr-list.do" class="form-inline">
				      <input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}">
	                  <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}">
	                  	<div class="row">
		                  <div class="col-md-3">
	                          <label class="labelSelect">客户姓名：
	                          	<input type="text" id="code_table_customername" name="filter_LIKES_customername" value="${param.filter_LIKES_customername}">
	                          </label>
	                      </div>
	                      <div class="col-md-2">
	                          <label class="labelSelect">客户状态:
							    	<select id="code-tosource" name="filter_LIKES_tosource" class="form-control input-sm required" >          
							    	   <option value="" >请选择</option>  
			                           <option value="电话邀约" ${'电话邀约'==param.filter_LIKES_tosource? 'selected':''} >电话邀约</option>
			                           <option value="司机来电" ${'司机来电'==param.filter_LIKES_tosource? 'selected':''} >司机来电</option>
			                           <option value="司机到场" ${'司机到场'==param.filter_LIKES_tosource? 'selected':''} >司机到场</option>
			                           <option value="已签约" ${'已签约'==param.filter_LIKES_tosource? 'selected':''} >已签约</option>
			                           <option value="已离职" ${'已离职'==param.filter_LIKES_tosource? 'selected':''} >已离职</option> 
			                        </select>
	                          </label>
	                      </div>
	                      <div class="col-md-2">
	                          <label class="labelSelect">客户来源:
							    	<select id="code-userstatus" name="filter_LIKES_userstatus" class="form-control input-sm required" >          
							    	   <option value="" >请选择</option>  
			                           <option value="网络" ${'网络'==param.filter_LIKES_userstatus? 'selected':''} >网络</option>
			                           <option value="地面广告" ${'地面广告'==param.filter_LIKES_userstatus? 'selected':''} >地面广告</option>
			                           <option value="司机介绍" ${'司机介绍'==param.filter_LIKES_userstatus? 'selected':''} >司机介绍</option>
			                           <option value="业务邀约" ${'已签约'==param.filter_LIKES_userstatus? 'selected':''} >业务邀约</option>
			                           <option value="其他" ${'其他'==param.filter_LIKES_userstatus? 'selected':''} >其他</option> 
			                        </select>
	                          </label>
	                      </div>
	                      <div class="col-md-5">
	                      </div>
	                  </div>
	                  <div class="row">
		                  <div class="col-md-3">
	                          <label class="labelSelect">业&nbsp;&nbsp;务&nbsp;&nbsp;员：
	                          	<input type="text" id="code_table_salesman" name="filter_LIKES_salesman" value="${param.filter_LIKES_salesman}">
	                          </label>
	                      </div>
	                      <div class="col-md-2">
	                          <label class="labelSelect">备注:
	                          	<input type="text" id="code_table_remarks" name="filter_LIKES_remarks" value="${param.filter_LIKES_remarks}">
	                          </label>
	                      </div>
	                      <div class="col-md-3">
	                          <label class="labelSelect">客户电话:
	                          	<input type="text" id="code_table_customercell" name="filter_LIKES_customercell" value="${param.filter_LIKES_customercell}">
	                          </label>
	                      </div>
	                  </div>
	                  <div class="row">
	                  	<div class="col-md-4">
	                          <div class="datepicker date">  
		                  		<label class="labelSelect">开始日期：</label>
	                            <span class="add-on">    
		                           	<input id="code-eventdate" type="text" name="filter_GED_createdatetime" value="${param.filter_GED_createdatetime}" placeholder="点击选择" class="form-control input-sm required" readonly >  
		                       	</span>  
	                          </div>
                        </div>
	                  </div>
	                  <div class="row">
	                      <div class="col-md-4">
	                          <div class="input-append datepicker date">  
		                       <label class="labelSelect">结束日期：</label>
	                           <span class="add-on">    
		                        	<input id="code-eventdate" type="text" name="filter_LED_createdatetime" value="${param.filter_LED_createdatetime}" placeholder="点击选择" class="form-control input-sm required" readonly >  
		                        </span> 
	                          </div>
	                      </div>
	                  </div>
                    <button class="btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
                    <input class="btn btn-primary btn-sm" type="reset" value="重置">
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">客户管理列表</h4></div>
       <div class="panel-body">
		    <div class="pull-left">
			    <button class="btn btn-primary btn-sm a-insert" href="customer_mgr-input.do" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>新建</button>
			    <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()"><span class="glyphicon glyphicon-trash"></span>删除</button>
			    <button class="btn btn-primary btn-sm" onclick="table.exportExcel()"><span class="glyphicon glyphicon-export"></span>导出Excel</button>
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
			<form id="gridForm" name="gridForm" method='post' action="customer_mgr-remove.do" class="m-form-blank">
			  <table id="codeGrid" class="table table-hover table-striped">
			      <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					                <th >客户姓名</th>
					                <th >客户电话</th>
					                <!-- 
					                <th >单位名称</th>
					                <th >客户分类</th>
					                 -->
					                <th >新建日期</th>
					                <th >业务员</th>
					                <th >客户来源</th>
					                <th >客户归属</th>
					                <th >备注</th>
				        <th width="30">&nbsp;</th>
				      </tr>
				    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item" varStatus="status">  
					      <tr class="${status.index%2==1? 'active':''}">
					        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						            <td>${item.customername}</td>
						            <td>${item.customercell}</td>
						            <!-- 
						            <td>${item.unitname}</td>
						            <td>${item.customertype}</td>
						             -->
						            <td><fmt:formatDate value="${item.createdatetime}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.salesman}</td>
						            <td>${item.tosource}</td>
						            <td>${item.customertobe}</td>
						            <td>${item.remarks}</td>
					        <td>
					          <a href="customer_mgr-input.do?id=${item.id}" class="a-update" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-pencil"></span></a>
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

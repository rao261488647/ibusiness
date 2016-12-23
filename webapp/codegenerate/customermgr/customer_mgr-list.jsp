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
				    <div class="form-group">
				       <div class="col-lg-12 col-md-12 col-sm-12 " >
				       <!-- 
					       <div class="form-group col-lg-3 col-md-3 col-sm-3">
					            <label class="col-lg-4 col-md-4 col-sm-4 control-label" >客户分类:</label>
		                        <div class="col-lg-8 col-md-8 col-sm-8"> 
		                         <select id="code-customertype" name="filter_LIKES_customertype" class="form-control input-sm required" >          <option value="" >请选择</option>  
	                                <option value="单位" ${'单位'==param.filter_LIKES_customertype? 'selected':''} >单位</option>
	                                <option value="个人" ${'个人'==param.filter_LIKES_customertype? 'selected':''} >个人</option> 
		                            </select>
		                        </div>
		                    </div>
		                     -->
	                        <div class="form-group col-lg-3 col-md-3 col-sm-3">
						        <label class="col-lg-4 col-md-4 col-sm-4 control-label" >开始时间:</label>
						        <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="filter_GED_createdatetime" value="${param.filter_GED_createdatetime}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
			                </div>
			                <div class="form-group col-lg-3 col-md-3 col-sm-3">
				                <label class="col-lg-4 col-md-4 col-sm-4 control-label" >结束时间:</label>
				                <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="filter_LED_createdatetime" value="${param.filter_LED_createdatetime}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
		                    </div>
		                    
		                    <button class="btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
	                    </div>
	                    <div class="col-lg-12 col-md-12 col-sm-12 " >  
	                    <!-- 
	                        <label class="col-lg-1 col-md-1 col-sm-1 control-label" >单位:</label>
                            <input class="col-lg-1 col-md-1 col-sm-1" type="text" id="code_table_unitname" name="filter_LIKES_unitname" value="${param.filter_LIKES_unitname}">
				          -->
			                <label class="col-lg-1 col-md-1 col-sm-1 control-label">客户姓名:</label>
			                <input class="col-lg-1 col-md-1 col-sm-1" type="text" id="code_table_customername" name="filter_LIKES_customername" value="${param.filter_LIKES_customername}">
				            
				            <label class="col-lg-1 col-md-1 col-sm-1 control-label">业务员:</label>
			                <input class="col-lg-1 col-md-1 col-sm-1" type="text" id="code_table_salesman" name="filter_LIKES_salesman" value="${param.filter_LIKES_salesman}">
				            <label class="col-lg-1 col-md-1 col-sm-1 control-label">备注:</label>
			                <input class="col-lg-1 col-md-1 col-sm-1" type="text" id="code_table_remarks" name="filter_LIKES_remarks" value="${param.filter_LIKES_remarks}">
					        <label class="col-lg-1 col-md-1 col-sm-1 control-label">客户电话:</label>
			                <input class="col-lg-2 col-md-2 col-sm-2" type="text" id="code_table_customercell" name="filter_LIKES_customercell" value="${param.filter_LIKES_customercell}">
					        
					        <div class="col-lg-1 col-md-1 col-sm-1" ></div>
					        
				         </div>   
					</div>
				 </form>
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

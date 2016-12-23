<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>财务结算页面列表</title>
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
		        'filter_LIKES_id': '${param.filter_LIKES_id}'
                ,'filter_LIKES_customername': '${param.filter_LIKES_customername}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm',
	        exportUrl: 'financial_settlement-export.do'
		};

		var table;
		$(function() {
			table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
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
			  <form name="cgForm" method="post" action="financial_settlement-list.do" class="form-inline">
			    <div class="form-group col-lg-12 col-md-12 col-sm-12 ">
	                <label class="col-lg-2 col-md-2 col-sm-2" >司机姓名:</label>
	                <div class="col-lg-2 col-md-2 col-sm-2">
	                <input class="form-control input-sm " type="text" id="code_table_customername" name="filter_LIKES_customername" value="${param.filter_LIKES_customername}">
	                </div>
	                <label class="col-lg-2 col-md-2 col-sm-2" >结算类别:</label>
	                <div class="col-lg-2 col-md-2 col-sm-2"> <select id="code-setbusinesstype" name="filter_LIKES_setbusinesstype" class="form-control input-sm required" > <option value="" >请选择</option> <c:forEach items="${setbusinesstypeItems}" var="item"> <option value="${item.key}" ${item.key==param.filter_LIKES_setbusinesstype? 'selected':''} >${item.value}</option> </c:forEach> </select></div>
                    
                    <button class="btn btn-primary btn-sm col-lg-1 col-md-1 col-sm-1" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
                </div>
                <div class="form-group col-lg-12 col-md-12 col-sm-12 ">
                    <!-- 
                    <label class="col-lg-2 col-md-2 col-sm-2" >交款日期:</label>
                    <div class="col-lg-2 col-md-2 col-sm-2"> <div class="input-append datepicker date">  <span class="add-on"> <input id="code-eventdate" type="text" name="filter_LIKES_eventdate" value="${param.filter_LIKES_eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly > </span> </div></div>
	                 -->
	                <label class="col-lg-2 col-md-2 col-sm-2" >所属月份:</label>
	                <script type="text/javascript">$(".form_datetime").datepicker({format: 'yyyy-mm'});</script>
	                <div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker_yyyymm date">  <span class="add-on">    <input id="code-monthdate" type="text" name="filter_LIKES_monthdate" value="${param.filter_LIKES_monthdate}" placeholder="点击选择" class="form-control input-sm required" >  </span>  </div></div>
				    
				    <label >司机类别:</label>
	                <input class="form-control input-sm " type="text" id="code_table_driverclass" name="filter_LIKES_driverclass" value="${param.filter_LIKES_driverclass}">
	                <label >司机车辆型号:</label>
	                <input class="form-control input-sm " type="text" id="code_table_carmodel" name="filter_LIKES_carmodel" value="${param.filter_LIKES_carmodel}">
				</div>
			 </form>
		  </div>
	   </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">财务结算页面列表</h4></div>
       <div class="panel-body">
		    <div class="pull-left">
		    <!-- 
			    <button class="btn btn-primary btn-sm a-insert" href="financial_settlement-input.do" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>新建</button>
			 -->
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
			<form id="gridForm" name="gridForm" method='post' action="financial_settlement-remove.do" class="m-form-blank">
			  <table id="codeGrid" class="table table-hover table-striped">
			      <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					                <th >司机名称</th>
					                <th >司机电话</th>
									<th >司机类别</th>
					                <th >司机车辆型号</th>
					                <th >车牌号</th>
					                <th >结算业务类型</th>
					                <th>租金所属月份</th>
					                <th>结算方式</th>
					                <th>凭证号码</th>
					                <th >日期</th>
					                <th >金额</th>
				        <th width="30">&nbsp;</th>
				      </tr>
				    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item" varStatus="status">  
					      <tr class="${status.index%2==1? 'active':''}">
					        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						            <td>${item.customername}</td>
						            <td>${item.telephone}</td>
									<td>${item.driverclass}</td>
						            <td>${item.carmodel}</td>
						            <td>${item.carnum}</td>
						            <td>${item.setbusinesstype}</td>
						            <td>${item.monthdate}</td>
						            <td>${item.paymsg}</td>
						            <td>${item.documentnum}</td>
						            <td><fmt:formatDate value="${item.eventdate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.amount}</td>
					        <td>
					          <a href="financial_settlement-input.do?id=${item.id}" class="a-update" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-pencil"></span></a>
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

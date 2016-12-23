<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>司机流水列表</title>
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
		        'filter_GED_eventdate': '${param.filter_GED_eventdate}'
	        	,'filter_LED_eventdate': '${param.filter_LED_eventdate}'
                ,'filter_LIKES_telephone': '${param.filter_LIKES_telephone}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm',
	        exportUrl: 'flow_driver_financial-export.do'
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
				  <form name="cgForm" method="post" action="flow_driver_financial-list.do" class="form-inline">
				    <div class="form-group col-lg-12 col-md-12 col-sm-12">
				        <div class="form-group col-lg-4 col-md-4 col-sm-4">
					        <label class="col-lg-4 col-md-4 col-sm-4 control-label" >开始时间:</label>
					        <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="filter_GED_eventdate" value="${param.filter_GED_eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
		                </div>
		                <div class="form-group col-lg-4 col-md-4 col-sm-4">
			                <label class="col-lg-4 col-md-4 col-sm-4 control-label" >结束时间:</label>
			                <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="filter_LED_eventdate" value="${param.filter_LED_eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
	                    </div>
	                    <div class="form-group col-lg-4 col-md-4 col-sm-4">
		                    <label class="col-lg-4 col-md-4 col-sm-4 control-label" >平台类型:</label>
			                <div class="col-lg-8 col-md-8 col-sm-8">
			                    <select id="code-sex" name="filter_LIKES_platform" class="form-control input-sm required" >
			                        <option value="" >请选择</option>
			                        <option value="滴滴" ${'滴滴'==param.filter_LIKES_platform? 'selected':''} >滴滴</option>
			                        <option value="快" ${'快'==param.filter_LIKES_platform? 'selected':''} >快滴</option>
			                        <option value="优步" ${'优步'==param.filter_LIKES_platform? 'selected':''} >优步</option>
			                        <option value="易到" ${'易到'==param.filter_LIKES_platform? 'selected':''} >易到用车</option>
			                    </select>
			                </div>
		                </div>
	                </div>
				    <!-- -->
				    <div class="form-group col-lg-12 col-md-12 col-sm-12">
					    <label class="col-lg-1 col-md-1 col-sm-1 control-label" >电话:</label>
		                <input class="col-lg-3 col-md-3 col-sm-3 form-control input-sm " type="text" name="filter_LIKES_telephone" value="${param.filter_LIKES_telephone}">
					     <div class="col-lg-1 col-md-1 col-sm-1"></div>
					    <button class="col-lg-1 col-md-1 col-sm-1 btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
					</div>
				 </form>
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">司机流水列表</h4></div>
       <div class="panel-body">
		    <div class="pull-left">
			    <button class="btn btn-primary btn-sm a-insert" href="flow_driver_financial-input.do" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>新建</button>
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
			<form id="gridForm" name="gridForm" method='post' action="flow_driver_financial-remove.do" class="m-form-blank">
			  <table id="codeGrid" class="table table-hover table-striped">
			      <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					                <th>日期</th>
					                <th>客户</th>
					                <th>电话</th>
					                <th>交易编号</th>
					                <th>平台</th>
					                <th>金额</th>
					                <th>车牌号</th>
					                <th>备注</th>
				        <th width="30">&nbsp;</th>
				      </tr>
				    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item" varStatus="status">  
					      <tr class="${status.index%2==1? 'active':''}">
					        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						            <td><fmt:formatDate value="${item.eventdate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.customername}</td>
						            <td>${item.telephone}</td>
						            <td>${item.transactionno}</td>
						            <td>${item.platform}</td>
						            <td>${item.amount}</td>
						            <td>${item.carnum}</td>
						            <td>${item.remark}</td>
					        <td>
					          <a href="flow_driver_financial-input.do?id=${item.id}" class="a-update" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-pencil"></span></a>
					        </td>
					      </tr>
					      </c:forEach>
					    </tbody>
					  </table>
					</form>
	        </div>
		  <article>
		    <div class="col-lg-12 col-md-12 col-sm-12" >
			    <div class="m-page-info pull-left col-lg-3 col-md-3 col-sm-3">
				      共100条记录 显示1到10条记录
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 pull-left">
				      当前页合计金额：${pagetotalAmount}
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 pull-left">
				      总合计金额：${totalAmount}
				</div>
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

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>车辆库存管理列表</title>
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
                ,'filter_LIKES_carnum': '${param.filter_LIKES_carnum}'
                
               	,'filter_GED_infactorydate': '${param.filter_GED_infactorydate}'
                ,'filter_LED_infactorydate': '${param.filter_LED_infactorydate}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm',
	        exportUrl: 'car_mgr-export.do'
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
				  <form name="cgForm" method="post" action="car_mgr-list.do" class="form-inline">
				      <input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}">
	                  <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}">
				    <div class="form-group">
		                <label for="code_table_carnum">车牌号:</label>
		                <input type="text" id="code_table_carnum" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}">
		                <label for="code_table_typename">型号名称:</label>
		                <input type="text" id="code_table_typename" name="filter_LIKES_typename" value="${param.filter_LIKES_typename}">
		                <label for="code_table_intype">入库类型:</label>
		                <input type="text" id="code_table_intype" name="filter_LIKES_intype" value="${param.filter_LIKES_intype}">
		                <label for="code_table_savefactory">存放仓库:</label>
		                <input type="text" id="code_table_savefactory" name="filter_LIKES_savefactory" value="${param.filter_LIKES_savefactory}">
					    <button class="btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
                        <div class="col-lg-4 col-md-4 col-sm-4">
	                        <label >备注:</label>
			                <input type="text" id="code_table_remark" name="filter_LIKES_remark" value="${param.filter_LIKES_remark}">
		                
		                    <label for="code_table_carstatus">车辆状态:</label>
		                    <select id="code-intype" name="filter_LIKES_carstatus" class="form-control input-sm required" >
                                <option value="" >请选择</option><option value="在库" ${'在库'==param.filter_LIKES_carstatus? 'selected':''} >在库</option><option value="已出库" ${'已出库'==param.filter_LIKES_carstatus? 'selected':''} >已出库</option>
                            </select>
                        </div>
                        <!-- //////////////////// -->
                        <div class="form-group col-lg-5 col-md-5 col-sm-5">
					        <label class="col-lg-2 col-md-2 col-sm-2 control-label" >入库日期:</label>
					        <div class="col-lg-4 col-md-4 col-sm-4">  <div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_GED_infactorydate" value="${param.filter_GED_infactorydate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
			                <label class="col-lg-1 col-md-1 col-sm-1 control-label" >到:</label>
			                <div class="col-lg-4 col-md-4 col-sm-4">  <div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_LED_infactorydate" value="${param.filter_LED_infactorydate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
	                    </div>
	                    
					</div>
				 </form>
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">车辆库存管理列表</h4></div>
       <div class="panel-body">
		    <div class="pull-left">
			    <button class="btn btn-primary btn-sm a-insert" href="car_mgr-input.do" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>新建</button>
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
			<form id="gridForm" name="gridForm" method='post' action="car_mgr-remove.do" class="m-form-blank">
			  <table id="codeGrid" class="table table-hover table-striped">
			      <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					                <th >型号名称</th>
					                <th >车牌号</th>
					                <th >车架号</th>
					                <th >入库类型</th>
					                <th >车辆出厂日期</th>
					                <th >入库日期</th>
					                <th >存放仓库</th>
					                <th >车辆状态</th>
					                <th >指标公司</th>
					                <th >备注</th>
				        <th width="30">&nbsp;</th>
				      </tr>
				    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item" varStatus="status">  
					      <tr class="${status.index%2==1? 'active':''}">
					        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						            <td>${item.typename}</td>
						            <td>${item.carnum}</td>
						            <td>${item.carframenum}</td>
						            <td>${item.intype}</td>
						            <td><fmt:formatDate value="${item.caroutfaydate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td><fmt:formatDate value="${item.infactorydate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.savefactory}</td>
						            <td>${item.carstatus}</td>
						            <td>${item.targetcompany}</td>
						            <td>${item.remark}</td>
					        <td>
					          <a href="car_mgr-input.do?id=${item.id}" class="a-update" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-pencil"></span></a>
					        </td>
					      </tr>
					      </c:forEach>
					    </tbody>
					  </table>
					</form>
	        </div>
		  <article>
		    <div class="col-lg-8 col-md-8 col-sm-8" > 车辆总数:${page.totalCount} 台 </div>
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

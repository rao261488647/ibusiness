<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>车辆借出管理列表</title>
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
		        'filter_GED_date': '${param.filter_GED_date}'
		         ,'filter_LED_date': '${param.filter_LED_date}'
		         ,'filter_LIKES_status': '${param.filter_LIKES_status}'
		        ,'filter_LIKES_specialname': '${param.filter_LIKES_specialname}'
		        ,'filter_LIKES_carnum': '${param.filter_LIKES_carnum}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm',
	        exportUrl: 'export.do'
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
				<form name="cgForm" method="post" action="list.do" class="form-inline">
						<div class="col-md-3">
                                <label class="labelSelect">开始日期：
                                <div><div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_GED_date" value="${param.filter_GED_date}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                                结束日期：
                                <div><div class="input-append datepicker date">  <span class="add-on">    <input type="text" name="filter_LED_date" value="${param.filter_LED_date}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label class="labelSelect">状态：
                                    <select id="code-status" name="filter_LIKES_status" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${statusItems}" var="item">          <option value="${item.key}" ${item.key==param.filter_LIKES_status? 'selected':''} >${item.value}</option>        </c:forEach>    </select>
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label >借出人:</label>
			                <input type="text" id="code_table_specialname" name="filter_LIKES_specialname" value="${param.filter_LIKES_specialname}">
                            </div>
                            <div class="col-md-4">
                               <label >车牌号:</label>
			                  <input type="text" id="code_table_carnum" name="filter_LIKES_carnum" value="${param.filter_LIKES_carnum}">
                            </div>
                            <button class="btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
				 </form>
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">车辆借出管理列表</h4></div>
       <div class="panel-body">
		    <div class="pull-left">
			    <button class="btn btn-primary btn-sm a-insert" href="input.do" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-tasks"></span>新建</button>
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
			<form id="gridForm" name="gridForm" method='post' action="remove.do" class="m-form-blank">
			  <table id="codeGrid" class="table table-hover table-striped">
			      <thead>
				      <tr>
				        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
					                <th class="sorting">日期</th>
					                <th class="sorting">借车人</th>
					                <th class="sorting">车型</th>
					                <th class="sorting">车牌号</th>
					                <th class="sorting">借出日期</th>
					                <th class="sorting">状态</th>
					                <th class="sorting">还回日</th>
					                <th class="sorting">备注</th>
				        <th width="30">&nbsp;</th>
				      </tr>
				    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item" varStatus="status">  
					      <tr class="${status.index%2==1? 'active':''}">
					        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						            <td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.specialname}</td>
						            <td>${item.typename}</td>
						            <td>${item.carnum}</td>
						            <td><fmt:formatDate value="${item.loandate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.status}</td>
						            <td><fmt:formatDate value="${item.backdate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
						            <td>${item.remark}</td>
					        <td>
					          <a href="input.do?id=${item.id}" class="a-update" data-target="#modalInput" data-toggle="modal" data-database="true"><span class="glyphicon glyphicon-pencil"></span></a>
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

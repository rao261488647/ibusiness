<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>截至今日各车型库存盘点</title>
    <%@include file="/common/center.jsp"%>
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
				  <form name="cgForm" method="post" action="count3.do" class="form-inline">
				<div class="form-group col-lg-12 col-md-12 col-sm-12">
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
					    <label class="col-lg-4 col-md-4 col-sm-4 control-label" >时间:</label>
					    <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="filter_GED_date" value="${param.filter_GED_date}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div>
					    </div>
		            </div>
	           </div>
	            <div class="form-group col-lg-12 col-md-12 col-sm-12">
			    <button class="col-lg-1 col-md-1 col-sm-1 btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
				</div>
				 </form>
				 <button class="btn btn-success btn-dialog" id="exportBtn">导出记录</button>
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">截至今日各车型库存盘点</h4></div>
	   <div class="content">
<table class="table table-bordered" style="width:60%">
   <tbody>
      <tr>
         <td>车型</td>
         <td>数量</td>
         <td>已出库</td>
         <td>待出库</td>
         <td>维修厂</td>
         <td>外借</td>
         <td>出车率</td>
      </tr>
      <c:forEach items="${customerCountList1}" var="item" varStatus="status">  
	     <tr class="">
	         <td>${item.name}</td>
	         <td>${item.count1}</td>
	         <td>${item.count2}</td>
	         <td>${item.count3}</td>
	         <td>${item.count4}</td>
	         <td>${item.count5}</td>
	         <td><fmt:formatNumber value="${item.count1==0?0:(item.count2*100/item.count1)}" pattern="#,###.##" />%</td>
	     </tr>
	  </c:forEach>
      <tr>
         <td>合计</td>
         <td>${customerCount10.count1}</td>
         <td>${customerCount10.count2}</td>
         <td>${customerCount10.count3}</td>
         <td>${customerCount10.count4}</td>
         <td>${customerCount10.count5}</td>
         <td>${customerCount10.count6}%</td>
      </tr>
   </tbody>
</table>
		</div>
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
        <script type="text/javascript">
	// 导出数据
	$('#exportBtn').click(function(){
		var $form = $("#cgForm");
		var data = $form.serializeArray();
		var url = '${ctx}/count/exportcount3.do?filter_GED_date=' + $('#code-eventdate').val();
		window.open(url);
	});
    </script>
</html>

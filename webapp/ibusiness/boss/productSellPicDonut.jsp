<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>产品销售额饼图</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript" src="${ctx}/plugin/amcharts/amcharts.js"></script>
    <script type="text/javascript" src="${ctx}/plugin/amcharts/amstock.js"></script>
    
    <script type="text/javascript">
	    // 
	    var chartData = [];
	    var chart = null;
	    var dataSet = null;
	    // 调用amChart

	    // 取得数据
		function getChartData() {
			$.ajax({
				url: "${ctx}/produce-schedule-chart/show-pieDonut.do?",
				data: {
	    		},
	    		dataType : 'json',
				success:function(json){
					// 循环
					$.each(json, function(i, n) {
							chartData.push({
								productName : n.productName,
								totalAmount : n.totalAmount
							});
					});
					// 用取得的数据创建amchart
					createAmChart();
				}
			});
		}
	    // 曲线图数据查询
		function ajaxRequest() {
	    	
	    }
        
	     // 实例化AmCharts对象
	     var createAmChart = function createAmChart() {
	     	if (null == chart) {
	     	    // PIE CHART
                chart = new AmCharts.AmPieChart();
                // title of the chart
                chart.addTitle("产品销售额", 16);
                chart.dataProvider = chartData;
                chart.titleField = "productName";
                chart.valueField = "totalAmount";
                chart.sequencedAnimation = true;
                chart.startEffect = "elastic";
                chart.innerRadius = "30%";
                chart.startDuration = 2;
                chart.labelRadius = 15;

                // the following two lines makes the chart 3D
                chart.depth3D = 10;
                chart.angle = 15;

                // WRITE                                 
	     	   chart.write('chartdiv');
	     	} else {
	     		dataSet.dataProvider = chartData;
	     		// 在你的数据变化或重新设置dataProvider。调用此方法后，图表将分析数据和重绘。
	     		chart.validateData();
	     		// 缩小绘制区更新
	     		chart.zoomOut();
	     	}
	     };
	     
	     // 调用
	     getChartData();
		 
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
	    <div class="col-lg-1"></div>
		<!-- start of main -->
		<div class="panel panel-default col-lg-10">
		<!-- 查询条件 -->
	        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
	        <div class="panel-body">
		          <div id="search" class="content content-inner">
					  <form name="cgForm" method="post" action="list-chart.do" class="form-inline">
					    <div class="form-group">
			                <label for="code_table_name">产品名:</label>
			                <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">
						    <button class="btn btn-primary btn-sm" onclick="document.cgForm.submit()">查询</button>
						</div>
					 </form>
				  </div>
			</div>
	           
		   <div class="content">
		           <div id="chartdiv" style="width:100%; height:500px;"></div>
		   </div>
	  </div>
	<!-- end of main -->
	</div>
  </body>
</html>

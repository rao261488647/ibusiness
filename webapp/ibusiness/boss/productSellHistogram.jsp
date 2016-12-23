<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>产品生产柱形图</title>
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
				url: "${ctx}/produce-schedule-chart/show-histogram.do",
				data: {
	    		},
	    		dataType : 'json',
				success:function(json){
					// 循环
					var colorIndex=0;
					$.each(json, function(i, n) {
							chartData.push({
								productName : n.productName,
								produceNum : n.produceNum,
								COLOR:getRandomColor()
							});
							colorIndex++;
					});
					// 用取得的数据创建amchart
					createAmChart();
				}
			});
		}
        
		// 实例化AmCharts对象
		var createAmChart = function createAmChart() {
		    chart = new AmCharts.AmSerialChart();
		    chart.startDuration = 1;
		    chart.dataProvider = chartData;
		    chart.categoryField = "productName";
		         
		    //Y坐标深度
		    chart.depth3D = 15;
		    //角度
		    chart.angle = 50;
		    
		    // AXES category
		    var categoryAxis = chart.categoryAxis;
		    //X坐标刻度的高度值
		    categoryAxis.labelRotation = 90;
		    categoryAxis.dashLength = 5;
		    categoryAxis.gridPosition = "start";
		    // value
		    var valueAxis = new AmCharts.ValueAxis();
		    valueAxis.dashLength = 5;
		    chart.addValueAxis(valueAxis);
		    
		    // 
		    var graph = new AmCharts.AmGraph();
		    graph.type = "column";
		    graph.valueField = "produceNum";
		    graph.colorField = "COLOR";//
		    graph.balloonText = "[[category]]: [[value]]";
		    graph.type = "column";
		    graph.title = '产品生产数';
		    //柱子的边线
		    graph.lineAlpha = 0;
		    //是否填充珠子的颜色
		    graph.fillAlphas = 1;
		    chart.addGraph(graph);
		    
		    // 显示选项                
            var legend = new AmCharts.AmLegend();
            chart.addLegend(legend);
            
		    //找到amcharts-panel容器的'amcharts-panel-body'子节点的id值
		    chart.write('chartdiv');
			
		};
		//获得随机颜色
		var getRandomColor = function getRandomColor(){
		  return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).slice(-6);
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
	        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">产品销售柱形图查询</h4></div>
	        <div class="panel-body">
		          <div id="search" class="content content-inner">
					  <form name="cgForm" method="post" action="list-productSell.do" class="form-inline">
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

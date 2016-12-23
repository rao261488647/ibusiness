<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>内存监控曲线</title>
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
				url: "${ctx}/common/show-memory-chart.do?",
				data: {
	    		},
	    		dataType : 'json',
				success:function(json){
					// 循环
					$.each(json, function(i, n) {
						    // 转换日期类型
						    var eventDate = n.eventTime.replace(/-/g, "/");
						    var newdate = new Date(eventDate);
						    // 
							chartData.push({
								eventTime : newdate,
								totalMemory : n.totalMemory,
								maxMemory : n.maxMemory,
								freeMemory : n.freeMemory
							});
					});
					// 大于1000个点清空一次
					if(chartData.length > 500){
						chartData = [];
					}
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
	     	// AmStockChart is a main class Stock chart.
	     	if (null == chart) {
	     		chart = new AmCharts.AmStockChart();
	     		chart.pathToImages = "${ctx}/plugin/amcharts/imag/";
	     		// 如果设置为TRUE 更新数据集结束时更新视图的范围。
	     		chart.glueToTheEnd = true;
	
	     		// 设置分类轴
	     		var categoryAxesSettings = new AmCharts.CategoryAxesSettings();
	     		// 指定时间最短的数据。fff-毫秒，ss-秒，mm-分钟，hh-小时，DD-天，MM-月，YYYY-年。
	     		categoryAxesSettings.minPeriod = "ss";
	     		chart.categoryAxesSettings = categoryAxesSettings;
	
	     		// DataSets数据对象持有的所有数据信息。
	     		if (null == dataSet) {
	     			dataSet = new AmCharts.DataSet();
	     		}
	     		dataSet.fieldMappings = [{
	     					fromField : "eventTime",
	     					toField : "eventTime"
	     				}, {
	     					fromField : "totalMemory",
	     					toField : "totalMemory"
	     				}, {
	     					fromField : "maxMemory",
	     					toField : "maxMemory"
	     				}, {
	     					fromField : "freeMemory",
	     					toField : "freeMemory"
	     				}];
	     		// 设置数据
	     		dataSet.dataProvider = chartData;
	     		dataSet.categoryField = "eventTime";
	
	     		chart.dataSets = [dataSet];
	     		chart.mainDataSet = dataSet;
	
	     		// 继承关系StockPanel → AmSerialChart → AmRectangularChart → AmCoordinateChart → AmChart
	     		// StockPanel类创建stock panels (charts). AmStockChart可以有多个Stock panels.                                                
	     		var stockPanel = new AmCharts.StockPanel();
	     		// 指定是否该面板将显示分类轴。
	     		stockPanel.showCategoryAxis = true;
	     		stockPanel.title = "内存监控曲线";
	     		stockPanel.eraseAll = false;
	     		// stockGraph在stockPanel上显示图.
	     		// 最大可使用内存
	     		var graph = new AmCharts.StockGraph();
	     		graph.lineColor = '#FF4500';
	     		graph.useDataSetColors = false;
	     		graph.lineThickness = 2;
	     		graph.valueField = "maxMemory";
	     		graph.title = '最大可使用内存';
	     		stockPanel.addStockGraph(graph);
	     		// 可使用内存数
	     		var graph1 = new AmCharts.StockGraph();
	     		graph1.lineColor = '#009900';
	     		graph1.useDataSetColors = false;
	     		graph1.lineThickness = 2;
	     		graph1.valueField = "totalMemory";
	     		graph1.title = '可使用内存数';
	     		stockPanel.addStockGraph(graph1);
	     		// 剩余内存数
	     		var graph2 = new AmCharts.StockGraph();
	     		graph2.lineColor = '#296CA9';
	     		graph2.useDataSetColors = false;
	     		graph2.lineThickness = 2;
	     		graph2.valueField = "freeMemory";
	     		graph2.title = '剩余内存数';
	     		stockPanel.addStockGraph(graph2);
	     		
	
	     		// stockLegend是图表图例。
	     		var stockLegend = new AmCharts.StockLegend();
	     		stockPanel.stockLegend = stockLegend;
	     		stockPanel.drawingIconsEnabled = true;
	     		chart.panels = [stockPanel];
	
	     		// 滚动条设置
	     		var scrollbarSettings = new AmCharts.ChartScrollbarSettings();
	     		scrollbarSettings.graph = graph;
	     		// 只有在释放的时候更新
	     		//	scrollbarSettings.updateOnReleaseOnly = true;
	     		chart.chartScrollbarSettings = scrollbarSettings;
	
	     		// 图光标设置
	     		// 如果你改变一个属性初始化后的图，你应该告诉给stockchart
	     		// 如果没有指定的默认值，默认值将使用chartcursor类。
	     		var cursorSettings = new AmCharts.ChartCursorSettings();
	     		cursorSettings.valueBalloonsEnabled = true;
	     		chart.chartCursorSettings = cursorSettings;
	
	     		// 显示数据范围
	     		var periodSelector = new AmCharts.PeriodSelector();
	     		periodSelector.position = "bottom";
	     		// 是否显示查询框
	     		periodSelector.inputFieldsEnabled = false;
	     		// 显示全部数据提示文本
	     		periodSelector.periodsText = "显示数据范围：";
	     		periodSelector.periods = [{
	     					period : "MAX",
	     					label : "全显示"
	     				}];
	     		chart.periodSelector = periodSelector;
	     	    //找到amcharts-panel容器的‘amcharts-panel-body’子节点的id值
	     	   chart.write('chartdiv');
	     	} else {
	     		dataSet.dataProvider = chartData;
	     		// 在你的数据变化或重新设置dataProvider。调用此方法后，图表将分析数据和重绘。
	     		chart.validateData();
	     		// 缩小绘制区更新
	     		chart.zoomOut();
	     	}
	     };
	     
	     /*
	 	  *	设定定时刷新
	 	  */
	 	setInterval(function(){
	 		// 调用
	 		getChartData();
	 		//	getDataByExchanger();
	 	}, 3000);
		 
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <div class="row">
	    <div class="col-lg-1"></div>
		<!-- start of main -->
		<div class="panel panel-default col-lg-10">
	        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">内存监控</h4></div>
	        <div class="panel-body">
		          <div id="search" class="content content-inner">
					    <div class="form-group">
						    <button class="btn btn-primary btn-sm" onclick="history.back();">返回</button>
						</div>
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

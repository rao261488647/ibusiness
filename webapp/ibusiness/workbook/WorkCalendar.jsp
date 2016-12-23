<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作日历</title>
<jsp:include page="./jsps/base/head_base.jsp"></jsp:include>
<style type="text/css">
	th,td{text-align:center;}
	.popover th,.popover td{text-align:center;width:25px;font-size:8px;height:25px;}
	.popover{position:static;margin:10px;}
	#yearLargeScope td,#yearSmallScope td{width:100px;height:50px;cursor:hand}
	#yearLargeScope,#yearSmallScope{display:none}
	div.modal-dialog{width:440px;}
</style>
</head>
<body>
	<div class="container">
		<table>
			<thead>
				<th colspan="7">
					<a type="button" class="btn btn-link" data-toggle="tooltip" data-placement="right" title="双击设置年度">
						当前工作日历的年份是<span id="setCalendarYear"></span>年
					</a>
				</th>
			</thead>
			<tbody>
			<c:forEach var="row" begin="1" end="3" step="1">
				<tr>
					<c:forEach var="column" begin="1" end="4" step="1">
						<td>
							<div class="popover down">
								<div class="popover-content">
									<table>
										<thead>
											<th colspan="7" bgcolor="#FBEFB6" ><span id="titleYear" class="year"></span>年${(row-1)*4+column}月</th>
										</thead>
										<tbody>
											<tr>
												<th>日</th>
												<th>一</th>
												<th>二</th>
												<th>三</th>
												<th>四</th>
												<th>五</th>
												<th>六</th>
											</tr>
											<c:forEach var="calendarRow" begin="1" end="6" step="1">
												<tr bgcolor="#FDF7D9">
													<c:forEach var="dayNo" begin="1" end="7" step="1">
														<td  class="dayNo" id="${(row-1)*4+column}-${(calendarRow-1)*7+dayNo}"></td>
													</c:forEach>												
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- 年份设置模态窗口 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title" id="myModalLabel" style="text-align:center">
							<a style="font-size:18px;" type="button" class="btn btn-link" data-toggle="tooltip" data-placement="right" title="双击选择年度范围">
							</a>
						</h4>
					</div>
				<div class="modal-body">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveYear" type="button" class="btn btn-primary">确定</button>
				</div>
				</div>
			</div>
		</div>
		<!-- 年份设置模态窗口 -->
		<!-- 小范围年份范围（当前日历选择年份所在的十二年）-->
		<table id="yearSmallScope">
			<c:forEach var="yearSmallScopeRow" begin="1" end="3" step="1">
				<tr>
					<c:forEach var="yearSmallScopeColumn" begin="1" end="4" step="1">
						<td>
							<a id="ss-${(yearSmallScopeRow-1)*4+yearSmallScopeColumn}" class="btn btn-link"></a>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<!-- 大范围年份范围（1990-2099）-->
		<table id="yearLargeScope">
			<c:forEach var="yearLargeScopeRow" begin="1" end="5" step="1">
				<tr>
					<c:forEach var="yearLargeScope" begin="1" end="4" step="1">
						<td>
							<a id="ls-${(yearLargeScopeRow-1)*4+yearLargeScope}" class="btn btn-link">${1890+((yearLargeScopeRow-1)*4+yearLargeScope)*10}-${1899+((yearLargeScopeRow-1)*4+yearLargeScope)*10}</a>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<!-- 备忘提示窗口 -->
		<div class="modal fade static" id="memorandum">
			<div id="modle-margin-left" class="col-md-3"></div>
			<div id="model-width" class="col-md-6">
				<div id="modle-margin-top" style="height:30px"></div>
				<div class="col-md-12">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 id="modal-title" class="modal-title"><span class="glyphicon glyphicon-edit"></span><strong>备忘提醒</strong></h4>
						</div>
						<div id="modal-body" class="modal-body" style="padding-bottom:0px">
							<table class="table" id="memorandumInfo" border="0" style="margin-bottom:0px">
								<tr class="warning">
									<td colspan="2">
										<div class="col-md-12" style="text-align:left">
											日期：&nbsp;&nbsp;
											<span id="memorandumInfo-memorandumDate"><!--日期 --></span>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="memorandumInfo-memorandumBeginTime"><!-- 开始时间 --></span>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="memorandumInfo-memorandumEndTime"><!--结束时间 --></span>
										</div>
									</td>
									<!-- <td><div class="col-md-12">天气：&nbsp;&nbsp;<span id="memorandumInfo-memorandumWeather">雾霾</span></div></td> -->
								</tr>
								<tr class="warning">
									<td colspan="2" align="center"><div class="col-md-12"><h4><span id="memorandumInfo-memorandumTitle">
										<!-- 备忘录标题 -->
									</span></h4></div></td>
								</tr>
								<tr class="warning">
									<td colspan="2">
										<div class="col-md-12" style="text-align:left"><span id="memorandumInfo-memorandumContent">
											<!-- 备忘录内容 -->
										</span></div>
									</td>
								</tr>
								<!-- <tr>
									<td colspan="2"><hr /></td>
								</tr> -->
								<tr class="warning">
									<td colspan="2">
										<nav>
										    <ul class="pager" style="margin-bottom:0px">
											    <li class=""><a id="memorandumInfo-previousPage" href="#">上一条</a></li>
											    <li class="">
											    	<strong id="memorandumInfo-currPage">
											    		<!-- 当前页数 -->
											    	</strong>
											    	<strong>/</strong>
											    	<strong id="memorandumInfo-countPageNumber">
											    		<!-- 总页数 -->
											    	</strong>
											    </li>
											    <li class=""><a id="memorandumInfo-nextPage" href="#">下一条 </a></li>
										    </ul>
										</nav>
									</td>
								</tr>
								<tr>
									<td><input type="hidden" value="" id="memorandumInfo-memorandumId"/></td>
								</tr>
							</table>
						</div>
						<!-- <div class="modal-footer">
							<button id="button-left" type="button" class="btn btn-default" data-dismiss="modal">关闭对话框</button>
							<button id="button-right" type="button" class="btn btn-primary"></button>
						</div> -->
					</div><!-- /.modal-content -->
					
				</div><!-- /.modal-dialog -->
			</div>
		</div><!-- /.modal -->
	</div>
</body>
<script type="text/javascript">

	function setMemorandom(id,title,content,date,currPage,countPageNumber){
		$('span#memorandumInfo-memorandumDate').text(date);
		$('span#memorandumInfo-memorandumTitle').text(title);
		$('span#memorandumInfo-memorandumContent').text(content);
		$('strong#memorandumInfo-currPage').text(currPage);
		$('strong#memorandumInfo-countPageNumber').text(countPageNumber);
		$('input#memorandumInfo-memorandumId').val(id);
	}

	$(function(){
		var passDate = new Date();
		var passDate_currYear = passDate.getFullYear();
		var passDate_currMonth = (passDate.getMonth()+1) < 10 ? "0" + (passDate.getMonth()+1) : (passDate.getMonth()+1);
		var passDate_currDay = passDate.getDate() < 10 ? "0" + passDate.getDate() : passDate.getDate();
		//var passDate_hour = passDate.getHours() < 10 ? "0" + passDate.getHours() : passDate.getHours();
		//var passDate_minute = passDate.getMinutes() < 10 ? "0" + passDate.getMinutes() : passDate.getMinutes();
		$.post("<%=basePath%>WorkBook/getWorkBookByDate.do",
				{
					passDate:passDate_currYear+"-"+passDate_currMonth+"-"+passDate_currDay,
					//首次调出info页面传入-1，用来检索总页数
					workBookNo:-1,
					type:'备忘录'
				},
				function(data,status){
					if(status == 'success'){
						var memorandom = eval(data);
						if(memorandom[0].countPageNumber > 0){
							//设置分页按钮活性
							$('a#memorandumInfo-previousPage').parent('li').addClass('disabled');
							if(memorandom[0].currPage == memorandom[0].countPageNumber){
								$('a#memorandumInfo-nextPage').parent('li').addClass('disabled');
							}
							setMemorandom(memorandom[0].WorkBookId,memorandom[0].WorkBookTitle,memorandom[0].WorkBookContent,memorandom[0].WorkBookDate,memorandom[0].currPage,memorandom[0].countPageNumber);
							$('#memorandum').modal('show');
						}else{
							//不做操作
						}
					}else{
						alert('请求失败了！！');
					}
				});
		$('.popover').show();
		
		//详情页面上一页按钮点击事件
		$('a#memorandumInfo-previousPage').bind('click',function(){
			if($('strong#memorandumInfo-currPage').text() != 1){
				$.post("<%=basePath%>WorkBook/getWorkBookByDate",
						{
							passDate:$('span#memorandumInfo-memorandumDate').text(),
							workBookNo:parseInt($('strong#memorandumInfo-currPage').text())-2,
							type:'备忘录'
						},
						function(data,status){
							if(status == 'success'){
								if($('strong#memorandumInfo-currPage').text() == 2){
									$('a#memorandumInfo-previousPage').parent('li').addClass('disabled');
								}
								$('strong#memorandumInfo-currPage').text(parseInt($('strong#memorandumInfo-currPage').text())-1);
								if($('strong#memorandumInfo-currPage').text() != $('strong#memorandumInfo-countPageNumber').text()){
									$('a#memorandumInfo-nextPage').parent('li').removeClass('disabled');
								}
								var memorandom = eval(data);
								setMemorandom(memorandom[0].WorkBookId,memorandom[0].WorkBookTitle,memorandom[0].WorkBookContent,memorandom[0].WorkBookDate,memorandom[0].currPage,memorandom[0].countPageNumber);
							}else{
								alert('请求失败了！！');
							}
						});
			}
		});
		
		//详情页面下一页按钮点击事件
		$('a#memorandumInfo-nextPage').bind('click',function(){
			if($('strong#memorandumInfo-currPage').text() != $('strong#memorandumInfo-countPageNumber').text()){
				$.post("<%=basePath%>WorkBook/getWorkBookByDate",
						{
							passDate:$('span#memorandumInfo-memorandumDate').text(),
							workBookNo:$('strong#memorandumInfo-currPage').text(),
							type:'备忘录'
						},
						function(data,status){
							if(status == 'success'){
								$('strong#memorandumInfo-currPage').text(parseInt($('strong#memorandumInfo-currPage').text())+1);
								if($('strong#memorandumInfo-currPage').text() == $('strong#memorandumInfo-countPageNumber').text()){
									$('a#memorandumInfo-nextPage').parent('li').addClass('disabled');
								}
								if($('strong#memorandumInfo-currPage').text() > 1 || $('strong#memorandumInfo-currPage').text() < 1){
									$('a#memorandumInfo-previousPage').parent('li').removeClass('disabled');
								}
								var memorandom = eval(data);
								setMemorandom(memorandom[0].WorkBookId,memorandom[0].WorkBookTitle,memorandom[0].WorkBookContent,memorandom[0].WorkBookDate,memorandom[0].currPage,memorandom[0].countPageNumber);
							}else{
								alert('请求失败了！！');
							} 
						});
			}
		});
		
		//设置日历标题中的日期，默认是系统所在年份
		var sysYear = new Date().getFullYear();
		var year = $('#setCalendarYear').text();
		if(year=='' ||year==' '||typeof year=='undefined') {
			year = sysYear;
		}
		$('#setCalendarYear').text(year);
		$('span.year').text(year);
		$('a').tooltip();

		//调用获取当年日历日期函数
		initCalendar();
		//日历标题点击事件
		(function(){
			$('#setCalendarYear').parent('a').bind('dblclick',function(){
				//将日历标题中的年份传递给模态窗口的标题
				$('.modal-title').children('a').text($('#setCalendarYear').text());
				
				//生成小范围年份table中的数据
				var yearAddToTd = $('#setCalendarYear').text() - $('#setCalendarYear').text()%10;
				for(var i=1;i<=12;i++){
					$('#ss-'+i).text(yearAddToTd);
					yearAddToTd = yearAddToTd + 1;
				}

				//将小范围年限添加到模态窗口的主体中
				$('div.modal-body').append($('#yearSmallScope'));

				//展示模态窗口
				$('#myModal').modal('show');

				//展示模态窗口主体中的数据
				$('#yearSmallScope').show('slow');

				//将大范围年份table隐藏
				$('#yearLargeScope').hide();
			});
		})();
		
		//模态窗口标题点击事件
		$('.modal-title').children('a').bind('dblclick',function(){
			//将大范围年份table加入模态窗口主体
			$('div.modal-body').append($('#yearLargeScope'));
			$('#myModal').modal('show');
			$('#yearLargeScope').show('slow');
			$('#yearSmallScope').hide();
		});

		//小范围年份中年份的点击事件
		$('#yearSmallScope a').bind('click',function(){
			$('.modal-title').children('a').text($(this).text());
		});

		//大范围年份中年份的点击事件
		(function(){
			$('#yearLargeScope a').bind('click',function(){
				var yearAddToTd = $(this).text().split('-')[0];
				$('.modal-title').children('a').text(yearAddToTd);
				for(var i=1;i<=12;i++){
					$('#ss-'+i).text(yearAddToTd);
					yearAddToTd = 1 + parseInt(yearAddToTd);
				}
				$('div.modal-body').append($('#yearSmallScope'));
				$('#yearSmallScope').show('slow');
				$('#yearLargeScope').hide();
			});
		})();

		//模态窗口确定按钮点击事件
		(function(){
			$('#saveYear').bind('click',function(){
				$('#setCalendarYear').text($('.modal-title a').text());
				$('span.year').text($('.modal-title a').text());
				$('#myModal').modal('hide');

				//调用获取当年日历日期函数
				initCalendar();
			});
		})();

		//日历初始化
		function initCalendar(){

			//日历清空
			$('td.dayNo').each(function(){
				$(this).text('');
			});
			
			var curYear = parseInt($('#setCalendarYear').text());

			var countDaysEachMonth = [31,28,31,30,31,30,31,31,30,31,30,31];
			if(curYear%4==0 && curYear%100!=0 || curYear%100==0 && curYear%400==0) {
				countDaysEachMonth[1] = 29;
			}
			var sysYear = new Date().getFullYear();  //获取年
	        var sysMonth = new Date().getMonth() + 1;    //获取月
	        sysMonth=sysMonth<=9?'0'+sysMonth:sysMonth;
	        var sysDay = new Date().getDate(); //获取日
	        sysDay=sysDay<=9?'0'+sysDay:sysDay;
	        var sysDate=sysYear+'-'+sysMonth+'-'+sysDay;
			$.post("<%=basePath%>WorkCalendar/getCalendar.do",
					{
						year:curYear,
						method:'getYearMs'
					},
					function(data,status){
						if(status == 'success') {
							var yearMs = data.split('/');
							for(var i=0;i<yearMs.length;i++) {
								var eachMonth = yearMs[i].split('-');
								var month = parseInt(eachMonth[1]);
								var day = parseInt(eachMonth[2]);
								//后台返回的星期一到星期日对应(1到7),前台星期日到星期六对应1到7，所以把后台的星期加1与前台对应
								var week = parseInt(eachMonth[3]) + 1;
								for(var j=1;j<=countDaysEachMonth[month-1];j++) {
									var j1;
									var month1;
									if(j<10){ j1 = '0'+j; }else{ j1 = ''+j;}
									if(month<10){ month1 = '0'+month; }else{ month1 = ''+month; }
									var passDate = curYear+'-'+month1+'-'+j1;
									
									//这个地址要修改
									if(passDate==sysDate){
									$('td#'+month+'-'+week).css("background-color","#FFCC66");
									var url = '<%=basePath%>/ibusiness/workbook/WorkBook.jsp?currDate='+passDate;
									$('td#'+month+'-'+week).html('<a href="'+url+'">'+j+'</a>');
									}else{
										var url = '<%=basePath%>/ibusiness/workbook/WorkBook.jsp?currDate='+passDate;
										$('td#'+month+'-'+week).html('<a href="'+url+'">'+j+'</a>');
									}
								    week = parseInt(week) + 1;
								}
								
							}
						}
					});
		}
		
		
	});
</script>
</html>
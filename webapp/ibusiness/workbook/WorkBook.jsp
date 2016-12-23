<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作日志</title>
<jsp:include page="./jsps/base/head_base.jsp"></jsp:include>
<style type="text/css">
table#workbook-ourter td.MtF>div.blockpage {
	width: 108px;
	height: 100px;
}
table#workbook-ourter td.StS>div.blockpage {
	width: 108px;
	height: 50px;
}
table#workbook-ourter>thead>tr>th{
	font-weight:bold;
	font-size:17px;
	padding:5px;
	text-align:center;
}
div.blockpage {
	padding: 1px;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, 0.2);
	border-radius: 6px;
	-webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
	background-clip: padding-box;
}

div.title {
	font-weight:bold;
	font-size:15px;
	padding:2px;
	background-color: #f7f7f7;
	border-bottom: 1px solid #ebebeb;
	border-radius: 5px 5px 0 0;
}

div#workbook-ourter-dataframe {
	display:none;
}

div.popover {
	height: 30px;
}

table#WorkBookType,table#addWorkBookTemplet,table#WorkBookInfo{
	display:none;
}
radio:hover{
	cursor:hand;
}
table#addWorkBookTemplet td{
	
}
</style>
</head>
<body>
    <div class="row">
		<div class="col-lg-1"></div>
		<!-- start of main -->
		<div class="panel panel-default col-lg-10"> 
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#tab1" id="tab1-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true"></a></li>
				<!-- 这个地址要修改 -->
				<li><a class="btn btn-link" href="<%=basePath%>/ibusiness/workbook/WorkCalendar.jsp">年日历</a></li>
				<li class="navbar-right"><button id="btn-addWorkbook" class="btn btn-default">填写日志</button></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade in active" id="tab1" aria-labelledBy="home-tab"></div>
				<div role="tabpanel" class="tab-pane fade" id="tab2" aria-labelledBy="profile-tab"></div>
			</div>
	
			<!-- dataframe -->
			<div id="workbook-ourter-dataframe" class="table-responsive">
				<table id="workbook-ourter" class="table">
					<thead>
						<tr>
							<th>星期一</th>
							<th>星期二</th>
							<th>星期三</th>
							<th>星期四</th>
							<th>星期五</th>
							<th>星期六/星期日</th>
						</tr>
					</thead>
					<tboody>
					<c:forEach var="row" begin="1" end="6" step="1">
						<tr>
							<c:forEach var="column" begin="1" end="5" step="1">
								<td id="${(row-1)*5+column+(row-1)*2}" class="MtF">
									<div class="blockpage">
										<div class="title" >
											<span class="year"></span><span class="month"></span><span class="day"></span>&nbsp;
										</div>
										<div class="content" >&nbsp;</div>
									</div>
								</td>
							</c:forEach>
							<td>
								<table>
									<tr>
										<td id="${(row-1)*5+(row-1)*2+6}" class="StS">
											<div class="blockpage">
												<div class="title">
													<span class="year"></span>&nbsp;<span class="month"></span>&nbsp;<span class="day"></span>&nbsp;
												</div>
												<div class="content">&nbsp;</div>
											</div>
										</td>
									</tr>
									<tr>
										<td id="${(row-1)*5+(row-1)*2+7}" class="StS">
											<div class="blockpage">
												<div class="title">
													<span class="year"></span>&nbsp;<span class="month"></span>&nbsp;<span class="day"></span>&nbsp;
												</div>
												<div class="content">&nbsp;</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
					</tboody>
				</table>
			</div>
			
			<!-- 模态窗口 -->
			<div class="modal fade static" id="myModal">
				<div id="modle-margin-left" class=""></div>
				<div id="model-width" class="">
					<div id="modle-margin-top"></div>
					<div class="col-md-12">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 id="modal-title" class="modal-title"><span class="glyphicon glyphicon-edit"></span><strong></strong></h4>
							</div>
							<div id="modal-body" class="modal-body" style="padding-bottom:0px">
								
							</div>
							<div class="modal-footer">
								<button id="button-left" type="button" class="btn btn-default" data-dismiss="modal">关闭对话框</button>
								<button id="button-right" type="button" class="btn btn-primary"></button>
							</div>
						</div><!-- /.modal-content -->
						
					</div><!-- /.modal-dialog -->
				</div>
			</div><!-- /.modal -->
			
			<!-- 日志类型 -->
			<table class="table" id="WorkBookType" class="col-md-12" style="margin-bottom:0px">
				<tr class="warning">
					<td></td>
					<td></td>
					<td></td>
					<td align="center">
						<div class="radio">
						    <label>
							    <input type="radio" name="WorkBookType" value="workbook" checked="checked">
							            填写工作日志
						    </label>
						</div>
					</td>
					<td align="center">
						<div class="radio">
						    <label>
							    <input type="radio" name="WorkBookType" value="memorandum">
							            填写备忘录
						    </label>
						</div>
					</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr sytle="margin:0px;padding:0px">
					<td colspan="8" sytle="margin:0px;padding:0px;hight:0px;"></td>
				</tr>
			</table>
			
			<!-- 新增日志模板 -->
			<table id="addWorkBookTemplet" border="0">
				<tr>
					<!-- <td>
						<div class="col-md-1"></div>
					</td> -->
					<td colspan="2" id="changeText">填写日期：</td>
					<td style="padding-left:1%">
						<div class="dropdown" style="display:inline">
						    <button class="btn btn-info dropdown-toggle" type="button" id="dropdown-year" data-toggle="dropdown">
						    	<span class="year"></span><span class="caret"></span>
						    </button>
						    <ul id="dropdown-year-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-year">
							    <!-- 年列表 -->
						    </ul>
						</div>
						<span>年</span>
						<div class="dropdown" style="display:inline">
						    <button class="btn btn-info dropdown-toggle" type="button" id="dropdown-month" data-toggle="dropdown">
						    	<span class="month"></span><span class="caret"></span>
						    </button>
						    <ul id="dropdown-month-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-month">
							   <!-- 月列表 -->
						    </ul>    
						</div>
						<span>月</span>
						<div class="dropdown" style="display:inline">
						    <button class="btn btn-info dropdown-toggle" type="button" id="dropdown-day" data-toggle="dropdown">
						    	<span class="day"></span><span class="caret"></span>
						    </button>
						    <ul id="dropdown-day-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-day">
							    <!-- 日列表 -->
						    </ul>
						</div>
						<span>日</span>
					</td>
					<td>
						<input disabled id="inserWorkBook-date"  type="text" placeholder="日志填写日期" class="form-control" />
					</td>
					<td>
						<div class="col-md-1"></div>
					</td>
					<td>
						<div class="col-md-1"></div>
					</td>
					<td>
						<div class="col-md-1"></div>
					</td>
					<td></td>
					<td></td>
				</tr>
				<!-- <tr>
					<td colspan="9"><hr/></td>
				</tr> -->
				<tr>
					<!-- <td>
						<div class="col-md-1"></div>
					</td> -->
					<td colspan="2">类型：</td>
					<td style="padding-left:1%">
						<!-- <input disabled id="WorkBook-id"  type="text" placeholder="日志编号" class="form-control" /> -->
						<input disabled id="InsertType" type="text" class="form-control" />
					</td>
					<td colspan="3" style="text-align:right">天气：</td>
					<td colspan="3" style="padding-left:1%">
						<div class="dropdown">
							<button class="btn btn-info dropdown-toggle" type="button" id="dropdown-weather" data-toggle="dropdown">
								<span class="weather">晴天</span><span class="caret"></span>
							</button>
							<ul id="dropdown-weather-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-weather">
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">晴天</a></li>
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">阴天</a></li>
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">雨天</a></li>
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">暴雪</a></li>
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">台风</a></li>
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">雾霾</a></li>
								<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">冰雹</a></li>
							</ul>
						</div>
					</td>
					<!-- <td colspan="3" style="text-align:right"></td>
					<td colspan="3" style="padding-left:1%"> -->
						
					</td>
				</tr>
				<!-- <tr>
					<td>
						<div class="col-md-1"></div>
					</td>
					<td colspan="2">工作编号：</td>
					<td style="padding-left:1%">
						<input disabled id="Work-id"  type="text" placeholder="工作编号" class="form-control" />
					</td>
				</tr> -->
				<tr>
					<td colspan="9"><hr/></td>
				</tr>
				<tr>
					<!-- <td>
						<div class="col-md-2"></div>
					</td> -->
					<td colspan="2">工作起始时间：</td>
					<td style="padding-left:1%">
						<div class="col-md-4" style="margin-left:0px;margin-right:0px;padding-left:0px;padding-right:2%;">
							<input id="Work-startTime-hour"  type="text" placeholder="24时制" class="form-control" />
						</div>
						<div class="col-md-2" style="margin-left:0px;margin-right:0px;padding-left:5%;padding-right:0px;"><h5>点</h5></div>
						<div class="col-md-4" style="margin-left:0px;margin-right:0px;padding-left:2%;padding-right:2%;">
							<input id="Work-startTime-minute"  type="text" placeholder="60分钟" class="form-control" />
						</div>
						<div class="col-md-2" style="margin-left:0px;margin-right:0px;padding-left:5%;padding-right:0px;"><h5>分</h5></div>
					</td>
					<td align="right">工作结束时间：</td>
					<td colspan="5">
						<div class="col-md-4" style="margin-left:2;margin-right:0px;padding-left:2%;padding-right:2%;">
							<input id="Work-endTime-hour"  type="text" placeholder="24时制" class="form-control" />
						</div>
						<div class="col-md-2" style="margin-left:0px;margin-right:0px;padding-left:5%;padding-right:0px;"><h5>点</h5></div>
						<div class="col-md-4" style="margin-left:0px;margin-right:0px;padding-left:2%;padding-right:2%;">
							<input id="Work-endTime-minute"  type="text" placeholder="60分钟" class="form-control" />
						</div>
						<div class="col-md-2" style="margin-left:0px;margin-right:0px;padding-left:5%;padding-right:0px;"><h5>分</h5></div>
					</td>
				</tr>
				<tr>
					<!-- <td>
						<div class="col-md-1"></div>
					</td> -->
					<td colspan="2">名称：</td>
					<td colspan="7" style="padding-left:1%;">
						<div>
							<input id="Work-name"  type="text" placeholder="标题" class="form-control" />
						</div>
					</td>
				</tr>
				<tr>
					<!-- <td>
						<div class="col-md-1"></div>
					</td> -->
					<td colspan="2">内容：</td>
					<td colspan="7" colspan="7" style="padding-left:1%;">
						<div>
							<textarea id="Work-content" rows="7" placeholder="内容" class="form-control" ></textarea>
						</div>
					</td>
				</tr>
			</table>
			<!-- 工作日志详情模板 -->
			<table class="table" id="WorkBookInfo" border="0" style="margin-bottom:0px">
				<tr class="warning">
					<td>
						<div class="col-md-12">
							时间：&nbsp;&nbsp;
							<span id="WorkBookInfo-WorkBookDate"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="WorkBookInfo-WorkBookBeginTime"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="WorkBookInfo-WorkBookEndTime"></span>
						</div>
					</td>
					<td><div class="col-md-12">天气：&nbsp;&nbsp;<span id="WorkBookInfo-WorkBookWeather"></span></div></td>
				</tr>
				<tr class="warning">
					<td colspan="2" align="center"><div class="col-md-12"><h4><span id="WorkBookInfo-WorkBookTitle"></span></h4></div></td>
				</tr>
				<tr class="warning">
					<td colspan="2">
						<div class="col-md-12"><span id="WorkBookInfo-WorkBookContent"></span></div>
					</td>
				</tr>
				<!-- <tr>
					<td colspan="2"><hr /></td>
				</tr> -->
				<tr class="warning">
					<td colspan="2">
						<nav>
						    <ul class="pager" style="margin-bottom:0px">
							    <li class=""><a id="WorkBookInfo-previousPage" href="#">上一条</a></li>
							    <li class="">
							    	<strong id="WorkBookInfo-currPage">1</strong>
							    	<strong>/</strong>
							    	<strong id="WorkBookInfo-countPageNumber">5</strong>
							    </li>
							    <li class=""><a id="WorkBookInfo-nextPage" href="#">下一条 </a></li>
							    &nbsp;&nbsp;&nbsp;&nbsp;
							    <a id="WorkBookInfo-update" href="#">修改</a>
							    &nbsp;
							    <a id="WorkBookInfo-delete" href="#">删除 </a>
						    </ul>
						</nav>
					</td>
				</tr>
				<tr>
					<td><input type="hidden" value="" id="WorkBookInfo-WorkBookId"/></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	//工作日志详情按钮点击事件
	(function(){
		$('#myModal').on('hidden.bs.modal', function (e) {
			$('input#Work-startTime-hour').val('').parent('div').removeClass('has-error');
			$('input#Work-startTime-minute').val('').parent('div').removeClass('has-error');
			$('input#Work-endTime-hour').val('').parent('div').removeClass('has-error');
			$('input#Work-endTime-minute').val('').parent('div').removeClass('has-error');
			$('input#Work-name').parent('div').removeClass('has-error');
			$('input#Work-content').parent('div').removeClass('has-error');
			location.reload([true]);
		});
	})();
	//详情页面上一页按钮点击事件
	$('a#WorkBookInfo-previousPage').bind('click',function(){
		if($('strong#WorkBookInfo-currPage').text() != 1){
			$.post("<%=basePath%>WorkBook/getWorkBookByDate",
					{
						passDate:$('span#WorkBookInfo-WorkBookDate').text(),
						workBookNo:parseInt($('strong#WorkBookInfo-currPage').text())-2,
						type:'工作日志'
					},
					function(data,status){
						if(status == 'success'){
							var workBookInfo = eval(data);
							//设置详情页面的数据
							initWorkBookInfo(workBookInfo);
							if($('strong#WorkBookInfo-currPage').text() == 2){
								$('a#WorkBookInfo-previousPage').parent('li').addClass('disabled');
							}
							$('strong#WorkBookInfo-currPage').text(parseInt($('strong#WorkBookInfo-currPage').text())-1);
							if($('strong#WorkBookInfo-currPage').text() != $('strong#WorkBookInfo-countPageNumber').text()){
								$('a#WorkBookInfo-nextPage').parent('li').removeClass('disabled');
							}
						}else{
							alert('请求失败了！！');
						}
					});
		}
	});
	
	//详情页面下一页按钮点击事件
	$('a#WorkBookInfo-nextPage').bind('click',function(){
		if($('strong#WorkBookInfo-currPage').text() != $('strong#WorkBookInfo-countPageNumber').text()){
			$.post("<%=basePath%>WorkBook/getWorkBookByDate",
					{
						passDate:$('span#WorkBookInfo-WorkBookDate').text(),
						workBookNo:$('strong#WorkBookInfo-currPage').text(),
						type:'工作日志'
					},
					function(data,status){
						if(status == 'success'){
							var workBookInfo = eval(data);
							//设置详情页面的数据
							initWorkBookInfo(workBookInfo);
							$('strong#WorkBookInfo-currPage').text(parseInt($('strong#WorkBookInfo-currPage').text())+1);
							if($('strong#WorkBookInfo-currPage').text() == $('strong#WorkBookInfo-countPageNumber').text()){
								$('a#WorkBookInfo-nextPage').parent('li').addClass('disabled');
							}
							if($('strong#WorkBookInfo-currPage').text() > 1 || $('strong#WorkBookInfo-currPage').text() < 1){
								$('a#WorkBookInfo-previousPage').parent('li').removeClass('disabled');
							}
						}else{
							alert('请求失败了！！');
						} 
					});
		}
	});
	
	//设置工作日志详情函数
	function initWorkBookInfo(workBookInfo){
		$('span#WorkBookInfo-WorkBookDate').text(workBookInfo[0].WorkBookDate);
		$('span#WorkBookInfo-WorkBookBeginTime').text(workBookInfo[0].WorkBookBeginTime);
		$('span#WorkBookInfo-WorkBookEndTime').text(workBookInfo[0].WorkBookEndTime);
		$('span#WorkBookInfo-WorkBookWeather').text(workBookInfo[0].WorkBookWeather);
		$('span#WorkBookInfo-WorkBookTitle').text(workBookInfo[0].WorkBookTitle);
		$('span#WorkBookInfo-WorkBookContent').text(workBookInfo[0].WorkBookContent);
		$('input#WorkBookInfo-WorkBookId').val(workBookInfo[0].WorkBookId);
	}
	
	//全局数组
	var monthArry31 = ['01','03','05','07','08','10','12'];
	var monthArry30 = ['04','06','09','11'];
	//日历初始化
	(function(){
		$('.popover').modal('show');
		var currDate = '<%=request.getParameter("currDate")%>';
		var currYear = currDate.split('-')[0];
		var currMonth = currDate.split('-')[1];
		var currDay = currDate.split('-')[2];
		$('div#tab1').append($('div#workbook-ourter-dataframe'));
		$('div#workbook-ourter-dataframe').show();
		$('a#tab1-tab').text(currYear+'年'+currMonth+'月工作日志');
		$.post("<%=basePath%>WorkCalendar/getCalendar.do",
				{
					year:currYear+'-'+currMonth+'-01',
					method:'getDayOfWeek'
				},
				function(data,status){
					if(status == 'success'){
						var loopTimes = 0;
						if(isContain(monthArry31,currMonth) == true){
							loopTimes = 31;
						}else if(isContain(monthArry30,currMonth) == true){
							loopTimes = 30;
						}else{
							if((parseInt(currYear)%4==0&&parseInt(currYear)%100!=0)||parseInt(currYear)%400==0){
								loopTimes = 29;
							}else{
								loopTimes = 28;
							}
						}
					}
					for(var j=0;j<loopTimes;j++){
						$('td#'+(parseInt(data)+j)+' span.year').text(currYear+'-');
						$('td#'+(parseInt(data)+j)+' span.month').text(currMonth+'-');
						if(j<=9)
						$('td#'+(parseInt(data)+j)+' span.day').text('0'+(j+1));
						else
						$('td#'+(parseInt(data)+j)+' span.day').text((j+1));
						$('td#'+(parseInt(data)+j)+' div.title').css("background-color","#FBEFB6");
						$('td#'+(parseInt(data)+j)+' div.blockpage').css("background-color","#FDF7D9");
						
						if((j+1) == parseInt(currDay)){
							$('td#'+(parseInt(data)+j)).css('border','2px red solid');
						}
					}
				});
		
		//向日历中添加数据
		var date = new Date();
		var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
		var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
		$.post("<%=basePath%>WorkBook/getWorkBooks.do",
				{
					currDate:currDate+" "+hour+":"+minute
				},
				function(data,status){
					if(status == 'success'){
						var workBookObj = eval(data);
						for(var i0=0;i0<workBookObj[0].workBooks.length;i0++){
							$('td#'+workBookObj[0].workBooks[i0].number+" div.content").html('<a href="#" class="WorkBookInfo">'+workBookObj[0].workBooks[i0].content+'</a>');
						}
						//为详情按钮绑定点击事件
						$('a.WorkBookInfo').bind('click',function(){
							//获取本日的工作日志内容传递本日日期
							var passYear = parseInt($(this).parents('div.blockpage').children('div.title').children('span.year').text());
							var passMonth = parseInt($(this).parents('div.blockpage').children('div.title').children('span.month').text());
							var passDay = parseInt($(this).parents('div.blockpage').children('div.title').children('span.day').text());
							var passDate = passYear+"-"+passMonth+"-"+passDay;
							getAWorkBook(passDate);
						});
					}else{
						alert('请求失败！');
					}
				});
	})();
	
	//详情按钮点击事件回调函数
	function getAWorkBook(passDate){
		$.post("<%=basePath%>WorkBook/getWorkBookByDate",
			{
				passDate:passDate,
				//首次调出info页面传入-1，用来检索总页数
				workBookNo:-1,
				type:'工作日志'
			},
			function(data,status){
				if(status == 'success'){
					var workBookInfo = eval(data);
					//设置详情页面的数据
					initWorkBookInfo(workBookInfo);
					//设置分页详情
					$('strong#WorkBookInfo-currPage').text(workBookInfo[0].currPage);
					$('strong#WorkBookInfo-countPageNumber').text(workBookInfo[0].countPageNumber);
					//设置分页按钮活性
					$('a#WorkBookInfo-previousPage').parent('li').addClass('disabled');
					if(workBookInfo[0].currPage == workBookInfo[0].countPageNumber){
						$('a#WorkBookInfo-nextPage').parent('li').addClass('disabled');
					}
					//设置模态窗口的尺寸
					$('div#modle-margin-left').attr('class','col-md-3');
					$('div#model-width').attr('class','col-md-6');
					$('div#modle-margin-top').css('height','30px');
					$('h4#modal-title strong').text('工作详细信息');
					$('button#button-right').html('&nbsp;确认选择&nbsp;');
					$('div#modal-body').append($('table#WorkBookInfo'));
					$('table#WorkBookType').hide();
					$('button#button-left').hide();
					$('button#button-right').hide();
					$('table#addWorkBookTemplet').hide();
					$('table#WorkBookInfo').show();
					//模态窗口展示
					$('#myModal').modal({
						keyboard: false,
						backdrop:'static',
						show:true
					});
				}else{
					alert('请求失败！');
				}
			});
	}
	
	function isContain(array,param){
		for(var i=0;i<array.length;i++){
			if(parseInt(array[i]) == parseInt(param)) {
				return true;
			}
		}
		return false;
	}
	//模态窗口样式说明：
	/*
		WorkBookType:
			model的参数：modle-margin-left：col-md-3			模态框距浏览器左边距
					  model-width	          ：col-md-6			模态框宽度（左边距减1宽度加2）
					  modle-margin-top ：height=100px		模态框距浏览器顶端距离	
					  #modal-title strong:选择添加日志的类型		模态框标题
					  button-right     ：&nbsp;确认选择&nbsp;	模态框确认按钮的文本
	
	*/
	//btn-addWorkbook点击事件
	$('button#btn-addWorkbook').bind('click',function(){
		$('div#modle-margin-left').attr('class','col-md-3');
		$('div#model-width').attr('class','col-md-6');
		$('div#modle-margin-top').css('height','100px');
		$('h4#modal-title strong').text('选择添写的类型');
		$('button#button-right').html('&nbsp;确认选择&nbsp;');
		$('div#modal-body').append($('table#WorkBookType'));
		$('table#WorkBookType').show();
		$('button#button-left').show();
		$('button#button-right').show();
		$('table#addWorkBookTemplet').hide();
		$('table#WorkBookInfo').hide();
		//模态窗口展示
		$('#myModal').modal({
					keyboard: false,
					backdrop:'static',
					show:true
				});
	});

	//inserWorkBook-date的日期同步
	function initInserWorkBookDate(){
		var year_span = $('button#dropdown-year span.year').text();
		var month_span = $('button#dropdown-month span.month').text();
		var day_span = $('button#dropdown-day span.day').text();
		if(year_span!=''&&year_span!=null&&typeof year_span!='undefined'){
			if(month_span!=''&&month_span!=null&&typeof month_span!='undefined'){
				if(day_span!=''&&day_span!=null&&typeof day_span!='undefined'){
					$('input#inserWorkBook-date').val(year_span+'-'+month_span+'-'+day_span);
				}else{
					$('input#inserWorkBook-date').val('');
				}
			}else{
				$('input#inserWorkBook-date').val('');
			}
		}else{
			$('input#inserWorkBook-date').val('');
		}
	}
	
	//button-right按钮（模态窗口确定按钮）点击事件
	$('button#button-right').bind('click',function(){
		if($(this).html()=='&nbsp;确认选择&nbsp;'){
			if($("input[name='WorkBookType']:checked").val() == 'memorandum'){
				//填入类型
				$('input#InsertType').val('备忘录');
				//同步日期
				var currYear = new Date().getFullYear();
				var currMonth = new Date().getMonth()+1;
				var currDay = new Date().getDate();
				$('button#dropdown-year span.year').text(currYear).parent('button').attr('disabled',false);
				$('button#dropdown-month span.month').text(currMonth).parent('button').attr('disabled',false);
				$('button#dropdown-day span.day').text(currDay).parent('button').attr('disabled',false);
				$('td#changeText').text('备忘提醒日期：').css('border','1px red solid');
				
				initInserWorkBookDate();
				//设置模态框参数
				/*
					WorkBookType:
						model的参数：modle-margin-left：col-md-2
								  model-width	          ：col-md-8
								  modle-margin-top ：height=30px
								  #modal-title strong:填写今日工作日志
								  button-right     ：&nbsp;保存日志&nbsp;
				
				*/
				$('div#modle-margin-left').attr('class','col-md-2');
				$('div#model-width').attr('class','col-md-8');
				$('div#modle-margin-top').css('height','30px');
				$('h4#modal-title strong').text('填写备忘录');
				$('button#button-right').html('保存备忘录');
				$('div#modal-body').append($('table#addWorkBookTemplet'));
				$('input#Work-startTime-hour').attr('disabled',true);
				$('input#Work-startTime-minute').attr('disabled',true);
				$('input#Work-endTime-hour').attr('disabled',true);
				$('input#Work-endTime-minute').attr('disabled',true);
				$('button#dropdown-weather').attr('disabled',true);
				
				$('table#addWorkBookTemplet').show();
				$('button#button-left').show();
				$('button#button-right').show();
				$('table#WorkBookType').hide();
				$('table#WorkBookInfo').hide();
				
				//设置日期下拉框
				var loopTimes = 30;
				if(isContain(monthArry31,currMonth) == true){
					loopTimes = 31;
				}else if(isContain(monthArry30,currMonth) == true){
					loopTimes = 30;
				}else{
					if((parseInt(currYear)%4==0&&parseInt(currYear)%100!=0)||parseInt(currYear)%400==0){
						loopTimes = 29;
					}else{
						loopTimes = 28;
					}
				}
				$('ul#dropdown-year-menu').html('');
				for(var k=(currYear-5);k<=(currYear+5);k++){
					$('ul#dropdown-year-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+k+'</a></li>');
				}
				$('ul#dropdown-month-menu').html('');
				for(var i=1;i<=12;i++){
					$('ul#dropdown-month-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+i+'</a></li>');
				}
				$('ul#dropdown-day-menu').html('');
				for(var j=1;j<=loopTimes;j++){
					$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
				}
				//为新添加的元素绑定点击事件
				$("a[role='menuitem']").bind('click',function(){
					var dropdown_text = $(this).text();
					$(this).parents('ul').each(function(){
						var dropdown_mark = $(this).attr('id').split('-')[1];
						$('.dropdown span.'+dropdown_mark).text(dropdown_text);
						//month下拉按钮与day下拉按钮做联动
						if(dropdown_mark == 'month'){
							//如果点击的月份不是系统月份，那么日期下拉框菜单展示当月所有天数
							var loopTimes = 30;
							if(isContain(monthArry31,dropdown_text) == true){
								loopTimes = 31;
							}else if(isContain(monthArry30,dropdown_text) == true){
								loopTimes = 30;
							}else{
								if((parseInt($('button#dropdown-year span.year').text())%4==0&&parseInt($('button#dropdown-year span.year').text())%100!=0)||parseInt($('button#dropdown-year span.year').text())%400==0){
									loopTimes = 29;
								}else{
									loopTimes = 28;
								}
							}
							$('ul#dropdown-day-menu').html('');
							for(var j=1;j<=loopTimes;j++){
								$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
							}
						}else if(dropdown_mark == 'year'){
							$('button#dropdown-month span.month').text('1');
							$('button#dropdown-day span.day').text('1');
							$('ul#dropdown-day-menu').html('');
							for(var j=1;j<=31;j++){
								$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
							}
						}
						//为新添加的日期下拉框绑定点击事件
						$("a[role='menuitem']").bind('click',function(){
							var dropdown_text = $(this).text();
							$(this).parents('ul').each(function(){
								var dropdown_mark = $(this).attr('id').split('-')[1];
								$('.dropdown span.'+dropdown_mark).text(dropdown_text);
							});
							initInserWorkBookDate();
						});
					});
					initInserWorkBookDate();
				});
			}else if($("input[name='WorkBookType']:checked").val() == 'workbook'){
				//填入类型
				$('input#InsertType').val('工作日志');
				//同步日期
				var currYear = new Date().getFullYear();
				var currMonth = new Date().getMonth()+1;
				var currDay = new Date().getDate();
				$('button#dropdown-year span.year').text(currYear).parent('button').attr('disabled',false);
				$('button#dropdown-month span.month').text(currMonth).parent('button').attr('disabled',false);
				$('button#dropdown-day span.day').text(currDay).parent('button').attr('disabled',false);
				$('td#changeText').text('日志填写日期：').css('border','0px red solid');
				$('input#Work-startTime-hour').attr('disabled',false);
				$('input#Work-startTime-minute').attr('disabled',false);
				$('input#Work-endTime-hour').attr('disabled',false);
				$('input#Work-endTime-minute').attr('disabled',false);
				$('button#dropdown-weather').attr('disabled',false);
				
				initInserWorkBookDate();
				//设置模态框参数
				$('div#modle-margin-left').attr('class','col-md-2');
				$('div#model-width').attr('class','col-md-8');
				$('div#modle-margin-top').css('height','30px');
				$('h4#modal-title strong').text('填写工作日志');
				$('button#button-right').html('&nbsp;保存日志&nbsp;');
				$('div#modal-body').append($('table#addWorkBookTemplet'));
				$('table#addWorkBookTemplet').show();
				$('table#WorkBookType').hide();
				//设置日期下拉框
				var loopTimes = 30;
				if(isContain(monthArry31,currMonth) == true){
					loopTimes = 31;
				}else if(isContain(monthArry30,currMonth) == true){
					loopTimes = 30;
				}else{
					if((parseInt(currYear)%4==0&&parseInt(currYear)%100!=0)||parseInt(currYear)%400==0){
						loopTimes = 29;
					}else{
						loopTimes = 28;
					}
				}
				$('ul#dropdown-year-menu').html('');
				for(var k=(currYear-5);k<=(currYear+5);k++){
					$('ul#dropdown-year-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+k+'</a></li>');
				}
				$('ul#dropdown-month-menu').html('');
				for(var i=1;i<=12;i++){
					$('ul#dropdown-month-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+i+'</a></li>');
				}
				$('ul#dropdown-day-menu').html('');
				for(var j=1;j<=loopTimes;j++){
					$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
				}
				//为新添加的元素绑定点击事件
				$("a[role='menuitem']").bind('click',function(){
					var dropdown_text = $(this).text();
					$(this).parents('ul').each(function(){
						var dropdown_mark = $(this).attr('id').split('-')[1];
						$('.dropdown span.'+dropdown_mark).text(dropdown_text);
						//month下拉按钮与day下拉按钮做联动
						if(dropdown_mark == 'month'){
							//如果点击的月份不是系统月份，那么日期下拉框菜单展示当月所有天数
							var loopTimes = 30;
							if(isContain(monthArry31,dropdown_text) == true){
								loopTimes = 31;
							}else if(isContain(monthArry30,dropdown_text) == true){
								loopTimes = 30;
							}else{
								if((parseInt($('button#dropdown-year span.year').text())%4==0&&parseInt($('button#dropdown-year span.year').text())%100!=0)||parseInt($('button#dropdown-year span.year').text())%400==0){
									loopTimes = 29;
								}else{
									loopTimes = 28;
								}
							}
							$('ul#dropdown-day-menu').html('');
							for(var j=1;j<=loopTimes;j++){
								$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
							}
						}else if(dropdown_mark == 'year'){
							$('button#dropdown-month span.month').text('1');
							$('button#dropdown-day span.day').text('1');
							$('ul#dropdown-day-menu').html('');
							for(var j=1;j<=31;j++){
								$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
							}
						}
						//为新添加的日期下拉框绑定点击事件
						$("a[role='menuitem']").bind('click',function(){
							var dropdown_text = $(this).text();
							$(this).parents('ul').each(function(){
								var dropdown_mark = $(this).attr('id').split('-')[1];
								$('.dropdown span.'+dropdown_mark).text(dropdown_text);
							});
							initInserWorkBookDate();
						});
					});
					initInserWorkBookDate();
				});
			}else{
				alert('出错了！！！');
			}
		}else if($(this).html() == '&nbsp;保存日志&nbsp;'){
			var notNullCheckResult = notNullCheck();
			var timeCheckResult = timeCheck();
			if(timeCheckResult.status == 0 || notNullCheckResult.status == 0){
				if(timeCheckResult.status == 0){
					alert('请输入正确的时间');
				}
				if(notNullCheckResult.status == 0){
					alert('请输入工作标题和内容');
				}
			}else{
				//var addWorkBookUrl = 'http://localhost:8081/iBusiness_ldy/WorkBook/addWorkBook';
				var addWorkBookUrl = '<%=basePath%>WorkBook/addWorkBook';
				//var getWorkBookUrl = 'http://localhost:8081/iBusiness_ldy/WorkBook/getWorkBook';
				$.post(addWorkBookUrl,
				{
					weather:$('button#dropdown-weather span.weather').text(),
					beginTime:timeCheckResult.startTime,
					endTime:timeCheckResult.endTime,
					title:$('input#Work-name').val(),
					content:document.getElementById('Work-content').value,
					type:$('input#InsertType').val()
				},
				function(data,status){
					if(status == 'success'){
						if(data == 0){
							alert('插入失败！');
						}else{
							alert('插入成功！');
							$('input#Work-name').val('');
							document.getElementById('Work-content').value = '';
						}
					}
				});
			}
		}else if($(this).html() == '保存备忘录'){
			var work_content = document.getElementById('Work-content').value;
			if(work_content==null || work_content=='' || typeof work_content=='undefined'){
				alert('备忘录内容不能为空');
			}else{
				var addWorkBookUrl = '<%=basePath%>WorkBook/addWorkBook';
				$.post(addWorkBookUrl,
				{
					weather:'',
					beginTime:$('input#inserWorkBook-date').val(),
					endTime:'',
					title:$('input#Work-name').val(),
					content:document.getElementById('Work-content').value,
					type:$('input#InsertType').val()
				},
				function(data,status){
					if(status == 'success'){
						if(data == 0){
							alert('插入失败！');
						}else{
							alert('插入成功！');
							$('input#Work-name').val('');
							document.getElementById('Work-content').value = '';
						}
					}
				});
			}
		}else if($(this).html() == '&nbsp;保存修改&nbsp;'){
			var notNullCheckResult = notNullCheck();
			var timeCheckResult = timeCheck();
			if(timeCheckResult.status == 0 || notNullCheckResult.status == 0){
				if(timeCheckResult.status == 0){
					alert('请输入正确的时间');
				}
				if(notNullCheckResult.status == 0){
					alert('请输入工作标题和内容');
				}
			}else{
				//var addWorkBookUrl = 'http://localhost:8081/iBusiness_ldy/WorkBook/addWorkBook';
				var updateWorkBookUrl = '<%=basePath%>WorkBook/updateWorkBook';
				//var getWorkBookUrl = 'http://localhost:8081/iBusiness_ldy/WorkBook/getWorkBook';
				$.post(updateWorkBookUrl,
				{
					workBookId:$('input#WorkBookInfo-WorkBookId').val(),
					weather:$('button#dropdown-weather span.weather').text(),
					beginTime:timeCheckResult.startTime,
					endTime:timeCheckResult.endTime,
					title:$('input#Work-name').val(),
					content:document.getElementById('Work-content').value,
					type:$('input#InsertType').val()
				},
				function(data,status){
					if(status == 'success'){
						if(data == 0){
							alert('修改失败！');
						}else{
							alert('修改成功！');
							//重新设置分页按钮的活性
							$('a#WorkBookInfo-nextPage').parent('li').removeClass('disabled');
							$('a#WorkBookInfo-previousPage').parent('li').removeClass('disabled');
							getAWorkBook($('input#inserWorkBook-date').val());
							/* $('table#WorkBookInfo').show();
							$('table#addWorkBookTemplet').hide();
							$('button#button-left').hide();
							$('button#button-right').hide(); */
							
						}
					}
				});
			}
		}
	});
	
	//为新添加的日期下拉框绑定点击事件
	(function(){
		$("a[role='menuitem']").bind('click',function(){
			var dropdown_text = $(this).text();
			$(this).parents('ul').each(function(){
				var dropdown_mark = $(this).attr('id').split('-')[1];
				$('.dropdown span.'+dropdown_mark).text(dropdown_text);
			});
			initInserWorkBookDate();
		});
	})();
	
	//日志删除按钮点击事件
	$('a#WorkBookInfo-delete').bind('click',function(){
		if(confirm('确认要删除这一条日志吗？') == true){
			var deleteWorkBookUrl = '<%=basePath%>WorkBook/deleteWorkBook';
			$.post(deleteWorkBookUrl,
					{
						workBookId:$('input#WorkBookInfo-WorkBookId').val()	
					},
					function(data,status){
						if(status == 'success'){
							alert('删除成功！');
							//重置详情信息
							getAWorkBook($('span#WorkBookInfo-WorkBookDate').text());
						}else{
							alert('删除失败！');
						}
					});
		}
	});
	
	//更新按钮点击事件
	$('a#WorkBookInfo-update').bind('click',function(){
		//获取详情页面 中的数据信息
		var updateDate = $('span#WorkBookInfo-WorkBookDate').text().split('-');
		var updateBeginTime = $('span#WorkBookInfo-WorkBookBeginTime').text().split(':');
		var updateEndTime = $('span#WorkBookInfo-WorkBookEndTime').text().split(':');
		var updateWeather = $('span#WorkBookInfo-WorkBookWeather').text();
		var updateTitle = $('span#WorkBookInfo-WorkBookTitle').text();
		var updateContent = $('span#WorkBookInfo-WorkBookContent').text();
		//var updateId = $('input#WorkBookInfo-WorkBookId').val();
		
		//对日期进行分割处理
		var updateYear = updateDate[0];
		var updateMonth = updateDate[1];
		var udpateDat = updateDate[2];
		
		//对时间进行分割处理
		var updateBeginHour = updateBeginTime[0];
		var updateBeginMinute = updateBeginTime[1];
		var updateEndHour = updateEndTime[0];
		var updateEndMinute = updateEndTime[1];
		
		//填入类型
		$('input#InsertType').val('工作日志');
		//同步日期
		var currYear = new Date().getFullYear();
		var currMonth = new Date().getMonth()+1;
		var currDay = new Date().getDate();
		$('button#dropdown-year span.year').text(updateYear).parent('button').attr('disabled',false);
		$('button#dropdown-month span.month').text(updateMonth).parent('button').attr('disabled',false);
		$('button#dropdown-day span.day').text(udpateDat).parent('button').attr('disabled',false);
		$('td#changeText').text('日志填写日期：').css('border','0px red solid');
		$('input#Work-startTime-hour').val(updateBeginHour).attr('disabled',false);
		$('input#Work-startTime-minute').val(updateBeginMinute).attr('disabled',false);
		$('input#Work-endTime-hour').val(updateEndHour).attr('disabled',false);
		$('input#Work-endTime-minute').val(updateEndMinute).attr('disabled',false);
		$('button#dropdown-weather').attr('disabled',false);
		$('button#dropdown-weather span.weather').text(updateWeather);
		$('input#Work-name').val(updateTitle);
		document.getElementById('Work-content').value = updateContent;
		initInserWorkBookDate();
		//设置模态框参数
		$('div#modle-margin-left').attr('class','col-md-2');
		$('div#model-width').attr('class','col-md-8');
		$('div#modle-margin-top').css('height','30px');
		$('h4#modal-title strong').text('修改工作日志');
		$('button#button-right').html('&nbsp;保存修改&nbsp;');
		$('div#modal-body').append($('table#addWorkBookTemplet'));
		$('table#addWorkBookTemplet').show();
		$('table#WorkBookType').hide();
		//设置日期下拉框
		var loopTimes = 30;
		if(isContain(monthArry31,currMonth) == true){
			loopTimes = 31;
		}else if(isContain(monthArry30,currMonth) == true){
			loopTimes = 30;
		}else{
			if((parseInt(currYear)%4==0&&parseInt(currYear)%100!=0)||parseInt(currYear)%400==0){
				loopTimes = 29;
			}else{
				loopTimes = 28;
			}
		}
		$('ul#dropdown-year-menu').html('');
		for(var k=(currYear-5);k<=(currYear+5);k++){
			$('ul#dropdown-year-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+k+'</a></li>');
		}
		$('ul#dropdown-month-menu').html('');
		for(var i=1;i<=12;i++){
			$('ul#dropdown-month-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+i+'</a></li>');
		}
		$('ul#dropdown-day-menu').html('');
		for(var j=1;j<=loopTimes;j++){
			$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
		}
		//为新添加的元素绑定点击事件
		$("a[role='menuitem']").bind('click',function(){
			var dropdown_text = $(this).text();
			$(this).parents('ul').each(function(){
				var dropdown_mark = $(this).attr('id').split('-')[1];
				$('.dropdown span.'+dropdown_mark).text(dropdown_text);
				//month下拉按钮与day下拉按钮做联动
				if(dropdown_mark == 'month'){
					//如果点击的月份不是系统月份，那么日期下拉框菜单展示当月所有天数
					var loopTimes = 30;
					if(isContain(monthArry31,dropdown_text) == true){
						loopTimes = 31;
					}else if(isContain(monthArry30,dropdown_text) == true){
						loopTimes = 30;
					}else{
						if((parseInt($('button#dropdown-year span.year').text())%4==0&&parseInt($('button#dropdown-year span.year').text())%100!=0)||parseInt($('button#dropdown-year span.year').text())%400==0){
							loopTimes = 29;
						}else{
							loopTimes = 28;
						}
					}
					$('ul#dropdown-day-menu').html('');
					for(var j=1;j<=loopTimes;j++){
						$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
					}
				}else if(dropdown_mark == 'year'){
					$('button#dropdown-month span.month').text('1');
					$('button#dropdown-day span.day').text('1');
					$('ul#dropdown-day-menu').html('');
					for(var j=1;j<=31;j++){
						$('ul#dropdown-day-menu').append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#">'+j+'</a></li>');
					}
				}
				//为新添加的日期下拉框绑定点击事件
				$("a[role='menuitem']").bind('click',function(){
					var dropdown_text = $(this).text();
					$(this).parents('ul').each(function(){
						var dropdown_mark = $(this).attr('id').split('-')[1];
						$('.dropdown span.'+dropdown_mark).text(dropdown_text);
					});
					initInserWorkBookDate();
				});
			});
			initInserWorkBookDate();
		});
		$('table#WorkBookInfo').hide();
		$('table#addWorkBookTemplet').show();
		$('button#button-left').show();
		$('button#button-right').show();
	});
	//日期验证,生成函数
	function timeCheck(){
		$('input#Work-startTime-hour').parent('div').removeClass('has-error');
		$('input#Work-startTime-minute').parent('div').removeClass('has-error');
		$('input#Work-endTime-hour').parent('div').removeClass('has-error');
		$('input#Work-endTime-minute').parent('div').removeClass('has-error');
		var startTime_hour = $('input#Work-startTime-hour').val();
		var startTime_minite = $('input#Work-startTime-minute').val();
		var endtTime_hour = $('input#Work-endTime-hour').val();
		var endTime_minite = $('input#Work-endTime-minute').val();
		
		var timeCheckResult = new Object({});
		if(startTime_hour == parseInt(startTime_hour) && startTime_hour>=0 && startTime_hour<=24 && startTime_minite == parseInt(startTime_minite) && startTime_minite>=0 && startTime_minite<=60 && endtTime_hour == parseInt(endtTime_hour) && endtTime_hour>=0 && endtTime_hour<=24 && endTime_minite == parseInt(endTime_minite) && endTime_minite>=0 && endTime_minite<=60){
			if(startTime_hour.length == 1) startTime_hour = '0'+startTime_hour;
			if(startTime_minite.length == 1) startTime_minite = '0'+startTime_minite;
			if(endtTime_hour.length == 1) endtTime_hour = '0'+endtTime_hour;
			if(endTime_minite.length == 1) endTime_minite = '0'+endTime_minite;
			timeCheckResult.status = '1';
			timeCheckResult.startTime = $('input#inserWorkBook-date').val()+' '+startTime_hour+':'+startTime_minite;
			timeCheckResult.endTime = $('input#inserWorkBook-date').val()+' '+endtTime_hour+':'+startTime_minite;
			return timeCheckResult;
		}else{
			if(startTime_hour != parseInt(startTime_hour) || startTime_hour<0 || startTime_hour>24){
				$('input#Work-startTime-hour').parent('div').addClass('has-error');
			}
			if(startTime_minite != parseInt(startTime_minite) || startTime_minite<0 || startTime_minite>60){
				$('input#Work-startTime-minute').parent('div').addClass('has-error');
			}
			if(endtTime_hour != parseInt(endtTime_hour) || endtTime_hour<0 || endtTime_hour>24){
				$('input#Work-endTime-hour').parent('div').addClass('has-error');
			}
			if(endTime_minite != parseInt(endTime_minite) || endTime_minite<0 || endTime_minite>60){
				$('input#Work-endTime-minute').parent('div').addClass('has-error');
			}
			timeCheckResult.status = '0';
			return timeCheckResult;
		}
	}
	//非空检查
	function notNullCheck(){
		var notNullCheckResult = new Object({});
		var work_name = $('input#Work-name').val();
		var work_content = document.getElementById('Work-content').value;
		$('input#Work-name').parent('div').removeClass('has-error');
		$('input#Work-content').parent('div').removeClass('has-error');
		if((work_name!=null && work_name!='' && typeof work_name!='undefined') && (work_content!=null && work_content!='' && typeof work_content!='undefined')){
			notNullCheckResult.status = 1;
			return notNullCheckResult;
		}else{
			if(work_name==null || work_name=='' || typeof work_name=='undefined'){
				$('input#Work-name').parent('div').addClass('has-error');
				notNullCheckResult.message_workName = '工作名称为空';
			}
			if(work_content==null || work_content=='' || typeof work_content=='undefined'){
				$('input#Work-content').parent('div').addClass('has-error');
				notNullCheckResult.message_workContent = '工作内容为空';
			}
			notNullCheckResult.status = 0;
			return notNullCheckResult;
		}
		
	}
</script>
</html>
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
<title>日志、备忘录管理</title>
<jsp:include page="./jsps/base/head_base.jsp"></jsp:include>
<style type="text/css">
	div#queryItems{display:none;}
</style>
</head>
<body>
	<div class="container">
		<h4><strong><span class="glyphicon glyphicon-edit"></span>工作日志、备忘录管理</strong></h4>
		<div id="queryItems" class="col-md-12" style="padding-left:0px;">
			<div class="col-md-6"  style="padding-left:0px;margin-left:0px;">
				<table class="table table-bordered">
					<tr>
						<td id="changeText">日期：</td>
						<td style="padding-left:1%">
							<div class="dropdown" style="display:inline">
							    <button class="btn btn-info dropdown-toggle" type="button" id="dropdown-year" data-toggle="dropdown">
							    	<span class="year">2015</span><span class="caret"></span>
							    </button>
							    <ul id="dropdown-year-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-year">
								    <!-- 年列表 -->
							    </ul>
							</div>
							<span>年</span>
							<div class="dropdown" style="display:inline">
							    <button class="btn btn-info dropdown-toggle" type="button" id="dropdown-month" data-toggle="dropdown">
							    	<span class="month">01</span><span class="caret"></span>
							    </button>
							    <ul id="dropdown-month-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-month">
								   <!-- 月列表 -->
							    </ul>    
							</div>
							<span>月</span>
							<div class="dropdown" style="display:inline">
							    <button class="btn btn-info dropdown-toggle" type="button" id="dropdown-day" data-toggle="dropdown">
							    	<span class="day">03</span><span class="caret"></span>
							    </button>
							    <ul id="dropdown-day-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-day">
								    <!-- 日列表 -->
							    </ul>
							</div>
							<span>日</span>
						</td>
						<td>
							<input disabled type="input" id="" class="form-control"/>
						</td>
					</tr>
					<tr>
						<td>类型：</td>
						<td colspan="2">
							<div class="dropdown">
								<button class="btn btn-info dropdown-toggle" type="button" id="dropdown-type" data-toggle="dropdown">
									<span class="type"></span><span class="caret"></span>
								</button>
								<ul id="dropdown-type-menu" class="dropdown-menu" role="menu" aria-labelledby="dropdown-type">
									<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">工作日志</a></li>
									<li style="text-align:left" role="presentation"><a role="menuitem" tabindex="-1" href="#">备忘录</a></li>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>标题：</td>
						<td colspan="2">
							<input type="text" class="form-control"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:right">
							<button class="btn btn-success">查询</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<table class="table" style="border-bottom:1px solid #EEE9E9;">
			<tr>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td id="query-show" style="text-align:center;padding:0px;border-left:1px solid #EEE9E9;border-right:1px solid #EEE9E9;border-top:1px solid #EEE9E9;"><a href="#"><div class="dropdown"><span class="caret"></span></div></a></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
				<td style="border-top:0px"></td>
			</tr>
		</table>
		<table class="table">
			<thead>
				<th></th>
				<th>序号</th>
				<th>标题</th>
				<th>内容</th>
				<th>类型</th>
				<th>天气</th>
				<th>开始时间</th>
				<th>结束时间</th>
			</thead>
			<tr>
				<td>
					<input type="checkbox" />
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('td#query-show').bind('mouseover',function(){
			$(this).css('background','#D1EEEE');
		});
		$('td#query-show').bind('mouseout',function(){
			$(this).css('background','#ffffff');
		})
		$('td#query-show').bind('click',function(){
			var queryButtonClassName = $('td#query-show a div').attr('class');
			if(queryButtonClassName == 'dropdown'){
				$('div#queryItems').show('slowlly');
				$('td#query-show a div').attr('class','dropup');
			}else if(queryButtonClassName == 'dropup'){
				$('div#queryItems').hide('slowlly');
				$('td#query-show a div').attr('class','dropdown');
			}
		});
	});
</script>
</html>
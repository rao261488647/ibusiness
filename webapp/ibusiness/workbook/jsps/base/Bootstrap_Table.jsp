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
<jsp:include page="head_base.jsp"></jsp:include>
<style type="text/css">
</style>
<body class="container">
<div class="table-responsive">
	<table class="table table-hover table-bordered">
		<thead>
			<tr class="success">
				<td>
					<div class="col-md-10">表格标题</div>
					<div class="col-md-2 btn-group">
						<button type="button" class="btn btn-success">换肤</button>
						<!-- 这个btn-success要写在最后面 换肤已经写死 -->
						<button class="dropdown-toggle btn btn-success" type="button" id="dropdownMenu1" data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
							<li role="presentation"><a class="table-skin" role="menuitem" tabindex="-1" href="#">success</a></li>
							<li role="presentation"><a class="table-skin" role="menuitem" tabindex="-1" href="#">default</a></li>
							<li role="presentation"><a class="table-skin" role="menuitem" tabindex="-1" href="#">danger</a></li>
							<li role="presentation"><a class="table-skin" role="menuitem" tabindex="-1" href="#">warning</a></li>
						</ul>
					</div>
				</td>
			</tr>
			
		</thead>
			<tbody>
				<tr>
					<td>
						<table class="table-data table table-hover table-bordered table-condensed" style="margin:0px;">
							<thead>
								<tr class="alert alert-success" role="alert">
									<!-- 表头数据 -->
									<th draggable="true"><span class="th-text">标题1</span><a class="btn-link"><span class="pull-right caret" style="margin-top:8px;"></span></a></th>
									<th draggable="true"><span class="th-text">标题2</span><a class="btn-link"><span class="pull-right caret" style="margin-top:8px;"></span></a></th>
									<th draggable="true"><span class="th-text">标题3</span><a class="btn-link"><span class="pull-right caret" style="margin-top:8px;"></span></a></th>
									<th draggable="true"><span class="th-text">标题4</span><a class="btn-link"><span class="pull-right caret" style="margin-top:8px;"></span></a></th>
									<!-- 表头数据 -->
								</tr>
							</thead>
							<!-- 数据 -->
							<tbody>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							<tr>
								<td class="标题1">123123123123123123123123123123</td>
								<td class="标题2">123123123123123123123123123123</td>
								<td class="标题3">123123123123123123123123123123</td>
								<td class="标题4">123123123123123123123123123123</td>
							</tr>
							</tbody>
							<!-- 数据 -->
						</table>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr class="success">
					<td>
						<div class="col-md-2">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="输入关键字"/>
								<span class="input-group-btn">
									<button class="btn btn-success" type="button">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
						<div class="btn-group col-md-3 ">
							<button type="button" class="btn btn-success">
								<span class="page-text">每页</span>
								<span id="page-itemNo">10</span>
								<span class="page-text">条记录</span>
							</button>
							<button class="dropdown-toggle btn btn-success" type="button" id="dropdownMenu1" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								<li role="presentation"><a role="menuitem" tabindex="-1" href="#">10</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="#">15</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="#">20</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="#">25</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="#">30</a></li>
							</ul>
						</div>
						<div class="col-md-1" style="text-align:right">
							<a class="btn btn-link">
								<span class="glyphicon glyphicon-backward"></span>
							</a>
						</div>
						<div class="col-md-5 table-message">
							<div class="input-group">
								<span class="input-group-addon alert-success"><a class="btn-link">GO!</a>第</span>
								<input type="text" class="form-control" placeholder="当前页码"/>
								<span class="input-group-addon alert-success">页,共<span id="pageNo">120</span>页，<span id="count-itemNo">1200</span>条</span>
							</div>
						</div>
						<div class="col-md-1 panel-success" style="padding-left:0px;padding-right:0px">
							<a class="btn btn-link" style="margin-right:0px;padding-right:0px">
								<span class="glyphicon glyphicon-forward"></span>
							</a>
							<a class="btn btn-link" style="margin-right:0px;padding-right:0px">
								<span class="glyphicon glyphicon-refresh"></span>
							</a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
<script type="text/javascript">
	//juery拖拽扩展
	(function(){
		jQuery.fn.dragstart = function(handler) {  
			return this.each(function() {  
				this.addEventListener("dragstart", handler, false);  
			});  
		};  
		  
		jQuery.fn.drag = function(handler) {  
			return this.each(function() {  
				this.addEventListener("drag", handler, false);  
			});  
		};  
		  
		jQuery.fn.dragenter = function(handler) {  
			return this.each(function() {  
				this.addEventListener("dragenter", handler, false);  
			});  
		};  
		  
		jQuery.fn.dragover = function(handler) {  
			return this.each(function() {  
				this.addEventListener("dragover", handler, false);  
			});  
		};  
		  
		jQuery.fn.dragleave = function(handler) {  
			return this.each(function() {  
				this.addEventListener("dragleave", handler, false);  
			});  
		};  
		  
		jQuery.fn.drop = function(handler) {  
			var handler1 = function(event) {  
				handler(event);  
				event.preventDefault();  
				event.stopPropagation();  
			};  
			return this.each(function() {  
				this.addEventListener("drop", handler, false);  
			});  
		};  
		  
		jQuery.fn.dragend = function(handler) {  
			var handler1 = function(event) {  
				handler(event);  
				event.preventDefault();  
			};  
			return this.each(function() {  
				this.addEventListener("dragend", handler, false);  
			});  
		};  	
	})();
	$(function(){
		//拖拽功能（暂时未实现）
		$('.table>thead th').drag(function(e){
			var th_text_span = $(this).children('button').children('span.th-text').text();
			var new_table_list = $('<table class="table"></table>');
			new_table_list.append($(this));
			
			$('.'+th_text_span).each(function(){
				new_table_list.append($('<tr></tr>').append($(this)));
			});
			$('div#new-table-list-inner').html('');
			$('div#new-table-list-inner').append(new_table_list);
			$('div#new-table-list').show();
			$('div#new-table-list').css('left',(e.pageX-138)+'px').css('top',(e.pageY-15)+'px');
		});
		 
		//换肤功能
		$('a.table-skin').bind('click',function(){
			//修改后的皮肤类
			var skin_class;
			//当前要换皮肤的对象
			var curr_obj;
			//选择按钮选中的皮肤
			var table_skin = $(this).text();
			//换肤前的皮肤
			var curr_table_skin;
			curr_obj = $(this).parents('table').children('thead').children('tr').first();
			//表格标题换肤
			if(table_skin != 'default'){
				curr_table_skin = curr_obj.attr('class');
				skin_class = curr_obj.attr('class').replace(curr_table_skin,table_skin);
				curr_obj.attr('class',skin_class);
			}else{
				var curr_table_skin = curr_obj.attr('class');
				skin_class = curr_obj.attr('class').replace(curr_table_skin,'active');
				curr_obj.attr('class',skin_class);
			}
			//换肤按钮换肤
			curr_obj = curr_obj.children('td').children('div.btn-group').children('button');
			curr_obj.each(function(){
				curr_table_skin = $(this).attr('class').split('btn-')[1];
				skin_class = $(this).attr('class').replace(curr_table_skin,table_skin);
				$(this).attr('class',skin_class);
			});
			//表格表头换肤
			curr_obj = $(this).parents('table').children('tbody').children('tr').children('td').children('table').children('thead').children('tr');
			if(table_skin != 'default'){
				curr_table_skin = curr_obj.attr('class').split('alert-')[1];
				skin_class = curr_obj.attr('class').replace(curr_table_skin,table_skin);
				curr_obj.attr('class',skin_class);
			}else{
				curr_obj.attr('class','alert alert-info');
			}
			//表格尾部换肤
			curr_obj = $(this).parents('table').children('tfoot').children('tr');
			if(table_skin != 'default'){
				curr_obj.attr('class',table_skin);
			}else{
				curr_obj.attr('class','active');
			}
			//表格尾部搜索按钮换肤
			curr_obj = $(this).parents('table').children('tfoot').children('tr').children('td')
				.children('div').children('div').children('span.input-group-btn').children('button');
			curr_table_skin = curr_obj.attr('class').split('btn-')[1];
			skin_class = curr_obj.attr('class').replace(curr_table_skin,table_skin);
			curr_obj.attr('class',skin_class);
			//表格尾部每页记录数按钮换肤
			curr_obj = $(this).parents('table').children('tfoot').children('tr').children('td')
				.children('div.btn-group').children('button');
			curr_obj.each(function(){
				curr_table_skin = $(this).attr('class').split('btn-')[1];
				skin_class = $(this).attr('class').replace(curr_table_skin,table_skin);
				$(this).attr('class',skin_class);
			});
			//表格尾部当前页数信息换肤
			curr_obj = $(this).parents('table').children('tfoot').children('tr').children('td')
				.children('div.table-message').children('div').children().first();
			curr_table_skin = curr_obj.attr('class').split('alert-')[1];
			skin_class = curr_obj.attr('class').replace(curr_table_skin,table_skin);
			curr_obj.attr('class',skin_class);
			//表格尾部当前页数信息换肤
			curr_obj = $(this).parents('table').children('tfoot').children('tr').children('td')
				.children('div.table-message').children('div').children().last();
			curr_table_skin = curr_obj.attr('class').split('alert-')[1];
			skin_class = curr_obj.attr('class').replace(curr_table_skin,table_skin);
			curr_obj.attr('class',skin_class);
		});
	
	});
</script>
</html>
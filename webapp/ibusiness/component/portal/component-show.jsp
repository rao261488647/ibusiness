<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>业务模块管理</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'orgGrid',
	selectedItemClass: 'selectedItem',
	gridFormId: 'orgGridForm'
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
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <div class="row">
	<%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>
		<!-- start of main -->
		<div class="panel panel-default col-lg-10"> 
	        <div class="panel-body">
	            <div>
						<h2> 快速开发平台/业务模块组件. </h2>
						<p style="margin-left: 40px;"><strong><em>业务模块组件</em>是管理包括各个业务模块的内容。</strong></p>
						<p style="margin-left: 40px;"><strong>每个业务模块下都包含以下三方面功能：</strong></p>
						<p style="margin-left: 40px;"><strong>1.存储控制器</strong></p>
						<p style="margin-left: 40px;"><strong>2.表单设计器</strong></p>
						<p style="margin-left: 40px;"><strong>3.流程设计器</strong></p>
				</div>
			</div>
	    </div>
		<!-- end of main -->
    </div>
  </body>

</html>

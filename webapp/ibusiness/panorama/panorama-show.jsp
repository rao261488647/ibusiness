<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑</title>
    <%@include file="/common/center.jsp"%>
    <style type="text/css" title="Default">
	</style>
  </head>
  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
		<div class="panel panel-default col-lg-12" >
    <!-- -->
    
			<div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
	          <div class="panel-body">
		          <div id="search" class="content content-inner">
					  <form name="cgForm" method="post" action="customer_info-list.do" class="form-inline">
					    <div class="form-group">
			                <label for="code_table_customername">地点:</label>
			                <input type="text" name="filter_LIKES_customername" value="">
						    <a href="#" class="btn btn-primary btn-sm">查询</a>
						</div>
					 </form>
				  </div>
			  </div>
			
		    <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">全景图展示</h4></div>
	        <div class="panel-body">
				<div id="tabContent" class="tab-content">
	              <!-- 全景 -->
	              <div id="popConfTaskNodeDiv" style="height:100%;">
	                 <iframe id="panoramaIframe" src="${ctx}${urlStr}" width="100%" height="100%" ></iframe>
	              </div>
		        </div>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    //窗口改变尺寸后自适应
	/** */
	    window.onresize = function() {
		  //  var newwidth = $(window).width() - 2;
			var newheight = $(window).height() - 2;
			$("#popConfTaskNodeDiv").css('height',newheight-100);
		}
	    
		$(function(){
			$("#popConfTaskNodeDiv").css('height',$(window).height()-100);
		 });
		
    </script>
  </body>
</html>

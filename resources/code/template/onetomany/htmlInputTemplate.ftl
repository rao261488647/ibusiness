<!DOCTYPE html>
<html>
  <head>
    <title>${confForm.formTitle}编辑</title>
    <!-- jquery -->
    <script type="text/javascript" src="../../plugin/jquery/jquery.min.js"></script>
    <!-- bootstrap -->
    <link type="text/css" rel="stylesheet" href="../../plugin/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="../../plugin/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
  </head>
  <body>
    <script type="text/javascript">
        <!-- 数据初始化 -->
		function initData() {
			// 调用初始化方法
			var id = GetQueryString("id");
			var initdata = "{id:'"+id+"'}";
			var urlstr = urlpath + "/rs/ws-${entityName?uncap_first}/${entityName?uncap_first}-input?jsonStr="+initdata;
			$.ajax({
				type: "post",
			    url: urlstr,
				dataType: "json",
			//	data: initdata,
				success: function(json){
					// 进度列表
					var datas = json.datas;
					// 
					var content = "";
					content = content + '<div class="form-group">';
					content = content + '  <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">'+datas.name+'</div>';
					content = content + '  <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9"><input id="code-name" type="text" name="name" value="'+datas.name+'" class="text form-control input-sm required"  ></div>';
					content = content + '</div>';
					$("#datas").append(content);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("服务器异常请稍后再试。");
				}
			});
		}
		initData();
    </script>
    <div class="col-lg-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">测试练习表单编辑</h4></div>
        <div id="datas" class="panel-body">

        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

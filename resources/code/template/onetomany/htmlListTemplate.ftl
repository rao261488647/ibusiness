<!DOCTYPE html>
<html>

  <head>
    <title>${confForm.formTitle}列表</title>
    <!-- jquery -->
    <script type="text/javascript" src="../../plugin/jquery/jquery.min.js"></script>
    <!-- bootstrap -->
    <link type="text/css" rel="stylesheet" href="../../plugin/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="../../plugin/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/style.css" >
    
    <script type="text/javascript">
        <!-- 数据初始化 -->
		function initData() {
			// 调用初始化方法
			var initdata = "{pageNo:1, pageSize:10}";
			var urlstr = urlpath + "/rs/ws-${entityName?uncap_first}/${entityName?uncap_first}-list?jsonStr="+initdata;
			$.ajax({
				type: "post",
			    url: urlstr,
				dataType: "json",
			//	data: initdata,
				success: function(json){
					// 进度列表
					var dataList = json.datas;
					// 先删除再插入
				//	$("#datas :last-child").remove();
					for (var i=0; i<dataList.length; i++) {
						var content = "";
						content = content + '<tr>';
						content = content + '<td class="normal" width="9%"><img src="../images/listpic.jpg" class="list-img" /></td>';
					    
			            content = content + '<td class="normal" width="91%">';
						content = content + '<div class="list-title" onclick="window.location.href=\'test-input.html?id='+dataList[i].id+'\'" >';
						<#list columns as po>
				            <#if po.fcDisplay="1">
						content = content + '<span class="list-title-span">${po.filedComment}:'+dataList[i].${po.fieldName}+'</span>';
						    </#if>
				        </#list>
						content = content + '</div></td>';
				        
						content = content + '</tr>';
						content = content + '<tr>';
						content = content + '<td class="border-bottom-line"><span class="border-bottom"></span></td>';
						content = content + '<td class="border-bottom-line"><span class="border-bottom"></span></td>';
						content = content + '</tr>';
						$("#datas").append(content);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("服务器异常请稍后再试。");
				}
			});
		}
		initData();
    </script>
  </head>

  <body style="background:#ddd" >
    <div class="container-fluid">
	    <table id="datas" width="100%" border="0" class="list-table">
	    
	    </table>
    </div>
    <!-- 模态框 -->
	  <div id="modalInput" class="modal fade" tabindex="-1" style="display: none;" data-backdrop="static">
		  <div class="modal-dialog modal-lg">
			  <div class="modal-content" style="text-align: center;height: 600px">
			  </div>
		  </div>
	  </div>
	  
  </body>
</html>

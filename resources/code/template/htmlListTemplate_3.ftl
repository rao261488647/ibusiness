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
						  content = content + '<li class="share-info"><img class="share-head" src="../images/4.jpg" />';
					      content = content + '<div class="share-title"><span class="share-nicheng">错叔</span></div>';
					      content = content + '<div class="share-comment">';
					      content = content + '<span class="share-zan"><img class="share-zan-img" src="../images/zan.png" /></span>';
					      content = content + '<span class="share-pinglun">3</span>';
					      content = content + '</div>';
					      content = content + '<div class="share-comment">';
					      content = content + '<span class="share-zan"><img class="share-zan-img" src="../images/pinglun.png" /></span>';
					      content = content + '<span class="share-pinglun">3</span>';
					      content = content + '</div>';
					      content = content + '<div class="share-picture"><img class="share-pictrue-img" src="../images/listpic.jpg" /></div>';
					      <#list columns as po>
					          <#if po.fcDisplay="1">
					      content = content + '<div class="share-text">${po.filedComment}:'+dataList[i].${po.fieldName}+'</div>';
					          </#if>
					      </#list>
					      content = content + '</li>';
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

  <body style="background:#d1d4db">
	 <div class="container-fluid1">
	    <ul id="datas" class="share-list">
	    
	    </ul>
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

<%@ page language="java" pageEncoding="UTF-8"%>
<!-- tree -->
<link rel="stylesheet" href="${ctx}/plugin/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/plugin/ztree/jquery.ztree.all-3.5.min.js"></script>

<!-- start of sidebar -->
<div class="col-lg-2">
	<div class="panel-heading">
		<a class="accordion-toggle" data-toggle="collapse"
			data-parent="#m-sidebar" href="#collapse-group"> <i
			class="icon-user"></i> <span class="title"><h4 class="panel-title glyphicon glyphicon-paperclip">业务模块组件</h4></span>
		</a>
	</div>
	<div class="panel-body">
		<div id="collapse-group">
			<ul id="treeMenu" class="ztree"></ul>
		</div>
	</div>

	<footer id="m-footer" class="text-center">
		<hr>
		&copy;iBusiness
	</footer>
</div>
<!-- end of sidebar -->

<script type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url: "${ctx}/rs/component/left-menu-tree?packageName=${packageName}&typeId=${typeId}&parentId=${parentId}"
			},
			callback: {
				onClick: function(event, treeId, treeNode) {
					location.href = '${ctx}/component/component-action.do?packageName=' +  treeNode.packageName + '&typeId=' + treeNode.typeId
							   + '&tableName=' + treeNode.tableName + '&formId=' + treeNode.formId + '&flowId='  + treeNode.flowId;
				}
			}
		};

		var zNodes =[
		             ];

		$(function(){
			$.fn.zTree.init($("#treeMenu"), setting, zNodes);
		});
</script>

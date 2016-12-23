<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
	
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/uuid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/js/ArrayList.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/HashMap.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/html5/excanvas.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/js/CruciateAuxiliaryLine.js"></script>
		<!--  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/js/AdjustValve.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/js/CyclePump.js"></script>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/js/eventDraw.js"></script>
		<style type="text/css">
			.tool {height: 30px; float: left; cursor: pointer; padding: 5px; filter: alpha(Opacity = 70); opacity: 0.7; }
		</style>
	<!-- ================================================================================= -->
	<script type="text/javascript">
		$(function() {
		    $("#bpmBaseForm").validate({
		        submitHandler: function(form) {
					bootbox.animate(false);
					var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
		            form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		});
		// 任务节点基础信息
		function taskNodeBase(){
			// 设置显示标签
			$("#taskNodeBaseTabs").attr("class","active");
			$("#popConfTaskNodeTabs").attr("class"," ");
			// 
			$("#taskNodeBaseDiv").attr("class","tab-pane fade active in ");
			$("#popConfTaskNodeDiv").attr("class","tab-pane fade");
		}
		// 任务节点弹出层
		function popConfTaskNode(){
		    // 设置显示标签
		    $("#taskNodeBaseTabs").attr("class"," ");
		    $("#popConfTaskNodeTabs").attr("class","active");
		   // 
			$("#taskNodeBaseDiv").attr("class","tab-pane fade ");
			$("#popConfTaskNodeDiv").attr("class","tab-pane fade active in ");
		}
		
    </script>
	<!-- start of main -->
	<div class="col-lg-1"></div>
	<div class="panel panel-default col-md-10" >
	        <input id="bpmId" type="hidden" name="bpmId" value="${bpmId}">
	        <input id="packageName" type="hidden" name="packageName" value="${packageName}">
	        <input id="taskNodeId" type="hidden" name="taskNodeId"  >
	        <!--  -->
	        	<div id="toolCanvas"  style="position: relative; height: 40px; width:1080px; border: 1px solid;">
	        	    <div class="tool" title="文本" id="CyclePump">
						<img name="icon" id="xhbImg" height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/img/word.jpg" />
					</div>
					<div class="tool" title="输入框" id="AdjustValve">
						<img name="icon" id="iconImg" height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/img/text001.png" />
					</div>
					<div class="tool" title="按钮" id="CyclePump">
						<img name="icon" id="xhbImg" height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/img/btn001.png" />
					</div>
					<div class="tool" title="灯" id="CyclePump">
						<img name="icon" id="xhbImg" height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/plc-control/img/png-0803.png" />
					</div>
					
						<div class="tool"  title="选择" id="select">
							<img height="30px" width="30px" name="select" id="selectImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/select.jpg" />
						</div>
						<!--  -->
						<div  style="height: 30px; float: left; cursor: pointer; padding: 5px; filter: alpha(Opacity = 100); opacity: 1;" >
						    <label class="control-label" for="select_flowname">弹出页面:</label>
							<select id="select_flowname" name="flowId" >
							      <option value="" selected>请选择页面:</option>
							</select>
						</div>
						<!--  -->
						<div class="tool"  title="查询" id="search"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/query.gif" /></div>
						<div class="tool"  title="保存" id="save"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/save.gif" /></div>
						<div class="tool"  title="删除所有" id="delete"><img height="30px" width="30px"  src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/delete.png" /></div>
						<div style="float: right;padding-top:8px;padding-right:5px">
							<a href="${ctx}/bpm-process/bpm-process-input.do?packageName=${packageName}&bpmId=${bpmId}" class="btn btn-primary btn-sm " >返回</a>
						</div>
					</div>
					
					<!-- 基础弹出form页面 -->
					<div id ="baseForm"  style="width: 200px;position: absolute; top: 330; left: 400; z-index: 1000; text-align: center; background-color: #CFDFEF; display: none;">
						<table style="width:200px;">
							<tr><td><label >id</label></td><td align="left">
							<input id="baseId"  style="width:100px;" type="text" value="" readonly="readonly"> </td></tr>
							<tr><td><label >标题</label></td><td align="left">
							<input id ="baseTitle"  style="width:100px;" type="text" value=""></td></tr>
							<tr><td><label >x坐标</label></td><td align="left">
							<input id ="baseX"  style="width:100px;" type="text" disabled/></td></tr>
							<tr><td><label >y坐标</label></td><td align="left">
							<input id = "baseY"  style="width:100px;" type="text" value="" disabled></td></tr>
							<tr><td>操作</td>
								<td>
									<a href="#" id = "addBase" >确定</a>
									<a href="#" id = "deleteBase" >删除</a>
									<a href="#" onclick="javascript:$('#baseForm').hide(300)">关闭</a>
								</td>
							</tr>
						</table> 
					</div>
					
					<!--  判断节点弹出层  -->
				    <div id="gatewayDiv" class="modal fade"  style="top:10%;">
					    <div class="modal-dialog">
						    <div class="modal-content">
							      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal"><span>&times;</span><span class="sr-only">Close</span></button>
							      </div>
							      <div class="modal-body"  style="height:200px;" >
						                <div class="form-group">
                                            <label class="col-lg-4 control-label" for="code-remark">判断节点ID:</label>
                                            <div class="col-lg-6">
	                                            <input id="gatewayId" type="text" name="gatewayId" class="form-control" readonly="readonly">
	                                        </div>
						                </div>
						                <div class="form-group">
                                            <label class="col-lg-4 control-label" for="code-remark">下一节点ID:</label>
                                            <div class="col-lg-6">
	                                            <select id="nextNodeId" name="nextNodeId" class="form-control" >
									              <option value="" >请选择</option>
		          								</select>
                                            </div>
						                </div>
						                <div class="form-group">
                                            <label class="col-lg-4 control-label" for="code-remark">计算公式:</label>
                                            <div class="col-lg-6">
                                                <textarea class="form-control" id="gatewayInfo" name="gatewayInfo" rows="2" >${gatewayInfo}</textarea>
                                            </div>
						                </div>
							            </div>
						      </div>
						      <div class="modal-footer">
						          <a href="#"  id = "submitGateway" class="btn btn-primary btn-sm"  >确定</a>
						          <button class="btn btn-primary btn-sm" data-dismiss="modal" >关闭</button>
						      </div>
					    </div>
				    </div>
					
					<!-- 任务节点弹出层 -->
				    <div id="taskNodeDiv" class="modal fade" style="top:10%;" >
				        <div class="col-md-2"></div>
			            <div class="col-md-8">
						    <div class="modal-content">
							      <div class="modal-header">
							          <button type="button" class="close" data-dismiss="modal"><span >&times;</span><span class="sr-only">Close</span></button>
							      </div>
							      <div class="modal-body" style="height:450px;" >
							              <ul class="nav nav-tabs">
											  <li id="taskNodeBaseTabs" class=""><a href="#" onclick="taskNodeBase();" >节点基础信息</a></li>
											  <li id="popConfTaskNodeTabs" class=""><a href="#" onclick="popConfTaskNode();" >节点列设置</a></li>
										  </ul>
										  <div id="tabContent" class="tab-content">
										        <!-- 基础信息sheet -->
												<div id="taskNodeBaseDiv" class="tab-pane fade ">
												    <div class="panel panel-default col-lg-10">
													<div class="panel-body">
								                        <div class="form-group">
				                                            <label class="col-lg-4 control-label" for="code-remark">结点ID:</label>
				                                            <div class="col-lg-6">
					                                            <input id="nodeId" type="text" name="nodeId" class="form-control" readonly="readonly">
					                                        </div>
										                </div>
							                            <div class="form-group">
				                                            <label class="col-lg-4 control-label" for="code-remark">结点标题:</label>
				                                            <div class="col-lg-6">
					                                            <input id="taskNodeTitle" type="text" name="taskNodeTitle" class="form-control" >
					                                        </div>
										                </div>
										                <div class="form-group">
				                                            <label class="col-lg-4 control-label" for="code-remark">结点名:</label>
				                                            <div class="col-lg-6">
					                                            <input id="taskNodeName" type="text" name="taskNodeName" class="form-control" >
					                                        </div>
										                </div>
										                <br/>
										                <div class="form-group">
												          <a href="#"  id = "taskNodeSave" class="btn btn-primary btn-sm"  >确定</a>
												          <button class="btn btn-primary btn-sm" data-dismiss="modal" >关闭</button>
												      </div>
											      </div>
											      </div>
								                </div>
								                <!-- 节点对应字段sheet -->
								                <div id="popConfTaskNodeDiv" class="tab-pane fade" style="height:400px;" >
						                              <iframe id="taskNodeIframe" src="" width="100%" height="100%" ></iframe>
						                        </div>
							               </div>
						           </div>
						           <div class="modal-footer">
						                    <button class="btn btn-primary btn-sm" data-dismiss="modal" >关闭</button>
						           </div>
					            </div>
						  </div>
				    </div>
					
					<!-- 画图面板 -->
					<canvas id="canvas" style="border:1px solid #DDDDDD"></canvas>
        	</div>
    </div>
	<!-- end of main -->
		<script type="text/javascript">
			var elements = new ArrayList();
			var canvas = null;
			var ctx = null;
			var width = 1080;
			var height = 500;
			var deviceTyleList = null;
			/**
			 *	页面初始化
			 */
			function init(){
				canvas = document.getElementById("canvas");  
				ctx = canvas.getContext("2d"); 
				//设置场景的大小
				canvas.width = width;
				canvas.height = height;
				
				start();  
				//开启定时器
				setInterval( start,100 );
			}
			/**
			 * 每300毫秒调用这个方法一次
			 */
			function start(){
				if( ctx != null ){
					//清空画板
					ctx.clearRect(0,0,width,height);
					//将背景画板涂白 ctx.fillStyle='#DDDDDD';
					ctx.fillStyle='#FFFFFF';
					ctx.fillRect( 0,0,width,height );	//填充颜色 x y坐标 宽 高
					for(var i=0;i<elements.size();i++){
						var ele = elements.get(i);
						if (null != ele && "{}" != ele) {
							//绘制动画下效果
							ele.animation();
						}
					}
				}
			}
			// 
			init();
		</script>
	
  </body>
</html>

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
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    <div class="row">
	    <%@include file="/ibusiness/component/portal/component-leftmenu.jsp"%>
	
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/uuid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/ArrayList.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/HashMap.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/html5/excanvas.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/CruciateAuxiliaryLine.js"></script>
		<!--  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/StartNode.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/EndNode.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Gateway.js"></script> 
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/ReviveScene.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Lane.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/TaskNode.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Texts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Pipeline.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/CalculatePos.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Line.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/eventDraw.js"></script>
	<!-- ================================================================================= -->
	<script type="text/javascript">
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
		// ===================================================================
		
		// ===================================================================
    </script>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10" >
	    <div class="row">
	        <!-- 工具条 -->
	        <div class="col-lg-12">
	        <nav class="navbar navbar-default">
	            <div class="navbar-form navbar-left" >
				    <label class="control-label" for="select_flowname">关联流程:</label>
				    <label>${bpmProcess.flowTitle}</label>
				    <input type="hidden" name="flowId" value="${bpmProcess.id}">
				</div>
				<button class="btn btn-primary btn-sm" onclick="searchChartInfo('${bpmProcess.id}');" ><span class="glyphicon glyphicon-search"></span>查询</button>
				<button class="btn btn-primary btn-sm" onclick="saveChart('${bpmProcess.id}');" ><span class="glyphicon glyphicon-tasks"></span>保存</button>
				<button class="btn btn-primary btn-sm" onclick="deleteChart('${bpmProcess.id}');" ><span class="glyphicon glyphicon-trash"></span>删除</button>
				<!-- 
				<div class="navbar-form navbar-left" title="查询" id="search"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/query.gif" /></div>
				<div class="navbar-form navbar-left" title="保存" id="save"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/save.gif" /></div>
				<div class="navbar-form navbar-left" title="删除所有" id="delete"><img height="30px" width="30px"  src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/delete.png" /></div>
				 -->
				<div class="navbar-right">
				    <a href="${ctx}/bpm-process/bpm-process-input.do?packageName=${packageName}&bpmId=${bpmId}" class="btn btn-primary btn-sm " >返回</a>
					<input type="radio" name="animated" id="animated" value="1">显示动画
					<input type="radio" name="animated" id="animated" value="0" checked>不显示动画
				</div>
			</nav>
	        </div>
	        
	        <div class="col-lg-1">
					<div class="tool"  title="泳道" id="addLane">
					    <img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/lanebg.png" />
					</div>
					<div class="tool"  title="开始" id="StartNode">
						<img height="30px" width="30px" name="startNode" id="startNodeImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/startnode.png" />
					</div>
					<div class="tool"  title="任务节点" id="TaskNode">
						<img height="30px" width="30px" name="taskNode" id="taskNodeImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/tasknode.png" />
					</div>
					<div class="tool"  title="结束" id="EndNode">
						<img height="30px" width="30px" name="endNode" id="endNodeImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/endnode.png" />
					</div>
					<div class="tool"  title="线" id="Pipeline">
						<img height="30px" width="30px" name="line" id="lineImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/waterflow.png" />
					</div>
					<div class="tool"  title="判断" id="Gateway">
						<img height="30px" width="30px" name="gateway" id="gatewayImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/gateway.png" />
					</div>
					<div class="tool"  title="选择" id="select">
						<img height="30px" width="30px" name="select" id="selectImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/select.jpg" />
					</div>
	        </div>
	        <div id="canvasDiv" class="panel panel-default col-lg-11" >
		        <input id="bpmId" type="hidden" name="bpmId" value="${bpmId}">
		        <input id="packageName" type="hidden" name="packageName" value="${packageName}">
		        <input id="taskNodeId" type="hidden" name="taskNodeId"  >
		        <!--  -->
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
        </div>
    </div>
	<!-- end of main -->
		<script type="text/javascript">
			var elements = new ArrayList();
			var canvas = null;
			var ctx = null;
			var width = document.getElementById("canvasDiv").offsetWidth - 30;
// $("#content").height()， offsetHeight
			var height = $(document).height();
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
			//窗口改变尺寸后自适应
			/* */
			window.onresize = function() {
			//	var newwidth = $(document).width() - 2 * canvas.offsetLeft;
			    var newwidth = $(window).width() - 2 * canvas.offsetLeft;
				var newheight = $(window).height() - 2 * canvas.offsetTop;
				canvas.width = newwidth;
				canvas.height = newheight;
				//放大、缩小
			//	ctx.scale(newwidth / width, newheight / height);
				
			//	width = newwidth;
			//	height = newheight;
				//alert( "width:"+width +",height:"+height);
			}
			/* 
			$(window).resize(function (){
			     alert('窗口大小');
			 });
			*/
		</script>
  </body>
</html>

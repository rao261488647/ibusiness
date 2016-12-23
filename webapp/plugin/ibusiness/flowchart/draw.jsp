<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=10" />
	<title>在线绘图页面</title>

	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/uuid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/ArrayList.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/HashMap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/html5/excanvas.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/CruciateAuxiliaryLine.js"></script>
	<!--  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/StartNode.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/EndNode.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/ReviveScene.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Lane.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/TaskNode.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Texts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Pipeline.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/CalculatePos.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/Line.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/js/eventDraw.js"></script>
	<style type="text/css">
		.tool {
		    height: 30px; float: left; cursor: pointer; padding: 5px; filter: alpha(Opacity = 70); opacity: 0.7;
		}
		.popDiv {
			position: absolute;
			z-index: 1000;
			text-align: center;
			display: none;
		}
	</style>
	<script type="text/javascript">
		/**
		 * 选择颜色
		 */
		function selectColor( selectObj ){
			selectObj.style.background=selectObj.value;
			//强制失去焦点
			$(selectObj).blur();
		}
	</script>
</head>
<body onload="init();">
	<div id="toolCanvas"  style="position: relative; height: 40px; width:1080px; border: 1px solid;">
		<div class="tool"  title="泳道" id="addLane"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/lanebg.png" /></div>
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
		<div class="tool"  title="选择" id="select">
			<img height="30px" width="30px" name="select" id="selectImg" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/select.jpg" />
		</div>
		<!--  -->
		<div  style="height: 30px; float: left; cursor: pointer; padding: 5px; filter: alpha(Opacity = 100); opacity: 1;" >
		    <label class="control-label" for="select_flowname">关联流程:</label>
			<select id="select_flowname" name="flowId" >
			      <option value="" selected>请选择流程:</option>
			</select>
		</div>
		<!--  -->
		<div class="tool"  title="查询" id="search"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/query.gif" /></div>
		<div class="tool"  title="保存" id="save"><img height="30px" width="30px" src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/save.gif" /></div>
		<div class="tool"  title="删除所有" id="delete"><img height="30px" width="30px"  src="<%=request.getContextPath()%>/plugin/ibusiness/flowchart/img/delete.png" /></div>
		<div style="float: right;padding-top:8px;padding-right:5px">
			<input type="radio" name="animated" id="animated" value="1">显示动画
			<input type="radio" name="animated" id="animated" value="0" checked>不显示动画
		</div>
	</div>
	<!-- 基础弹出form页面 -->
	<div id ="baseForm"  style="width: 200px;position: absolute; top: 330; left: 400; z-index: 1000; text-align: center; background-color: #CFDFEF; display: none;">
		<table style="width:200px;">
			<tr><td><label >id</label></td><td align="left"><input id ="baseId"  style="width:100px;" type="text" value="" readonly="readonly"> </td></tr>
			<tr><td><label >标题</label></td><td align="left"><input id ="baseTitle"  style="width:100px;" type="text" value=""></td></tr>
			<tr><td><label >x坐标</label></td><td align="left"><input id ="baseX"  style="width:100px;" type="text" disabled/></td></tr>
			<tr><td><label >y坐标</label></td><td align="left"><input id = "baseY"  style="width:100px;" type="text" value="" disabled></td></tr>
			<tr><td>操作</td>
				<td>
					<a href="#" id = "addBase" >确定</a>
					<a href="#" id = "deleteBase" >删除</a>
					<a href="#" onclick="javascript:$('#baseForm').hide(300)">关闭</a>
				</td>
			</tr>
		</table> 
	</div>
	
	<!-- 任务节点 -->
	<div id ="taskNodeForm"  style="width: 200px;position: absolute; top: 330; left: 400; z-index: 1000; text-align: center; background-color: #CFDFEF; display: none;">
		<table style="width: 200px;">
			<tr><td>id</td><td align="left"><input id ="taskNodeId" type="text" value="" readonly="readonly"> </td></tr>
			<tr><td>名称</td><td align="left"><input id ="taskNodeName" type="text" value=""></td></tr>
			<tr><td>x坐标</td><td align="left"><input id ="taskNodeX" type="text" value="" disabled></td></tr>
			<tr><td>y坐标</td><td align="left"><input id = "taskNodeY" type="text" value="" disabled></td></tr>
			<tr><td>操作</td>
				<td>
					<a href="#" id = "addTaskNode" >确定</a>
					<a href="#" id = "deleteLamp" >删除</a>
					<a href="#" onclick="javascript:$('#taskNodeForm').hide(300)">关闭</a>
				</td>
			</tr>
		</table> 
	</div>
	
	<!-- 节点设置页面 -->
	<div id="taskNodeDiv" class="popDiv" >
		<iframe id="controlIframe" src="" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" ></iframe>
	</div>
	
	<!-- 线弹出页面 -->
	<div id ="lineForm"  style="width: 250px;position: absolute; top: 330; left: 400; z-index: 1000; text-align: center; background-color: #CFDFEF; display: none;">
		<table style="width: 250px;">
			<tr><td>id</td><td align="left"><input id ="lineId" readonly="readonly"> </td></tr>
			<tr><td>名称</td><td align="left"><input id ="lineN" type="text" value="" readonly> </td></tr>
			<tr><td>x坐标</td><td align="left"><input id ="lineX"> </td></tr>
			<tr><td>y坐标</td><td align="left"><input id = "lineY"> </td></tr>
			<tr><td>宽度</td><td align="left"><input id = "lineW"> </td></tr>
			<tr><td>长度</td><td align="left"><input id = "lineL"> </td></tr>
			<tr><td>颜色</td>
				<td align="left">
					<SELECT id = "fillColor" onchange="javascript:selectColor(this)"> 
						<option value="red" style="background:red" selected>&emsp;&emsp;&emsp;&emsp;</option>
						<option value="#FDB7B9" style="background:#FDB7B9" selected>&emsp;&emsp;&emsp;&emsp;</option>
						<option value="white" style="background:white">&emsp;&emsp;&emsp;&emsp;</option>
						<option value="green" style="background:green">&emsp;&emsp;&emsp;&emsp;</option>
						<option value="yellow" style="background:yellow">&emsp;&emsp;&emsp;&emsp;</option>
						<option value="#00CCFF" style="background:#00CCFF">&emsp;&emsp;&emsp;&emsp;</option>	
						<option value="#0099FF" style="background:#0099FF">&emsp;&emsp;&emsp;&emsp;</option>	
					</SELECT>
				</td>
			</tr>
			<tr><td>流向</td>
				<td align="left">
					<select id="flowTo">
 
					</select>
				</td>
			</tr>
			<tr><td>箭头</td>
				<td align="left">
					<select id="isArrow">
						 
					</select>
				</td>
			</tr>
			<tr><td>操作</td>
				<td>
					&nbsp;&nbsp;
					<a href="#" id="addLine">确定</a>
					&nbsp;&nbsp;
					<a href="#" id="deleteLine">删除</a>
					&nbsp;&nbsp;
					<a href="#" onclick="javascript:$('#lineForm').hide(300)">关闭</a>
				</td>
			</tr>
		</table> 
	</div>
	<!-- 画图面板 -->
	<canvas id="canvas" style="border:1px solid #00F"></canvas>  
	
</body>
</html>

<script language="JavaScript">
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
		/*
		width = $(document).width() - 2 * canvas.offsetLeft;
		height = $(document).height() - 2 * canvas.offsetTop;
		*/
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
			//将背景画板涂白
			ctx.fillStyle='#DDDDDD';
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

//窗口改变尺寸后自适应
/* 
window.onresize = function() {
	var newwidth = $(document).width() - 2 * canvas.offsetLeft;
	var newheight = $(document).height() - 2 * canvas.offsetTop;
	canvas.width = newwidth;
	canvas.height = newheight;
	//放大、缩小
	ctx.scale(newwidth / width, newheight / height);
	
	width = newwidth;
	height = newheight;
	
	//alert( "width:"+width +",height:"+height);
}
*/
</script>

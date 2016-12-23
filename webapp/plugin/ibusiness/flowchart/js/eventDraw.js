$(function() {
    //用于标示鼠标是否点击（按下）
	var mousedown = false;
	var mouseImage = null;
	var elementId = 1;
	var drawPipeline = null;
	var isQuery = false;
	var mouseClickElemetId = null; // 页面当前选中的对象的ID
    var lineMap = new HashMap(); // 线的map
    /**
     * 取得参数
     */
    var getParam = function(name){
    	       var search = document.location.search;
    	        var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    	        var matcher = pattern.exec(search);
    	        var items = null;
    	        if(null != matcher){
    	                try{
    	                        items = decodeURIComponent(decodeURIComponent(matcher[1]));
    	                }catch(e){
    	                        try{
    	                                items = decodeURIComponent(matcher[1]);
    	                       }catch(e){
    	                                items = matcher[1];
    	                        }
    	                }
    	        }
    	        return items;
    	};
    	
	/**
	 * 查询【流程信息】下拉列表信息
    function getFlowList(){
		//向服务端获取二级菜单列表
    	var urlPath = '/'+window.location.pathname.split("/")[1] +'/flowchart/flow-list.do?flowId='+getParam("bpmId");
    	$.ajax({
			url:urlPath,
			success:function(responseText){
				$('#select_flowname').append(responseText);
			}
		});
	}
    getFlowList();
    */
	 
	/**
	 * 获得画板的移动事件
	 */
	$('#canvas').mousemove(function(e) {
		var mouseX = e.pageX - $('#canvas').offset().left;
		var mouseY = e.pageY - $('#canvas').offset().top;
		// 判断当前是哪个控件对象
		for(var i=0;i<elements.size();i++){
		    // 判断控件对象或者控件子对象
			var isRange = elements.get(i).clickRange(mouseX,mouseY);
		    elements.get(i).moveOver = isRange;
		}
		return false;
	});
	
	/**
	 * ----------------------------------------------------------------------
	 * 鼠标单击事件处理
	 * ----------------------------------------------------------------------
	 */
	$('#canvas').click(function (e) {
	    // 鼠标单击位置x轴坐标 
		var mouseX = e.pageX - $('#canvas').offset().left;
		// 鼠标单击位置y轴坐标 
		var mouseY = e.pageY - $('#canvas').offset().top;
		// 清除之前选中控件的状态
		if (mouseClickElemetId != null) {
		    if (null != elements.getId(mouseClickElemetId)) {
		        elements.getId(mouseClickElemetId).mouseClick = 0;
		    }
		    var oldMouseClickElemetId = mouseClickElemetId;
		    mouseClickElemetId = null;
		}
		// 遍历画面中所有对象,判断当前点击的是哪个对象
		for(var i=0;i<elements.size();i++){
		    // 判断取得当前点击的对象
			var isRange = elements.get(i).clickRange(mouseX,mouseY);
			if( isRange > 0){
				// 隐藏弹出窗口
				hideForm();
				// 如果不是点击主对象 那么进行子按钮的业务处理 并且 主对象以选中
				if (1 != isRange && elements.get(i).id == oldMouseClickElemetId) {
					elements.get(i).moveOver = isRange;
					elements.get(i).clickEdit(mouseX,mouseY);
				}
				// 显示当前对象的单击选中框
				elements.get(i).mouseClick = isRange;
				// 选中当前对象
				if (1 == isRange) {
				    mouseClickElemetId = elements.get(i).id;
				}
				return true;
			}
		}
		return false;
	});
	/**
	 * ----------------------------------------------------------------------
	 * 鼠标双击事件处理
	 * ----------------------------------------------------------------------
	 */
	$('#canvas').dblclick(function (e) {
	    // 鼠标单击位置x轴坐标 
		var mouseX = e.pageX - $('#canvas').offset().left;
		// 鼠标单击位置y轴坐标 
		var mouseY = e.pageY - $('#canvas').offset().top;
		// 遍历画面中所有对象,判断当前点击的是哪个对象
		for(var i=0;i<elements.size();i++){
		    // 判断取得当前点击的对象
			var isRange = elements.get(i).clickRange(mouseX,mouseY);
			if( isRange > 0){
				// 隐藏弹出窗口
				hideForm();
				
				// 双击主对象 显示弹出框
				elements.get(i).moveOver = isRange;
				if (1 == isRange) {
				  elements.get(i).clickEdit(mouseX,mouseY);
				}
				return true;
			}
		}
		return false;
	});
	
	/**
	 * ----------------------------------------------------------------------
	 * 按下鼠标时触发
	 * ----------------------------------------------------------------------
	 */
	$('#canvas').mousedown(function(e) {
		var mouseX = e.pageX - $('#canvas').offset().left;
		var mouseY = e.pageY - $('#canvas').offset().top;
		//设定为按下状态
		mousedown = true;
		// 左上角提示鼠标操作信息
		if( elements.getId('console') != null ){
			elements.getId('console').realData = '鼠标按下：'+mouseX+','+mouseY;
		}
		//取消【画阀门】【画换热器】【画补水泵】【画水箱】等
		if( mouseImage != null ){
			mouseImage = null;
			//初始化工具栏
			initTools();
		}
		//记住起始坐标
		if( drawPipeline != null ){
			drawPipeline.x = mouseX;
			drawPipeline.y = mouseY;
		}
		// 遍历画面中所有对象,判断当前点击的是哪个对象
		for(var i=0;i<elements.size();i++){
		    // 判断取得当前点击的对象
			var isRange = elements.get(i).clickRange(mouseX,mouseY);
			// 如果是当前已经选中的对象,那么进行拖动
			if( isRange > 0 && elements.get(i).id == mouseClickElemetId){
			    // 选中的对象
			    mouseImage = elements.get(i);
			    // 隐藏弹出窗口
				hideForm();
		   }
	   }
	});
	
	//松开鼠标时候触发
	$('#canvas').mouseup(function(e) {
		var mouseX = e.pageX - $('#canvas').offset().left;
		var mouseY = e.pageY - $('#canvas').offset().top;
		mousedown = false;
		if( elements.getId('console') != null ) {
			elements.getId('console').realData = '鼠标抬起：'+mouseX+','+mouseY;
		}
		// 删除参与移动的线重新画
		var node = elements.getId(mouseClickElemetId);
		if (null != mouseClickElemetId && null!=node) {
		    // 指向当前节点的线
			if (null!=node.headLineIds) {
				    var arrayKey = node.headLineIds.keys();
					for (var i=0;i<arrayKey.length;i++) {
				        var lineId = arrayKey[i];
				        var line = elements.getId(lineId);
				        if (line) {
				        	if (null == lineMap.get(line.startElmId+"|"+line.endElmId)) {
				        		  lineMap.put(line.startElmId+"|"+line.endElmId, "1");
				        	}
				        	// 删除线关联的对象中的 afterLineId
							var lineStartElm = elements.getId(line.startElmId);
							lineStartElm.afterLineIds.remove(line.id);
				            // 删除线
				            elements.removeId(lineId);
				        }
				    };
				    // 清空
				    node.headLineIds.clear();
			}
		    // 当前节点指向下一节点的线
		    if (node.afterLineIds && node.afterLineIds.size() > 0) {
		    	    var arrayKey = node.afterLineIds.keys();
			    	for (var i=0;i<arrayKey.length;i++) {
				        var lineId = arrayKey[i];
				        var line = elements.getId(lineId);
				        // 
				        if (line) {
				            if (null == lineMap.get(line.startElmId+"|"+line.endElmId)) {
				                lineMap.put(line.startElmId+"|"+line.endElmId, "2");
				            }
				            // 删除线关联的对象中的 endLineId
							var lineEndElm = elements.getId(line.endElmId);
							lineEndElm.headLineIds.remove(line.id);
				            // 删除
				            elements.removeId(lineId);
				        }
				    };
				    // 清空
				    node.afterLineIds.clear();
		    }
		    // 所有线统一重新画
		    if (lineMap.size() > 0) {
			    	for (var i=0;i<lineMap.size();i++) {
				        var strs = lineMap.element(i).key.split("|");
					    var startElm = elements.getId(strs[0]);
					    var endElm = elements.getId(strs[1]);
					    // 重新画
					    var realPoints = [];
						realPoints = calculateStartEndPos(startElm,endElm,realPoints);
						var lineBean = new Line(Math.uuid(),startElm.x+startElm.width,endElm.y+endElm.height/2);
						lineBean.init();
						lineBean.realPoints=realPoints;
						lineBean.startElmId = startElm.id;
						lineBean.endElmId = endElm.id;
						elements.add(lineBean);
						// 设置任务节点对应的线的信息
						endElm.headLineIds.put(lineBean.id, lineBean.id);
						startElm.afterLineIds.put(lineBean.id,lineBean.id);
				    }
				    // 清空临时用线信息list
				    lineMap.clear();
		    }
		}
		//取消 以选中的画图
		if( mouseImage != null ){
			mouseImage = null;
		}
		//取消【画线】
		if( drawPipeline != null ){
			drawPipeline = null;
			//初始化工具栏
			initTools();
		}
	});
	
	//鼠标移动
	$('#canvas').mousemove(function(e) {
		var mouseX = e.pageX - $('#canvas').offset().left;
		var mouseY = e.pageY - $('#canvas').offset().top;
		// 鼠标拖拽移动
		if (mousedown == true && mouseImage != null) {
		    mouseImage.x = mouseX;
		    mouseImage.y = mouseY;
		} else if( mousedown == true ){
			if( elements.getId('console') != null ){
				elements.getId('console').realData = '鼠标拖动：'+mouseX+','+mouseY;
			}
			//画2个节点之间的线
			if( drawPipeline != null ){
				//elements.add( drawPipeline );
			}
		}else{
			if( elements.getId('console') != null  ){
				elements.getId('console').realData = '鼠标移动：'+mouseX+','+mouseY;
			}
			//如果带着图片
			if( mouseImage != null ){
				mouseImage.x = mouseX;
				mouseImage.y = mouseY;
			}
		}
		if( elements.getId('line') != null ){
			elements.getId('line').x = mouseX;
			elements.getId('line').y = mouseY;
		}
	});
	
	//鼠标移进
	$('#canvas').mouseover(function(e) {
		var mouseX = e.pageX - $('#canvas').offset().left;
		var mouseY = e.pageY - $('#canvas').offset().top;
		if( elements.getId('console') != null  ) {
			elements.getId('console').realData = '鼠标移进画板';
		}
		//如果选择的不是手型
		if( selectId != 'select' ){
			//alert( selectId +"---->"+ isType(selectId) );
			switch( isType(selectId) ){
			case 'draw':
				if( selectId == 'Pipeline'  ){
					if( drawPipeline == null ){
						elementId = createId();
						drawPipeline = new Pipeline();
						drawPipeline.id = "p"+elementId;
						//elementId++;
						elements.insert( 0 , drawPipeline );
					}
				}
				break;
			case 'image':
				// alert('创建一张图片:'+mouseImage+",selectId:"+selectId);
				if( mouseImage == null ){
					elementId = createId();
					mouseImage = createElement(selectId,mouseX,mouseY);
					//alert("创建对象："+ mouseImage );
					elements.add(mouseImage);
				}
				break;
			}
		}
	});
	
	//鼠标移出
	$('#canvas').mouseout(function(e) {
		if( elements.getId('console') != null ){
			elements.getId('console').realData = '鼠标移出画板';
		}
		if( mouseImage != null ){
			elements.removeId(mouseImage.id);
			mouseImage = null;
		}
	});
	
	/**工具栏的单击事件（选中某一个）*/
	$('.tool').click(function() {
		selectId = $(this).attr("id");
		//select($(this).children().attr("name"), $(this).children().attr("src"))
		
		//鼠标离开时移除divOver样式
		$(".tool[id!="+selectId+"]").css({
			'background-color' : '#CFDFEF',
			'filter' : 'alpha(Opacity=70)',
			'padding':'5px',
			'opacity' : 0.7
		});
		
		mouseImage = null;
	});
	
	//默认为【选择】手型
	var selectId = 'select';
	//工具栏的鼠标事件
	$('.tool').hover(function() {
		$(this).css( {
			'background-color' : '#f58220',
			'padding':'0px 5px 10px 5px',
			'filter' : 'alpha(Opacity=100)',
			'opacity' : 1
		});
		
	}, function() {
		//鼠标离开时移除divOver样式
		$(".tool[id!="+selectId+"]").css({
			'background-color' : '#CFDFEF',
			'filter' : 'alpha(Opacity=70)',
			'padding':'5px',
			'opacity' : 0.7
		});
	});
	//设定手型为默认
	$("#select").css({
		'background-color' : '#f58220',
		'padding':'0px 5px 10px 5px',
		'filter' : 'alpha(Opacity=100)',
		'opacity' : 1
	});
	
	//判断是那一个类型
	function isType( selectId ){
		//alert( "selectId:"+selectId);
		switch( selectId ){
		case 'StartNode'://开始节点
			return 'image';
			break;
		case 'Lane'://泳道
			return 'image';
			break;
		case 'TaskNode'://任务节点
			return 'image';
			break;
		case 'EndNode'://开始节点
			return 'image';
			break;
		case 'Gateway'://判断节点
			return 'image';
			break;
		case 'Pipeline'://线
			return 'draw';
			break;
		case 'select'://选择
			return '-';
			break;		
		}
	}
	
	//创建元素
	function createElement( selectId , x,y , id ){
		switch( selectId ){
		case 'StartNode'://开始节点
			var v = new StartNode(id==null?Math.uuid():id,x,y);
			v.init();
			return v;
			break;
		case 'Pipeline'://管线
			return 'draw';
			break;
		case 'Lane'://泳道
			var v = new Lane(id==null?Math.uuid():id,x,y);
			v.init();
			//elementId++; 
			return v;
			break;
		case 'TaskNode'://任务节点
			var v = new TaskNode( id==null?Math.uuid():id ,x,y);
			v.init();
			return v;
			break;
		case 'EndNode'://结束节点
			var v = new EndNode(id==null?Math.uuid():id,x,y);
			v.init();
			return v;
			break;
		case 'Gateway'://判断节点
			var v = new Gateway(id==null?Math.uuid():id,x,y);
			v.init();
			return v;
			break;	
		case 'select'://选择
			return '-';
			break;		
		}
	}
	//----------------------------------------------------------------
	//----------------------------------------------------------
	//----------------------------------------------------------
	//单击【判断节点】弹出框中的确定按钮
	$('#submitGateway').click(function(e) {
		var charCheck = new RegExp("^(\\d+)$");
		if( window.confirm("您确认要更新该组件吗？") == true ){
			// 1.根据判断节点ID取得判断节点
			
			// 3.根据线信息取得线连接的下一步节点ID
			// 4.根据前台传递过来的下一节点ID设置判断条件到对应的线对象
			var gwItem = elements.getId($("#gatewayId").val());
			// 2.循环判断节点下一步对应的线
			var arrayKey = gwItem.afterLineIds.keys();
			for (var i=0;i<arrayKey.length;i++) {
				// 取得线对象
				var lineItem = elements.getId(arrayKey[i]);
				if (lineItem.endElmId == $("#nextNodeId").val()) {
					lineItem.gatewayInfo=$("#gatewayInfo").val();
					break;
				}
			}
			// 关闭弹出层
			$('#taskNodeDiv').modal('hide');
		}
	});
	//----------------------------------------------------------
	//----------------------------------------------------------
	//单击【任务节点】弹出框中的确定按钮
	$('#taskNodeSave').click(function(e) {
		var charCheck = new RegExp("^(\\d+)$");
		if( window.confirm("您确认要更新该组件吗？") == true ){
			var taskNodeItem = elements.getId($("#nodeId").val());
			taskNodeItem.title =$("#taskNodeTitle").val();
			taskNodeItem.name =$("#taskNodeName").val();
			$('#taskNodeDiv').modal('hide');
		}
	});
	
	//----------------------------------------------------------
	//----------------------------------------------------------
	//单击【基础】弹出框中的确定按钮
	$('#addBase').click(function(e) {
		var charCheck = new RegExp("^(\\d+)$");
		if( !charCheck.test($("#baseX").val()) ){
            alert('[X坐标]必须是整数!');
            $("#baseX").focus();
            return;
        }
		if( !charCheck.test($("#baseY").val()) ){
            alert('[Y坐标]必须是整数!');
            $("#baseY").focus();
            return;
        }
		if( window.confirm("您确认要更新该组件吗？") == true ){
			elements.getId($("#baseId").val()).clickAdd();
			$("#baseForm").hide(300);
		}
	});
	//单击【泳道】中的删除按钮
	$("#deleteBase").click(function(e) {
		if( window.confirm("您确认要删除该组件吗？") == true ){
			elements.removeId($("#baseId").val());
			$("#baseForm").hide(300);
		}
	});
	/**
	//------------------------------------------------
	//单击【查询】按钮，将返回的json数据显示到页面上
	$("#search").click(function(e) {
		if($('#select_flowname option:selected').val() == '' ){
			alert('请选择对应【流程】后再试！');
			//初始化工具栏
			initTools();
			return;
		}
		//获得用于选中的【流程】
		// var flowId = $('#select_flowname option:selected').val();
		// 查询数据
		searchChartInfo(getParam("bpmId"));
	});
	*/
	//------------------------------------------------
	// 查询数据
	this.searchChartInfo = function(flowId){
		//清空页面上的所有元素。
		elements.clear();
		//添加一个控制台，用于显示鼠标信息
		var texts = new Texts("console",2,5);
		texts.width = 200;
		texts.textColor = '#000000';
		elements.add(texts);
		//添加一个十字辅助线
		var cruciate = new CruciateAuxiliaryLine("line",0,0);
		elements.add(cruciate);
		//还原场景
		var rs  = new ReviveScene('design');
		rs.requestJson(flowId);
		//初始化工具栏
		initTools();
		isQuery = true;
	};
	//------------------------------------------------
	/**
	//单击【保存】按钮，将页面上的对象，保存到表中
	$("#save").click(function(e) {
		if(isQuery == false ||  $('#select_flowname option:selected').val() == ''){
			alert('请选择对应【流程】后再试！');
			//初始化工具栏
			initTools();
			return;	
		}
		var result = window.confirm("您确认要保存工艺画图信息吗？");
		if( result == false ){
			//初始化工具栏
			initTools();
			return ;
		}
		//获得用于选中的【流程】
		var flowId = $('#select_flowname option:selected').val();
		
	});
	*/
	//------------------------------------------------
	// 保存数据
	this.saveChart = function(flowId){
		// 确认保存
		var result = window.confirm("您确认要保存流程画图信息吗？");
		if( result == false ){
			//初始化工具栏
			initTools();
			return ;
		}
		//遍历所有的元素，生成json格式的数据。
		var jsonString = "[";
		for(var i=0;i<elements.size();i++){
			var json = elements.get(i).getJson();
			if( elements.get(i).id == 'console'  || elements.get(i).id == 'line'){
				//这个不用保存到表中
				continue;
			}
			jsonString = jsonString + json ;
			if( i < elements.size()-1 ){
				jsonString = jsonString + ",";
			}			
		}
		jsonString = jsonString + "]";
		
		//发起AJAX请求，提交参数
		$.ajax({
			 type: "POST",
			 url: "/"+window.location.pathname.split("/")[1]+"/flowchart/save-flow-chart.do?contexts="+jsonString+"&flowId="+flowId,
			 dataType: "text",
			 success: function(data){
				 if (data == "failure") {
					 alert('保存失败！');
				 } else {
					//初始化工具栏
					initTools();
				 }
			 },
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
				 elements.getId('console').realData = '请求数据出错了!';
	    		//初始化工具栏
	    		initTools();
	         } 
		});
	};
	/**
	//单击【删除】按钮，将页面上的对象，保存到表中
	$("#delete").click(function(e) {
		if( isQuery == false ){
			alert('请查询完对应【热力站】【机组】后再试！');
			//初始化工具栏
			initTools();
			return;
		}
		if( $('#select_flowname option:selected').val() == ''){
			alert('请选择对应【流程】后再试！');
			//初始化工具栏
			initTools();
			return;	
		}
		var result = window.confirm("您确认要删除流程图信息吗？");
		if( result == false ){
			//初始化工具栏
			initTools();
			return ;
		}
		//获得用于选中的【流程】
		var flowId = $('#select_flowname option:selected').val();
	});
	*/
	//------------------------------------------------
	// 删除数据
	this.deleteChart = function(flowId){
		// 确认删除
		var result = window.confirm("您确认要删除流程图信息吗？");
		if( result == false ){
			//初始化工具栏
			initTools();
			return ;
		}
		//发起AJAX请求，提交参数
		$.ajax({
			 type: "POST",
			 url: "/"+window.location.pathname.split("/")[1]+"/flowchart/delete-flow-chart.do?flowId="+flowId,
			 dataType: "text",
			 success: function(data){
				 // 流程图形数据删除成功!
				 if( data == "success" ){
					 elements.clear();
				 }
				//初始化工具栏
				initTools();
			 },
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
				 elements.getId('console').realData = '请求数据出错了!';
	    		//初始化工具栏
	    		initTools();
	         } 
		});
	};
	//------------------------------------------------
	//单击【泳道】按钮，在页面上添加一个泳道对象
	$("#addLane").click(function(e) {
		// 遍历画面中所有对象,判断当前有几个泳道对象
		var count = 0;
		for(var i=0;i<elements.size();i++){
		    // 判断取得当前点击的对象
			if( elements.get(i).type == 'Lane'){
			    count = count + elements.get(i).height + 0;
		   }
	   }
	   // 画一个泳道
	    var lane = new Lane(Math.uuid(),0, count+40);
		lane.init();
		elements.add(lane);
		//初始化工具栏
		initTools();
	});
	/**
	 * 初始化工具栏（设置为手型）
	 */
	function initTools(){
		selectId = 'select';
		//鼠标离开时移除divOver样式
		$(".tool[id!="+selectId+"]").css({
			'background-color' : '#CFDFEF',
			'filter' : 'alpha(Opacity=70)',
			'padding':'5px',
			'opacity' : 0.7
		});
		$("#select").css( {
			'background-color' : '#f58220',
			'padding':'0px 5px 10px 5px',
			'filter' : 'alpha(Opacity=100)',
			'opacity' : 1
		});
	}
	/**
	 * 创建界面元素的ID号
	 */
	function createId(){
		if( elements.size() == 0 ){
			return 1;
		}
		var max = 0;
		for(var i=0;i<elements.size();i++){
			var ele = elements.get(i);
			var id = ele.id+"";
			if( id == 'console' ){
				continue;
			}
			if( parseInt(id.substring(1)) > max ){
				max = parseInt(id.substring(1));
			}
		}
		return max+1;
	}
	// 隐藏其它弹出框
	function hideForm(){
		$("#baseForm").hide(); // 泳道
		$("#lineForm").hide(); // 线
	}
	// 清除所有控件的选中状态
	function clearMouseClick(){
	    // 遍历画面中所有对象,清除选中状态
		for(var i=0;i<elements.size();i++){
			elements.get(i).mouseClick = 0;
		}
	}
	// 查询数据
	this.searchChartInfo(getParam("bpmId"));
});
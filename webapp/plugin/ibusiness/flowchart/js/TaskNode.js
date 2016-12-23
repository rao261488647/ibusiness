
/**
 *	定义一个任务节点对象
 *	@author	JiangBo
 */
function TaskNode(id,x,y ){
	var pathBase = "/"+window.location.pathname.split("/")[1]; // URL path
	this.model = 'design';//设定2个模式（design/control）
	this.id = id;
	this.name = '发起节点';
	this.type = 'TaskNode';
	this.title = '标题';
	this.x = x;				//任务节点对象的起始位置（x坐标）
	this.y = y;				//任务节点对象的起始位置（y坐标）
	this.height = 50;		//任务节点对象的高度
	this.width = 100;		//任务节点对象的宽度
	this.moveOver = 1;		//是否可以移入（0：没有移入,1:移入主对象,2:移入判断子对象 3:下一节点 8:结束节点 9:删除）
	this.mouseClick = 0;    //单击选中此对象 0：未单击, 1：单击
	
	this.headLineIds=new HashMap(); // 头节点线ID List
	this.afterLineIds=new HashMap(); // 尾节点线ID List

	// 主对象图片
	this.imageMain = new Image();
	// =========== 判断子按钮图片 ===========================
	this.imageGateWayBtn =  new Image();
	this.xEditBtn = this.width+5;          //x坐标偏移量
	this.yEditBtn = this.height-15;          //y坐标偏移量
	this.heightEditBtn = 15;	//编辑子按钮的高度
	this.widthEditBtn = 15;     //编辑子按钮的宽度
	
	// =========== 删除子按钮 ===========================
	this.imageDeleteBtn =  new Image();
	this.xDeleteBtn = this.width-15;          //x坐标偏移量
	this.yDeleteBtn = this.height+10;          //y坐标偏移量
	this.heightDeleteBtn = 15;	//编辑子按钮的高度
	this.widthDeleteBtn = 15;     //编辑子按钮的宽度

    // =========== 建立下一节点子按钮 ===========================
	this.imageNextTaskBtn =  new Image();
	this.xNextTaskBtn = this.width-15;          //x坐标偏移量
	this.yNextTaskBtn = -20;          //y坐标偏移量
	this.heightNextTaskBtn = 15;	//编辑子按钮的高度
	this.widthNextTaskBtn = 15;     //编辑子按钮的宽度
	
    // =========== 建立结束节点子按钮 ===========================
	this.imageEndBtn =  new Image();
	this.xEndBtn = this.width+5;          //x坐标偏移量
	this.yEndBtn = 0;          //y坐标偏移量
	this.heightEndBtn = 15;	//编辑子按钮的高度
	this.widthEndBtn = 15;     //编辑子按钮的宽度

	/**
	 *	初始化
	 */
	this.init = function( json ){
	    // 主对象图片
		this.imageMain.src = pathBase + "/plugin/ibusiness/flowchart/img/tasknode.png";
		// 判断子对象图片
		this.imageGateWayBtn.src = pathBase + "/plugin/ibusiness/flowchart/img/gateway.png";
		// 删除子
		this.imageDeleteBtn.src= pathBase + "/plugin/ibusiness/flowchart/img/cross.png";
		// 建立下一节点按钮图片
		this.imageNextTaskBtn.src = pathBase + "/plugin/ibusiness/flowchart/img/add.png";
		// 结束节点图片
		this.imageEndBtn.src = pathBase + "/plugin/ibusiness/flowchart/img/endnode.png";
		if( null != json ){
			this.id = json.id;
			this.name = json.name;
			this.title = json.title;
			this.x = parseInt(json.x);
			this.y = parseInt(json.y);
			// 设置节点前线id
			if (null != json && json.headLineIds.length>0) {
				var strHeadlieids=json.headLineIds
				strHeadlieids = strHeadlieids.replace("[","");
				strHeadlieids = strHeadlieids.replace("]","");
				var items = strHeadlieids.split(",");
				for (var i=0;i<items.length;i++) {
					this.headLineIds.put(items[i], items[i]);
				}
			}
			// 设置节点后线id
			if (null != json && json.afterLineIds.length>0) {
				var strAfterLineIds=json.afterLineIds
				strAfterLineIds = strAfterLineIds.replace("[","");
				strAfterLineIds = strAfterLineIds.replace("]","");
				var items = strAfterLineIds.split(",");
				for (var i=0;i<items.length;i++) {
					this.afterLineIds.put(items[i], items[i]);
				}
			}
			
		}
	}

	/**
	 *	获得对象的json数据格式
	 */
	this.getJson = function(){
		return "{" +
					"\"id\":\""+this.id+"\"," +
					"\"name\":\""+this.name+"\"," +
					"\"type\":\""+this.type+"\"," +
					"\"title\":\""+this.title+"\"," +
					"\"x\":\""+this.x+"\"," +
					"\"y\":\""+this.y+"\"," +
					"\"width\":\""+this.width+"\"," +
					"\"height\":\""+this.height+"\"," +
					"\"headLineIds\":\""+this.headLineIds.keys()+"\"," +
					"\"afterLineIds\":\""+this.afterLineIds.keys()+"\"" +
				"}";
	}

	/**
	 *	根据实时数据更新组件的状态
	 */
	this.updateStatus = function( json ){
		//更新【实时数据】
	}
	/**
	 *	鼠标移入事件
	 */
	this.mousemove = function( mouseX,mouseY ){
	    // 画出边线
		ctx.strokeStyle="#000000";
	    ctx.strokeRect( parseInt(this.x)-5, parseInt(this.y)-5, parseInt(this.width)+10, parseInt(this.height)+10 , false );
	}
	/**
	 *	单击事件
	 */
	this.clickView = function( mouseX,mouseY ){
	}

	/**
	 *	单击事件（编辑）
	 */
	this.clickEdit = function(mouseX,mouseY){
		// alert( this.id+this.name+'被单击（编辑）!' );
		if (this.moveOver == 1) {
			
			var url = pathBase + "/flowchart/pop-conf-taskNode.do?flowId="+$("#bpmId").val()+"&packageName="+$("#packageName").val()+"&id="+this.id;
			$("#taskNodeIframe").attr("src", url );
		//	$("#taskNodeDiv").css({width:570 ,height:380});
			
			$("#taskNodeId").attr("value",this.id);
			$("#nodeId").attr("value",this.id);
			$("#taskNodeTitle").attr("value",this.title);
			$("#taskNodeName").attr("value",this.name);

			// 设置显示标签
			$("#taskNodeBaseTabs").attr("class","active");
			$("#popConfTaskNodeTabs").attr("class"," ");
			$("#taskNodeBaseDiv").attr("class","tab-pane fade active in ");
			$("#popConfTaskNodeDiv").attr("class","tab-pane fade");
			
			// 
			$('#taskNodeDiv').modal('show');
			
		} else if (this.moveOver == 2) {
			// 新建一个判断节点
		    var gateway = new Gateway(Math.uuid(), this.x+200, this.y);
		    gateway.init();
			elements.add(gateway);
			// 计算划线的开始坐标
			var realPoints = [];
			realPoints = calculateStartEndPos(this,gateway,realPoints);
			// 画线
			var lineBean = new Line(Math.uuid(),this.x+this.width,this.y+this.height/2);
			lineBean.init();
			lineBean.realPoints=realPoints;
			lineBean.startElmId = this.id;
			lineBean.endElmId = gateway.id;
			elements.add(lineBean);
			// 设置任务节点对应的线的信息
			gateway.headLineIds.put(lineBean.id,lineBean.id);
			this.afterLineIds.put(lineBean.id,lineBean.id);
		} else if (this.moveOver == 3) {
		    // 新建一个task节点
		    var taskNode = new TaskNode(Math.uuid(), this.x+200, this.y);
			taskNode.init();
			elements.add(taskNode);
			// 计算划线的开始坐标
			var realPoints = [];
			realPoints = calculateStartEndPos(this,taskNode,realPoints);
			// 画线
			var lineBean = new Line(Math.uuid(),this.x+this.width,this.y+this.height/2);
			lineBean.init();
			lineBean.realPoints=realPoints;
			lineBean.startElmId = this.id;
			lineBean.endElmId = taskNode.id;
			elements.add(lineBean);
			// 设置任务节点对应的线的信息
			taskNode.headLineIds.put(lineBean.id,lineBean.id);
			this.afterLineIds.put(lineBean.id,lineBean.id);
		} else if (this.moveOver == 8) {
		    // 建立结束节点
		    var endNode = new EndNode(Math.uuid(), this.x+200, this.y);
		    endNode.y = this.y + (this.height - endNode.height)/2
			endNode.init();
			elements.add(endNode);
			// 计算划线的开始坐标
			var realPoints = [];
			realPoints = calculateStartEndPos(this,endNode,realPoints);
			// 画连接线
			var lineBean = new Line(Math.uuid(),this.x+this.width,this.y+this.height/2);
			lineBean.init();
			lineBean.realPoints=realPoints;
			lineBean.startElmId = this.id;
			lineBean.endElmId = endNode.id;
			elements.add(lineBean);
			// 设置任务节点对应的线的信息
			endNode.headLineIds.put(lineBean.id,lineBean.id);
			this.afterLineIds.put(lineBean.id,lineBean.id);
			
		} else if (this.moveOver == 9) {
		    // 删除节点相关的线 ---- 头节点线
			if (this.headLineIds.size() > 0) {
				var arrayKey = this.headLineIds.keys();
				for (var i=0;i<arrayKey.length;i++) {
					// 取得线对象
					var lineItem = elements.getId(arrayKey[i]);
					// 删除线关联的对象中的 afterLineId
					var startElm = elements.getId(lineItem.startElmId);
					startElm.afterLineIds.remove(lineItem.id);
					// 删除线
					elements.removeId(lineItem.id);
				}
			}
			// 删除节点相关的线 ---- 尾节点线
			if (this.afterLineIds.size() > 0) {
				var arrayKey = this.afterLineIds.keys();
				for (var i=0;i<arrayKey.length;i++) {
					// 取得线对象
					var lineItem = elements.getId(arrayKey[i]);
					// 删除线关联的对象中的 afterLineId
					var endElm = elements.getId(lineItem.endElmId);
					endElm.headLineIds.remove(lineItem.id);
					// 删除线
					elements.removeId(lineItem.id);
				}
			}
		    // 删除子按钮
		    elements.removeId(this.id);
		}
	}

	/**
	 *	单击事件（新加&编辑）
	 */
	this.clickAdd = function(){
		alert("单击事件（新加&编辑）");
		/**
		this.id = $("#taskNodeId").val();
		this.name = $("#taskNodeName").val();
	    this.title = $("#taskNodeName").val();
		$("#taskNodeForm").hide(300);
		*/
	}
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){
	    // 对象主体
		if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.width)) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.height)) ){
			return 1;
		}
		// 判断子按钮
		if (mouseX >= this.x+this.xEditBtn && mouseX <= (parseInt(this.x)+this.xEditBtn+parseInt(this.widthEditBtn)) && mouseY >= this.y+this.yEditBtn && mouseY<=(parseInt(this.y)+this.yEditBtn+parseInt(this.heightEditBtn))) {
		   return 2;
		}
		// 创建下一节点子按钮
		if (mouseX >= this.x+this.xNextTaskBtn && mouseX <= (parseInt(this.x)+this.xNextTaskBtn+parseInt(this.widthNextTaskBtn)) && mouseY >= this.y+this.yNextTaskBtn && mouseY<=(parseInt(this.y)+this.yNextTaskBtn+parseInt(this.heightNextTaskBtn))) {
		   return 3;
		}
		// 创建结束节点子按钮
		if (mouseX >= this.x+this.xEndBtn && mouseX <= (parseInt(this.x)+this.xEndBtn+parseInt(this.widthEndBtn)) && mouseY >= this.y+this.yEndBtn && mouseY<=(parseInt(this.y)+this.yEndBtn+parseInt(this.heightEndBtn))) {
		   return 8;
		}
		// 删除子按钮
		if (mouseX >= this.x+this.xDeleteBtn && mouseX <= (parseInt(this.x)+this.xDeleteBtn+parseInt(this.widthDeleteBtn)) && mouseY >= this.y+this.yDeleteBtn && mouseY<=(parseInt(this.y)+this.yDeleteBtn+parseInt(this.heightDeleteBtn))) {
		   return 9;
		}
		return 0;
	}

	/**
	 *	画出控件
	 */
	this.paint = function(){
	    // 主控件
		ctx.drawImage( this.imageMain, this.x, this.y, this.width, this.height );
		// 主控件标题
		if( this.title != "" ){
			ctx.fillStyle="#000000";  //填充颜色
			ctx.font="bold 15px 宋体";
			ctx.fillText( this.title , parseInt(this.x)+10, parseInt(this.y)+30 );
		}
		if (this.mouseClick == 1) {
			// 画判断子控件
			ctx.drawImage( this.imageGateWayBtn, this.x+this.xEditBtn, this.y+this.yEditBtn, this.widthEditBtn, this.heightEditBtn);
			// 画删除子控件
			ctx.drawImage( this.imageDeleteBtn, this.x+this.xDeleteBtn, this.y+this.yDeleteBtn, this.widthDeleteBtn, this.heightDeleteBtn);
			// 建立结束节点子按钮控件
			ctx.drawImage( this.imageEndBtn, this.x+this.xEndBtn, this.y+this.yEndBtn, this.widthEndBtn, this.heightEndBtn);
			// 建立下一节点子按钮控件
			ctx.drawImage( this.imageNextTaskBtn, this.x+this.xNextTaskBtn, this.y+this.yNextTaskBtn, this.widthNextTaskBtn, this.heightNextTaskBtn);
		}
		
		// 鼠标移动到控件位置显示内容
		if(this.moveOver == 1 || this.mouseClick == 1){
		    // 主控件
			ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)-5, parseInt(this.y)-5, parseInt(this.width)+10, parseInt(this.height)+10 , false );
		}
		if (this.moveOver == 2) {
		    // 判断子按钮
		    ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)+this.xEditBtn-5, parseInt(this.y)+this.yEditBtn-5, parseInt(this.widthEditBtn)+10, parseInt(this.heightEditBtn)+10, false );
		}
		if (this.moveOver == 3) {
		    // 建立下一节点
		    ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)+this.xNextTaskBtn-5, parseInt(this.y)+this.yNextTaskBtn-5, parseInt(this.widthNextTaskBtn)+10, parseInt(this.heightNextTaskBtn)+10, false );
		}
		if (this.moveOver == 8) {
		    // 建立结束节点
		    ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)+this.xEndBtn-5, parseInt(this.y)+this.yEndBtn-5, parseInt(this.widthEndBtn)+10, parseInt(this.heightEndBtn)+10, false );
		}
		if (this.moveOver == 9) {
		    // 删除子按钮
		    ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)+this.xDeleteBtn-5, parseInt(this.y)+this.yDeleteBtn-5, parseInt(this.widthDeleteBtn)+10, parseInt(this.heightDeleteBtn)+10, false );
		}
	}

	/**
	 *	动画效果
	 */
	this.animation = function(){
		//正常,警报
		this.paint();
		return;
	}

	/**
	 *	设置模型
	 */
	this.setModel = function(model){
		this.model = model;
	}
}
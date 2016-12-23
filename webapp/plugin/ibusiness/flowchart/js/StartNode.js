
/**
 *	定义一个开始节点类
 *
 *	@author	jiangbo
 */
function StartNode( id,x,y ){
	var pathBase = "/"+window.location.pathname.split("/")[1]; // URL path
	this.model = 'design';//设定2个模式（design/control）
	this.id = id;
	this.name = '开始节点';
	this.type = 'StartNode';
	this.title = '标题';
	this.x = x;			//开始节点的起始位置（x坐标）
	this.y = y;			//开始节点的起始位置（y坐标）
	this.height = 50;	//开始节点的高度
	this.width = 50;	//开始节点的宽度
	this.moveOver = 1;		//是否可以移入（0：没有边框,1:画边框）
	this.mouseClick = 0;    //单击选中此对象 0：未单击, 1：单击
	
	this.afterLineIds=new HashMap(); // 尾节点线ID HashMap
	
	this.bark = new Image();
    // =========== 建立下一节点子按钮 ===========================
	this.imageNextTaskBtn =  new Image();
	this.xNextTaskBtn = this.width+5;          //x坐标偏移量
	this.yNextTaskBtn = 0;          //y坐标偏移量
	this.heightNextTaskBtn = 15;	//编辑子按钮的高度
	this.widthNextTaskBtn = 15;     //编辑子按钮的宽度
	
	// =========== 删除子按钮 ===========================
	this.imageDeleteBtn =  new Image();
	this.xDeleteBtn = this.width+5;          //x坐标偏移量
	this.yDeleteBtn = this.height-15;          //y坐标偏移量
	this.heightDeleteBtn = 15;	//编辑子按钮的高度
	this.widthDeleteBtn = 15;     //编辑子按钮的宽度
	
	/**
	 *	初始化
	 */
	this.init = function( json ){
	    //开始节点图片
		this.bark.src = pathBase + "/plugin/ibusiness/flowchart/img/startnode.png";
		// 建立下一节点按钮图片
		this.imageNextTaskBtn.src = pathBase + "/plugin/ibusiness/flowchart/img/add.png";
		// 删除子
		this.imageDeleteBtn.src= pathBase + "/plugin/ibusiness/flowchart/img/cross.png";
		if( null != json ) {
			this.id = json.id;
			this.name = json.name;
			this.title = json.title;
	        this.x = parseInt(json.x);
			this.y = parseInt(json.y);
			// 设置节点后线id
			if (null != json && json.afterLineIds.length>0) {
				var strAfterLineIds=json.afterLineIds
				strAfterLineIds = strAfterLineIds.replace("[","");
				strAfterLineIds = strAfterLineIds.replace("]","");
				var items = strAfterLineIds.split(",");
				for (var i=0;i<items.length;i++) {
					this.afterLineIds.put(items[i],items[i]);
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
		ctx.strokeStyle="#000000";
		ctx.strokeRect( parseInt(this.x)-5, parseInt(this.y)-5, parseInt(this.width)+10, parseInt(this.height)+10 , false ); 
	}
	
	/**
	 *	单击事件
	 */
	this.clickView = function( mouseX,mouseY ){
	    alert("开始节点单击事件");
	}

	/**
	 *	单击事件（编辑）
	 */
	this.clickEdit = function(mouseX,mouseY){
		// alert( this.id+this.name+'开始节点被单击（编辑）!' );
		if (this.moveOver == 1) {
			$("#baseForm").css({top:mouseY + canvas.offsetTop ,left: mouseX + canvas.offsetLeft});
			$("#baseForm").show(300);
			$("#baseId").attr("value",this.id);
			$("#baseX").attr("value",this.x);
			$("#baseY").attr("value",this.y);
			$("#baseTitle").attr("value",this.title);
		} else if (this.moveOver == 3) {
			// 新建一个task节点
		    var taskNode = new TaskNode(Math.uuid(), this.x+150, this.y);
			taskNode.init();
			elements.add(taskNode);
			// 计算划线的开始坐标
			var realPoints = [];
			realPoints = calculateStartEndPos(this,taskNode,realPoints);
			// 画连接线
			var lineBean = new Line(Math.uuid(), this.x+this.width, this.y+this.height/2);
			lineBean.init();
			lineBean.realPoints=realPoints;
			lineBean.startElmId = this.id;
			lineBean.endElmId = taskNode.id;
			elements.add(lineBean);
			// 设置任务节点对应的线的信息
			taskNode.headLineIds.put(lineBean.id, lineBean.id);
			this.afterLineIds.put(lineBean.id, lineBean.id);
		} else if (this.moveOver == 9) {
			// 删除节点相关的线 ---- 尾节点线
			if (this.afterLineIds.size() > 0) {
				var arrayKey = this.afterLineIds.keys();
				for (var i=0;i<arrayKey.length;i++) {
					// 取得线对象
					var lineItem = elements.getId(arrayKey[i]);
					// 删除线关联的对象中的 endLineId
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
	  //alert("开始节点单击事件（新加&编辑）");
	    this.id = $("#baseId").val();
		this.x = $("#baseX").val();
		this.y = $("#baseY").val();
		this.title = $("#baseTitle").val(); // 显示标题
		$("#baseForm").hide(300);
	}
	
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){
		if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.width)) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.height)) ){
			// alert('比较是否是开始节点！'+ mouseX +","+mouseY);
			return 1;
		}
		// 创建下一节点子按钮
		if (mouseX >= this.x+this.xNextTaskBtn && mouseX <= (parseInt(this.x)+this.xNextTaskBtn+parseInt(this.widthNextTaskBtn)) && mouseY >= this.y+this.yNextTaskBtn && mouseY<=(parseInt(this.y)+this.yNextTaskBtn+parseInt(this.heightNextTaskBtn))) {
		   return 3;
		}
		// 删除子按钮
		if (mouseX >= this.x+this.xDeleteBtn && mouseX <= (parseInt(this.x)+this.xDeleteBtn+parseInt(this.widthDeleteBtn)) && mouseY >= this.y+this.yDeleteBtn && mouseY<=(parseInt(this.y)+this.yDeleteBtn+parseInt(this.heightDeleteBtn))) {
		   return 9;
		}
		return false;
	}

	/**
	 *	画出图片
	 */
	this.paint = function(){
		//画出图片
		ctx.drawImage( this.bark, this.x, this.y, this.width, this.height );
		if (this.mouseClick == 1) {
		    // 建立下一节点子按钮控件
		    ctx.drawImage( this.imageNextTaskBtn, this.x+this.xNextTaskBtn, this.y+this.yNextTaskBtn, this.widthNextTaskBtn, this.heightNextTaskBtn);
		    // 画删除子控件
			ctx.drawImage( this.imageDeleteBtn, this.x+this.xDeleteBtn, this.y+this.yDeleteBtn, this.widthDeleteBtn, this.heightDeleteBtn);
		}
		// 鼠标移动到此 或者当前已经选定此组件
	    if(this.moveOver == 1 || this.mouseClick == 1){
			ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)-5, parseInt(this.y)-5, parseInt(this.width)+10, parseInt(this.height)+10 , false );
		}
		if (this.moveOver == 3) {
		    // 建立下一节点
		    ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)+this.xNextTaskBtn-5, parseInt(this.y)+this.yNextTaskBtn-5, parseInt(this.widthNextTaskBtn)+10, parseInt(this.heightNextTaskBtn)+10, false );
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
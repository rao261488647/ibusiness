
/**
 *	定义一个结束节点类
 *
 *	@author	jiangbo
 */
function EndNode( id,x,y ){
	var pathBase = "/"+window.location.pathname.split("/")[1]; // URL path
    this.model = 'design';//设定2个模式（design/control）
	this.id = id;
	this.name = '结束节点';
	this.type = 'EndNode';
	this.title = '标题';
	this.x = x;			//结束节点的起始位置（x坐标）
	this.y = y;			//结束节点的起始位置（y坐标）
	this.height = 50;	//结束节点的高度
	this.width = 50;	//结束节点的宽度
	this.moveOver = 1;		//是否可以移入（0：没有边框,1:画边框）
	
	this.headLineIds=new HashMap(); // 头节点线ID HashMap
	this.bark = new Image();
	
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
	    //结束节点图片
		this.bark.src = pathBase + "/plugin/ibusiness/flowchart/img/endnode.png";
		// 删除子
		this.imageDeleteBtn.src= pathBase + "/plugin/ibusiness/flowchart/img/cross.png";
		
		if( null != json ) {
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
					"\"headLineIds\":\""+this.headLineIds.keys()+"\"" +
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
	    alert("结束节点单击事件");
	}

	/**
	 *	单击事件（编辑）
	 */
	this.clickEdit = function(mouseX,mouseY){
		if (this.moveOver == 1) {
			// 点击结束节点对象
			
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
		    // 删除当前结束节点
		    elements.removeId(this.id);
		}
	}
	
	/**
	 *	单击事件（新加&编辑）
	 */
	this.clickAdd = function(){
	   // alert("结束节点单击事件（新加&编辑）");
	}
	
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){
		//alert('比较是否是结束节点！'+ mouseX +","+mouseY);
		if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.width)) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.height)) ){
			return 1;
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
		    // 画删除子控件
			ctx.drawImage( this.imageDeleteBtn, this.x+this.xDeleteBtn, this.y+this.yDeleteBtn, this.widthDeleteBtn, this.heightDeleteBtn);
		}
		// 鼠标移动到此 或者当前已经选定此组件
	    if(this.moveOver == 1  || this.mouseClick == 1){
			ctx.strokeStyle="#000000";
			ctx.strokeRect( parseInt(this.x)-5, parseInt(this.y)-5, parseInt(this.width)+10, parseInt(this.height)+10 , false );
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
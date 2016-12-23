
/**
 *	定义一个泳道类
 *
 *	@author	jiangbo
 */
function Lane( id,x,y ){
	var pathBase = "/"+window.location.pathname.split("/")[1]; // URL path
	this.model = 'design';//设定2个模式（design/control）
	this.id = id;
	this.name = '泳道';
	this.type = 'Lane';
	this.title = '标题';
	this.x = x;			//泳道的起始位置（x坐标）
	this.y = y;			//泳道的起始位置（y坐标）
	this.height = 110;	//泳道的高度
	this.width = 35;	//泳道的宽度
	this.moveOver = 1;		//是否可以移入（0：没有边框,1:画边框）
	this.bark = new Image(); 
	// 泳道图片
	this.bark.src = pathBase + "/plugin/ibusiness/flowchart/img/lanebg2.png";

	/**
	 *	初始化
	 */
	this.init = function( json ) {
		if( null != json ) {
			this.id = json.id;
			this.name = json.name;
			this.title = json.title;
			this.x = parseInt(json.x);
			this.y = parseInt(json.y);
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
					"\"height\":\""+this.height+"\"" +
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
	    alert("单击事件");
	}

	/**
	 *	单击事件（编辑）
	 */
	this.clickEdit = function(mouseX,mouseY){
		// alert( this.id+this.name+'被单击（编辑）!' );
		$("#baseForm").css({top:mouseY + canvas.offsetTop ,left: mouseX + canvas.offsetLeft});
		$("#baseForm").show(300);
		$("#baseId").attr("value",this.id);
		$("#baseX").attr("value",this.x);
		$("#baseY").attr("value",this.y);
		$("#baseTitle").attr("value",this.title);
	}
	
	/**
	 *	单击事件（新加&编辑）
	 */
	this.clickAdd = function(){
	  //  alert("单击事件（新加&编辑）");
		this.id = $("#baseId").val();
		this.title = $("#baseTitle").val(); // 显示标题
		$("#baseForm").hide(300);
	}
	
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){
		// alert('比较是否是泳道！'+ mouseX +","+mouseY);
		if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.width)) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.height)) ){
			return true;
		}
		return false;
	}

	/**
	 *	画出图片
	 */
	this.paint = function(){
	    // 鼠标移动到此
	    if(this.moveOver == 1 ){
	    	//画出边线
			ctx.strokeStyle="blue";
			ctx.strokeRect( parseInt(this.x)-5, parseInt(this.y)-5, parseInt(this.width)+10, parseInt(this.height)+10 , false );
		}
	    // 泳道设施靠左
	    this.x = 0;
		//画出图片
		ctx.drawImage( this.bark, this.x, this.y, this.width, this.height ); 
		ctx.strokeStyle="gray"; // 黑black  白： white   蓝：blue 灰色：gray
		// 泳道线上
		ctx.beginPath();
		ctx.moveTo(0,parseInt(this.y)-0);
		ctx.lineTo(parseInt(this.x)+1200,parseInt(this.y)-0);
		ctx.closePath();
	    ctx.stroke();
		// 泳道线下
		ctx.beginPath();
		ctx.moveTo(0,parseInt(this.y)+this.height+0);
		ctx.lineTo(parseInt(this.x)+1200,parseInt(this.y)+this.height+0);
		ctx.closePath();
    	ctx.stroke();
    	// 文字添加
    	ctx.fillStyle='yellow';  //边框颜色
		ctx.font="bold 18px 宋体";
		ctx.fillText( this.title, 0,this.y+parseInt(this.height/2));
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
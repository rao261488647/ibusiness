/**
 *	定义一个十字辅助线类
 *	@author	lsm
 *	@time	2013-08-13
 */
function CruciateAuxiliaryLine(	id,x,y ){

	this.id = id;
	this.name = '十字辅助线';
	this.type = 'CruciateAuxiliaryLine';

	this.x = x;			//CruciateAuxiliaryLine的起始位置（x坐标）
	this.y = y;			//流量计的起始位置（y坐标）
	
	this.moveOver = 0;		//是否可以移入（0：没有移入,1:可以移入）

	/**
	 *	初始化
	 */
	this.init = function( json ){
		
	}

	/**
	 *	获得对象的json数据格式
	 */
	this.getJson = function(){
		return "{}";
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
		//alert( this.id+this.name+'被单击（编辑）!' );
		
		
	}
	
	/**
	 *	单击事件（新加&编辑）
	 */
	this.clickAdd = function(){

	}
	
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){

		return false;
	}

	/**
	 *	画出管道
	 */
	this.paint = function(){
		//画一条直线(横)
	    ctx.beginPath();  
	    ctx.moveTo(0, parseInt(this.y) );  
	    ctx.lineTo(parseInt(width), parseInt(this.y) );  
		ctx.closePath();
		ctx.strokeStyle="#FF0000";
		ctx.stroke();
	
		//画一条直线(竖)
		
	    ctx.beginPath();  
	    ctx.moveTo(parseInt(this.x), 0);  
	    ctx.lineTo(parseInt(this.x), parseInt(height) );  
		ctx.closePath();
		ctx.strokeStyle="#FF0000";
		ctx.stroke();
	}

	/**
	 *	动画效果
	 */
	this.animation = function(){
		this.paint();
		return;
	}
}
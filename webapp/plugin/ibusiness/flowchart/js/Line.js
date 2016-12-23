
/**
 *	定义一个线对象类
 *
 *	@author	JiangBo
 */
function Line(id,x,y){
	var pathBase = "/"+window.location.pathname.split("/")[1]; // URL path
	this.model = 'design';//设定2个模式（design/control）
	this.id = id;
	this.name = '线';
	this.type = 'Line';
	this.fillColor = 'red';

	this.title = '标题';
	this.x = x;			//管道的起始位置（x坐标）
	this.y = y;			//管道的起始位置（y坐标）
	this.moveOver = 0;		//是否可以移入（0：没有移入,1:可以移入）
	
	this.realPoints=[]; // 线位置坐标点
	
	// 线前(开始)节点id
	this.startElmId = '';
	// 线后(结束)节点id
	this.endElmId = '';
	
	// 判断节点的判断条件
	this.gatewayInfo = '';
	
	/**
	 *	初始化
	 */
	this.init = function( json ){
		if( json != null ){
			this.id = json.id;
			this.name = json.name;

			this.title = json.title;
			this.x = parseInt(json.x);
			this.y = parseInt(json.y);
			
			this.startElmId = json.startElmId;
			this.endElmId = json.endElmId;
			
			this.gatewayInfo = json.gatewayInfo;
			// 设置线位置坐标点
		//	this.realPoints = calculateStartEndPos(elements.getId(json.startElmId),elements.getId(json.endElmId),this.realPoints);
			// 设置线位置坐标点
			var  strRealPoints= json.realPoints.split(",");
			for (var i=0; i< strRealPoints.length; i++) {
				this.realPoints.push(parseInt(strRealPoints[i])); // 转换成整型,不然
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
					"\"fillColor\":\""+this.fillColor+"\"," +
					
					"\"title\":\""+this.title+"\"," +
					"\"x\":\""+this.x+"\"," +
					"\"y\":\""+this.y+"\"," +
					
					"\"gatewayInfo\":\""+this.gatewayInfo+"\"," +
		
					"\"realPoints\":\""+this.realPoints+"\"," +
					"\"startElmId\":\""+this.startElmId+"\"," +
					"\"endElmId\":\""+this.endElmId+"\"" +
					
				"}";
	}

	/**
	 *	根据实时数据更新组件的状态
	 */
	this.updateStatus = function( json ){
	    //更新【实时数据】
	}
	
	/**
	 *	判断管道是否有流动效果
	 */
	this.compare = function(json , monitoring,valueData){
		var key = monitoring.substring(0, monitoring.indexOf(".") );
		var value = monitoring.substring(monitoring.indexOf(".")+1 );
		var type = monitoring.substring(0, 1);
		
		if( valueData == 'undefined' || valueData == '' ){
			valueData = 0;
		}
	}
	/**
	 *	鼠标移入事件
	 */
	this.mousemove = function( mouseX,mouseY ){

	}
	/**
	 *	单击事件（显示）
	 */
	this.clickView = function(){
		//alert( this.id+this.name+'被单击（显示）!' );
	}

	/**
	 *	单击事件（编辑）
	 */
	this.clickEdit = function(mouseX,mouseY){
		//alert( this.id+this.name+'被单击（线控件编辑）!' );
	}
	
	/**
	 *	单击事件（新加&编辑）
	 */
	this.clickAdd = function(){
		$("#lineForm").hide(300);
	}
	
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){
		return false;
	}

	/**
	 *	画出线
	 */
	this.paint = function(){
		 ctx.beginPath();
		 //
		 ctx.moveTo(this.realPoints[0], this.realPoints[1]);
		 for (var i =2; i <this.realPoints.length; i=i+2) {
			   ctx.lineTo(this.realPoints[i], this.realPoints[i+1]);
		 }
		 ctx.lineWidth=2;
		 ctx.strokeStyle='green'; // blue  black
		 ctx.stroke();
         // 箭头类型
		 var x=this.realPoints[this.realPoints.length-4];
		 var y= this.realPoints[this.realPoints.length-3];
		 var x1=this.realPoints[this.realPoints.length-2];
		 var y1=this.realPoints[this.realPoints.length-1];
		 var arrowType = calculateArrowAspect(x,y,x1,y1);
		//画一个【向北】的箭头
		if( arrowType == N ){
			var length = y-y1;
			ctx.beginPath();
			ctx.moveTo(x+4/2-2, y-length-0);  	//起始位置
		    ctx.lineTo(x-(4*1)-2, y-length-0+4*3 );  
		    ctx.lineTo(x+(4*1)+4-2, y-length-0+4*3 );  
		    ctx.closePath();
		    ctx.fillStyle="black";			//填充的颜色
		    ctx.fill();
		} else	if( arrowType == S ){
			    //画一个【向南】的箭头
			    var length = y1-y;
				ctx.beginPath();
				ctx.moveTo(x+4/2-2, y+length-10+4*3);  	//起始位置
			    ctx.lineTo(x-(4*1)-2, y+length-10 );  
			    ctx.lineTo(x+(4*1)+4-2, y+length-10 );  
			    ctx.closePath();
			    ctx.fillStyle="black";			//填充的颜色
			    ctx.fill();
		} else if( arrowType == E ){
			//画一个【向东】的箭头
			var length = x1-x;
				ctx.beginPath();
				ctx.moveTo(x+length-0, y+4/2-2 );  	//起始位置
			    ctx.lineTo(x+length-4*3, y-6*1 );  
			    ctx.lineTo(x+length-4*3, y+4+4*1-2 );  
			    ctx.closePath();
			    ctx.fillStyle="black";			//填充的颜色
			    ctx.fill();
		} else if( arrowType == W){
			 //画一个【向西】的箭头
			var length = x-x1;
				ctx.beginPath();
				ctx.moveTo(x-length, y+4/2-2 ); //起始位置 
				ctx.lineTo(x-length+4*3, y-4*1-2);  	
			    ctx.lineTo(x-length+4*3, y+4+4*1-2 );  
			    ctx.closePath();
			    ctx.fillStyle="black";			//填充的颜色
			    ctx.fill();
			}
	}

	/**
	 *	动画效果
	 */
	this.animation = function(){
		//先画出基本的线条
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
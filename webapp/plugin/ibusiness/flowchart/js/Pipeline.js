
/**
 *	定义一个管道类
 *	@author	lsm
 *	@time	2013-05-20
 */
function Pipeline(id,x,y,length,endElmId){
	var pathBase = "/"+window.location.pathname.split("/")[1]; // URL path
	this.model = 'design';//设定2个模式（design/control）
	this.id = id;
	this.name = '管道';
	this.type = 'Pipeline';
	this.fillColor = 'red';

	this.title = '标题';
	this.x = x;			//管道的起始位置（x坐标）
	this.y = y;			//管道的起始位置（y坐标）
	this.length = length;		//管道的长度
	this.width = 4;		//管道的宽度
	this.moveOver = 0;		//是否可以移入（0：没有移入,1:可以移入）
	
	this.flowTo = 'east';//north, south, east and west
	this.isArrow = 'right';	//是否显示箭头 false right
	// 线前(开始)节点id
	this.startElmId = '';
	// 线后(结束)节点id
	this.endElmId = '';
	
	this.water = null;		//流动的水
	this.count = 0;			//水流动4次就归位。
	
	/**
	 *	初始化
	 */
	this.init = function( json ){
		if( json != null ){
			this.id = json.id;
			this.name = json.name;
			this.type = json.type;
			this.fillColor = json.fillColor;

			this.title = json.title;
			this.x = json.x;
			this.y = json.y;
			this.length = json.length;
			this.width = json.width;
			
			this.flowTo = json.flowTo;
			this.isArrow = json.isArrow;
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
					"\"width\":\""+this.width+"\"," +
					"\"length\":\""+this.length+"\"," +
					
					
					"\"flowTo\":\""+this.flowTo+"\"," +
					"\"isArrow\":\""+this.isArrow+"\"" +
					
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
		//$("#iconForm").css("display","block");
		$("#lineForm").css({top:mouseY + canvas.offsetTop ,left: mouseX + canvas.offsetLeft});
		$("#lineForm").show(300);
		
		//alert(  this.x );
		$("#lineId").attr("value",this.id);
		$("#lineN").attr("value",this.name);
		$("#lineX").attr("value",this.x);
		$("#lineY").attr("value",this.y);
		$("#lineW").attr("value",this.width);
		$("#lineL").attr("value",this.length);
		
		$("#fillColor").val(this.fillColor);
		$("#fillColor").css("background",this.fillColor);
		
		//这个是动态的箭头数据（根据东南西北）
		var selectflow = "";
		var selectArrow = "<option value=\"false\">不显示箭头</option>";
		switch (this.flowTo) {//north, south, east and west
		case "north"://北
			
		case "south"://南
			selectArrow = selectArrow+"<option value=\"top\" "+(this.isArrow=='top'?'selected':'')+">上边显示</option>";
			selectArrow = selectArrow+"<option value=\"bottom\" "+(this.isArrow=='bottom'?'selected':'')+">下边显示</option>";
			selectflow = selectflow+"<option value=\"north\" "+(this.flowTo=='north'?'selected':'')+">向上</option>";
			selectflow = selectflow+"<option value=\"south\" "+(this.flowTo=='south'?'selected':'')+">向下</option>";
			break;
		case "east"://东
			
		case "west"://西
			selectArrow = selectArrow+"<option value=\"left\" "+(this.isArrow=='left'?'selected':'')+">左边显示</option>";
			selectArrow = selectArrow+"<option value=\"right\" "+(this.isArrow=='right'?'selected':'')+">右边显示</option>";
			selectflow = selectflow+"<option value=\"east\" "+(this.flowTo=='east'?'selected':'')+">向右</option>";
			selectflow = selectflow+"<option value=\"west\" "+(this.flowTo=='west'?'selected':'')+">向左</option>";
			break;
		}
		$("#flowTo").html( selectflow );
	    $('#isArrow').html(selectArrow);
	}
	
	/**
	 *	单击事件（新加&编辑）
	 */
	this.clickAdd = function(){
		this.id = $("#lineId").val();
		this.name = $("#lineN").val();
		this.x = $("#lineX").val();
		this.y = $("#lineY").val();
		this.width = $("#lineW").val();
		this.length = $("#lineL").val();
		
		this.fillColor = $("#fillColor").find("option:selected").val();
		
		//如果流向改变了，对应的x,y,height都会有变化
		if( this.flowTo != $("#flowTo").val() ){
			switch ( $("#flowTo").val() ) {//north, south, east and west
			case "north"://北（问题）
				this.x = this.x;
				this.y = parseInt(this.y)+Math.abs(this.length);
				this.length = 0-Math.abs(this.length);
				break;
			case "south"://南
				this.x = this.x;
				this.y = parseInt(this.y)-Math.abs(this.length);
				this.length = Math.abs(this.length);
				break;
			case "east"://东
				this.x = parseInt(this.x)-Math.abs(this.length);
				this.y = this.y;
				this.length = Math.abs(this.length);
				break;
			case "west"://西（问题）
				this.x = parseInt(this.x)+Math.abs(this.length);
				this.y = this.y;
				this.length = 0-Math.abs(this.length);
				break;
			}
			this.water = null;
		}
		this.flowTo = $("#flowTo").val();
		this.isArrow = $("#isArrow").val();
		
		$("#lineForm").hide(300);
	}
	
	/**
	 *	判断单击的是自己吗
	 */
	this.clickRange = function( mouseX,mouseY ){
		switch (this.flowTo) {//north, south, east and west
			case "north"://北（问题）
				if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.width)) && mouseY >= (parseInt(this.y)+parseInt(this.length)) && mouseY<=parseInt(this.y) ){
					return true;
				}
				break;
			case "south"://南
				if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.width)) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.length)) ){
					return true;
				}
				break;
			case "east"://东
				if( mouseX >= this.x && mouseX <= (parseInt(this.x)+parseInt(this.length)) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.width)) ){
					return true;
				}
				break;
			case "west"://西（问题）
				if( mouseX >= (parseInt(this.x)+parseInt(this.length)) && mouseX <= parseInt(this.x) && mouseY >= this.y && mouseY<=(parseInt(this.y)+parseInt(this.width)) ){
					return true;
				}
				break;
		}
		return false;
	}

	/**
	 *	画出管道
	 */
	this.paint = function(){
		//ctx.save(); 
		ctx.fillStyle=this.fillColor;			//填充的颜色
		
		switch (this.flowTo) {//north, south, east and west
			case "north":
				ctx.fillRect( parseInt(this.x),parseInt(this.y),parseInt(this.width),parseInt(this.length) ,false);	//填充颜色 x y坐标 宽 高
				//画一个【向北】的箭头
				if( this.isArrow == 'top' ){
					//alert('画出一个向上的箭头！');
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+parseInt(this.width)/2, parseInt(this.y)+parseInt(this.length)+10);  	//起始位置
				    ctx.lineTo(parseInt(this.x)-(parseInt(this.width)*1), parseInt(this.y)+parseInt(this.length)+10+parseInt(this.width)*3 );  
				    ctx.lineTo(parseInt(this.x)+(parseInt(this.width)*1)+parseInt(this.width), parseInt(this.y)+parseInt(this.length)+10+parseInt(this.width)*3 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}else if( this.isArrow == 'bottom' ){
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+parseInt(this.width)/2, parseInt(this.y)-10-parseInt(this.width)*3);  	//起始位置
				    ctx.lineTo(parseInt(this.x)-(parseInt(this.width)*1), parseInt(this.y)-10 );  
				    ctx.lineTo(parseInt(this.x)+(parseInt(this.width)*1)+parseInt(this.width), parseInt(this.y)-10 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}
				break;
			case "south":
				ctx.fillRect( parseInt(this.x),parseInt(this.y),parseInt(this.width),parseInt(this.length) ,false);	//填充颜色 x y坐标 宽 高
				//画一个【向南】的箭头
				if( this.isArrow == 'top' ){
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+parseInt(this.width)/2, parseInt(this.y)+10+parseInt(this.width)*3);  	//起始位置
				    ctx.lineTo(parseInt(this.x)-(parseInt(this.width)*1), parseInt(this.y)+10 );  
				    ctx.lineTo(parseInt(this.x)+(parseInt(this.width)*1)+parseInt(this.width), parseInt(this.y)+10 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}else if( this.isArrow == 'bottom' ){
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+parseInt(this.width)/2, parseInt(this.y)+parseInt(this.length)-10);  	//起始位置
				    ctx.lineTo(parseInt(this.x)-(parseInt(this.width)*1), parseInt(this.y)+parseInt(this.length)-10-parseInt(this.width)*3 );  
				    ctx.lineTo(parseInt(this.x)+(parseInt(this.width)*1)+parseInt(this.width), parseInt(this.y)+parseInt(this.length)-10-parseInt(this.width)*3);  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}
				
				break;
			case "east":
				//alert(this.x+","+this.y+","+this.length+","+this.width);
				ctx.fillRect( parseInt(this.x),parseInt(this.y),parseInt(this.length),parseInt(this.width) ,false);	//填充颜色 x y坐标 宽 高
				//画一个【向东】的箭头
				if( this.isArrow == 'left' ){
					//alert('画出一个向上的箭头！');
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+10+parseInt(this.width)*3, parseInt(this.y)+parseInt(this.width)/2 ); //起始位置 
					ctx.lineTo(parseInt(this.x)+10, parseInt(this.y)-parseInt(this.width)*1 );  	
				    ctx.lineTo(parseInt(this.x)+10, parseInt(this.y)+parseInt(this.width)+parseInt(this.width)*1 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}else if( this.isArrow == 'right' ){
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+parseInt(this.length)-10, parseInt(this.y)+parseInt(this.width)/2 );  	//起始位置
				    ctx.lineTo(parseInt(this.x)+parseInt(this.length)-10-parseInt(this.width)*3, parseInt(this.y)-parseInt(this.width)*1 );  
				    ctx.lineTo(parseInt(this.x)+parseInt(this.length)-10-parseInt(this.width)*3, parseInt(this.y)+parseInt(this.width)+parseInt(this.width)*1 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}
				//ctx.stroke();
				break;
			case "west":
				ctx.fillRect( parseInt(this.x),parseInt(this.y),parseInt(this.length),parseInt(this.width) ,false);	//填充颜色 x y坐标 宽 高
				//画一个【向西】的箭头
				if( this.isArrow == 'left' ){
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)+parseInt(this.length)+10, parseInt(this.y)+parseInt(this.width)/2 ); //起始位置 
					ctx.lineTo(parseInt(this.x)+parseInt(this.length)+10+parseInt(this.width)*3, parseInt(this.y)-parseInt(this.width)*1 );  	
				    ctx.lineTo(parseInt(this.x)+parseInt(this.length)+10+parseInt(this.width)*3, parseInt(this.y)+parseInt(this.width)+parseInt(this.width)*1 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色
				    ctx.fill();
				}else if( this.isArrow == 'right' ){
					ctx.beginPath();
					ctx.moveTo(parseInt(this.x)-10-parseInt(this.width)*3, parseInt(this.y)+parseInt(this.width)/2 ); //起始位置 
					ctx.lineTo(parseInt(this.x)-10, parseInt(this.y)-parseInt(this.width)*1 );  	
				    ctx.lineTo(parseInt(this.x)-10, parseInt(this.y)+parseInt(this.width)+parseInt(this.width)*1 );  
				    ctx.closePath();
				    ctx.fillStyle=this.fillColor;			//填充的颜色 
				    ctx.fill();
				}
				break;
		}
		//ctx.restore();
	}

	/**
	 *	动画效果
	 */
	this.animation = function(){
		//先画出基本的线条
		this.paint();
		//判断是否有流动的效果
		if( this.isFlow == 'false' ){
			return;
		}
		//取出动画按钮，如果选择不显示，就不画动画了
		var animated = $("input[name='animated']:checked").val();
		if( animated == 0 ){
			return;
		}
		var length = 11;
		
		switch (this.flowTo) {//north, south, east and west
			case "north":
				//生成水流中的水珠
				if ( this.water == null ) {
					var waterPoints = [];
					for ( var j = 0-length; j >= (this.length-length); j = j - (length+5) ) {
						var water = {
							x : 0,
							y : j
						};
						waterPoints.push(water);
					}
					this.water = waterPoints;
				}

				
				ctx.save(); 
				ctx.beginPath();
				//每个水珠长度11间隔为5一共16.每次移动4，移动4次就归位。
				this.count++;
				ctx.fillStyle = "#009ad6";
				
				if( this.count == 4 ){
					//判断是不是最后一次【1,2,3,4】
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].y)+parseInt(length) < this.length ) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x) + 1;
						var lineY = parseInt(this.y) + parseInt(this.water[j].y);

						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							ctx.fillRect(lineX, lineY, this.width-2, length, false);
						}else{
							//计算画出水珠那一部分
							if( parseInt(this.water[j].y)<parseInt(this.length) ){
								//计算超出【管道】多少
								var len = Math.abs(parseInt(this.length)-parseInt(this.water[j].y))  ;
								if( len > 0 ){
									ctx.fillRect(lineX, parseInt(this.y)+parseInt(this.length), this.width-2, parseInt(length)-len, false);
								}
							}else{
								//没有超出(正常画水珠)
								ctx.fillRect(lineX, lineY, this.width-2, length, false);
							}
						}

						ctx.fill();
						//这个是【1,2,3,4】最后一个，该归位了。
						this.water[j].y = parseInt(this.water[j].y)+(length+1);
					}
					this.count=0;
				}else{
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].y)+parseInt(length) < this.length ) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x) + 1;
						var lineY = parseInt(this.y) + parseInt(this.water[j].y);
						
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							ctx.fillRect(lineX, lineY, this.width-2, length, false);
						}else{
							if( parseInt(this.water[j].y)<parseInt(this.length) ){
								//计算超出【管道】多少
								var len = Math.abs(parseInt(this.length)-parseInt(this.water[j].y))  ;
								if( len > 0 ){
									ctx.fillRect(lineX, parseInt(this.y)+parseInt(this.length), this.width-2, parseInt(length)-len, false);
								}
							}else{
								//没有超出(正常画水珠)
								ctx.fillRect(lineX, lineY, this.width-2, length, false);
							}
						}
						
						ctx.fill();
						//每次移动4像素
						this.water[j].y = parseInt(this.water[j].y)-4;
					}
				}
				ctx.restore();
				break;
			case "south":
				if ( this.water == null ) {
					var waterPoints = [];
					for ( var j = 0; j < this.length; j = j + (length+5) ) {
						var water = {
							x : 0,
							y : j
						};
						waterPoints.push(water);
					}
					this.water = waterPoints;
				}

				ctx.save(); 
				ctx.beginPath();

				this.count++;
				ctx.fillStyle = "#009ad6";
				
				if( this.count == 4 ){
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].y) > this.length) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x) + 1;
						var lineY = parseInt(this.y) + parseInt(this.water[j].y);
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							//没有超出(正常画水珠)
							ctx.fillRect(lineX, lineY, this.width-2, length, false);
						}else{
							//计算水珠的长度[有可能短一点,也可能正好]
							var len = (parseInt(this.water[j].y)+length)>parseInt(this.length)?parseInt(this.length)-parseInt(this.water[j].y):length;
							if( len > 0 ){
								ctx.fillRect(lineX, lineY, this.width-2, len , false);
							}
						}

						ctx.fill();
						//这个是【1,2,3,4】最后一个，该归位了。
						this.water[j].y = parseInt(this.water[j].y)-(length+1);
					}
					this.count=0;
				}else{
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].x) >= this.length) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x) + 1;
						var lineY = parseInt(this.y) + parseInt(this.water[j].y);
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							//没有超出(正常画水珠)
							ctx.fillRect(lineX, lineY, this.width-2, length, false);
						}else{
							//计算水珠的长度[有可能短一点,也可能正好]
							var len = (parseInt(this.water[j].y)+length)>this.length?parseInt(this.length)-parseInt(this.water[j].y):length;
							if( len > 0 ){
								ctx.fillRect(lineX, lineY, this.width-2, len , false);
							}
						}
						
						ctx.fill();
						//每次移动4像素
						this.water[j].y = parseInt(this.water[j].y)+4;
					}
				}
				ctx.restore();
				break;
			case "east":

				//创建水流中的水
				if ( this.water == null ) {
					var waterPoints = [];
					for ( var j = 0; j < this.length; j = j + (length+5) ) {
						var water = {
							x : j,
							y : 0
						};
						waterPoints.push(water);
					}
					this.water = waterPoints;
				}
				
				
				ctx.save(); 
				ctx.beginPath();

				this.count++;
				ctx.fillStyle = "#009ad6";
				
				if( this.count == 4 ){
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].x) > this.length) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x);
						var lineY = parseInt(this.y) + parseInt(this.water[j].y) + 1;
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							ctx.fillRect(lineX, lineY, length, this.width-2, false);
						}else{
							//计算水珠的长度[有可能短一点,也可能正好]
							var len = (parseInt(this.water[j].x)+length)>this.length?parseInt(this.length)-parseInt(this.water[j].x):length;
							if( len > 0 ){
								ctx.fillRect(lineX, lineY, len, this.width-2, false);
							}
						}

						ctx.fill();
						//这个是【1,2,3,4】最后一个，该归位了。
						this.water[j].x = parseInt(this.water[j].x)-(length+1);
					}
					this.count=0;
				}else{
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].x) >= this.length) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x);
						var lineY = parseInt(this.y) + parseInt(this.water[j].y) + 1;
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							ctx.fillRect(lineX, lineY, length, this.width-2, false);
						}else{
							//计算水珠的长度[有可能短一点,也可能正好]
							var len = (parseInt(this.water[j].x)+length)>this.length?parseInt(this.length)-parseInt(this.water[j].x):length;
							if( len > 0 ){
								ctx.fillRect(lineX, lineY, len, this.width-2, false);
							}
						}
						
						ctx.fill();
						//每次移动4像素
						this.water[j].x = parseInt(this.water[j].x)+4;
					}
				}
				ctx.restore();
				break;
			case "west":

				//创建水流中的水
				if ( this.water == null ) {
					var waterPoints = [];
					for ( var j = 0-length; j >= (this.length-length); j = j - (length+5) ) {
						var water = {
							x : j,
							y : 0
						};
						waterPoints.push(water);
					}
					this.water = waterPoints;
				}
				
				ctx.save(); 
				ctx.beginPath();

				this.count++;
				ctx.fillStyle = "#009ad6";
				
				if( this.count == 4 ){
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].x)+parseInt(length) < this.length ) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x);
						var lineY = parseInt(this.y) + parseInt(this.water[j].y) + 1;
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							ctx.fillRect(lineX, lineY, length, this.width-2, false);
						}else{
							if( parseInt(this.water[j].x)<parseInt(this.length) ){
								//计算超出【管道】多少
								var len = Math.abs(parseInt(this.length)-parseInt(this.water[j].x))  ;
								if( len > 0 ){
									ctx.fillRect(parseInt(this.x)+parseInt(this.length), lineY, parseInt(length)-len, this.width-2, false);
								}
							}else{
								//正常画水珠
								ctx.fillRect(lineX, lineY, length, this.width-2, false);
							}
						}

						ctx.fill();
						//这个是【1,2,3,4】最后一个，该归位了。
						this.water[j].x = parseInt(this.water[j].x)+(length+1);
					}
					this.count=0;
				}else{
					for ( var j in this.water) {
						//如果水珠的尾部都出去了，就不用画出来了。
						if( parseInt(this.water[j].x)+parseInt(length) < this.length ) continue;
						//计算水珠相对位置
						var lineX = parseInt(this.x) + parseInt(this.water[j].x);
						var lineY = parseInt(this.y) + parseInt(this.water[j].y) + 1;
						//判断是不是数组中的最后2个水珠（最后的2个有可能画一部分）
						if( j < this.water.length-2 ){
							ctx.fillRect(lineX, lineY, length, this.width-2, false);
						}else{
							if( parseInt(this.water[j].x)<parseInt(this.length) ){
								//计算超出【管道】多少
								var len = Math.abs(parseInt(this.length)-parseInt(this.water[j].x))  ;
								if( len > 0 ){
									ctx.fillRect(parseInt(this.x)+parseInt(this.length), lineY, parseInt(length)-len, this.width-2, false);
								}
							}else{
								//正常画水珠
								ctx.fillRect(lineX, lineY, length, this.width-2, false);
							}
						}
						
						ctx.fill();
						//每次移动4像素
						this.water[j].x = parseInt(this.water[j].x)-4;
					}
				}
				ctx.restore();
				break;
		}
	}

	
	/**
	 *	设置模型
	 */
	this.setModel = function(model){
		this.model = model;
	}
}
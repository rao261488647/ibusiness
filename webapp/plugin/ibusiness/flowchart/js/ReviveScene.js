
/**
 *	复活场景
 *	@author	lsm
 *	@time	2013-05-21
 */
function ReviveScene(model){

	this.jsons = null;

	/**
	 *	获得服务器端的json数据
	 */
	this.requestJson = function(flowId){
		//自适应大小
		//windowResize();
		$.ajax({
			 type: "POST",
			 url: "/"+window.location.pathname.split("/")[1]+"/flowchart/query-flow-chart.do?flowId="+flowId,
			 dataType: "json",
			 success: function(data){
				 this.jsons = data;
				// this.jsons = JSON.parse(data);
				 //将json创建各种对象
				for(var i=0;i<this.jsons.length;i++){
					var jsonString = this.jsons[i].context;
					var json = JSON.parse(jsonString);
					var x = parseInt(json.x);
					var y =parseInt(json.y);
					var obj = null;
					switch( json.type ){
						case "Pipeline":
							var p = new Pipeline();
							p.init();
							elements.insert( 0 , p);
							break;
						case "Lane":
							obj = new Lane(json.id, x, y);
							obj.init(json);
							break;
						case "TaskNode":
							obj = new TaskNode(json.id, x, y);
							obj.init(json);
							break;
						case "StartNode":
							obj = new StartNode(json.id, x, y);
							obj.init(json);
							break;
						case "EndNode":
							obj = new EndNode(json.id, x, y);
							obj.init(json);
							break;
						case "Gateway":
							obj = new Gateway(json.id, x, y);
							obj.init(json);
							break;
						case "Line":
							obj = new Line(json.id, x, y);
							obj.init(json);
							break;
						case "Texts":
							obj = new Texts();
							obj.init();
							break;
					}
					if( obj != null ){
						elements.add(obj);
					}
				}
			 },
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
				 elements.getId('console').realData = '请求数据出错了!';
	         } 
		});
	}

	/**
	 *	将json数据画出图形
	 */
	this.paintAll = function(){
		if( ctx != null ){
			/*
			//清空画板
			ctx.clearRect(0,0,800,500);
			for(var i=0;i<elements.size();i++){
				var ele = elements.valSet()[i];
				//绘制动画下效果
				ele.animation();
			}
			*/
			//将背景画板涂黑
			ctx.fillStyle='#000000';
			ctx.fillRect( 0,0,800,500 );	//填充颜色 x y坐标 宽 高
		}
	}
}
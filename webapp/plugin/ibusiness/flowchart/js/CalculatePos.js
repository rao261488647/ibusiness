/**
		画图的种类
*/
var flag=false;

  /**
       		表示方向
       */
       var E="E";
       var W="W";
       var S="S";
       var N="N";
    /**
       		箭头角度
       */
       var arrowAngle=30;
       /**
       		箭头半径
       */
       var arrowRadius=5;
       /**
        	箭头长度
       */
       var arrowLenght=10;
		/**
		 * 计算划线的开始坐标
		 */
		function calculateStartEndPos(imgStart,imgEnd,realPoints){ 
				var realSx=0;
				var realSy=0;
				var realEx=0;
				var realEy=0;
		
				var sx=imgStart.x;
				var sy=imgStart.y;
				var swidth=imgStart.width;
				var sheight=imgStart.height; 
				var ex=imgEnd.x;
				var ey=imgEnd.y; 
				var ewidth=imgEnd.width;
				var eheight=imgEnd.height;  
				// 计算连线开始和结束坐标的位置
				var array=calculateXY(sy,sheight,ey,eheight ,sx,swidth,ex,ewidth);
				var crossArray=null;
				if((array[0]!=array[2])&&(array[1]!=array[3])){
					// 计算折叠点的位置
					var crossArray= calculateCrossPoints(array,sy,sheight,ey,eheight ,sx,swidth,ex,ewidth);
				} 
				realPoints.push(array[0]);
				realPoints.push(array[1]);
				if(crossArray!=null) 
		 		for(var i=0;i<crossArray.length;i++){
		 			realPoints.push(crossArray[i]);
		 		}
				realPoints.push(array[2]);
				realPoints.push(array[3]); 
				return realPoints;
		}
   /**
	 * 计算连线开始和结束坐标的位置
	 */
	function calculateXY(sy,sheight,ey,eheight,sx,swidth,ex,ewidth ){
			var array=[];
			if(sy==ey){ 
				if(sx>ex){ 
					array.push(sx);
					array.push(sy+(sheight/2));
					array.push(ex+ewidth);
					array.push(sy+(eheight/2));
				}else{
					array.push(sx+swidth);
					array.push(sy+(sheight/2));
					array.push(ex);
					array.push(sy+(eheight/2));
				}
			}else{ 
				if(sy>ey&&(sy-ey>sheight)){
					if(sx>ex){
					 	if(sx-ex<swidth/2){
					 		array.push(sx);
	 					array.push(sy+(sheight/2));
	 					array.push(ex);
	 					array.push(ey+eheight/2);
					 	}else{
					 		array.push(sx);
	 					array.push(sy+(sheight/2));
	 					array.push(ex+(ewidth/2));
	 					array.push(ey+eheight);
					 	}
					}else if(sx==ex){
						array.push(sx+(swidth/2));
						array.push(sy);
						array.push(ex+(ewidth/2));
						array.push(ey+eheight);
					}else{
						if(ex-sx<ewidth/2){
							array.push(sx);
	 					array.push(sy+(sheight/2));
	 					array.push(ex);
	 					array.push(ey+eheight/2);
						}else{
							array.push(sx+swidth);
	 					array.push(sy+(sheight/2));
	 					array.push(ex+(ewidth/2));
	 					array.push(ey+eheight);
						}
					}
				}else if(sy<ey&&(ey-sy>eheight)){
					if(sx>ex){
					 	if(sx-ex<swidth/2){
					 		array.push(sx);
					 		array.push(sy+sheight/2);
					 		array.push(ex);
					 		array.push(ey+eheight/2);
					 	}else{
					 		array.push(sx+(swidth/2));
	 					array.push(sy+sheight);
	 					array.push(ex+ewidth);
	 					array.push(ey+(eheight/2));
					 	}
						
					}else if(sx==ex){
						array.push(sx+(swidth/2));
						array.push(sy+sheight);
						array.push(ex+(ewidth/2));
						array.push(ey);
					}else{
						if(ex-sx<ewidth/2){
							array.push(sx);
							array.push(sy+sheight/2);
							array.push(ex);
							array.push(ey+eheight/2);
						}else{
	 					array.push(sx+(swidth/2));
	 					array.push(sy+sheight);
	 					array.push(ex);
	 					array.push(ey+(eheight/2));
						}
					}
				}else{
					if((sy<ey&&(ey-sy<eheight))||sy>ey&&(sy-ey<sheight)){
						array.push(sx+swidth/2);
						array.push(sy);
						array.push(ex+ewidth/2);
						array.push(ey);
					} 
				}
			} 
			return  array;
	}
	/**
	  *		计算折叠点的位置
	  */
	function calculateCrossPoints(array,sy,sheight,ey,eheight ,sx,swidth,ex,ewidth){
			var pointsArray=[];
			var x=array[0];
			var y=array[1];
			var x1=array[2];
			var y1=array[3];
			var f=false;
			if((x-x1)>0&&(y-y1)>0){
				f=true;
			}
			if((x1-x)>0&&(y1-y)>0){
				f=true;
			}
			/**
				y轴的多个折叠点
			*/
			if((sy>ey&&sy-ey<=sheight)||(sy<ey&&ey-sy<=eheight)){
				if(sy>ey&&sy-ey<=sheight){
					pointsArray.push(x);
					pointsArray.push(y1-sheight);
					pointsArray.push(x1);
					pointsArray.push(y1-sheight);
				}else if(sy<ey&&ey-sy<=eheight){
					pointsArray.push(x);
					pointsArray.push(y-eheight);
					pointsArray.push(x1);
					pointsArray.push(y-eheight);
				}
			}else if((sx>ex&&sx-ex<=sheight/2)||(sx<ex&&ex-sx<=eheight/2)){
				/**
			 		x轴的多个折点
			 	*/
				//x= sx-swidth/2
				//y=y+sheight/2
				 
				if(sx-ex<sheight/2){
					pointsArray.push(sx-ewidth);
					pointsArray.push(sy+sheight/2);
					pointsArray.push(sx-ewidth);
					pointsArray.push(ey+eheight/2);
				}else if(ex-sx<eheight/2){
					pointsArray.push(ex-swidth);
					pointsArray.push(sy+sheight/2);
					pointsArray.push(ex-swidth);
					pointsArray.push(ey+eheight/2);
				}
			}else{
				/**
				单个折叠点
				*/
				if(f){
		 			if(x<x1){
		 				pointsArray.push(x);
		 			}else{
		 				pointsArray.push(x1);
		 			}
		 			if(y<y1){
		 				pointsArray.push(y1);
		 			}else{
		 				pointsArray.push(y);
		 			}
		 		}else{ 
		 			if(x<x1){
		 				pointsArray.push(x1);
		 			}else{
		 				pointsArray.push(x);
		 			}
		 			if(y<y1){
		 				pointsArray.push(y1);
		 			}else{
		 				pointsArray.push(y);
		 			}
		 		}
			}
			return pointsArray;
	}
	
	
	
	
	    /**
	      * 通过line计算 arrow 的points 数组
	      */
	function calculateArrowPointsByLine(line){
			var points=line.getPoints();
			var lenght=points.length; 
			var x=points[lenght-4];
			var y=points[lenght-3];
			var x1=points[lenght-2];
			var y1=points[lenght-1];  
			return calculateArrowPosition(x1,y1,calculateArrowAspect(x,y,x1,y1));
	 
	}
	function calculateArrowPointsByPoints(points){
			var lenght=points.length;
			var x=points[lenght-4];
			var y=points[lenght-3];
			var x1=points[lenght-2];
			var y1=points[lenght-1]; 
			return calculateArrowPosition(x1,y1,calculateArrowAspect(x,y,x1,y1));
	}
	/**
		 计算箭头朝向
	*/
	function calculateArrowAspect(x,y,x1,y1){
			if(x==x1){
				if(y>y1){
					return N;
				}else{
					return S;
				}
			}else if(y==y1){
				if(x>x1){
					return W;
				}else{
					return E;
					
				}
			}
	}
	
	/**
		  计算箭头具体位置
	*/
	function calculateArrowPosition(x,y,aspect){
			 
			var points=[];
			switch(aspect){
				case N:
					points.push(x);
					points.push(y);
					points.push(x-arrowRadius);
					points.push(y+arrowLenght);
					points.push(x+arrowRadius);
					points.push(y+arrowLenght); 
					break;
				case S:
					points.push(x);
					points.push(y);
					points.push(x-arrowRadius);
					points.push(y-arrowLenght);
					points.push(x+arrowRadius);
					points.push(y-arrowLenght);
				 
					break;
				case E:
					points.push(x);
					points.push(y);
					
					points.push(x-arrowLenght);
					points.push(y+arrowRadius);  
					
					points.push(x-arrowLenght);
					points.push(y-arrowRadius);
					
					break;
				case W:
					points.push(x);
					points.push(y);
					points.push(x+arrowLenght);
					points.push(y+arrowRadius);  
					
					points.push(x+arrowLenght);
					points.push(y-arrowRadius);
					
					break;
			}
		 
			return points;
	}

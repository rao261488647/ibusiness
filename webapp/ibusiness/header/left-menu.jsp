<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>左侧导航栏</title>
    
    <style>
    	#main-nav { font-family:"微软雅黑";font-size:16px}
		#main-nav.nav-tabs.nav-stacked > li > a {padding: 20px 8px 20px 30px;font-size: 12px;font-weight: 600;
                		color: #c0c0c0;background: #333742; }
		#main-nav.nav-tabs.nav-stacked > li > a > span {color: #fff; }
 		#main-nav.nav-tabs.nav-stacked > li.active > a{
						color: #FFF;  }
		#main-nav.nav-tabs.nav-stacked > li > a {padding: 20px 8px 20px 30px;font-size: 14px;font-weight: 600;
                		color: #c0c0c0;background: #333742; }
				
		#main-nav.nav-tabs.nav-stacked > li > a:hover,#main-nav.nav-tabs.nav-stacked > li > a:active{color: #FFF;}
		#main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
                        color: #FFF; }
						
		.nav-stacked>li+li{margin-top:0px}
		.glyphicon{margin-right:15px}
		.nav-tabs>li>a:hover{border-radius:0px;border:0px;color:#fff}
		.nav-tabs>li>a{border-radius:0px;margin-right:0px;border:0px}
		.nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus{border:none}

		.menu-second>li>a{padding:10px 55px; font-size:12px}
		
		.col-md-12,.container-fluid{padding-left:0px;padding-right:0px}
		.panel-body{padding:15px 0px}
		
        /*定义二级菜单样式*/
        .secondmenu a {
            font-size: 10px;
            color: #4A515B;
            text-align: left;
        }
     	
    </style>
    
    <script type="text/javascript">
		var menuname = getRequest()["menuname"];//从url获取menuname
		
		// 获取url参数
       	function getRequest() {  
       		var url = location.search;  
        	var theRequest = new Object();  
        	if (url.indexOf("?") != -1) {
            	var str = url.substr(1);  
            	strs = str.split("&");  
            	for ( var i = 0; i < strs.length; i++) {  
                	theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
            	}  
        	}
        	return theRequest;  
		}	
		
		
		
	</script>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <!-- 动态菜单部分 -->
					        <c:forEach items="${menuItemList}" var="item">
					            <!-- 无子节点 -->
	                            <c:if test="${item.chiledItems == '[]'}">
	                                <c:if test="${item.menuUrl == '#'}">
	                                
	                                	<li class="active">
                        					<a href="#">
                            					<i class="glyphicon glyphicon-th-large"></i>
                           					 ${item.menuName}
                        					</a>
                    					</li>
	                                
								    </c:if>
								    <c:if test="${item.menuUrl != '#'}">
							            <c:if test="${fn:indexOf(item.menuUrl, 'http:')>=0}">
								    	    
								    	    <li class="active">
                        						<a href="${item.menuUrl}">
                        							<i class="glyphicon glyphicon-th-large"></i>
                           						 ${item.menuName}
                        						</a>
                    						</li>
								    	    
							    	    </c:if>
							    	    <c:if test="${fn:indexOf(item.menuUrl, 'http:')<0}">
								    	    
								    	    <li class="active">
                        						<a href="${ctx}${item.menuUrl}">
                        							<i class="glyphicon glyphicon-th-large"></i>
                           						 ${item.menuName}
                        						</a>
                    						</li>
                    						
							    	    </c:if>
								    </c:if>
	                            </c:if>
                    
                    
                    			<!-- 有子节点的 -->
								<c:if test="${item.chiledItems != '[]'}">
	                                <li class="active">
	                                	<a href="#${item.menuName}Meun" data-toggle="collapse" class="collapsed">
	                                		<i class="glyphicon glyphicon-cog"></i>${item.menuName}
	                                		<span class="pull-right glyphicon glyphicon-chevron-down"></span>
	                                	</a>
	                                </li>
	                                <ul id="${item.menuName}Meun" class="nav nav-list collapse.in menu-second collapse" style="height: 0px;">
							            <c:forEach items="${item.chiledItems}" var="son">
											    <c:if test="${son.chiledItems == '[]'}">
													<li>
													    <c:if test="${son.menuUrl == '#'}">
													    	<a href="#"><i class="glyphicon glyphicon-th-list"></i> ${son.menuName}</a>
													    </c:if>
													    <c:if test="${son.menuUrl != '#'}">
													        <c:if test="${fn:indexOf(son.menuUrl, 'http:')>=0}">
													    	    <a href="${son.menuUrl}"><i class="glyphicon glyphicon-th-list"></i> ${son.menuName}</a>
												    	    </c:if>
												    	    <c:if test="${fn:indexOf(son.menuUrl, 'http:')<0}">
													    	    <a href="${ctx}${son.menuUrl}"><i class="glyphicon glyphicon-th-list"></i> ${son.menuName}</a>
												    	    </c:if>
													    </c:if>
													</li>
												</c:if>
												<c:if test="${son.chiledItems != '[]'}">
												    <li class="divider"></li>
													<li class="dropdown-submenu"><a href="#" ><i class="glyphicon glyphicon-th-list"></i>${son.menuName}</a>
														<ul class="dropdown-menu  panel-body nav nav-list">
															<c:forEach items="${son.chiledItems}" var="grandson">
															    <li>
																    <c:if test="${grandson.menuUrl == '#'}">
																    	<a href="#"><i class="glyphicon glyphicon-th-list"></i>${grandson.menuName}</a>
																    </c:if>
																    <c:if test="${grandson.menuUrl != '#'}">
																        <c:if test="${fn:indexOf(grandson.menuUrl, 'http:')>=0}">
																    	    <a href="${grandson.menuUrl}"><i class="glyphicon glyphicon-th-list"></i>${grandson.menuName}</a>
															    	    </c:if>
															    	    <c:if test="${fn:indexOf(grandson.menuUrl, 'http:')<0}">
																    	    <a href="${ctx}${grandson.menuUrl}"><i class="glyphicon glyphicon-th-list"></i>${grandson.menuName}</a>
															    	    </c:if>
																    </c:if>
																</li>
															</c:forEach>
														</ul>
													</li>
												</c:if>
										</c:forEach>
			                         </ul>
								</c:if>
	                        </c:forEach>

                </ul>
            </div>
            <div class="col-md-10">
                
            </div>
        </div>
    </div>
    
    <script>
    </script>
</body>
<script type="text/javascript">	
	var navDiv = document.getElementById("main-nav");
	var innerHeight = document.documentElement.clientHeight;
	navDiv.style.height = innerHeight-53+"px";
	//navDiv.style.background = "#272b30";
</script>
</html>

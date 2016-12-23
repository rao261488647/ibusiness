<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <c:if test="${not empty flashMessages}">
		<div id="m-success-message" style="display:none;">
		  <ul>
		  <c:forEach items="${flashMessages}" var="item">
		    <li>${item}</li>
		  </c:forEach>
		  </ul>
		</div>
	</c:if>
	<!-- 菜单CSS样式 -->
	<style type="text/css">
        .dropdown-submenu {
            position: relative;
        }
        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -6px;
            margin-left: -1px;
            -webkit-border-radius: 0 6px 6px 6px;
            -moz-border-radius: 0 6px 6px;
            border-radius: 0 6px 6px 6px;
        }
        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }
        .dropdown-submenu > a:after {
            display: block;
            content: " ";
            float: right;
            width: 0;
            height: 0;
            border-color: transparent;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            border-left-color: #ccc;
            margin-top: 5px;
            margin-right: -10px;
        }
        .dropdown-submenu:hover > a:after {
            border-left-color: #fff;
        }
        .dropdown-submenu.pull-left {
            float: none;
        }
        .dropdown-submenu.pull-left > .dropdown-menu {
            left: -100%;
            margin-left: 10px;
            -webkit-border-radius: 6px 0 6px 6px;
            -moz-border-radius: 6px 0 6px 6px;
            border-radius: 6px 0 6px 6px;
        }
    </style>
	
    <!-- start of header bar -->
    <div class="container col-lg-2 ">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
				<div class="row">
					<div class="col-md-12">
					    <div class="sidebar-menu">
					        <!-- 动态菜单部分 -->
					        <c:forEach items="${menuItemList}" var="item">
					            <!-- 无子节点 -->
	                            <c:if test="${item.chiledItems == '[]'}">
	                                <c:if test="${item.menuUrl == '#'}">
								    	<div class="menu-first" ><a href="#" >${item.menuName}</a></div>
								    </c:if>
								    <c:if test="${item.menuUrl != '#'}">
							            <c:if test="${fn:indexOf(item.menuUrl, 'http:')>=0}">
								    	    <div class="menu-first" ><a href="${item.menuUrl}">${item.menuName}</a></div>
							    	    </c:if>
							    	    <c:if test="${fn:indexOf(item.menuUrl, 'http:')<0}">
								    	    <div class="menu-first" ><a href="${ctx}${item.menuUrl}">${item.menuName}</a></div>
							    	    </c:if>
								    </c:if>
	                            </c:if>
	                            
	                            <!-- 有子节点的 -->
								<c:if test="${item.chiledItems != '[]'}">
	                                <div class="menu-first" ><a href="#${item.menuName}Meun" data-toggle="collapse">${item.menuName}</a></div>
	                                <ul id="${item.menuName}Meun" class="nav nav-list collapse.in menu-second">
							            <c:forEach items="${item.chiledItems}" var="son">
											    <c:if test="${son.chiledItems == '[]'}">
													<li>
													    <c:if test="${son.menuUrl == '#'}">
													    	<a href="#"> ${son.menuName}</a>
													    </c:if>
													    <c:if test="${son.menuUrl != '#'}">
													        <c:if test="${fn:indexOf(son.menuUrl, 'http:')>=0}">
													    	    <a href="${son.menuUrl}"> ${son.menuName}</a>
												    	    </c:if>
												    	    <c:if test="${fn:indexOf(son.menuUrl, 'http:')<0}">
													    	    <a href="${ctx}${son.menuUrl}">${son.menuName}</a>
												    	    </c:if>
													    </c:if>
													</li>
												</c:if>
												<c:if test="${son.chiledItems != '[]'}">
												    <li class="divider"></li>
													<li class="dropdown-submenu"><a href="#"><i class="icon-user"></i>${son.menuName}</a>
														<ul class="dropdown-menu  panel-body nav nav-list">
															<c:forEach items="${son.chiledItems}" var="grandson">
															    <li>
																    <c:if test="${grandson.menuUrl == '#'}">
																    	<a href="#"><i class="icon-user"></i>${grandson.menuName}</a>
																    </c:if>
																    <c:if test="${grandson.menuUrl != '#'}">
																        <c:if test="${fn:indexOf(grandson.menuUrl, 'http:')>=0}">
																    	    <a href="${grandson.menuUrl}"><i class="icon-user"></i>${grandson.menuName}</a>
															    	    </c:if>
															    	    <c:if test="${fn:indexOf(grandson.menuUrl, 'http:')<0}">
																    	    <a href="${ctx}${grandson.menuUrl}"><i class="icon-user"></i>${grandson.menuName}</a>
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
					        	
					    
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ============================================================================= -->
    <!-- end of header bar -->

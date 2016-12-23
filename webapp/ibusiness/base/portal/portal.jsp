<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
    
  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <%@include file="/common/meta.jsp"%>
    <title>iBusiness智能平台</title>
    <%@include file="/common/center.jsp"%>
    <!-- CSS  -->
    <link rel="stylesheet" href="${ctx}/plugin/ibusiness/imac/css/style.css" type="text/css" />
    <script type="text/javascript" src="${ctx}/plugin/ibusiness/imac/js/imac.js"></script>
    <!--  -->
    <script type="text/javascript">
	    function init() {
	        $(".launch").click();
	    }
    </script>
    <!-- 图片右上角数值CSS样式 -->
    <style>
		.imageBoxItem{position: relative;}
		/**background-color:red; */
		.diagnoseItem{position: absolute;display: block;width: 11px;height: 11px;right:-30px;top:0px;color:#fff;}
	</style>
  </head>

  <body onload="init()">
    <!-- start of main -->
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    
    <div class="panel panel-default ">
    <!-- 图标显示画布DIV  -->
      <div id="launchpad" style="width:100%">
          <!--***************************   ***********************************-->
          <c:forEach items="${deskMenuItems}" var="item">
             <div class="icon imageBoxItem" style="width:30px">
                   <a href="${ctx}${item.menuUrl}">
                       <img src="${ctx}/plugin/ibusiness/${item.iconUrl}" title="${item.menuName}"  />
                       <span class="badge">${item.menuName}</span>
                    </a>
                    <!-- 字体加粗 最大值900  -->
                    <c:if test="${null != item.dataCount}">
                        <span class="diagnoseItem" style="color:red;  font-weight:900; font-size:12px; " >${item.dataCount}</span>
                    </c:if>
              </div>
          </c:forEach>
      </div>
      
      <div class="title"></div>
      <img src="${ctx}/plugin/ibusiness/imac/img/launchpad.png" title="launchpad"  class="launch"  />
    </div>
  </body>
</html>



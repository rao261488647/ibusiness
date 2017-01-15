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
<table class="table table-bordered" style="width:60%">
   <caption>来店客户统计分析<a class="btn btn-primary" href="${ctx}/count/count1.do"><span class="glyphicon glyphicon-search"></span>查询</a></caption>
	   <tbody>
	      <tr>
	         <td>今日来店司机数</td>
	         <td>本周来店司机数</td>
	         <td>本月来店司机数</td>
	         <td>累计来店司机数</td>
	      </tr>
	      <tr>
	         <td>${customerCount1.count1}</td>
	         <td>${customerCount1.count4}</td>
	         <td>${customerCount1.count7}</td>
	         <td>${customerCount1.count10}</td>
	      </tr>
	      <tr>
	         <td>今日签约司机数</td>
	         <td>本周签约司机数</td>
	         <td>本月签约司机数</td>
	         <td>累计签约司机数</td>
	      </tr>
	      <tr>
	         <td>${customerCount1.count2}</td>
	         <td>${customerCount1.count5}</td>
	         <td>${customerCount1.count8}</td>
	         <td>${customerCount1.count11}</td>
	      </tr>
	      <tr>
	         <td>签约率</td>
	         <td>签约率</td>
	         <td>签约率</td>
	         <td>签约率</td>
	      </tr>
	      <tr>
	         <td>${customerCount1.count3}%</td>
	         <td>${customerCount1.count6}%</td>
	         <td>${customerCount1.count9}%</td>
	         <td>${customerCount1.count12}%</td>
	      </tr>
	   </tbody>
	</table>

<table class="table table-bordered" style="width:60%">
<caption>所有司机数据分析<a class="btn btn-primary" href="${ctx}/count/count2.do"><span class="glyphicon glyphicon-search"></span>查询</a></caption>
   <tbody>
      <tr>
         <td>今日在职司机总数</td>
         <td>今日在职自营司机总数</td>
         <td>今日以租代购司机总数</td>
         <td>无</td>
      </tr>
      <tr>
         <td>${customerCount2.count1}</td>
         <td>${customerCount2.count2}</td>
         <td>${customerCount2.count3}</td>
         <td></td>
      </tr>
      <tr>
         <td>今日司机提车数</td>
         <td>本周司机提车数</td>
         <td>本月司机提车数</td>
         <td>累计总提车数</td>
      </tr>
      <tr>
         <td>${customerCount2.count4}</td>
         <td>${customerCount2.count5}</td>
         <td>${customerCount2.count6}</td>
         <td>${customerCount2.count7}</td>
      </tr>
      <tr>
         <td>今日离职司机数</td>
         <td>本周离职司机数</td>
         <td>本月离职司机数</td>
         <td>累计总离职数</td>
      </tr>
      <tr>
         <td>${customerCount2.count8}</td>
         <td>${customerCount2.count9}</td>
         <td>${customerCount2.count10}</td>
         <td>${customerCount2.count11}</td>
      </tr>
   </tbody>
</table>


<table class="table table-bordered" style="width:60%">
<caption>截至今日各车型库存盘点<a class="btn btn-primary" href="${ctx}/count/count3.do"><span class="glyphicon glyphicon-search"></span>查询</a></caption>
   <tbody>
      <tr>
         <td>车型</td>
         <td>数量</td>
         <td>已出库</td>
         <td>待出库</td>
         <td>维修厂</td>
         <td>外借</td>
         <td>出车率</td>
      </tr>
      <c:forEach items="${customerCountList1}" var="item" varStatus="status">  
	     <tr class="">
	         <td>${item.name}</td>
	         <td>${item.count1}</td>
	         <td>${item.count2}</td>
	         <td>${item.count3}</td>
	         <td>${item.count4}</td>
	         <td>${item.count5}</td>
	         <td><fmt:formatNumber value="${item.count1==0?0:(item.count2*100/item.count1)}" pattern="#,###.##" />%</td>
	     </tr>
	  </c:forEach>
      <tr>
         <td>合计</td>
         <td>${customerCount10.count1}</td>
         <td>${customerCount10.count2}</td>
         <td>${customerCount10.count3}</td>
         <td>${customerCount10.count4}</td>
         <td>${customerCount10.count5}</td>
         <td>${customerCount10.count6}%</td>
      </tr>
   </tbody>
</table>


<table class="table table-bordered" style="width:60%">
<caption>车辆出入库情况周统计表<a class="btn btn-primary" href="${ctx}/count/count4.do"><span class="glyphicon glyphicon-search"></span>查询</a></caption>
   <tbody>
      <tr>
         <td>车型</td>
         <td>上周库存</td>
         <td>退车入库</td>
         <td>新车入库</td>
         <td>车辆出库</td>
         <td>现库存</td>
      </tr>
     <c:forEach items="${customerCountList2}" var="item" varStatus="status">  
	     <tr class="">
	         <td>${item.name}</td>
	         <td>${item.count1}</td>
	         <td>${item.count2}</td>
	         <td>${item.count3}</td>
	         <td>${item.count4}</td>
	         <td>${item.count5}</td>
	     </tr>
	  </c:forEach>
      <tr>
         <td>合计</td>
         <td>${customerCount11.count1}</td>
         <td>${customerCount11.count2}</td>
         <td>${customerCount11.count3}</td>
         <td>${customerCount11.count4}</td>
         <td>${customerCount11.count5}</td>
      </tr>
   </tbody>
</table>


<table class="table table-bordered" style="width:60%">
<caption>维修保养周统计表<a class="btn btn-primary" href="${ctx}/count/count5.do"><span class="glyphicon glyphicon-search"></span>查询</a></caption>
   <tbody>
      <tr>
         <td>维修厂</td>
         <td>保养</td>
         <td>维修进厂</td>
         <td>维修出厂</td>
      </tr>
      <c:forEach items="${customerCountList3}" var="item" varStatus="status">  
	     <tr class="">
	         <td>${item.name}</td>
	         <td>${item.count1}</td>
	         <td>${item.count2}</td>
	         <td>${item.count3}</td>
	     </tr>
	  </c:forEach>
      <tr>
         <td>合计</td>
         <td>${customerCount12.count1}</td>
         <td>${customerCount12.count2}</td>
         <td>${customerCount12.count3}</td>
      </tr>
   </tbody>
</table>

<table class="table table-bordered" style="width:60%">
<caption>违章周统计表<a class="btn btn-primary" href="${ctx}/count/count6.do"><span class="glyphicon glyphicon-search"></span>查询</a></caption>
   <tbody>
      <tr>
         <td>违章车辆数</td>
         <td>${map1.count}</td>
      </tr>
      <c:forEach items="${list1}" var="item" varStatus="status">  
	     <tr class="">
	         <td>${item.name}</td>
	         <td>${item.count}</td>
	     </tr>
	  </c:forEach>
      <tr>
         <td>违章总数</td>
         <td>${map2.count}</td>
      </tr>
   </tbody>
</table>
      </div>
      
      <div class="title"></div>
      <img src="${ctx}/plugin/ibusiness/imac/img/launchpad.png" title="launchpad"  class="launch"  />
    </div>
  </body>
</html>



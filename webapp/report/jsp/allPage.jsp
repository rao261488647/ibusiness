<%@page pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import ="java.util.*,java.text.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>巨米电商-AllPage</title>
      	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/global.css" />
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/global_color.css" />
            
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="<%=request.getContextPath()%>/images/logo.png" alt="logo" class="left"/>
            <a href="javascript:void(0)"  onclick="location.reload()">[刷新]</a>            
        </div>
        <!--Logo区域结束-->
        <div id="main">
        	<div id="link" style="padding:10px;">
	           <!-- --> 
	            <a href="<%=request.getContextPath()%>/report/jsp/report/show_report.jsp?reportName=goodsRepot.raq&reportTitleName=goodsRepot_arg.raq">电商订单查看页面(订单生成时间)</a><br/>
	            <a href="<%=request.getContextPath()%>/report/jsp/report/show_report.jsp?reportName=classRepot.raq&reportTitleName=classRepot_arg.raq">电商商家商品报表页面(订单生成时间)</a><br/>
	            <a href="<%=request.getContextPath()%>/report/jsp/report/show_report.jsp?reportName=dayRepot.raq&reportTitleName=dayRepot_arg.raq">电商订单报表页面(订单生成时间)</a><br/>
			  	<a href="<%=request.getContextPath()%>/report/jsp/report/show_report.jsp?reportName=macketRepot.raq&reportTitleName=macketRepot_arg.raq">小区仓买报表(订单生成时间)</a><br/>
			  	
			</div>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[最优秀的开发人员，最真诚的企业精神，最前沿的科技水平，最完美的互联网产品]</p>
            <p>版权所有(C)哈尔滨市巨米科技发展有限公司 </p>
        </div>
    </body>
</html>
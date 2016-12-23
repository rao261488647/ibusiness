<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>报表</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/report/jsp/report/css/calendar.css"/>"></link>
		<style type="text/css">
		<!--
		.STYLE2 {font-size: 12px}
		-->
		</style>
		<script type="text/javascript" src="<c:url value="/report/jsp/report/js/calendar.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/report/jsp/report/js/show_report.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/report/jsp/report/js/date_operation.js"/>"></script>
	</head>
	<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0 >
	    <table class="ds_box" cellpadding="0" cellspacing="0" id="ds_conclass" style="display: none;">
            <tr>
                <td id="ds_calclass"></td>
            </tr>
        </table>
		<table align=center>
			<!-- 第一行显示  自定义、开始时间、结束时间、粒度 -->
			<tr>
				<td bgcolor="#ddebf6">
					<form name="reportForm" id="reportForm" action="<c:url value="/reportAction!searchReport.action" />" method="post">
						<!-- 报表条件选择区域开始  -->
						<table id="conditionArear" cellspacing="" cellpadding="5px" border="0px">
							<tr>
	                            <td><report:param name="titleForm" paramFileName="${reportBean.reportTitleName}" needSubmit="no" /></td>
	                        </tr>
							<tr>
							    <!-- 查询按钮 -->
								<td>
									<input TYPE="button" value="查询" onClick="doSubmit()">
								</td>
							</tr>
						</table>
						<!-- 报表条件选择区域结束  -->
						<input type="hidden" id="hiddenDegree" value="${reportBean.degree}" />
						<input type="hidden" name="reportName" value="${reportBean.reportName}" />
						<input type="hidden" name="reportTitleName" value="${reportBean.reportTitleName}" />
						<input type="hidden" name="hiddenPageCount" value="${reportBean.pageCount}" />
					</form>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<c:if test="${param.name==''}">
						<script language="javascript">
                        alert("报表展现中暂时遇到问题 请关闭当前报表页面后重试");
                    </script>
					</c:if>
					<c:if test="${param.name==''}">
						<script language="javascript">alert("报表展现中暂时遇到问题请关闭当前报表页面后重试");</script>
					</c:if>
					<c:if test="${param.name!=''}">
						<report:extHtml name="reportResult" reportFileName="${reportBean.reportName}"
                                    funcBarLocation="no" needPageMark="yes"
                                    functionBarColor="#fff5ee" funcBarFontFace="微软雅黑"
                                    funcBarFontSize="14px" funcBarFontColor="#17b578"
                                    separator="|" needSaveAsExcel="yes" needSaveAsPdf="yes"
                                    needSaveAsWord="yes" needPrint="yes"
                                    printLabel="打印" generateParamForm="no" displayNoLinkPageMark="yes"
                                    needSelectPrinter="yes" needScroll="no" scrollWidth="600"
                                    scrollHeight="400" exceptionPage="/report/jsp/report/reportError.jsp"
                                    totalCountExp="${reportBean.totalCount}"
                                    pageCount="${reportBean.pageCount}"
                                    cachePageNum="5"
                                    startRowParamName="startRow"
                                    endRowParamName="endRow"
                                    params="${reportBean.param}"
                        />
					</c:if>
				</td>
			</tr>
		</table>
		<table id=titleTable width=100% cellspacing=0 cellpadding=0 border=0>
			<tr>
				<td align="left" height="22" width=100% valign="middle" style="font-size: 13px" background="../images/ta51top.jpg">
					<table width="100%">
						<tr>
							<td width="100%" align="center" valign="middle" style="font-size: 12px">共<span id="t_page_span"></span>页/第<span id="c_page_span"></span>页&nbsp;&nbsp;
								<a href="#" title="存为Excel" onClick="reportResult_saveAsExcel();"><img src='images/excel.gif' border=no alt=存为Excel></a>
								<a href="#" title="存为PDF" onClick="reportResult_saveAsPdf();return false;"><img src='images/pdf.gif' border=no alt=存为PDF></a>
								<a href="#" title="首页" onClick="try{reportResult_toPage( 1 );}catch(e){}return false;"><img src='images/firstpage.gif' border=no alt=首页></a>
								<a href="javascript:;" title="上一页" onClick="try{reportResult_toPage(reportResult_getCurrPage()-1);}catch(e){}return false;"><img src='images/prevpage.gif' border=no alt=上一页></a>
								<a href="javascript:;" title="下一页" onClick="try{reportResult_toPage(reportResult_getCurrPage()+1);}catch(e){}return false;"><img src='images/nextpage.gif' border=no alt=下一页></a>
								<a href="#" title="尾页" onClick="try{reportResult_toPage(reportResult_getTotalPage());}catch(e){}return false;"><img src='images/lastpage.gif' border=no alt=尾页></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<script language="javascript">
        //设置分页显示值
        document.getElementById("t_page_span").innerHTML = reportResult_getTotalPage();
        document.getElementById("c_page_span").innerHTML = reportResult_getCurrPage();
    </script>
	</body>
</html>

<%@ page contentType="text/html;charset=utf-8" %>
<%@include file="/common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>

<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>

<html>
<%@include file="/common/meta.jsp"%>
<%@include file="/common/center.jsp"%>
<!-- 
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/report/js/calendar.js"></script>
 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/report/js/show_report.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/report/js/date_operation.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>

<script>


	var _calendarMainBackColor = "#6699FF";
	var _calendarWeekColor = "#6600FF";
	var _calendarDayColor = "#FF0000";
	var _calendarDayBackColor = "#E0DFDE";
	
	
	function  tree_hide(){
	
	}
	function _hiddenCalendar(){
	
	}
</script>

<Script language="javascript">
 function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
    if (url.indexOf("?") != -1) { 
       var str = url.substr(1);
       strs = str.split("&");
       for(var i = 0; i < strs.length; i ++) { 
          theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
       }
    }
    return theRequest;
 }
 var Request = new Object();
 Request = GetRequest();
 var reportName;
 reportName = Request['reportName'];
  
 function jumpPage(){
	if(reportName.indexOf("stock")>=0){
		window.location.href = "http://123.57.131.196:8080/jumierp/";
//		window.location.href = "http://192.168.50.250:8080/jumierp/";

	}else{
		window.location.href = "<%=request.getContextPath() %>/jsp/allPage.jsp";
	}
 }
 function printorder(id){
	 if(id != null && id != '' ){
		 window.location.href = "<%=request.getContextPath() %>/jsp/report/show_report.jsp?reportName=ehomeExpress.raq&order_id="+id;
	 }else{
		 alert("请正确选择订单");
	 }
 }
 var nearValiday="reportName=stocknearvalidayRepot.raq&reportTitleName=stocknearvalidayRepot_arg.raq";
 var outValiday ="reportName=stockoutvalidayRepot.raq&reportTitleName=stockoutvalidayRepot_arg.raq"; 
 var inBackdate ="reportName=stockinbackdateRepot.raq&reportTitleName=stockinbackdateRepot_arg.raq";
 function jumpDatePage(term){
	 
		 window.location.href = "<%=request.getContextPath() %>/jsp/report/show_report.jsp?"+term;
	
 }
 </Script>

<style>
	#form1{
		margin:0px
	}
</style>
<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0>
<%@include file="/ibusiness/header/header-portal.jsp"%>
<%
	request.setCharacterEncoding( "utf-8" );
	String report = request.getParameter( "reportName" );
	String reportFileHome=Context.getInitCtx().getMainDir();
	StringBuffer param=new StringBuffer();

	session.setAttribute("user_id","111111");

	//保证报表名称的完整性
	int iTmp = 0;
	if( (iTmp = report.lastIndexOf(".raq")) <= 0 ){
		report = report + ".raq";
		iTmp = 0;
	}
	
	Enumeration paramNames = request.getParameterNames();
	if(paramNames!=null){
		while(paramNames.hasMoreElements()){
			String paramName = (String) paramNames.nextElement();
			String paramValue=request.getParameter(paramName);
			if(paramValue!=null){
				//把参数拼成name=value;name2=value2;.....的形式
				param.append(paramName).append("=").append(paramValue).append(";");
			}
		}
	}

	//以下代码是检测这个报表是否有相应的参数模板
	String paramFile = report.substring(0,iTmp)+"_arg.raq";
	File f=new File(application.getRealPath(reportFileHome+ File.separator +paramFile));

%>




<jsp:include page="toolbar.jsp" flush="false" />
<table id=rpt align=center><tr><td>
<%	//如果参数模板存在，则显示参数模板
	if( f.exists() ) {
	%>
	<table id=param_tbl >
		<tr  height="10px">
		<td >  
	
		<report:param name="form1" paramFileName="<%=paramFile%>"
			needSubmit="no" 
			params="<%=param.toString()%>" >
		
		</report:param>
		
	</td>
	<td><a href="javascript:_submit( form1 )"><img src="<%=request.getContextPath() %>/jsp/report/images/query.jpg" border=no style="vertical-align:middle"></a></td>
	<td><a href="javascript:void(0)" onclick = "jumpPage()"><img src="<%=request.getContextPath() %>/jsp/report/images/back.jpg" border=no style="vertical-align:middle"></a></td>
	</tr></table>
	<% }
%>

<table align=center>
	<tr><td>

		<report:html name="report1"
			reportFileName="<%=report%>" funcBarLocation="no"
			needPageMark="yes" functionBarColor="#fff5ee"
			funcBarFontFace="微软雅黑" funcBarFontSize="14px"
			funcBarFontColor="#17b578" separator="|" needSaveAsExcel="yes"
			needSaveAsPdf="yes" needSaveAsWord="yes" needPrint="yes"
			printLabel="打印" generateParamForm="no"
			displayNoLinkPageMark="yes" needSelectPrinter="yes"
			needScroll="no" scrollWidth="800" scrollHeight="400" width="-1"
			height="700" excelPageStyle="0" pdfExportStyle="text,0"
			exceptionPage="/jsp/report/reportError.jsp"
			params="<%=param.toString()%>" />
	</td></tr>
</table>

<script language="javascript">
	//设置分页显示值
	document.getElementById( "t_page_span" ).innerHTML=report1_getTotalPage();
	document.getElementById( "c_page_span" ).innerHTML=report1_getCurrPage();
	function show_detail(orderId){
		window.open("showReport.jsp?raq=order_detail.raq&orderid="+orderId,"_blank");
	}
</script>
</body>
</html>

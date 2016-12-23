<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'testPrint.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<embed type="application/x-java-applet;version=1.4"
			pluginspage="http://192.168.1.49:9001/XMCP/j2re-1_4_1-windows-i586-i.exe#Version=1,4,1,0"
			width="0" height="0" id="report1_printApplet"
			name="report1_printApplet"
			code="com.runqian.report4.view.applet.HtmlPrintApplet.class"
			archive="http://192.168.1.49:9001/XMCP/runqianReport4Applet.jar"
			appRoot="http://192.168.1.49:9001/XMCP"
			dataServlet="/reportServlet;jsessionid=C0714FB78B4EC0E303ADAB1183496C02?action=1"
			fileName="planRealCompare.raq" srcType="file" unitName="标题"
			savePrintSetup="no" serverPagedPrint="no" mirror="no"
			reportParamsId="100006" cachedId="A_8" wrapInchingWidth="5"
			wrapByChar="false" scriptable="true">
		</embed>


	</body>
</html>

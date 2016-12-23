<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/meta.jsp"%>
    <title>发起请假申请</title>
    <%@include file="/common/center.jsp"%>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" layout="table" action="leaveController.do?leaveStart">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 请假原因: </label></td>
			<td class="value" width="85%">
				<input id="reason" class="inputxt" name="reason" value="" datatype="s2-10" />
				<span class="Validform_checktip">用户名范围在2~10位字符</span>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
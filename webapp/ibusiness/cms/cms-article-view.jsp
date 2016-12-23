<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

<head>
<%@include file="/common/meta.jsp"%>
<title>公告列表</title>
<%@include file="/common/center.jsp"%>
<script type="text/javascript">
	
</script>
</head>

<body>
	<%@include file="/ibusiness/header/header-portal.jsp"%>

	<div class="row">
		<div class="col-lg-1"></div>

		<!-- start of main -->
		<div class="panel panel-default col-lg-10">
			<div class="panel-heading"><h4 align="center" class="panel-title">${title}</h4></div>
			<div class="panel-body">${content}</div>
			<div class="panel-body">
				<div class="form-group" align="center">
				    <div class="col-lg-2" ></div>
				    <div class="col-lg-4" >
					    <label class="control-label" >发布人:</label>
					    <label class="control-label" >${userName}</label>
				    </div>
				    <div class="col-lg-4" >
					    <label class="control-label" >发布时间:</label>
					    <fmt:formatDate value="${creatTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
			    </div>
			    <div class="col-lg-10 col-lg-offset-5">
			        <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
			    </div>
			 </div>
		</div>
		<!-- end of main -->
	</div>

</body>

</html>

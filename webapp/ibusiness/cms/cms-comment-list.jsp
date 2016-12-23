<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>评论列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'cmsCommentGrid',
    pageNo: ${page.pageNo},
    pageSize: ${page.pageSize},
    totalCount: ${page.totalCount},
    resultSize: ${page.resultSize},
    pageCount: ${page.pageCount},
    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
    asc: ${page.asc},
    params: {
        'filter_LIKES_cmsCommentname': '${param.filter_LIKES_cmsCommentname}'
    },
	selectedItemClass: 'selectedItem',
	gridFormId: 'cmsCommentGridForm',
	exportUrl: 'cms-comment-export.do'
};

var table;

$(function() {
	table = new Table(config);
    table.configPagination('.m-pagination');
    table.configPageInfo('.m-page-info');
    table.configPageSize('.m-page-size');
});
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
	    <div class="col-lg-1"></div>
		<!-- start of main -->
		<div class="panel panel-default col-lg-10">
	          <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
	          <div class="panel-body">
	       	  	<div id="cmsCommentSearch" class="content content-inner">
					  <form name="cmsCommentForm" method="post" action="cms-comment-list.do" class="form-inline">
					    <label for="cmsComment_cmsCommentname"><spring:message code='cmsComment.cmsComment.list.search.cmsCommentname' text='账号'/>:</label>
					    <input type="text" id="cmsComment_cmsCommentname" name="filter_LIKES_cmsCommentname" value="${param.filter_LIKES_cmsCommentname}">
					    <label for="cmsComment_enabled"><spring:message code='cmsComment.cmsComment.list.search.status' text='状态'/>:</label>
					    <select id="cmsComment_enabled" name="filter_EQI_status" class="input-mini">
						  <option value=""></option>
						  <option value="1" ${param.filter_EQI_status == 1 ? 'selected' : ''}><spring:message code='cmsComment.cmsComment.list.search.enabled.true' text='启用'/></option>
						  <option value="0" ${param.filter_EQI_status == 0 ? 'selected' : ''}><spring:message code='cmsComment.cmsComment.list.search.enabled.false' text='禁用'/></option>
					    </select>
						<button class="btn btn-small" onclick="document.cmsCommentForm.submit()">查询</button>
					  </form>
			    </div>
		  </div>
	      <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">文章列表</h4></div>
	          <div class="panel-body">
				    <div class="pull-left">
				        <p>
						    <button class="btn btn-primary btn-sm a-insert" onclick="location.href='cms-comment-input.do'">新建</button>
						    <button class="btn btn-primary btn-sm a-remove" onclick="table.removeAll()">删除</button>
					    </p>
					</div>
					<div class="pull-right">
					  每页显示
					  <select class="m-page-size">
					    <option value="5">5</option>
					    <option value="10">10</option>
					    <option value="50">50</option>
					    <option value="100">100</option>
					  </select>
					  条
					</div>
					<div class="m-clear"></div>
		    </div>
		    <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">评论列表</h4></div>
	        <div class="panel-body">
		          <div class="content">
						<form id="cmsCommentGridForm" name="cmsCommentGridForm" method='post' action="cms-comment-remove.do" class="m-form-blank">
						  <table id="cmsCommentGrid" class="table table-hover table-striped">
						    <thead>
						      <tr>
						        <th width="30" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
						        <th class="sorting" ><spring:message code="cmsComment.cmsComment.list.id" text="编号"/></th>
						        <th class="sorting" >标题</th>
						        <th class="sorting" >内容</th>
						        <th width="30">&nbsp;</th>
						      </tr>
						    </thead>
						
						    <tbody>
						      <c:forEach items="${page.result}" var="item">
						      <tr>
						        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						        <td>${item.id}</td>
						        <td>${item.title}</td>
						        <td>${item.content}</td>
						        <td>
						          <a href="cms-comment-input.do?id=${item.id}" class="a-update"><span class="glyphicon glyphicon-pencil"></span></a>
						        </td>
						      </tr>
						      </c:forEach>
						    </tbody>
						  </table>
						</form>
	        </div>
	      </div>
		  <article>
		    <div class="m-page-info pull-left">
			  共100条记录 显示1到10条记录
			</div>
			<div class="btn-group m-pagination pull-right">
			  <button class="btn btn-small">&lt;</button>
			  <button class="btn btn-small">1</button>
			  <button class="btn btn-small">&gt;</button>
			</div>
		    <div class="m-clear"></div>
	      </article>
	    </div>
	<!-- end of main -->
	</div>

  </body>

</html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>文章列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'cmsArticleGrid',
    pageNo: ${page.pageNo},
    pageSize: ${page.pageSize},
    totalCount: ${page.totalCount},
    resultSize: ${page.resultSize},
    pageCount: ${page.pageCount},
    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
    asc: ${page.asc},
    params: {
        'filter_LIKES_title': '${param.filter_LIKES_title}'
    },
	selectedItemClass: 'selectedItem',
	gridFormId: 'cmsArticleGridForm',
	exportUrl: 'cms-article-export.do'
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
		        <div id="cmsArticleSearch" class="content content-inner">
					  <form name="cmsArticleForm" method="post" action="cms-article-view-list.do" class="form-inline">
						    <label for="cmsArticle_titlee"><spring:message code='cmsArticle.cmsArticle.list.search.title' text='标题'/>:</label>
						    <input type="text" id="cmsArticle_title" name="filter_LIKES_title" value="${param.filter_LIKES_title}">
							<button class="btn btn-primary btn-small" onclick="document.cmsArticleForm.submit()">查询</button>
					  </form>
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
				</div>
			</div>
		    <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">列表</h4></div>
	        <div class="panel-body">
		          <div class="content">
						<form id="cmsArticleGridForm" name="cmsArticleGridForm" method='post' action="cms-article-remove.do" class="m-form-blank">
						  <table id="cmsArticleGrid" class="table table-hover table-striped">
						    <thead>
						      <tr>
						          <th class="sorting col-lg-2" >发布日期</th>
						          <th class="sorting col-lg-2" >栏目</th>
						          <th class="sorting col-lg-8" >标题</th>
						      </tr>
						    </thead>
						
						    <tbody>
						      <c:forEach items="${page.result}" var="item">
						      <tr>
						          <td >${item.createTime}</td>
						          <td >${item.cmsCatalog.name}</td>
						          <td><a href="cms-article-view.do?id=${item.id}">${item.title}</a></td>
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

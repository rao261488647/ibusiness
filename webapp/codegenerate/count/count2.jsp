<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>所有司机数据分析</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>
    <div class="row">
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
	<!-- start of main -->
	<div class="panel panel-default col-lg-10 col-md-10 col-sm-10">
	<!-- 查询条件 -->
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">查询</h4></div>
          <div class="panel-body">
	          <div id="search" class="content content-inner">
				  <form name="cgForm" method="post" action="count2.do" class="form-inline">
				<div class="form-group col-lg-12 col-md-12 col-sm-12">
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
					    <label class="col-lg-4 col-md-4 col-sm-4 control-label" >时间:</label>
					    <div class="col-lg-8 col-md-8 col-sm-8">  <div class="input-append datepicker date">  <span class="add-on">    <input id="code-eventdate" type="text" name="filter_GED_date" value="${param.filter_GED_date}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div>
					    </div>
		            </div>
		                                        <div class="col-md-4">
                                <label class="">车型：
                                <select id="code-typename" name="filter_LIKES_typename" class="form-control input-sm required" >          <option value="" >请选择</option>        <c:forEach items="${typenameItems}" var="item">          <option value="${item.key}" ${item.key==param.filter_LIKES_typename? 'selected':''} >${item.value}</option>        </c:forEach>    </select>
								</label>
                            </div>
	           </div>
	            <div class="form-group col-lg-12 col-md-12 col-sm-12">
			    <button class="col-lg-1 col-md-1 col-sm-1 btn btn-primary btn-sm" onclick="document.cgForm.submit()"><span class="glyphicon glyphicon-search"></span>查询</button>
				</div>
				 </form>
				 <button class="btn btn-success btn-dialog" id="exportBtn">导出记录</button>
			  </div>
		  </div>
	   <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">所有司机数据分析</h4></div>
	   <div class="content">
<table class="table table-bordered" style="width:60%">
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
		</div>
	  </div>
	  <!-- 模态框 -->
	  <div id="modalInput" class="modal fade" tabindex="-1" style="display: none;" data-backdrop="static">
		  <div class="modal-dialog modal-lg">
			  <div class="modal-content" style="text-align: center;height: 600px">
			  </div>
		  </div>
	  </div>
	<!-- end of main -->
	</div>
  </body>
        <script type="text/javascript">
	// 导出数据
	$('#exportBtn').click(function(){
		var $form = $("#cgForm");
		var data = $form.serializeArray();
		var url = '${ctx}/count/exportcount2.do?filter_GED_date=' + $('#code-eventdate').val()+'&filter_LIKES_typename='+encodeURI(encodeURI($('#code-typename').val()));
		window.open(url);
	});
    </script>
</html>

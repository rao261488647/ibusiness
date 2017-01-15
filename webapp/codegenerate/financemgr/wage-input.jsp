<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>工资方案编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <script type="text/javascript">
		$(function() {
		    $("#cgForm").validate({
		        submitHandler: function(form) {
		            if (typeof(bootbox) != 'undefined') {
					    bootbox.animate(false);
					    var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
					}
					form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		})
    </script>
    <div class="col-lg-1 col-md-1 col-sm-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-12 col-md-12 col-sm-12"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">工资方案编辑</h4></div>
        <div class="panel-body">
                <form id="cgForm" method="post" action="wage-save.do" class="form-horizontal">
                  <c:if test="${model != null}">
                      <input id="code_id" type="hidden" name="id" value="${model.id}">
                  </c:if>
                  
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-name">姓名:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-name" type="text" name="name" value="${model.name}" class="text form-control input-sm required" readonly >   <a href="#" class="btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('show');" >选择</a>   <script type="text/javascript">   	function changenameValue(name,carname,carnum,contractvalidday){   	$("#code-name").val(name);   	$("#code-carname").val(carname);   	$("#code-carnum").val(carnum);   	$("#code-getcardate").val(contractvalidday);$('#nameSInputDiv').modal('hide');        }function searchOwnernamename(urlStr) {  $.ajax({  	 type: "POST", 	 url: "/"+window.location.pathname.split("/")[1]+"/"+urlStr, 	 dataType:"json",     data:"filter_LIKES_name="+$("#code_table_name").val(), 	 success: function(jsonData){ 	   $("#nameRowadd tr").remove();  	   $.each(jsonData, function(i, item) { 		   var newRow='            	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue(\''+item.name+'\',\''+item.carname+'\',\''+item.carnum+'\',\''+item.contractvalidday+'\')" >选择</a></td>  <td>'+item.name+'</td>  <td>'+item.carname+'</td>  <td>'+item.carnum+'</td>  <td>'+item.contractvalidday+'</td>           	     </tr>';          $('#nameRowadd').append(newRow);         });     }, 	 error: function (XMLHttpRequest, textStatus, errorThrown) { 		 alert('请求数据出错了!');     }   }); }    </script>   <div id="nameSInputDiv" class="modal fade" tabindex="-1" style="top:20%;" >     <div class="modal-dialog">       <div class="modal-content">         <div class="modal-header">           <a href="#" class="close btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('hide');" ><span >&times;</span><span class="sr-only">Close</span></a>           <h4 class="modal-title glyphicon glyphicon-paperclip">选择带出</h4>           <div class="form-group">              <label for="code_table_name">司机姓名:</label>              <input type="text" id="code_table_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}">              <a class="btn btn-primary btn-sm" href="#" onclick="searchOwnernamename('wage/name-list.do')"><span class="glyphicon glyphicon-search"></span>查询</a>            </div>          </div>         <div class="modal-body">           <div class="content">           	<table id="codeGrid" class="table table-hover table-bordered">           	   <thead>           	   <tr>           	      <th width="80">&nbsp;</th>   <th class="sorting">司机姓名</th>   <th class="sorting">车型名称</th>   <th class="sorting">车牌号码</th>   <th class="sorting">合同生效日</th>           	   </tr>           	</thead>           	<tbody id='nameRowadd'>           	   <c:forEach items="${namePage.result}" var="item">           	     <tr>           	        <td><a href="#" class="btn btn-primary btn-sm" onClick="changenameValue('${item.name}','${item.carname}','${item.carnum}','${item.contractvalidday}')" >选择</a></td>  <td>${item.name}</td>  <td>${item.carname}</td>  <td>${item.carnum}</td>  <td>${item.contractvalidday}</td>           	     </tr>           	  </c:forEach>           	</tbody>           </table>         </div>       </div>       <div class="modal-footer">         <a href="#" class="btn btn-primary btn-sm" onclick="$('#nameSInputDiv').modal('hide');" >关闭</a>       </div>     </div>    </div>  </div></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-eventdate">工资年月:</label>
                          <script type="text/javascript">$(".form_datetime").datepicker({format: 'yyyy-mm'});</script><div class="col-lg-3 col-md-3 col-sm-3">  <div class="input-append datepicker_yyyymm date">  <span class="add-on">    <input id="code-eventdate" type="text" name="eventdate" value="${model.eventdate}" placeholder="点击选择" class="form-control input-sm required" readonly >  </span>  </div></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-wagescheme">工资方案:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-wagescheme" type="text" name="wagescheme" value="${model.wagescheme}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carname">车型:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carname" type="text" name="carname" value="${model.carname}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-carnum">车牌号码:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-carnum" type="text" name="carnum" value="${model.carnum}" class="text form-control input-sm " readonly ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-getcardate">提车日期:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-getcardate" type="text" name="getcardate" value="${model.getcardate}" class="text form-control input-sm " readonly ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-getcardays">提车天数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-getcardays" type="text" name="getcardays" value="${model.getcardays}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-initialkm">月初公里数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-initialkm" type="text" name="initialkm" value="${model.initialkm}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-endmonthkm">月末公里数:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-endmonthkm" type="text" name="endmonthkm" value="${model.endmonthkm}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-turnover1">当月第一周营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-turnover1" type="text" name="turnover1" value="${model.turnover1}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-expenses1">当月第一周路费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-expenses1" type="text" name="expenses1" value="${model.expenses1}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-turnover2">当月第2周营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-turnover2" type="text" name="turnover2" value="${model.turnover2}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-expenses2">当月第2周路费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-expenses2" type="text" name="expenses2" value="${model.expenses2}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-turnover3">当月第3周营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-turnover3" type="text" name="turnover3" value="${model.turnover3}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-expenses3">当月第3周路费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-expenses3" type="text" name="expenses3" value="${model.expenses3}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-turnover4">当月第4周营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-turnover4" type="text" name="turnover4" value="${model.turnover4}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-expenses4">当月第4周路费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-expenses4" type="text" name="expenses4" value="${model.expenses4}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-turnover5">当月第5周营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-turnover5" type="text" name="turnover5" value="${model.turnover5}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-expenses5">当月第5周路费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-expenses5" type="text" name="expenses5" value="${model.expenses5}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-airportamount">机场单金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-airportamount" type="text" name="airportamount" value="${model.airportamount}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-totalturnover">总营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-totalturnover" type="text" name="totalturnover" value="${model.totalturnover}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-turnoverscore">营业额评分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-turnoverscore" type="text" name="turnoverscore" value="${model.turnoverscore}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-servicescore">服务评分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-servicescore" type="text" name="servicescore" value="${model.servicescore}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-safetyscore">安全评分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-safetyscore" type="text" name="safetyscore" value="${model.safetyscore}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-coordscore">配合度评分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-coordscore" type="text" name="coordscore" value="${model.coordscore}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-referralscore">转介绍评分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-referralscore" type="text" name="referralscore" value="${model.referralscore}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-excessscore">超额业绩评分:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-excessscore" type="text" name="excessscore" value="${model.excessscore}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-totalscore">评分合计:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-totalscore" type="text" name="totalscore" value="${model.totalscore}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ddturnover">DD总营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-ddturnover" type="text" name="ddturnover" value="${model.ddturnover}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-uberturnover">UBer总实际营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-uberturnover" type="text" name="uberturnover" value="${model.uberturnover}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-qnwturnover">去哪儿网总营业额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-qnwturnover" type="text" name="qnwturnover" value="${model.qnwturnover}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-monthexpenses">月总路费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-monthexpenses" type="text" name="monthexpenses" value="${model.monthexpenses}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-qnwsubsidyamount">去哪儿网爽约补贴金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-qnwsubsidyamount" type="text" name="qnwsubsidyamount" value="${model.qnwsubsidyamount}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-shouldpaid">应发工资:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-shouldpaid" type="text" name="shouldpaid" value="${model.shouldpaid}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ltcard">联通流量卡套餐费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-ltcard" type="text" name="ltcard" value="${model.ltcard}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-utilitiescosts">司机住宿和水电费用:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-utilitiescosts" type="text" name="utilitiescosts" value="${model.utilitiescosts}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-bydelectriccard">BYD电卡金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-bydelectriccard" type="text" name="bydelectriccard" value="${model.bydelectriccard}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-ttelectriccard">通途电卡金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-ttelectriccard" type="text" name="ttelectriccard" value="${model.ttelectriccard}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-smhcelectriccard">水木华程电卡金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-smhcelectriccard" type="text" name="smhcelectriccard" value="${model.smhcelectriccard}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-insurancefee">出险扣款:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-insurancefee" type="text" name="insurancefee" value="${model.insurancefee}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-monthsocial">月社保扣款:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-monthsocial" type="text" name="monthsocial" value="${model.monthsocial}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-monthloan">月借款金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-monthloan" type="text" name="monthloan" value="${model.monthloan}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-illegalfee">违章扣款:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-illegalfee" type="text" name="illegalfee" value="${model.illegalfee}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-qnwfine">去哪儿网罚款金额:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-qnwfine" type="text" name="qnwfine" value="${model.qnwfine}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-otherexpenses">其他费用:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-otherexpenses" type="text" name="otherexpenses" value="${model.otherexpenses}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-deductedele">应扣电费:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-deductedele" type="text" name="deductedele" value="${model.deductedele}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-totaldeducted">应扣费用合计:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-totaldeducted" type="text" name="totaldeducted" value="${model.totaldeducted}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-elesubsidies">电费补贴:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-elesubsidies" type="text" name="elesubsidies" value="${model.elesubsidies}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-realwage">实发工资:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-realwage" type="text" name="realwage" value="${model.realwage}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-bankaccount">银行账号:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-bankaccount" type="text" name="bankaccount" value="${model.bankaccount}" class="text form-control input-sm "  ></div>
                          
                            </div>
                          <div class="form-group">
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-bank">开户银行及所在支行:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-bank" type="text" name="bank" value="${model.bank}" class="text form-control input-sm "  ></div>
                          
                          <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="code-remark">备注:</label>
                          <div class="col-lg-3 col-md-3 col-sm-3">   <input id="code-remark" type="text" name="remark" value="${model.remark}" class="text form-control input-sm "  ></div>
                          
                            </div>
                  
                  <div class="form-group">
	                  <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-2">
	                      <button id="submitButton" class="btn btn-primary btn-sm a-submit"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
	                      <button type="button" onclick="location.href='wage-list.do'" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-out"></span> 关闭</button>
	                  </div>
                  </div>
                </form>
        </div>
    </div>
    <!-- end of main -->
  </body>
</html>

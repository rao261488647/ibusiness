<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "serviceModule");%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑表单组件</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
	    // 设置值
		function defailtChange(str){
			$("#form-fcDefault").val($("#form-fcDefault").val() + str);
		}
	    
	    // ===================================== 数据字典 ====================================================
	    // 保存下来数据字典选择信息
	    function saveDataSelect() {
			$('#form-confSelectInfo').attr('value', "{\"sql\":\"select "+$("#tablecolumnKeyAdd").val()+" vKey, "+$("#tablecolumnNameAdd").attr('value')+" vValue from "+$("#tablelistAdd").val()+" \"}");
			$('#dataSelectDiv').modal('hide');
		}
	    // 查询表名列表
		function selectTableList() {
			$.ajax({type : "POST",
					url : "/"+ window.location.pathname.split("/")[1]+ "/"+ "form/ajax-tablelist.do",
					dataType : "json",
					success : function(jsonData) {
						// 清空
						$("#tablelistAdd option").remove();
						var newRow = '<option value="">请选择.....</option>';
						$('#tablelistAdd').append(newRow);
						$.each(jsonData, function(i, item) {
							newRow = '<option value="'+ item.tableName +'">' + item.tableNameComment + '</option>';
							$('#tablelistAdd').append(newRow);
						});
						// 
						$('#dataSelectDiv').modal('show');
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert('请求数据出错了!');
					}
			});
		}
		// 根据表名查询 字段列表
	    function selectTableColumn(tablename) {
	    	$.ajax({type : "POST",
				url : "/"+ window.location.pathname.split("/")[1]+ "/"+ "form/ajax-tablecolumnlist.do?tablename="+tablename,
				dataType : "json",
				success : function(jsonData) {
					// 清空key值
					$("#tablecolumnKeyAdd option").remove();
					$.each(jsonData, function(i, item) {
							var newRow = '<option value="'+ item.columnValue +'">' + item.columnName + '</option>';
							$('#tablecolumnKeyAdd').append(newRow);
					});
					// 清空value值
					$("#tablecolumnNameAdd option").remove();
					$.each(jsonData, function(i, item) {
							var newRow = '<option value="'+ item.columnValue +'">' + item.columnName + '</option>';
							$('#tablecolumnNameAdd').append(newRow);
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('请求数据出错了!');
				}
			});
	    }
	    // ===================================== 选择带出 ====================================================
	    function editSelectInput() {
	    	$.ajax({type : "POST",
				url : "/"+ window.location.pathname.split("/")[1]+ "/"+ "form/ajax-tablelist.do",
				dataType : "json",
				success : function(jsonData) {
					// 清空
					$("#selectInputTablelist option").remove();
					var newRow = '<option value="">请选择.....</option>';
					$('#selectInputTablelist').append(newRow);
					$.each(jsonData, function(i, item) {
						newRow = '<option value="'+ item.tableName +'">' + item.tableNameComment + '</option>';
						$('#selectInputTablelist').append(newRow);
					});
					// 显示
					$('#selectInputDiv').modal('show');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('请求数据出错了!');
				}
		    });
	    }
	    // =============================================
    	// 选择带出   根据表名查询 字段列表
    	var columnTableRow=''; 
	    function selectInputTableColumn(tablename) {
	    	$.ajax({type : "POST",
				url : "/"+ window.location.pathname.split("/")[1]+ "/"+ "form/ajax-tablecolumnlist.do?tablename="+tablename,
				dataType : "json",
				success : function(jsonData) {
					// 清空key值
					$('select[name="querytablecolumnname"] option').remove();
					columnTableRow = "";
					// 改变表名重新设置对应字段
					$.each(jsonData, function(i, item) {
						columnTableRow = columnTableRow + '<option value="'+ item.columnValue +'">' + item.columnName + '</option>';
					});
					$('select[name="querytablecolumnname"]').append(columnTableRow);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('请求数据出错了!');
				}
			});
	    }
	    // 添加一个字段
	    function addSelectInput() {
	    	var newRow = '<div class="col-lg-12">';
			newRow = newRow + '<div class="col-lg-4">';
			newRow = newRow + $("#tableColumnsStrId").val();
			newRow = newRow + '</div>';
			// 
			newRow = newRow + '<div class="col-lg-4">';
	    	newRow = newRow + '<select name="tablecolumnname" class="form-control input-sm required" >';
            newRow = newRow + columnTableRow;
			newRow = newRow + '</select>';
			newRow = newRow + '</div>';
			newRow = newRow + '</div>';
	    	$('#selsctedColumnNames').append(newRow);
	    }
	    // 选择带出确定按钮
	    function saveSelectInput() {
	        var str = "";
	        // 目标属性名
	        var inputKey=[];
	        $('select[name="tablecolumninputKey"]').each(function() {
	        	inputKey.push($(this).val());
	        });
	        // 查询属性名
	        var columnname=[];
	        // 查询标题名称
	        var titlename=[];
	        $('select[name="tablecolumnname"]').each(function() {
	        	columnname.push($(this).val());
	        	titlename.push($(this ).find("option:selected").text());
	        });
	        // 
	        // 选择带出值
	        str = "{\"jsplist\":[";
		        for(var i=0;i<$('select[name="tablecolumninputKey"]').size();i++){
		        	if( i > 0 ) {
		        		str = str + ",";
		        	}
		        	str = str + "{\"inputKey\":\""+inputKey[i]+"\",\"inputValue\":\""+columnname[i]+"\",\"inputTitle\":\""+titlename[i]+"\"}";
		        }
		    // className:service名及全路径 queryTitle:查询标题名 queryName:查询对象
            var queryTitle = $('#findcolumn option:selected').text();
		    var queryName = $('#findcolumn option:selected').val();
            str = str + "],\"className\":\""+$("#selectInputTablelist").val()+"\",\"queryTitle\":\""+queryTitle+"\",\"queryName\":\""+queryName+"\"}";
	        // 
	        $('#form-confSelectInfo').attr('value', str);
			// 确定按钮隐藏弹出页面
			$('#selectInputDiv').modal('hide');
		}
    </script>
  </head>
  <body>
    <%@include file="/ibusiness/component/manage/header-manage.jsp"%>
    
    <div class="col-lg-1"></div>
    <!-- start of main -->
    <div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑表单组件</h4></div>
        <div class="panel-body">
                <form id="formLabelForm" method="post" action="conf-formLabel-save.do" class="form-horizontal">
                    <c:if test="${model != null}">
                        <input type="hidden" name="formName" value="${model.formName}">
                        <input type="hidden" name="formColumn" value="${model.formColumn}">
                        <input type="hidden" name="packageName" value="${model.packageName}">
                        <input type="hidden" name="tableName" value="${model.tableName}">
                        <input id="tableColumnId" type="hidden" name="tableColumn" value="${model.tableColumn}">
                    </c:if>
                      <!-- java传递 -->
                      <input id="tableColumnsStrId" type="hidden" value="${tableColumnsStr}">
                      
                      <div class="form-group">
                          <label class="control-label col-lg-2" for="form-column">字段:</label>
                          <div class="col-lg-3">
                              <label id="form-column" >${model.formColumn}</label>
                          </div>
                          <label class="control-label col-lg-2" for="form-column-title">显示标题:</label>
                          <div class="col-lg-3">
                              <input id="form-column-title" type="text" name="formColumnTitle" value="${model.formColumnTitle}"  class="text required" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="control-label col-lg-2" for="form-fcType">组件类型:</label>
                          <div class="col-lg-2">
                          <script type="text/javascript">
								function setConfSelectInfo(str) {
										if ("6" == str) {
											$('#form-confSelectInfo').attr('value', "[{\"key\":\"1\",\"value\":\"男\"},{\"key\":\"2\",\"value\":\"女\"}]");
										} else if ("7" == str) {
											// 下拉数据字典
											selectTableList();
										} else if ("10" == str) {
											// 选择带出
											editSelectInput();
										} else if ("11" == str) {
											$('#form-confSelectInfo').attr('value', "{\"pathName\":\"ibfile\"}");
										} else if ("13" == str) {
											$('#form-confSelectInfo').attr('value', "{\"pathName\":\"ibimg\"}");
										} else {
											$('#form-confSelectInfo').attr('value', "");
										}
								//		if ("1" == str || "2" == str || "3" == str || "4" == str || "5" == str || "8" == str || "9" == str) {
								//			$('#form-confSelectInfo').attr('value', "");
								//		}
								}
							</script>
                              <select id="form-fcType" name="fcType"  class="form-control" onChange="setConfSelectInfo(this.value);" >
                                    <option value="1" ${model.fcType==1 ? 'selected' : ''}>单行</option>
                                    <option value="2" ${model.fcType==2 ? 'selected' : ''}>多行</option>
                                    <option value="3" ${model.fcType==3 ? 'selected' : ''}>日期</option>
                                    <option value="14" ${model.fcType==14 ? 'selected' : ''}>日期年月</option>
                                    <option value="4" ${model.fcType==4 ? 'selected' : ''}>时间</option>
                                    <option value="5" ${model.fcType==5 ? 'selected' : ''}>数值</option>
                                    <option value="6" ${model.fcType==6 ? 'selected' : ''}>下拉列表</option>
                                    <option value="7" ${model.fcType==7 ? 'selected' : ''}>下拉数据字典</option>
                                    <option value="8" ${model.fcType==8 ? 'selected' : ''}>单选按钮</option>
                                    <option value="9" ${model.fcType==9 ? 'selected' : ''}>多选按钮</option>
                                    <option value="10" ${model.fcType==10 ? 'selected' : ''}>选择带出</option>
                                    <option value="11" ${model.fcType==11 ? 'selected' : ''}>附件上传组件</option>
                                    <option value="13" ${model.fcType==13 ? 'selected' : ''}>图片组件</option>
                               </select>
                           </div>
                      </div>
                      <div class="form-group">
                          <div class="col-lg-1"></div>
                          <div class="col-lg-11">
                          <label class="control-label" for="form-confSelectInfo">
                          
                          <p class="text-left">
              *组件类型:选择组件类型后如果下面框中内容为空,会自动在框中提示参数写法,如果已有内容则不提示。<br/>
                  1.输入范围Check：{"maxlength":"30","minlength":"2"}<br/>
                  2.设置下拉列表固定值：[{"key":"1","value":"男"},{"key":"2","value":"女"}]<br/>
                  3.单附件上传组件：{"pathName":"存储文件夹名"}<br/>
                          </p>
                     
                          </label>
                          </div>
                          <div class="col-lg-1"></div>
                          <div class="col-lg-9">
                              <textarea id="form-confSelectInfo" class="form-control" rows="4" name="confSelectInfo"  >${model.confSelectInfo}</textarea>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="control-label col-lg-2" for="form-fcWidth">录入宽度:</label>
                          <div class="col-lg-3">
                              <input id="form-fcWidth" type="text" name="fcWidth" value="${model.fcWidth}"  class="text required" >
                          </div>
                          <label class="control-label col-lg-2" for="form-fcHeight">录入高度:</label>
                          <div class="col-lg-3">
                              <input id="form-fcHeight" type="text" name="fcHeight" value="${model.fcHeight}"  class="text required" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 control-label " for="form-fcDisplay">是否显示:</label>
                          <div class="col-lg-2">
                              <select id="form-fcDisplay" name="fcDisplay"  class="form-control">
                                    <option value="1" ${model.fcDisplay==1 ? 'selected' : ''}>是</option>
                                    <option value="2" ${model.fcDisplay==2 ? 'selected' : ''}>否</option>
                               </select>
                           </div>
                          <label class="col-lg-2 control-label" for="form-fcEdit">是否编辑:</label>
                          <div class="col-lg-2">
                              <select id="form-fcEdit" name="fcEdit"  class="form-control">
                                    <option value="1" ${model.fcEdit==1 ? 'selected' : ''}>是</option>
                                    <option value="2" ${model.fcEdit==2 ? 'selected' : ''}>否</option>
                               </select>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-lg-2 control-label" for="form-fcEdit">是否查询字段:</label>
                          <div class="col-lg-2">
                              <select id="form-fcQuery" name="fcQuery"  class="form-control">
                                    <option value="1" ${model.fcQuery==1 ? 'selected' : ''}>是</option>
                                    <option value="2" ${model.fcQuery==2 ? 'selected' : ''}>否</option>
                               </select>
                          </div>
                          <label class="col-lg-2 control-label " for="form-fcMust">必须输入:</label>
                          <div class="col-lg-2">
                              <select id="form-fcMust" name="fcMust"  class="form-control">
                                    <option value="1" ${model.fcMust==1 ? 'selected' : ''}>是</option>
                                    <option value="2" ${model.fcMust==2 ? 'selected' : ''}>否</option>
                               </select>
                           </div>
                      </div>
                      <div class="form-group">
                          <label class="control-label col-lg-2" for="form-fcDefault">默认值:</label>
                          <div class="input-group col-lg-4">
						      <input id="form-fcDefault" type="text" name="fcDefault" value="${model.fcDefault}" class="form-control">
						      <div class="input-group-btn">
						        <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown">公式<span class="caret"></span></button>
						        <ul class="dropdown-menu dropdown-menu-right">
						          <li><a href="#" onClick="defailtChange('@currentDate')" >当前日期时间</a></li>
						          <li><a href="#" onClick="defailtChange('@currentDateToStr')" >当前日期时间字符串</a></li>
						          <li><a href="#" onClick="defailtChange('@userName')" >当前用户</a></li>
						          <li class="divider"></li>
						          <li><a href="#" onClick="defailtChange('@autoId')" >自增长ID</a></li>
						        </ul>
						      </div>
						  </div>
                      </div>
                      <div class="form-group">
                          <label class="control-label col-lg-2" for="form-columnNo">排序:</label>
                          <div class="col-lg-3">
                              <input id="form-fcWidth" type="text" name="columnNo" value="${model.columnNo}"  class="text required" >
                          </div>
                      </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button id="submitButton" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
                      <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
                    </div>
                  </div>
                </form>
                
        </div>
      </div>
           <!-- 选择带出__弹出modal -->
           <div id="selectInputDiv" class="modal fade" tabindex="-1" style="top: 20%;">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<div>
							    <a href="#" class="btn btn-primary btn-sm" onclick="addSelectInput()">添加</a>
							    <a href="#" class="btn btn-primary btn-sm" onclick="saveSelectInput()">确定</a>
								<a href="#" class="btn btn-primary btn-sm" onclick="$('#selectInputDiv').modal('hide');">关闭</a>
							    <a href="#" class="close btn btn-primary btn-sm" onclick="$('#selectInputDiv').modal('hide');"><span>&times;</span><span class="sr-only">Close</span></a>
						    </div>
						</div>
						<div class="modal-body">
							<div class="col-lg-6">
							    <label>${model.tableName}:</label>
							</div>
							<div class="col-lg-6">
							    <label class="col-lg-4" >表名：</label>
							    <div class="col-lg-8">
							        <select id="selectInputTablelist" name="tablename"  class="form-control input-sm required" onChange="selectInputTableColumn(this.value);" >
	                                </select>
							    </div>
							</div>
							<div class="col-lg-5">
							</div>
							<div class="col-lg-7 form-group">
						        <label class="col-lg-5" >查询字段:</label>
							    <div class="col-lg-7">
	                                <select name="querytablecolumnname" id="findcolumn" class="col-lg-8 form-control input-sm required" >
	                                </select>
	                            </div>
							</div>
							<div class="col-lg-12" id="selsctedColumnNames" >
							    
							</div>
						</div>
						<div class="modal-footer">
						</div>
					</div>
				</div>
			</div>
		
		   <!-- 下拉数据字典__弹出modal -->
           <div id="dataSelectDiv" class="modal fade" tabindex="-1" style="top: 20%;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
					    <div>
						    <a href="#" class="btn btn-primary btn-sm" onclick="saveDataSelect()">确定</a>
							<a href="#" class="btn btn-primary btn-sm" onclick="$('#dataSelectDiv').modal('hide');">关闭</a>
						</div>
					</div>
					<div class="modal-body">
						<div class="col-lg-4">
						  <label>表名:</label>
						  <select id="tablelistAdd" name="tablename"  class="form-control input-sm required" onChange="selectTableColumn(this.value);" >
                          </select>
						</div>
						<div class="col-lg-4">
                          <label>key字段:</label>
						  <select id="tablecolumnKeyAdd" name="tablecolumnKey"  class="form-control input-sm required" >
                          </select>
						</div>
						<div class="col-lg-4">
                          <label>显示字段:</label>
						  <select id="tablecolumnNameAdd" name="tablecolumnname"  class="form-control input-sm required" >
                          </select>
						</div>
					</div>
					<div class="modal-footer">
					</div>
				</div>
			</div>
		</div>
        <!-- end of main -->
        <script type="text/javascript">
            // 
            
        </script>
  </body>
</html>

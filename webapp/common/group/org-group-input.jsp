<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "group");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑小组</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#orgForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress" ><div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"> 60% </div></div>');
			form.submit();
        },
        errorClass: 'validate-error',
        rules: {
            orgname: {
                remote: {
                    url: 'org-group-checkOrgname.do',
                    data: {
                        <c:if test="${model != null}">
                        id: function() {
                            return $('#org_id').val();
                        }
                        </c:if>
                    }
                }
            }
        },
        messages: {
            orgname: {
                remote: "存在重复小组"
            }
        }
    });
})
    </script>
  </head>

  <body>
    <%@include file="/ibusiness/header/header-portal.jsp"%>

    <div class="col-lg-1"></div>

	<!-- start of main -->
	<div class="panel panel-default col-lg-10"> 
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑小组</h4></div>
        <div class="panel-body">
        
        <form id="orgForm" method="post" action="org-group-save.do" class="form-horizontal">
		  <c:if test="${model != null}">
		  <input id="org_id" type="hidden" name="id" value="${model.id}">
		  </c:if>
	      <div class="form-group">
              <label class="col-lg-2 control-label" for="org_orgname"><spring:message code="org.org.input.orgname" text="小组名称"/>:</label>
			  <div class="col-lg-3">
			      <input id="org_orgname" type="text" name="name" value="${model.name}"  class="text required"  maxlength="50">
			  </div>
	       </div>
	       <div class="form-group">
                    <label class="col-lg-2 control-label" for="org_companyname">公司名:</label>
                     <div class="col-lg-3">
                         <select id="org_companyname" name="companyid" class="form-control required" >
                               <option value="" >请选择</option>
                            <c:forEach items="${companyidItems}" var="item">
                               <option value="${item.key}" ${item.key==model.companyid? 'selected':''} >${item.value}</option>
                            </c:forEach>
                         </select>
                     </div>
          </div>
          <div class="form-group">
                    <label class="col-lg-2 control-label" for="org_deptid"><spring:message code="org.org.input.orgname" text="部门编号"/>:</label>
                    <div class="col-lg-3">
                         <select id="org_deptid" name="deptid" class="form-control required" >
                               <option value="" >请选择</option>
                            <c:forEach items="${deptidItems}" var="item">
                               <option value="${item.key}" ${item.key==model.deptid? 'selected':''} >${item.value}</option>
                            </c:forEach>
                         </select>
                     </div>
          </div>
			  
		  <div class="form-group">
                  <div class="col-lg-10 col-lg-offset-2">
				      <button id="submitButton" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn btn-primary"><spring:message code='core.input.back' text='返回'/></button>
		          </div>
		  </div>
		</form>
        </div>
    </div>
	<!-- end of main -->
  </body>

</html>

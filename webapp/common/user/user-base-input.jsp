<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="user.user.input.title" text="编辑用户"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
        $(function() {
            $("#userForm").validate({
                submitHandler: function(form) {
                    bootbox.animate(false);
                    var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
                    form.submit();
                },
                errorClass: 'validate-error',
                rules: {
                    username: {
                        remote: {
                            url: 'user-base-checkUsername.do',
                            data: {
                                <c:if test="${model != null}">
                                id: function() {
                                    return $('#user-base_id').val();
                                }
                                </c:if>
                            }
                        }
                    }
                },
                messages: {
                    username: {
                        remote: "<spring:message code='user.user.input.duplicate' text='存在重复账号'/>"
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑用户</h4></div>
        <div class="panel-body">
        
        <form id="userForm" method="post" action="user-base-save.do?operationMode=STORE" class="form-horizontal">
              <c:if test="${model != null}">
                  <input id="user-base_id" type="hidden" name="id" value="${model.id}">
                  <input id="user-base_userRepoId" type="hidden" name="userRepoId" value="${model.userRepoId}">
              </c:if>
              <c:if test="${model == null}">
                  <input id="user-base_userRepoId" type="hidden" name="userRepoId" value="1">
              </c:if>
              <!--  -->
              <div class="form-group">
                <label class="col-lg-2 control-label" for="user-base_username">账号:</label>
                <input id="user-base_username" type="text" name="username" value="${model.username}"  class="text" >
               </div>
               <c:if test="${model == null || model.password == null}">
                    <div class="form-group">
                        <label class="col-lg-2 control-label" for="user-base_password"><spring:message code="user.user.input.password" text="密码"/>:</label>
                         <input id="user-base_password" type="password" name="password" class="text required" maxlength="20">
                     </div>
                     <div class="form-group">
                        <label class="col-lg-2 control-label" for="user-base_confirmPassword"><spring:message code="user.user.input.confirmpassword" text="验证密码"/>:</label>
                        <input id="user-base_confirmPassword" type="password" name="confirmPassword" class="text required" maxlength="10" equalTo="#user-base_password">
                     </div>
                 </c:if>
                 <div class="form-group">
                      <label class="col-lg-2 control-label" for="user-base_status"><spring:message code="user.user.input.enabled" text="启用"/>:</label>
                      <input id="user-base_status" type="checkbox" name="status" value="1" ${model.status == 1 ? 'checked' : ''}>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label" for="user-base_displayName">显示名:</label>
                      <input id="user-base_displayName" type="text" name="displayName" value="${model.displayName}"  class="text required" minlength="2" maxlength="50">
                  </div>
                  <!-- 公司名称 -->
                  <div class="form-group">
                      <label class="col-lg-2 control-label" for="org_companyname">公司名称:</label>
                       <div class="col-lg-3">
                           <c:if test="${currentUserName=='admin'}">
	                           <select id="org_companyname" name="companyId" class="form-control required" >
	                               <option value="" >请选择</option>
	                               <c:forEach items="${companyidItems}" var="item">
	                                   <option value="${item.id}" ${item.id==model.companyId ? 'selected' : ''}>${item.name}</option>
	                               </c:forEach>
	                           </select>
                           </c:if>
                           <c:if test="${currentUserName!='admin'}">
                               <c:forEach items="${companyidItems}" var="item">
                                   <c:if test="${item.id==model.companyId}">
                                     <label>${item.name}</label>
                                   </c:if>
                               </c:forEach>
                               <input type="hidden" name="companyId" value="${model.companyId}">
                           </c:if>
                       </div>
                  </div>
                  
                  <div class="form-group">
                    <label class="col-lg-2 control-label" for="job_level">职位:</label>
                    <div class="col-lg-3">
                          <select class="form-control"  id="job_level" name="jobId" >
                              <c:forEach items="${jobInfos}" var="item">
                                <option value="${item.id}" ${item.id==model.jobInfo.id ? 'selected' : ''}>${item.jobTitle.name}</option>
                              </c:forEach>
                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-2 control-label" for="role_def">角色:</label>
                    <div class="col-lg-3">
                      <select class="form-control"  id="role_def" name="roleId" class="">
                          <c:forEach items="${roleDefs}" var="item">
                            <option value="${item.id}" ${item.id==model.roleDef.id ? 'selected' : ''}>${item.name}</option>
                          </c:forEach>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label" for="user-base_email">邮箱:</label>
                      <input id="user-base_email" type="text" name="email" value="${model.email}">
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label" for="user-base_mobile">手机:</label>
                      <input id="user-base_mobile" class="number"  type="text" name="mobile" value="${model.mobile}">
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label" for="user-base_ref"><spring:message code="user.user.input.ref" text="引用"/>:</label>
                      <input id="user-base_ref" type="text" name="ref" value="${model.ref}">
                  </div>
                  <div class="form-group">
                      <div class="col-lg-10 col-lg-offset-2">
                          <button id="submitButton" class="btn btn-primary a-submit"><spring:message code='core.input.save' text='保存'/></button>
                          <button type="button" onclick="history.back();" class="btn btn-primary a-cancel"><spring:message code='core.input.back' text='返回'/></button>
                      </div>
                  </div>
             </form>
        </div>
    <!-- end of main -->
    </div>

  </body>

</html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "group");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑公司</title>
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
                    url: 'org-company-checkOrgname.do',
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
                remote: "存在重复公司"
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
        <div class="panel-heading"><h4 class="panel-title glyphicon glyphicon-paperclip">编辑公司</h4></div>
        <div class="panel-body">
            <div class="content content-inner">
                <form id="orgForm" method="post" action="org-company-save.do" class="form-horizontal">
                    <c:if test="${model != null}">
                        <input id="org_id" type="hidden" name="id" value="${model.id}">
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-lg-2" for="org_orgname">公司名称:</label>
                        <!-- 编辑类型     多行 -->
                        <div class="col-lg-6">
                            <textarea class="form-control required" id="org_orgname" name="name" rows="1">${model.name}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
	                        <button id="submitButton" class="btn btn-primary btn-sm a-submit">保存</button>
	                        <button type="button" onclick="history.back();" class="btn btn-primary btn-sm">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
      <!-- end of main -->
      </div>
  </body>
</html>

<%@ page language="java" pageEncoding="UTF-8" %>
<div class="navbar navbar-inverse">
  <div class="navbar-header">
    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-inverse-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${ctx}/manage">后台管理</a>
  </div>
  <div class="navbar-collapse collapse navbar-inverse-collapse">
    <!-- 菜单部分 -->
    <ul class="nav navbar-nav">
        <!-- 业务组件管理 -->
        <!-- 内存监控曲线 -->
        <li class="active"><a href="${ctx}/common/memory/memoryCharts.jsp">内存监控</a></li>
        <!-- 导出SQL -->
        <li class="active"><a href="${ctx}/export-table/export-table-show.do">导出SQL</a></li>
    </ul>
    <!-- 靠右边部分 -->
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
           <a href="/iBusiness/j_spring_security_logout">退出</a>
       </li>
    </ul>
  </div>
</div>
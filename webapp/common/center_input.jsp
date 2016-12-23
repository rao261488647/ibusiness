<%@page language="java" pageEncoding="UTF-8" %>
    <!-- datepicker -->
    <style>
		.datepicker{
			z-index:99999999;
		}
	</style>
	 
    <script type="text/javascript">
		$(function() {
			// 显示信息
			if (typeof(showMessage) != 'undefined') {
			    $.showMessage($('#m-success-message').html(), {
			        position: 'top',
			        size: '55',
			        fontSize: '20px'
			    });
			}
		    // 日期控件
		    $('.datepicker').datepicker({
		    	format:'yyyy-mm-dd',
		    	language:'zh-CN',
		    	autoclose: true
	    	});
		    // 日期控件--年月
		    $('.datepicker_yyyymm').datepicker({
		    	format:'yyyy-mm',
	    		minViewMode:1,
	    		language:'zh-CN',
			    autoclose: true
		    });
		    // 日期时间控件
		    $('.datetimepicker').datetimepicker({
		    	language: 'zh-CN',
		    	weekStart:1, 
		    	todayBtn:1, 
		    	autoclose:true, 
		    	todayHighlight:1, 
		    	startView:2,
		    	minView:2,
		    	forceParse:0
		    	});
		});
    </script>
    
    <!-- 自动换行 
    <style type="text/css">
        table {table-layout:fixed}
        td {word-break:break-all;overflow:hidden;} 
    </style>
    -->
    <!--[if lte IE 9]>
	<script src="${ctx}/ibusiness/plc-control/skin/js/html5shiv.js"></script>
	<![endif]-->
	
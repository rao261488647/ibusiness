// JavaScript Document

$(function() {
	var iWid = 128;
	var iWidth = 64;
	var i = 0;

	var posLeft = 0;
	var posTop = 0;

	var flag = false;
	var scale = "scale(1)";
	$('body').removeClass("blur");
   // 获取浏览器显示区域的宽度
	var left = $(window).width() - $('#dock img').size() * 70;
	
    // 对浏览器窗口调整大小
	$(window).resize(function() {
		var size = $(window).width();
		if (size < 1024) {
			flag = true;
		} else {
			flag = false;
		}
		left = $(window).width() - $('#dock img').size() * 70;
	});
	
	// 遍历桌面每一个图标
	$('.icon').each(function() {
		  // 将图片中的title设置到span中
		  var title = $(this).children('img').attr('title');
		  $(this).children('span').html(title);

	})
    // 快捷菜单：单击
	$('#dock img').click(function() {
		if (flag) {
		} else {
			flag = true;
			var y = 90;
			$(this).animate({
				"margin-bottom" : 90
			}, 200, vibrant);
		}
		// 
		function vibrant() {
			if (y > 5) {
				y -= y * 0.45
				$(this).animate({
					"margin-bottom" : 0
				}, 90).animate({
					"margin-bottom" : y
				}, 90, vibrant);
			} else {
				$(this).animate({
					"margin-bottom" : 0
				})
				flag = false;
			}
		}
	})
    // 切换元素的可见状态
	$(".launch").toggle(function() {
		scale = "scale(1)";
		zoom();
		// 设置背景图片
	//	$('body').css({
	//		"background" : "url(mac/galaxyblur.jpg)"
	//	});
		// 使用淡出效果来隐藏一个元素
		// 方法将被选元素的不透明度逐渐地改变为指定的值
		$("#launchpad").fadeTo(300, 1);
	}, function() {
		scale = "scale(0.9)";
		zoom();
		// 设置背景图片
	//	$('body').css({
	//		"background" : "url(mac/galaxy.jpg)"
		//});
		// 使用淡出效果来隐藏一个元素
		// 方法将被选元素的不透明度逐渐地改变为指定的值
		$("#launchpad").fadeTo(100, 0);
	})
    // 设置图标显示画布DIV的CSS样式
	function zoom() {
		$("#launchpad").css({
			"-ms-transform" : scale,
			"-webkit-transform" : scale,
			"-moz-transform" : scale,
			"-o-transform" : scale
		});
	}

	$(".close").click(function() {
		$(".note").fadeOut(300);
	})

});
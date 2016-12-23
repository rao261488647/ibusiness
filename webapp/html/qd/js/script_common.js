/*
	[UCenter Home] (C) 2007-2008 Comsenz Inc.
	$Id: script_common.js 8541 2008-08-21 02:34:18Z zhengqingpeng $
*/

var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);
var is_safari = (userAgent.indexOf('webkit') != -1 || userAgent.indexOf('safari') != -1);
var note_step = 0;
var note_oldtitle = document.title;
var note_timer;

//iframe包含
if (top.location != location) {
	top.location.href = location.href;
}

function $(id) {
	return document.getElementById(id);
}

function addSort(obj) {
	if (obj.value == 'addoption') {
 	var newOptDiv = document.createElement('div')
 	newOptDiv.id = obj.id+'_menu';
 	newOptDiv.innerHTML = '<h1>添加</h1><a href="javascript:;" onclick="addOption(\'newsort\', \''+obj.id+'\')" class="float_del">删除</a><div class="popupmenu_inner" style="text-align: center;">名称：<input type="text" name="newsort" size="10" id="newsort" class="t_input" /><input type="button" name="addSubmit" value="创建" onclick="addOption(\'newsort\', \''+obj.id+'\')" class="button" /></div>';
 	newOptDiv.className = 'popupmenu_centerbox';
 	newOptDiv.style.cssText = 'position: absolute; left: 50%; top: 200px; width: 400px; margin-left: -200px;';
 	document.body.appendChild(newOptDiv);
 	$('newsort').focus();
 	}
}
	
function addOption(sid, aid) {
	var obj = $(aid);
	var newOption = $(sid).value;
	$(sid).value = "";
	if (newOption!=null && newOption!='') {
		var newOptionTag=document.createElement('option');
		newOptionTag.text=newOption;
		newOptionTag.value="new:" + newOption;
		try {
			obj.add(newOptionTag, obj.options[0]); // doesn't work in IE
		} catch(ex) {
			obj.add(newOptionTag, obj.selecedIndex); // IE only
		}
		obj.value="new:" + newOption;
	} else {
		obj.value=obj.options[0].value;
	}
	// Remove newOptDiv
	var newOptDiv = document.getElementById(aid+'_menu');
	var parent = newOptDiv.parentNode;
	var removedChild = parent.removeChild(newOptDiv);
}

function checkAll(form, name) {
	for(var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];
		if(e.name.match(name)) {
			e.checked = form.elements['chkall'].checked;
		}
	}
}

function cnCode(str) {
	return is_ie && document.charset == 'utf-8' ? encodeURIComponent(str) : str;
}

function isUndefined(variable) {
	return typeof variable == 'undefined' ? true : false;
}

function in_array(needle, haystack) {
	if(typeof needle == 'string' || typeof needle == 'number') {
		for(var i in haystack) {
			if(haystack[i] == needle) {
					return true;
			}
		}
	}
	return false;
}

function strlen(str) {
	return (is_ie && str.indexOf('\n') != -1) ? str.replace(/\r?\n/g, '_').length : str.length;
}

function getExt(path) {
	return path.lastIndexOf('.') == -1 ? '' : path.substr(path.lastIndexOf('.') + 1, path.length).toLowerCase();
}

function doane(event) {
	e = event ? event : window.event;
	if(is_ie) {
		e.returnValue = false;
		e.cancelBubble = true;
	} else if(e) {
		e.stopPropagation();
		e.preventDefault();
	}
}

//验证码
function seccode() {
	var img = 'do.php?ac=seccode&rand='+Math.random();
	document.writeln('<img id="img_seccode" src="'+img+'">');
}
function updateseccode() {
	var img = 'do.php?ac=seccode&rand='+Math.random();
	if($('img_seccode')) {
		$('img_seccode').src = img;
	}
}

//新通知
function newnote() {
	note_step++;
　　if (note_step==3) {note_step=1}
　　if (note_step==1) {document.title = '[有新通知]' + note_oldtitle}
　　if (note_step==2) {document.title = '[请您查收]' + note_oldtitle}
	note_timer = setTimeout("newnote();", 1500);
}

//缩小图片并添加链接
function resizeImg(id,size) {
	var theImages = $(id).getElementsByTagName('img');
	for (i=0; i<theImages.length; i++) {
		theImages[i].onload = function() {
			if (this.width > size) {
				this.style.width = size + 'px';
				if (this.parentNode.tagName.toLowerCase() != 'a') {
					var zoomDiv = document.createElement('div');
					this.parentNode.insertBefore(zoomDiv,this);
					zoomDiv.appendChild(this);
					zoomDiv.style.position = 'relative';
					zoomDiv.style.cursor = 'pointer';
					
					this.title = '点击图片，在新窗口显示原始尺寸';
					
					var zoom = document.createElement('img');
					zoom.src = 'images/zoom.gif';
					zoom.style.position = 'absolute';
					zoom.style.marginLeft = size -28 + 'px';
					zoom.style.marginTop = '5px';
					this.parentNode.insertBefore(zoom,this);
					
					zoomDiv.onmouseover = function() {
						zoom.src = 'images/zoom_h.gif';
					}
					zoomDiv.onmouseout = function() {
						zoom.src = 'images/zoom.gif';
					}
					zoomDiv.onclick = function() {
						window.open(this.childNodes[1].src);
					}
				}
			}
		}
	}
}

//Ctrl+Enter 发布
function ctrlEnter(event, btnId, onlyEnter) {
	if(isUndefined(onlyEnter)) onlyEnter = 0;
	if((event.ctrlKey || onlyEnter) && event.keyCode == 13) {
		$(btnId).click();
	}
}
//缩放Textarea
function zoomTextarea(id, zoom) {
	zoomSize = zoom ? 10 : -10;
	obj = $(id);
	if(obj.rows + zoomSize > 0 && obj.cols + zoomSize * 3 > 0) {
		obj.rows += zoomSize;
		obj.cols += zoomSize * 3;
	}
}

//复制URL地址
function setCopy(_sTxt){
	if(is_ie) {
		clipboardData.setData('Text',_sTxt);
		alert ("网址“"+_sTxt+"”\n已经复制到您的剪贴板中\n您可以使用Ctrl+V快捷键粘贴到需要的地方");
	} else {
		prompt("请复制网站地址:",_sTxt); 
	}
}

//验证是否有选择记录
function ischeck(id, prefix) {
	form = document.getElementById(id);
	for(var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];
		if(e.name.match(prefix) && e.checked) {
			if(confirm("您确定要执行本操作吗？")) {
				return true;
			} else {
				return false;
			}
		}
	}
	alert('请选择要操作的对象');
	return false;
}
function showPreview(val, id) {
	var showObj = $(id);
	if(typeof showObj == 'object') {
		showObj.innerHTML = val.replace(/\n/ig, "<br />");
	}
}

//导航二级菜单
function openSub(obj, target, isclick) {
	var tSub = $('sub' + target);
	if (tSub.style.display != 'block') {
		closeSub();
		var tMenu = obj.previousSibling;
		var tMenuP = obj.parentNode;
			tMenuP.id = 'subopen';
		var tLeft = tMenu.offsetLeft;
		var tTop = tMenu.offsetTop + 20;
		while(tMenu=tMenu.offsetParent) {
			tLeft+=tMenu.offsetLeft;
			tTop+=tMenu.offsetTop;
		}
		tSub.style.left = tLeft + 'px';
		tSub.style.top = tTop + 'px';
		tSub.style.display = 'block';
		tMenuP.className = 'active';
		obj.blur();
	} else {
		closeSub();
	}
	
	if(isclick) {
		var clickId = 0;
		document.body.onclick = function() {
			clickId = clickId + 1;
			if( clickId > 1 ) {
				closeSub();
			}
		}
	} else {
		document.body.onclick = function() {
			closeSub();
		}
	}
}

function closeSub() {
	var evt = getEvent();
	if(evt != null) {
		var srcElement = evt.srcElement || evt.target;
		if (srcElement.rel != 'menufocus') {
			var subMenu = $('submenu').getElementsByTagName('div');
			if($('subopen')){
				$('subopen').className = '';
				$('subopen').id = '';
			}
			for (i=0;i<subMenu.length;i++) {
				subMenu[i].style.display = 'none';
			}
		}
	}
}

function appExpand() { 
	if ($('subapp').className == 'submenu') {
		$('subapp').className = 'submenu expand';
		$('app-expand').childNodes[1].innerHTML = '收起';
	} else {
		$('subapp').className = 'submenu';
		$('app-expand').childNodes[1].innerHTML = '更多';
	}
	$('app-expand').childNodes[1].blur();
}

function getEvent() {
	if (document.all) return window.event;
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func=func.caller;
	}
	return null;
 }
 
function taskScroll(direction) {
	var task = $('task');
	var offset;
	task.className ? offset = parseInt(task.className) : offset = 0;
	var taskWidth = 0;
	var taskNum = task.getElementsByTagName('li');
	for ( i=0; i<taskNum.length; i++ ) {
		taskWidth += taskNum[i].clientWidth;
	}
	var taskbarWidth = $('taskbar').clientWidth;
	var startWidth = $('start').clientWidth;
	var quicklaunchWidth = $('quicklaunch') ? $('quicklaunch').clientWidth : 0;
	var taskScrollWidth = $('taskbar').clientWidth - startWidth - quicklaunchWidth - 70;
	var stop = false;
	$('scrollright').onmouseout = function(){stop = true;};
	$('scrollleft').onmouseout = function(){stop = true;};
	function scrollRight() {
		if(!stop && taskWidth > taskScrollWidth) {
			if ( direction == 'right') {
				$('scrollright').className = '';
				is_ie ? offset += 10 : offset += 3;
				if ( offset > 0 ) { offset = 0; $('scrollleft').className = 'unuse'; }
			} else {
				$('scrollleft').className = '';
				is_ie ? offset -= 10 : offset -= 3;
				if ( offset < - (taskWidth - taskScrollWidth) ) { offset = -(taskWidth - taskScrollWidth); $('scrollright').className = 'unuse'; }
			}
			task.style.left = offset + 'px';
			task.className = offset;
		}
		setTimeout(scrollRight, 5);
	}
	setTimeout(scrollRight, 80);
}

function getTaskWidth() {
	var taskbarWidth = $('taskbar').clientWidth;
	var startWidth = $('start').clientWidth;
	var quicklaunchWidth = $('quicklaunch') ? $('quicklaunch').clientWidth : 0;
	if ( is_safari ) {
		$('taskbody').style.width = taskbarWidth - 308  + 'px';
		$('taskscroll').style.width = taskbarWidth - 350  + 'px';
	} else if (is_ie) {
		$('taskbody').style.width = taskbarWidth - startWidth - quicklaunchWidth - 2 + 'px';
		$('taskscroll').style.width = taskbarWidth - startWidth - quicklaunchWidth - 45 + 'px';
	} else  {
		$('taskbody').style.width = taskbarWidth - startWidth - quicklaunchWidth - 25  + 'px';
		$('taskscroll').style.width = taskbarWidth - startWidth - quicklaunchWidth - 67  + 'px';
	}
}
 
function copyRow(tbody) {
	var add = false;
	var newnode;
	if($(tbody).rows.length == 1 && $(tbody).rows[0].style.display == 'none') {
		$(tbody).rows[0].style.display = '';
		newnode = $(tbody).rows[0];
	} else {
		newnode = $(tbody).rows[0].cloneNode(true);
		add = true;
	}
	tags = newnode.getElementsByTagName('input');
	for(i in tags) {
		if(tags[i].name == 'pics[]') {
			tags[i].value = 'http://';
		}
	}
	if(add) {
		$(tbody).appendChild(newnode);
	}
}
	
function delRow(obj, tbody) {
	if($(tbody).rows.length == 1) {
		var trobj = obj.parentNode.parentNode;
		tags = trobj.getElementsByTagName('input');
		for(i in tags) {
			if(tags[i].name == 'pics[]') {
				tags[i].value = 'http://';
			}
		}
		trobj.style.display='none';
	} else {
		$(tbody).removeChild(obj.parentNode.parentNode);
	}
}

function insertWebImg(obj) {
	if(checkImage(obj.value)) {
		insertImage(obj.value);
		obj.value = 'http://';
	} else {
		alert('图片地址不正确');
	}
}

function checkFocus(target) {
	var obj = $(target);
	if(!obj.hasfocus) {
		obj.focus();
	}
}
function insertImage(text) {
	text = "\n[img]" + text + "[/img]\n";
	insertContent('message', text)
}

function insertContent(target, text) {
	var obj = $(target);
	selection = document.selection;
	checkFocus(target);
	if(!isUndefined(obj.selectionStart)) {
		var opn = obj.selectionStart + 0;
		obj.value = obj.value.substr(0, obj.selectionStart) + text + obj.value.substr(obj.selectionEnd);
	} else if(selection && selection.createRange) {
		var sel = selection.createRange();
		sel.text = text;
		sel.moveStart('character', -strlen(text));
	} else {
		obj.value += text;
	}
}

function checkImage(url) {
	var re = /^http\:\/\/.{5,200}\.(jpg|gif|png)$/i
	return url.match(re);
}

//菜单自动出现
var menuTimer = 0;
function calculagraph() {
	menuTimer = window.setTimeout("execOpen();", 1000);
}
function execOpen() {
	openSub($('startmenu'), 'app', 0);
}
function stopCalculagraph() {
	window.clearTimeout(menuTimer);
}

//快捷面板
function quick_op(type) {
	$('quick_tab_doing').className = '';
	$('quick_tab_blog').className = '';
	$('quick_tab_upload').className = '';
	$('quick_tab_thread').className = '';
	$('quick_tab_share').className = '';
	$('quick_tab_'+type).className = 'active';
	$('quick_a_'+type).blur();
	
	var x = new Ajax();
	x.get('do.php?ac=ajax&op=quick&type='+type, function(s){
		$('quick_box').style.display = 'block';
		$('quick_box').innerHTML = s;
	});
}

function quick_op_close() {
	$('quick_box').style.display = 'none';
}

function quick_validate(obj) {
    if($('seccode')) {
		var code = $('seccode').value;
		var x = new Ajax();
		x.get('cp.php?ac=common&op=seccode&code=' + code, function(s){
			s = trim(s);
			if(s != 'succeed') {
				alert(s);
				$('seccode').focus();
           		return false;
			} else {
				obj.form.submit();
				return true;
			}
		});
    } else {
    	obj.form.submit();
    	return true;
    }
}

function trim(str) { 
	var re = /\s*(\S[^\0]*\S)\s*/; 
	re.exec(str); 
	return RegExp.$1; 
}
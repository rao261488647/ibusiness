function init(){
	alert("2222222222");
}

/**
 * 提交
 */
function doSubmit(){
//	var spanObj = document.getElementById("errorInfo");
//	if(checkBeingAndEndTime()){
////		spanObj.innerHTML = "";
		document.getElementById('titleForm').submit();
//	}else{
//		spanObj.innerHTML = "开始时间大于结束时间，请重新选择！";
//	}
}

/**
 * 设定select菜单选中项
 * @param data 选中的值
 * @param el select菜单对象名
 */
function reSetOptions(data,el){
	if(data.length > 0 ){
		var selectobj = document.getElementById(el);
		if(selectobj!=null || selectobj != undefined){
			var obj = selectobj.options;
			var i;
			for(i=0;i<obj.length;i++){
				if(obj[i].value == data){
					obj[i].selected = true;
					break;
				}
			}
		}
	}
}

/**
 * 创建下拉框中的对象。
 * @param el
 * @param textList
 * @param valueList
 */
function createOptions(el, textList, valueList){
	var obj = document.getElementById(el);
	if(obj.options.length > 1){
		removeOptions(el);
	}
	for(var i = 0 ; i < textList.length; i++){
		var option = new Option(textList[i], valueList[i]); 
		obj.options[i + 1]=option; 
	}
}
/**
 * 删除下拉框中的对象。
 * @param el
 */
function removeOptions(el) { 
    var	obj = document.getElementById(el); 
    var len = obj.options.length; 
    for (var i = 1; i < len; i++) {
        obj.options[1] = null; 
    } 
} 

function addHours(day, hour) {
	var value = hour.value.replace(/\s/g,"");
	if (value=="" || value==null) {
		return;
	}
	if (!/^[0-9]+$/.test(value)) {
		alert("请输入0-23的小时数");
		hour.value = "";
		return;
	}
	if (value<0 || value>=24) {
		alert("请输入0-23的小时数");
		hour.value = "";
		return;
	}
	var time = document.getElementById(day);
	if (time.value=="" || time.value==null) {
		alert("请选择日期");
		hour.value = "";
		return;
	}
	if (value.length == 1)
		value = "0" + value;
	time.value = time.value.split(" ")[0] + " " +value + ":00" + ":00";
}

/**
 * 判断结束时间是否比开始时间小，并提示
 * */
function checkBeingAndEndTime(){
	var beginTime = document.getElementById("beginTime").value;
	var endTime = document.getElementById("endTime").value;
	if(beginTime>endTime) return false;
	return true;
}

/** ================================选择时间的控件 begin ================================*/
var str = "";
//拼写填出窗口的样式
document.writeln("<div id=\"_contents\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777; position:absolute; left:?px; top:?px; width:?px; height:?px; z-index:1; visibility:hidden\">");
str += "\u65f6<select id=\"_hour\">";
for (h = 0; h <= 9; h++) {
    str += "<option value=\"0" + h + "\">0" + h + "</option>";
}
for (h = 10; h <= 23; h++) {
    str += "<option value=\"" + h + "\">" + h + "</option>";
}
str += "</select> \u5206<select id=\"_minute\">";
str += "<option value=00>00</option>";
str += "<option value=05>05</option>";
str += "<option value=10>10</option>";
str += "<option value=15>15</option>";
str += "<option value=20>20</option>";
str += "<option value=25>25</option>";
str += "<option value=30>30</option>";
str += "<option value=35>35</option>";
str += "<option value=40>40</option>";
str += "<option value=45>45</option>";
str += "<option value=50>50</option>";
str += "<option value=55>55</option>";
str += "</select> <input name=\"queding\" type=\"button\" onclick=\"_select()\" value=\"\u786e\u5b9a\" style=\"font-size:12px\" /></div>";

var _fieldname;
function doSetTime(tt) {
	_fieldname = tt;
    var ttop = tt.offsetTop;    //TT控件的定位点高
    var thei = tt.clientHeight;    //TT控件本身的高
    var tleft = tt.offsetLeft;    //TT控件的定位点宽
    while (tt = tt.offsetParent) {
        ttop += tt.offsetTop;
        tleft += tt.offsetLeft;
    }
    document.getElementById("_contents").style.top = ttop + thei + 4;
    document.getElementById("_contents").style.left = tleft;
    document.getElementById("_contents").style.visibility = "visible";
}
document.writeln(str);
function _select() {
    _fieldname.value = document.getElementById("_hour").value + ":" + document.getElementById("_minute").value + ":00";
    document.getElementById("_contents").style.visibility = "hidden";
}
/**================================ 选择时间的控件 end ===============================*/

/**
 * Format the date to output.
 */
function formatDate(date) {
	// 2 digits month.
	m2 = '00' + (date.getMonth() + 1);
	m2 = m2.substr(m2.length - 2);
	// 2 digits day.
	d2 = '00' + date.getDate();
	d2 = d2.substr(d2.length - 2);
	// YYYY-MM-DD
	return date.getFullYear() + '-' + m2 + '-' + d2;
}
/**
 * stype改变后设定开始时间和结束时间的值
 */
function stypeChange(name,beginTimeObject,endTimeObject){
	var beginTime;
	var endTime;
	var now = new Date();
	var month = now.getMonth();
	var year = now.getFullYear();
	var day = now.getDate();
	var wday = now.getDay();
	
	switch(name){
	case "today":	//今天
		beginTime = formatDate(now);
		endTime = beginTime;
		break;
	case "yestheday":  //昨天
		now.setDate(now.getDate()-1);
		beginTime = formatDate(now);
		endTime = beginTime;
		break;
	case "thedaybeforeyestheday": //前天
		now.setDate(now.getDate() -2);
		beginTime = formatDate(now);
		endTime = beginTime;
		break;
	case "pnweek":	//最近一周
		var day  = now.getDay();
		now.setDate(now.getDate() - 6 );
		beginTime = formatDate(now);

		now.setDate(now.getDate() + 6);
		endTime = formatDate(now);
	
		break;		
	case "tweek": //本周
		var day  = now.getDay();
		now.setDate(now.getDate() - (day -1));
		beginTime = formatDate(now);
		endTime = formatDate(new Date());
		break;
	case "pweek":	//上周
		var day  = now.getDay();
		now.setDate(now.getDate() - (day -1) - 7 );
		beginTime = formatDate(now);

		now.setDate(now.getDate() + 6);
		endTime = formatDate(now);
	
		break;
	case "p2week":	//上上周
		var day  = now.getDay();
		now.setDate(now.getDate() - (day -1) - 7 *2);
		beginTime = formatDate(now);

		now.setDate(now.getDate() + 6);
		endTime = formatDate(now);			
		break;
	case "pn30day":	//最近30天
		now.setDate(now.getDate() - 29);
		beginTime = formatDate(now);
		now.setDate(now.getDate() + 29 );
		endTime = formatDate(now);
		break;
	case "tmonth":  //本月
		now.setDate(1);
		beginTime = formatDate(now);
		endTime = formatDate(new Date());
		break;
	case "pmonth":	//上月
		now.setDate(1);
		now.setMonth(now.getMonth() -1 );
		beginTime = formatDate(now);

		var days = getDays(now);
		now.setDate(days);
		endTime = formatDate(now);
		break;
	case "p2month":	//上上月
		now.setDate(1);
		now.setMonth(now.getMonth() -1*2 );
		beginTime = formatDate(now);

		now.setMonth(now.getMonth() + 1);
		now.setDate(0);
		endTime = formatDate(now);
		break;
	case "pn90day":	//最近90天
		now.setDate(now.getDate() - 89);
		beginTime = formatDate(now);
		now.setDate(now.getDate() + 90 -1 );
		endTime = formatDate(now);
		break;
	case "tquarter":   //本季
		var m = now.getMonth() + 1;
		var q = parseInt((m + 2 ) / 3 ); //得到第几季
		m = q * 3 - 2;  //得到季的首月份

		now.setMonth(m-1);
		now.setDate(1);
		beginTime = formatDate(now);

		endTime = formatDate(new Date());
		break;
	case "pquarter":	//上季
		var m = now.getMonth() + 1;
		var q = parseInt((m + 2 ) / 3 ); //得到第几季
		m = q * 3 - 2;  //得到季的首月份

		m = m-3 ; //上季
		now.setMonth(m-1);
		now.setDate(1);
		beginTime = formatDate(now);

		now.setMonth(now.getMonth() + 3);
		now.setDate(0);
		endTime = formatDate(now);		
		break;
	case "p2quarter":	//上上季
		var m = now.getMonth() + 1;
		var q = parseInt((m + 2 ) / 3 ); //得到第几季
		m = q * 3 - 2;  //得到季的首月份

		m = m-3 -3 ; //上上季
		now.setMonth(m-1);
		now.setDate(1);
		beginTime = formatDate(now);

		now.setMonth(now.getMonth() + 3);
		now.setDate(0);
		endTime = formatDate(now);		
		break;		
	case "pn365day":	//最近365天
		now.setDate(now.getDate() - 365);
		beginTime = formatDate(now);
		now.setDate(now.getDate() + 365 );
		endTime = formatDate(now);
		break;
	case "tyear":	//今年
		now.setMonth(0);
		now.setDate(1);
		beginTime = formatDate(now);
		
		endTime = formatDate(new Date());		
		break;

	case "pyear":	//去年
		now.setFullYear(now.getFullYear() -1 );
		now.setMonth(0);
		now.setDate(1);
		beginTime = formatDate(now);
		
		now.setMonth(11);
		now.setDate(31);
		endTime = formatDate(now);			
		break;
	case "p2year":	//前年
		now.setFullYear(now.getFullYear() -2 );
		now.setMonth(0);
		now.setDate(1);
		beginTime = formatDate(now);
		
		now.setMonth(11);
		now.setDate(31);
		endTime = formatDate(now);			
		break;
	case "customize":	//自定义
		beginTime = "";
		endTime = "";
		break;
	}

	beginTimeObject.value = beginTime;
	endTimeObject.value = endTime;
}

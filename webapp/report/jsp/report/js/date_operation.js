/*
 * 报表展现页面的共用javascript
 *  
 * @param date
 * @return
 */
//Format the date to output.
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

function getDays(date){
	var m = date.getMonth() + 1;
	var y = date.getFullYear();
	var days ;
	if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
		days = 31;
	} else if (m == 4 || m == 6 || m == 9 || m == 11) {
		days = 30;
	} else {
		days = (y % 4 == 0) ? 29 : 28;
	}
	return days;
}

function dateChange(name,beginTimeObject,endTimeObject){
//	var beginTimeObject = document.getElementById("beginTime");
//	var endTimeObject = document.getElementById("endTime");


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

//		now.setMonth(now.getMonth() + 3);
//		now.setDate(0);
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
		
//		now.setMonth(11);
//		now.setDate(31);
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

function calendar_change(){
	var ob = document.getElementById("stype");
	ob.options[ob.options.length-1].selected = true;
}
function setDegree(value){
	if(degree.length > 0 ){
		var obj = document.getElementById("degree").options;
		var i;
		for(i=0;i<obj.length;i++){
			if(obj[i].value == value){
				obj[i].selected = true;
				break;
			}
		}
	}
}
function like_stype(stype){
	
	switch(stype){
	case "today":	//今天
		setDegree("hour");
		break;
	case "yestheday":  //昨天
		setDegree("hour");
		break;
	case "thedaybeforeyestheday": //前天
		setDegree("hour");
		break;
	case "tweek": //本周
		setDegree("day");
		break;
	case "pweek":	//上周
		setDegree("day");
		break;
	case "pnweek":	//最近一周
		setDegree("day");
		break;		
	case "p2week":	//上上周
		setDegree("day");	
		break;
	case "tmonth":  //本月
		setDegree("day");
		break;
	case "pmonth":	//上月
		setDegree("day");
		break;
	case "pn30day":	//最近30天
		setDegree("day");
		break;
	case "p2month":	//上上月
		setDegree("day");
		break;
	case "pn90day":	//最近90天
		setDegree("week");
		break;
	case "tquarter":   //本季
		setDegree("week");
		break;
	case "pquarter":	//上季
		setDegree("week");
		break;
	case "p2quarter":	//上上季
		setDegree("week");	
		break;		
	case "tyear":	//本年
		setDegree("month");	
		break;
	case "pyear":	//去年
		setDegree("month");		
		break;
	case "p2year":	//前年
		setDegree("month");		
		break;
	case "pn365day" : //最近365天
		setDegree("month");		
		break;
	case "customize":	//自定义
		setDegree("hour");
		break;
	}
}
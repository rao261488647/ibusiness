// 取得当前路径上下文
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
}
// 取得参数对象
var LocString = String(window.document.location.href);
function GetQueryString(str) {
	var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
	if (tmp = rs) {
		return tmp[2];
	}
	return "没有这个参数";
}
// 
//var urlpath = http://192.168.50.254:8080/;
var urlpath = getRootPath();


/**
 * 得到项目的根路径
 * @returns
 */
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： 
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

/**
 * 创建XMLHttpRequest对象
 * @returns
 */
function getxmlhttp(){
	var xmlhttp=null;
	try{
		xmlhttp=new XMLHttpRequest();
	}catch(e){
		try{
			xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}catch(e){
			alert("您的浏览器不支持ajax");
		}
	}
	return xmlhttp;
}

function ajax_Request(url,method_type,parameter,functionName,obj){
	var xmlhttp=getxmlhttp();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4&&xmlhttp.status==200){
			//http响应已经完全接受到才调用
			alert("hello");
			//functionName(xmlhttp,obj);
		}
	};
	xmlhttp.open("GET",url,true);
	xmlhttp.send(parameter);
}





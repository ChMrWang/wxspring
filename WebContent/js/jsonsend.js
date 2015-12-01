function sendjson(element,url){
	var url = url+"?t="+Math.random();
	alert("hello");
	var tags = element.getElementsByTagName("input");
	var json = "{";
	for(i=0;i<tags.length-1;i++){
		json+='"'+tags[i].name+'"'+':';
		json+='"'+tags[i].value+'"'+',';
	}
	json.length = json.length-1;
	json+="}";
	httpxml = new XMLHttpRequest();
	httpxml.open("post",url,true);
	httpxml.setRequestHeader("Content-Type", "application/json");
	httpxml.send(json);
	httpxml.onreadystatechange = function(){
		if(httpxml.readyState==4&&httpxml.status==200){
			alert("send successful!");
		}
	}
}
function shenpi(){
  		confirm("确认审批？");
 }


function getTime(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDay();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	month = month<10?('0'+month):month;
	day = day<10?('0'+day):day;
	hour = hour<10?('0'+hour):hour;
	minute = minute<10?('0'+minute):minute;
	second = second<10?('0'+second):second;
	var time = year+"."+month+"."+day+" "+hour+":"+minute+":"+second;
	/* alert("time"); */
	document.getElementById('time').innerHTML = time; 
} 
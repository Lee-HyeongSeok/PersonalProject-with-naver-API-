function loginPopWindow(){
	var width='850';
	var height='680';
	var left= window.screenLeft+((document.body.offsetWidth/2)-(width/2));
	var top = window.screenTop+((document.body.offsetHeight/2)-(height/2));
	
	let popURL = "/login/loginPopWindow";
	let popOption = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=no';
	
	window.name="parentWindow";
	window.open(popURL, "login", popOption);
}


function submitEvent(form){
	form.target = opener.name;
	form.submit();
	
	if(opener != null){
		opener.insert = null;
		self.close();
	}
}



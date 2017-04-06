
$.ajax({
		url : "/simsui/profile.htm",
		data : {firstname :"fgs",lastname :"fa"},
		type : "GET",

		success : function(response) {
			alert( response );
		},
		error : function(xhr, status, error) {
			alert(xhr.responseText);
		}
});

//one way of callinf ajax
ajaxCall_01(profile_url,{firstname :"fgs",lastname :"fa"},"POST",function(data){alert(data)});

//another way of callinf ajax
ajaxCall_01(profile_url,{firstname :"fgs",lastname :"fa"},"POST",onSucess,onError)
function onSucess(data){
	console.info(data)
}
function onError(data){
	console.error(data)
}
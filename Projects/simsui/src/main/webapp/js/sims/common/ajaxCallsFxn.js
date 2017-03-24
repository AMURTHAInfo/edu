
function ajaxUrl(endPoint){
	var fullPath="/simsui/"+endPoint+".htm";
	return fullPath;
}

function ajaxCall_01(url,data,type,onSucess,onError){
	$.ajax({
		url :  url,
		data : data,
		type : type,
		success : function(response) {
			onSucess( response );
		},
		error : function(xhr, status, error) {
			onError(xhr.responseText);
		}
	});

}
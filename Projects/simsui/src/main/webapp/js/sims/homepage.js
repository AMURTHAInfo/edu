$.ajax({
				url : "/ssimsui/profile.htm",
				data : {firstname :"fgs",lastname :"fa"},
				type : "GET",

				success : function(response) {
					alert( response );
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});

$.ajax({
	type: "post",
	url: "http://localhost:8080/simsui/employee/add.htm",
	cache: false,				
	data:"firstName=ning&lastName=king&email=lord",
	success: function(response){
		alert(response)
	},
	error: function(){						
		alert('Error while request..');
	}
});
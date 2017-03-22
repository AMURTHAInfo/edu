(function($) {
			$(window).load(function() {

				$("#content-2").mCustomScrollbar({
					scrollButtons : {
						enable : true
					},
					theme : "light-thick",
					scrollbarPosition : "outside"
				});
				$("#content-3").mCustomScrollbar({
					scrollButtons : {
						enable : true
					},
					theme : "light-thick",
					scrollbarPosition : "outside"
				});

				$("#content-5").mCustomScrollbar({
					axis:"x",
					theme:"dark-thin",
					autoExpandScrollbar:true,
					advanced:{autoExpandHorizontalScroll:true}
				}); 

			});
		})(jQuery);
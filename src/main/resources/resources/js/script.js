$(document).ready(function(){
	/* This code is executed after the DOM has been completely loaded */

	/* Changing thedefault easing effect - will affect the slideUp/slideDown methods: */
	$.easing.def = "easeOutBounce";

	/* Binding a click event handler to the links: */
	$('li.button a').click(function(e){
	
		/* Finding the drop down list that corresponds to the current section: */
		var dropDown = $(this).parent().next();
		
		/* Closing all other drop down sections, except the current one */
		$('.dropdown').not(dropDown).slideUp('slow');
		dropDown.slideToggle('slow');
		
		/* Preventing the default event (which would be to navigate the browser to the link's address) */
		e.preventDefault();
	})
	
});


var Inventory ={
		init : function() {
			if($('#password').val() != $('#confirm').val() || $('#password').val() == null || $('#confirm').val()){
				$("#btnSubmit").attr("disabled", true);
				$('#message').html("mat khau chua khop vui long nhap lai")	
			} 
			$('#btnSubmit').click(function () {
				if($('#exampleCheck1').checked == false){
					alert("ban chua dong y vs nhung dieu khoan cua chung toi")
				}
				});
		},
}
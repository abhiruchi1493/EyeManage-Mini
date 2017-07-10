$(document).ready(function() {
	$(function(){
		$("form[name='loginForm']").validate({
			rules	:	{
				userNameTxt	:	{
					required	:	true
				}
			},
			messages	:	{
				userNameTxt	:	{
					required	:	"Please enter your userName"
				}
			}/*,
			submitHandler	:	function(form)	{
				form.submit();
			}*/
		});
	});
});
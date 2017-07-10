$('#resultTable').on('click', '.clickable-row', function(event) {
	  if($(this).hasClass("info"))
	        $(this).removeClass('info');
	    else
	        $(this).addClass('info').siblings().removeClass('info');
	});

$('#resultTable .btn-info').on('click',function(event) {
	event.stopPropagation();
});

// On change of Search Filter Drop Down 
/*$('#searchDrop').change(function(){
    alert("The text has been changed.");
    $("#searchValue").attr("th:field","*{patientId}");
    alert($("#searchValue").attr("th:field"));
});*/


$('#birthDate').on('blur', function(event) {
	var dob			=	$(this).val();
	var dobYear		=	dob.substring(0,4);
	var dobMonth	=	dob.substring(5,7);
	var dobDay		=	dob.substring(8,10);
	var today		=	new Date();
	var todayYear	=	today.getFullYear();
	var todayMonth	=	today.getMonth() + 1;
	var todayDay	=	today.getDate();
});


// Delete Patient AJAX Starts

function deleteBtn(patientId){
	ajaxDelPost(patientId);
}

function ajaxDelPost(patientId){
	var url	=	window.location;
	var formData ={
			patientId : patientId
	}
	
	$.ajax({
	    type : "POST",
        contentType : "application/json",
        url : url + "/patientDelete",
        data :JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            if(result.status == "Done"){
            
            	$("#msg").html("<strong>" + "Patient Deleted Successfully!</strong>");
            	deleteRow(patientId);
            }else{
            	
            	$("#msg").html("<strong>" + "Something Went wrong</strong>");
            }
            console.log(result);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function deleteRow(patientId){
	$("#tr"+patientId).hide();
} 

//Delete Patient AJAX Ends


//Edit Patient AJAX Starts

function editBtn(patientId,firstName, lastName, gender, email, mobile, phoneNumber){
	ajaxEditPost(patientId,firstName,lastName,gender,email,mobile,phoneNumber);
}

function ajaxEditPost(patientId,firstName,lastName,gender,email,mobile,phoneNumber){
	var url	=	window.location;
	var formData ={
			patientId	:	patientId,
			firstName	:	firstName,
			lastName	:	lastName,
			gender		:	gender,
			email		:	email,
			mobile		:	mobile,
			phoneNumber	:	phoneNumber
	}
	
	$.ajax({
	    type : "POST",
        contentType : "application/json",
        url : url + "/patientEdit",
        data :JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            if(result.status == "Done"){
            
            	$("#msg").html("<strong>" + "Patient Details Edited Successfully!</strong>");
            }else{
            	
            	$("#msg").html("<strong>" + "Something Went wrong</strong>");
            }
            console.log(result);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

//Edit Patient AJAX Ends
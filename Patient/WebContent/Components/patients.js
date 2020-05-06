$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================

$(document).on("click", "#btnSave", function(event)
{
		// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
	// Form validation-------------------
	var status = validatePatientForm();
	if (status != true)
	{
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	}
	
	// If valid------------------------
	$("#formPatient").submit();
	
	});

	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hidPatientIDSave").val($(this).closest("tr").find('#hidPatientIDUpdate').val());
		 $("#patientName").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#patientAddress").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#patientPhone").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#patientEmail").val($(this).closest("tr").find('td:eq(3)').text());
});
	
	
	
// CLIENTMODEL=========================================================================
function validatePatientForm()
{
	// CODE
	if ($("#patientName").val().trim() == "")
	{
		return "Insert Patient Name.";
	}
	
	// NAME
	if ($("#patientAddress").val().trim() == "")
	{
		return "Insert Patient Address.";
	} 
	
	//PRICE-------------------------------
	if ($("#patientPhone").val().trim() == "")
	{
		return "Insert Patient Phone.";
	}
	
	// DESCRIPTION------------------------
	if ($("#patientEmail").val().trim() == "")
	{
		return "Insert Patient Email.";
	}
	return true;
}
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.Patient"%>

<% 
	
	
	if (request.getParameter("patientName") != null)  
	{  
		Patient patientObj = new Patient();   
		String stsMsg = ""; 
		
		if(request.getParameter("hidPatientIDSave") == "")
		{
	
			stsMsg = patientObj.insertPatients(request.getParameter("patientName"),     
											request.getParameter("patientAddress"),     
											request.getParameter("patientPhone"),        
											request.getParameter("patientEmail"));  
		}
		else
		{
			stsMsg = patientObj.updatePatients(request.getParameter("hidPatientIDSave"),
					 request.getParameter("patientName"),
					 request.getParameter("patientAddress"),
					 request.getParameter("patientPhone"),
					 request.getParameter("patientEmail")); 
		}
	
		session.setAttribute("statusMsg", stsMsg);
}

	if (request.getParameter("hidPatientIDDelete") != null)
	{
		Patient patientObj = new Patient();
		String stsMsg = patientObj.deletePatients(request.getParameter("hidPatientIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	} 
%>    

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="ISO-8859-1"> 
<title>Patients Management</title> 
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/patients.js"></script> 
</head> 
<body> 
<div class="container">
	<div class="row">
		<div class="col">
 
 		<h1>Patients Management</h1>
	
		<form id="formPatient" name="formPatient" method="post" action="patients.jsp">
			Patient name: <input id="patientName" name="patientName" type="text" class="form-control form-control-sm"><br>
	 		Patient address: <input id="patientAddress" name="patientAddress" type="text" class="form-control form-control-sm"><br>
	 		Patient phone: <input id="patientPhone" name="patientPhone" type="text" class="form-control form-control-sm" ><br>
	 		Patient email: <input  id="patientEmail" name="patientEmail" type="text" class="form-control form-control-sm"><br>
 			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 			<input type="hidden" id="hidPatientIDSave" name="hidPatientIDSave" value="">
		</form>
		
		<div id=alertSuccess class="alert alert-success">
			<% out.print(session.getAttribute("statusMsg")); %>
		</div>
		<div id=alertError class="alert alert-danger"></div>
		<br>
		<%   
			 Patient patientObj = new Patient(); 
			 out.print(patientObj.readPatients());
		%> 
		</div>
	</div>
</div>
 	
</body> 
</html> 
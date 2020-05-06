package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/patientdb", "root", "");

			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	public String insertPatients(String name, String address, String phone, String email) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into patients(`patientID`,`patientName`,`patientAddress`,`patientPhone`,`patientEmail`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			/*
			 * preparedStmt.execute(); con.close();
			 */
			preparedStmt.executeUpdate();

			String newPatients = readPatients();
			output = "{\"status\":\"success\", \"data\": \"" + newPatients + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the patient.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPatients() {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			output = "<table border='1'><tr>" + "<th>Patient Name</th>"
					+ "<th>Patinet Address</th><th>Patient Phone</th>" + "<th>Patient Email</th>" + "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from patients";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String patientID = Integer.toString(rs.getInt("patientID"));
				String patientName = rs.getString("patientName");
				String patientAddress = rs.getString("patientAddress");
				String patientPhone = rs.getString("patientPhone");
				String patientEmail = rs.getString("patientEmail");

				output += "<tr><td><input id='hidPatientIDUpdate' name='hidPatientIDUpdate' type='hidden' value='"
						+ patientID + "'>" + patientName + "</td>";
				output += "<td>" + patientAddress + "</td>";
				output += "<td>" + patientPhone + "</td>";
				output += "<td>" + patientEmail + "</td>";

				output +=  "<td><input name='btnUpdate' type='button'value='Update'class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button'value='Remove'class='btnRemove btn btn-danger' data-patientid='"
						 + patientID + "'>" + "</td></tr>"; 

			}
			con.close();
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the patients.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String updatePatients(String ID, String name, String address, String phone, String email) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the DB";
			}

			String query = "UPDATE patients SET patientName=?,patientAddress=?,patientPhone=?,patientEmail=? WHERE patientID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, email);
			preparedStmt.setInt(5, Integer.parseInt(ID));

			preparedStmt.execute();
			con.close();

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the patient.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePatients(String patientID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patients where patientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(patientID));
			
			// execute the statement
			/*
			 * preparedStmt.execute(); con.close();
			 */
			preparedStmt.executeUpdate();
			String newPatients = readPatients();
			output = "{\"status\":\"success\", \"data\": \"" + newPatients + "\"}";
		} 
		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the patient.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

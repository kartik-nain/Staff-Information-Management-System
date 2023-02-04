package staff_information_management_system;
/* I have developed a GUI and stored staff information in  the database (firstname, lastname, address, city, state, email, telephone.
 * Following functions can be performed:
 * Insertion, viewing information,clearing information,updating information. */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Information extends JFrame {

	public Information() throws ClassNotFoundException, SQLException {
		// Setting the title
		this.setTitle("Staff Information Management System");
		this.setLayout(null);

		// Declaring and Initializing the labels
		JLabel id_label = new JLabel("ID"); // label
		id_label.setBounds(20, 60, 100, 30); // size
		this.add(id_label); // adding label

		JTextField id_field = new JTextField(""); // text field
		id_field.setBounds(40, 60, 180, 30); // size
		id_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(id_field);

		JLabel last_name_label = new JLabel("Last Name");
		last_name_label.setBounds(20, 110, 100, 30);
		this.add(last_name_label);

		JTextField last_name_field = new JTextField("");
		last_name_field.setBounds(90, 110, 180, 30);
		last_name_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(last_name_field);

		JLabel first_name_label = new JLabel("First Name");
		first_name_label.setBounds(300, 110, 100, 30);
		this.add(first_name_label);

		JTextField first_name_field = new JTextField("");
		first_name_field.setBounds(370, 110, 180, 30);
		first_name_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(first_name_field);

		JLabel mi_label = new JLabel("mi");
		mi_label.setBounds(580, 110, 100, 30);
		this.add(mi_label);

		JTextField mi_field = new JTextField("");
		mi_field.setBounds(600, 110, 40, 30);
		mi_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(mi_field);

		JLabel address_label = new JLabel("Address");
		address_label.setBounds(20, 160, 100, 30);
		this.add(address_label);

		JTextField address_field = new JTextField("");
		address_field.setBounds(75, 160, 265, 30);
		address_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(address_field);

		JLabel city_label = new JLabel("City");
		city_label.setBounds(20, 210, 100, 30);
		this.add(city_label);

		JTextField city_field = new JTextField("");
		city_field.setBounds(55, 210, 260, 30);
		city_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(city_field);

		JLabel state_label = new JLabel("State");
		state_label.setBounds(335, 210, 100, 30);
		this.add(state_label);

		JTextField state_field = new JTextField("");
		state_field.setBounds(370, 210, 35, 30);
		state_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(state_field);

		JLabel telephone_label = new JLabel("Telephone");
		telephone_label.setBounds(20, 260, 100, 30);
		this.add(telephone_label);

		JTextField telephone_field = new JTextField("");
		telephone_field.setBounds(90, 260, 245, 30);
		telephone_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(telephone_field);

		JLabel email_label = new JLabel("Email");
		email_label.setBounds(365, 260, 100, 30);
		this.add(email_label);

		JTextField email_field = new JTextField("");
		email_field.setBounds(400, 260, 245, 30);
		email_field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border for the texfield
		this.add(email_field);

		// Declaring and Initializing the buttons.
		JButton view = new JButton("View");
		view.setBounds(20, 320, 100, 30);// submit button
		view.setBackground(Color.white);
		view.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border
		this.add(view);

		JButton insert = new JButton("INSERT");// button for inserting information
		insert.setBounds(200, 320, 100, 30);
		insert.setBackground(Color.white);
		insert.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border
		this.add(insert);

		JButton update = new JButton("UPDATE"); // update button for updating database information
		update.setBounds(370, 320, 100, 30);
		update.setBackground(Color.white);
		update.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border
		this.add(update);

		JButton clear = new JButton("CLEAR");// button for clearing data
		clear.setBounds(550, 320, 100, 30);
		clear.setBackground(Color.white);
		clear.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.add(clear);

		JLabel product_title = new JLabel("Staff Information:"); //label for staff information
		product_title.setBounds(20, 20, 150, 30);
		this.add(product_title);
		
		JLabel dc_label = new JLabel("Database Connected"); 
		dc_label.setBounds(20, 360, 150, 30);
		this.add(dc_label);

		Database db = new Database(); //creating new database object of its class
		Statement stmt = db.dbConnection().createStatement();  // creating the statement

		insert.addActionListener(new ActionListener() { //method for inserting information to database
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet rs;
				try {

					rs = stmt.executeQuery(
							"INSERT INTO staff (id, lastName, firstName, mi, address, city, state, telephone, email)" //Insert query
									+ " VALUES ('" + id_field.getText() + "','" + last_name_field.getText() + "','"
									+ first_name_field.getText() + "','" + mi_field.getText() + "','"
									+ address_field.getText() + "','" + city_field.getText() + "','"
									+ state_field.getText() + "','" + telephone_field.getText() + "','"
									+ email_field.getText() + "')");
					
					JOptionPane.showMessageDialog(null, "Insert Successfull! Click on 'View' to show the record.");  //message after insertion.
					
				} catch (SQLIntegrityConstraintViolationException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error! Unique ID violated");
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error! Entered Number of Characters exceeds the allowed number");
				}
			}
		});

		clear.addActionListener(new ActionListener() { // Method for clearing data 
			@Override
			public void actionPerformed(ActionEvent e) {
				id_field.setText("");
				last_name_field.setText("");
				first_name_field.setText("");
				mi_field.setText("");
				address_field.setText("");
				city_field.setText("");
				state_field.setText("");
				telephone_field.setText("");
				email_field.setText("");

			}
		});

		view.addActionListener(new ActionListener() { // Method for viewing the data stored and added to the database.
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet rs;
				try {
					rs = stmt.executeQuery(  
							"Select lastName, firstName, mi, address, city, state, telephone, email from staff WHERE id ='" 
					        + id_field.getText() + "'");
					
					int flag = 0;
					
					while (rs.next()) {
					last_name_field.setText(rs.getString("lastName"));
					first_name_field.setText(rs.getString("firstName"));
					mi_field.setText(rs.getString("mi"));
					address_field.setText(rs.getString("address"));
					city_field.setText(rs.getString("city"));
					state_field.setText(rs.getString("state"));
					telephone_field.setText(rs.getString("telephone"));
					email_field.setText(rs.getString("email"));
					flag = 1;
					}
					
					if(flag != 1) {
						JOptionPane.showMessageDialog(null, "Error! ID doesn't exist"); //checks if id exists or not in the database for viewing information.
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		update.addActionListener(new ActionListener() { // Method for updating information in the database
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!id_field.getText().equals("") && !last_name_field.getText().equals("")
							|| !first_name_field.getText().equals("") || !mi_field.getText().equals("")
							|| !address_field.getText().equals("") || !city_field.getText().equals("")
							|| !state_field.getText().equals("") || !telephone_field.getText().equals("")
							|| !email_field.getText().equals("")) {

						int flag = 0;
						if (!last_name_field.getText().equals("")) {
							String query = "update staff set lastName = '" + last_name_field.getText() //updating lastname
									+ "' where id = '" + id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
						}
						if (!first_name_field.getText().equals("")) {
							String query = "update staff set firstName = '" + first_name_field.getText() //updating firstname
									+ "' where id = '" + id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
							
						}
						if (!mi_field.getText().equals("")) {
							String query = "update staff set mi = '" + mi_field.getText() + "' where id = '" // updating mi field
									+ id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
							
						}
						if (!address_field.getText().equals("")) { //updating address
							String query = "update staff set address = '" + address_field.getText() + "' where id = '"
									+ id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
							
						}
						if (!city_field.getText().equals("")) { 
							String query = "update staff set city = '" + city_field.getText() + "' where id = '"
									+ id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
							
						}
						if (!state_field.getText().equals("")) {
							String query = "update staff set state = '" + state_field.getText() + "' where id = '"
									+ id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
							
						}
						if (!telephone_field.getText().equals("")) { //updating contact number
							String query = "update staff set telephone = '" + telephone_field.getText()
									+ "' where id = '" + id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
							
						}
						if (!email_field.getText().equals("")) { //updating email address.
							String query = "update staff set email = '" + email_field.getText() + "' where id = '"
									+ id_field.getText() + "'";
							PreparedStatement preparedStmt = db.dbConnection().prepareStatement(query);
							preparedStmt.executeUpdate();
							flag = preparedStmt.getUpdateCount();
						}

						if(flag == 0) { //checking for the id for which information needs to be updated
							JOptionPane.showMessageDialog(null, "Error! ID doesn't exist");
						}else if(flag == 1){
						    JOptionPane.showMessageDialog(null, "Update Successfull! Click on 'View' to show the update"); //message after updating fields.
						}

					} else {
						JOptionPane.showMessageDialog(null, "Error! Please enter id and one of the fields which you want to update");
					}

				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error! Entered Number of Characters exceeds the allowed number");
				}
			}
		});

	}

}

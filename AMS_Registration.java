package amsGui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class AMS_Registration extends JFrame{
	JLabel lblTitle, lblRegistration, lblUsername, lblPassword, lblFullname;
	JTextField txtUsername, txtFullName;
	JPasswordField txtPassword;
	JButton btnRegister, btnClear, btnBack;
	
	

	public static void main(String[] args) {
		new AMS_Registration();
	}
	
	
	
	
	
	AMS_Registration() {
		lblTitle = new JLabel("ACCOUNT MANAGEMENT SYSTEM", SwingConstants.CENTER);
		lblTitle.setBounds(0, 20, 480, 30);
		lblTitle.setFont(new Font("Serif", Font.BOLD, 20));
		add(lblTitle);
		
		
		lblRegistration = new JLabel("REGISTRATION", SwingConstants.CENTER);
		lblRegistration.setBounds(0, 50, 480, 30);
		lblRegistration.setFont(new Font("Serif", Font.BOLD, 20));
		add(lblRegistration);
		
		
		lblFullname = new JLabel("Full Name:");
		lblFullname.setBounds(30, 110, 90, 30);
		lblFullname.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblFullname);
		
		txtFullName = new JTextField();
		txtFullName.setBounds(120, 110, 250, 30);
		txtFullName.setFont(new Font("Serif", Font.PLAIN, 16));
		add(txtFullName);
		
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(30, 150, 90, 30);
		lblUsername.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(120, 150, 250, 30);
		txtUsername.setFont(new Font("Serif", Font.PLAIN, 16));
		add(txtUsername);
		
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(30, 190, 90, 30);
		lblPassword.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(120, 190, 250, 30);
		txtPassword.setFont(new Font("Serif", Font.PLAIN, 16));
		add(txtPassword);
		
		
		
		btnBack = new JButton("Back");
		btnBack.setBounds(150, 250, 100, 30);
		btnBack.setFont(new Font("Serif", Font.PLAIN, 16));
		add(btnBack);
		btnBack.addActionListener(e -> back());
		
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(260, 250, 100, 30);
		btnClear.setFont(new Font("Serif", Font.PLAIN, 16));
		add(btnClear);
		btnClear.addActionListener(e -> clear());
		
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(370, 250, 100, 30);
		btnRegister.setFont(new Font("Serif", Font.PLAIN, 16));
		add(btnRegister);
		btnRegister.addActionListener(e -> register());
		
		
		setLayout(null);
		setTitle("AMS Registration");
		setSize(500, 330);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	
	
	void back() {
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to return to log in?", "Return to Log In", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			new AMS_LogIn();
			dispose();
		}
	}
	
	
	
	void clear() {
		txtFullName.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
	}
	
	
	
	void register() {
		if (txtFullName.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill-up the requirements.", "Full Name/Username/Password", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to register this account?", "Confirm Registration", JOptionPane.YES_NO_OPTION);
	    if (confirm != JOptionPane.YES_OPTION) {
	        return;
	    }
		
		try {
			FileWriter fw = new FileWriter("Users.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String data = txtUsername.getText().trim() + ","
						+ new String(txtPassword.getPassword()).trim() + ","
						+ txtFullName.getText().trim();
			
			bw.write(data);
			bw.newLine();
			
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Registration successful!");
		clear();
	}

}

package amsGui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class AMS_LogIn extends JFrame{
	JLabel lblTitle, lblLogIn, lblUsername, lblPassword;
	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogIn, btnRegister, btnExit;
	
	
	
	public static void main(String[] args) {
		new AMS_LogIn();
	}
	
	
	
	
	
	AMS_LogIn() {
		lblTitle = new JLabel("ACCOUNT MANAGEMENT SYSTEM", SwingConstants.CENTER);
		lblTitle.setBounds(0, 20, 480, 30);
		lblTitle.setFont(new Font("Serif", Font.BOLD, 20));
		add(lblTitle);
		
		
		lblLogIn = new JLabel("LOG IN", SwingConstants.CENTER);
		lblLogIn.setBounds(0, 50, 480, 30);
		lblLogIn.setFont(new Font("Serif", Font.BOLD, 20));
		add(lblLogIn);
		
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(30, 110, 90, 30);
		lblUsername.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblUsername);
		
		
		txtUsername = new JTextField();
		txtUsername.setBounds(120, 110, 250, 30);
		txtUsername.setFont(new Font("Serif", Font.PLAIN, 16));
		add(txtUsername);
		
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(30, 150, 90, 30);
		lblPassword.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblPassword);
		
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(120, 150, 250, 30);
		txtPassword.setFont(new Font("Serif", Font.PLAIN, 16));
		add(txtPassword);
		
		
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(150, 220, 100, 30);
		btnExit.setFont(new Font("Serif", Font.PLAIN, 16));
		add(btnExit);
		btnExit.addActionListener(e -> exit());
		
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(260, 220, 100, 30);
		btnRegister.setFont(new Font("Serif", Font.PLAIN, 16));
		add(btnRegister);
		btnRegister.addActionListener(e -> register());
		
		
		btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(370, 220, 100, 30);
		btnLogIn.setFont(new Font("Serif", Font.PLAIN, 16));
		add(btnLogIn);
		btnLogIn.addActionListener(e -> logIn());
		
		
		
		setLayout(null);
		setTitle("AMS Log In");
		setSize(500, 300);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	
	
	void exit() {
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit log in?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	
	
	void register() {
		if (!txtUsername.getText().trim().isEmpty() || !txtPassword.getText().trim().isEmpty()) {
			int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to discard inputs?", "Confirm Registration", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				new AMS_Registration();
				dispose();
			}
		} else {
			new AMS_Registration();
			dispose();
		}
	}
	
	
	
	void logIn() {
	    String inputUser = txtUsername.getText().trim();
	    String inputPassword = new String(txtPassword.getPassword()).trim();

	    if (inputUser.isEmpty() || inputPassword.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Please fill-up username and password, or register new account.", "Username/Password/Register", JOptionPane.WARNING_MESSAGE);
	        return;
	    }


	    try {
	    	FileReader fr = new FileReader("Users.txt");
	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	boolean found = false;
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length == 3) {
	                String dataUser = parts[0].trim();
	                String dataPass = parts[1].trim();
	                String dataFullName = parts[2].trim();

	                if (inputUser.equals(dataUser) && inputPassword.equals(dataPass)) {
	                	JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + dataFullName + "!");
	        	        new AMS_AccountPage();
	        	        dispose();
	        	        found = true;
	        	        break;
	                }
	            }
	        }
	        
	        if (!found){
    	        JOptionPane.showMessageDialog(this, "Invalid username/password, or register new account.", "Login Failed", JOptionPane.WARNING_MESSAGE);
    		    clear();
    		}
	        
	        br.close();
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	void clear() {
		txtUsername.setText("");
		txtPassword.setText("");
	}

}

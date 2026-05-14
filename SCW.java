package gui;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class desabille extends JFrame {
	JLabel lblFullName, lblCourseDept, lblYearLevel, lblWalletBalance, lblStatus;
	JTextField txtFullName, txtCourseDept, txtYearLevel, txtWalletBalance, txtStatus;
	JButton btnAdd, btnUpdate, btnDelete, btnClear;
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollTable;
	
	public static void main(String[] args) {
		new desabille();
	}
	
	
	
	
	
	desabille() {
		lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(20, 0, 100, 20);
		lblFullName.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblFullName);
		
		txtFullName = new JTextField();
		txtFullName.setBounds(20, 20, 200, 20);
		txtFullName.setFont(new Font("Serif", Font.PLAIN, 12));
		add(txtFullName);
		
		
		lblCourseDept = new JLabel("Course/Dept");
		lblCourseDept.setBounds(20, 70, 100, 20);
		lblCourseDept.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblCourseDept);
		
		txtCourseDept = new JTextField();
		txtCourseDept.setBounds(20, 90, 200, 20);
		txtCourseDept.setFont(new Font("Serif", Font.PLAIN, 12));
		add(txtCourseDept);
		
		
		lblYearLevel = new JLabel("Year Level");
		lblYearLevel.setBounds(20, 140, 100, 20);
		lblYearLevel.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblYearLevel);
		
		txtYearLevel = new JTextField();
		txtYearLevel.setBounds(20, 160, 200, 20);
		txtYearLevel.setFont(new Font("Serif", Font.PLAIN, 12));
		add(txtYearLevel);
		
		
		lblWalletBalance = new JLabel("Wallet Balance");
		lblWalletBalance.setBounds(20, 210, 100, 20);
		lblWalletBalance.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblWalletBalance);
		
		txtWalletBalance = new JTextField();
		txtWalletBalance.setBounds(20, 230, 200, 20);
		txtWalletBalance.setFont(new Font("Serif", Font.PLAIN, 12));
		add(txtWalletBalance);
		
		
		lblStatus = new JLabel("Wallet Balance");
		lblStatus.setBounds(20, 280, 100, 20);
		lblStatus.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(20, 300, 200, 20);
		txtStatus.setFont(new Font("Serif", Font.PLAIN, 12));
		add(txtStatus);
		
		
		String[] columns = {"Full Name", "Course/Dept", "Year Level", "Wallet Balance", "Status"};
		model = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollTable = new JScrollPane(table);
		scrollTable.setBounds(240, 10, 470, 500);
		scrollTable.setFont(new Font("Serif", Font.PLAIN, 12));
		add(scrollTable);
		scrollTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row	 = table.getSelectedRow();
				if (row != -1) {
					txtFullName.setText(model.getValueAt(row, 0).toString());
					txtCourseDept.setText(model.getValueAt(row, 0).toString());
					txtYearLevel.setText(model.getValueAt(row, 0).toString());
					txtWalletBalance.setText(model.getValueAt(row, 0).toString());
					txtStatus.setText(model.getValueAt(row, 0).toString());
				}
			}
		});
		
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(300, 520, 80, 20);
		btnAdd.setFont(new Font("Serif", Font.PLAIN, 12));
		add(btnAdd);
		btnAdd.addActionListener(e -> add());
		
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(385, 520, 80, 20);
		btnUpdate.setFont(new Font("Serif", Font.PLAIN, 12));
		add(btnUpdate);
		btnUpdate.addActionListener(e -> update());
		
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(470, 520, 80, 20);
		btnDelete.setFont(new Font("Serif", Font.PLAIN, 12));
		add(btnDelete);
		btnDelete.addActionListener(e -> delete());
		
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(555, 520, 80, 20);
		btnClear.setFont(new Font("Serif", Font.PLAIN, 12));
		add(btnClear);
		btnClear.addActionListener(e -> clear());
		
		
		setLayout(null);
		setTitle("YUEM School Canteen Wallet Management System");
		setSize(750, 590);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	void add() {
		if (txtFullName.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter full name.", "Missing", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (txtCourseDept.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter course/department.", "Missing", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		if (txtStatus.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter course/department.", "Missing", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (!txtStatus.getText().trim().equals("Active") || !txtStatus.getText().trim().equals("Suspended")) {
			JOptionPane.showMessageDialog(this, "Please enter Active or Suspended.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Add Confirmation", JOptionPane.QUESTION_MESSAGE);
		if (choice != JOptionPane.YES_OPTION) {
			return;
		}
		
		
		try {
			int yearLevel = Integer.parseInt(txtYearLevel.getText().trim());
			double walletBalance = Double.parseDouble(txtWalletBalance.getText().trim());
			
			
			FileWriter fw = new FileWriter("SCW.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String data = txtFullName.getText().trim() + "#"
						+ txtCourseDept.getText().trim() + "#"
						+ yearLevel + "#"
						+ walletBalance + "#"
						+ txtStatus.getText().trim();
			
			bw.write(data);
			bw.newLine();
			
			bw.close();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Enter number only.", "Year Level  & Wallet Balance", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this,"Data added successfully.");
		clear();
		viewTableData();
		table.clearSelection();
	}
	
	
	
	void update() {
		int selectedRow = table.getSelectedRow();
	    
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Please select a data to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    if (txtFullName.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter full name.", "Missing", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (txtCourseDept.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter course/department.", "Missing", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		if (txtStatus.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter course/department.", "Missing", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (!txtStatus.getText().trim().equals("Active") || !txtStatus.getText().trim().equals("Suspended")) {
			JOptionPane.showMessageDialog(this, "Please enter Active or Suspended.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to updated this data?", "Update Confirmation", JOptionPane.YES_NO_OPTION);
		if (choice != JOptionPane.YES_OPTION) {
			return;
		}
		
		
		
		ArrayList<String> lines = new ArrayList<>();
	    try {
	    	int yearLevel = Integer.parseInt(txtYearLevel.getText().trim());
			double walletBalance = Double.parseDouble(txtWalletBalance.getText().trim());
			
			
			
	    	FileReader fr = new FileReader("SCW.txt");
	        BufferedReader br = new BufferedReader(fr);
	        
	        String line = "";
	        int rowIndex = 0;
	        
	        while ((line = br.readLine()) != null) {
	        	if (rowIndex == selectedRow) {
	        		String updateData = txtFullName.getText().trim() + "#"
							+ txtCourseDept.getText().trim() + "#"
							+ yearLevel + "#"
							+ walletBalance + "#"
							+ txtStatus.getText().trim();
	        		
	        		lines.add(updateData);
	        	} else {
	        		lines.add(line);
	        	}
	        	
	        	rowIndex++;
	        }
	        
	        br.close();
	    } catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog(this, "Enter number only.", "Year Level  & Wallet Balance", JOptionPane.ERROR_MESSAGE);
			return;
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    
	    
	    try {
	        FileWriter fw = new FileWriter("SCW.txt");
	        BufferedWriter bw = new BufferedWriter(fw);

	        for (String data : lines) {
	        	bw.write(data);
	        	bw.newLine();
	        }

	        bw.close();
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    JOptionPane.showMessageDialog(this,"Data updated successfully.");
	    clear();
	    table.clearSelection();
	    viewTableData();
	}
	
	
	
	void delete() {
		int selectedRow = table.getSelectedRow();
	    
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Please select a data to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    
	    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this data?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
	    if (confirm != JOptionPane.YES_OPTION) {
	        return;
	    }

	    

	    ArrayList<String> lines = new ArrayList<>();
	    try {
	    	FileReader fr = new FileReader("SCW.txt");
	        BufferedReader br = new BufferedReader(fr);
	        
	        String line = "";
	        int rowIndex = 0;
	        
	        while ((line = br.readLine()) != null) {
	        	if (rowIndex != selectedRow) {
	        		lines.add(line);
	        	}
	        	
	        	rowIndex++;
	        }
	        
	        br.close();
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    
	    
	    try {
	        FileWriter fw = new FileWriter("Employees.txt");
	        BufferedWriter bw = new BufferedWriter(fw);

	        for (String data : lines) {
	        	bw.write(data);
	        	bw.newLine();
	        }

	        bw.close();
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    JOptionPane.showMessageDialog(this,"Employee deleted successfully.");
	    clear();
	    table.clearSelection();
	    viewTableData();
	}
	
	
	
	void clear() {
		txtFullName.setText("");
		txtCourseDept.setText("");
		txtYearLevel.setText("");
		txtWalletBalance.setText("");
		txtStatus.setText("");
	}
	
	
	
	void viewTableData() {
		model.setRowCount(0);

		try {
			FileReader fr = new FileReader("SCW.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] row = line.split("#");
				model.addRow(row);
			}

			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "ERROR OCCURRED: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

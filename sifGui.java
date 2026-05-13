package amsGui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class sifGui extends JFrame{
	JLabel lblName, lblCourse, lblSection;
	JTextField txtName, txtCourse, txtSection;
	JButton btnAdd, btnUpdate, btnDelete, btnClear;
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollTable;
	
	

	public static void main(String[] args) {
		new sifGui();
	}
	
	
	
	
	
	sifGui() {
		lblName = new JLabel("Name");
		lblName.setBounds(10, 10, 80, 20);
		lblName.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 30, 130, 20);
		txtName.setFont(new Font("Serif", Font.BOLD, 12));
		add(txtName);
		
		
		lblCourse = new JLabel("Course");
		lblCourse.setBounds(150, 10, 80, 20);
		lblCourse.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblCourse);
		
		txtCourse = new JTextField();
		txtCourse.setBounds(150, 30, 130, 20);
		txtCourse.setFont(new Font("Serif", Font.BOLD, 12));
		add(txtCourse);
		
		
		lblSection = new JLabel("Section");
		lblSection.setBounds(290, 10, 80, 20);
		lblSection.setFont(new Font("Serif", Font.BOLD, 12));
		add(lblSection);
		
		txtSection = new JTextField();
		txtSection.setBounds(290, 30, 130, 20);
		txtSection.setFont(new Font("Serif", Font.BOLD, 12));
		add(txtSection);
		
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(20, 60, 90, 20);
		btnAdd.setFont(new Font("Serif", Font.BOLD, 12));
		add(btnAdd);
		btnAdd.addActionListener(e -> add());
		
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(119, 60, 90, 20);
		btnUpdate.setFont(new Font("Serif", Font.BOLD, 12));
		add(btnUpdate);
		btnUpdate.addActionListener(e -> update());
		
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(219, 60, 90, 20);
		btnDelete.setFont(new Font("Serif", Font.BOLD, 12));
		add(btnDelete);
		btnDelete.addActionListener(e -> delete());
		
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(319, 60, 90, 20);
		btnClear.setFont(new Font("Serif", Font.BOLD, 12));
		add(btnClear);
		btnClear.addActionListener(e -> clear());
		
		
		String[] columns = {"Name", "Course", "Section"};
		model = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollTable = new JScrollPane(table);
		scrollTable.setBounds(10, 90, 410, 265);
		add(scrollTable);
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					txtName.setText(model.getValueAt(row, 0).toString());
					txtCourse.setText(model.getValueAt(row, 1).toString());
					txtSection.setText(model.getValueAt(row, 2).toString());
				}
			}
		});
		
		
		refreshTable();
		
		
		
		setLayout(null);
		setTitle("WYZ Student Information Form");
		setSize(445, 405);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	
	
	void add() {
		if (txtName.getText().trim().isEmpty() || txtCourse.getText().trim().isEmpty() || txtSection.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill-up the requirements.", "Name/Course/Section", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this student information?", "Confirm Add", JOptionPane.YES_NO_OPTION);
		if (choice != JOptionPane.YES_OPTION) {
			return;
		}
		
		try {
			FileWriter fw = new FileWriter("Students.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String data = txtName.getText().trim() + "#"
						+ txtCourse.getText().trim() + "#"
						+ txtSection.getText().trim();
			
			bw.write(data);
			bw.newLine();
			
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Add Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Student information successfully saved!", "Add Saved", JOptionPane.INFORMATION_MESSAGE);
		clear();
		refreshTable();
	}
	
	
	
	void update() {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a student to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
	        return;
		}
		
		if (txtName.getText().trim().isEmpty() || txtCourse.getText().trim().isEmpty() || txtSection.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill-up the requirements.", "Name/Course/Section", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this student information?", "Confirm Update", JOptionPane.YES_NO_OPTION);
		if (choice != JOptionPane.YES_OPTION) {
			return;
		}
		
		
		
		ArrayList<String> lines = new ArrayList<>();
		try {
			FileReader fr = new FileReader("Students.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			int rowIndex = 0;
			while ((line = br.readLine()) != null) {
				if (rowIndex == selectedRow) {
					String updateData = txtName.getText().trim() + "#"
									+ txtCourse.getText().trim() + "#"
									+ txtSection.getText().trim();
					
					lines.add(updateData);
				} else {
					lines.add(line);
				}
				
				rowIndex++;
			}
			
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
		try {
			FileWriter fw = new FileWriter("Students.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (String data : lines) {
				bw.write(data);
				bw.newLine();
			}
			
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Student information successfully updated!", "Update Saved", JOptionPane.INFORMATION_MESSAGE);
		clear();
		refreshTable();
	}
	
	
	
	void delete() {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a student to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
	        return;
		}
		
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student information?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (choice != JOptionPane.YES_OPTION) {
			return;
		}
		
		
		
		ArrayList<String> lines = new ArrayList<>();
		try {
			FileReader fr = new FileReader("Students.txt");
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
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Delete Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
		try {
			FileWriter fw = new FileWriter("Students.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (String data : lines) {
				bw.write(data);
				bw.newLine();
			}
			
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Delete Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Student information successfully deleted!", "Update Deleted", JOptionPane.INFORMATION_MESSAGE);
		clear();
		refreshTable();
	}
	
	
	
	void clear() {
		txtName.setText("");
		txtCourse.setText("");
		txtSection.setText("");
		table.clearSelection();
	}
	
	
	
	void refreshTable() {
		model.setRowCount(0);
		
		try {
			FileReader fr = new FileReader("Students.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] row = line.split("#");
				model.addRow(row);
			}
			
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

}

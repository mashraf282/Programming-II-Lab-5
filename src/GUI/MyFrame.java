package GUI;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import Admin.AdminRole;
import System.StudentDatabase;
import System.StudentRecord;

import static java.lang.System.exit;


public class MyFrame extends JFrame {

    private AdminRole admin;

    public AdminRole getAdmin() {
        return admin;
    }

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        button.setSize(150, 150);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        return button;
    }

    private JPanel createHeaderPanel(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(0, 50, this.getWidth(), 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setOpaque(true);
        return titlePanel;
    }

    private JButton createReturnButton() {
        JButton button = new JButton("Go back");
        button.setFocusable(false);
//        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setBounds(0, 0, 300, 30);
        button.addActionListener(e -> displayHome());
        return button;
    }

    public MyFrame() {
        this.setTitle("Student Management System");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                getAdmin().logout();
                MyFrame.this.dispose();
            }
        });
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        displayLoginMenu();
    }

    private void displayLoginMenu() {
        this.getContentPane().removeAll();
        this.add(createHeaderPanel("Login Menu - Student Management System"));


        // Username Input
        JLabel userInputLabel = new JLabel("Username:");
        userInputLabel.setBounds(this.getWidth() / 2 - 150, 160, 300, 30);
        userInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(userInputLabel);

        JTextField userInputField = new JTextField();
        userInputField.setBounds(this.getWidth() / 2 - 150, 200, 300, 40);
        userInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        userInputField.setText("admin");
        this.add(userInputField);


        // Password Input
        JLabel passwordInputLabel = new JLabel("Password:");
        passwordInputLabel.setBounds(this.getWidth() / 2 - 150, 260, 300, 30);
        passwordInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(passwordInputLabel);

        JTextField passwordInputField = new JTextField();
        passwordInputField.setBounds(this.getWidth() / 2 - 150, 300, 300, 40);
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordInputField.setText("admin123");
        this.add(passwordInputField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(this.getWidth() / 2 - 75, 380, 150, 40);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setFocusable(false);
        this.add(loginButton);

        loginButton.addActionListener(e -> {
            if (AdminRole.login(userInputField.getText(), passwordInputField.getText())) {

                admin = new AdminRole(new StudentDatabase("Students.txt"));
                displayHome();
            } else {
                JOptionPane.showMessageDialog(MyFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.revalidate();
        this.repaint();

    }

    private void displayHome() {
        this.getContentPane().removeAll();
        this.add(createHeaderPanel("Welcome to Dashboard"));

        // Buttons label
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(2, 2, 10, 10));
        homePanel.setBackground(this.getBackground());
        homePanel.setBounds(0, 200, this.getWidth(), 300);
        homePanel.add(createButton("Add", e -> addStudentMenu()));
        homePanel.add(createButton("Delete", e -> viewStudentMenu(true)));
        homePanel.add(createButton("Search", e -> searchStudentMenu()));
        homePanel.add(createButton("View", e -> viewStudentMenu(false)));
        this.add(homePanel, BorderLayout.CENTER);

        // Log out button
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setBounds(this.getWidth() / 2 - 150, 600, 300, 50);
        JButton logoutButton = new JButton("Log out");
        logoutButton.setFocusable(false);
        logoutButton.setPreferredSize(new Dimension(300, 50));
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 30));
        logoutPanel.add(logoutButton);
        this.add(logoutPanel);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin.logout();
                exit(1);
            }
        });

        this.revalidate();
        this.repaint();
    }

    private void addStudentMenu() {
        this.getContentPane().removeAll();
        this.add(createHeaderPanel("Add Students Menu"));
        this.setLayout(null);

//        enum Idx {Id, Name, Age, Gender, Department}

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBounds(20, 200, 400, 400);

        // Name
        JPanel nameInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameInputLabel = new JLabel("Enter name:");
        nameInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField nameInputField = new JTextField(10);
        nameInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameInputPanel.add(nameInputLabel);
        nameInputPanel.add(nameInputField);
        formPanel.add(nameInputPanel);

        // Age
        JPanel ageInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel ageInputLabel = new JLabel("Enter age:");
        ageInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField ageInputField = new JTextField(10);
        ageInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        ageInputField.setBounds(100, 20, 20, 20);
        ageInputPanel.add(ageInputLabel);
        ageInputPanel.add(ageInputField, FlowLayout.CENTER);
        formPanel.add(ageInputPanel);

        // Gender
        JPanel genderInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel genderInputLabel = new JLabel("Enter gender:");
        genderInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        String[] genderType = {"Male", "Female"};
        JComboBox<String> genderInputBox = new JComboBox<>(genderType);
        genderInputBox.setBackground(Color.WHITE);
        genderInputBox.setOpaque(true);
        genderInputPanel.add(genderInputLabel);
        genderInputPanel.add(genderInputBox);
        formPanel.add(genderInputPanel);

        // Department
        JPanel departmentInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel departmentInputLabel = new JLabel("Enter department:");
        departmentInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField departmentInputField = new JTextField(10);
        departmentInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        departmentInputPanel.add(departmentInputLabel);
        departmentInputPanel.add(departmentInputField);
        formPanel.add(departmentInputPanel);

        // GPA or Grade
        JPanel gradeInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel gradeInputLabel = new JLabel("Enter Grade/GPA:");
        gradeInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField gradeInputField = new JTextField(10);
        gradeInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        gradeInputPanel.add(gradeInputLabel);
        gradeInputPanel.add(gradeInputField);
        formPanel.add(gradeInputPanel);

        // Submit Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(300, 50));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonPanel.add(submitButton);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(buttonPanel);
        this.add(formPanel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentRecord record = new StudentRecord();
                record.setName(nameInputField.getText());
                record.setGender(genderInputBox.getSelectedItem().toString());
                record.setDepartment(departmentInputField.getText());
                if (record.setAge(Integer.parseInt(ageInputField.getText()))) {
                    JOptionPane.showMessageDialog(MyFrame.this, "Invalid grade.", "Adding student: error", JOptionPane.ERROR_MESSAGE);
                    gradeInputField.setText("");
                } else if (record.setGPA(Double.parseDouble(gradeInputField.getText()))) {
                    JOptionPane.showMessageDialog(MyFrame.this, "Invalid age.", "Adding student: error", JOptionPane.ERROR_MESSAGE);
                    ageInputField.setText("");
                } else {
                    admin.addStudentRecord(record);
                    JOptionPane.showMessageDialog(MyFrame.this, "Student added", "Adding student", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        this.add(createReturnButton());
        this.revalidate();
        this.repaint();
    }

    private void viewStudentMenu(boolean delete) {
        this.getContentPane().removeAll();
        this.setLayout(null);
        this.add(createHeaderPanel(delete ? "Delete Students Menus" : "View Students Menu"));

        String[] columnNames = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        String[][] students = admin.getDatabase().toStringArray();
        DefaultTableModel model = new DefaultTableModel(students, columnNames);

        JTable viewTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        viewTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        viewTable.setRowHeight(25);
        viewTable.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(viewTable);
        scrollPane.setBounds(0, 150, this.getWidth(), this.getHeight() - 60);
        this.add(scrollPane);

        // Delete
        if (delete) {
            JButton deleteButton = new JButton("Delete");
            deleteButton.setBounds(this.getWidth() - 350, 0, 300, 30);
            deleteButton.setFocusable(false);
            this.add(deleteButton);
            deleteButton.addActionListener(e -> {
                int row;
                if ((row = viewTable.getSelectedRow()) > -1) {
                    int choice = JOptionPane.showConfirmDialog(MyFrame.this, "Are you sure you want to delete this student?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        var record = admin.getStudentByID(Integer.parseInt(viewTable.getValueAt(row, 0).toString()));
                        admin.deleteStudentRecord(record);
                        model.removeRow(row);
                    }
                }
            });
        }

        this.add(createReturnButton());
        this.revalidate();
        this.repaint();
    }

    private void searchStudentMenu() {

        this.getContentPane().removeAll();
        this.setLayout(null);
        this.add(createHeaderPanel("Search Student Panel"));

        String[] columnNames = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        String[][] students = admin.getDatabase().toStringArray();
        DefaultTableModel model = new DefaultTableModel(students, columnNames);

        JTable viewTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        viewTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        viewTable.setRowHeight(25);
        viewTable.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(viewTable);
        scrollPane.setBounds(0, 200, this.getWidth(), this.getHeight() - 60);
        this.add(scrollPane);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBounds(this.getWidth() / 2 - 150, 150, 300, 50);

        JTextField searchTextField = new JTextField(10);
        searchTextField.setFont(new Font("Arial", Font.PLAIN, 20));

        TableModelListener[] listenerHolder = new TableModelListener[1];

        listenerHolder[0] = e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int selectedRow = e.getFirstRow();
                int selectedColumn = e.getColumn();
                String modified = viewTable.getValueAt(selectedRow, selectedColumn).toString();
                int id = Integer.parseInt(viewTable.getValueAt(selectedRow, 0).toString());
                viewTable.getModel().removeTableModelListener(listenerHolder[0]);
                boolean is_modified = false;
                String message = "Invalid Input";

                if (modified == null || modified.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(MyFrame.this, "Cannot Change to empty Name", "Error modifying name", JOptionPane.ERROR_MESSAGE);
                    String[] fields = admin.getStudentByID(id).lineRepresentation().split(",");
                    System.out.println(fields[selectedColumn]);
                    viewTable.setValueAt(fields[selectedColumn], selectedRow, selectedColumn);
                } else
                    switch (selectedColumn) {
                        case 1:
                            if (modified.matches("[a-zA-Z]+")) {
                                admin.getStudentByID(id).setName(modified);
                                is_modified = true;
                            } else
                                message = "invalid name";
                            break;
                        case 2:
                            if (modified.matches("[0-9]+") && admin.getStudentByID(id).setAge(Integer.parseInt(modified)))
                                is_modified = true;
                            else
                                message = "invalid age";
                            break;
                        case 3:
                            if (admin.getStudentByID(id).setGender(modified)) {
                                is_modified = true;
                            } else
                                message = "invalid gender";
                            break;
                        case 4:
                            if(modified.matches("[a-zA-Z]+]")) {
                                admin.getStudentByID(id).setDepartment(modified);
                                is_modified = true;
                            }
                            else
                                message = "invalid department";
                            break;
                        case 5:
                            if(admin.getStudentByID(id).setGPA(Double.parseDouble(modified))){
                                is_modified = true;
                            }
                            else
                                message = "invalid GPA";
                            break;
                    }
                if (!is_modified) {
                    JOptionPane.showMessageDialog(MyFrame.this, message, "Error editing student", JOptionPane.ERROR_MESSAGE);
                    String[] fields = admin.getStudentByID(id).lineRepresentation().split(",");
                    System.out.println(fields[selectedColumn]);
                    viewTable.setValueAt(fields[selectedColumn], selectedRow, selectedColumn);
                }

            }
            viewTable.getModel().addTableModelListener(listenerHolder[0]);
        };

        viewTable.getModel().addTableModelListener(listenerHolder[0]);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            if (viewTable.isEditing())
                viewTable.getCellEditor().stopCellEditing();
            viewTable.getModel().removeTableModelListener(listenerHolder[0]);
            String search_key = searchTextField.getText();
            boolean is_numeric = true;
            int id = 0;
            try {
                id = Integer.parseInt(search_key);
            } catch (NumberFormatException nfe) {
                model.setDataVector(admin.searchStudentRecord(search_key), columnNames);
                is_numeric = false;
            }
            if (is_numeric) {
                model.setDataVector(admin.searchStudentRecord(id), columnNames);
            }
            viewTable.getModel().addTableModelListener(listenerHolder[0]);
        });

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        this.add(searchPanel);

        this.add(createReturnButton());
        this.revalidate();
        this.repaint();
    }
}

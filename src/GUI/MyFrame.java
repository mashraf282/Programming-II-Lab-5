package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Admin.AdminRole;
import System.StudentDatabase;
import System.StudentRecord;

import static java.lang.System.exit;


public class MyFrame extends JFrame {

    private AdminRole admin;

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        button.setSize(150, 150);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        return button;
    }

    private JButton createReturnButton(){
        JButton button = new JButton("Go back");
        button.setFocusable(false);
//        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setBounds(0,0,300,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHome();
            }
        });
        return button;
    }

    public MyFrame() {
        this.setTitle("Student Management System");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        displayLoginMenu();
    }

    private void displayLoginMenu() {
        this.getContentPane().removeAll();

        // Title Label
        JLabel titleLabel = new JLabel("Login Menu - Student Management System");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(0, 50, this.getWidth(), 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setOpaque(true);
        this.add(titlePanel);


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
    private void displayHome(){
        this.getContentPane().removeAll();

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Dashboard");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(0, 50, this.getWidth(), 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setOpaque(true);
        this.add(titlePanel);

        // Buttons label
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new  GridLayout(2, 2, 10, 10));
        homePanel.setBackground(this.getBackground());
        homePanel.setBounds(0, 200, this.getWidth(), 300);
        homePanel.add(createButton("Add", e -> addStudentMenu()));
        homePanel.add(createButton("Delete", e -> viewStudentMenu(true)));
        homePanel.add(createButton("Search", e -> searchStudentMenu()));
        homePanel.add(createButton("View", e-> viewStudentMenu(false)));
        this.add(homePanel, BorderLayout.CENTER);

        // Log out button
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setBounds(this.getWidth() / 2 - 150, 600 ,300,50);
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

        // Title panel
        JLabel titleLabel = new JLabel("Add student menu");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(0, 50, this.getWidth(), 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setOpaque(true);
        this.add(titlePanel);

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
        ageInputPanel.add(ageInputLabel);
        ageInputPanel.add(ageInputField);
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
                record.setDepartment( departmentInputField.getText());
                if(record.setAge(Integer.parseInt(ageInputField.getText())))
                {
                    JOptionPane.showMessageDialog(MyFrame.this,"Invalid grade.","Adding student: error",JOptionPane.ERROR_MESSAGE);
                    gradeInputField.setText("");
                }
                else if(record.setGPA(Double.parseDouble(gradeInputField.getText())))
                {
                    JOptionPane.showMessageDialog(MyFrame.this,"Invalid age.","Adding student: error",JOptionPane.ERROR_MESSAGE);
                    ageInputField.setText("");
                }
                else {
                    admin.addStudentRecord(record);
                    JOptionPane.showMessageDialog(MyFrame.this,"Student added","Adding student",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });


        this.add(createReturnButton());
        this.revalidate();
        this.repaint();
    }


    private void viewStudentMenu(boolean delete) {
        this.getContentPane().removeAll();

        // Title panel
        JLabel titleLabel = new JLabel("View Student menu");
        if(delete)
            titleLabel.setText("Delete Student menu");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(0, 50, this.getWidth(), 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setOpaque(true);
        this.add(titlePanel);

        // Table
        JTable viewTable;
        JScrollPane sp = null;

        String[] info = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        String[][] students = admin.returnAllStudentsID();
        DefaultTableModel model = new DefaultTableModel(students, info);
        viewTable = new JTable(model){
                @Override
                public boolean isCellEditable(int row,int column) {
                    return false;
                }
            };
        sp = new JScrollPane(viewTable);
        sp.setBounds(0, 150, this.getWidth(), this.getHeight() - 60);
        this.setLayout(null);
        this.add(sp);

        // Delete
        if(delete){
            JButton deleteButton = new JButton("Delete");
            deleteButton.setBounds(this.getWidth() - 350,0,300,30);
            deleteButton.setFocusable(false);
            this.add(deleteButton);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row;
                    if((row = viewTable.getSelectedRow()) > -1)
                    {
                        int choice = JOptionPane.showConfirmDialog(MyFrame.this,
                                "Are you sure you want to delete this student?",
                                "Confirm Deletion",
                                JOptionPane.YES_NO_OPTION
                                );
                        if(choice == JOptionPane.YES_OPTION)
                        {
                            StudentRecord record = new StudentRecord(
                                    Integer.parseInt(viewTable.getValueAt(row,0).toString()),
                                    viewTable.getValueAt(row,1).toString(),
                                    Integer.parseInt(viewTable.getValueAt(row,2).toString()),
                                    viewTable.getValueAt(row,3).toString(),
                                    viewTable.getValueAt(row,4).toString(),
                                    Double.parseDouble(viewTable.getValueAt(row,5).toString())
                            );

                          admin.deleteStudentRecord(record);
                          model.removeRow(row);
                        }
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

        this.add(createReturnButton());
        this.revalidate();
        this.repaint();
    }
}

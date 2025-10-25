package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Admin.AdminRole;
import System.Validation;
import System.StudentDatabase;
import System.StudentRecord;


public class MyFrame extends JFrame {

    private StudentDatabase database;
    private AdminRole admin;

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        button.setSize(150, 150);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        return button;
    }

    public MyFrame() {
//        database = new StudentDatabase("Students.txt");
        this.setTitle("Student Management System");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
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
            if (Validation.login(userInputField.getText(), passwordInputField.getText())) {
//                admin = new AdminRole(database);
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
        homePanel.setBounds(0, 300, this.getWidth(), 300);
        homePanel.add(createButton("Add", e -> addStudentMenu()));
        homePanel.add(createButton("Delete", e -> deleteStudentMenu()));
        homePanel.add(createButton("Edit", e -> editStudentMenu()));
        homePanel.add(createButton("View", e-> viewStudentMenu()));
        this.add(homePanel, BorderLayout.CENTER);

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

        // ID
        JPanel idInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idInputLabel = new JLabel("Enter ID:");
        idInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField idInputField = new JTextField(10);
        idInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        idInputPanel.add(idInputLabel);
        idInputPanel.add(idInputField);
        formPanel.add(idInputPanel);

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
                StudentRecord record = new StudentRecord(
                        Integer.parseInt(idInputField.getText()),
                        nameInputField.getText(),
                        Integer.parseInt(ageInputField.getText()),
                        genderInputBox.getSelectedItem().toString(),
                        departmentInputField.getText(),
                        Double.parseDouble(gradeInputField.getText())
                );
                if(Validation.studentIsCorrect(record))
                    JOptionPane.showMessageDialog(MyFrame.this, "Student added successfully", "Student add", JOptionPane.INFORMATION_MESSAGE);
                else {
                    String[] options = {"Try again", "Go back"};
                    JOptionPane.showOptionDialog(MyFrame.this, "Invalid input type.", "Student adding error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, 0);
                }
            }
        });


        this.revalidate();
        this.repaint();
    }

    private void deleteStudentMenu() {

    }

    private void viewStudentMenu() {

    }

    private void editStudentMenu() {

    }
}

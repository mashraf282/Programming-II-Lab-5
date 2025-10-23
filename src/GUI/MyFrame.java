package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import System.Validation;

public class MyFrame extends JFrame {

    public MyFrame() {
        this.setTitle("Student Management System");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        displayMainMenu();
    }

    public void displayMainMenu() {
        this.getContentPane().removeAll();

        // Title Label
        JLabel titleLabel = new JLabel("Main Menu - Student Management System");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(0, 50, this.getWidth(), 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setOpaque(true);


        // Username Input
        JLabel userInputLabel = new JLabel("Username:");
        userInputLabel.setBounds(this.getWidth() / 2 - 150, 160, 300, 30);
        userInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(userInputLabel);

        JTextField userInputField = new JTextField();
        userInputField.setBounds(this.getWidth() / 2 - 150, 200, 300, 40);
        userInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(userInputField);


        // Password Input
        JLabel passwordInputLabel = new JLabel("Password:");
        passwordInputLabel.setBounds(this.getWidth() / 2 - 150, 260, 300, 30);
        passwordInputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(passwordInputLabel);

        JTextField passwordInputField = new JTextField();
        passwordInputField.setBounds(this.getWidth() / 2 - 150, 300, 300, 40);
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(passwordInputField);


        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(this.getWidth() / 2 - 75, 380, 150, 40);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setFocusable(false);
        this.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Validation.login(userInputField.getText(), passwordInputField.getText())){
                    System.out.println("Login successful");
                    // proceed to admin menu
                } else {
                    // show error message
                    JOptionPane.showMessageDialog(MyFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.add(titlePanel);
        this.revalidate();
        this.repaint();

    }
}

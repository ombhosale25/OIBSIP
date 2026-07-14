package gui;

import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Online Reservation System");

        setSize(500,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        JLabel title = new JLabel("Online Reservation System");
        title.setBounds(130,20,250,30);
        add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(70,80,100,25);
        add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(170,80,180,25);
        add(username);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(70,130,100,25);
        add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(170,130,180,25);
        add(password);

        JButton login = new JButton("Login");
        login.setBounds(170,190,100,35);
        add(login);

        login.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = username.getText();

        String pass = String.valueOf(password.getPassword());

        if(user.equals("admin") && pass.equals("admin123")){

            JOptionPane.showMessageDialog(null,
        "Login Successful");

        new Dashboard();

dispose();
        }else{

            JOptionPane.showMessageDialog(null,
                    "Invalid Username or Password");

        }

    }

});
        setVisible(true);

    }

}
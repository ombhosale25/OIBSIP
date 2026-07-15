package gui;

import javax.swing.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Dashboard");

        setSize(500, 350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        JLabel title = new JLabel("Welcome to Dashboard");
        title.setBounds(150, 30, 200, 30);
        add(title);

        JButton reserveButton = new JButton("Reserve Ticket");
        reserveButton.setBounds(150, 90, 180, 35);
        add(reserveButton);

        reserveButton.addActionListener(e -> {
             new ReservationForm();
             dispose();
        });

        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(150, 150, 180, 35);
        add(cancelButton);
        cancelButton.addActionListener(e -> {

    new CancellationForm();

});

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 210, 180, 35);
        add(logoutButton);

        logoutButton.addActionListener(e -> {

            new LoginFrame();   // Open Login Window
           dispose();          // Close Dashboard

        });

        setVisible(true);
    }
}
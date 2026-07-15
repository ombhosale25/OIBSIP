package gui;

import dao.TrainDAO;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import dao.ReservationDAO;
import model.Reservation;
import util.PNRGenerator;

public class ReservationForm extends JFrame {

    public ReservationForm() {

        setTitle("Reservation Form");
        setSize(550,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("Train Reservation Form");
        heading.setBounds(170,20,250,30);
        add(heading);

        JLabel passengerLabel = new JLabel("Passenger Name");
        passengerLabel.setBounds(40,70,120,25);
        add(passengerLabel);

        JTextField passengerField = new JTextField();
        passengerField.setBounds(180,70,250,25);
        add(passengerField);

        JLabel trainNoLabel = new JLabel("Train Number");
        trainNoLabel.setBounds(40,110,120,25);
        add(trainNoLabel);

        JTextField trainNoField = new JTextField();
        trainNoField.setBounds(180,110,250,25);
        add(trainNoField);

        JLabel trainNameLabel = new JLabel("Train Name");
        trainNameLabel.setBounds(40,150,120,25);
        add(trainNameLabel);

        JTextField trainNameField = new JTextField();
        trainNameField.setBounds(180,150,250,25);
        trainNameField.setEditable(false);
        add(trainNameField);
        //#########for fetch train name using its number
        trainNoField.addFocusListener(new FocusAdapter() {

    @Override
    public void focusLost(FocusEvent e) {

        try {

            int trainNo =
                    Integer.parseInt(trainNoField.getText());

            TrainDAO dao = new TrainDAO();

            String trainName =
                    dao.getTrainName(trainNo);
            trainNameField.setText(trainName);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "Invalid Train Number");
        }
    }

});

        JLabel classLabel = new JLabel("Class");
        classLabel.setBounds(40,190,120,25);
        add(classLabel);

        String[] classes = {
                "Sleeper",
                "AC",
                "General",
                "First Class"
        };

        JComboBox<String> classBox =
                new JComboBox<>(classes);
        classBox.setBounds(180,190,250,25);
        add(classBox);

        JLabel dateLabel = new JLabel("Journey Date");
        dateLabel.setBounds(40,230,120,25);
        add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(180,230,250,25);
        add(dateField);

        JLabel sourceLabel = new JLabel("Source");
        sourceLabel.setBounds(40,270,120,25);
        add(sourceLabel);

        JTextField sourceField = new JTextField();
        sourceField.setBounds(180,270,250,25);
        add(sourceField);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(40,310,120,25);
        add(destinationLabel);

        JTextField destinationField = new JTextField();
        destinationField.setBounds(180,310,250,25);
        add(destinationField);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBounds(180,370,150,35);
        add(bookButton);
        setVisible(true);
        bookButton.addActionListener(e -> {

    try {

        Reservation reservation = new Reservation();

        reservation.setPnr(PNRGenerator.generatePNR());
        reservation.setPassengerName(passengerField.getText().trim());
        reservation.setTrainNo(Integer.parseInt(trainNoField.getText()));
        reservation.setTrainName(trainNameField.getText());
        reservation.setClassType(classBox.getSelectedItem().toString());
        reservation.setJourneyDate(dateField.getText().trim());
        reservation.setSource(sourceField.getText().trim());
        reservation.setDestination(destinationField.getText().trim());

        ReservationDAO dao = new ReservationDAO();

        if (dao.bookTicket(reservation)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking Successful!\n\nPNR : " + reservation.getPnr());

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking Failed!");

        }

    } catch (Exception ex) {

        ex.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "Please enter valid details.");

    }

});

    }

}
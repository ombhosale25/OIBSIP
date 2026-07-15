package gui;

import dao.TrainDAO;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import dao.ReservationDAO;
import model.Reservation;
import util.PNRGenerator;

public class ReservationForm extends JFrame {

    private JTextField passengerField;
private JTextField trainNoField;
private JTextField trainNameField;
private JTextField dateField;
private JTextField sourceField;
private JTextField destinationField;
private JComboBox<String> classBox;

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

         passengerField = new JTextField();
        passengerField.setBounds(180,70,250,25);
        add(passengerField);

        JLabel trainNoLabel = new JLabel("Train Number");
        trainNoLabel.setBounds(40,110,120,25);
        add(trainNoLabel);

         trainNoField = new JTextField();
        trainNoField.setBounds(180,110,250,25);
        add(trainNoField);

        JLabel trainNameLabel = new JLabel("Train Name");
        trainNameLabel.setBounds(40,150,120,25);
        add(trainNameLabel);

         trainNameField = new JTextField();
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

       classBox = new JComboBox<>(classes);
        classBox.setBounds(180,190,250,25);
        add(classBox);

        JLabel dateLabel = new JLabel("Journey Date");
        dateLabel.setBounds(40,230,120,25);
        add(dateLabel);

         dateField = new JTextField();
        dateField.setBounds(180,230,250,25);
        add(dateField);

        JLabel sourceLabel = new JLabel("From");
        sourceLabel.setBounds(40,270,120,25);
        add(sourceLabel);

         sourceField = new JTextField();
        sourceField.setBounds(180,270,250,25);
        add(sourceField);

        JLabel destinationLabel = new JLabel("To");
        destinationLabel.setBounds(40,310,120,25);
        add(destinationLabel);

         destinationField = new JTextField();
        destinationField.setBounds(180,310,250,25);
        add(destinationField);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBounds(180,370,150,35);
        add(bookButton);
        setVisible(true);
        //Booking
        bookButton.addActionListener(e -> {
              if(passengerField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(
                this,
                "Passenger Name is required.");
        passengerField.requestFocus();
        return;
    }
    // Train Number
    if(trainNoField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(
                this,
                "Train Number is required.");
        trainNoField.requestFocus();
        return;
    }
    // Train Name
    if(trainNameField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid Train Number.");
        trainNoField.requestFocus();
        return;
    }
    // Journey Date
    if(dateField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(
                this,
                "Journey Date is required.");
        dateField.requestFocus();
        return;
    }
    // Source
    if(sourceField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(
                this,
                "Source Station is required.");
        sourceField.requestFocus();
        return;
   }

    // Destination
    if(destinationField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(
                this,
                "Destination Station is required.");
        destinationField.requestFocus();
        return;
    }
    // Source != Destination
    if(sourceField.getText().trim()
            .equalsIgnoreCase(destinationField.getText().trim())){
        JOptionPane.showMessageDialog(
                this,
                "Source and Destination cannot be the same.");
        destinationField.requestFocus();
        return;
    }
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

            String message =
        "Booking Successful!\n\n"
        + "PNR : " + reservation.getPnr()
        + "\nPassenger : " + reservation.getPassengerName()
        + "\nTrain No : " + reservation.getTrainNo()
        + "\nTrain Name : " + reservation.getTrainName()
        + "\nClass : " + reservation.getClassType()
        + "\nJourney Date : " + reservation.getJourneyDate()
        + "\nFrom : " + reservation.getSource()
        + "\nTo : " + reservation.getDestination();
        clearForm();

JOptionPane.showMessageDialog(
        this,
        message,
        "Reservation Successful",
        JOptionPane.INFORMATION_MESSAGE);
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
    private void clearForm(){

    passengerField.setText("");

    trainNoField.setText("");

    trainNameField.setText("");

    dateField.setText("");

    sourceField.setText("");

    destinationField.setText("");

    classBox.setSelectedIndex(0);

    passengerField.requestFocus();

}

}

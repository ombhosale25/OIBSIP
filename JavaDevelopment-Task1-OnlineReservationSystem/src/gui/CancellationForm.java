package gui;

import javax.swing.*;
import dao.ReservationDAO;
import model.Reservation;

public class CancellationForm extends JFrame {

    JTextField pnrField;

    JTextArea detailsArea;

    JButton fetchButton;

    JButton cancelButton;

    public CancellationForm() {

        setTitle("Cancel Reservation");

        setSize(500,450);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        JLabel title = new JLabel("Cancellation Form");
        title.setBounds(170,20,180,30);
        add(title);

        JLabel pnrLabel = new JLabel("Enter PNR");
        pnrLabel.setBounds(40,70,100,25);
        add(pnrLabel);

        pnrField = new JTextField();
        pnrField.setBounds(140,70,220,25);
        add(pnrField);

        fetchButton = new JButton("Fetch");
        fetchButton.setBounds(370,70,80,25);
        add(fetchButton);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(detailsArea);

        scroll.setBounds(40,120,410,180);

        add(scroll);

        cancelButton = new JButton("Cancel Ticket");

        cancelButton.setBounds(160,330,150,35);

        add(cancelButton);

        setVisible(true);

        fetchButton.addActionListener(e -> {

    String pnr = pnrField.getText().trim();

    if (pnr.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a PNR.");

        return;

    }

    ReservationDAO dao = new ReservationDAO();

    Reservation reservation =
            dao.getReservationByPNR(pnr);

    if (reservation != null) {

        detailsArea.setText(

                "Passenger : " + reservation.getPassengerName()

                + "\nPNR : " + reservation.getPnr()

                + "\nTrain No : " + reservation.getTrainNo()

                + "\nTrain Name : " + reservation.getTrainName()

                + "\nClass : " + reservation.getClassType()

                + "\nJourney Date : " + reservation.getJourneyDate()

                + "\nSource : " + reservation.getSource()

                + "\nDestination : " + reservation.getDestination()

        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Reservation not found.");

        detailsArea.setText("");

    }

});

cancelButton.addActionListener(e -> {

    String pnr = pnrField.getText().trim();

    if (pnr.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please fetch a reservation first.");

        return;

    }

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel this ticket?",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {

        ReservationDAO dao = new ReservationDAO();

        if (dao.cancelReservation(pnr)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ticket cancelled successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            pnrField.setText("");
            detailsArea.setText("");
            pnrField.requestFocus();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancellation failed.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

});

    }

}
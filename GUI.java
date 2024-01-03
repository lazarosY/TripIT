import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Information Input");
        JPanel inputPanel = new JPanel();
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel destination = new JLabel("Destination");
        destination.setBounds(10, 20, 80, 25);
        inputPanel.add(destination);
        JTextField destinationField = new JTextField(15);
        destinationField.setBounds(100, 20, 165, 25);
        inputPanel.add(destinationField);

        JLabel startdate = new JLabel("Starting date");
        startdate.setBounds(10, 50, 80, 25);
        inputPanel.add(startdate);
        JTextField startdateField = new JTextField(15);
        startdateField.setBounds(100, 50, 165, 25);
        inputPanel.add(startdateField);

        JLabel enddate = new JLabel("Ending date");
        enddate.setBounds(10, 80, 80, 25);
        inputPanel.add(enddate);
        JTextField enddateField = new JTextField(15);
        enddateField.setBounds(100, 80, 165, 25);
        inputPanel.add(enddateField);

        JLabel budget = new JLabel("Budget");
        budget.setBounds(10, 110, 80, 25);
        inputPanel.add(budget);
        JTextField budgetField = new JTextField(15);
        budgetField.setBounds(100, 110, 165, 25);
        inputPanel.add(budgetField);

        JLabel interests = new JLabel("Interests");
        interests.setBounds(10, 140, 80, 25);
        inputPanel.add(interests);
        JTextField interestsField = new JTextField(15);
        interestsField.setBounds(100, 140, 165, 25);
        inputPanel.add(interestsField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 170, 80, 25);
        submitButton.addActionListener(new GUI()); // Associate ActionListener
        inputPanel.add(submitButton);

        frame.add(inputPanel);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Submit successful");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDestinationApp {

    private JFrame frame;

    
    private JTextField destinationField;
    private JTextField budgetField;
    private JTextField interestsField;
    private JFormattedTextField startDateField;
    private JFormattedTextField endDateField;
    private JButton submitButton;
    private JTextArea destinationArea;

    public UserDestinationApp() {
        
        frame = new JFrame("User Destinations");
        frame.setSize(400, 400); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        destinationField = new JTextField(15);
        budgetField = new JTextField(15);
        interestsField = new JTextField(15);
        submitButton = new JButton("Submit");

     
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        startDateField = new JFormattedTextField(dateFormat);
        endDateField = new JFormattedTextField(dateFormat);
        startDateField.setColumns(10);
        endDateField.setColumns(10);

        
        destinationArea = new JTextArea(10, 30);
        destinationArea.setEditable(false); 

 
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Destination:"));
        inputPanel.add(destinationField);
        inputPanel.add(new JLabel("Budget:"));
        inputPanel.add(budgetField);
        inputPanel.add(new JLabel("Interests:"));
        inputPanel.add(interestsField);
        inputPanel.add(new JLabel("Start Date (yyyy-mm-dd):"));
        inputPanel.add(startDateField);
        inputPanel.add(new JLabel("End Date (yyyy-mm-dd):"));
        inputPanel.add(endDateField);
        inputPanel.add(submitButton);

        
        JPanel displayPanel = new JPanel();
        displayPanel.add(new JScrollPane(destinationArea));

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(displayPanel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchAndDisplayDestinations();
            }
        });

        
        frame.setVisible(true);
    }

    private void fetchAndDisplayDestinations() {
        
        String destination = destinationField.getText();
        String budget = budgetField.getText();
        String interests = interestsField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
    
 
        try {
            String fetchedData = fetchDataFromDatabase(destination, budget, interests, startDate, endDate);
    
            destinationArea.setText(fetchedData);
        } catch (Exception e) {
           
            destinationArea.setText("Error fetching data: " + e.getMessage());
        }
    }
    
   
    private String fetchDataFromDatabase(String destination, String budget, String interests, String startDate, String endDate) {
     
        return "Destinations for " + destination + " from " + startDate + " to " + endDate + ": [Destination Data Here]";
    }
    

    public static void main(String[] args) {
        new UserDestinationApp();
    }
}


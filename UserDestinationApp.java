import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserDestinationApp {

    private JFrame frame;

    // UI 
    private JTextField destinationField;
    private JTextField budgetField;
    private JTextField interestsField;
    private JButton submitButton;

    // UI για εμφάνιση προορισμών
    private JTextArea destinationArea;

    public UserDestinationApp() {
        // Ορισμός frame
        frame = new JFrame("User Destinations");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Στοιχεία διεπαφής χρήστη για εισαγωγή
        destinationField = new JTextField(15);
        budgetField = new JTextField(15);
        interestsField = new JTextField(15);
        submitButton = new JButton("Submit");

        // Στοιχεία διεπαφής χρήστη για  εμφάνιση προορισμών
        destinationArea = new JTextArea(10, 30);
        destinationArea.setEditable(false); 

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Destination:"));
        inputPanel.add(destinationField);
        inputPanel.add(new JLabel("Budget:"));
        inputPanel.add(budgetField);
        inputPanel.add(new JLabel("Interests:"));
        inputPanel.add(interestsField);
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

        // Εμφάνιση πλαισίου
        frame.setVisible(true);
    }

    private void fetchAndDisplayDestinations() {
       /* Εφαρμογή για την ανάκτηση προορισμών από το chatGPT με βάση τα στοιχεία του χρήστη 
       (προορισμός, προϋπολογισμός, ενδιαφέροντα) 
       και θα τα εμφανίζει στην περιοχή προορισμού*/ 
    }

    public static void main(String[] args) {
        new UserDestinationApp();
    }
}
/*Περιγραφή κλάσης :
Ο χρήστης εισάγει τον επιθυμητό προορισμό, τον προϋπολογισμό και τα ενδιαφέροντά του στα πεδία κειμένου.
Κάνοντας κλικ στο κουμπί "Υποβολή", ενεργοποιείται η μέθοδος fetchAndDisplayDestinations(). 
Αυτή η μέθοδος θα πρέπει να περιέχει τη λογική για να ρωτήσετε το chatGPT για να επιστραφούν δεδομένα προορισμού με βάση τις εισόδους του χρήστη.
Στη συνέχεια, τα αποτελέσματα εμφανίζονται σε ένα JTextArea.
    Για να τρέξει σωστά ο κώδικας πρέπει να :
        1)Εφαρμόσουμε τη μέθοδο fetchAndDisplayDestinations() για να συνδεθούμε με τη βάση δεδομένων μας και να πάρουμε τα αποτελέσματα.
        2)Τσεκ εξαιρέσεις και πιθανά error
*/
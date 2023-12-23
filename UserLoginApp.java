import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
//Αφού προσθετεί στη βιβλιοθήκη το BCrypt , ο μεταγλωττιστής θα μπορεί να χρησιμοποιησει τη κλάση BCrypt και θα φύγει το error " BCrypt cannot be resolved"
import org.mindrot.jbcrypt.BCrypt;


public class UserLoginApp {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public UserLoginApp() {

// Δημιουργία του Frame
        frame = new JFrame("User Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Δημιουργία στοιχείων διεπαφής χρήστη
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");


        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);

        frame.add(panel);

       loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

// Εμφάνιση πλαισίου
        frame.setVisible(true);
    }

    private void login() {
/*Σύνδεση με επαλήθευση απο τη βάση δεδομένων
    παράδειγμα πως θα μπορούσε να είναι αυτή η μέθοδος
        Στο pom.xml αρχείο προσθήκη αυτής της εξαρτησίας:<dependency> <groupId>org.mindrot</groupId><artifactId>jbcrypt</artifactId> <version>0.4</version> </dependency> */
            String username = usernameField.getText();
            String enteredPassword = new String(passwordField.getPassword());
            String sql = "SELECT password FROM users WHERE username = ?";
        
            try (Connection conn = connectToDatabase();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
                pstmt.setString(1, username);
        
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");
                        
                        // BCrypt για κωδικό
                        if (BCrypt.checkpw(enteredPassword, storedPassword)) {
                            // Passwords match
                            JOptionPane.showMessageDialog(frame, "Login successful!");
                        } else {
                            // Κωδικοί δεν αντιστοιχίζονται
                            JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                        }
                    } else {
                        // Username δεν υπάρχει
                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Σφάλματα βάσης δεδομένων
                JOptionPane.showMessageDialog(frame, "Error connecting to the database.");
            }
        }
        
    

    private void signUp() {
// Εγγραφή και αποθήκευση στοιχείων στη βάση δεδομένων
    

String username = usernameField.getText();
    String password = new String(passwordField.getPassword());

// Έλεγχος να μην ειναι κενό τα στοιχεία
    if(username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Username and password cannot be empty.");
        return;
    }

// Hash των κωδικό για ασφάλεια
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

// SQL query για εισαγωγή δεδομένων νέου χρήστη
    String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

    try (Connection conn = connectToDatabase();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, username);
        pstmt.setString(2, hashedPassword);

        int affectedRows = pstmt.executeUpdate();

// Έλεγχος αν η εισαγωγή ήταν επιτυχημένη
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(frame, "Signup successful!");
        } else {
// Σε περίπτωση που υπάρξει θέμα με το SQL query εκτελείτε αυτό το else
            JOptionPane.showMessageDialog(frame, "Signup failed. Please try again.");
        }
    } catch (SQLException e) {
        if(e.getErrorCode() == java.sql.SQLException.DUPLICATE_KEY) {
// Διαχείριση εξαίρεσης σε περίπτωση που το Username υπάρχει ήδη
            JOptionPane.showMessageDialog(frame, "Username already taken. Please choose a different one.");
        } else {
            e.printStackTrace();
// Διαχείρηση άλλων SQL εξαιρέσεων
            JOptionPane.showMessageDialog(frame, "Error connecting to the database.");
        }
    } catch (Exception e) {
        e.printStackTrace();
// Άλλες εξαιρέσεις
        JOptionPane.showMessageDialog(frame, "An error occurred.");
    }


    }

    
//Είναι μια ιδιωτική μέθοδος που δημιουργεί και επιστρέφει μια σύνδεση στη βάση δεδομένων SQL    
    private Connection connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/yourDatabaseName"; // Αντικαταστήστε τη διεύθυνση URL και το όνομα της βάσης δεδομένων σας
        String user = "yourUsername"; // Αντικαταστήστε με το όνομα χρήστη της βάσης δεδομένων σας
        String password = "yourPassword"; // Αντικαταστήστε με τον κωδικό πρόσβασης της βάσης δεδομένων σας

        try {
            return DriverManager.getConnection(url, user, password);
        }   catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public static void main(String[] args) {
        new UserLoginApp();
    }
}



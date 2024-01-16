# TripIt

## Overview

Η εφαρμογή TripIt , βοηθάει τον χρήστη να προγραμματίσει τις διακοπές του στο μέρος επιλογής του μέσω λίγων απλών ερωτήσεων.

## Πίνακας Περιεχομένων

- [Οδηγίες μεταγλώτισης Προγράματος](#οδηγίες-μεταγλώτισσης-προγράμαμτος)
- [Οδηγίες εκτέλεσης του προγράμματος](#οδηγίες-εκτέλεσης-του-προγράμματος)
- [Οδηγίες χρήσης](#Οδηγίες-χρήσης)
- [Δομή περιεχομένων αποθετηρίου](#δομή-περιεχομένων-αποθετηρίου)
- [Διάγραμμα UML](#διάγραμμα-uml)
- [Δομές Δεδομένων και αλγόριθμοι](#δομές-δεδομένων-και-αλγόριθμοι)
- [API KEYS](#api-keys)

## Οδηγίες μεταγλώτισης Προγράματος

Παίρνουμε όλα τα αρχεία που περιέχονται στο GitHub και τα τοποθετούμε σε έναν φάκελο στον υπολογιστή μας. Από το cmd κάνετε cd μέσα στο directory όπου βρίσκεται ο φάκελος αυτός, γράφετε την εντολή mvn clean package. Ύστερα κάνετε cd στον φάκελο target εντός του tripit folder και μετακινείτε σε εκείνο τον φάκελο και το αρχείο config. ΠΡΟΣΟΧΗ: Στο αρχείο config πρέπει να συμπληρώσετε τις σωστές τιμές πρώτα, αντικαθιστώντας τα πεδια που αναγράφονται.


##  Οδηγίες εκτέλεσης του προγράμματος

### Δημιουργία MySQL Server:

Download MySQL
The simplest and recommended method is to download MySQL Installer for Windows from https://dev.mysql.com/downloads/installer/ and execute it.

MySQL Installer 8.0.xx
Select mysql-installer-web-community-8.0.xx.msi if you have good internet connection, otherwise choose mysql-installer-community-8.0.xx.msi.

Install MySQL
After downloading, double click the MSI installer .exe file.

Then follow the steps below:

1. "Choosing a Setup Type" screen: Choose "Full" setup type. This installs all MySQL products and features. Then click the "Next" button to continue.

2. "Check Requirements" screen: The installer checks if your pc has the requirements needed. If there is some failing requirements, click on each item to try to resolve them by clicking on the Execute button that will install all requirements automatically. Click "Next".

3. "Installation" screen: See what products will be installed. Click "Execute" to download and install the Products. After finishing the installation, click "Next".

4. "Product Configuration" screen: See what products that will be configured. Click the "MySQL Server 8.0.xx" option to configure the MySQL Server. Click the "Next" button. In page  "Type and Networking" set Config Type to "Development Computer" and "Connectivity" to "TCP/IP" and "Port" to "3006". Then, click the "Next" button.

5. "Authentication Method" screen: Choose "Use Strong Password Encryption for Authentication". Click "Next".

6. "Accounts and Roles" screen: Set a password for the root account. Click "Next". (Do not crete a new user)

7. "Windows Service" screen: Here, you configure the Windows Service to start the server. Keep the default setup, then click "Next".

8. "Apply Configuration" screen: Click the "Execute" button to apply the Server configuration. After finishing, click the "Finish" button.

9. "Product Configuration" screen: See that the Product Configuration is completed. Keep the default setting and click on the "Next" and "Finish" button to complete the MySQL package installation.

10. In the next screen, you can choose to configure the Router. Click on "Next", "Finish" and then click the "Next" button.

11. "Connect To Server" screen: Type in the root password (from step 6). Click the "Check" button to check if the connection is successful or not. Click on the "Next" button.

12. "Apply Configuration" screen: Select the options and click the "Execute" button. After finishing, click the "Finish" button.

13. "Installation Complete" screen: The installation is complete. Click the "Finish" button.


### Εκτέλεση προγράμματος


Kάνετε cd από το command line στον φάκελο target εντός του tripit folder και μετακινείτε σε εκείνο τον φάκελο και το αρχείο config. ΠΡΟΣΟΧΗ: Στο αρχείο config πρέπει να συμπληρώσετε τις σωστές τιμές πρώτα, αντικαθιστώντας τα πεδια που αναγράφονται. Στο command line εκτελείτε την εντολή java -jar tripit-1.0-shaded.jar


## Οδηγίες χρήσης 

Ο Χρήστης απαιτείται να βάζει τα inputs στο cmd ανάλογα με τις ερωτήσεις που του τίθονται από το πρόγραμμα. Σε περίπτωση εσφαλμένων απαντήσεων το πρόγραμμα θα κατευθύνει τον χρήστη για την εισαγωγή σωστών δεδομένων. 


## Δομή περιεχομένων αποθετηρίου

Κάθε branch, ανήκει σε ένα διαφορετικό μέλος της ομάδας. Το main branch, ανήκει στον αρχηγό. Εφόσον ο κώδικας λειτουργεί και θεωρείται σωστός, γίνεται push στο branch του κάθε μέλους. Πέρα από κύριες κλάσεις και μεθόδους που θα προστεθούν, πολλά repositories περιέχουν fixes σε υπάρχον κώδικα, bug fixes και προσθήκες στον κώδικα που αξιοποιείται ήδη. Πολλές κλάσεις και μέθοδοι που βρίσκονται στο αποθετήριο έμειναν αχρησιμοποίητες, είτε γιατί δεν ταίριαζαν με την τελική εικόνα του προγράμματος, είτε επειδή ήταν features τα οποία δεν μπορούσαμε να συμπεριλάβουμε εγκαίρως ή δεν καταφέραμε να τα κάνουμε να δουλέψουν όπως θέλαμε. Σημειώνεται ότι λόγω δυσκολίας ορισμένων μελών να κατανοήσουν την λειτουργία του GitHub υπήρξε συνδιαλλαγή κώδικα και με άλλους τρόπους.


## Διάγραμμα UML

Το παρακάτω διάγραμμα UML παρουσιάζει με αφαιρετικότητα την λειτουργία του προγράμματος και δεν περιλαμβάνει όλες τις κλάσεις και μεθόδους του κώδικα.

![alt text](https://github.com/lazarosY/TripIT/blob/main/UML.png?raw=true)


## Δομές Δεδομένων και αλγόριθμοι

Αξιοποίηση hashing algorithm, για την αποκρυπτογράφυση των κωδικών χρηστών.

Αποθήκευση κωδικού και email στο SQL database.


## API KEYS 

Σε περίπωτση που δεν διαθέτετε API keys για την openai και τα google places api keys σας παρέχουμε τα παρακάτω:
(Μέχρι την χρησιμποιήση των κλειδιών λόγω παραβίασης των κανόνων του GitHub μπορεί να έχουν καταργηθεί)

open ai key1: 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

open ai key 2: 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

google places api key: AIzaSyBz32QZHuvccsYTCsAe9Wxd0RCWO2hXDas


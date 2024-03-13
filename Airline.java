import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;

/**
 * the {@code Airline} class creates a comprehensive user interface for managing flight bookings
 * includes functionality for searching flights, booking management, user settings, and support
 * integrates various Swing components to create a dynamic and interactive application
 */
public class Airline implements ActionListener {
    JFrame frame = new JFrame();

    JLabel titleLabel = new JLabel("Flight Booking");
    JLabel usernameLabel;
    JLabel passwordLabel;

    JLabel userinprofilnamedhange = new JLabel();

    JLabel usserID;

    String imagePath1 = "";
    String imagePath2 = "";

    // profile 
    JButton settingsButton = new JButton("settings"); 
    JLabel pofile = new JLabel("Profile");
    JLabel nameProfileLableJLabel = new JLabel();
    JLabel passwortPofiJLabel = new JLabel();
    JButton changeNameProfilButton = new JButton("Change Name");
    JButton changePasswordProfilButton = new JButton("Change Password");
    JLabel newNameProfilJLabel = new JLabel("Change Name");
    JLabel newPasswordProfilJLabel = new JLabel("Change Password");
    JButton accteptProfiJLabel = new JButton("Accept");
    JButton accteptPasswordJLabel  = new JButton( "Accept" );
    JButton backToTJButton  = new JButton("Cancel");
    JButton loggoutButton = new JButton("Log out");

    JLabel topusername = new JLabel("User: ");
    JLabel topusermoney  = new JLabel("Money: ∞");

    JLabel Information = new JLabel("Information about flight");
    
    
    JPasswordField passworField  = new JPasswordField();

    JLabel messagLabel = new JLabel();

    // J for change name
    JLabel newName = new JLabel("New Name: ");
    JTextField textFieldNewName = new JTextField();

    // J for  Change Passowrd
    JPasswordField  oldPassWord = new JPasswordField();
    JPasswordField newPassWord = new JPasswordField();
    JPasswordField newPassWord2 = new JPasswordField();
    JLabel newPassWord_L = new JLabel("New  Password: ");
    JLabel reTypeNewPassWord_L = new JLabel("Retype New Password: ");
    JLabel oldPasswordJLabel = new JLabel("Old Password: ");


    // search
    JTextField searchField = new JTextField();
    JButton searchButton = new JButton();
    JTextArea searchResultsArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(searchResultsArea);

    // search place
    JScrollPane searchResultsScrollPane = new JScrollPane();
    JPanel searchResultsPanel = new JPanel();
    JButton scrollUpButton = new JButton("▲");
    JButton scrollDownButton = new JButton("▼");

    // settings
    JFrame settingsFrame = new JFrame("Settings");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    // for search 
    JRadioButton startairport = new JRadioButton("Start Airport", true);
    JRadioButton destination = new JRadioButton("Destination airport", false);
    ButtonGroup group = new ButtonGroup();

    JLabel airport_start = new JLabel("Start Airport: ");
    JLabel airport_destination = new JLabel("Destination A.: ");
    JLabel start_airport_at_the_moment = new JLabel("none");
    JLabel  dest_airport_at_the_moment = new JLabel("none");

    JLabel time_travel = new JLabel("Time of Travel: ");
    JLabel time_travel_time = new JLabel("none");

    JButton bookingButton = new JButton("Booking");

    JButton helpButton = new JButton("Help");

    
    /**
     * constructs a new {@code Airline} window with user information and initializes the GUI components
     * constructor sets up the interface for flight booking, including search functionality, user profile settings, and booking details
     *
     * @param username username of the user
     * @param userId user ID, used for identifying the user in the system
     * @param password user's password for authentication purposes
     */
    public Airline(String username,String userId, String password) {
        usernameLabel = new JLabel(username);
        passwordLabel = new JLabel(password);
        usserID = new JLabel(userId);

        userinprofilnamedhange.setText(username);

        usserID.setBounds(60,120,140,30);

        topusermoney.setBounds(10, 45, 200,  30);
        topusername.setBounds(10, 10, 40,  30);
        Information.setBounds(70, 200, 600, 60);

        frame.add(Information);
        frame.add(topusername);
        frame.add(topusermoney);

        titleLabel.setBounds(0, 50, 2000, 80);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));

        usernameLabel.setBounds(55, 10, 300,  30);
        passwordLabel.setBounds(60, 110, 140, 30);

        // layout Setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1400);
        frame.setLayout(null);

        // settings
        settingsButton.setBounds(1900,20,70,70);
        settingsButton.addActionListener(this);

        // messagLabel
        messagLabel.setBounds(20,300,600,30);

        // airport
        startairport.setBounds(600, 170, 150,  30);
        destination.setBounds(775, 170, 200, 30);
        group.add(startairport);
        group.add(destination);
        startairport.setVisible(true);
        startairport.setEnabled(true);

        destination.setVisible(true);
        destination.setEnabled(true);


        frame.add(startairport);
        frame.add(destination);

        // searchfield
        searchField.setBounds(600,200, 800, 40);

        searchButton.setBounds(1425, 200, 100, 40); 
        searchButton.setText("Search");
        searchButton.addActionListener(this);

        // searchfield con
        searchResultsScrollPane.setBounds(600, 250, 800, 500);
        searchResultsPanel.setLayout(new BoxLayout(searchResultsPanel, BoxLayout.Y_AXIS));
        searchResultsScrollPane.setViewportView(searchResultsPanel);
        frame.add(searchResultsScrollPane);

        // sc up and down
        scrollUpButton.setBounds(1410, 250, 40, 40);
        scrollUpButton.addActionListener(this);
        frame.add(scrollUpButton);

        scrollDownButton.setBounds(1410, 710, 40, 40);
        scrollDownButton.addActionListener(this);
        frame.add(scrollDownButton);


        frame.add(searchField);
        frame.add(searchButton);

        frame.add(titleLabel);
        frame.add(usernameLabel);

        frame.add(settingsButton);
        frame.setVisible(true);

        // profile head
        pofile.setBounds(200,40,200,30);
        pofile.setFont(new Font("Arial", Font.BOLD, 20));


        // chossen suff 
        airport_start.setBounds(50, 250, 100, 30);
        airport_destination.setBounds(50, 300, 100, 30);
        start_airport_at_the_moment.setBounds(200, 250, 500, 30);
        dest_airport_at_the_moment.setBounds(200, 300, 500, 30);

        time_travel.setBounds(50, 350, 100, 30);
        time_travel_time.setBounds(200, 350, 500, 30);
        frame.add(time_travel);
        frame.add(time_travel_time);

        // helper
        helpButton.setBounds(1900,100,70,70);
        frame.add(helpButton);

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This could have been a support tree with various options.", "Support Tree Placeholder", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(airport_start);
        frame.add(airport_destination);
        frame.add(start_airport_at_the_moment);
        frame.add(dest_airport_at_the_moment);

        bookingButton.setBounds(190, 400, 100, 30); 
        frame.add(bookingButton);
        bookingButton.addActionListener(this);

        bookingButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (!start_airport_at_the_moment.getText().isEmpty() && !dest_airport_at_the_moment.getText().isEmpty() && !start_airport_at_the_moment.getText().equals(dest_airport_at_the_moment.getText())){
                    openBookingWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select two different airports.", "Selection required", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton showBookingsButton = new JButton("Show Bookings");
        showBookingsButton.setBounds(30, 400, 150, 30);
        frame.add(showBookingsButton);
        showBookingsButton.addActionListener(e -> showBookings());

    }



    /**
     * searches the specified text file for flights matching the search term
     * this method returns a list of search results that contain the search term
     *
     * @param filePath path to the text file containing flight data
     * @param searchTerm term to search for within the flight data
     * @return list of strings representing the search results
     */
    public List<String> searchInTextFile(String filePath, String searchTerm) {
        List<String> results = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.toLowerCase().contains(searchTerm.toLowerCase())) {
                    String[] parts = line.split(";");
                    results.add(parts[0] + ": " + parts[1] + "  (" + parts[4] + ")");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
    
    private String selectedSearchResult_a = "";
    private String selectedSearchResult_b ="";

    /**
     * displays the search results in the user interface
     * this method updates the GUI to show search results, allowing the user to select from available options
     *
     * @param searchResults list of search results to display
     */
    public void displaySearchResults(List<String> searchResults) {
        searchResultsPanel.removeAll();

        for (String result : searchResults) {
            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel resultLabel = new JLabel(result);
            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (startairport.isSelected()) {
                        System.out.println(result);
                        if (!result.equals(selectedSearchResult_b)){
                            selectedSearchResult_a = result.split(":")[0].trim();
                            String displayName = result.split(":")[1].trim();
                            start_airport_at_the_moment.setText(displayName); 
                            settingsFrame.validate();
                            settingsFrame.repaint();
                        }
                    } else if (destination.isSelected()) {
                        if (!result.equals(selectedSearchResult_a)){
                            selectedSearchResult_b = result.split(":")[0].trim(); 
                            String displayName = result.split(":")[1].trim(); 
                            dest_airport_at_the_moment.setText(displayName);
                            settingsFrame.validate();
                            settingsFrame.repaint();
                        }
                        
                    }
                    if (!selectedSearchResult_a.isEmpty() && !selectedSearchResult_b.isEmpty()) {
                        calculateAndDisplayFlightTime();
                    }
                }

            });
            resultPanel.add(resultLabel);
            resultPanel.add(selectButton);
            searchResultsPanel.add(resultPanel);
        }

        searchResultsPanel.revalidate();
        searchResultsPanel.repaint();
    }

    /**
     * calculates and displays the flight time based on selected start and destination airports
     * this method uses the Haversine formula to calculate the distance between two points and estimates flight time
     */
    public void calculateAndDisplayFlightTime() {
        if (!selectedSearchResult_a.equals("") && !selectedSearchResult_b.equals("")) {
            String[] airportAData = searchInTextFileForAirport("data/data.txt", selectedSearchResult_a.split(":")[0].trim());
            String[] airportBData = searchInTextFileForAirport("data/data.txt", selectedSearchResult_b.split(":")[0].trim());
            
            if (airportAData != null && airportBData != null) {
                double lat1 = Double.parseDouble(airportAData[2]);
                double lon1 = Double.parseDouble(airportAData[3]);
                double lat2 = Double.parseDouble(airportBData[2]);
                double lon2 = Double.parseDouble(airportBData[3]);
                
                double distance = calculateHaversineDistance(lat1, lon1, lat2, lon2);
                
                double speed = 800.0;
                
                double flightTimeHours = distance / speed;
                
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        time_travel_time.setText(String.format("%.2f h", flightTimeHours));
                    }
                });
            }
        }
    }

    public String[] searchInTextFileForAirport(String filePath, String airportId) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (line.startsWith(airportId + ";")) {
                    return line.split(";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }



    /**
     * handles actions performed on the GUI components
     * this method responds to user interactions such as button clicks, enabling the functionality for searching, booking, and user settings adjustments
     *
     * @param e action event triggered by user interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton && !searchField.getText().trim().isEmpty()) {
            String searchTerm = searchField.getText();
            List<String> searchResults = searchInTextFile("data/data.txt", searchTerm);
            displaySearchResults(searchResults); 
        }
        if (e.getSource() == settingsButton) {
            showSettingsWindow();
        }
        if (e.getSource() == scrollUpButton) {
            JScrollBar vertical = searchResultsScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getValue() - 50);
        } else if (e.getSource() == scrollDownButton) {
            JScrollBar vertical = searchResultsScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getValue() + 50);
        }
    }


    public boolean changeUserNameInFileById(String userId, String newName, String filePath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        boolean newNameExists = lines.stream().anyMatch(line -> line.split(";")[0].equals(newName));
        if (newNameExists) {
            return false;
        }
        
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;
    
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length >= 3 && parts[1].equals(userId)) {
                updatedLines.add(newName + ";" + parts[1] + ";" + parts[2]);
                found = true;
            } else {
                updatedLines.add(line);
            }
        }
        
        if (!found) {
            return false;
        }
    
        try {
            Files.write(Paths.get(filePath), updatedLines);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public void showSettingsWindow() {

        settingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                settingsFrame.getContentPane().removeAll();
                e.getWindow().dispose();
            }
        });

        settingsFrame.setSize(500, 400);
        settingsFrame.setLayout(null);

        settingsFrame.add(pofile);
        messagLabel.setText("");
        settingsFrame.add(messagLabel);

        nameProfileLableJLabel.setText("Name: ");
        nameProfileLableJLabel.setBounds(50, 100, 75, 25);
        settingsFrame.add(nameProfileLableJLabel);

        passwortPofiJLabel.setText("Passwort: ");
        passwortPofiJLabel.setBounds(50, 150, 100, 25);
        settingsFrame.add(passwortPofiJLabel);

        //usernameLabel
        userinprofilnamedhange.setBounds(150, 100, 200, 25);
        settingsFrame.add(userinprofilnamedhange);

        // passwordLabel
        passwordLabel.setBounds(150, 150, 200, 25);
        settingsFrame.add(passwordLabel);

        // changeusernameBotton 
        changeNameProfilButton.setBounds(200, 100, 140, 25);
        settingsFrame.add(changeNameProfilButton);
        changeNameProfilButton.addActionListener(this);

        // changepasswordutton
        changePasswordProfilButton.setBounds(200, 150, 140, 25);
        settingsFrame.add(changePasswordProfilButton);
        changePasswordProfilButton.addActionListener(this);

        // change name
        newNameProfilJLabel.setBounds(200,40,600,30);
        newNameProfilJLabel.setFont(new Font("Arial", Font.BOLD, 20));

        textFieldNewName.setBounds(150, 150, 75, 25);

        newName.setBounds(50, 150, 150, 25);

        // acctept
        accteptProfiJLabel.setBounds(250,200,75,50);
        accteptProfiJLabel.addActionListener(this);
        accteptPasswordJLabel.setEnabled(true);

        // cancel
        backToTJButton.setBounds(150,200,75,50);
        backToTJButton.addActionListener(this);
        backToTJButton.setEnabled(true);

        // change  passwort
        newPasswordProfilJLabel.setBounds(200,40,600,30);
        newPasswordProfilJLabel.setFont(new Font("Arial", Font.BOLD, 20));

        //  oldPasswordJLabel
        oldPasswordJLabel.setBounds(50, 100, 150, 25);

        // oldPassWord
        oldPassWord.setBounds(200, 100, 150, 25);

        // newPassWord_L
        newPassWord_L.setBounds(50, 150, 150, 25);
        // reTypeNewPassWord_L
        reTypeNewPassWord_L.setBounds(50,200,150,25);

        // newPassWord
        newPassWord.setBounds(200,150,150,25);

        // newPassWord2
        newPassWord2.setBounds(200,200,150,25);

        // acctept new password 
        accteptPasswordJLabel.setBounds(250,250,75,50);
        accteptPasswordJLabel.addActionListener(this);

        loggoutButton.setBounds(200, 200, 140, 25);
        settingsFrame.add(loggoutButton);
        
        loggoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String javaCommand = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                    String classpath = System.getProperty("java.class.path");
                    String mainClass = "Main";
        
                    List<String> command = new ArrayList<>();
                    command.add(javaCommand);
                    command.add("--enable-preview");
                    command.add("-cp");
                    command.add(classpath);
                    command.add(mainClass);
        
                    ProcessBuilder processBuilder = new ProcessBuilder(command);
                    processBuilder.inheritIO();
                    processBuilder.start();
        
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        
        changeNameProfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == changeNameProfilButton) {
                    settingsFrame.getContentPane().removeAll();
                    settingsFrame.repaint();
                    settingsFrame.add(newNameProfilJLabel);
                    settingsFrame.add(nameProfileLableJLabel);
                    settingsFrame.add(userinprofilnamedhange);
                    settingsFrame.add(newName);
                    settingsFrame.add(textFieldNewName);  
                    settingsFrame.add(accteptProfiJLabel);
                    backToTJButton.setBounds(150,200,75,50);
                    settingsFrame.add(backToTJButton);
                    settingsFrame.validate();
                    settingsFrame.repaint();
                }
            }
        });

        changePasswordProfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if  (e.getSource() == changePasswordProfilButton){
                    settingsFrame.getContentPane().removeAll();
                    settingsFrame.repaint();
                    settingsFrame.add(newPasswordProfilJLabel); // top text
                    settingsFrame.add(oldPasswordJLabel);  // text
                    settingsFrame.add(newPassWord_L); // text
                    settingsFrame.add(reTypeNewPassWord_L); //text
                    settingsFrame.add(oldPassWord);  // old password field
                    settingsFrame.add(newPassWord);  // new password field
                    settingsFrame.add(newPassWord2); // new password field 2
                    settingsFrame.add(accteptPasswordJLabel);
                    backToTJButton.setBounds(150,250,75,50);
                    settingsFrame.add(backToTJButton);
                    settingsFrame.validate();
                    settingsFrame.repaint();

                }
            }
        });

        

        accteptPasswordJLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptToChangePassword();
            }
        });


        accteptProfiJLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptToChangeName();
            }
        });

        backToTJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldNewName.setText("");
                oldPassWord.setText("");
                newPassWord.setText("");
                newPassWord2.setText("");
                settingsFrame.getContentPane().removeAll();
                settingsFrame.dispose();
            }
        });
        

        settingsFrame.setVisible(true);

    }


    public void attemptToChangeName() {
        String newName = textFieldNewName.getText();
        String userId = usserID.getText();

        if (newName.trim().isEmpty()) {
            messagLabel.setText("Name cannot be empty.");
            settingsFrame.add(messagLabel);
            settingsFrame.revalidate();
            settingsFrame.repaint();
            return;
        }
        
        if (changeUserNameInFileById(userId, newName, "loginData.txt") && newName != "") {
            usernameLabel.setText(newName);
            userinprofilnamedhange.setText(newName);
            textFieldNewName.setText("");
            settingsFrame.getContentPane().removeAll();
            settingsFrame.dispose();
        } else {
            messagLabel.setText("This name is already used or update failed.");
            if (!settingsFrame.getContentPane().getComponents().equals(messagLabel)) {
                settingsFrame.add(messagLabel);
            }
            textFieldNewName.setText("");
            settingsFrame.revalidate();
            settingsFrame.repaint();
        }
    }
    
    
    

    public void attemptToChangePassword() {
        String UserID = usserID.getText();
        String oldPasswordInput = new String(oldPassWord.getPassword());
        String newPasswordInput = new String(newPassWord.getPassword());
        String confirmPasswordInput = new String(newPassWord2.getPassword());
    
        if (!newPasswordInput.equals(confirmPasswordInput)) {
            messagLabel.setText("New passwords do not match.");
            settingsFrame.add(messagLabel);
            settingsFrame.revalidate();
            settingsFrame.repaint();
            return;
        }
    
        if (changePasswordInFile(UserID, oldPasswordInput, newPasswordInput, "loginData.txt")) {
            passwordLabel.setText(newPasswordInput);
            oldPassWord.setText("");
            newPassWord.setText("");
            newPassWord2.setText("");
            settingsFrame.getContentPane().removeAll();
            settingsFrame.dispose();
        } else {
            messagLabel.setText("Old password is incorrect or update failed.");
            settingsFrame.add(messagLabel);
            oldPassWord.setText("");
            newPassWord.setText("");
            newPassWord2.setText("");
            settingsFrame.revalidate();
            settingsFrame.repaint();
        }
    }
    
    public boolean changePasswordInFile(String userId, String oldPassword, String newPassword, String filePath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        boolean updated = false;
        List<String> updatedLines = new ArrayList<>();
        
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts[1].equals(userId) && parts[2].equals(oldPassword)) { 
                updatedLines.add(parts[0] + ";" + userId + ";" + newPassword);
                updated = true;
            } else {
                updatedLines.add(line);
            }
        }
        
        if (updated) {
            try {
                Files.write(Paths.get(filePath), updatedLines);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    private void openBookingWindow() {
        if (!selectedSearchResult_a.isEmpty() && !selectedSearchResult_b.isEmpty() && !selectedSearchResult_a.equals(selectedSearchResult_b)) {
            String[] airportAData = searchInTextFileForAirport("data/data.txt", selectedSearchResult_a.split(":")[0].trim());
            String[] airportBData = searchInTextFileForAirport("data/data.txt", selectedSearchResult_b.split(":")[0].trim());
    
            if (airportAData != null && airportBData != null) {
                double lat1 = Double.parseDouble(airportAData[2]);
                double lon1 = Double.parseDouble(airportAData[3]);
                double lat2 = Double.parseDouble(airportBData[2]);
                double lon2 = Double.parseDouble(airportBData[3]);
    
                double distance = calculateHaversineDistance(lat1, lon1, lat2, lon2);
                double flightTimeHours = distance / 800.0; 
                double price = distance * 0.1;
    
                JFrame bookingFrame = new JFrame("Booking Details");
                bookingFrame.setSize(600, 400);
                bookingFrame.setLayout(null);
    
                // Use airport names instead of IDs
                JLabel fromLabel = new JLabel("From: " + airportAData[1]);
                JLabel toJLabel = new JLabel("To: " + airportBData[1]);
                JLabel distanceLabel = new JLabel(String.format("Distance: %.2f km", distance));
                JLabel timeLabel = new JLabel(String.format("Flight time: %.2f hours", flightTimeHours));
                JLabel priceLabel = new JLabel(String.format("Price: €%.2f", price));
                
                fromLabel.setBounds(50,50,400,25);
                toJLabel.setBounds(50,90,400,25);
                distanceLabel.setBounds(50,130,400,25);
                timeLabel.setBounds(50,170,400,25);
                priceLabel.setBounds(50,210,400,25);
    
                bookingFrame.add(fromLabel);
                bookingFrame.add(toJLabel);
                bookingFrame.add(distanceLabel);
                bookingFrame.add(timeLabel);
                bookingFrame.add(priceLabel);
    
                JButton saveDetailsButton = new JButton("Save Booking Details");
                saveDetailsButton.setBounds(150,300,300,30);
                bookingFrame.add(saveDetailsButton);

                JButton  cancelButton =new JButton ("Cancel");
                cancelButton.setBounds(50, 300, 100, 30);
                cancelButton.addActionListener(this);
                bookingFrame.add(cancelButton);
                
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bookingFrame.dispose();
                    }
                });
                
                saveDetailsButton.addActionListener(e -> {
                    new Thread(() -> {
                        saveBookingDetails(airportAData[1], airportBData[1], distance, flightTimeHours, price, () -> {
                            SwingUtilities.invokeLater(() -> bookingFrame.dispose());
                        });
                    }).start();
                });
                

                
                bookingFrame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select two different airports.", "Selection Required", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void saveBookingDetails(String startName, String destinationName, double distance, double time, double price, Runnable onSuccess) {
        String userId = usserID.getText();
        String filePath = generateFileName(userId);
        filePath = "air_data/" + filePath;
    
        String bookingDetails = String.format("From: %s, To: %s, Distance: %.2f km, Time: %.2f hours, Price: €%.2f",
                                              startName, destinationName, distance, time, price);
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(bookingDetails);
            writer.newLine();
            SwingUtilities.invokeLater(onSuccess);
            JOptionPane.showMessageDialog(null, "Booking details successfully saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving booking details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private String generateFileName(String userId) {
        return "booking_" + userId + ".txt";
    }
    
    private void showBookings() {
        JFrame bookingsFrame = new JFrame("Bookings");
        bookingsFrame.setSize(900, 400);
        bookingsFrame.setLayout(new BorderLayout());
    
        DefaultListModel<String> bookingsModel = new DefaultListModel<>();
        JList<String> bookingsList = new JList<>(bookingsModel);
        JScrollPane scrollPane = new JScrollPane(bookingsList);
        bookingsFrame.add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> bookingsFrame.dispose());
        buttonPanel.add(cancelButton);
    
        JButton removeButton = new JButton("Remove Selected Booking");
        removeButton.addActionListener(e -> removeSelectedBooking(bookingsList, bookingsModel));
        buttonPanel.add(removeButton);
    
        bookingsFrame.add(buttonPanel, BorderLayout.SOUTH);
    
        loadBookings(bookingsModel);
    
        bookingsFrame.setVisible(true);
    }
    
    private void loadBookings(DefaultListModel<String> bookingsModel) {
        String userId = usserID.getText();
        String filePath = "air_data/booking_" + userId + ".txt"; 
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookingsModel.addElement(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void removeSelectedBooking(JList<String> bookingsList, DefaultListModel<String> bookingsModel) {
        int selectedIndex = bookingsList.getSelectedIndex();
        if (selectedIndex != -1) {
            bookingsModel.remove(selectedIndex);
            saveUpdatedBookings(bookingsModel);
        }
    }
    
    private void saveUpdatedBookings(DefaultListModel<String> bookingsModel) {
        String userId = usserID.getText();
        String filePath = "air_data/booking_" + userId + ".txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < bookingsModel.getSize(); i++) {
                writer.write(bookingsModel.getElementAt(i));
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Booking successfully removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating booking details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * the entry point for the application
     * this main method initializes the {@code Airline} application with user provided arguments
     *
     * @param args command line arguments containing the username, user ID, and password
     */
    public static void main(String[] args) {
        if (args.length >= 3) {
            String username = args[0];
            String userId = args[1];
            String password = args[2];
            SwingUtilities.invokeLater(() -> new Airline(username,userId, password));
        }
    }
}

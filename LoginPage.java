import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




public class LoginPage implements ActionListener{

    private IDandPasswords idAndPasswords; // stores reference to the credentials storage
    boolean login = false; // tracks login status
    
    // swing components for the UI
    JFrame frame = new JFrame();
 
    JButton loginButton = new JButton("Login");
    JButton signupButton = new JButton( "Create Account" );
    JButton createNewAccountButton = new JButton("SignUp Page");
    JButton backToLoginButton = new JButton("Login Page");
    JButton resetButton = new JButton("Reset");
    JLabel titleJLabel = new JLabel();
    JButton logoutButton  = new JButton("Log Out");

    JTextField usernamefField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();

    JLabel messagLabel = new JLabel();
    JLabel userNameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");

    /**
     * constructs a new LoginPage instance
     * initializes the GUI components and sets up the action listeners for the buttons
     * the UI includes fields for username and password input, labels, and buttons for various actions
     *
     * @param idAndPasswords an instance of {@code IDandPasswords} used for verifying or registering user credentials
     */
    public LoginPage(IDandPasswords idAndPasswords) {
        this.idAndPasswords = idAndPasswords;

        userNameLabel.setBounds(50, 100, 75, 25);
        passwordLabel.setBounds(50, 150, 75, 25);

        titleJLabel.setText("Create Account");
        titleJLabel.setBounds(200, 40, 600, 35);
        titleJLabel.setFont(new Font("Arial", Font.BOLD, 20));

        messagLabel.setBounds(125, 250, 250, 35);
        messagLabel.setFont(new Font(null, Font.ITALIC,25));

        usernamefField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        signupButton.setBounds(125, 200, 100, 25);
        signupButton.addActionListener(this);
        signupButton.setFocusable(false);

        backToLoginButton.setBounds(325, 200, 150, 25);
        backToLoginButton.addActionListener(this);
        backToLoginButton.setFocusable(false);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        createNewAccountButton.setBounds(325, 200, 150, 25);
        createNewAccountButton.addActionListener(this);
        createNewAccountButton.setFocusable(false);

        logoutButton.setBounds(375, 0, 100, 25);
        logoutButton.addActionListener(this);
        logoutButton.setFocusable(false);

        frame.add(titleJLabel);
        frame.add(userNameLabel);
        frame.add(passwordLabel);
        frame.add(usernamefField);
        frame.add(userPasswordField);
        frame.add(messagLabel);
        frame.add(resetButton);
        frame.add(signupButton);
        frame.add(backToLoginButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setLayout(null);
        frame.setVisible(true);


    }
    public String getUserId(String username) {
        String filePath = "loginData.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";"); 
                if (parts.length > 2 && parts[0].equals(username)) { 
                    return parts[1]; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * handles actions performed on the GUI components, such as button clicks
     * method is called automatically whenever an action is performed on a component
     *
     * @param e action event that was triggered
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == resetButton) {
            usernamefField.setText("");
            userPasswordField.setText("");
        }

        if(e.getSource() == signupButton && !login) {
            String username = usernamefField.getText();
            String password = new String(userPasswordField.getPassword());
            if(username.trim().isEmpty() || password.trim().isEmpty()) {
                messagLabel.setText("Username and password cannot be empty!");
                usernamefField.setText("");
                userPasswordField.setText("");
            } else if (!idAndPasswords.getLoginInfo().containsKey(username)) {
                idAndPasswords.addNewUser(username, password);
                messagLabel.setText("User created!");
                login = true;
                frame.add(logoutButton);
                frame.validate();
                frame.repaint();
            } else {
                messagLabel.setText("Username is already taken!");
                usernamefField.setText("");
                userPasswordField.setText("");
            }
        }

        if(e.getSource() == loginButton && !login) {
            String username = usernamefField.getText();
            String password = new String(userPasswordField.getPassword());
            String storedData = idAndPasswords.getLoginInfo().get(username);
            if (storedData != null) {
                String[] parts = storedData.split(";");
                if (parts.length == 2 && parts[1].equals(password)) {
                    messagLabel.setForeground(Color.green);
                    messagLabel.setText("Login Successfully!");
                    login = true;
                    String userId = parts[0];
                } else {
                    messagLabel.setForeground(Color.red);
                    messagLabel.setText("Wrong Password!");
                    userPasswordField.setText("");
                }
            } else {
                messagLabel.setForeground(Color.red);
                messagLabel.setText("Username does not exist!");
                usernamefField.setText("");
                userPasswordField.setText("");
            }
        }
        
        if(e.getSource() == createNewAccountButton && login == false){
            frame.remove(loginButton);
            frame.add(signupButton);
        
            titleJLabel.setText("Create Account");
        
            frame.remove(createNewAccountButton);
            frame.add(backToLoginButton);
        
            frame.validate();
            frame.repaint();
        } else if (e.getSource() == createNewAccountButton  && login == true) {
            messagLabel.setText("You need to logout first!");
        }
        
        if(e.getSource() == backToLoginButton && login == false){
            frame.remove(signupButton);
            frame.add(loginButton);
        
            titleJLabel.setText("Login");
        
            frame.remove(backToLoginButton);
            frame.add(createNewAccountButton);
        
            frame.validate();
            frame.repaint();
        } else if (e.getSource() == backToLoginButton && login == true){
            messagLabel.setText("You need to logout first!");
        }
        

        if(e.getSource() == logoutButton && login == true){
            login = false;
            messagLabel.setForeground(Color.green);
            messagLabel.setText("Logged out successfully.");
            frame.remove(logoutButton);
            frame.validate();
            frame.repaint();
        }

        if (login == true){
            frame.getContentPane().removeAll();
            frame.repaint();
        
            new Thread(() -> {
                try {
                    String username = usernamefField.getText(); 
                    String userId = getUserId(username);
                    String password = new String(userPasswordField.getPassword()); 
            
                    String javaCommand = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                    String classpath = System.getProperty("java.class.path"); 
            
                    List<String> command = new ArrayList<>(Arrays.asList(
                    javaCommand,
                    "--enable-preview",
                    "-XX:+ShowCodeDetailsInExceptionMessages",
                    "-cp",
                    classpath,
                    "Airline",
                    username,
                    userId,
                    password
                ));
            
                    ProcessBuilder processBuilder = new ProcessBuilder(command);
                    processBuilder.inheritIO();
                    processBuilder.start();
            
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
            
            
        }
    }
}


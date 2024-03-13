/**
 * the {@code Main} class serves as the entry point for the application
 * initializes the necessary components for user authentication
 */
public class Main {
    /**
     * main method serves as the entry point of the application.
     * creates an instance of {@code IDandPasswords} to store user credentials
     * and uses it to initialize the {@code LoginPage} for user login
     *
     * @param args the command line arguments
     */
    public static void main(String[] args){

        // initializes a new IDandPasswords object to hold user IDs and passwords
        IDandPasswords idandPasswords = new IDandPasswords();

        // creates a login page, passing in the idandPasswords object for authentication
        LoginPage loginPage = new LoginPage(idandPasswords);

    }
}

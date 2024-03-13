import java.util.HashMap;
import java.util.Map;
import java.io.*;

/**
 * the {@code IDandPasswords} class manages the storage and retrieval of user login information
 * it supports operations such as loading existing login data from a file, adding new users,
 * and saving the updated login information back to the file
 * this class uses a {@code HashMap} to store the username as the key and a combination of user ID and password as the value
 */
public class IDandPasswords {
    private HashMap<String, String> loginInfo = new HashMap<>();
    private final String filePath = "loginData.txt";

    /**
     * constructs an {@code IDandPasswords} object by loading login information from the specified file
     */
    public IDandPasswords() {
        loadLoginInfoFromFile();
    }
    
    /**
     * loads login information from a file and stores it in the {@code loginInfo} map
     * each line of the file is expected to contain the username, user ID, and password, separated by semicolons
     * this method populates the {@code loginInfo} map with usernames as keys and a combination of user ID and password as values
     */
    private void loadLoginInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) { 
                    String userInfo = parts[1] + ";" + parts[2];
                    loginInfo.put(parts[0], userInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading login data.");
            e.printStackTrace();
        }
    }

    /**
     * adds a new user to the login information map and saves the updated map to the file
     * if the username does not already exist in the map, the method generates a new user ID, 
     * adds the new user,and then saves the updated login information to the file
     *
     * @param username username of the new user
     * @param password password for the new user
     */
    public void addNewUser(String username, String password) {
        if (!loginInfo.containsKey(username)) {
            int newUserId = loginInfo.size() + 1; 
            String userInfo = newUserId + ";" + password;
            loginInfo.put(username, userInfo);
            saveLoginInfoToFile();
        }
    }

    /**
     * saves the current state of the {@code loginInfo} map to the file
     * this method writes each entry in the map to the file, with each part of the entry (username, user ID, and password) separated by semicolons
     */
    private void saveLoginInfoToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            for (Map.Entry<String, String> entry : loginInfo.entrySet()) {
                String username = entry.getKey();
                String[] userInfo = entry.getValue().split(";");
                if (userInfo.length == 2) {
                    String userId = userInfo[0]; 
                    String password = userInfo[1];
                    writer.println(username + ";" + userId + ";" + password);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving login data.");
            e.printStackTrace();
        }
    }
    
    /**
     * returns the login information map containing all user credentials
     * 
     * @return A {@code HashMap} where each key is a username and each value is a combination of user ID and password
     */
    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
}

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class IDandPasswords {
    private HashMap<String, String> loginInfo = new HashMap<>();
    private final String filePath = "loginData.txt";

    public IDandPasswords() {
        loadLoginInfoFromFile();
    }
    
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

    public void addNewUser(String username, String password) {
        if (!loginInfo.containsKey(username)) {
            int newUserId = loginInfo.size() + 1; 
            String userInfo = newUserId + ";" + password;
            loginInfo.put(username, userInfo);
            saveLoginInfoToFile();
        }
    }

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
    
    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
}

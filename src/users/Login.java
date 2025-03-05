package users;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import tasks.Priority;

public class Login {

    String userFile;

    public Login(String userName, String password) {
        this.userFile = "_" + userName + ".txt";
        createFiles(userFile);
        boolean conditon = checkCredentials(userName, password);
        if (conditon) {
            System.out.println("\nWelcome " + userName + " !");
        } else {
            System.out.println("Invalid credentials. Try again !\nTerminating..");
            System.exit(1);
        }
    }

    boolean checkCredentials(String userName, String password) {
        try (FileReader fileReader = new FileReader(Registration.registrationInfoPath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] credStrings = line.split(" ");
                if (userName.equals(credStrings[0]) && password.equals(credStrings[1])) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.err.println(e
                    + "Unable to check credentials. The file is not found in the directory. Please check for any directory changes");
            return false;
        } catch (IOException e) {
            System.err
                    .println(e + "Unable to check credentials. The file may have been restricted access. Please check");
            return false;
        }
    }

    private void createFiles(String userFile) {
        for (Priority priority : Priority.values()) {

            File file = new File(priority.getFilePath() + userFile);
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + priority.getFilePath() + userFile);
                e.printStackTrace();
            }
        }
    }
}

package users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Registration {
    private final String userName;
    private final String password;
    static final String registrationInfoPath = "C:/Users/ASUS/Downloads/checkMate/resources/registrationInfo.txt";
    static final String passswordValidity = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    static final String userNameValidity = "^[a-z]+$";

    public Registration(String userName, String password) {
        createFile();
        this.userName = userName;
        checkForDuplicate(userName);
        this.password = password;
        saveCredentials();
    }

    boolean isStrongPassword(String password) {
        Pattern pattern = Pattern.compile(passswordValidity);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    boolean isValidUserName(String userName) {
        Pattern pattern = Pattern.compile(userNameValidity);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    void checkForDuplicate(String userName) {
        try (FileReader fileReader = new FileReader(registrationInfoPath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] checkStrings = line.split(" ");
                if (userName.equals(checkStrings[0])) {
                    System.out.println("\nUsername already taken. Please choose another username");
                    System.out.println("Application terminating..");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void createFile() {
        File file = new File(registrationInfoPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error occured to create registration database file: " + registrationInfoPath);
            e.printStackTrace();
        }
    }

    private void saveCredentials() {
        try (FileWriter fileWriter = new FileWriter(registrationInfoPath,
                true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if (isStrongPassword(password) && isValidUserName(userName)) {
                bufferedWriter.append(userName + " " + password);
                bufferedWriter.newLine();
                System.out.println("Registration successful !");
            } else {
                throw new IllegalArgumentException("Invalid user name or password format");
            }

        } catch (FileNotFoundException e) {
            System.err.println(e
                    + "Unable to save credentials. The file is not found in the directory. Please check for any directory changes");
            System.exit(1);
        } catch (IOException e) {
            System.err
                    .println(e + "Unable to save credentials. The file may have been restricted access. Please check");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println(e +
                    "\nUsername should contain only lower-case letters" +
                    "\nYour password should be: \n1.Atleast 8 characters long\n2.Combination of atleast 1 uppercase letter, 1 number, and no white-space characters");
            System.err.println("Registration failed. Try again!\nTerminating..");
            System.exit(1);
        }

    }

}

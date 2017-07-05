/*
 * This class contains all of the fields relating to backend procedures. 
 * This includes loading the user databas, validating and logging the user in
 * and out, and sending the correct user file.
 */
package finalproject;

/**
 *
 * @author John
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class users {
    private static String userName;
    private static int numberUsers;
    public static boolean userCorrect = false;
    private static String password;
    public static boolean passwordCorrect = false;
    private static String privilege;
    private static String[][] usersInfo = new String[20][20];
    //The user constructor, also loads the user database for later use.
    public users(){
        userName = null;
        password = null;
        privilege = null;
        
        //loading the userdatabase and gets the number of entrys in the database
        //from the top of the file.
        try{
            numberUsers = loadValidEmployees();
        }catch(IOException e){
            System.err.println(e);
        };
        
        
    }
    //the method that finds and loads the correct database
    private static int loadValidEmployees()throws IOException{
        FileInputStream employeesStream = new FileInputStream("employees.txt");
        Scanner employeeStreamer = new Scanner(employeesStream);
        int numUsers = employeeStreamer.nextInt(); //used throughout the program
                                                   //for a number of things
                                                   //found at the top of the file
        
        for (int i = 0; i <  numUsers; ++i){
            for (int j = 0; j <= 3; ++j){
                usersInfo[i][j] = employeeStreamer.next();
            }
            
        }
        
        employeesStream.close();
        return numUsers;
        
    }
    
    //checks the username and sees if it is a valid one, if it is it loads the 
    // rest of the user info and returns true
    public static boolean check_username(String user_input){
        for (int i = 0; i <  numberUsers; ++i){
            if (user_input.equals(usersInfo[i][0])){
                userName = user_input;
                userCorrect = true;
                
                password = usersInfo[i][2];
                password = password.replace("\"", "");
                password = password.replace("_", " ");
                privilege = usersInfo[i][3];
                
                return true;
            
            }
        }
        return false;
    }
    
    //checks the password, if it is correct returns true
    public static boolean check_password (String sentPassword){
        if (sentPassword.equals(password)){
            passwordCorrect = true;
            return true;
        }
        return false;
    }
    
    //used throughout the program to check security.
    public static boolean return_login_status(){
        if(passwordCorrect && userCorrect)
            return true;
        return false;
    }
    
    //loads the current users valid file "admin", "zookeeper", etc
    public static String load_user_data()throws IOException{
        String userData = "";
        
        if(return_login_status()){
            FileInputStream employeeFile = new FileInputStream(privilege + ".txt");
            Scanner userFile = new Scanner(employeeFile);
            while(userFile.hasNextLine())
                userData += (userFile.nextLine() + "\n");
            employeeFile.close();
        }
        
        
        return userData;
    }
    //logs the userout for security reasons, not used for much in this program.
    public static void logout(){
        userName = null;
        password = null;
        privilege = null;
        userCorrect = false;
        passwordCorrect = false;
    }
}


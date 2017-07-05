/*
 * This file contains all methods and filed relating to login and menues.
 * Made for future expandability and to easily allow future features. 
 */
package finalproject;

/**
 *
 * @author John
 */
import java.io.IOException;
import java.util.Scanner;
public class Menu {
    static users currentUser = new users(); //initialize the user class
    private static String loginMenuText ="@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                                         "   Welcome To the Zoo \n" +
                                         "       Employee\n" +
                                         "        Portal\n" +
                                         "\n" +
                                         "Please enter your Employee \n" +
                                         "    login Credentials\n" +
                                         "@@@@@@@@@@@@@@@@@@@@@@@@@@@";
    private static String enteredUsername;
    private static String enteredPassword;
    // The main login menu, this handles getting the user credntials from the 
    //user and sending them to the user class to. Could probably be split up
    // into more methods later on. 
    public static void login_menu(){
        int loginAttempt = 0;
        boolean correctUser = false;
        boolean correctPass = false;
        System.out.print(loginMenuText);
       
        do{
            ++loginAttempt;
            System.out.print("\nUsername: ");
            Scanner inputScanner = new Scanner(System.in);
            enteredUsername = inputScanner.nextLine();
            correctUser = currentUser.check_username(enteredUsername);
            System.out.print("\nPassword: ");
            enteredPassword = inputScanner.nextLine();
            correctPass = currentUser.check_password(enteredPassword);
        }while( (!correctUser || (!correctPass)) && loginAttempt < 3);
        if(currentUser.return_login_status()){
            System.out.print(print_userData() + "\n\n\nLogging out.");
            currentUser.logout();
        }
        else
            
            System.out.println("You have exceeded your designated amount of attempts"
                    + "exiting login, try again later");
        }
    //checks for correct security and prints user file.
    private static String print_userData(){
        String userData = "";
        if(currentUser.return_login_status()){
            try{
                userData = currentUser.load_user_data();
            }catch(IOException e){
               System.err.println(e);
            }    
        }
        return userData;
    }
}

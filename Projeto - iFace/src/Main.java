/* 

OBS: Alterar input da atualização do perfil;

*/


import java.util.*;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
        
        // Scanner class (input method)
        Scanner getOptions = new Scanner(System.in);
        Scanner getUserInfo = new Scanner(System.in);
        Console console = System.console();

        // Users list
        ArrayList <User> users_list = new ArrayList<User>();

        // Program main loop
        while(true){

            menu();

            // Choose menu option
            int option = getOptions.nextInt();

            if(option == 1){

                // Getting informations about user
                String login = new String();
                String password = new String();
                String nickname = new String();

                System.out.println("Please, insert your name: ");
                login = getUserInfo.nextLine();

                System.out.println("Please, insert your password: ");
                password = getUserInfo.next();

                System.out.println("Please, insert your nickname: ");
                nickname = getUserInfo.next();

                // Calling function to add user
                users_list.add(signUpUser(login, password, nickname));

                //Print all users
                for(int i = 0; i < users_list.size(); i++){

                    User user = users_list.get(i);

                    System.out.println("------------------");
                    System.out.println("User login: " + user.login);
                    System.out.println("User password: " + user.password);
                    System.out.println("User nickname: " + user.nickname);
                    System.out.println("------------------");

                }

            } else if(option == 2) {

                // Getting informations about user
                String address = new String();
                String sex;
                String occupation = new String();
                String nickname = new String();

                System.out.println("Please, insert your nickname: ");
                nickname = getUserInfo.next();

                //Searching for nickname
                for(int i = 0; i < users_list.size(); i++){

                    User getUser = users_list.get(i);

                    // User founded
                    if(getUser.nickname.equals(nickname)){

                        System.out.println("Founded!");

                        // Have a profile already
                        // Update
                        if(getUser.profile != null){

                            System.out.println("Please, insert your address: ");
                            address = getUserInfo.next();

                            System.out.println("Please, insert your occupation: ");
                            occupation = getUserInfo.next();

                            System.out.print("Please, insert your sex (M, F, O): \n");
                            sex = getUserInfo.next();
    
                            getUser.profile.updateProfile(nickname, address, sex, occupation);

                            System.out.println("Updated!");

                        } else { //Create a profile

                            address = console.readLine("Enter address: ");
                            sex = console.readLine("Enter sex: ");
                            occupation = console.readLine("Enter occupation: ");

                            getUser.profile = new Profile(nickname, address, sex, occupation);

                            System.out.println("Profile created!");

                        }

                    }

                }
                 
            } else if(option == 3){

            } else if(option == 4){
                
            } else if(option == 5){
                
            } else if(option == 6){
                
            } else if(option == 7){

                User user = users_list.get(0);
                user.showUserInformations();
                
            } else if(option == 8){
                
            } else if(option == 9){
                
            } else if(option == 10){
                
            }

            //getScannerMethods.close();
            //getUserInfo.close();

        }

    }

    public static User signUpUser(String name, String password, String nickname){

        User newUser = new User(name, password, nickname);
        return newUser;
    }

    // Show menu before option selection
    public static void menu(){

        System.out.println("1. Sign up\n2. Create or edit profile\n3. Add friends\n4. Send messages\n5. Create group\n6. Add group members\n7. Recover users informations\n8. Remove account\n9. Send Feed messages\n10. Control Feed view");

    }

}
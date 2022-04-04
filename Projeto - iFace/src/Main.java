import java.util.*;
import java.io.Console;

public class Main {
    public static void main(String[] args) {

        // User is logged or not
        boolean isLogged = false;
        User userLogged = null;

        // Get user input
        Scanner getOptions = new Scanner(System.in);
        Console console = System.console(); 

        // Users list
        ArrayList <User> users_list = new ArrayList<User>();

        while(true){

            if(isLogged){

                // Logged menu
                menuLogged();

                int option = getOptions.nextInt();

                if(option == 1){//Create/Edit profile
                    
                    // Getting informations about user
                    String address = new String();
                    String sex;
                    String occupation = new String();
                    String nickname = new String();

                    if(userLogged.profile != null){

                        address = console.readLine("Please, insert your address: ");
                        occupation = console.readLine("Please, insert your occupation: ");
                        sex = console.readLine("Please, insert your sex (M, F, O): ");

                        userLogged.profile.updateProfile(nickname, address, sex, occupation);

                        System.out.println("Updated!");

                    } else { //Create a profile

                        address = console.readLine("Enter address: ");
                        sex = console.readLine("Enter sex: ");
                        occupation = console.readLine("Enter occupation: ");

                        userLogged.profile = new Profile(nickname, address, sex, occupation);

                        System.out.println("Profile created!");

                    }

                } else if(option == 2){//Add friends/See invites

                    System.out.println("1. See invites\n2. Add a friend");
                    
                    option = getOptions.nextInt();

                    if(option == 1){//See invites

                        userLogged.seeFriendshipInvites();

                    } else if(option == 2) {//Add friend

                        String friendNickname = console.readLine("Insert user nickname: ");

                        if(searchUser(friendNickname, users_list)){

                            User invitedFriend = getUser(friendNickname, users_list);
                            invitedFriend.invites.add(userLogged);

                        } else {

                            System.out.println("There is no user with this nickname.");

                        }

                    } else {

                        System.out.println("Insert a valid option");

                    }

                } else if(option == 3){//Send message



                } else if(option == 4){ //Create group



                } else if(option == 5){//Add group members


 
                } else if(option == 6){ //Get user information

                    String userNickname = console.readLine("User nickname: ");

                    System.out.println("----------------------------------");

                    if(searchUser(userNickname, users_list)){

                        System.out.println("User founded!");

                        User user = getUser(userNickname, users_list);

                        if(user.profile != null){

                            user.showUserInformations();

                            //See friends
                            for(int i = 0; i < user.friends.size(); i++){

                                System.out.println(user.friends.get(i).nickname);

                            }

                        } else {
                            
                            System.out.println("This user doesn't have a profile.");

                        }

                    } else {

                        System.out.println("User not found!");

                    }

                    System.out.println("----------------------------------");

                } else if(option == 7){//Remove account

                    System.out.println("This action cannot be undone.");
                    users_list.remove(userLogged);
                    userLogged = null;
                    isLogged = !isLogged;

                } else if(option == 8){//Send message feed



                } else if(option == 9){//Control feed view



                } else if(option == 10){//Logout

                    System.out.println("Logging out!");
                    isLogged = !isLogged;
                    userLogged = null;

                } else {

                    System.out.println("Insert a valid option!");

                }
                
            } else {//USER IS NOT LOGGED

                menuNotLogged();

                int option = getOptions.nextInt();

                if(option == 1){

                    //Create user
                    System.out.println("You're creating a new account.");
                    String userName = console.readLine("User name: ");
                    String userPassword = console.readLine("User password: ");
                    String userNickname = console.readLine("User nickname: ");

                    // Verificar se existe um usuário com mesmo nickname
                    if(searchUser(userNickname, users_list)){

                        System.out.println("The user is already created. Please, check another nickname");

                    } else {

                        users_list.add(signUpUser(userName, userPassword, userNickname));
                        System.out.println("User created! Please, login for more options.");

                    }

                } else if(option == 2){

                    //Login
                    System.out.println("Insert your informations to sign in.");
                    String userPassword = console.readLine("User password: ");
                    String userNickname = console.readLine("User nickname: ");

                    //Check if user exists
                    if(searchUser(userNickname, users_list)){

                        //Validate login informations
                        if(validateLogin(userNickname, userPassword, users_list)){

                            isLogged = !isLogged;

                            for(int i = 0; i < users_list.size(); i++){

                                if(users_list.get(i).nickname.equals(userNickname)){

                                    userLogged = users_list.get(i);
                                    break;

                                }

                            }

                        } else {

                            System.out.println("Wrong password!");

                        }
 
                    } else {

                        System.out.println("This user doesn't exist. Try again or create a new account");

                    }

                } else if(option == 3){

                    System.out.println("Exit!");
                    break;

                } else {

                    System.out.println("Insert a valid option!");

                }

            }

        }

    }

    public static User signUpUser(String name, String password, String nickname){

        User newUser = new User(name, password, nickname);
        return newUser;
    }

    // Show menu before option selection
    public static void menuNotLogged(){

        // Sign up and login options
        System.out.println("1. Sign up\n2. Sign in\n3. Exit");

    }

    public static void menuLogged(){

        System.out.println("1. Create or edit profile\n2. Add friends\n3. Send messages\n4. Create group\n5. Add group members\n6. Recover users informations\n7. Remove account\n8. Send Feed messages\n9. Control Feed view\n10. Logout");

    }
    
    public static boolean searchUser(String nickname, ArrayList<User> user_list){

        for(int i = 0; i < user_list.size(); i++){

            String userName = user_list.get(i).nickname;

            if(userName.equals(nickname)){

                return true;

            }

        }

        return false;

    }

    public static boolean validateLogin(String userNickname, String userPassword, ArrayList<User> users_list){

        // Search for user and password
        for(int i = 0; i < users_list.size(); i++){

            User actualUser = users_list.get((i));

            if(userNickname.equals(actualUser.nickname)){

                if(userPassword.equals(actualUser.password)){

                    return true;

                } else {

                    return false;

                }

            }

        }

        return false;

    }

    public static User getUser(String userNickname, ArrayList<User> users_list){

        User user = null;

        for(int i = 0; i < users_list.size(); i++){

            user = users_list.get(i);

            if(user.nickname.equals(userNickname)){

                return user;

            }

        }

        return user;

    }

}
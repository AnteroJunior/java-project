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
        ArrayList <Group> comunity_groups = new ArrayList<Group>();

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

                    System.out.println("1. User\n2. Group\n3. User feed\n4. See my messages");
            
                    option = getOptions.nextInt();

                    if(option == 1){//Message to user

                        String destiny = console.readLine("User you want to send: ");

                        if(searchUser(destiny, users_list)){

                            String text = console.readLine("What's your message? ");

                            Message message = new Message(text, userLogged);

                            User destinyUser = getUser(destiny, users_list);

                            destinyUser.inbox.add(message);

                        } else {

                            System.out.println("User not found!");

                        }

                    } else if(option == 2){//Message to group

                        String destiny = console.readLine("Group you want to send: ");

                        Group group = null;
                        group = searchGroup(comunity_groups, destiny);

                        if(group != null){

                            String text = console.readLine("What's your message? ");

                            Message message = new Message(text, userLogged);

                            group.inbox.add(message);

                        } else {

                            System.out.println("Group not found!");

                        }

                    } else if(option == 3){//Message to user feed

                        String destiny = console.readLine("User you want to send: ");

                        if(searchUser(destiny, users_list)){

                            String text = console.readLine("Post something: ");

                            Message message = new Message(text, userLogged);

                            User destinyUser = getUser(destiny, users_list);

                            destinyUser.posts.add(0, message);

                        } else {

                            System.out.println("User not found!");

                        }                        

                    } else if(option == 4){

                        Message message;

                        for(int i = 0; i < userLogged.inbox.size(); i++){

                            System.out.println("You have " + userLogged.inbox.size() + " unread messages");

                            message = userLogged.inbox.get(i);

                            if(!message.read){

                                System.out.println("Author: " + message.author.nickname);
                                System.out.println("Message: " + message.message);
    
                                System.out.println();

                                userLogged.inbox.remove(message);//Message read

                            }

                        }

                    }

                } else if(option == 4){//Create/Add group

                    System.out.println("1. Send invite to a group\n2. Create a group");
                    option = getOptions.nextInt();

                    if(option == 1){//Send invite

                        String groupName;    
                        groupName = console.readLine("Please, what's the group name? ");

                        Group group = null;

                        group = searchGroup(comunity_groups, groupName);

                        if(group != null){

                            group.invite_members.add(userLogged);
                            group.groupInformations();

                        } else {

                            System.out.println("Group not found");

                        }

                    } else if(option == 2){//Create group
                        
                        String groupName;
                        String groupDescription;
    
                        groupName = console.readLine("Please, what's the group name? ");
                        groupDescription = console.readLine("Insert a good description of your group: ");
    
                        Group newGroup = null;

                        newGroup = searchGroup(comunity_groups, groupName);

                        if(newGroup != null){

                            System.out.println("This group is already created.");

                        } else {

                            newGroup = new Group(groupName, groupDescription, userLogged);

                            newGroup.members.add(userLogged);//New member
                            comunity_groups.add(newGroup);//New group to global list
                            newGroup.groupInformations();

                            System.out.println("Group created with success! Enjoy!");

                        }
                    
                    }

                } else if(option == 5){//Add group members

                    System.out.println("1. Add members\n2. Remove members");
                    option = getOptions.nextInt();

                    if(option == 1){//Add

                        String groupName = console.readLine("What group would you like do add members? ");

                        Group group = null;

                        group = searchGroup(comunity_groups, groupName);

                        //If found
                        if(group != null){

                            group.addMembers(userLogged);

                        } else {

                            System.out.println("Group not found.");

                        }

                    } else if(option == 2){//Remove

                        String groupName = console.readLine("What group would you like do remove members? ");

                        Group group = null;

                        group = searchGroup(comunity_groups, groupName);

                        //If found
                        if(group != null){

                            group.removeUser(userLogged);

                        } else {

                            System.out.println("Group not found.");

                        }                        

                    }
 
                } else if(option == 6){ //Get user information

                    String userNickname = console.readLine("User nickname: ");

                    if(searchUser(userNickname, users_list)){

                        System.out.println("User founded!");

                        User user = getUser(userNickname, users_list);

                        if(user.profile != null){

                            user.showUserInformations();

                            //See friends
                            /* for(int i = 0; i < user.friends.size(); i++){

                                System.out.println(user.friends.get(i).nickname);

                            } */

                        } else {
                            
                            System.out.println("This user doesn't have a profile.");

                        }

                    } else {

                        System.out.println("User not found!");

                    }

                } else if(option == 7){//Remove account

                    System.out.println("This action cannot be undone.");

                    //Friends
                    for(int i = 0; i < userLogged.friends.size(); i++){

                        User friend = userLogged.friends.get(i);
                        friend.friends.remove(userLogged);

                    }

                    //Groups
                    //All user groups
                    for(int i = 0; i < userLogged.groups.size(); i++){

                        Group group = userLogged.groups.get(i); //Group i

                        if(group.userOwner.nickname.equals(userLogged.nickname)){ //If user is the owner delete group and members

                            for(int j = 0; j < group.members.size(); j++){

                                User user = group.members.get(j);

                                user.groups.remove(group);

                            }

                            comunity_groups.remove(group);//Remove group

                        } else {
                            //If user is not the owner, just remove user from group
                            group.members.remove(userLogged); 

                        }

                    }
                    
                    users_list.remove(userLogged);



                    userLogged = null;
                    isLogged = !isLogged;

                } else if(option == 8){//See feed

                    String userNickname = console.readLine("User you want to see the feed: ");
                    
                    User user = getUser(userNickname, users_list);

                    user.showFeed(userLogged);

                } else if(option == 9){//Control feed view

                    userLogged.changeProfileView();

                } else if(option == 10){//See group messages

                    String groupName = console.readLine("Group name: ");

                    Group group = searchGroup(comunity_groups, groupName);

                    for(int i = 0; i < group.inbox.size(); i++){

                        System.out.println("Author: " + group.inbox.get(i).author.nickname);
                        System.out.println("Message: " + group.inbox.get(i).message);

                        System.out.println();

                    }

                } else if(option == 11) {//Logout
                    
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
        System.out.println("+----------------------------+");
        System.out.println("1. Sign up\n2. Sign in\n3. Exit");
        System.out.println("+----------------------------+");

    }

    public static void menuLogged(){

        System.out.println("+----------------------------+");
        System.out.println("MENU");
        System.out.println("1. Create or edit profile\n2. Add friends\n3. Send messages\n4. Create a  group or send invite\n5. Add/Remove group members\n6. Recover users informations\n7. Remove account\n8. See feed\n9. Control Feed view\n10. Infomations about groups\n11. Log out");

        System.out.println("+----------------------------+");

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

    public static Group searchGroup(ArrayList<Group> groups, String groupName){

        Group group = null;

        for(int i = 0; i < groups.size(); i++){

            if(groups.get(i).name.equals(groupName)){

                group = groups.get(i);

                return group;

            }

        }

        return group;

    }   

}
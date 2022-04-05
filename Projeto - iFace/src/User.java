import java.io.Console;
import java.util.ArrayList;

public class User {
    
    String login;
    String password;
    String nickname;
    Profile profile = null;
    boolean publicProfile = true;
    ArrayList<User> friends = new ArrayList<User>();
    ArrayList<User> invites = new ArrayList<User>();
    ArrayList<Group> groups = new ArrayList<Group>();
    ArrayList<Message> inbox = new ArrayList<Message>();
    ArrayList<Message> posts = new ArrayList<Message>();

    public User(String login, String password, String nickname){

        this.login = login;
        this.password = password;
        this.nickname = nickname;

    }

    public void showFeed(User user){

        if(publicProfile){

            System.out.println(this.nickname + " feed");

            for(int i = 0; i < this.posts.size(); i++){

                System.out.println("Author: " + this.posts.get(i).author.nickname);
                System.out.println("Message: " + this.posts.get(i).message);

                System.out.println();

            }

        } else {

            //Check if loggedUser is friend
            if(this.friends.contains(user) || this.nickname.equals(user.nickname)){

                for(int i = 0; i < this.posts.size(); i++){

                    System.out.println("Author: " + this.posts.get(i).author.nickname);
                    System.out.println("Message: " + this.posts.get(i).message);
    
                    System.out.println();
    
                }                

            } else {

                System.out.println("You cannot see this feed. First you should be friends.");

            }

        }

    }

    public void changeProfileView(){

        Console console = System.console();

        System.out.println("Your feed vilibility is: " + this.publicProfile);

        String option = console.readLine("Change vilibility to public (P) or private (O): ");
        if(option.equals("P")){

            this.publicProfile = true;

        } else if(option.equals("O")){

            this.publicProfile = false;

        }

    }

    public Profile createProfile(String nickname, String address, String sex, String occupation){

        Profile newProfile = new Profile(this.nickname, address, sex, occupation);

        return newProfile;

    }

    public void showUserInformations(){

        System.out.println("----------------------");
        System.out.println("Name: " + this.nickname + "\n" + "Address: " + this.profile.address + "\n" + "Sex: " + this.profile.sex + "\n" + "Occupation: " + this.profile.occupation + "\n" + "Friends: " + friends.size() + "\n");

        listUserGroups();
        System.out.println("----------------------");
    }

    public void listUserGroups(){

        System.out.println("Groups " + groups.size());

        for(int i = 0; i < groups.size(); i++){

            System.out.println("Name: " + groups.get(i).name);

        }

    }

    public void seeFriendshipInvites(){

        System.out.println("You have " + invites.size() + " invites.");

        Console console = System.console();

        for(int i = 0; i < invites.size(); i++){

            User userSendInvite = invites.get(i);

            System.out.println(userSendInvite.nickname + " wants to be your friend.");
            String answer = console.readLine("Do you accept?(Y/N) ");

            if(answer.equals("Y")){

                //Confirm invite
                /* 1. Remove invite from ArrayList; 2. Insert user at friends ArrayList */
                this.friends.add(userSendInvite);
                this.invites.remove(userSendInvite);

                userSendInvite.friends.add(this);

                System.out.println("Friend added!");

            } else if(answer.equals("N")) {

                System.out.println("Okay. Invite removed.");
                    
            }

        }

    }

}

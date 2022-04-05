import java.io.Console;
import java.util.ArrayList;

public class User {
    
    String login;
    String password;
    String nickname;
    Profile profile = null;
    ArrayList<User> friends = new ArrayList<User>();
    ArrayList<User> invites = new ArrayList<User>();
    ArrayList<Group> groups = new ArrayList<Group>();
    ArrayList<Message> inbox = new ArrayList<Message>();

    public User(String login, String password, String nickname){

        this.login = login;
        this.password = password;
        this.nickname = nickname;

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

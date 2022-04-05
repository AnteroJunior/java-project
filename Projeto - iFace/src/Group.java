import java.util.ArrayList;
import java.io.*;
public class Group {
    
    //Attributes
    String name;
    String description;
    User userOwner;
    ArrayList<User> members = new ArrayList<User>();
    ArrayList<User> invite_members = new ArrayList<User>();
    ArrayList<Message> inbox = new ArrayList<Message>();

    
    //Methods
    public Group(String name, String description, User userOwner){

        this.name = name;
        this.description = description;
        this.userOwner = userOwner;
        userOwner.groups.add(this);

    }

    public void addMembers(User userOwner){
        
        Console console = System.console();

        //If user is the owner
        if(userOwner.nickname.equals(this.userOwner.nickname)){

            //List of invite
            for(int i = 0; i < invite_members.size(); i++){

                User actualUser = invite_members.get(i);

                String option = console.readLine(actualUser.nickname + " wants to join your group. Do you accept? (Y/N) ");

                if(option.equals("Y")){//If YES

                    this.members.add(actualUser);
                    actualUser.groups.add(this);
                    invite_members.remove(i);

                    System.out.println("User accepted with success");

                    this.groupInformations();

                } else {//If NO

                    System.out.println("User not accepted.");
                    invite_members.remove(i);
                    
                    this.groupInformations();

                }
                

            }

        } else {

            System.out.println("You don't have the privilege to add/remove members.");

        }

    }

    public void removeUser(User user){
        
        Console console = System.console();

        String option = console.readLine("How do you want to remove from the group? ");

        //If user is the owner
        if(user.nickname.equals(this.userOwner.nickname)){

            //List of members
            for(int i = 0; i < this.members.size(); i++){

                User actualUser = this.members.get(i);

                if(actualUser.nickname.equals(option)){//User found

                    this.members.remove(actualUser);
                    actualUser.groups.remove(this);

                    System.out.println("User removed with success");

                    this.groupInformations();

                }

            }

        } else {

            System.out.println("You don't have the privilege to add/remove members.");

        }

    }

    public void groupInformations(){

        System.out.println("Total members: " + this.members.size());

        for(int i = 0; i < this.members.size(); i++){

            System.out.println("User: " + this.members.get(i).nickname);

        }

    }

}

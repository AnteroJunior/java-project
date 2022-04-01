/* 

    Create User class
    1. login
    2. password
    3. nickname

    New attributes
    4. address
    5. sex
    6. occupation

*/
public class User {
    
    String login;
    String password;
    String nickname;
    Profile profile = null;


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
        System.out.println("Name: " + this.nickname + "\n" + "Address: " + this.profile.address + "\n" + "Sex: " + this.profile.sex + "\n" + "Occupation: " + this.profile.occupation);
        System.out.println("----------------------");
    }

}

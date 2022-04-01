public class Profile {

    String nickname;
    String address;
    String sex;
    String occupation;
    
    public Profile(String nickname, String address, String sex, String occupation){

        this.nickname = nickname;
        this.address = address;
        this.sex = sex;
        this.occupation = occupation;

    }

    public void updateProfile(String nickname, String address, String sex, String occupation){

        this.nickname = nickname;
        this.address = address;
        this.sex = sex;
        this.occupation = occupation;

    }

}

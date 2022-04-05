public class Message {
    
    String message;
    User author;
    boolean read = false;

    public Message(String message, User author){

        this.message = message;
        this.author = author;

    }

}

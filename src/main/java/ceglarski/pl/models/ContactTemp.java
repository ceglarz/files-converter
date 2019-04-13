package ceglarski.pl.models;

public class ContactTemp {

    private Type Type;
    private String Contact;

    public ContactTemp(Type contactType){
        this.Type = contactType;
    }

    public Type getType() {
        return Type;
    }

    public String getContact(){
        return Contact;
    }

    public void setContact(String contact){
        this.Contact = contact;
    }
}

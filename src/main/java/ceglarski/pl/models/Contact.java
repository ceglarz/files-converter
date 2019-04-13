package ceglarski.pl.models;

import java.util.UUID;

public class Contact {

    private UUID Id;
    private UUID Id_customer;
    private Type Type;
    private String Contact;

    public Contact(UUID Id_customer, String Contact, Type contactType){
        this.Id = UUID.randomUUID();
        this.Id_customer = Id_customer;
        this.Type = contactType;
        this.Contact = Contact;
    }

    public UUID getId() {
        return Id;
    }

    public UUID getId_customer() {
        return Id_customer;
    }

    public int getTypeValue() {
        return Type.getValue();
    }

    public String getContact() {
        return Contact;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "Id=" + Id +
                ", Id_customer=" + Id_customer +
                ", Type=" + Type +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}

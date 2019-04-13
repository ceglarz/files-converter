package ceglarski.pl.models;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private UUID Id;
    private String Name;
    private String Surname;
    private int Age;
    private List<Contact> contacts;

    public Customer(String name, String surname, int age){
        this.Id = UUID.randomUUID();
        this.Name = name;
        this.Surname = surname;
        this.Age = age;
        this.contacts = new ArrayList();
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getAgeAsString() {
        return String.valueOf(Age);
    }

    public int getAge() { return Age; }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Age=" + Age +
                ", contacts=" + contacts +
                '}';
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }
}

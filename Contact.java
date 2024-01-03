

import java.util.List;

public interface Contact{
    void addContact(String firstName, String lastName, String phone, String address, String city);
    void removeContact(String firstName, String lastName);
    String getPhoneNo(String firstName, String lastName);
    String getAddress(String firstName, String lastName);
    String getCity(String firstName, String lastName);
    void updatePhoneNo(String firstName, String lastName, String newPhone);
    void updateAddress(String firstName, String lastName, String newAddress);

    List<String> searchContactByLastName(String lastName);
    List<String> searchContactByPhone(String phone);
    List<String> searchContactByCity(String city); 
    List<String> searchContacts(String lastName, String phone, String city); 
    List<String> searchContacts(String searchTerm);

    List<String> getAllContacts(); 
}
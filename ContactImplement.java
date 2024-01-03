

import java.util.List;
import java.util.ArrayList;
import java.util.Map; 
import java.util.HashMap;
import java.io.*;

public class ContactImplement implements Contact{ 
    
    private final Map<String, ContactDetails> contacts; 
    private static final String CSV = "contacts.csv"; 
    public ContactImplement(){
        this.contacts = new HashMap<>(); 
    }

    private void saveContactsToCSV(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/src/directory/contacts.csv"))){
            for(ContactDetails contact : contacts.values()){
                writer.write(String.format("%s, %s, %s, %s\n", 
                   contact.getFirstName(), 
                   contact.getLastName(), 
                   contact.getPhoneNo(), 
                   contact.getAddress(),
                   contact.getCity()
                ));
            }
            System.out.println("Contact saved");
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    private void loadContactCSV(){
        try(BufferedReader reader = new BufferedReader(new FileReader("/src/directory/contacts.csv"))){
            String line; 
            while((line = reader.readLine()) != null){
                String[] parts = line.split(","); 
                if(parts.length == 4){
                    addContact(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
            System.out.println("contacts loaded from CSV file"); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addContact(String firstName, String lastName, String phone, String address, String city){
        String key = generateKey(firstName, lastName);
        contacts.put(key, new ContactDetails(firstName, lastName, phone, address, city)); 

        System.out.println("Contact added" + firstName + " " + lastName);
    }

    @Override
    public void removeContact(String firstName, String lastName){
        String key = generateKey(firstName, lastName); 
        if(contacts.containsKey(key)){
            contacts.remove(key); 
            System.out.println("Contact removed: " + firstName + " " + lastName); 
        } else{
            System.out.println("Contact not found"); 
        }
    }

    @Override
    public String getPhoneNo(String firstName, String lastName){
        String key = generateKey(firstName, lastName); 
        if(contacts.containsKey(key)){
            return  
            contacts.get(key).getPhoneNo();
        } else{
            return "Contact not found"; 
        }
    }

    @Override
    public String getAddress(String firstName, String lastName){
        String key = generateKey(firstName, lastName); 
        if(contacts.containsKey(key)){
            return 
            contacts.get(key).getAddress();
        } else{
            return "Contact not found";
        }
    }

    @Override
    public String getCity(String firstName, String lastName){
        String key = generateKey(firstName, lastName); 
        if(contacts.containsKey(key)){
            return 
            contacts.get(key).getCity();
        } else{
            return "Contact not found";
        }
    }

    @Override
    public void updatePhoneNo(String firstName, String lastName, String newPhone){
        String key = generateKey(firstName, lastName); 
        if(contacts.containsKey(key)){
            contacts.get(key).setPhoneNo(newPhone);

            System.out.println("Phone number updated for " + firstName + " " + lastName);
        } else{
            System.out.println("No contact found");
        }
    }

    @Override
    public void updateAddress(String firstName, String lastName, String newAddress){
        String key = generateKey(firstName, lastName); 
        if(contacts.containsKey(key)){
            contacts.get(key).setAddress(newAddress);

            System.out.println("Address updated for " + firstName + " " + lastName);
        } else{
            System.out.println("No contact found"); 
        }
    }

    @Override
    public List<String> searchContactByLastName(String lastName){
        List<String> result = new ArrayList<>(); 

        for(ContactDetails contact: contacts.values()){
            if(contact.getLastName().equalsIgnoreCase(lastName)){
                result.add(contact.toString());
            }
        }
        return result; 
    }

    @Override
    public List<String> searchContactByCity(String city){
        List<String> result = new ArrayList<>(); 

        for(ContactDetails contact: contacts.values()){
            if(contact.getAddress().toLowerCase().contains(city.toLowerCase())){
                result.add(contact.toString());
            }
        }
        return result; 
    }

    @Override
    public List<String> searchContactByPhone(String phone){
        List<String> result = new ArrayList<>(); 

        for(ContactDetails contact : contacts.values()){
            if(contact.getPhoneNo().equals(phone)){
                result.add(contact.toString());
            }
        }
        return result;
    }

    @Override 
    public List<String> searchContacts(String lastName, String phone, String city){
        List<String> result = new ArrayList<>(); 
        for(ContactDetails contact : contacts.values()){
            if((lastName.isEmpty() || contact.getLastName().equalsIgnoreCase(lastName)) 
            && (city.isEmpty() || contact.getAddress().toLowerCase().contains(city.toLowerCase()))
            && (phone.isEmpty() || contact.getPhoneNo().equals(phone))){
                result.add(contact.toString());
            }
        }
        return result;
    }

    @Override 
    public List<String> searchContacts(String searchTerm){
        List<String> result = new ArrayList<>(); 
        for(ContactDetails contact: contacts.values()){
            if(contact.getFirstName().toLowerCase().contains(searchTerm.toLowerCase())
            || contact.getLastName().toLowerCase().contains(searchTerm.toLowerCase())
            || contact.getPhoneNo().contains(searchTerm)
            || contact.getAddress().toLowerCase().contains(searchTerm.toLowerCase())
            || contact.getCity().toLowerCase().contains(searchTerm.toLowerCase())){
                result.add(contact.toString());
            }
        }
        return result;
    }

    @Override
    public List<String> getAllContacts(){
        List<String> result = new ArrayList<>(); 
        for(ContactDetails contact : contacts.values()){
            result.add(contact.toString());
        }
        return result;
    }

    private String generateKey(String firstName, String lastName){
        return firstName.toLowerCase() + " " + lastName.toLowerCase();
    }

    private static class ContactDetails{
        private final String firstName; 
        private final String lastName; 
        private String phone; 
        private String address; 
        private String city; 

        public ContactDetails(String firstName, String lastName, String phone, String address, String city){
            this.firstName = firstName; 
            this.lastName = lastName; 
            this.phone = phone; 
            this.address = address; 
            this.city = city; 
        }

        public String getFirstName(){
            return firstName;
        }

        public String getLastName(){
            return lastName;
        }
        public String getPhoneNo(){
            return phone; 
        }

        public String getAddress(){
            return address;
        }

        public String getCity(){
            return city;
        }

        public void setPhoneNo(String phone){
            this.phone = phone;
        }

        public void setAddress(String address){
            this.address = address; 
        }

        @Override 
        public String toString(){
            return "Name: " + firstName + " " + lastName + ", phone: " + phone + ", Address: " + address + ", city : " + city;
        }
    }
}

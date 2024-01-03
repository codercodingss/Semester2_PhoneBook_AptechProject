

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.List; 

public class mainFrame extends JFrame{
    private final Contact contactManager; 

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextArea resultArea;
    private JTextField searchField;

    public mainFrame(Contact contactManager){
        this.contactManager = contactManager;
        initializeUI();
    }

    private void initializeUI(){
        setTitle("Contact Management App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 600);
        JPanel inputPanel = new JPanel(new GridLayout(5,2));
        firstNameField = new JTextField();
        lastNameField = new JTextField(); 
        phoneNumberField = new JTextField(); 
        addressField = new JTextField(); 
        cityField = new JTextField(); 

        JButton addButton = new JButton(" + ");
        JButton saveBtn = new JButton("save contacts");
        JButton loadBtn = new JButton("load contact");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addContact();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                saveContacts();
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                loadContacts();
            }
        });

        JPanel saveLoadPanel = new JPanel(new FlowLayout());
        saveLoadPanel.add(saveBtn, BorderLayout.WEST); 
        saveLoadPanel.add(loadBtn, BorderLayout.EAST); 
        add(saveLoadPanel, BorderLayout.SOUTH); 

        inputPanel.add(new JLabel("First Name: "));
        inputPanel.add(firstNameField); 
        inputPanel.add(new JLabel("Last Name: "));
        inputPanel.add(lastNameField); 
        inputPanel.add(new JLabel("phone: "));
        inputPanel.add(phoneNumberField); 
        inputPanel.add(new JLabel("address: "));
        inputPanel.add(addressField); 
        inputPanel.add(new JLabel("City: "));
        inputPanel.add(cityField); 

        inputPanel.add(addButton);

        resultArea = new JTextArea(); 
        resultArea.setEditable(false);
        
        searchField = new JTextField(); 
        JButton searchButton = new JButton("search");

        searchButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                performSearch(); 
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout()); 
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER); 
        searchPanel.add(searchButton, BorderLayout.EAST); 
        add(searchPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.NORTH); 
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(searchButton, BorderLayout.SOUTH); 
    }

    private void addContact(){
        String firstName = firstNameField.getText(); 
        String lastName = lastNameField.getText(); 
        String phone = phoneNumberField.getText(); 
        String address = addressField.getText();
        String city = cityField.getText(); 

        contactManager.addContact(firstName, lastName, phone, address, city);

        firstNameField.setText(" ");
        lastNameField.setText(" ");
        phoneNumberField.setText(" ");
        addressField.setText(" ");
        cityField.setText(" ");

        displayAllContacts();
    }

    private void searchContacts(){
        String lastName = lastNameField.getText(); 
        String city = cityField.getText(); 
        String phone = phoneNumberField.getText(); 

        List<String> searchResults = contactManager.searchContacts(lastName, phone, city);

        resultArea.setText(" ");
        for(String result : searchResults){
            resultArea.append(result + "\n");
        }
    }

    private void displayAllContacts(){
        List<String> allContacts = contactManager.getAllContacts();
        resultArea.setText("");
        for(String contact : allContacts){
            resultArea.append(contact + "\n");
        }
    }


    private void performSearch(){
        String searchTerm = searchField.getText(); 
        List<String> searchResults = contactManager.searchContacts(searchTerm); 

        resultArea.setText("");
        for(String result : searchResults){
            resultArea.append(result + "\n");
        }
    }

    private void saveContacts(){
        //((ContactImplement) contactManager).saveContactsToCSV();

    }

    private void loadContacts(){
       // ((ContactImplement) contactManager).loadContactCSV();
        displayAllContacts();
    }
}

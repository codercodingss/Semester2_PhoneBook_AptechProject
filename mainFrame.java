import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class mainFrame extends JFrame {
    private final Contact contactManager;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextArea resultArea;
    private JTextField searchField;

    public mainFrame(Contact contactManager) {
        this.contactManager = contactManager;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Contact Management App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 600);
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        phoneNumberField = new JTextField();
        addressField = new JTextField();
        cityField = new JTextField();

        String firstNamePlaceholder = "First Name";
        String lastNamePlaceholder = "Last Name";
        String phonePlaceholder = "Phone Number";
        String addressPlaceholder = "Address";
        String cityPlaceholder = "City";

        JButton addButton = new JButton(" + ");
        JButton saveBtn = new JButton("save contacts");
        JButton loadBtn = new JButton("load contact");

        JButton deleteBtn = new JButton("Delete Contact");
        JButton modifyBtn = new JButton("Modify Contact");
        JButton countBtn = new JButton("Count Contacts");

        // Placeholder for firstNameField
        firstNameField.setForeground(Color.GRAY);
        firstNameField.setText(firstNamePlaceholder);
        firstNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (firstNameField.getText().equals(firstNamePlaceholder)) {
                    firstNameField.setText("");
                    firstNameField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (firstNameField.getText().isEmpty()) {
                    firstNameField.setForeground(Color.GRAY);
                    firstNameField.setText(firstNamePlaceholder);
                }
            }
        });

        // Placeholder for lastNameField
        lastNameField.setForeground(Color.GRAY);
        lastNameField.setText(lastNamePlaceholder);
        lastNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameField.getText().equals(lastNamePlaceholder)) {
                    lastNameField.setText("");
                    lastNameField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameField.getText().isEmpty()) {
                    lastNameField.setForeground(Color.GRAY);
                    lastNameField.setText(lastNamePlaceholder);
                }
            }
        });

        // Placeholder for addressField
        addressField.setForeground(Color.GRAY);
        addressField.setText(addressPlaceholder);
        addressField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (addressField.getText().equals(addressPlaceholder)) {
                    addressField.setText("");
                    addressField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (addressField.getText().isEmpty()) {
                    addressField.setForeground(Color.GRAY);
                    addressField.setText(addressPlaceholder);
                }
            }
        });

        // Placeholder for phoneNumberField
        phoneNumberField.setForeground(Color.GRAY);
        phoneNumberField.setText(phonePlaceholder);
        phoneNumberField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneNumberField.getText().equals(phonePlaceholder)) {
                    phoneNumberField.setText("");
                    phoneNumberField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneNumberField.getText().isEmpty()) {
                    phoneNumberField.setForeground(Color.GRAY);
                    phoneNumberField.setText(phonePlaceholder);
                }
            }
        });

        // Placeholder for cityField
        cityField.setForeground(Color.GRAY);
        cityField.setText(cityPlaceholder);
        cityField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cityField.getText().equals(cityPlaceholder)) {
                    cityField.setText("");
                    cityField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cityField.getText().isEmpty()) {
                    cityField.setForeground(Color.GRAY);
                    cityField.setText(cityPlaceholder);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveContacts();
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadContacts();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        modifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyContact();
            }
        });

        countBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countContacts();
            }
        });

        JPanel saveLoadPanel = new JPanel(new FlowLayout());
        saveLoadPanel.add(saveBtn, BorderLayout.WEST);
        saveLoadPanel.add(loadBtn, BorderLayout.EAST);
        saveLoadPanel.add(deleteBtn);
        saveLoadPanel.add(modifyBtn);
        saveLoadPanel.add(countBtn);
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
        inputPanel.add(loadBtn);
        inputPanel.add(deleteBtn);
        inputPanel.add(modifyBtn);
        inputPanel.add(countBtn);

        setLocationRelativeTo(null);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        searchField = new JTextField();
        JButton searchButton = new JButton("search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    }

    private void addContact() {
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

    // private void searchContacts() {
    // String lastName = lastNameField.getText();
    // String city = cityField.getText();
    // String phone = phoneNumberField.getText();

    // List<String> searchResults = contactManager.searchContacts(lastName, phone,
    // city);

    // resultArea.setText(" ");
    // for (String result : searchResults) {
    // resultArea.append(result + "\n");
    // }
    // }

    private void deleteContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        contactManager.removeContact(firstName, lastName);

        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");
        cityField.setText("");

        displayAllContacts();
    }

    private void modifyContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String newPhone = phoneNumberField.getText();
        String newAddress = addressField.getText();

        contactManager.updatePhoneNo(firstName, lastName, newPhone);
        contactManager.updateAddress(firstName, lastName, newAddress);

        displayAllContacts();
    }

    private void countContacts() {
        List<String> allContacts = contactManager.getAllContacts();
        int count = allContacts.size();
        resultArea.setText("Total Contacts: " + count);
    }

    private void displayAllContacts() {
        List<String> allContacts = contactManager.getAllContacts();
        resultArea.setText("");
        for (String contact : allContacts) {
            resultArea.append(contact + "\n");
        }
    }

    private void performSearch() {
        String searchTerm = searchField.getText();
        List<String> searchResults = contactManager.searchContacts(searchTerm);

        resultArea.setText("");
        for (String result : searchResults) {
            resultArea.append(result + "\n");
        }
    }

    private void saveContacts() {
        ((ContactImplement) contactManager).saveContactsToCSV();

    }

    private void loadContacts() {
        ((ContactImplement) contactManager).loadContactCSV();
        displayAllContacts();

    }
}

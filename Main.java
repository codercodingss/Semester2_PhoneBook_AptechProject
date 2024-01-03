

import javax.swing.*;

public class Main {
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
               // new appFrame();
               Contact contactManager = new ContactImplement(); 
               mainFrame app = new mainFrame(contactManager); 
               app.setVisible(true);
            }
        });
    }   

}

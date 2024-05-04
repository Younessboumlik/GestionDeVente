import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
// import javafx.scene.text.*;
public class logincontroller {
    @FXML
    private TextField usernametext ;
    @FXML
    private PasswordField passwordtext;
    @FXML
    private Label labelcheck ;

    @FXML
    void submit(ActionEvent event) {
      try {
        BufferedReader readline = new BufferedReader(new FileReader("userdata"));
        String input = usernametext.getText();
        String password = passwordtext.getText();
        if (input.equals(readline.readLine())){
           if (password.equals(readline.readLine())){
            labelcheck.setText("your password is correct !");
           }
           else {
            labelcheck.setText("yerooooooor you are incorrect !");
           }
        }
        else {
            labelcheck.setText("yerooooooor you are incorrect !");
      
        }
        
        readline.close();
     } 
     catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
   
    }

    }

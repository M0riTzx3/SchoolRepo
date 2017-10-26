package adminLogin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public TextField userIDLogin;

    @FXML
    public TextField passwordLogin;

    @FXML
    public Button loginButton;

    public void login(){
        new LoginWithConnectionAccess().tryLogin(this);
    }

    public TextField getUserIDLogin() {
        return userIDLogin;
    }

    public TextField getPasswordLogin() {
        return passwordLogin;
    }



    public void test(){
        System.out.println("TEST");
    }
}



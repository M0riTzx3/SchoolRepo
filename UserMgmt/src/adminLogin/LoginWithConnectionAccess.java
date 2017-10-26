package adminLogin;

import createUser.userManagement.ConnectionData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class LoginWithConnectionAccess extends ConnectionData {


    public void tryLogin(LoginController loginController){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            setConn(DriverManager.getConnection("jdbc:mysql://" + getDbHost() + ":" + getDbPort() + "/" + getDbName() + "?" + "user=" + getDbUser() + "&" + "password=" + getDbPass()));

            Statement statement = getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE userID = " + loginController.getUserIDLogin().getText());

            String passwordDecoded = Base64.getEncoder().encodeToString(loginController.getPasswordLogin().getText().getBytes("utf-8"));

            while(resultSet.next()){
                if(resultSet.getString("whichType").equals("Admin")){
                    if(String.valueOf(resultSet.getInt("userID")).equals(loginController.getUserIDLogin().getText()) &&
                            resultSet.getString("password").equals(passwordDecoded) ){
                        System.out.println("LOGIN ERFOLGREICH");
                        Parent root = FXMLLoader.load(getClass().getResource("productManager.fxml"));
                        Stage secondStage = new Stage();
                        secondStage.setTitle("Product Management");
                        secondStage.setScene(new Scene(root));
                        secondStage.show();
                    }else {
                        System.out.println("LOGIN FEHLGESCHLAGEN");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

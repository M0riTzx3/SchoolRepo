package createUser;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import createUser.userManagement.*;
import java.util.*;

public class UserCreationController {

    @FXML
    private TableView<UserData> tableView;

    @FXML
    private TableColumn<UserData, String> userIDColumn;

    @FXML
    private TableColumn<UserData, String> nameColumn;

    @FXML
    private TableColumn<UserData, String> lastNameColumn;

    @FXML
    private TableColumn<UserData, String> typeColumn;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldPassword;

    @FXML
    public void initialize() {
        userIDColumn.setCellValueFactory( new PropertyValueFactory<>("userID"));
        nameColumn.setCellValueFactory( new PropertyValueFactory<>("firstName1"));
        lastNameColumn.setCellValueFactory( new PropertyValueFactory<>("lastName1"));
        typeColumn.setCellValueFactory( new PropertyValueFactory<>("type"));
    }

    private String firstName;
    private String lastName;
    private String password;
    public Integer usersID = 1;
    private String whichType;
    ArrayList<Integer> users = new ArrayList<>();

    public void addUser(){
        users.add(usersID);
        firstName = textFieldName.getText();
        lastName = textFieldLastName.getText();
        password = textFieldPassword.getText();

        new AddUser().addUser(this);
    }

    public void deleteUser(){
        new GetAndDelUser().deleteUser(this);
    }

    public void isChecked(){
        setWhichType("Admin");
    }

    public void table_reload(){
       new GetAndDelUser().reloadTable(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getWhichType() {
        return whichType;
    }

    public void setWhichType(String whichType) {
        this.whichType = whichType;
    }

    public TableView<UserData> getTableView() {
        return tableView;
    }
}
package createUser.userManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import createUser.UserCreationController;
import createUser.UserData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAndDelUser extends ConnectionData {

    private ObservableList<UserData> data = FXCollections.observableArrayList();

    public void reloadTable(UserCreationController controller) {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            setConn(DriverManager.getConnection("jdbc:mysql://"+ getDbHost()+":"+ getDbPort()+"/"+getDbName()+"?"+"user="+getDbUser()+"&"+"password="+getDbPass()));
            String SQL = "SELECT * FROM users";
            Statement statement = getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                UserData userData = new UserData();
                userData.setUserID(resultSet.getInt("userID"));
                userData.setFirstName1(resultSet.getString("firstName"));
                userData.setLastName1(resultSet.getString("lastName"));
                userData.setType(resultSet.getString("whichType"));
                data.add(userData);
            }
            System.out.println("*Tabelle neu geladen!*");
            controller.getTableView().setItems(data);

            resultSet.close();
            statement.close();
            getConn().close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteUser(UserCreationController controller){
        try {
            UserData userData = controller.getTableView().getSelectionModel().getSelectedItem();

            Class.forName( "com.mysql.jdbc.Driver" );
            setConn(DriverManager.getConnection("jdbc:mysql://" + getDbHost() + ":" + getDbPort()+ "/" + getDbName() + "?" + "user=" +getDbUser() + "&" + "password=" + getDbPass()));
            String SQL = "SELECT * FROM users";
            Statement statement = getConn().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                String getName = resultSet.getString("firstName");
                if (getName.equals(userData.getFirstName1())) {
                    resultSet.deleteRow();
                    System.out.println("*Benutzer gelöscht*");
                    controller.table_reload();
                    break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package createUser.userManagement;

import createUser.UserCreationController;
import java.sql.*;
import java.util.Base64;

public class AddUser extends ConnectionData {

    public void addUser(UserCreationController controller){
        try {
            //load JDBC
            Class.forName("com.mysql.jdbc.Driver");

            //Strings for Connecting
            setConn(DriverManager.getConnection("jdbc:mysql://"+getDbHost()+":"+ getDbPort()+"/"+getDbName()+"?"+"user="+getDbUser()+"&"+"password="+getDbPass()));
            String test = "INSERT INTO users(firstName, lastName, password, whichType) " +
                    "VALUES (?,?,?,?)";

            //Get Rows in Database
            Statement statement = getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM userdatabase.users");

            //encode Password
            String passwordEncoded = Base64.getEncoder().encodeToString(controller.getPassword().getBytes("utf-8"));
            //Fill Database Tables
            PreparedStatement preparedStatement = getConn().prepareStatement(test);
            preparedStatement.setString(1, controller.getFirstName());
            preparedStatement.setString(2, controller.getLastName());
            preparedStatement.setString(3, passwordEncoded);

            //Check if Admin or Customer
            if(controller.getWhichType().equals("Admin")){
                preparedStatement.setString(4, controller.getWhichType());
            }else if(controller.getWhichType() == null){
                preparedStatement.setString(4, "Customer");
            }

            preparedStatement.execute();
            System.out.println("*BENUTZER HINZUGEFÜGT*");
            controller.table_reload();

            //close connections
            resultSet.close();
            statement.close();
            getConn().close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}

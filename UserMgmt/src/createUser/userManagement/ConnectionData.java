package createUser.userManagement;

import createUser.UserCreationController;

import java.sql.*;

public class ConnectionData extends UserCreationController {

    private Connection conn = null;
    private String dbHost = "localhost";                      //Host
    private String dbPort = "3306";                          //Port
    private String dbName = "userdatabase";                 //Database name
    private String dbUser = "moritz_ebert";                 //Username
    private String dbPass = "123456";                        //User password

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

}

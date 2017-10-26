package adminLogin.productManager;

import adminLogin.ProductController;
import createUser.userManagement.ConnectionData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteProductFromConnection extends ConnectionData {

    public void deleteUser(ProductController productController){
        try {
            ProductData productData = productController.getTableView().getSelectionModel().getSelectedItem();

            Class.forName( "com.mysql.jdbc.Driver" );
            setConn(DriverManager.getConnection("jdbc:mysql://" + getDbHost() + ":" + getDbPort() + "/" + getDbName() + "?" + "user=" + getDbUser() + "&" + "password=" + getDbPass()));
            String SQL = "SELECT * FROM products";
            Statement statement = getConn().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                String getName = resultSet.getString("productName");
                if (getName.equals(productData.getProductName())) {
                    resultSet.deleteRow();
                    System.out.println("*Produkt gelöscht*");
                    productController.reloadTable();
                    break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

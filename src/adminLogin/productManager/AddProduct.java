package adminLogin.productManager;

import adminLogin.ProductController;
import createUser.userManagement.ConnectionData;

import java.sql.*;

public class AddProduct  extends ConnectionData{

   private ConnectionData connectionData = new ConnectionData();

    public void addProduct(ProductController productController){
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            connectionData.setConn(DriverManager.getConnection("jdbc:mysql://"+ getDbHost()+ ":" + getDbPort() + "/" + getDbName()+"?"+"user=" + getDbUser() + "&" + "password=" + getDbPass()));

            Statement statement = connectionData.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM userdatabase.products");
            resultSet.next();

            int rowCount = resultSet.getInt(1);

            while(productController.getProductNr() < rowCount + 1) {
                productController.productNr++;
            }

            PreparedStatement preparedStatement = connectionData.getConn().prepareStatement("INSERT INTO products(articleNumber, productName, stockValue, priceValue) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, productController.getProductNr());
            preparedStatement.setString(2, productController.getProductName());
            preparedStatement.setString(3, productController.getStockValue());
            preparedStatement.setString(4, productController.getPriceValue());
            preparedStatement.execute();
            System.out.println("*Produkt hinzugefügt*");
            productController.reloadTable();

            resultSet.close();
            statement.close();
            connectionData.getConn().close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

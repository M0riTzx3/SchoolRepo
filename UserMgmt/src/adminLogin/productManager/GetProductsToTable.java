package adminLogin.productManager;

import adminLogin.ProductController;
import createUser.userManagement.ConnectionData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetProductsToTable extends ConnectionData {

    private ObservableList<ProductData> data = FXCollections.observableArrayList();

    public void reloadTable(ProductController productController) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            setConn(DriverManager.getConnection("jdbc:mysql://"+ getDbHost()+":"+ getDbPort()+"/"+getDbName()+"?"+"user="+getDbUser()+"&"+"password="+getDbPass()));

            String SQL = "SELECT * FROM products";

            Statement statement = getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                ProductData productData = new ProductData();
                productData.setProductNr(resultSet.getInt("articleNumber"));
                productData.setProductName(resultSet.getString("productName"));
                productData.setStockValue(resultSet.getInt("stockValue"));
                productData.setPriceValue(resultSet.getInt("priceValue"));
                data.add(productData);
            }
            System.out.println("*Tabelle neu geladen!*");
            productController.getTableView().setItems(data);

            resultSet.close();
            statement.close();
            getConn().close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

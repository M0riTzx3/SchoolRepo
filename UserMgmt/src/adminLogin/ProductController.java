package adminLogin;

import adminLogin.productManager.AddProduct;
import adminLogin.productManager.DeleteProductFromConnection;
import adminLogin.productManager.GetProductsToTable;
import adminLogin.productManager.ProductData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class ProductController {

    @FXML
    private TableView<ProductData> tableView;

    @FXML
    private TableColumn<ProductData,Integer> productNrColumn;

    @FXML
    private TableColumn<ProductData,String> productNameColumn;

    @FXML
    private TableColumn<ProductData,Integer> stockValueColumn;

    @FXML
    private TableColumn<ProductData,Integer> priceValueColumn;

    @FXML
    private TextField textFieldProductName;

    @FXML
    private TextField textFieldStock;

    @FXML
    private TextField textFieldPrice;

    @FXML
    public void initialize() {
        productNrColumn.setCellValueFactory( new PropertyValueFactory<>("productNr"));
        productNameColumn.setCellValueFactory( new PropertyValueFactory<>("productName"));
        stockValueColumn.setCellValueFactory( new PropertyValueFactory<>("stockValue"));
        priceValueColumn.setCellValueFactory( new PropertyValueFactory<>("priceValue"));
    }
    
    private String productName;
    private String stockValue;
    private String priceValue;
    public int productNr = 1;
    private ArrayList<Integer> products = new ArrayList<>();

    public void addProduct(){
        products.add(productNr);
        productName = textFieldProductName.getText();
        stockValue = textFieldStock.getText();
        priceValue = textFieldPrice.getText();
        new AddProduct().addProduct(this);
    }

    public void reloadTable(){
        new GetProductsToTable().reloadTable(this);
    }

    public void deleteUser(){
        new DeleteProductFromConnection().deleteUser(this);
    }

    public String getProductName() {
        return productName;
    }

    public String getStockValue() {
        return stockValue;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public int getProductNr() {
        return productNr;
    }

    public TableView<ProductData> getTableView() {
        return tableView;
    }


    public void test(){
        LoginController l = new LoginController();
        l.test();
    }
}
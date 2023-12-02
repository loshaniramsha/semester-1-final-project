package lk.ijse.FactoryManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.ProductDto;
import lk.ijse.FactoryManage.model.ProductModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    public AnchorPane root;
    public TableView tblProduct;
    public TableColumn colProdutId;
    public TableColumn colName;
    public TableColumn colBrand;
    public TableColumn colQty;
    public TextField textProductId;
    public TextField textName;
    public TextField textBrand;
    public TextField textQty;

    private ProductModel productModel=new ProductModel();

    public void initialize(){
        setCellValueFactory();
        loadAllProducts();

    }

    private void loadAllProducts() {
        var model = new ProductModel();
        ObservableList<ProductDto> obList = FXCollections.observableArrayList();
        try {
            List<ProductDto> dtoList = model.getAllProducts();
            for (ProductDto dto : dtoList) {
                obList.add(new ProductDto(dto.getProductId(), dto.getName(), dto.getBrand(), dto.getQty()));
            }
            tblProduct.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProdutId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    public void clearOnAction(ActionEvent event) {
        clearField();
    }

    public void deleteOnAction(ActionEvent event) throws Exception{
        String productId=textProductId.getText();
        boolean isDeleted=ProductModel.deleteProduct(productId);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted"+productId).show();
            clearField();
            clearCollection();

        }
    }

    public void updateOnAction(ActionEvent event) throws Exception {
        String productId = textProductId.getText();
        String name = textName.getText();
        String brand = textBrand.getText();
        String qty = textQty.getText();
        var Dto=new ProductDto(productId,name,brand,qty);
        boolean isUpdated=ProductModel.updateProduct(Dto);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            clearField();
        }
    }

    public void saveOnAction(ActionEvent event) throws  Exception{
        String productId = textProductId.getText();
        String name = textName.getText();
        String brand = textBrand.getText();
        String qty = textQty.getText();
        var Dto=new ProductDto(productId,name,brand,qty);
        boolean isSaved=ProductModel.saveProduct(Dto);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            clearField();
        }


    }

    private void clearField() {
        textProductId.clear();
        textName.clear();
        textBrand.clear();
        textQty.clear();
        
    }
    private void clearCollection(){
        textProductId.clear();
        textName.clear();
        textBrand.clear();
        textQty.clear();
    }


    public void employeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void targetOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/target_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void materialOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void madeproductOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/madeproduct_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void backOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void searchOnAction(ActionEvent event) {
        String productId = textProductId.getText();
        try {
            ProductDto productDto = ProductModel.searchProduct(productId);
         if (productDto != null) {
             textName.setText(productDto.getName());
             textBrand.setText(productDto.getBrand());
             textQty.setText(productDto.getQty());
         } else {
             new Alert(Alert.AlertType.WARNING, "Empty").show();
         }
    }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void lblBackOnAction(MouseEvent mouseEvent) throws Exception {
        URL resource = getClass().getResource("/view/dashboard2_form.fxml");
        assert resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            root.getChildren().clear();
            root.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public void reportOnAction(ActionEvent actionEvent) throws Exception {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Products.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
    }
}

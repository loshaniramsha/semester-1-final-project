package lk.ijse.FactoryManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FactoryManage.dto.SupplierDto;
import lk.ijse.FactoryManage.model.SupplierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierController {
    public AnchorPane root;
    public TextField textSupId;
    public TextField textName;
   // public TextField textAttenborough;
    public TextField textDate;
    public TableView tblSupplier;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colAmountBrought;
    public TableColumn colDate;
    public TextField textAmountbrought;

    private SupplierModel supplierModel = new SupplierModel();

    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        var model = new SupplierModel();
        ObservableList<SupplierDto> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> dtoList = model.getAllSuppliers();
            for (SupplierDto dto : dtoList) {
                obList.add(new SupplierDto(dto.getSupplierId(), dto.getName(), dto.getAmmountbrought(), dto.getDate()));
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAmountBrought.setCellValueFactory(new PropertyValueFactory<>("ammountbrought"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));

    }

    public void saveOnAction(ActionEvent event) throws Exception {
        String supId = textSupId.getText();
        String name = textName.getText();
        String ammountbrought = textAmountbrought.getText();
        String date = textDate.getText();
        var Dto = new SupplierDto(supId, name, ammountbrought, date);
        boolean issaved= SupplierModel.saveSupplier(Dto);
        if (issaved){

            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            clerField();
        }
    }

    private void clerField() {
        textSupId.clear();
        textName.clear();
        textAmountbrought.clear();
        textDate.clear();
    }
    private void clearCollection() {
        textSupId.clear();
        textName.clear();
        textAmountbrought.clear();
        textDate.clear();
    }

    public void updateOnAction(ActionEvent event) {
        String supId = textSupId.getText();
        String name = textName.getText();
        String ammountbrought = textAmountbrought.getText();
        String date = textDate.getText();
        var Dto = new SupplierDto(supId, name, ammountbrought, date);
        try {
            boolean isUpdated = SupplierModel.updateSupplier(Dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated" +" "+ supId).show();
                clerField();
                clearCollection();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void deleteOnAction(ActionEvent event) throws SQLException {
        String supId = textSupId.getText();
        try {
            boolean isDeleted = SupplierModel.deleteSupplier(supId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted" +" "+ supId).show();
                clerField();
                clearCollection();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchOnAction(ActionEvent event) {
        String supId = textSupId.getText();
        try {
            SupplierDto supplierDto = SupplierModel.searchSupplier(supId);
            if (supplierDto != null) {
                textName.setText(supplierDto.getName());
                textAmountbrought.setText(supplierDto.getAmmountbrought());
                textDate.setText(supplierDto.getDate());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void materialOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
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

    public void clearOnAction(ActionEvent event) {
        clerField();
    }

    public void lblBackOnAction(MouseEvent mouseEvent) {
    }
}

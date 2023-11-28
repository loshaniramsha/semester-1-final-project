package lk.ijse.FactoryManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FactoryManage.dto.MaterialDto;
import lk.ijse.FactoryManage.dto.SupplierDto;
import lk.ijse.FactoryManage.model.MaterialModel;
import lk.ijse.FactoryManage.model.SupplierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MaterialController {
    public AnchorPane root;
    public TextField textMaterialId;
    public TextField txtNameoftyp;
    public TextField txtQty;
    public TextField txtSupplier;
    public TableView tblMaterial;
    public TableColumn colMaterialId;
    public TableColumn colQty;
    public TableColumn colNameOfTyp;
    public TableColumn colSupplier;
    public ComboBox<String> combSupplierId;

    private MaterialModel materialModel=new MaterialModel();

    public void initialize() throws Exception {
        loadCmb();
        setCellValueFactory();
        loardAllMaterials();

    }

    private void loardAllMaterials() {
        var model = new MaterialModel();
        ObservableList<MaterialDto> obList = FXCollections.observableArrayList();
        try {
            List<MaterialDto> dtoList = model.getAllMaterials();
            for (MaterialDto dto : dtoList) {
                obList.add(new MaterialDto(dto.getMaterialId(), dto.getNameOfType(), dto.getQty(), dto.getSupplierId()));
            }
            tblMaterial.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colNameOfTyp.setCellValueFactory(new PropertyValueFactory<>("NameOfType"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));

    }

    private void loadCmb() throws Exception {
        combSupplierId.getItems().clear();
        List<SupplierDto> allSuppliers = SupplierModel.getAllSuppliers();
        for (SupplierDto getAllSuppliers:allSuppliers){
            combSupplierId.getItems().add(getAllSuppliers.getSupplierId());
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void saveOnAction(ActionEvent event) throws Exception{
        String materialId = textMaterialId.getText();
        String nameoftyp = txtNameoftyp.getText();
        String qty = txtQty.getText();
        String supplier = combSupplierId.getValue().toString();
        var Dto=new MaterialDto(materialId,nameoftyp,qty,supplier);
        boolean isSaved= MaterialModel.saveMaterial(Dto);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            clearField();
        }
    }

    private void clearField() {
        textMaterialId.clear();
        txtNameoftyp.clear();
        txtQty.clear();
        combSupplierId.getSelectionModel().clearSelection();
    }
    private void clearCollection(){
        textMaterialId.clear();
        txtNameoftyp.clear();
        txtQty.clear();
        combSupplierId.getSelectionModel().clearSelection();
    }

    public void deleteOnAction(ActionEvent event) throws Exception {
        String materialId = textMaterialId.getText();
        boolean isDeleted = MaterialModel.deleteMaterial(materialId);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            clearField();
            clearCollection();
        }
    }

    public void updateOnAction(ActionEvent event) throws Exception {
        String materialId = textMaterialId.getText();
        String nameoftyp = txtNameoftyp.getText();
        String qty = txtQty.getText();
        String supplier = combSupplierId.getValue().toString();
        var Dto=new MaterialDto(materialId,nameoftyp,qty,supplier);
        boolean isUpdated= MaterialModel.updateMaterial(Dto);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            clearField();
        }

    }

    public void searchOnAction(ActionEvent event) {
        String materialId = textMaterialId.getText();
        try {
            MaterialDto materialDto = MaterialModel.searchMaterial(materialId);
            if (materialDto != null) {
                textMaterialId.setText(materialDto.getMaterialId());
                txtNameoftyp.setText(materialDto.getNameOfType());
                txtQty.setText(materialDto.getQty());
                combSupplierId.setValue(materialDto.getSupplierId());
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearOnAction(ActionEvent event) {
        clearField();
    }

    public void combSupplieritemOnAction(ActionEvent event) {

    }

    public void lblbackOnAction(MouseEvent mouseEvent) {
    }

 /*   public void combSupplieritemOnAction(ActionEvent event) {
        String supplier = combSupplierId.getValue().toString();
        txtSupplier.setText(supplier);
        try {
            MaterialDto materialDto= MaterialModel.searchMaterial(supplier);


        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}

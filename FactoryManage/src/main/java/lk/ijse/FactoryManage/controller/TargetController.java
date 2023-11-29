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
import lk.ijse.FactoryManage.dto.TargetDto;
import lk.ijse.FactoryManage.model.TargetModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class TargetController {
    public AnchorPane root;
    public TextField textTargetId;
    public TextField textTargetAmount;
    public TextField textReceiveDate;
    public TextField textSendDate;
    public TableView tblTarget;
    public TableColumn colTargetId;
    public TableColumn colTargetAmount;
    public TableColumn colReceiveDate;
    public TableColumn colSendDate;
    //private Object TargetModel;

    private TargetModel targetModel=new TargetModel();
    public void initialize() {
        setCellValueFactory();
        loadAllTargets();
    }

    private void loadAllTargets() {
        var model = new TargetModel();
        ObservableList<TargetDto> obList = FXCollections.observableArrayList();
        try {
            List<TargetDto> dtoList = model.getAllTargets();
            for (TargetDto dto : dtoList) {
                obList.add(new TargetDto(dto.getTargetId(), dto.getTargetAmount(), dto.getReceiveDate(), dto.getSendDate()));
            }
            tblTarget.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colTargetId.setCellValueFactory(new PropertyValueFactory<>("targetId"));
        colTargetAmount.setCellValueFactory(new PropertyValueFactory<>("targetAmount"));
        colReceiveDate.setCellValueFactory(new PropertyValueFactory<>("receiveDate"));
        colSendDate.setCellValueFactory(new PropertyValueFactory<>("sendDate"));

    }


    public void productOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/product_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void exportOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/exportableproduct_form.fxml"));
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
        clearField();
    }

    public void deleteOnAction(ActionEvent event) throws SQLException {
        String targetId = textTargetId.getText();
        try {
            boolean isDeleted = TargetModel.deleteTarget(targetId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted"+targetId).show();
                clearField();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void updateOnAction(ActionEvent event) throws Exception {
        String targetId = textTargetId.getText();
        String targetAmount = textTargetAmount.getText();
        String receiveDate = textReceiveDate.getText();
        String sendDate = textSendDate.getText();
        var Dto=new TargetDto(targetId,targetAmount,receiveDate,sendDate);
        boolean isUpdated= TargetModel.updateTarget(Dto);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            clearField();
        }
    }

    public void saveOnAction(ActionEvent event) throws Exception {
        String targetId = textTargetId.getText();
        String targetAmount = textTargetAmount.getText();
        String receiveDate = textReceiveDate.getText();
        String sendDate = textSendDate.getText();
        var Dto=new TargetDto(targetId,targetAmount,receiveDate,sendDate);
        boolean isSaved= TargetModel.saveTarget(Dto);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            clearField();
        }
    }
    private void clearField() {
        textTargetId.clear();
        textTargetAmount.clear();
        textReceiveDate.clear();
        textSendDate.clear();
    }
    private void clearCollection() {
        textTargetId.clear();
        textTargetAmount.clear();
        textReceiveDate.clear();
        textSendDate.clear();
    }

    public void searchOnAction(ActionEvent event) {
        String targetId = textTargetId.getText();
        try {
            TargetDto dto = TargetModel.searchTarget(targetId);
            if (dto != null) {
                textTargetAmount.setText(dto.getTargetAmount());
                textReceiveDate.setText(dto.getReceiveDate());
                textSendDate.setText(dto.getSendDate());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void lblBackOnAction(MouseEvent mouseEvent) {
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

    }


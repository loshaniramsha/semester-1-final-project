package lk.ijse.FactoryManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FactoryManage.Regex.Regex;
import lk.ijse.FactoryManage.dto.EmployeeDto;
import lk.ijse.FactoryManage.dto.ScheduleDto;
import lk.ijse.FactoryManage.dto.UserDto;
import lk.ijse.FactoryManage.model.EmployeeModel;
import lk.ijse.FactoryManage.model.ScheduleModel;
import lk.ijse.FactoryManage.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {
    public AnchorPane root;
    public TextField txtEmployeeId;
    public TextField txtType;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPhoneNumber;
    public TextField txtUserid;
    public TextField txtSchedule;
    public TableView tblEmployee;
    public TableColumn colEmoId;
    public TableColumn colName;
    public TableColumn colTelNum;
    public TableColumn colType;
    public TableColumn colEmail;
    public TableColumn colUserId;
    public TableColumn colScheduleId;
    public ComboBox cmbUserId;
    public ComboBox cmbScheduleId;


    public void initialize() throws Exception {
        loadCmb();
        setCellValueFactory();
      loardAllEmployees();
    }

    private void loardAllEmployees() {
        var model = new EmployeeModel();
        ObservableList<EmployeeDto> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> dtoList = model.getAllEmployees();
            for (EmployeeDto dto : dtoList) {
                obList.add(new EmployeeDto(dto.getEmployeeId(), dto.getType(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getUserId(), dto.getScheduleId()));
            }
            tblEmployee.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmoId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTelNum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
       // colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colScheduleId.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));

    }

    private void loadCmb() throws Exception {
        cmbUserId.getItems().clear();
        List<UserDto> allUserIds = UserModel.getAllUsers();
        for (UserDto userId : allUserIds) {
            cmbUserId.getItems().add(userId.getUserId());

        }
        cmbScheduleId.getItems().clear();
        List<ScheduleDto> allScheduleIds = ScheduleModel.getAllSchedules();
        for (ScheduleDto scheduleId : allScheduleIds) {
            cmbScheduleId.getItems().add(scheduleId.getScheduleId());
        }

    }

    public void deleteOnAction(ActionEvent event) throws Exception {
        String employeeId = txtEmployeeId.getText();
        if (EmployeeModel.delete(employeeId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted"+employeeId).show();
        }
    }

    public void updateOnAction(ActionEvent event) throws Exception {
        String employeeId = txtEmployeeId.getText();
        String type = txtType.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String userId = (String) cmbUserId.getValue();
        String scheduleId = (String) cmbScheduleId.getValue();
        var Dto = new EmployeeDto(employeeId, type, name, email, phoneNumber, userId, scheduleId);
      try {
          boolean isUpdated = EmployeeModel.update(Dto);
          if (isUpdated) {
              new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
          }
      }catch (Exception e){
          new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
      }
    }

    public void searchOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        try {
            EmployeeDto employeeDto = EmployeeModel.search(employeeId);
            if (employeeDto != null) {
                txtEmployeeId.setText(employeeDto.getEmployeeId());
                txtType.setText(employeeDto.getType());
                txtName.setText(employeeDto.getName());
                txtEmail.setText(employeeDto.getEmail());
                txtPhoneNumber.setText(employeeDto.getPhone());
                cmbUserId.setValue(employeeDto.getUserId());
                cmbScheduleId.setValue(employeeDto.getScheduleId());
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void clearOnAction(ActionEvent event) {
        clearField();
    }

    public void newEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/google_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void viewMailOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void userOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/user_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void productOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/product_form.fxml"));
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

    public void SaveOnAction(ActionEvent event) throws Exception {
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String userId = cmbUserId.getValue().toString();
        String scheduleId = cmbScheduleId.getValue().toString();


        if(Regex.getMobilePattern().matcher(txtPhoneNumber.getText()).matches()){
            if(Regex.getEmailPattern().matcher(txtEmail.getText()).matches()){
                if(Regex.getNamePattern().matcher(txtName.getText()).matches()){

                    var Dto = new EmployeeDto(employeeId,name,type,email,phoneNumber,userId,scheduleId);
                    boolean isSaved= EmployeeModel.saveEmployee(Dto);
                    if (isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                        clearField();
                    }

                }else {
                    new Alert(Alert.AlertType.WARNING,"Invalid Contact Number").show();
                }

            } else {}
            new Alert(Alert.AlertType.WARNING,"Invalid Contact Number").show();

        }
        else{
            new Alert(Alert.AlertType.WARNING,"Invalid Contact Number").show();

        }





    }

    private void clearField() {
        txtEmployeeId.clear();
        txtName.clear();
        txtType.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        cmbUserId.getSelectionModel().clearSelection();
        cmbScheduleId.getSelectionModel().clearSelection();
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
    }


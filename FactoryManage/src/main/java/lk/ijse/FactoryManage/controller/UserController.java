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
import lk.ijse.FactoryManage.dto.UserDto;
import lk.ijse.FactoryManage.dto.tm.UserTm;
import lk.ijse.FactoryManage.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class UserController {
    public AnchorPane root;
    public TextField textuserId;
    public TextField textPost;
    public TextField textName;
    public TextField textBranch;
    public TableView tblUser;
    public TableColumn colUserId;
    public TableColumn colPost;
    public TableColumn colBranch;
    public TableColumn colName;
    public TableColumn colPasswoed;
    public TextField textPassword;

    private UserModel userModel = new UserModel();
    private PolicyNode changePain;

    public void initialize() {
        setCellValueFactory();
        loadAllUsers();

    }




    private void loadAllUsers() {
        var model=new UserModel();
        ObservableList<UserTm> obList= FXCollections.observableArrayList();
        try {
            List<UserDto> dtoList=model.getAllUsers();
            for (UserDto dto : dtoList) {
                obList.add(new UserTm(dto.getUserId(),dto.getPost(),dto.getBranch(),dto.getName(),dto.getPassword()));
            }
            tblUser.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
      colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
      colPost.setCellValueFactory(new PropertyValueFactory<>("post"));
      colName.setCellValueFactory(new PropertyValueFactory<>("branch"));
      colBranch.setCellValueFactory(new PropertyValueFactory<>("name"));
      colPasswoed.setCellValueFactory(new PropertyValueFactory<>("password"));



    }

    public void saveOnAction(ActionEvent event) throws Exception {
        boolean isUserValidated = validateUser();
        if (!isUserValidated) {
           // new Alert(Alert.AlertType.ERROR, "Invalid User").show();
            return;
        }else {
        String userId = textuserId.getText();
        String post = textPost.getText();
        String name = textName.getText();
        String branch = textBranch.getText();
        String password = textPassword.getText();
        UserDto Dto = new UserDto(userId, post, name, branch, password);
        boolean isSaved = UserModel.saveUser(Dto);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            clearField();
        }
         }


    }

    private boolean validateUser() {
      String text=textuserId.getText();
      boolean isUserValidated = Pattern.compile("[U][0-9]{3,}").matcher(text).matches();
      if (!isUserValidated) {
          new Alert(Alert.AlertType.ERROR, "Invalid User Id").show();
          return false;
      }
      return true;



    }

    private void clearField() {
        textuserId.clear();
        textPost.clear();
        textName.clear();
        textBranch.clear();
        textPassword.clear();
    }
    private void clearCollection() {
        textuserId.clear();
        textPost.clear();
        textName.clear();
        textBranch.clear();
        textPassword.clear();
    }

    public void deleteOnAction(ActionEvent event) throws SQLDataException, ClassNotFoundException {

        String userId = textuserId.getText();
        try {
            boolean isDeleted = UserModel.deleteUser(userId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted"+" -"+ userId).show();
                clearField();
                clearCollection();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void updateOnAction(ActionEvent event) {
        String userId = textuserId.getText();
        String post = textPost.getText();
        String name = textName.getText();
        String branch = textBranch.getText();
        String password = textPassword.getText();
        var Dto = new UserDto(userId, post, name, branch, password);
        try {
            boolean isUpdated = UserModel.updateUser(Dto);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearField();

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public void clearOnAction(ActionEvent event) {
        clearField();
    }

    public void employeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void materialOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void madeproductOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/madeproduct_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void exportOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/exportableproduct_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void productOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/product_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void supplierOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void scheduleOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void targetOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/target_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void backOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void registerOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/google_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void searchOnAction(ActionEvent event) {
        String userId = textuserId.getText();
        try {
            UserDto dto = UserModel.searchUser(userId);
            if (dto != null) {
                textPost.setText(dto.getPost());
                textName.setText(dto.getName());
                textBranch.setText(dto.getBranch());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void summaryOnAction(ActionEvent event) {
    }

    public void lblBackOnAction(MouseEvent mouseEvent) throws Exception{

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





//^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$
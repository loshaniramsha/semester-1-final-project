package lk.ijse.FactoryManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {

    @FXML
    private TextField txtAdminPassword;

    @FXML
    private TextField txtAdminUserName;
    private final static String userName1 = "admin";
    private final static String password1 = "1234";
    private final static String userName2 = "loshani";
    private final static String password2 = "loshani";
    private DashboardController dashboardController;


    @FXML
    void btnUnlockOnAction(ActionEvent event) throws IOException {
        String username = txtAdminUserName.getText();
        String password = txtAdminPassword.getText();
        System.out.println(username+password);
        if (username == null || password == null){
            // alert ekak dann user password empty kyl
            return;
        }
        if (
                (username.equals(userName1) && password.equals(password1)) ||
                (username.equals(userName2) && password.equals(password2))
        ){
            DashboardController.isAdminUnlock = true;
            dashboardController.goUserForm();
            ((Stage)txtAdminPassword.getScene().getWindow()).close();
        }else {
            new Alert(Alert.AlertType.WARNING,"Wrong user name or password").show();
        }
    }

    public void init(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}

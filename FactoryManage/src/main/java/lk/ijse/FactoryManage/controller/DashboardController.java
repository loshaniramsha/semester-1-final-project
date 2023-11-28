package lk.ijse.FactoryManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardController {
    public AnchorPane root;
    public AnchorPane changePain;
    public static boolean isAdminUnlock = false;
    public ImageView lblHome;

    public void initialize() {
        setForm("/view/dashboard2_form.fxml");
    }
    public void UserOnAction(ActionEvent actionEvent) {
        System.out.println("hello");
    }

    public void setForm(String form){
        URL resource = getClass().getResource(form);
        assert resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            changePain.getChildren().clear();
            changePain.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void userOnAction(ActionEvent event) throws IOException {
        if (isAdminUnlock) {
            goUserForm();
        }else {

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/AdminLoginForm.fxml"));
            Parent load = fxmlLoader.load();
            AdminLoginFormController adminLoginFormController = fxmlLoader.getController();
            adminLoginFormController.init(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Admin Login");
            stage.centerOnScreen();
            stage.show();

        }
    }

    public void goUserForm() {
        setForm("/view/user_form.fxml");
    }

    public void employeeOnAction(ActionEvent event) throws IOException {

        setForm("/view/employee_form.fxml");

    }

    public void scheduleOnAction(ActionEvent event) throws IOException {
        setForm("/view/schedule_form.fxml");
      /*  AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }


    public void productOnAction(ActionEvent event) throws IOException {
        setForm("/view/product_form.fxml");
      /*  AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/product_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void madeProductOnAction(ActionEvent event) throws IOException {
        setForm("/view/madeproduct_form.fxml");
        /*AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/madeproduct_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void supplierOnAction(ActionEvent event) throws IOException {
        setForm("/view/supplier_form.fxml");

       /* AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void materialOnAction(ActionEvent event) throws IOException {
        setForm("/view/material_form.fxml");
        /*AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void countMaterialOnAction(ActionEvent event) throws IOException {
        setForm("/view/countmaterial_form.fxml");
        /*AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/countmaterial_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void targetOnAction(ActionEvent event)throws IOException {
        setForm("/view/target_form.fxml");
       /* AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/target_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void exportableOnAction(ActionEvent event) throws IOException {
        setForm("/view/exportableproduct_form.fxml");
      /*  AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/exportableproduct_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();*/
    }

    public void exitOnAction(ActionEvent event) throws IOException {
        System.exit(0);

    }

    public void nextOnAction(ActionEvent event) {
    }

    public void lblBackOnAction(MouseEvent mouseEvent) throws Exception {
        System.exit(0);
    }

    public void suplierOnAction(ActionEvent actionEvent) throws Exception {
        setForm("/view/supplier_form.fxml");
    }

    public void lblHomeOnAction(MouseEvent mouseEvent) throws Exception{
        setForm("/view/dashboard2_form.fxml");
    }
}

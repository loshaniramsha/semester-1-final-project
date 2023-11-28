package lk.ijse.FactoryManage;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent load = FXMLLoader.load(this.getClass().getResource("/view/userlogin_form.fxml"));

        Scene scene = new Scene(load);
        stage.setTitle("***Texton Private Limited***");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

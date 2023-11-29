package lk.ijse.FactoryManage.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.ijse.FactoryManage.dto.ScheduleDto;
import lk.ijse.FactoryManage.model.EmployeeModel;
import lk.ijse.FactoryManage.model.ProductModel;
import lk.ijse.FactoryManage.model.ScheduleModel;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Dashboard2Controller implements Initializable {
    public Label lblProduct;
    public Label lblEmployee;
    public Label lblSchedulePlane;
    public Label lblScheduleDate;
    public Label lblTime;
    public Label lblDate;



    private void setValues() throws Exception {
        lblEmployee.setText(String.valueOf(EmployeeModel.getAllEmployees().size()));
        lblProduct.setText(String.valueOf(ProductModel.getAllProductIds().size()));

        List<ScheduleDto> allSchedules = ScheduleModel.getAllSchedules();
        for (ScheduleDto schedule : allSchedules) {
            lblSchedulePlane.setText(String.valueOf(schedule.getPlane()));
            lblScheduleDate.setText(String.valueOf(schedule.getDate()));
        }

    }

    public void lblBackOnAction(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        loadDateandTime();
        try {
            setValues();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDateandTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

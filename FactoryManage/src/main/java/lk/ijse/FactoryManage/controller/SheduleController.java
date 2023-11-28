package lk.ijse.FactoryManage.controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
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
import lk.ijse.FactoryManage.Mail.Mail;
import lk.ijse.FactoryManage.dto.EmployeeDto;
import lk.ijse.FactoryManage.dto.ScheduleDto;
import lk.ijse.FactoryManage.dto.tm.ScheduleTm;
import lk.ijse.FactoryManage.model.EmployeeModel;
import lk.ijse.FactoryManage.model.ScheduleModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class SheduleController {
    public AnchorPane root;
    public TableView tblSchedule;
    public TableColumn colDate;
    public TableColumn colPlane;
    public TextField textscheduleId;
    public TextField textType;
    public TextField textPlne;
    public TextField textDate;

    private final ObservableList<ScheduleDto> obList = FXCollections.observableArrayList();

    private ScheduleModel scheduleModel = new ScheduleModel();

    public void initialize() {
        setCellValueFactory();
        loadAllSchedules();
    }

    private void loadAllSchedules() {
        var model = new ScheduleModel();

        try {
            List<ScheduleDto> dtoList = model.getAllSchedules();
            for (ScheduleDto dto : dtoList) {
                obList.add(new ScheduleDto(dto.getScheduleId(), dto.getType(), dto.getDate(), dto.getPlane()));
            }
            tblSchedule.setItems(obList);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPlane.setCellValueFactory(new PropertyValueFactory<>("plane"));

    }

    public void saveOnAction(ActionEvent event) throws Exception{
        String scheduleId = textscheduleId.getText();
        String type = textType.getText();
        String date = textDate.getText();
        String plane = textPlne.getText();
        var Dto = new ScheduleDto(scheduleId, type, date, plane);
        boolean issaved= ScheduleModel.saveSchedule(Dto);
        if (issaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            clearField();
        }

    }

    private void clearField() {
        textscheduleId.clear();
        textType.clear();
        textDate.clear();
        textPlne.clear();
    }
    private void clearCollection(){
        textscheduleId.clear();
        textType.clear();
        textDate.clear();
        textPlne.clear();
    }

    public void updateOnAction(ActionEvent event) throws Exception{
        String scheduleId = textscheduleId.getText();
        String type = textType.getText();
        String date = textDate.getText();
        String plane = textPlne.getText();
        var Dto = new ScheduleDto(scheduleId, type, date, plane);
        boolean isUpdated= ScheduleModel.updateSchedule(Dto);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            clearField();
        }



    }

    public void deleteOnAction(ActionEvent event) throws Exception{
        String scheduleId = textscheduleId.getText();
        try {
            boolean isDeleted = ScheduleModel.deleteSchedule(scheduleId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted" + scheduleId).show();
                clearField();
                clearCollection();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void clearOnAction(ActionEvent event) {
        clearField();
    }

    public void backOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);

        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void searchOnAction(ActionEvent event)  {
        String scheduleId = textscheduleId.getText();
        try {
            ScheduleDto scheduleDto = ScheduleModel.searchSchedule(scheduleId);
            if (scheduleDto != null) {
                textscheduleId.setText(scheduleDto.getScheduleId());
                textType.setText(scheduleDto.getType());
                textDate.setText(scheduleDto.getDate());
                textPlne.setText(scheduleDto.getPlane());
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void sendOnAction(ActionEvent event) throws Exception {
        List<EmployeeDto> employeeDtos = EmployeeModel.getAllEmployees();
        String sheduleMassage = "";
        for (int i = 0;i<tblSchedule.getItems().size();i++){
            ScheduleDto scheduleDto = obList.get(i);
            String date = scheduleDto.getDate();
            String plane = scheduleDto.getPlane();
            sheduleMassage = sheduleMassage + "\n"+date+" - "+ plane;
        }

        for (EmployeeDto employeeDto:employeeDtos){
            String to = employeeDto.getEmail();


            Mail mail = new Mail();
            mail.setMsg(sheduleMassage);
            mail.setTo(to);
            mail.setSubject("Factory Manage");


            Thread thread = new Thread(mail);
            thread.start();

            /*
            String from = "loshaniramsha01@gmail.com";
            final String username = "loshaniramsha";
            final String password = "200198@AB";
            //provide Mailtrap's host address
            String host = "saji@123";
            //configure Mailtrap's SMTP server details
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            //create the Session object
            Session session = Session.getInstance(props,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                //create a MimeMessage object
                Message message = new MimeMessage(session);
                //set From email field
                message.setFrom(new InternetAddress(from));
                //set To email field
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                //set email subject field
                message.setSubject("Here comes Jakarta Mail!");
                //set the content of the email message
                message.setText(sheduleMassage);
                //send the email message
                Transport.send(message);
                System.out.println("Email Message Sent Successfully");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            */
        }
    }

    public void lblbackOnAction(MouseEvent mouseEvent) {
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

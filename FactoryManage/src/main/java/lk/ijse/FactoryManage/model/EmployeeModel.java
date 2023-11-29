package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.EmployeeDto;
import lk.ijse.FactoryManage.dto.GoogleformDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public static boolean saveEmployee(EmployeeDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getEmployeeId());
        pstm.setObject(2, dto.getName());
        pstm.setObject(3, dto.getType());
        pstm.setObject(4, dto.getEmail());
        pstm.setObject(5, dto.getPhone());
       pstm.setObject(6,dto.getUserId());
       pstm.setObject(7,dto.getScheduleId());
        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }

    public static boolean delete(String employeeId) throws Exception  {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "DELETE FROM employee WHERE employeeId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeId);
        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;
    }

    public static boolean update(EmployeeDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "UPDATE employee SET type=?,name=?,email=?,phone=?,userId=?,scheduleId=? WHERE employeeId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getType());
        pstm.setObject(2, dto.getName());
        pstm.setObject(3, dto.getEmail());
        pstm.setObject(4, dto.getPhone());
        pstm.setObject(5, dto.getUserId());
        pstm.setObject(6, dto.getScheduleId());
        pstm.setObject(7, dto.getEmployeeId());
        boolean isUpdated = pstm.executeUpdate() > 0;
        return isUpdated;
    }

    public static EmployeeDto search(String employeeId)  throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE employeeId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeId);
        EmployeeDto employeeDto = null;
        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            String type = rst.getString(2);
            String name = rst.getString(3);
            String email = rst.getString(4);
            String phone = rst.getString(5);
            String userId = rst.getString(6);
            String scheduleId = rst.getString(7);
            employeeDto = new EmployeeDto(employeeId, type, name, email, phone, userId, scheduleId);
        }
        return employeeDto;
    }

    public static List<EmployeeDto> getAllEmployees() throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
        List<EmployeeDto> list = new ArrayList<>();
        while (rst.next()) {
            String employeeId = rst.getString(1);
            String type = rst.getString(2);
            String name = rst.getString(3);
            String email = rst.getString(4);
            String phone = rst.getString(5);
            String userId = rst.getString(6);
            String scheduleId = rst.getString(7);
            list.add(new EmployeeDto(employeeId, type, name, email, phone, userId, scheduleId));
        }
        return list;
    }
}


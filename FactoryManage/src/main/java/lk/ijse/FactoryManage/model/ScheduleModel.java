package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.ScheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleModel {
    public static boolean saveSchedule(ScheduleDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "INSERT INTO schedule VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getScheduleId());
        pstm.setObject(2, dto.getType());
        pstm.setObject(3, dto.getDate());
        pstm.setObject(4, dto.getPlane());
        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }

    public static boolean deleteSchedule(String scheduleId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "DELETE FROM schedule WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, scheduleId);
        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;
    }

    public static boolean updateSchedule(ScheduleDto dto) throws Exception{
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "UPDATE schedule SET type=?,date=?,plane=? WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getType());
        pstm.setObject(2, dto.getDate());
        pstm.setObject(3, dto.getPlane());
        pstm.setObject(4, dto.getScheduleId());
       return pstm.executeUpdate()>0;
    }

    public static ScheduleDto searchSchedule(String scheduleId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM schedule WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, scheduleId);
        ScheduleDto dto = new ScheduleDto();
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            dto.setScheduleId(rst.getString(1));
            dto.setType(rst.getString(2));
            dto.setDate(rst.getString(3));
            dto.setPlane(rst.getString(4));
        }
        return dto;
    }

    public static List<ScheduleDto> getAllSchedules() throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM schedule";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
        List<ScheduleDto> allSchedules = new ArrayList<>();
        while (rst.next()) {
            ScheduleDto dto = new ScheduleDto();
            dto.setScheduleId(rst.getString(1));
            dto.setType(rst.getString(2));
            dto.setDate(rst.getString(3));
            dto.setPlane(rst.getString(4));
            allSchedules.add(dto);
        }
        return allSchedules;
    }
}

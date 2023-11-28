package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.TargetDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TargetModel {
    public static boolean saveTarget(TargetDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "INSERT INTO target VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getTargetId());
        pstm.setObject(2, dto.getTargetAmount());
        pstm.setObject(3, dto.getReceiveDate());
        pstm.setObject(4, dto.getSendDate());
        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }

    public static boolean deleteTarget(String targetId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "DELETE FROM target WHERE targetId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, targetId);
        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;
    }

    public static boolean updateTarget(TargetDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "UPDATE target SET targetAmount=?,receiveDate=?,sendDate=? WHERE targetId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getTargetAmount());
        pstm.setObject(2, dto.getReceiveDate());
        pstm.setObject(3, dto.getSendDate());
        pstm.setObject(4, dto.getTargetId());
        return pstm.executeUpdate() > 0;
    }

    public static TargetDto searchTarget(String targetId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM target WHERE targetId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, targetId);
        TargetDto dto = new TargetDto();
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            dto.setTargetId(rst.getString(1));
            dto.setTargetAmount(rst.getString(2));
            dto.setReceiveDate(rst.getString(3));
            dto.setSendDate(rst.getString(4));
        }
        return dto;
    }


    public List<TargetDto> getAllTargets() throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM target";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
        List<TargetDto> allTargets = new ArrayList<>();
        while (rst.next()) {
            TargetDto dto = new TargetDto();
            dto.setTargetId(rst.getString(1));
            dto.setTargetAmount(rst.getString(2));
            dto.setReceiveDate(rst.getString(3));
            dto.setSendDate(rst.getString(4));
            allTargets.add(dto);
        }
        return allTargets;
    }
}

package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.ExportableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExportableproductModel {


    public boolean saveExportableproduct(ExportableDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);

            String sql = "INSERT INTO exportableproduct VALUES(?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, dto.getTargetId());
            pstm.setObject(2, dto.getProductId());
            pstm.setObject(3, dto.getStatus());
            pstm.setObject(4, dto.getExportableqty());
            pstm.setObject(5, dto.getAmountexport());

            boolean isExProductSaved = pstm.executeUpdate() > 0;
            if (isExProductSaved) {
                boolean isProductUpdated = ProductModel.updateQuantity(dto);
                if (isExProductSaved){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean deleteExportableproduct(String targetId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "DELETE FROM exportableproduct WHERE targetId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, targetId);
        return pstm.executeUpdate() > 0;
    }

    public boolean updateExportableproduct(ExportableDto dto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "UPDATE exportableproduct SET productId=?,status=?,Exportableqty=?,amountexport=? WHERE targetId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getProductId());
        pstm.setObject(2, dto.getStatus());
        pstm.setObject(3, dto.getExportableqty());
        pstm.setObject(4, dto.getAmountexport());
        pstm.setObject(5, dto.getTargetId());
        return pstm.executeUpdate() > 0;
    }

    public ExportableDto searchExportableproduct(String targetId) throws Exception{
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM exportableproduct WHERE targetId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,targetId);
        ResultSet rst=pstm.executeQuery();
        if (rst.next()){
            return new ExportableDto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
        }
        return null;
    }

    public List<ExportableDto> getAllExportProduct() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM exportableproduct";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst=pstm.executeQuery();
        List<ExportableDto> list=new ArrayList<>();
        while (rst.next()){
            list.add(new ExportableDto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return list;
    }
}

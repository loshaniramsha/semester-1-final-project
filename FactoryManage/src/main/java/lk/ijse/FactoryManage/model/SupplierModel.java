package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.ProductDto;
import lk.ijse.FactoryManage.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static boolean saveSupplier(SupplierDto dto) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="INSERT INTO supplier VALUES(?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getSupplierId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3,dto.getAmmountbrought());
        pstm.setObject(4,dto.getDate());
        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;
    }

    public static boolean deleteSupplier(String supId) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="DELETE FROM supplier WHERE supplierId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,supId);
        boolean isDeleted=pstm.executeUpdate()>0;
        return isDeleted;
    }

    public static SupplierDto searchSupplier(String supId) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM supplier WHERE supplierId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,supId);
        SupplierDto dto=new SupplierDto();
        ResultSet rst=pstm.executeQuery();
        if(rst.next()){
            dto.setSupplierId(rst.getString(1));
            dto.setName(rst.getString(2));
            dto.setAmmountbrought(rst.getString(3));
            dto.setDate(rst.getString(4));
        }
        return dto;
    }

    public static boolean updateSupplier(SupplierDto dto) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="UPDATE supplier SET name=?,ammountbrought=?,date=? WHERE supplierId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getSupplierId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3,dto.getAmmountbrought());
        pstm.setObject(4,dto.getDate());
        return pstm.executeUpdate()>0;
    }

    public static List<SupplierDto> getAllSuppliers() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM supplier");
        ResultSet rst=pstm.executeQuery();
        List<SupplierDto> list=new ArrayList<>();
        while (rst.next()){
            list.add(new SupplierDto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return list;
    }


}

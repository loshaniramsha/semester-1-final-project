package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.MaterialDto;
import lk.ijse.FactoryManage.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialModel {
    public static boolean saveMaterial(MaterialDto dto) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="INSERT INTO material VALUES(?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getMaterialId());
        pstm.setObject(2,dto.getNameOfType());
        pstm.setObject(3,dto.getQty());
        pstm.setObject(4,dto.getSupplierId());
        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;
    }


    public static List<MaterialDto> getAllMaterial() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM material");
        ResultSet rst=pstm.executeQuery();
        List<MaterialDto> list=new ArrayList<>();
        while (rst.next()){
            list.add(new MaterialDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return list;
    }

    public static boolean updateQuantity(String materialId, String ammountuse) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="UPDATE material SET qty=qty-? WHERE materialId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,materialId);
        pstm.setObject(2,Integer.parseInt(ammountuse));
        return pstm.executeUpdate()>0;
    }

    public static boolean deleteMaterial(String materialId) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="DELETE FROM material WHERE materialId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,materialId);
        return pstm.executeUpdate()>0;
    }


    public static boolean updateMaterial(MaterialDto dto) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="UPDATE material SET nameOfType=?,qty=?,supplierId=? WHERE materialId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getNameOfType());
        pstm.setObject(2,dto.getQty());
        pstm.setObject(3,dto.getSupplierId());
        pstm.setObject(4,dto.getMaterialId());
        return pstm.executeUpdate()>0;
    }

    public static MaterialDto searchMaterial(String materialId) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM material WHERE materialId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,materialId);
        ResultSet rst=pstm.executeQuery();
        if (rst.next()){
            return new MaterialDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    public List<MaterialDto> getAllMaterials() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM material";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst=pstm.executeQuery();
        List<MaterialDto> list=new ArrayList<>();
        while (rst.next()){
            list.add(new MaterialDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return list;
    }
}

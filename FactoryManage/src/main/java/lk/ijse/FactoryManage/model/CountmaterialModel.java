package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.CountmaterialDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountmaterialModel {
    public static boolean save(CountmaterialDto countmaterialDto) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
       try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO countmaterial VALUES(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, countmaterialDto.getMaterialId());
            pstm.setObject(2, countmaterialDto.getProductId());
            pstm.setObject(3, countmaterialDto.getAmmountuse());
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                boolean isSaved1 = MaterialModel.updateQuantity(countmaterialDto.getMaterialId(), countmaterialDto.getAmmountuse());
                if (isSaved1) {
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

    public static CountmaterialDto search(String matirialId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM countmaterial WHERE materialId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, matirialId);
        CountmaterialDto countmaterialDto = null;
        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            String materialId = rst.getString(1);
            String productId = rst.getString(2);
            String ammountuse = rst.getString(3);
            countmaterialDto = new CountmaterialDto(materialId, productId, ammountuse);
        }
        return countmaterialDto;
    }

    public static boolean delete(String materialId) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "DELETE FROM countmaterial WHERE materialId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, materialId);
        return pstm.executeUpdate() > 0;
    }

    public static boolean update(CountmaterialDto countmaterialDto) throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "UPDATE countmaterial SET productId=?, ammountuse=? WHERE materialId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, countmaterialDto.getProductId());
        pstm.setObject(2, countmaterialDto.getAmmountuse());
        pstm.setObject(3, countmaterialDto.getMaterialId());
        return pstm.executeUpdate() > 0;
    }

    public List<CountmaterialDto> getAllCountmaterial() throws Exception {
        Connection connection = DbConection.getInstance().getConnection();
        String sql = "SELECT * FROM countmaterial";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
        List<CountmaterialDto> list = new ArrayList<>();
        while (rst.next()) {
            String materialId = rst.getString(1);
            String productId = rst.getString(2);
            String ammountuse = rst.getString(3);
            list.add(new CountmaterialDto(materialId, productId, ammountuse));
        }
        return list;
    }
}

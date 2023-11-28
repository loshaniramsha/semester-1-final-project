package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.Madeproductdto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MadeproductModel {
    public static boolean deleteProduct(String productId) throws Exception{
        Connection connection= DbConection.getInstance().getConnection();
        String sql="DELETE FROM madeproduct WHERE productId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,productId);
        boolean isDeleted=pstm.executeUpdate()>0;
        return isDeleted;
    }

    public Madeproductdto searchMadeProduct(String productId) throws Exception{
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM madeproduct WHERE productId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,productId);
        ResultSet rst=pstm.executeQuery();
        if (rst.next()){
            return new Madeproductdto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    public List<Madeproductdto> getAllMadeProduct()throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM madeproduct";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst=pstm.executeQuery();
        List<Madeproductdto> list=new ArrayList<>();
        while (rst.next()){
            list.add(new Madeproductdto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return list;
    }
}

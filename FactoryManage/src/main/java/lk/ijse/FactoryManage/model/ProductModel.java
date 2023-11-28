package lk.ijse.FactoryManage.model;

//import jdk.internal.platform.CgroupMetrics;
import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.ExportableDto;
import lk.ijse.FactoryManage.dto.Madeproductdto;
import lk.ijse.FactoryManage.dto.MaterialDto;
import lk.ijse.FactoryManage.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    public static List<String> getAllProductIds() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT productId FROM product");
        ResultSet rst=pstm.executeQuery();
        List<String> list=new ArrayList<>();
        while (rst.next()){
            list.add(rst.getString(1));
        }
        return list;
    }


    public static boolean saveProduct(ProductDto dto) throws Exception{
        Connection connection=DbConection.getInstance().getConnection();
        String sql="INSERT INTO product VALUES(?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getProductId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3,dto.getBrand());
        pstm.setObject(4,dto.getQty());
        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;
    }

    public static boolean deleteProduct(String productId) throws Exception{
        Connection connection=DbConection.getInstance().getConnection();
        String sql="DELETE FROM product WHERE productId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,productId);
        boolean isDeleted=pstm.executeUpdate()>0;
        return isDeleted;
    }

    public static boolean updateProduct(ProductDto dto) throws Exception {
        Connection connection=DbConection.getInstance().getConnection();
        String sql="UPDATE product SET name=?,brand=?,qty=? WHERE productId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getName());
        pstm.setObject(2,dto.getBrand());
        pstm.setObject(3,dto.getQty());
        pstm.setObject(4,dto.getProductId());
        boolean isUpdated=pstm.executeUpdate()>0;
        return isUpdated;
    }

    public static ProductDto searchProduct(String productId) throws Exception {
        Connection connection=DbConection.getInstance().getConnection();
        String sql="SELECT * FROM product WHERE productId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,productId);
        ResultSet rst=pstm.executeQuery();
        if (rst.next()){
            return new ProductDto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    public static boolean updateQuantity(ExportableDto dto) throws Exception {
        String productId = dto.getProductId();
        int qty = Integer.parseInt(dto.getAmountexport());

        Connection connection=DbConection.getInstance().getConnection();
        String sql="UPDATE product SET qty = qty-? WHERE productId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,qty);
        pstm.setObject(2,dto.getProductId());
        boolean isUpdated=pstm.executeUpdate()>0;
        return isUpdated;
    }

    public List<ProductDto> getAllProducts() throws Exception {
        Connection connection=DbConection.getInstance().getConnection();
        String sql="SELECT * FROM product";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst=pstm.executeQuery();
        ArrayList<ProductDto> DtoList=new ArrayList<>();
        while (rst.next()){
            DtoList.add(new ProductDto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return DtoList;
    }

    public boolean saveMadeProduct(Madeproductdto dto) throws Exception {
        Connection connection=DbConection.getInstance().getConnection();
        String sql="INSERT INTO madeproduct VALUES(?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getTargetAmount());
        pstm.setObject(2,dto.getProductId());
        pstm.setObject(3,dto.getEmployeeId());
        pstm.setObject(4,dto.getCompleteAmount());
        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;
    }

    public boolean updateMadeProduct(Madeproductdto dto) throws Exception{
        Connection connection=DbConection.getInstance().getConnection();
        String sql="UPDATE madeproduct SET targetAmount=?,completeAmount=? WHERE productId=? AND employeeId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getTargetAmount());
        pstm.setObject(2,dto.getCompleteAmount());
        pstm.setObject(3,dto.getProductId());
        pstm.setObject(4,dto.getEmployeeId());
        boolean isUpdated=pstm.executeUpdate()>0;
        return isUpdated;
    }
}



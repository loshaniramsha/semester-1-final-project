package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    public static boolean saveUser(UserDto dto) throws Exception {
    Connection connection= DbConection.getInstance().getConnection();
    String sql="INSERT INTO user VALUES(?,?,?,?,?)";
    PreparedStatement pstm=connection.prepareStatement(sql);
    pstm.setObject(1,dto.getUserId());
    pstm.setObject(2,dto.getPost());
    pstm.setObject(3,dto.getName());
    pstm.setObject(4,dto.getBranch());
    pstm.setObject(5,dto.getPassword());

    boolean isSaved=pstm.executeUpdate()>0;
    return isSaved;



    }

    public static boolean deleteUser(String userId) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="DELETE FROM user WHERE userId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,userId);
        boolean isDeleted=pstm.executeUpdate()>0;
        return isDeleted;

    }

    public static boolean updateUser(UserDto dto) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="UPDATE user SET post=?,name=?,branch=?,password=? WHERE userId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,dto.getPost());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3,dto.getBranch());
        pstm.setObject(4,dto.getUserId());
        pstm.setObject(5,dto.getPassword());
        return pstm.executeUpdate()>0;
    }

    public static UserDto searchUser(String userId) throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM user WHERE userId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,userId);
        UserDto dto=new UserDto();
        ResultSet rst=pstm.executeQuery();
        if(rst.next()){
            dto.setUserId(rst.getString(1));
            dto.setPost(rst.getString(2));
            dto.setName(rst.getString(3));
            dto.setBranch(rst.getString(4));
            dto.setPassword(rst.getString(5));
        }
        return dto;
    }



    public static List<UserDto> getAllUsers() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM user";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst=pstm.executeQuery();
        ArrayList<UserDto> dtoList=new ArrayList<>();
        while (rst.next()){
            UserDto dto=new UserDto();
            dto.setUserId(rst.getString(1));
            dto.setPost(rst.getString(2));
            dto.setName(rst.getString(3));
            dto.setBranch(rst.getString(4));
            dto.setPassword(rst.getString(5));
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static String generateNextUserId() throws Exception {
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT userId FROM user ORDER BY userId DESC LIMIT 1";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst=pstm.executeQuery();
        if(rst.next()){
            return String.format("U%03d", Integer.parseInt(rst.getString(1).substring(1))+1);
        }
        return "U001";
    }
}

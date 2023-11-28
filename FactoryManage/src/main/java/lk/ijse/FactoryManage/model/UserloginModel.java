package lk.ijse.FactoryManage.model;

import lk.ijse.FactoryManage.db.DbConection;
import lk.ijse.FactoryManage.dto.UserDto;
import lk.ijse.FactoryManage.dto.UserloginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserloginModel {

    public static boolean verifyUser(UserloginDto userloginDto) throws Exception {
        if (userloginDto.getPassword() == null) {
            return false;
        }
        Connection connection= DbConection.getInstance().getConnection();
        String sql="SELECT * FROM user WHERE name=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,userloginDto.getUserName());
        UserDto findedUser=null;
        ResultSet rst=pstm.executeQuery();
        if(rst.next()){
            findedUser=new UserDto(
              rst.getString(1),
              rst.getString(2),
              rst.getString(3),
              rst.getString(4),
              rst.getString(5)
            );
        }
        if (findedUser!=null){
            if (findedUser.getPassword() != null && findedUser.getPassword().equals(userloginDto.getPassword())) {
                return true;
            }
        }
        return false;
    }
}

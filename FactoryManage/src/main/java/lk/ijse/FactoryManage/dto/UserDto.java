package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserDto {
    private String userId;
    private String post;
    private String name;
    private String branch;
    private String password;
}

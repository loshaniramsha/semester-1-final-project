package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTm {
    private String userId;
    private String post;
    private String name;
    private String branch;
    private String password;
}

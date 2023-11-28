package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class GoogleformDto {
    private String employeeId;
    private String type;
    private String name;
    private String email;
    private String phone;
    private String userId;
    private String scheduleId;

}

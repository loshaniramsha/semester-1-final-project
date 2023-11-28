package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeTm {
    private String employeeId;
    private String type;
    private String name;
    private String email;
    private String phone;
    private String userId;
    private String scheduleId;
}

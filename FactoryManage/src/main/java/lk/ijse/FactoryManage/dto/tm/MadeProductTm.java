package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MadeProductTm {
    private String targetId;
    private String productId;
    private String employeeId;
    private int completeAmount;
}

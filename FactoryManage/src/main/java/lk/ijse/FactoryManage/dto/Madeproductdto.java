package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Madeproductdto {

    private String targetAmount;
    private String productId;
    private String employeeId;
    private String completeAmount;
}
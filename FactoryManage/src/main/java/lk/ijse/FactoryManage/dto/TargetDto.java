package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TargetDto {
    private String targetId;
    private String targetAmount;
    private String receiveDate;
    private String sendDate;
}

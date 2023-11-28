package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TargetTm {
    private String targetId;
    private String targetAmount;
    private String receiveDate;
    private String sendDate;
}

package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleTm {
    private String scheduleId;
    private String type;
    private String date;
    private String plane;

}

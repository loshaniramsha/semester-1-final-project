package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ScheduleDto {
    private String scheduleId;
    private String type;
    private String date;
    private String plane;
}

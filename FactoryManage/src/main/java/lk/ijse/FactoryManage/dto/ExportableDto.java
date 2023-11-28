package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ExportableDto {
    private String targetId;
    private String productId;
    private String status;
    private String Exportableqty;
    private String  amountexport;
}

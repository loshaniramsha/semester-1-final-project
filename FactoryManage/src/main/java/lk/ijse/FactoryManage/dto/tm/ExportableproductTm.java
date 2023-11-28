package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExportableproductTm {
    private String targetId;
    private String productId;
    private String status;
    private int Exportableqty;
    private int amountexport;
}

package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierTm {
    private String supplierId;
    private String name;
    private String ammountbrought;
    private String date;
}

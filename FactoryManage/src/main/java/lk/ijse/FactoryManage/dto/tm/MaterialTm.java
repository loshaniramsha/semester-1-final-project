package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialTm {
    private String materialId;
    private String nameOfType;
    private String qty;
    private String supplierId;
}

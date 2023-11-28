package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialDto {
   // public Object cmbSupplierId;
    private String materialId;
    private String nameOfType;
    private String qty;
    private String supplierId;
}
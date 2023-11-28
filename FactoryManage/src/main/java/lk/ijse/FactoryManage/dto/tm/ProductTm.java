package lk.ijse.FactoryManage.dto.tm;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTm {
    private String productId;
    private String name;
    private String brand;
    private String qty;
}

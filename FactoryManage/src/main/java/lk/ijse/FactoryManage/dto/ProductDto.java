package lk.ijse.FactoryManage.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private String productId;
    private String name;
    private String brand;
    private String qty;
}

package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemDTO {
    private String id;
    private String itemName;
    private String qtyInStock;
    private String unitPrice;
}

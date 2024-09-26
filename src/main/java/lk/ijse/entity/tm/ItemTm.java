package lk.ijse.entity.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class ItemTm {
    private String id;
    private String itemName;
    private String qtyInStock;
    private String unitPrice;
}

package fit.iuh.edu.vn.lab07week07.backend.pks;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter @Getter
public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime price_date_time;
}

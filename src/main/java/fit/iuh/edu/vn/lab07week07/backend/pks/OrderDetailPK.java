package fit.iuh.edu.vn.lab07week07.backend.pks;


import fit.iuh.edu.vn.lab07week07.backend.models.Order;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

@Setter @Getter
public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}

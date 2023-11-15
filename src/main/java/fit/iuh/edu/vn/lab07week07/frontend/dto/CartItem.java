package fit.iuh.edu.vn.lab07week07.frontend.dto;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CartItem {
    private Product product;
    private int amount;
}

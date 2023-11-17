package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;
import fit.iuh.edu.vn.lab07week07.backend.pks.ProductPricePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ProductPriceResponsitory extends JpaRepository<ProductPrice, Long> {
}

package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductResponsitory extends JpaRepository<Product,Long> {
}

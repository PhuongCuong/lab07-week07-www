package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductResponsitory extends JpaRepository<Product,Long> {
    public Page<Product> findAllbyStatus(ProductStatus status1, ProductStatus status2, Pageable pageable);
}

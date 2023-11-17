package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageResponsitory extends JpaRepository<ProductImage,Long> {
}

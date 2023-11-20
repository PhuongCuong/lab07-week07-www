package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductResponsitory extends JpaRepository<Product,Long> {
//    public Page<Product> findAllbyStatus(ProductStatus status1, ProductStatus status2, Pageable pageable);


//    @Query("SELECT new fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel(p, pi, pr) " +
//            "FROM Product p " +
//            "INNER JOIN ProductImage pi ON p.product_id = pi.product.product_id " +
//            "INNER JOIN ProductPrice pr ON p.product_id = pr.product.product_id " +
//            "WHERE p.status = :status1 OR p.status = :status2 " +
//            "AND pr.price_date_time = (SELECT MAX(pr2.price_date_time) FROM ProductPrice pr2 WHERE pr2.product.product_id = p.product_id)")
@Query("SELECT new fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel(p, pi, pr) " +
        "FROM Product p " +
        "INNER JOIN ProductImage pi ON p.product_id = pi.product.product_id " +
        "INNER JOIN ProductPrice pr ON p.product_id = pr.product.product_id " +
        "WHERE pr.price_date_time = (SELECT MAX(pr2.price_date_time) FROM ProductPrice pr2 WHERE pr2.product.product_id = p.product_id) " +
        "and p.status = :status1 or p.status = :status2")
    public Page<ProductViewModel> FindAllWithDescriptionQuery(ProductStatus status1, ProductStatus status2, Pageable pageable);

    @Query("select new fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel(p,pi,pr) from Product p " +
            "inner join ProductPrice pr on p.product_id = pr.product.product_id " +
            "inner join ProductImage pi on p.product_id = pi.product.product_id" +
            " where pr.price_date_time = (SELECT MAX(pr2.price_date_time) FROM ProductPrice pr2 WHERE pr2.product.product_id = p.product_id) " +
            "and p.product_id = :id")
    Optional<ProductViewModel> findDesProductById(Long id);

}

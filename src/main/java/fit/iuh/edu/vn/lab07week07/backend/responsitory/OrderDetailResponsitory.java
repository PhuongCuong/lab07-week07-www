package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.OrderDetail;
import fit.iuh.edu.vn.lab07week07.backend.pks.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailResponsitory extends JpaRepository<OrderDetail, OrderDetailPK> {
}

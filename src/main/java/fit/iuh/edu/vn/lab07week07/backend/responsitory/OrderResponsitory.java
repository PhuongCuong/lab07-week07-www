package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderResponsitory extends JpaRepository<Order,Long> {
}

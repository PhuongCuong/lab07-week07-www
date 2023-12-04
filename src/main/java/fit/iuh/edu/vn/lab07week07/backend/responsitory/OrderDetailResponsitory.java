package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.OrderDetail;
import fit.iuh.edu.vn.lab07week07.backend.pks.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailResponsitory extends JpaRepository<OrderDetail, OrderDetailPK> {

    @Query("select od from OrderDetail od where od.order.order_id = :orderId")
    List<OrderDetail> findOrderDetailByOrderId(Long orderId);
}

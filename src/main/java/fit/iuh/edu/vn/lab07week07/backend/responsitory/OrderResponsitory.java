package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.Order;
import fit.iuh.edu.vn.lab07week07.frontend.dto.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderResponsitory extends JpaRepository<Order,Long> {

    @Query("select new fit.iuh.edu.vn.lab07week07.frontend.dto.OrderInfo(c,e,o) from Order o " +
            "inner join Customer c on o.customer.id = c.id " +
            "inner join Employee e on o.employee.id = e.id")
    List<OrderInfo> findAllOrderInfo();
}

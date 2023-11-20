package fit.iuh.edu.vn.lab07week07.backend.services;

import fit.iuh.edu.vn.lab07week07.backend.models.Order;
import fit.iuh.edu.vn.lab07week07.backend.models.OrderDetail;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.OrderDetailResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.OrderResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderResponsitory orderResponsitory;
    @Autowired
    private OrderDetailResponsitory orderDetailResponsitory;

    @Transactional
    public void SaveOrder(Order order, List<OrderDetail> orderDetails){
        orderResponsitory.save(order);

        for (OrderDetail orderDetail:orderDetails
             ) {
            orderDetailResponsitory.save(orderDetail);
        }
    }
}

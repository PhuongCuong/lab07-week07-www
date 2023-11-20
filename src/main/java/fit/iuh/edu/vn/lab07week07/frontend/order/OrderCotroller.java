package fit.iuh.edu.vn.lab07week07.frontend.order;

import fit.iuh.edu.vn.lab07week07.backend.models.*;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.CustomerResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductImageResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.OrderService;
import fit.iuh.edu.vn.lab07week07.frontend.dto.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("order")
public class OrderCotroller {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerResponsitory customerResponsitory;
    @Autowired
    private ProductResponsitory productResponsitory;

    @GetMapping("")
    public String getorder(Model model){
        return "admin/order/listorder";
    }

    @GetMapping("/pay")
    public String getorderPay(Model model){
        model.addAttribute("nameCus",new Customer());
        return "client/formatPayment";
    }

    @PostMapping("/action/pay")
    public String getActionPay(@ModelAttribute("nameCus") Customer customer, HttpSession session){
        try{
        if(customer.getEmail() != null){
                Map<Long, CartItem> o = (Map<Long, CartItem>) session.getAttribute("items");
                Employee employee = (Employee) session.getAttribute("loginEmp");
                System.out.println("check name" + customer.getEmail());
                Optional<Customer> optional = customerResponsitory.findbyemail(customer.getEmail());
                Customer customer1 = null;
                if(optional.isPresent()){
                    customer1 = optional.get();
                }
                Order order = new Order(LocalDateTime.now(),employee,customer1);
                List<OrderDetail> orderDetails = new ArrayList<>();
                if(o != null){
                    for (Map.Entry<Long, CartItem> entry:o.entrySet()
                         ) {
                        Long productId = entry.getValue().getProduct().getProduct_id();
                        Product product = productResponsitory.findById(productId).get();
                        OrderDetail orderDetail = new OrderDetail(entry.getValue().getAmount(), entry.getValue().getPrice().getPrice(),
                                "add order detail",order,product);
                        orderDetails.add(orderDetail);
                    }
                }
                orderService.SaveOrder(order,orderDetails);
                session.removeAttribute("itemsOnCart");
                session.removeAttribute("items");
                session.removeAttribute("total");
        }
        }catch (Exception exception){
            System.out.println("check error" + exception);
        }
        return "redirect:/home";
    }
}

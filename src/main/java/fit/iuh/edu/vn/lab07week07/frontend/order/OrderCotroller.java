package fit.iuh.edu.vn.lab07week07.frontend.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderCotroller {
    @GetMapping("/order")
    public String getorder(Model model){
        return "admin/order/listorder";
    }
}

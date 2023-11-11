package fit.iuh.edu.vn.lab07week07.frontend.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerCotroller {
    @GetMapping("/customer")
    public String getcustomer(Model model){
        return "admin/customer/listcustomer";
    }

}

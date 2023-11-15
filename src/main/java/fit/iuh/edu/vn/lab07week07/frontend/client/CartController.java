package fit.iuh.edu.vn.lab07week07.frontend.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/home/cart")
    public String showCart(){
        return "client/cart";
    }
}

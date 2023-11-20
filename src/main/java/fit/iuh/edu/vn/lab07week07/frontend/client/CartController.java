package fit.iuh.edu.vn.lab07week07.frontend.client;

import fit.iuh.edu.vn.lab07week07.frontend.dto.CartItem;
import fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String showCart(){
        return "client/cart";
    }

    @GetMapping("/checkout/cart")
    public String checkoutCart(HttpSession session){
        String url = "";
        if(session.getAttribute("loginCus") == null && session.getAttribute("loginEmp") == null){
            url = "redirect:/login";
        }else {

            Map<Long,CartItem> o = (Map<Long, CartItem>) session.getAttribute("items");
            if(o ==null){
                url = "redirect:/cart";
            }else{
                url = "redirect:/order/pay";
            }
        }
        return url;
    }
}

package fit.iuh.edu.vn.lab07week07.frontend.client;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.ProductService;
import fit.iuh.edu.vn.lab07week07.frontend.dto.CartItem;
import fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private ProductResponsitory productResponsitory;
    @Autowired
    private ProductService productService;
    @GetMapping("/home")
    public String getHomeIndex(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);

//        Page<Product> candidatePage = productService.findAllpage(currentPage - 1,
//                pageSize, "name", "asc");

        Page<ProductViewModel> candidatePage = productService.findAllpage(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productHomePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageHomeNumbers", pageNumbers);
        }
        return "client/index";
    }

    @GetMapping("/add-cart/{id}")
    public String addCartItem(Model model, HttpSession session, @PathVariable("id") Long id){
        Object obj = session.getAttribute("items");

        ProductViewModel productViewModel = productResponsitory.findDesProductById(id).get();

        HashMap<Long, CartItem> cart = null;
        if(obj == null){
            cart = new HashMap<>();
        }else{
            cart = (HashMap<Long, CartItem>) obj;
        }

        CartItem cartItem = new CartItem(productViewModel.getProduct(),1,productViewModel.getProductImage(),productViewModel.getPrice());
        if(cart.get(productViewModel.getProduct().getProduct_id()) != null){
            CartItem cartupdate = cart.get(productViewModel.getProduct().getProduct_id());
            cartupdate.setAmount(cartupdate.getAmount() +1);
            cart.put(productViewModel.getProduct().getProduct_id(),cartupdate);
        }else{
            cart.put(productViewModel.getProduct().getProduct_id(),cartItem);
        }

        double tong = 0;

        if(cart.size()>0){
            for (CartItem item: cart.values()
                 ) {
                tong += item.getPrice().getPrice();
            }
        }

        session.setAttribute("total",tong);

        session.setAttribute("items",cart);

        session.setAttribute("itemsOnCart",cart.size());

        return "redirect:/home";
    }

}

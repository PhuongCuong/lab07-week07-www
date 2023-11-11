package fit.iuh.edu.vn.lab07week07.frontend.product;

import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductCotroller {
    @Autowired
    private ProductResponsitory productResponsitory;

    @GetMapping("/products")
    public String getallProduct(Model model){
        model.addAttribute("products",productResponsitory.findAll());
        return "admin/product/listproduct";
    }

    @GetMapping("/product")
    public String getproduct(Model model){
        model.addAttribute("product","hello product");
        return "admin/product/listproduct";
    }

}

package fit.iuh.edu.vn.lab07week07.frontend.product;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

public class ProductCotroller {
    @Autowired
    private ProductResponsitory productResponsitory;
    @Autowired
    private ProductService productService;


    @GetMapping("/product")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Product> candidatePage = productService.findAllpage(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productPage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/product/listproduct";
    }

    @GetMapping("/product/add-form-product")
    public String addformproduct(Model model){
        model.addAttribute("product",new Product());
        return "admin/product/addproduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product){
        productResponsitory.save(product);
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        Optional<Product> optional = productResponsitory.findById(productId);
        if(optional.isPresent()){
            Product product = optional.get();
            product.setStatus(ProductStatus.TERMINATED);
            productResponsitory.save(product);
        }
        return "redirect:/product";
    }

    @GetMapping("/product/update/{productId}")
    public String updateformProduct(@PathVariable("productId") Long productId,Model model){
        Optional<Product> optional = productResponsitory.findById(productId);
        if(optional.isPresent()){
            model.addAttribute("updateproduct",optional.get());
        }
        return "admin/product/updateproduct";
    }

    @PostMapping("/product/update")
    public String updateProduct(@ModelAttribute("updateproduct") Product product){
        productResponsitory.save(product);
        return "redirect:/product";
    }




}

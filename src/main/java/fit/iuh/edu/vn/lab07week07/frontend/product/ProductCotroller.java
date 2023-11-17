package fit.iuh.edu.vn.lab07week07.frontend.product;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductImageResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductPriceResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.ProductService;
import fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Autowired
    private ProductPriceResponsitory productPriceResponsitory;
    @Autowired
    private ProductImageResponsitory productImageResponsitory;


    @GetMapping("/product")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        System.out.println("check" + productService.findAllpage(currentPage - 1,
                pageSize, "name", "asc"));

//        Page<Product> candidatePage = productService.findAllpage(currentPage - 1,
//                pageSize, "name", "asc");

        Page<ProductViewModel> candidatePage = productService.findAllpage(currentPage - 1,
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
        model.addAttribute("product",new ProductViewModel());
        return "admin/product/addproduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") ProductViewModel productViewModel){
        productResponsitory.save(productViewModel.getProduct());
        ProductPrice price = new ProductPrice(LocalDateTime.now(),productViewModel.getPrice().getPrice(),"note add");
        price.setProduct(productViewModel.getProduct());
        productPriceResponsitory.save(price);
        ProductImage productImage = new ProductImage("assest/"+productViewModel.getProductImage().getPath(),"productImg sample");
        productImage.setProduct(productViewModel.getProduct());
        productImageResponsitory.save(productImage);
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        Optional<ProductViewModel> optional = productResponsitory.findDesProductById(productId);
        if(optional.isPresent()){
            ProductViewModel viewModel = optional.get();
            viewModel.getProduct().setStatus(ProductStatus.TERMINATED);
            productResponsitory.save(viewModel.getProduct());
        }
        return "redirect:/product";
    }

    @GetMapping("/product/update/{productId}")
    public String updateformProduct(@PathVariable("productId") Long productId,Model model){
        Optional<ProductViewModel> optional = productResponsitory.findDesProductById(productId);

        if(optional.isPresent()){
            System.out.println("check"+optional);
            model.addAttribute("updateproduct",optional.get());
        }
        return "admin/product/updateproduct";
    }

    @PostMapping("/product/update")
    public String updateProduct(@ModelAttribute("updateproduct") ProductViewModel productViewModel){
        productResponsitory.save(productViewModel.getProduct());
        ProductPrice price = new ProductPrice(LocalDateTime.now(),productViewModel.getPrice().getPrice(),"note update");
        price.setProduct(productViewModel.getProduct());
        productPriceResponsitory.save(price);
        Optional<ProductImage> optional = productImageResponsitory.findById(productViewModel.getProductImage().getImage_id());
        if(optional.isPresent()){
            ProductImage productImage = optional.get();
            productImage.setPath("assest/"+productViewModel.getProductImage().getPath());
            productImageResponsitory.save(productImage);
        }
        return "redirect:/product";
    }




}

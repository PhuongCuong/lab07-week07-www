package fit.iuh.edu.vn.lab07week07.frontend.product;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        int pageSize = size.orElse(10);

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


}

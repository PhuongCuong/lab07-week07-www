package fit.iuh.edu.vn.lab07week07.frontend.customer;

import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.CustomerResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.CustomerService;
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
@RequestMapping("customer")
public class CustomerCotroller {

    @Autowired
    private CustomerResponsitory customerResponsitory;
    @Autowired
    private CustomerService customerService;
    @GetMapping("")
    public String getcustomer(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);


        Page<Customer> candidatePage = customerService.findAllpageCus(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("customerPage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/customer/listcustomer";
    }

    @GetMapping("/add-form-customer")
    public String getformCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "admin/customer/addcustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer){
        if(customer!=null){
            customerResponsitory.save(customer);
        }
        return "redirect:/customer";
    }

    @GetMapping("/update/{customerId}")
    public String updateformProduct(@PathVariable("customerId") Long customerId,Model model){
        Optional<Customer> optional = customerResponsitory.findById(customerId);

        if(optional.isPresent()){
            model.addAttribute("updatecus",optional.get());
        }
        return "admin/customer/updatecustomer";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("updatecus") Customer customer){
        if(customer!=null){
            customerResponsitory.save(customer);
        }
        return "redirect:/customer";
    }
}

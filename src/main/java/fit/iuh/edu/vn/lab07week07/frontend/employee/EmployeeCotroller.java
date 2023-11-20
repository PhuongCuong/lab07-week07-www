package fit.iuh.edu.vn.lab07week07.frontend.employee;

import fit.iuh.edu.vn.lab07week07.backend.enums.EmployeeStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.models.Employee;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.EmployeeResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.services.EmployeeService;
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
@RequestMapping("employee")
public class EmployeeCotroller {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeResponsitory employeeResponsitory;

    @GetMapping("")
    public String getemployee(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);


        Page<Employee> candidatePage = employeeService.findAllpageEmp(currentPage - 1,
                pageSize, "fullname", "asc");

        model.addAttribute("employeePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/employee/listemployee";
    }

    @GetMapping("/add-form-employee")
    public String getaddformEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return "admin/employee/addemployee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        if(employee!=null){
            employeeResponsitory.save(employee);
        }
        return "redirect:/employee";
    }

    @GetMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId){
        Optional<Employee> optional = employeeResponsitory.findById(employeeId);
        if(optional.isPresent()){
            Employee employee = optional.get();
            employee.setStatus(EmployeeStatus.TERMINATED);
            employeeResponsitory.save(employee);
        }
        return "redirect:/employee";
    }

    @GetMapping("/update/{employeeId}")
    public String updateformEmployee(@PathVariable("employeeId") Long employeeId,Model model){
        Optional<Employee> optional = employeeResponsitory.findById(employeeId);

        if(optional.isPresent()){
            model.addAttribute("updateemp",optional.get());
        }
        return "admin/employee/updateemployee";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("updateemp") Employee employee){
       if(employee != null){
           employeeResponsitory.save(employee);
       }
       return "redirect:/employee";
    }

}

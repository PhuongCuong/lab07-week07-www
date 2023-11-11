package fit.iuh.edu.vn.lab07week07.frontend.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeCotroller {
    @GetMapping("/employee")
    public String getemployee(Model model){
        return "admin/employee/listemployee";
    }
}

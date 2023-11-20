package fit.iuh.edu.vn.lab07week07.frontend.client;

import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.models.Employee;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.CustomerResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.EmployeeResponsitory;
import fit.iuh.edu.vn.lab07week07.frontend.dto.LoginAccount;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private CustomerResponsitory customerResponsitory;
    @Autowired
    private EmployeeResponsitory employeeResponsitory;

    @GetMapping("")
    public String getLogin(Model model,HttpSession session){
        model.addAttribute("loginaccount",new LoginAccount());

        if(session.getAttribute("loginEmp") != null){
            session.removeAttribute("loginEmp");
        }
        if(session.getAttribute("loginCus") != null){
            session.removeAttribute("loginCus");
        }
        return "client/login";
    }

    @GetMapping("/account")
    public String getInfoAccount(@ModelAttribute("loginaccount") LoginAccount loginAccount, HttpSession session){
        String url = "client/login";
        Optional<Employee> optionalemp = employeeResponsitory.findLoginEmp(loginAccount.getEmail(),loginAccount.getSoDt());
        Optional<Customer> optionalcus = customerResponsitory.findLoginCus(loginAccount.getEmail(),loginAccount.getSoDt());


        if(!optionalemp.isEmpty()){
            session.setAttribute("loginEmp",optionalemp.get());
            url = "redirect:/home";
        }else if(!optionalcus.isEmpty()){
            session.setAttribute("loginCus",optionalcus.get());
            url = "redirect:/home";
        }else {
            url = "client/login";
        }
        return url;
    }

}

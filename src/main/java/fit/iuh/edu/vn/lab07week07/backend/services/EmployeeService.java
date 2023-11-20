package fit.iuh.edu.vn.lab07week07.backend.services;

import fit.iuh.edu.vn.lab07week07.backend.enums.EmployeeStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.models.Employee;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.EmployeeResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeResponsitory employeeResponsitory;

    public Page<Employee> findAllpageEmp(int pageNo, int pageSize, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return employeeResponsitory.FindAllDescriptionQueryEmp(EmployeeStatus.ACTIVE,EmployeeStatus.IN_ACTIVE,pageable);
    }
}

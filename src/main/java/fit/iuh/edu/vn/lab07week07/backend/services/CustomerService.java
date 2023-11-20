package fit.iuh.edu.vn.lab07week07.backend.services;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.CustomerResponsitory;
import fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerResponsitory customerResponsitory;

    public Page<Customer> findAllpageCus(int pageNo, int pageSize, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return customerResponsitory.findAll(pageable);
    }

}

package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerResponsitory extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where c.email = :email and c.phone = :phone")
    Optional<Customer> findLoginCus(String email, String phone);

    @Query("select c from Customer c where c.email = :email")
    Optional<Customer> findbyemail(String email);

}

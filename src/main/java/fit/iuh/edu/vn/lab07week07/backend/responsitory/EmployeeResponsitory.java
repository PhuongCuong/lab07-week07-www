package fit.iuh.edu.vn.lab07week07.backend.responsitory;

import fit.iuh.edu.vn.lab07week07.backend.enums.EmployeeStatus;
import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Employee;
import fit.iuh.edu.vn.lab07week07.frontend.dto.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeResponsitory extends JpaRepository<Employee,Long> {

    @Query("select e from Employee e where e.status = :status1 or e.status = :status2")
    Page<Employee> FindAllDescriptionQueryEmp(EmployeeStatus status1, EmployeeStatus status2, Pageable pageable);

    @Query("select e from Employee e where e.email = :email and e.phone = :phone")
    Optional<Employee> findLoginEmp(String email,String phone);

}

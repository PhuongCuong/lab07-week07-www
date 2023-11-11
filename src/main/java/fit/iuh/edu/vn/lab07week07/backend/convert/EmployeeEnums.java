package fit.iuh.edu.vn.lab07week07.backend.convert;

import fit.iuh.edu.vn.lab07week07.backend.enums.EmployeeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.metamodel.Attribute;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeEnums implements AttributeConverter<EmployeeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        if(employeeStatus == null){
            return null;
        }
        return employeeStatus.getValue();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer integer) {
        if (integer == null) {

            return null;

        }
        return Stream.of(EmployeeStatus.values())
                .filter(c->c.getValue() == integer)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}

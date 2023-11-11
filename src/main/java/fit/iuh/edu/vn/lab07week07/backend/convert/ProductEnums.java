package fit.iuh.edu.vn.lab07week07.backend.convert;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductEnums implements AttributeConverter<ProductStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        if(productStatus == null){
            return null;

        }
        return productStatus.getValue();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;

        }
        return Stream.of(ProductStatus.values())
        .filter(c->c.getValue() == integer)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}

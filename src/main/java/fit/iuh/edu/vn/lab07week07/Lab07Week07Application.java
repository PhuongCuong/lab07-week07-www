package fit.iuh.edu.vn.lab07week07;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;

import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab07Week07Application {

    @Autowired
    private ProductResponsitory productResponsitory;

    public static void main(String[] args) {
        SpringApplication.run(Lab07Week07Application.class, args);
    }

//    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            Faker faker = new Faker();
            Device device = faker.device();
            for(int i = 0;i<100;i++){
                Product product = new Product(
                        device.modelName(),faker.lorem().paragraph(30),
                        "piece",device.manufacturer(), ProductStatus.ACTIVE
                );
                productResponsitory.save(product);
            }
        };
    }

}

package fit.iuh.edu.vn.lab07week07;

import fit.iuh.edu.vn.lab07week07.backend.enums.ProductStatus;
import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductImageResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductPriceResponsitory;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Lab07Week07Application {

    @Autowired
    private ProductResponsitory productResponsitory;
    @Autowired
    private ProductPriceResponsitory productPriceResponsitory;
    @Autowired
    private ProductImageResponsitory productImageResponsitory;

    public static void main(String[] args) {
        SpringApplication.run(Lab07Week07Application.class, args);
    }

//    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Faker faker = new Faker();
            Random random = new Random();
            Device device = faker.device();
            for (int i = 0; i < 100; i++) {
                Product product = new Product(
                        device.modelName(), faker.lorem().paragraph(30),
                        "piece", device.manufacturer(), ProductStatus.ACTIVE
                );
                double min = 1500;
                double max = 2000;
                double randoms = min + (max-min) + random.nextDouble();

                ProductPrice price = new ProductPrice(
                  LocalDateTime.now(),randoms,"note" +i
                );
                price.setProduct(product);

                ProductImage productImage = new ProductImage("assest/productImg.png","productImg sample");
                productImage.setProduct(product);

                productResponsitory.save(product);
                productPriceResponsitory.save(price);
                productImageResponsitory.save(productImage);
            }
        };
    }

}

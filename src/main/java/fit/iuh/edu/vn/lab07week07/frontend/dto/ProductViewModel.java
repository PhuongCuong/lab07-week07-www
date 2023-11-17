package fit.iuh.edu.vn.lab07week07.frontend.dto;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;

public class ProductViewModel {
    private Product product;
    private ProductImage productImage;
    private ProductPrice price;

    public ProductViewModel() {
    }

    public ProductViewModel(Product product, ProductImage productImage, ProductPrice price) {
        this.product = product;
        this.productImage = productImage;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public ProductPrice getPrice() {
        return price;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "product=" + product +
                ", productImage=" + productImage +
                ", price=" + price +
                '}';
    }
}

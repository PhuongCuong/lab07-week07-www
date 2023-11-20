package fit.iuh.edu.vn.lab07week07.frontend.dto;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductImage;
import fit.iuh.edu.vn.lab07week07.backend.models.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CartItem {
    private Product product;
    private int amount;
    private ProductImage productImage;
    private ProductPrice price;

    public CartItem() {
    }

    public CartItem(Product product, int amount, ProductImage productImage, ProductPrice price) {
        this.product = product;
        this.amount = amount;
        this.productImage = productImage;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
        return "CartItem{" +
                "product=" + product +
                ", amount=" + amount +
                ", productImage=" + productImage +
                ", price=" + price +
                '}';
    }
}

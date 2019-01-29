package test.tony.cloud.service.web.mall.service;

import org.springframework.stereotype.Component;
import test.tony.cloud.product.dto.Product;

import java.util.List;

@Component
public class ProductServiceHystrix implements ProductService {
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product loadByItemCode(String itemCode) {
        return null;
    }

    @Override
    public String productHello() {
        return "Sorry Product Error!";
    }
}

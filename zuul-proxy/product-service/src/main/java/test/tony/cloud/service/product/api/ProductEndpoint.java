/***
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.tony.cloud.service.product.api;

import test.tony.cloud.product.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Product API服务
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@RestController
@RequestMapping("/products")
@RefreshScope
public class ProductEndpoint {
    protected Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return this.buildProducts();
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public Product detail(@PathVariable String itemCode) {
        List<Product> products = this.buildProducts();
        for (Product product : products) {
            if (product.getItemCode().equalsIgnoreCase(itemCode))
                return product;
        }
        return null;
    }

    protected List<Product> buildProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("item-1", "测试商品-1", "Test", 100));
        products.add(new Product("item-2", "测试商品-2", "Test", 200));
        products.add(new Product("item-3", "测试商品-3", "Test", 300));
        products.add(new Product("item-4", "测试商品-4", "Test", 400));
        products.add(new Product("item-5", "测试商品-5", "Test", 500));
        products.add(new Product("item-6", "测试商品-6", "Test", 600));
        return products;
    }
}

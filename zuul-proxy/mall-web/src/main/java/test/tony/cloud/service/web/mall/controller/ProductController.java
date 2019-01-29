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
package test.tony.cloud.service.web.mall.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.tony.cloud.product.dto.Product;
import test.tony.cloud.service.web.mall.service.ProductService;

import java.util.List;


/**
 * Product Controller
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@RestController
@RequestMapping("/products")
@RefreshScope
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello(){
        return this.productService.productHello();
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return this.productService.findAll();
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public Product detail(@PathVariable String itemCode) {
        return this.productService.loadByItemCode(itemCode);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser")
    @RequestMapping("/getUser")
    public String getUser() {
        String forObject = restTemplate.getForObject("http://PRODUCT-SERVICE/getUser", String.class);
        return forObject;
    }

    private String getDefaultUser() {
        System.out.println("熔断，默认回调函数");
        return "{\"username\":\"admin\",\"age\":\"-1\"}";
    }
}

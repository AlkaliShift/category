package cn.shenghui.category.controller;

import cn.shenghui.category.dao.model.Product;
import cn.shenghui.category.rest.request.CreateProductRequest;
import cn.shenghui.category.rest.response.ProductResponse;
import cn.shenghui.category.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:15
 */
@RestController
@Api(value = "Product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "enter one product", notes = "statusCode = 0, failed; " +
            "statusCode = 1, success and return product information")
    @PostMapping("/createProduct")
    public ProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest){
        Product product = new Product();
        product.setProduct_name(createProductRequest.getProduct_name());
        product.setProduct_category(createProductRequest.getProduct_category());
        product.setProduct_num(createProductRequest.getProduct_num());
        product = productService.createProduct(product);

        ProductResponse response = new ProductResponse();
        response.setProduct_id(product.getProduct_id());
        response.setProduct_name(product.getProduct_name());
        response.setProduct_category(product.getProduct_category());
        response.setProduct_num(product.getProduct_num());
        response.setStatusCode(1);
        return response;
    }
}

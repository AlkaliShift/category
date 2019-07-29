package cn.shenghui.category.service;

import cn.shenghui.category.dao.mapper.ProductMapper;
import cn.shenghui.category.dao.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:16
 */
@Service
public class ProductService {

    private ProductMapper productMapper;

    @Autowired
    public void setAccountMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * create one product
     * @param product
     * @return
     */
    public Product createProduct(Product product) {
        int productId = (int)(1+Math.random()*(100-1+1));
        product.setProductId(productId);
        product.setProductNum(1000);
        product = productMapper.createProduct(product);
        return product;
    }
}

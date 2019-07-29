package cn.shenghui.category.dao.mapper;

import cn.shenghui.category.dao.model.Product;
import org.mapstruct.Mapper;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:18
 */
@Mapper
public interface ProductMapper {
    /**
     * create product
     * @param product
     * @return
     */
    Product createProduct(Product product);
}

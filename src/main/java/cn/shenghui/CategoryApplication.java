package cn.shenghui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 11:08
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.shenghui.category.dao.mapper")
@EnableTransactionManagement
public class CategoryApplication {
    public static void main(String[] args){
        SpringApplication.run(CategoryApplication.class, args);
    }
}

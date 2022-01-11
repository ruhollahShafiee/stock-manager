package com.eshop.stockmanager.repository;

import com.eshop.stockmanager.repository.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenProduct_whenCodeIsT1_thenReturnProduct() {
        Product product = productRepository.findByCode("t1").get();
        Assert.assertNotNull("There isn't product with t1 code!",product);
    }

    @Test
    public void givenInStock_whenCodeIsT1_thenReturn100() {
        Product product = productRepository.findByCode("t1").get();
        Assert.assertEquals((long)product.getInStock(),100l);
    }

    @Test
    public void givenProduct_whenNameIsTablet_thenReturnProducts() {
        List<Product> products=productRepository.findByName("tablet");
        Assert.assertTrue(products.size() > 1);
    }
}
